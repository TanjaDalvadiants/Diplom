package ru.iteco.fmhandroid.test;

import static ru.iteco.fmhandroid.test.TestUtils.logIn;
import static ru.iteco.fmhandroid.test.TestUtils.logOut;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.page.AboutPage;
import ru.iteco.fmhandroid.page.NavPage;
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AboutTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @After
    public void logout() {
        logOut();
    }

    @Before
    public void login() {
        logIn();
    }

    @Test
    public void goToAboutPageFromMainTest() {
        NavPage navPage = new NavPage();
        navPage.goToAboutPage();

        AboutPage aboutPage = new AboutPage();
        aboutPage.waitUntilPageLoaded();
        aboutPage.validatePageLoaded();

        Espresso.pressBack();
    }

    @Test
    public void goToAboutPageFromNewsTest() {

        NavPage navPage = new NavPage();
        navPage.goToNewsPage();

        navPage.goToAboutPage();

        AboutPage aboutPage = new AboutPage();
        aboutPage.waitUntilPageLoaded();
        aboutPage.validatePageLoaded();

        Espresso.pressBack();
    }
}
