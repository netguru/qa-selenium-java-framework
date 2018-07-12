package steps;

import base.TestBase;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.testng.Assert;
import pages.ForgotPasswordPage;
import pages.HomePage;
import pages.LogInPage;
import pages.SignUpPage;
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

    @Given("User is logged in as {string}")
    public void userIsLoggedInAs(String userType) {
        LogInPage logInPage = new LogInPage();
        logInPage.goTo();
        logInPage.logInUserAndRememberMe(UserType.valueOf(userType), false);
    }

    @Then("User is redirected to {string}")
    public void userIsRedirectedToPage(String page) {
        switch (page.toLowerCase()) {
            case "register page":
                SignUpPage signUpPage = new SignUpPage();
                Assert.assertTrue(signUpPage.isInitialized());
                break;
            case "forgot password page":
                ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
                Assert.assertTrue(forgotPasswordPage.isInitialized());
                break;
            case "home page":
                HomePage homePage = new HomePage();
                Assert.assertTrue(homePage.isInitialized());
                break;
        }
    }
}
