package ru.stellarburgers.tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.stellarburgers.dto.CreatedUserData;
import ru.stellarburgers.dto.LoginUserData;
import ru.stellarburgers.enums.Browser;
import ru.stellarburgers.pages.LoginPage;
import ru.stellarburgers.pages.MainPage;
import ru.stellarburgers.pages.RegisterPage;
import ru.stellarburgers.utils.Utilities;
import ru.stellarburgers.utils.WebDriverFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("1. Страница регистрации")
public class RegistrationTest extends BaseTest{

    @ParameterizedTest
    @EnumSource(Browser.class)
    @DisplayName("1.1. Регистрация пользователя - позитивный кейс")
    @Description("Проверка успешной регистрации и перехода на страницу входа")
    void testRegistrationWithCorrectData(Browser browser){
        driver = WebDriverFactory.createDriver(browser);
        driver.get(baseUrl);

        createdUserData = Utilities.createRandomUser();
        loginUserData = new LoginUserData(createdUserData.getEmail(), createdUserData.getPassword());

        LoginPage loginPage = new MainPage(driver).clickPersonalAccount();
        RegisterPage registerPage = loginPage.clickRegisterButton();
        LoginPage loginPageAfterRegistration = registerPage.registerUser(
                createdUserData.getName(), createdUserData.getEmail(), createdUserData.getPassword());

        assertTrue(loginPageAfterRegistration.isLoginPageDisplayed(),
                "Страница входа не отображается после регистрации");

    }

    @ParameterizedTest
    @EnumSource(Browser.class)
    @DisplayName("1.2. Регистрация пользователя - негативный кейс кейс")
    @Description("Проверка ошибки некорректного пароля. Минимальный пароль — шесть символов.")
    void testRegistrationIncorrectPassword(Browser browser){
        driver = WebDriverFactory.createDriver(browser);
        driver.get(baseUrl);

        createdUserData = Utilities.createRandomUserWithIncorrectPassword();

        LoginPage loginPage = new MainPage(driver).clickPersonalAccount();
        RegisterPage registerPage = loginPage.clickRegisterButton();
        registerPage.registerUser(
                createdUserData.getName(), createdUserData.getEmail(), createdUserData.getPassword());
        assertTrue(registerPage.isIncorrectPasswordErrorDisplayed(),
                "Ожидали ошибку регистрации: Некорректный пароль");

    }

    @ParameterizedTest
    @EnumSource(Browser.class)
    @DisplayName("1.3. Регистрация пользователя - негативный кейс кейс")
    @Description("Проверка отображения ошибки регистрации: Такой пользователь уже существует")
    void testRegistrationUserAlreadyExist(Browser browser){
        driver = WebDriverFactory.createDriver(browser);
        driver.get(baseUrl);

        createdUserData = Utilities.createRandomUser();
        registerUserApi(createdUserData);

        LoginPage loginPage = new MainPage(driver).clickPersonalAccount();
        RegisterPage registerPage = loginPage.clickRegisterButton();
        registerPage.registerUser(
                createdUserData.getName(), createdUserData.getEmail(), createdUserData.getPassword());
        assertTrue(registerPage.isUserAlreadyExistErrorDisplayed(),
                "Ожидали ошибку регистрации: Такой пользователь уже существует");
    }


}