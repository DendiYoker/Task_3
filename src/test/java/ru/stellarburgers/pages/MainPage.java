package ru.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;


public class MainPage extends BasePage{

    private By personalAccountButton = By.xpath("//p[text()='Личный Кабинет']");
    private By signAccountButton = By.xpath("//button[text()='Войти в аккаунт']");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Клик по кнопке 'Личный кабинет'")
    public LoginPage clickPersonalAccount() {
        clickWhenReady(personalAccountButton);
        return new LoginPage(driver); // ← возвращаем страницу логина
    }



}
