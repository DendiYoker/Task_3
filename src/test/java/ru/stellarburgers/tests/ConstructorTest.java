package ru.stellarburgers.tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.stellarburgers.enums.Browser;
import ru.stellarburgers.pages.MainPage;
import ru.stellarburgers.utils.WebDriverFactory;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("4. Раздел 'Конструктор'")
public class ConstructorTest extends BaseTest{

    @ParameterizedTest
    @EnumSource(Browser.class)
    @DisplayName("4.1. Раздел конструктор - позитивный кейс")
    @Description("Проверить, что работает переход к разделу: Соусы")
    void testCheckLinksSectionsSauces(Browser browser){
        driver = WebDriverFactory.createDriver(browser);
        driver.get(baseUrl);

        MainPage mainPage = new MainPage(driver);

        // 1. Клик по разделу "Соусы"
        mainPage.clickSectionSous();
        assertAll(
                () -> assertTrue(mainPage.isSousTabActive(), "Вкладка 'Соусы' не активна"),
                () -> assertFalse(mainPage.isRollsTabActive(), "Вкладка 'Булки' не должна быть активна"),
                () -> assertFalse(mainPage.isToppingsTabActive(), "Вкладка 'Начинки' не должна быть активна")
        );
    }

    @ParameterizedTest
    @EnumSource(Browser.class)
    @DisplayName("4.2. Раздел конструктор - позитивный кейс")
    @Description("Проверить, что работает переход к разделу: Начинки.")
    void testCheckLinksSectionsToppings(Browser browser){
        driver = WebDriverFactory.createDriver(browser);
        driver.get(baseUrl);

        MainPage mainPage = new MainPage(driver);

        // 2. Клик по разделу "Начинки"
        mainPage.clickSectionToppings();
        assertAll(
                () -> assertTrue(mainPage.isToppingsTabActive(), "Вкладка 'Начинки' не активна"),
                () -> assertFalse(mainPage.isRollsTabActive(), "Вкладка 'Булки' не должна быть активна"),
                () -> assertFalse(mainPage.isSousTabActive(), "Вкладка 'Соусы' не должна быть активна")
        );

    }

    @ParameterizedTest
    @EnumSource(Browser.class)
    @DisplayName("4.3. Раздел конструктор - позитивный кейс")
    @Description("Проверить,что работает переход к разделу: Булки.")
    void testCheckLinksSectionsBuns(Browser browser){
        driver = WebDriverFactory.createDriver(browser);
        driver.get(baseUrl);

        MainPage mainPage = new MainPage(driver);
        mainPage.clickSectionToppings();

        // 3. Клик по разделу "Булки"
        mainPage.clickSectionRolls();
        assertAll(
                () -> assertTrue(mainPage.isRollsTabActive(), "Вкладка 'Булки' не активна"),
                () -> assertFalse(mainPage.isSousTabActive(), "Вкладка 'Соусы' не должна быть активна"),
                () -> assertFalse(mainPage.isToppingsTabActive(), "Вкладка 'Начинки' не должна быть активна")
        );
    }

}
