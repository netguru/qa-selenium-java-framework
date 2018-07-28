package steps;

import base.TestingBase;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.testng.Assert;
import utilities.UserType;

public class StepsHelper extends TestingBase {

    @Given("User is logged in as {string}")
    public void userIsLoggedInAs(String userType) {
        pages.getLogInPage().goTo();
        pages.getLogInPage().logInUserAndRememberMe(UserType.valueOf(userType), false);
    }

    @Then("User is redirected to {string}")
    public void userIsRedirectedToPage(String page) {
        switch (page.toLowerCase()) {
            case "register page":
                Assert.assertTrue(pages.getSignUpPage().isInitialized());
                break;
            case "forgot password page":
                Assert.assertTrue(pages.getForgotPasswordPage().isInitialized());
                break;
            case "home page":
                Assert.assertTrue(pages.getHomePage().isInitialized());
                break;
        }
    }
}
