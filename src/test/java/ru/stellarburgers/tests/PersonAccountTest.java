package ru.stellarburgers.tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.stellarburgers.dto.LoginUserData;
import ru.stellarburgers.enums.Browser;
import ru.stellarburgers.pages.*;
import ru.stellarburgers.utils.Utilities;
import ru.stellarburgers.utils.WebDriverFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;


@DisplayName("3. Личный кабинет")
public class PersonAccountTest extends BaseTest{

    @ParameterizedTest
    @EnumSource(Browser.class)
    @DisplayName("3.1. Личный кабинет - позитивный кейс")
    @Description("Проверь переход по клику на «Личный кабинет».")
    void testLogin_clickLogInAccountButtonOnMainPage(Browser browser){
        driver = WebDriverFactory.createDriver(browser);
        driver.get(baseUrl);

        createdUserData = Utilities.createRandomUser();
        loginUserData = new LoginUserData(createdUserData.getEmail(), createdUserData.getPassword());

        registerUserApi(createdUserData);

        LoginPage loginPage = new MainPage(driver).clickSignAccountButton();
        MainPage mainPage = loginPage.loginUser(createdUserData.getEmail(), createdUserData.getPassword());

        PersonAccountPage personAccountPage = mainPage.clickPersonalAccountButtonAfterLogin();

        assertTrue(personAccountPage.isProfileButtonDisplayed(),
                "После перехода в личный кабинет не появилась кнопка 'Профиль'");
        addScreenshot();

    }
}
