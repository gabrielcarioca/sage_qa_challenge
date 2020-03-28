package PageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddPersonModalScreen extends AbstractScreen {


    @FindBy(xpath = "//div[contains(@class, 'MuiContainer-root') and .//span[text()='Dados da Pessoa']]")
    private WebElement addPersonModal;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(id = "document")
    private WebElement documentField;

    @FindBy(id = "mother")
    private WebElement motherField;

    @FindBy(id = "father")
    private WebElement fatherField;

    @FindBy(xpath = "//button[./span[text()='Próximo']]")
    private WebElement nextPageButton;

    @FindBy(id = "zipCode")
    private WebElement zipCodeField;

    @FindBy(id = "number")
    private WebElement numberField;

    @FindBy(xpath = "//button[./span[text()='Salvar']]")
    private WebElement saveButton;

    @FindBy(id = "address")
    private WebElement addressField;

    public AddPersonModalScreen(WebDriver driver) {
        super(driver);
    }

    public void waitForAddPersonModalToBeVisible() {
        waitUtility().waitForElementToBeVisible(driver, addPersonModal);
    }

    public WebElement getDocumentField() {
        return documentField;
    }

    public WebElement getEmailField() {
        return emailField;
    }

    public WebElement getFatherField() {
        return fatherField;
    }

    public WebElement getMotherField() {
        return motherField;
    }

    public WebElement getNameField() {
        return nameField;
    }

    public WebElement getZipCodeField() {
        return zipCodeField;
    }

    public WebElement getNumberField() {
        return numberField;
    }

    public WebElement getAddressField() {
        return addressField;
    }

    public void goToNextPage() {
        nextPageButton.click();
        waitUtility().waitForElementToBeClickable(driver, zipCodeField);
    }

    public void savePerson() {
        saveButton.click();
    }

    public void waitForAddressDataToBeLoaded() {
        waitUtility().waitForFieldToHaveAnyText(driver, addressField);
    }

    public void clearAllPersonDataFields() {
        waitUtility().waitForElementToBeVisible(driver, emailField);
        clearFieldText(emailField);
        clearFieldText(documentField);
        clearFieldText(nameField);
        clearFieldText(motherField);
        clearFieldText(fatherField);
    }

    // For some reason the field.clear() was not working and since I didn't have the proper time to debug
    // I've implemented this workaround
    public void clearFieldText(WebElement field) {
        field.click();
        while (!field.getAttribute("value").isEmpty()) {
            field.sendKeys(Keys.BACK_SPACE);
        }
    }

}
