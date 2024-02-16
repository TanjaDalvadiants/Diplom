package ru.iteco.fmhandroid.test;

import static ru.iteco.fmhandroid.test.TestUtils.logIn;
import static ru.iteco.fmhandroid.test.TestUtils.logOut;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.page.ControlPanelPage;
import ru.iteco.fmhandroid.page.CreatingNewsPage;
import ru.iteco.fmhandroid.page.NavPage;
import ru.iteco.fmhandroid.page.NewsPage;
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class NewsTest {

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

    public static String NEWS_CATEGORY = "Объявление";
    public static String NEWS_TITLE = "Тестовая новость";
    public static String NEWS_DATE = "14.02.2024";
    public static String NEWS_DESCRIPTION = "Тестовое описание";


    @Test
    public void goToNewsPageTest() {

        NavPage navPage = new NavPage();
        navPage.goToNewsPage();

        NewsPage newsPage = new NewsPage();
        newsPage.waitUntilPageLoaded();
        newsPage.validatePageLoaded();
    }

    @Test
    public void addNewsTest() {
        NavPage navPage = new NavPage();
        navPage.goToNewsPage();

        NewsPage newsPage = new NewsPage();
        newsPage.waitUntilPageLoaded();
        newsPage.validatePageLoaded();
        newsPage.goToControlPanel();

        ControlPanelPage controlPanelPage = new ControlPanelPage();
        controlPanelPage.waitUntilPageLoaded();
        controlPanelPage.validatePageLoaded();
        controlPanelPage.addNews();

        CreatingNewsPage creatingNewsPage = new CreatingNewsPage();
        creatingNewsPage.waitUntilPageLoaded();
        creatingNewsPage.validatePageLoaded();
        creatingNewsPage.typeCategory(NEWS_CATEGORY);
        creatingNewsPage.typeTitle(NEWS_TITLE);
        creatingNewsPage.typeDate(NEWS_DATE);
        creatingNewsPage.typeTime();
        creatingNewsPage.typeDescription(NEWS_DESCRIPTION);
        creatingNewsPage.addNews();

        controlPanelPage.waitUntilPageLoaded();

    }

}
