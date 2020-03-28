package PageObjects;

import Utility.WaitUtility;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;

public class AbstractScreen {

    protected final WebDriver driver;

    public AbstractScreen(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WaitUtility waitUtility() {
        return WaitUtility.getInstance();
    }
}
