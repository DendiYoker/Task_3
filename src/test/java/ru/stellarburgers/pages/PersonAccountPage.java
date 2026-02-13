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

    @Step("Клик по кнопке 'Выход'")
    public LoginPage clickExitProfileButton() {
        clickWhenReady(exitProfileButton);
        return new LoginPage(driver);
    }

    @Step("Клик по кнопке 'Профиль'")
    public void clickProfileButton() {
        clickWhenReady(profileButton);
    }

    @Step("Проверка наличия кнопки 'Профиль'")
    public boolean isProfileButtonDisplayed() {
        attachScreenshot("Состояние страницы");
        return isElementDisplayed(profileButton);
    }


}
