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

    @Step("Клик по кнопке 'Восстановить пароль'")
    public PasswordRecoveryPage clickRestorePasswordButton() {
        clickWhenReady(restorePasswordButton);
        return new PasswordRecoveryPage(driver); // ← возвращаем страницу регистрации
    }

    @Step("Ввести email: {email}")
    private void enterEmail(String email) {
        sendKeys(emailField, email);
    }

    @Step("Ввести пароль: {password}")
    private void enterPassword(String password) {
        sendKeys(passwordField, password);
    }

    @Step("Клик по кнопке 'Войти'")
    public void clickLoginButton() {
        clickWhenReady(loginButton);
    }

    @Step("Авторизация пользователя: {name}, {email}")
    public MainPage loginUser(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
        return new MainPage(driver);
    }

    @Step("Проверка отображения страницы входа")
    public boolean isLoginPageDisplayed() {
        return isElementDisplayed(loginButton);
    }


}
