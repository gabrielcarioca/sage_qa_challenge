package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NavigationBarScreen extends AbstractScreen {

    @FindBy(xpath="//a[@href='/new-update']")
    private WebElement newPersonButton;

    public NavigationBarScreen(WebDriver driver) {
        super(driver);
    }

    public void waitForNewPersonButton() {
        waitUtility().waitForElementToBeClickable(driver, newPersonButton);
    }

    public WebElement getNewPersonButton() {
        return newPersonButton;
    }
}
