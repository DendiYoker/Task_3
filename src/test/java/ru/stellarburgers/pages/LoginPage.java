package ru.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {

    private By emailField = By.xpath("//h2[.='Вход']//following::input[@name='name']");
    private By passwordField = By.xpath("//h2[.='Вход']//following::input[@name='Пароль']");
    private By loginButton = By.xpath("//h2[.='Вход']//following::button[text()='Войти']");
    private By registerButton = By.xpath("//h2[.='Вход']//following::a[text()='Зарегистрироваться']");
    private By restorePasswordButton = By.xpath("//h2[.='Вход']//following::a[text()='Восстановить пароль']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Клик по кнопке 'Зарегистрироваться'")
    public RegisterPage clickRegisterButton() {
        clickWhenReady(registerButton);
        return new RegisterPage(driver); // ← возвращаем страницу регистрации
    }

    @Step("Проверка отображения страницы входа")
    public boolean isLoginPageDisplayed() {
        return isElementDisplayed(loginButton);
    }


}
