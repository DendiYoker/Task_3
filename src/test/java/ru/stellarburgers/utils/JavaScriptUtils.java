package ru.stellarburgers.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtils {

    public static void smoothScrollToElement(WebDriver driver, WebElement element) {
        // Плавный скролл к элементу
        ((JavascriptExecutor)driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
                element
        );
        // Ждем завершения скролла
        try { Thread.sleep(500); } catch (InterruptedException e) {}
    }

}
