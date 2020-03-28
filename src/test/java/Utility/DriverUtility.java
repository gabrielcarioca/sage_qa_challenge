package Utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class DriverUtility {

    private static DriverUtility singleInstance;

    public static DriverUtility getInstance(){
        if(singleInstance == null){
            singleInstance = new DriverUtility();
        }
        return singleInstance;
    }

    private WebDriver driver;

    private boolean isDriverStarted = false;

    public void startANewChromeDriver() {

        if (!isDriverStarted) {
            // create a new instance of the chrome driver
            WebDriver driver = new ChromeDriver();
            // implicit wait when searching for elements on the page
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            this.driver = driver;
            isDriverStarted = true;
        }
    }

    public void stopRunningDriver() {
        if (isDriverStarted) {
            isDriverStarted = false;
            driver.quit();
            driver = null;
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
