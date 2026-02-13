package ru.stellarburgers.tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.stellarburgers.dto.LoginUserData;
import ru.stellarburgers.enums.Browser;
import ru.stellarburgers.pages.LoginPage;
import ru.stellarburgers.pages.MainPage;
import ru.stellarburgers.pages.PasswordRecoveryPage;
import ru.stellarburgers.pages.RegisterPage;
import ru.stellarburgers.utils.Utilities;
import ru.stellarburgers.utils.WebDriverFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;


@DisplayName("2. Страница авторизации")
public class LoginTest extends BaseTest{

    @ParameterizedTest
    @EnumSource(Browser.class)
    @DisplayName("2.1. Авторизация пользователя - позитивный кейс")
    @Description("Вход по кнопке «Войти в аккаунт» на главной")
    void testLogin_clickLogInAccountButtonOnMainPage(Browser browser){
        driver = WebDriverFactory.createDriver(browser);
        driver.get(baseUrl);

        createdUserData = Utilities.createRandomUser();
        loginUserData = new LoginUserData(createdUserData.getEmail(), createdUserData.getPassword());

        registerUserApi(createdUserData);

        LoginPage loginPage = new MainPage(driver).clickSignAccountButton();
        MainPage mainPage = loginPage.loginUser(createdUserData.getEmail(), createdUserData.getPassword());

        assertTrue(mainPage.isSetOrderButtonDisplayed(),
                "После авторизации не появилась кнопка 'Оформить заказ'");
        addScreenshot();

    }

    @ParameterizedTest
    @EnumSource(Browser.class)
    @DisplayName("2.2. Авторизация пользователя - позитивный кейс")
    @Description("вход через кнопку «Личный кабинет»")
    void testLogin_clickPersonAccountButtonOnMainPage(Browser browser){
        driver = WebDriverFactory.createDriver(browser);
        driver.get(baseUrl);

        createdUserData = Utilities.createRandomUser();
        loginUserData = new LoginUserData(createdUserData.getEmail(), createdUserData.getPassword());

        registerUserApi(createdUserData);

        LoginPage loginPage = new MainPage(driver).clickPersonalAccount();
        MainPage mainPage = loginPage.loginUser(createdUserData.getEmail(), createdUserData.getPassword());

        assertTrue(mainPage.isSetOrderButtonDisplayed(),
                "После авторизации не появилась кнопка 'Оформить заказ'");
        addScreenshot();

    }

    @ParameterizedTest
    @EnumSource(Browser.class)
    @DisplayName("2.3. Авторизация пользователя - позитивный кейс")
    @Description("вход через кнопку в форме регистрации")
    void testLogin_LogViaButtonFromRegistrationPage(Browser browser){
        driver = WebDriverFactory.createDriver(browser);
        driver.get(baseUrl);

        createdUserData = Utilities.createRandomUser();
        loginUserData = new LoginUserData(createdUserData.getEmail(), createdUserData.getPassword());

        registerUserApi(createdUserData);

        LoginPage loginPage = new MainPage(driver).clickPersonalAccount();
        RegisterPage registerPage = loginPage.clickRegisterButton();
        LoginPage loginPageAfterBackRegisterPage = registerPage.clickLoginButton();
        MainPage mainPage = loginPageAfterBackRegisterPage.loginUser(createdUserData.getEmail(), createdUserData.getPassword());

        assertTrue(mainPage.isSetOrderButtonDisplayed(),
                "После авторизации не появилась кнопка 'Оформить заказ'");
        addScreenshot();

    }

    @ParameterizedTest
    @EnumSource(Browser.class)
    @DisplayName("2.4. Авторизация пользователя - позитивный кейс")
    @Description("вход через кнопку в форме восстановления пароля.")
    void testLogin_LogViaButtonFromPasswordRecoveryPage(Browser browser){
        driver = WebDriverFactory.createDriver(browser);
        driver.get(baseUrl);

        createdUserData = Utilities.createRandomUser();
        loginUserData = new LoginUserData(createdUserData.getEmail(), createdUserData.getPassword());

        registerUserApi(createdUserData);

        LoginPage loginPage = new MainPage(driver).clickPersonalAccount();
        PasswordRecoveryPage recoveryPage = loginPage.clickRestorePasswordButton();
        LoginPage loginPageAfterBackRecoveryPage = recoveryPage.clickLoginButton();
        MainPage mainPage = loginPageAfterBackRecoveryPage.loginUser(createdUserData.getEmail(), createdUserData.getPassword());

        assertTrue(mainPage.isSetOrderButtonDisplayed(),
                "После авторизации не появилась кнопка 'Оформить заказ'");
        addScreenshot();

    }


}
