import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import org.testng.annotations.*;
import Listeners.*;

@CucumberOptions(
        features = "src/test/resources/features"
        ,tags = {"@WebServiceRequestFeature"}
        ,glue={"Stepdefs"}
        ,plugin = { "pretty", "html:results" }
        ,monochrome = true)
@Listeners(TestListener.class)
public class TestRunner {

    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass
    public void setUpClass() throws Exception {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature) {
        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }

    @DataProvider
    public Object[][] features() {
        return testNGCucumberRunner.provideFeatures();
    }

    @AfterClass
    public void tearDownClass() throws Exception {
        testNGCucumberRunner.finish();
    }
}
