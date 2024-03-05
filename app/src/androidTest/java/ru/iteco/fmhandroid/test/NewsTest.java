package ru.iteco.fmhandroid.test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static ru.iteco.fmhandroid.test.AuthUtils.goToMainPage;

import androidx.test.espresso.ViewInteraction;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Feature;
import io.qameta.allure.kotlin.Story;
import ru.iteco.fmhandroid.BaseTest;
import ru.iteco.fmhandroid.page.ControlPanelPage;
import ru.iteco.fmhandroid.page.CreatingNewsPage;
import ru.iteco.fmhandroid.page.MainPage;
import ru.iteco.fmhandroid.page.NavPage;
import ru.iteco.fmhandroid.page.NewsPage;

@Epic("Внутренние страницы приложения")
@Feature("Новости")
public class NewsTest extends BaseTest {

    @Before
    public void login() {
        goToMainPage();
    }

    public static String NEWS_CATEGORY = "Объявление";
    public static String NEWS_TITLE = "Тестовая новость";
    public static String NEWS_DESCRIPTION = "Тестовое описание";


    @Test
    @Story("Работа с новостями")
    @Description("Переход на экран с новостями")
    public void goToNewsPageTest() {
        Allure.step("Вызов меню навигации");
        NavPage navPage = new NavPage();

        Allure.step("Переход на экран News");
        navPage.goToNewsPage();

        NewsPage newsPage = new NewsPage();
        newsPage.waitUntilPageLoaded();
        newsPage.validatePageLoaded();
    }

    @Test
    @Story("Работа с новостями")
    @Description("Добавление новости")
    public void addNewsTest() {
        Allure.step("Вызов меню навигации");
        NavPage navPage = new NavPage();

        Allure.step("Переход на экран News");
        navPage.goToNewsPage();

        NewsPage newsPage = new NewsPage();
        newsPage.waitUntilPageLoaded();
        newsPage.validatePageLoaded();
        newsPage.goToControlPanel();

        Allure.step("Переход в режим редактирования новостей");
        ControlPanelPage controlPanelPage = new ControlPanelPage();
        controlPanelPage.waitUntilPageLoaded();
        controlPanelPage.validatePageLoaded();
        controlPanelPage.addNews();

        Allure.step("Переход на экран создания новости и заполнение всех полей");
        CreatingNewsPage creatingNewsPage = new CreatingNewsPage();
        creatingNewsPage.waitUntilPageLoaded();
        creatingNewsPage.validatePageLoaded();
        creatingNewsPage.typeCategory(NEWS_CATEGORY);
        String newsTitle = NEWS_TITLE + getRandomNumber();
        creatingNewsPage.typeTitle(newsTitle);
        creatingNewsPage.typeDate();
        creatingNewsPage.typeTime();
        creatingNewsPage.typeDescription(NEWS_DESCRIPTION);

        Allure.step("Cозданиe новости");
        creatingNewsPage.addNews();
        controlPanelPage.waitUntilPageLoaded();

        Allure.step("Подтверждение создания новости");
        navPage.goToNewsPage();
        ViewInteraction createdNews = controlPanelPage.findNewsByTitle(newsTitle);

        createdNews.check(matches(isDisplayed()));
    }

    @Test
    @Story("Работа с новостями")
    @Description("Переход на экран с новостями по тапу на all news")
    public void goToNewsPageByAllNewsTest() {
        Allure.step("Переход на экран Main");
        MainPage mainPage = new MainPage();
        mainPage.waitUntilPageLoaded();
        mainPage.validatePageLoaded();

        Allure.step("Тап на all news");
        mainPage.goToAllNewsButton();

        Allure.step("Проверка перехода на страницу с новостями");
        NewsPage newsPage = new NewsPage();
        newsPage.waitUntilPageLoaded();
        newsPage.validatePageLoaded();
    }

    @Test
    @Story("Работа с новостями")
    @Description("Отображение расширенного описания новости")
    public void checkNewsDescriptionVisibleTest() {
        Allure.step("Вызов меню навигации");
        NavPage navPage = new NavPage();

        Allure.step("Переход на экран News");
        navPage.goToNewsPage();

        NewsPage newsPage = new NewsPage();
        newsPage.waitUntilPageLoaded();
        newsPage.validatePageLoaded();
        newsPage.goToControlPanel();

        Allure.step("Переход в режим редактирования новостей");
        ControlPanelPage controlPanelPage = new ControlPanelPage();
        controlPanelPage.waitUntilPageLoaded();
        controlPanelPage.validatePageLoaded();
        controlPanelPage.addNews();

        Allure.step("Переход на экран создания новости и заполнение всех полей");
        CreatingNewsPage creatingNewsPage = new CreatingNewsPage();
        creatingNewsPage.waitUntilPageLoaded();
        creatingNewsPage.validatePageLoaded();
        creatingNewsPage.typeCategory(NEWS_CATEGORY);
        String newsTitle = NEWS_TITLE + getRandomNumber();
        creatingNewsPage.typeTitle(newsTitle);
        creatingNewsPage.typeDate();
        creatingNewsPage.typeTime();
        String newsDescription = NEWS_DESCRIPTION + getRandomNumber();
        creatingNewsPage.typeDescription(newsDescription);

        Allure.step("Cозданиe новости");
        creatingNewsPage.addNews();
        controlPanelPage.waitUntilPageLoaded();

        Allure.step("Подтверждение создания новости");
        navPage.goToNewsPage();
        ViewInteraction createdNews = controlPanelPage.findNewsByTitle(newsTitle);

        Allure.step("Тап на разворачивания новости");
        createdNews.perform(click());

        Allure.step("Проверка отображения описания новости");
        newsPage.checkNewsDescriptionVisible(newsDescription);
    }

    private String getRandomNumber(){
        Random random = new Random();
        return String.valueOf(random.nextInt(100_000));
    }

}
