package steps;

import base.TestBase;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import pages.LogInPage;
import utilities.UserType;

public class StepsHelper extends TestBase {
    @Before
    public void setupTestCase() {
        setupEnvironment();
    }

    @After
    public void ceaseTestCase(Scenario scenario) {
        if (scenario.isFailed())
            takeScreenshot(scenario);
        tearDown();
    }

    @Given("^User is logged in as \"([^\"]*)\"$")
    public void userIsLoggedInAs(String userType) {
        LogInPage logInPage = new LogInPage();
        logInPage.goTo();
        logInPage.logInUserAndRememberMe(UserType.valueOf(userType), false);
    }
}
