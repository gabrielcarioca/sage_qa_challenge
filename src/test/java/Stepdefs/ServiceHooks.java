package Stepdefs;

import Utility.DriverUtility;
import Utility.SeleniumUtility;
import Utility.UserUtility;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import java.net.MalformedURLException;
import java.util.Collection;


public class ServiceHooks{

    @Before(order = 0)
    public void initializeTest(Scenario scenario) throws MalformedURLException {
        // Code to setup initial configurations
        System.out.println("Initialize Test");

        UserUtility.getInstance().startANewFaker();
        Collection<String> tags = scenario.getSourceTagNames();
        if (tags.contains("@WebServiceRequestFeature")) {
            return;
        }
        // Creating the chrome driver
        DriverUtility.getInstance().startANewChromeDriver();
    }

    @After(order = 0)
    public void embedScreenshot(Scenario scenario) {
        System.out.println("Finishing Test");
        if (scenario.isFailed()) {
            try {
                // Code to capture and embed images in test reports (if scenario fails)
                SeleniumUtility.getInstance().takeScreenshotOnFailure(DriverUtility.getInstance().getDriver());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        DriverUtility.getInstance().stopRunningDriver();
    }
}