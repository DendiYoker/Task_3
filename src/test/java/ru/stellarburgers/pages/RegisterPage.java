package ru.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage extends BasePage {


    private By nameField = By.xpath("//h2[text()='Регистрация']/following-sibling::form//fieldset[.//label[text()='Имя']]//input");
    private By emailField = By.xpath("//h2[text()='Регистрация']/following-sibling::form//fieldset[.//label[text()='Email']]//input");
    private By passwordField = By.xpath("//h2[text()='Регистрация']/following-sibling::form//fieldset[.//label[text()='Пароль']]//input");
    private By registerButton = By.xpath("//h2[text()='Регистрация']/following::button[text()='Зарегистрироваться']");
    private By loginButton = By.xpath("//h2[text()='Регистрация']/following::a[text()='Войти']");

    //Ошибки
    private By userAlreadyExistsErrorElement = By.xpath("//p[text()='Такой пользователь уже существует']");
    private By incorrectPasswordErrorElement = By.xpath("//p[text()='Некорректный пароль']");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввести имя: {name}")
    private void enterName(String name) {
        sendKeys(nameField, name);
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
    public LoginPage clickLoginButton() {
        clickWhenReady(loginButton);
        return new LoginPage(driver);
    }

    @Step("Регистрация пользователя: {name}, {email}")
    public LoginPage registerUser(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        clickWhenReady(registerButton);;
        return new LoginPage(driver);
    }

    @Step("Проверка отображения ошибки регистрации: Некорректный пароль")
    public boolean isIncorrectPasswordErrorDisplayed() {
        attachScreenshot("Состояние страницы перед проверкой ошибки пароля");
        return isElementDisplayed(incorrectPasswordErrorElement);
    }

    @Step("Проверка отображения ошибки регистрации: Такой пользователь уже существует")
    public boolean isUserAlreadyExistErrorDisplayed() {
        attachScreenshot("Состояние страницы перед проверкой ошибки пароля");
        return isElementDisplayed(userAlreadyExistsErrorElement);
    }

}
