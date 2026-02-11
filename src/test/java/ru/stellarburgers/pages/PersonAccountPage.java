package ru.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonAccountPage extends BasePage {

    private By profileButton = By.xpath("//a[text()='Профиль']");
    private By historyOrdersButton = By.xpath("//a[text()='История заказов']");
    private By exitProfileButton = By.xpath("//button[text()='Выход']");

    public PersonAccountPage(WebDriver driver) {
        super(driver);
    }

//    @Step("Ввести имя: {name}")
//    private void enterName(String name) {
//        sendKeys(nameField, name);
//    }
//
//    @Step("Ввести email: {email}")
//    private void enterEmail(String email) {
//        sendKeys(emailField, email);
//    }
//
//    @Step("Ввести пароль: {password}")
//    private void enterPassword(String password) {
//        sendKeys(passwordField, password);
//    }
//
    @Step("Клик по кнопке 'Выход'")
    public LoginPage clickLoginButton() {
        clickWhenReady(exitProfileButton);
        return new LoginPage(driver);
    }

    @Step("Клик по кнопке 'Профиль'")
    public void clickProfileButton() {
        clickWhenReady(profileButton);
    }

//
//    @Step("Регистрация пользователя: {name}, {email}")
//    public LoginPage registerUser(String name, String email, String password) {
//        enterName(name);
//        enterEmail(email);
//        enterPassword(password);
//        clickWhenReady(registerButton);;
//        return new LoginPage(driver);
//    }
//
    @Step("Проверка наличия кнопки 'Профиль'")
    public boolean isProfileButtonDisplayed() {
        attachScreenshot("Состояние страницы");
        return isElementDisplayed(profileButton);
    }

//    @Step("Проверка отображения ошибки регистрации: Такой пользователь уже существует")
//    public boolean isUserAlreadyExistErrorDisplayed() {
//        attachScreenshot("Состояние страницы перед проверкой ошибки пароля");
//        return isElementDisplayed(userAlreadyExistsErrorElement);
//    }

}
