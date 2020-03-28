package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddTaskModalScreen extends AbstractScreen {

    @FindBy(name = "title")
    private WebElement titleField;

    @FindBy(name = "date")
    private WebElement dateLimitField;

    @FindBy(name = "time")
    private WebElement timeLimitField;

    @FindBy(name = "text")
    private WebElement tellUsMoreField;

    @FindBy(xpath = "//a[text()='Save']")
    private WebElement saveButton;

    @FindBy(xpath = "//button[text()='Today']")
    private WebElement dateLimitTodayButton;

    @FindBy(xpath = "//button[text()='Ok']")
    private WebElement dateLimitOkButton;

    @FindBy(xpath = "//button[text()='OK']")
    private WebElement timeLimitOkButton;

    private String hoursSelectorLocator = "//div[contains(@class, \'clockpicker-hours\')]//div[@class='clockpicker-tick' and text()=\'[HOURS]\']";
    private String minutesSelectorLocator = "//div[contains(@class, \'clockpicker-minutes\')]//div[@class='clockpicker-tick' and text()=\'[MINUTES]\']";

    public AddTaskModalScreen(WebDriver driver) {
        super(driver);
    }

    public void waitForPageToLoad() {
        waitUtility().waitForElementToBeClickable(driver, saveButton);
    }

    public void dateLimitValueShouldBeToday() {
        // Getting current date
        Date today = new Date();
        // Formatting current date in expected patter for date limit field
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM, yyyy");
        String formattedTextForToday = formatter.format(today);
        String dateLimitFieldText = this.getDateLimitField().getAttribute("value");
        // Validating if the filled value for date limit field is today
        Boolean dateLimitShouldBeToday = dateLimitFieldText.equals(formattedTextForToday);
        Assert.assertTrue(dateLimitShouldBeToday, "Selected date was wrongly selected. It was not today when clicked in Today button.");
    }

    public void timeLimitValueShouldBe(String expectedTime) {
        String timeLimitFieldText = this.getTimeLimitField().getAttribute("value");
        // Validating if the filled value for time limit field is the expected one
        Boolean timeLimitShouldBeExpected = timeLimitFieldText.equals(expectedTime);
        Assert.assertTrue(timeLimitShouldBeExpected, "Selected time was wrongly selected. It was not the inserted one.");
    }

    public void selectHourInTimeLimitPicker(String hour) {
        String locator = hoursSelectorLocator.replace("[HOURS]", hour);
        WebElement hourToSelect = driver.findElement(By.xpath(locator));
        hourToSelect.click();
        waitForHourSelectorToBeInvisible();
    }

    public void selectMinutesInTimeLimitPicker(String minutes) {
        waitForMinuteSelectorToBeVisible();
        String locator = minutesSelectorLocator.replace("[MINUTES]", minutes);
        WebElement minutesToSelect = driver.findElement(By.xpath(locator));
        minutesToSelect.click();
    }

    public void waitForHourSelectorToBeInvisible() {
        String locator = "//div[contains(@class, \'clockpicker-hours\')]";
        waitUtility().waitForElementToBeInvisible(driver, driver.findElement(By.xpath(locator)));
    }

    public void waitForMinuteSelectorToBeVisible() {
        String locator = "//div[contains(@class, \'clockpicker-minutes\')]";
        waitUtility().waitForElementToBeVisible(driver, driver.findElement(By.xpath(locator)));
    }

    public WebElement getDateLimitField() {
        return dateLimitField;
    }

    public WebElement getTellUsMoreField() {
        return tellUsMoreField;
    }

    public WebElement getTimeLimitField() {
        return timeLimitField;
    }

    public WebElement getTitleField() {
        return titleField;
    }

    public WebElement getSaveButton() {
        return saveButton;
    }

    public WebElement getDateLimitTodayButton() {
        return dateLimitTodayButton;
    }

    public WebElement getDateLimitOkButton() {
        return dateLimitOkButton;
    }

    public WebElement getTimeLimitOkButton() {
        return timeLimitOkButton;
    }
}
