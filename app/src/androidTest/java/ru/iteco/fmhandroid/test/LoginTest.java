package ru.iteco.fmhandroid.test;


import static ru.iteco.fmhandroid.test.AuthUtils.goToMainPage;
import static ru.iteco.fmhandroid.test.AuthUtils.logIn;
import static ru.iteco.fmhandroid.test.AuthUtils.logOut;

import org.junit.Before;
import org.junit.Test;

import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Feature;
import io.qameta.allure.kotlin.Story;
import ru.iteco.fmhandroid.BaseTest;
import ru.iteco.fmhandroid.page.MainPage;

@Epic("Авторизация")
@Feature("Вход в систему")
public class LoginTest extends BaseTest {


    @Before
    public void login() {
        goToMainPage();
        logOut();
    }

    @Test
    @Story("Вход с корректными данными")
    @Description("Проверка возможности входа в систему с корректными учетными данными")
    public void appLoginTestSuccess() {
        logIn();

        MainPage mainPage = new MainPage();

        mainPage.waitUntilPageLoaded();
        mainPage.validatePageLoaded();
    }
}
