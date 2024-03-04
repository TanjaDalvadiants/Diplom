package ru.iteco.fmhandroid.test;

import static ru.iteco.fmhandroid.test.AuthUtils.goToMainPage;

import org.junit.Before;
import org.junit.Test;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Feature;
import io.qameta.allure.kotlin.Story;
import ru.iteco.fmhandroid.page.ControlPanelPage;
import ru.iteco.fmhandroid.page.CreatingNewsPage;
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
    public static String NEWS_DATE = "14.02.2024";
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
        creatingNewsPage.typeTitle(NEWS_TITLE);
        creatingNewsPage.typeDate(NEWS_DATE);
        creatingNewsPage.typeTime();
        creatingNewsPage.typeDescription(NEWS_DESCRIPTION);

        Allure.step("Подтверждение создания новости");
        creatingNewsPage.addNews();

        controlPanelPage.waitUntilPageLoaded();
    }

}
