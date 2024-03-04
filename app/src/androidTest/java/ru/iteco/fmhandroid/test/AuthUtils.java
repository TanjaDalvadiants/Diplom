package ru.iteco.fmhandroid.test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.page.LoginPage;
import ru.iteco.fmhandroid.page.MainPage;
import ru.iteco.fmhandroid.page.NavPage;

public class AuthUtils {
    public static String LOGIN = "login2";
    public static String PASSWORD = "password2";

    public static void logOut() {
        LoginPage loginPage = new LoginPage();
        try {
            loginPage.waitUntilPageLoaded();
            loginPage.validatePageLoaded();
        } catch (Exception e) {
            ViewInteraction userButton = onView(withId(R.id.authorization_image_button));
            userButton.check(matches(isDisplayed()));
            userButton.perform(click());

            ViewInteraction logOutButton = onView(withText("Log out"));
            logOutButton.check(matches(isDisplayed()));
            logOutButton.perform(click());
        }
    }

    public static void logIn() {
        LoginPage loginPage = new LoginPage();

        loginPage.waitUntilPageLoaded();
        loginPage.validatePageLoaded();

        loginPage.typeLogin(LOGIN);
        loginPage.typePassword(PASSWORD);

        loginPage.signIn();

        MainPage mainPage = new MainPage();

        mainPage.waitUntilPageLoaded();
    }

    public static void unsuccessfulLogIn(String login, String password) {
        LoginPage loginPage = new LoginPage();

        loginPage.waitUntilPageLoaded();
        loginPage.validatePageLoaded();

        loginPage.typeLogin(login);
        loginPage.typePassword(password);

        loginPage.signIn();

    }


    public static void goToMainPage() {
        MainPage mainPage = new MainPage();
        try {
            mainPage.waitUntilPageLoaded();
            mainPage.validatePageLoaded();
        } catch (Exception e) {
            NavPage navPage = new NavPage();
            if (navPage.checkIfToastButtonVisible()) {
                navPage.goToMainPage();
            } else {
                logIn();
            }
        }
    }
}
