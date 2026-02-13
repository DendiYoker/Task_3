package ru.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PasswordRecoveryPage extends BasePage {

    private By loginButton = By.xpath("//h2[.='Восстановление пароля']//following::a[text()='Войти']");

    public PasswordRecoveryPage(WebDriver driver) {
        super(driver);
    }

    @Step("Клик по кнопке 'Войти'")
    public LoginPage clickLoginButton() {
        clickWhenReady(loginButton);
        return new LoginPage(driver); // ← возвращаем страницу регистрации
    }

    @Step("Проверка отображения кнопки входа")
    public boolean isLoginPageDisplayed() {
        return isElementDisplayed(loginButton);
    }


}
