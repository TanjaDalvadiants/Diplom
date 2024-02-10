package ru.iteco.fmhandroid.test;


import static ru.iteco.fmhandroid.test.TestUtils.logOut;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.page.LoginPage;
import ru.iteco.fmhandroid.page.NewsPage;
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AppLoginTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    String LOGIN = "login2";
    String PASSWORD = "password2";

    @After
    public void logout() {
        logOut();
    }

    @Test
    public void appLoginTest() {

        LoginPage loginPage = new LoginPage();
        NewsPage newsPage = new NewsPage();

        loginPage.waitUntilPageLoaded();
        loginPage.validatePageLoaded();

        loginPage.typeLogin(LOGIN);
        loginPage.typePassword(PASSWORD);

        loginPage.signIn();

        newsPage.waitUntilPageLoaded();
        newsPage.validatePageLoaded();
    }


}
