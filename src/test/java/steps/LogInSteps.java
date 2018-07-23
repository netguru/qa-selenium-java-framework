package steps;

import base.TestingBase;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import utilities.UserType;

public class LogInSteps extends TestingBase {

    @Given("The User is on login page")
    public void theUserIsOnLoginPage() {
        context.pages.getLogInPage().goTo();
    }


    @When("User provides email: {string} and password: {string}")
    public void userProvidesEmailAndPassword(String email, String password) {
        context.pages.getLogInPage().provideEmail(email);
        context.pages.getLogInPage().providePassword(password);
    }

    @When("User logs in as: {string}")
    public void userLogsInAs(String userType) {
        context.pages.getLogInPage().logInUserAndRememberMe(UserType.valueOf(userType), false);
    }

    @When("User selects Log in button")
    public void userSelectsLogInButton() {
        context.pages.getLogInPage().clickLogInButton();
    }

    @Then("User is logged in and taken to the dashboard page")
    public void userIsLoggedInAndTakenToTheDashboardPage() {
        Assert.assertTrue(context.pages.getDashboardPage().isInitialized());
    }

    @When("User selects Logout button")
    public void userSelectsLogoutButton() {
        context.pages.getDashboardPage().getMainHeaderSection().logOut();
    }

    @Then("User cannot access the dashboard")
    public void userCannotAccessTheDashboard() {
        context.pages.getDashboardPage().goTo();
        Assert.assertTrue(context.pages.getLogInPage().isInitialized());
    }

    @When("User does not provide the credentials")
    public void userDoesNotProvideTheCredentials() {
        context.pages.getLogInPage().clickLogInButton();
    }

    @Then("User cannot login")
    public void userCannotLogin() {
        Assert.assertTrue(context.pages.getLogInPage().isInitialized());
    }

    @Then("{string} message shows up")
    public void messageShowsUp(String alert) {
        Assert.assertEquals(alert, context.pages.getLogInPage().getAlertText());
    }

    @When("User selects Forgot Password button")
    public void userSelectsForgotPasswordButton() {
        context.pages.getLogInPage().clickForgotPasswordButton();
    }


    @When("User selects Sign up now button")
    public void userSelectsSignUpNowButton() {
        context.pages.getLogInPage().clickSignUpNowButton();
    }
}
