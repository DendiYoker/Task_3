package ru.stellarburgers.pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.ByteArrayInputStream;
import java.time.Duration;

public class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected By personalAccountButton = By.xpath("//p[text()='Личный Кабинет']");
    protected By constructorButton = By.xpath("//p[text()='Конструктор']");
    protected By logoStellarBurgers = By.xpath("//div[@class='AppHeader_header__logo__2D0X2']");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Клик по кнопке 'Личный кабинет' до авторизации")
    public LoginPage clickPersonalAccount() {
        clickWhenReady(personalAccountButton);
        return new LoginPage(driver); // ← возвращаем страницу логина
    }

    @Step("Клик по логотипу Stellar Burgers")
    public MainPage clickLogoStellarBurgers() {
        clickWhenReady(logoStellarBurgers);
        return new MainPage(driver);
    }

    @Step("Клик по кнопке 'Конструктор'")
    public MainPage clickConstructorButton() {
        clickWhenReady(constructorButton);
        return new MainPage(driver);
    }

    /**
     * Ожидает видимости элемента и возвращает true, если элемент появился.
     * Если элемент не появился за таймаут, возвращает false.
     */
    protected boolean isElementDisplayed(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Ожидает видимости элемента и кликает по нему.
     */
    protected void clickWhenReady(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    /**
     * Вводит текст в поле.
     */
    protected void sendKeys(By locator, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);
    }

    public void attachScreenshot(String name) {
        if (driver != null) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(name, new ByteArrayInputStream(screenshot));
        }
    }
}
