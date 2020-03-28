package PageObjects;

import Utility.Task;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TasksScreen extends AbstractScreen {

    @FindBy(xpath="//button[@data-target='addtask']")
    private WebElement addATaskButton;

    private String lastTaskLocator = "(//ul[@id='tasklist']/li)[1]";
    private String taskTitleLocator = "//span[@class='title']";
    private String taskDateAndTimeLocator = taskTitleLocator + "/following-sibling::p";
    private String taskNoteLocator = taskDateAndTimeLocator + "/following-sibling::small";

    public TasksScreen(WebDriver driver) {
        super(driver);
    }

    public void waitForTasksScreenToLoad() {
        waitUtility().waitForElementToBeClickable(driver, addATaskButton);
    }

    public WebElement getAddATaskButton() {
        return addATaskButton;
    }

    public Task getLastTask() throws Exception{
        String lastTaskTitle = driver.findElement(By.xpath(lastTaskLocator+taskTitleLocator)).getText();
        String lastTaskDateAndTime = driver.findElement(By.xpath(lastTaskLocator+taskDateAndTimeLocator)).getText();
        String lastTaskNote = driver.findElement(By.xpath(lastTaskLocator+taskNoteLocator)).getText();

        Date lastTaskTimeLimit = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse(lastTaskDateAndTime);

        // Creating the object for the last added task and returning it
        Task lastTask = new Task(lastTaskTitle, lastTaskNote, lastTaskTimeLimit);
        return lastTask;
    }

}
