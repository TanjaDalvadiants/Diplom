package ru.iteco.fmhandroid.test;

import static ru.iteco.fmhandroid.test.TestUtils.logIn;
import static ru.iteco.fmhandroid.test.TestUtils.logOut;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import ru.iteco.fmhandroid.page.MainPage;
import ru.iteco.fmhandroid.page.MissionPage;
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class MissionTest {
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
    public void goToAboutPageTest() {
        MainPage mainPage = new MainPage();

        mainPage.waitUntilPageLoaded();
        mainPage.validatePageLoaded();

        mainPage.goToMissionPage();

        MissionPage missionPage = new MissionPage();
        missionPage.waitUntilPageLoaded();
        missionPage.validatePageLoaded();
    }

}
