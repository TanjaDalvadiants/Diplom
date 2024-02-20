package ru.iteco.fmhandroid.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.iteco.fmhandroid.BuildConfig
import ru.iteco.fmhandroid.api.qualifier.Authorized
import ru.iteco.fmhandroid.api.qualifier.NonAuthorized
import ru.iteco.fmhandroid.api.qualifier.Refresh
import ru.iteco.fmhandroid.auth.AppAuth
import ru.iteco.fmhandroid.repository.authRepository.AuthRepository
import java.security.KeyStore
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.inject.Provider
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager
import javax.security.cert.CertificateException


@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    @Provides
    fun loggingInterceptor() = HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

    @NonAuthorized
    @Provides
    fun nonAuthorizedOkhttp(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return getUnsafeOkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .build()
    }

    @NonAuthorized
    @Provides
    fun provideNonAuthorizedRetrofit(@NonAuthorized client: OkHttpClient): Retrofit =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL).client(client).build()

    private fun getUnsafeOkHttpClient(): OkHttpClient {
        return try {
            return try {
                // Create a trust manager that does not validate certificate chains
                val trustAllCerts = arrayOf<TrustManager>(
                    object : X509TrustManager {
                        @Throws(CertificateException::class)
                        override fun checkClientTrusted(
                            chain: Array<X509Certificate?>?,
                            authType: String?
                        ) {
                        }

                        @Throws(CertificateException::class)
                        override fun checkServerTrusted(
                            chain: Array<X509Certificate?>?,
                            authType: String?
                        ) {
                        }

                        override fun getAcceptedIssuers(): Array<X509Certificate?>? {
                            return arrayOf()
                        }
                    }
                )


                // Install the all-trusting trust manager
                val sslContext = SSLContext.getInstance("SSL")
                sslContext.init(null, trustAllCerts, SecureRandom())
                // Create an ssl socket factory with our all-trusting manager
                val sslSocketFactory = sslContext.socketFactory
                val trustManagerFactory: TrustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
                trustManagerFactory.init(null as KeyStore?)
                val trustManagers: Array<TrustManager> =
                    trustManagerFactory.trustManagers
                check(!(trustManagers.size != 1 || trustManagers[0] !is X509TrustManager)) {
                    "Unexpected default trust managers:" + trustManagers.contentToString()
                }

                val trustManager =
                    trustManagers[0] as X509TrustManager


                val builder = OkHttpClient.Builder()
                builder.sslSocketFactory(sslSocketFactory, trustManager)
                builder.hostnameVerifier(HostnameVerifier { _, _ -> true })
                builder.build()
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @Authorized
    @Provides
    fun provideAuthorizedRetrofit(@Authorized client: OkHttpClient): Retrofit =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL).client(client).build()

    @Authorized
    @Provides
    fun authorizedOkhttp(
        interceptor: HttpLoggingInterceptor,
        appAuth: AppAuth,
        authRepositoryProvider: Provider<AuthRepository>
    ): OkHttpClient {
        val authInterceptor = AuthInterceptor(appAuth)
        val refreshAuthenticator = RefreshAuthenticator(authRepositoryProvider, appAuth)
        return getUnsafeOkHttpClient().newBuilder().addInterceptor(interceptor).addInterceptor(authInterceptor)
            .authenticator(refreshAuthenticator).build()
    }



    @Refresh
    @Provides
    fun refreshOkhttp(interceptor: HttpLoggingInterceptor, appAuth: AppAuth): OkHttpClient {
        val refreshInterceptor = RefreshInterceptor(appAuth)
        return getUnsafeOkHttpClient().newBuilder().addInterceptor(refreshInterceptor).addInterceptor(interceptor)
            .build()
    }

    @Refresh
    @Provides
    fun provideRefreshRetrofit(@Refresh client: OkHttpClient): Retrofit =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL).client(client).build()
}
