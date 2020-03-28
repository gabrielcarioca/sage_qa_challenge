package PageObjects;

import Utility.WaitUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpModalScreen extends AbstractScreen{

    @FindBy(name = "name")
    private WebElement nameField;

    @FindBy(name = "login")
    private WebElement loginField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//a[text()='Save']")
    private WebElement saveButton;

    public SignUpModalScreen(WebDriver driver) {
        super(driver);
    }

    public WebElement getNameField() {
        return nameField;
    }

    public WebElement getLoginField() {
        return loginField;
    }

    public WebElement getPasswordField() {
        return passwordField;
    }

    public WebElement getSaveButton() {
        return saveButton;
    }

    public void waitForSignUpModal() {
        WaitUtility.getInstance().waitForElementToBeVisible(driver, nameField);
    }
}
