package ru.stellarburgers.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import ru.stellarburgers.enums.Browser;

import java.io.File;


public class WebDriverFactory {

    public static WebDriver createDriver(Browser browser) {
        WebDriver driver;

        switch (browser) {
//            case FIREFOX:
//                FirefoxOptions firefoxOptions = new FirefoxOptions();
//                // Опциональные настройки:
//                // firefoxOptions.addArguments("--headless");
//                // firefoxOptions.addArguments("--width=1920");
//                // firefoxOptions.addArguments("--height=1080");
//                // WebDriverManager сам скачает нужный geckodriver
//                WebDriverManager.firefoxdriver().setup();
//                driver = new FirefoxDriver(firefoxOptions);
//                break;
//
//            case YANDEX:
//                // 1. Указываем путь к YandexDriver
//                System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/yandexdriver.exe");
//
//                // 2. Настройки браузера
//                ChromeOptions yandexOptions = new ChromeOptions();
//                yandexOptions.setBinary("C:\\Users\\Denis\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
//                yandexOptions.addArguments("--start-maximized");
//                yandexOptions.addArguments("--disable-notifications");
//                yandexOptions.addArguments("--disable-blink-features=AutomationControlled");
//                yandexOptions.addArguments("--remote-allow-origins=*"); // рекомендуется для новых версий
//
//                // 3. Создаём драйвер (используем ChromeDriver, но с yandexdriver.exe)
//                driver = new ChromeDriver(yandexOptions);
//                break;

            case CHROME:
            default:
                //ChromeOptions options = new ChromeOptions();
                //options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
        }

        // Общие настройки
        //driver.manage().window().maximize();
        return driver;
    }

}