package ru.iteco.fmhandroid.test;


import static ru.iteco.fmhandroid.test.TestUtils.logIn;
import static ru.iteco.fmhandroid.test.TestUtils.logOut;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.page.MainPage;
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);


    @After
    public void logout() {
        logOut();
    }

    @Test
    public void appLoginTestSuccess() {
        logIn();

        MainPage mainPage = new MainPage();

        mainPage.waitUntilPageLoaded();
        mainPage.validatePageLoaded();
    }

//    @Test
//    public void appLoginTestNotSuccess() {
//        LoginPage loginPage = new LoginPage();
//        MainPage mainPage = new MainPage();
//
//        loginPage.waitUntilPageLoaded();
//        loginPage.validatePageLoaded();
//
//        loginPage.typeLogin(LOGIN);
//        loginPage.typePassword(PASSWORD);
//
//        loginPage.signIn();
//
//        mainPage.waitUntilPageLoaded();
//        mainPage.validatePageLoaded();
//    }


}
