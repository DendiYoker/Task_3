package ru.stellarburgers.tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import ru.stellarburgers.dto.CreatedUserData;
import ru.stellarburgers.dto.LoginUserData;

import java.io.ByteArrayInputStream;

@ExtendWith(AllureJunit5.class)
public abstract class BaseTest extends BaseApiTest {

    protected WebDriver driver;                // создаётся в каждом тесте
    protected final String baseUrl = "https://stellarburgers.education-services.ru/";

    protected CreatedUserData createdUserData; //данные для регистрации
    protected LoginUserData loginUserData;     //данные для авторизации

    @RegisterExtension
    TestWatcher watcher = new TestWatcher() {

        @Override
        public void testFailed(ExtensionContext context, Throwable cause) {
            if (driver != null) {
                addScreenshot();
                driver.quit();
                driver = null;
            }
        }

        @Override
        public void testSuccessful(ExtensionContext context) {
            if (driver != null) {
                driver.quit();
                driver = null;
            }
        }
    };

    public void addScreenshot() {
        if (driver != null) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("screenshot", new ByteArrayInputStream(screenshot));
        }
    }

    @AfterEach
    protected void cleanUpApi() {
        if (loginUserData != null) {
            deleteUserIfExists(loginUserData);
            loginUserData = null;
        }
        if (createdUserData != null) {
            createdUserData = null;
        }
    }
}