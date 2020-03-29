package Stepdefs;

import PageObjects.*;
import Utility.*;
import Utility.PersonData.PersonData;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class PersonListStepDefs extends BaseStepDefs{
    private HomeScreen homeScreen;
    private NavigationBarScreen navigationBarScreen;
    private AddPersonModalScreen addPersonModalScreen;
    private ConfirmDeleteModalScreen confirmDeleteModalScreen;
    private PersonData lastAddedPerson = new PersonData();

    // User list to compare when editing or deleting
    List<PersonData> previousPersonList;
    List<PersonData> currentPersonList;

    @Before(order = 1)
    public void beforeSuite(Scenario scenario) {
        // No need to initialize page objects for webservice requests tests
        if (scenario.getSourceTagNames().contains("@WebServiceRequestFeature")) {
            return;
        }

        System.out.println("Configuration for PersonListStepDefs");

        homeScreen = new HomeScreen(driver());
        navigationBarScreen = new NavigationBarScreen(driver());
        addPersonModalScreen = new AddPersonModalScreen(driver());
        confirmDeleteModalScreen = new ConfirmDeleteModalScreen(driver());
    }

    @Given("^User goes to home page$")
    public void goToHomePage() throws MalformedURLException{
        driver().navigate().to(new URL("http://localhost:3000/"));
        // Waiting for page to load
        navigationBarScreen.waitForNewPersonButton();
        currentPersonList = homeScreen.getPersonList();
    }

    @When("^User clicks in new button$")
    public void clickInNewPersonButton() {
        navigationBarScreen.getNewPersonButton().click();
        // Waiting for add person modal to open
        addPersonModalScreen.waitForAddPersonModalToBeVisible();
    }

    @And("^User fills person data$")
    public void fillPersonDataInModal() {
        String randomEmail = UserUtility.getInstance().generateRandomEmail();
        addPersonModalScreen.getEmailField().sendKeys(randomEmail);
        lastAddedPerson.setUserEmail(randomEmail);

        String randomDocument = UserUtility.getInstance().generateRandomCPF();
        addPersonModalScreen.getDocumentField().sendKeys(randomDocument);
        lastAddedPerson.setUserDocument(randomDocument);

        String randomName = UserUtility.getInstance().generateRandomName();
        addPersonModalScreen.getNameField().sendKeys(randomName);
        lastAddedPerson.setUserName(randomName);

        String randomMotherName = UserUtility.getInstance().generateRandomName();
        addPersonModalScreen.getMotherField().sendKeys(randomMotherName);
        lastAddedPerson.setMotherName(randomMotherName);

        String randomFatherName = UserUtility.getInstance().generateRandomName();
        addPersonModalScreen.getFatherField().sendKeys(randomFatherName);
        lastAddedPerson.setFatherName(randomFatherName);
    }

    @And("^User goes to address data page$")
    public void goToAddressPageInModal() {
        addPersonModalScreen.goToNextPage();
    }

    @And("^User fills address data$")
    public void fillAddressDataInModal() {
        String zipCode = "11461760";
        addPersonModalScreen.getZipCodeField().sendKeys(zipCode);
        lastAddedPerson.setZipCode(zipCode);
        addPersonModalScreen.getAddressField().click();
        addPersonModalScreen.waitForAddressDataToBeLoaded();

        String number = "83";
        addPersonModalScreen.getNumberField().sendKeys(number);
        lastAddedPerson.setNumber(number);
    }

    @And("^User saves the .* person$")
    public void saveNewPerson() {
        addPersonModalScreen.savePerson();
    }

    @Then("^User can see a success pop-up$")
    public void isSuccessPopUpVisible() {
        homeScreen.waitForSuccessPopup();
    }

    @And("^User dismiss the success pop-up$")
    public void dismissSucessPopUp() {
        homeScreen.dismissSucessPopup();
    }

    @Then("^User can see the .* person in the list$")
    public void validatedAddedPersonInPersonList() {
        String lastAddedName = homeScreen.getColumnValueForRow("Nome", 1);
        Assert.assertEquals(lastAddedName, lastAddedPerson.getUserName());
        String lastAddedEmail = homeScreen.getColumnValueForRow("E-mail", 1);
        Assert.assertEquals(lastAddedEmail, lastAddedPerson.getUserEmail());
        String lastAddedDocument = homeScreen.getColumnValueForRow("Documento", 1);
        Assert.assertEquals(lastAddedDocument, lastAddedPerson.getFormattedUserDocument());
    }

    @And("^User opens the modal for .* person$")
    public void openModalForPerson() {
        homeScreen.openPersonInRow(1);
        addPersonModalScreen.waitForAddPersonModalToBeVisible();
    }

    @Then("^User can see all previous .* information already filled in the modal$")
    public void validateAllDataForAddedPerson() {

        String filledEmail = addPersonModalScreen.getEmailField().getAttribute("value");
        Assert.assertEquals(filledEmail, lastAddedPerson.getUserEmail());

        String filledDocument = addPersonModalScreen.getDocumentField().getAttribute("value");
        Assert.assertEquals(filledDocument, lastAddedPerson.getFormattedUserDocument());

        String filledName = addPersonModalScreen.getNameField().getAttribute("value");
        Assert.assertEquals(filledName, lastAddedPerson.getUserName());

        String filledMotherField = addPersonModalScreen.getMotherField().getAttribute("value");
        Assert.assertEquals(filledMotherField, lastAddedPerson.getMotherName());

        String filledFatherField = addPersonModalScreen.getFatherField().getAttribute("value");
        Assert.assertEquals(filledFatherField, lastAddedPerson.getFatherName());

        addPersonModalScreen.goToNextPage();

        String filledZipCode = addPersonModalScreen.getZipCodeField().getAttribute("value");
        Assert.assertEquals(filledZipCode, lastAddedPerson.getZipCode());

        String filledNumber = addPersonModalScreen.getNumberField().getAttribute("value");
        Assert.assertEquals(filledNumber, lastAddedPerson.getNumber());
    }

    @When("^User clicks in the delete button for first person in the list$")
    public void clickInDeletePersonIcon() {
        homeScreen.clickInDeletePersonInRow(1);
    }

    @Then("^User can see a modal to confirm if he/she wants to delete the person$")
    public void waitForConfirmDeletePersonModal() {
        homeScreen.waitForConfirmDeletePersonModal();
    }

    @And("^User clicks in OK to confirm deleting person$")
    public void confirmDeletingPerson() {
        confirmDeleteModalScreen.getOkButton().click();
    }

    @Then("^First person was deleted from the list$")
    public void confirmFirstPersonWasDeleted() {
        navigationBarScreen.waitForNewPersonButton();

        this.previousPersonList = currentPersonList;
        this.currentPersonList = homeScreen.getPersonList();

        // If there is no more user in the list, then we can assume user was deleted correctly
        if (currentPersonList.size() == 0) {
            return;
        }

        Assert.assertEquals(previousPersonList.get(1).getUserName(), currentPersonList.get(0).getUserName());
        Assert.assertEquals(previousPersonList.get(1).getUserEmail(), currentPersonList.get(0).getUserEmail());
        Assert.assertEquals(previousPersonList.get(1).getUserDocument(), currentPersonList.get(0).getUserDocument());
    }

    @And("^User clicks in CANCEL to not delete the person$")
    public void cancelDeletingPerson() {
        confirmDeleteModalScreen.getCancelButton().click();
    }

    @Then("^First person was not deleted from the list$")
    public void confirmFirstPersonWasNotDeleted() {
        navigationBarScreen.waitForNewPersonButton();

        this.previousPersonList = currentPersonList;
        this.currentPersonList = homeScreen.getPersonList();

        Assert.assertEquals(currentPersonList.size(), previousPersonList.size());

        Assert.assertEquals(previousPersonList.get(0).getUserName(), currentPersonList.get(0).getUserName());
        Assert.assertEquals(previousPersonList.get(0).getUserEmail(), currentPersonList.get(0).getUserEmail());
        Assert.assertEquals(previousPersonList.get(0).getUserDocument(), currentPersonList.get(0).getUserDocument());
    }

    @And("^User clear all person data fields$")
    public void clearAllPersonDataFields() {
        addPersonModalScreen.clearAllPersonDataFields();
    }

    @And("^User saves address data for person$")
    public void savePersonAddress() {
        lastAddedPerson.setZipCode(addPersonModalScreen.getZipCodeField().getAttribute("value"));
        lastAddedPerson.setNumber(addPersonModalScreen.getNumberField().getAttribute("value"));
    }
}
