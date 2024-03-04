package ru.iteco.fmhandroid.test;


import static ru.iteco.fmhandroid.test.AuthUtils.LOGIN;
import static ru.iteco.fmhandroid.test.AuthUtils.logIn;
import static ru.iteco.fmhandroid.test.AuthUtils.logOut;
import static ru.iteco.fmhandroid.test.AuthUtils.unsuccessfulLogIn;

import org.junit.Before;
import org.junit.Test;

import io.qameta.allure.Allure;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Feature;
import io.qameta.allure.kotlin.Story;
import ru.iteco.fmhandroid.BaseTest;
import ru.iteco.fmhandroid.page.LoginPage;
import ru.iteco.fmhandroid.page.MainPage;

@Epic("Авторизация")
@Feature("Вход в систему")
public class LoginTest extends BaseTest {

    @Before
    public void init() {
        logOut();
    }

    public static String EMPTY_STRING = "";
    public static String INCORRECT_LOGIN = "loggggin";
    public static String INCORRECT_PASSWORD = "passwoed";

    @Test
    @Story("Вход с корректными данными")
    @Description("Проверка возможности входа в систему с корректными учетными данными")
    public void appLoginTestSuccess() {
        logIn();

        MainPage mainPage = new MainPage();

        mainPage.waitUntilPageLoaded();
        mainPage.validatePageLoaded();
    }

    @Test
    @Story("Вход с неверным паролем")
    @Description("Проверка возможности входа в систему с некорректным паролем")
    public void appLoginTestIncorrectPassword() {
        unsuccessfulLogIn(LOGIN, INCORRECT_PASSWORD);
        LoginPage loginPage = new LoginPage();
        loginPage.waitUntilErrorMessageLoaded();

        Allure.step("Убедиться, что остались на странице логина");
        loginPage.validatePageLoaded();
    }

    @Test
    @Story("Вход с неверным логином и паролем")
    @Description("Проверка возможности входа в систему с некорректным логином и паролем")
    public void appLoginTestIncorrectLoginAndPassword() {
        unsuccessfulLogIn(INCORRECT_LOGIN, INCORRECT_PASSWORD);
        LoginPage loginPage = new LoginPage();
        loginPage.waitUntilErrorMessageLoaded();

        Allure.step("Убедиться, что остались на странице логина");
        loginPage.validatePageLoaded();
    }

    @Test
    @Story("Вход с пустыми логином и паролем")
    @Description("Проверка возможности входа в систему с пустыми логином и паролем")
    public void appLoginTestEmptyLoginAndPassword() {
        unsuccessfulLogIn(EMPTY_STRING, EMPTY_STRING);
        LoginPage loginPage = new LoginPage();
        loginPage.waitUntilErrorMessageLoaded();

        Allure.step("Убедиться, что остались на странице логина");
        loginPage.validatePageLoaded();
    }
}
