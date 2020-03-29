package PageObjects;

import Utility.PersonData.PersonData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeScreen extends AbstractScreen {

    @FindBy(xpath = "//div[@id='alert-dialog-title' and ./h2[text()='Status da operação']]/following-sibling::div[./p[text()='Sua operação foi concluída com sucesso!']]")
    private WebElement successPopUp;

    @FindBy(xpath = "//button[./span[text()='Ok']]")
    private WebElement okButton;

    private String personRowLocator = "//tr[@class='MuiTableRow-root'][[ROW_NO]]";
    private String personTableCellLocator = personRowLocator + "/td[[COLUMN_NO]]";
    private Map<String, Integer> columnIndexes = new HashMap<String, Integer>() {{
            put("Nome", 1);
            put("E-mail", 2);
            put("Documento", 3);
    }};
    private String deletePersonInRowLocator = "//tr[@class='MuiTableRow-root'][[ROW_NO]]/td[4]/button";

    @FindBy(xpath = "//div[@role='dialog' and .//h2[text()='Deletar pessoa'] and .//p[contains(text(), 'Você deseja realmente deletar')]]")
    private WebElement confirmDeletePersonModal;

    public HomeScreen(WebDriver driver) {
        super(driver);
    }

    public void waitForSuccessPopup() {
        waitUtility().waitForElementToBeVisible(driver, successPopUp);
    }

    public void dismissSucessPopup() {
        okButton.click();
    }

    public String getColumnValueForRow(String columnName, int rowNumber) {
        String locator = personTableCellLocator.replace("[ROW_NO]", String.valueOf(rowNumber));
        locator = locator.replace("[COLUMN_NO]", String.valueOf(columnIndexes.get(columnName)));

        String textContent = driver.findElement(By.xpath(locator)).getText();
        return textContent;
    }

    public void openPersonInRow(int rowNumber) {
        driver.findElement(
                By.xpath(personRowLocator.replace("[ROW_NO]", String.valueOf(rowNumber)))
        ).click();
    }

    public void clickInDeletePersonInRow(int rowNumber) {
        driver.findElement(
                By.xpath(deletePersonInRowLocator.replace("[ROW_NO]", String.valueOf(rowNumber)))
        ).click();
    }
    // Getting person list from the UI
    public List<PersonData> getPersonList() {
        List<WebElement> personListElements = driver.findElements(By.xpath(personRowLocator.replace("[[ROW_NO]]", "")));
        List<PersonData> personList = new ArrayList<>();
        for (int i = 0; i < personListElements.size(); i++) {
            PersonData person = new PersonData();
            person.setUserName(personListElements.get(i).findElement(By.xpath("./td[1]")).getText());
            person.setUserEmail(personListElements.get(i).findElement(By.xpath("./td[2]")).getText());
            person.setUserDocument(personListElements.get(i).findElement(By.xpath("./td[3]")).getText());
            personList.add(person);
        }
        return personList;
    }

    public void waitForConfirmDeletePersonModal() {
        waitUtility().waitForElementToBeVisible(driver, confirmDeletePersonModal);
    }

}
