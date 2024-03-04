package ru.iteco.fmhandroid.test;

import static ru.iteco.fmhandroid.test.AuthUtils.goToMainPage;

import androidx.test.espresso.Espresso;

import org.junit.Before;
import org.junit.Test;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Feature;
import io.qameta.allure.kotlin.Story;
import ru.iteco.fmhandroid.BaseTest;
import ru.iteco.fmhandroid.page.AboutPage;
import ru.iteco.fmhandroid.page.NavPage;

@Epic("Внутренние страницы приложения")
@Feature("О приложении")
public class AboutTest extends BaseTest {

    @Before
    public void login() {
        goToMainPage();
    }

    @Test
    @Story("Переход на экран О Приложении")
    @Description("Переход на экран О Приложении с экрана Main")
    public void goToAboutPageFromMainTest() {
        Allure.step("Вызвать меню");
        NavPage navPage = new NavPage();
        Allure.step("Перейти на страницу О приложении");
        navPage.goToAboutPage();

        AboutPage aboutPage = new AboutPage();
        aboutPage.waitUntilPageLoaded();
        aboutPage.validatePageLoaded();

        Espresso.pressBack();
    }

    @Test
    @Story("Переход на экран О Приложении")
    @Description("Переход на экран О Приложении с экрана News")
    public void goToAboutPageFromNewsTest() {
        Allure.step("Перейти на экран News");
        NavPage navPage = new NavPage();
        navPage.goToNewsPage();

        Allure.step("Перейти на страницу О приложении");
        navPage.goToAboutPage();

        AboutPage aboutPage = new AboutPage();
        aboutPage.waitUntilPageLoaded();
        aboutPage.validatePageLoaded();

        Espresso.pressBack();
    }
}
