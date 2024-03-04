package ru.iteco.fmhandroid.test;

import static ru.iteco.fmhandroid.test.AuthUtils.goToMainPage;

import org.junit.Before;
import org.junit.Test;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Feature;
import io.qameta.allure.kotlin.Story;
import ru.iteco.fmhandroid.BaseTest;
import ru.iteco.fmhandroid.page.MainPage;
import ru.iteco.fmhandroid.page.MissionPage;

@Epic("Внутренние страницы приложения")
@Feature("Цитаты")
public class MissionTest extends BaseTest {
    @Before
    public void login() {
        goToMainPage();
    }

    @Test
    @Story("Переход на экран Цитаты")
    @Description("Переход на экран Цитаты с экрана Main")
    public void goToAboutPageTest() {
        Allure.step("Перейти на экран Main");
        MainPage mainPage = new MainPage();

        mainPage.waitUntilPageLoaded();
        mainPage.validatePageLoaded();

        Allure.step("Перейти на экран с цитатами");
        mainPage.goToMissionPage();

        MissionPage missionPage = new MissionPage();
        missionPage.waitUntilPageLoaded();
        missionPage.validatePageLoaded();
    }

}
