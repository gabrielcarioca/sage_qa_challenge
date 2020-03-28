package Utility;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebDriver;

import java.util.function.Function;

public class WaitUtility {

    private static WaitUtility singleInstance;
    private long timeoutInSeconds = 5;

    public static WaitUtility getInstance(){
        if(singleInstance == null){
            singleInstance = new WaitUtility();
        }
        return singleInstance;
    }


    public void waitForElementToBeClickable(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementToBeVisible (WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToBeInvisible (WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitForFieldToHaveAnyText (WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(new Function<WebDriver, Boolean>() {
           public Boolean apply(WebDriver driver) {
               return !element.getAttribute("value").isEmpty();
           }
        });
    }

    public void waitForFieldTextToBeCleared (WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver driver) {
                return element.getAttribute("value").isEmpty();
            }
        });
    }
}