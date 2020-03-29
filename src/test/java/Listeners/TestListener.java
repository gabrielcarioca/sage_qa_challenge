package Listeners;

import Utility.DriverUtility;
import Utility.SeleniumUtility;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestListener extends TestListenerAdapter {
    @Override
    public void onTestFailure(ITestResult tr) {
        System.out.println("Running on test failure");
        SeleniumUtility.getInstance().takeScreenshotOnFailure(DriverUtility.getInstance().getDriver());
        super.onTestFailure(tr);
    }
}
