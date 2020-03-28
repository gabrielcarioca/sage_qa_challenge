package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConfirmDeleteModalScreen extends AbstractScreen {

    @FindBy(xpath = "//div[@role='dialog']//span[@class='MuiButton-label' and text()='Ok']")
    private WebElement okButton;

    @FindBy(xpath = "//div[@role='dialog']//span[@class='MuiButton-label' and text()='Cancelar']")
    private WebElement cancelButton;

    public ConfirmDeleteModalScreen(WebDriver driver) {
        super(driver);
    }

    public WebElement getCancelButton() {
        return cancelButton;
    }

    public WebElement getOkButton() {
        return okButton;
    }
}
