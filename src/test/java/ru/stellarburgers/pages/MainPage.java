package ru.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import ru.stellarburgers.utils.Utilities;


public class MainPage extends BasePage{

    private By collectBurgerField = By.xpath("//h1[text()='Соберите бургер']");

    private By tabRolls = By.xpath("//span[text()='Булки']/parent::div");
    private By tabSous = By.xpath("//span[text()='Соусы']/parent::div");
    private By tabToppings = By.xpath("//span[text()='Начинки']/parent::div");

    private By signAccountButton = By.xpath("//button[text()='Войти в аккаунт']");
    private By setOrderButton = By.xpath("//button[text()='Оформить заказ']");


    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Клик по разделу 'Булки'")
    public void clickSectionRolls() {
        clickWhenReady(tabRolls);
        Utilities.waitFor(2000);
        attachScreenshot("После клика на Булки");
    }

    @Step("Клик по разделу 'Соусы'")
    public void clickSectionSous() {
        clickWhenReady(tabSous);
        Utilities.waitFor(2000);
        attachScreenshot("После клика на Соусы");
    }

    @Step("Клик по разделу 'Начинки'")
    public void clickSectionToppings() {
        clickWhenReady(tabToppings);
        Utilities.waitFor(2000);
        attachScreenshot("После клика на Начинки");

    }

    /**
     * Проверяет, активна ли вкладка по локатору.
     * Активность определяется наличием класса 'tab_tab_type_current__2BEPc'.
     */
    private boolean isTabActive(By tabLocator) {
        WebElement tab = driver.findElement(tabLocator);
        String classValue = tab.getAttribute("class");
        return classValue.contains("tab_tab_type_current__2BEPc");
    }

    @Step("Проверка: вкладка 'Булки' ?")
    public boolean isRollsTabActive() {
        return isTabActive(tabRolls);
    }

    @Step("Проверка: вкладка 'Соусы' активна?")
    public boolean isSousTabActive() {
        return isTabActive(tabSous);
    }

    @Step("Проверка: вкладка 'Начинки' активна?")
    public boolean isToppingsTabActive() {
        return isTabActive(tabToppings);
    }

    @Step("Клик по кнопке 'Войти в аккаунт'")
    public LoginPage clickSignAccountButton() {
        clickWhenReady(signAccountButton);
        return new LoginPage(driver); // ← возвращаем страницу логина
    }

    @Step("Клик по кнопке 'Личный кабинет' после авторизации")
    public PersonAccountPage clickPersonalAccountButtonAfterLogin() {
        clickWhenReady(personalAccountButton);
        return new PersonAccountPage(driver); // ← возвращаем страницу логина
    }

    @Step("Проверка отображения кнопки: 'Оформить заказ'")
    public boolean isSetOrderButtonDisplayed() {
        return isElementDisplayed(setOrderButton);
    }

    @Step("Проверка отображения надписи: 'Соберите бургер'")
    public boolean isCollectBurgerFieldDisplayed() {
        return isElementDisplayed(collectBurgerField);
    }

}
