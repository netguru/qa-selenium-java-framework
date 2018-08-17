package steps;

import base.TestingBase;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import utilities.UserType;

public class LogInSteps extends TestingBase {

    @Given("The User is on login page")
    public void theUserIsOnLoginPage() {
        pages.getLogInPage().goTo();
    }


    @When("User provides email: \"([^\"]*)\" and password: \"([^\"]*)\"")
    public void userProvidesEmailAndPassword(String email, String password) {
        pages.getLogInPage().provideEmail(email);
        pages.getLogInPage().providePassword(password);
    }

    @When("User logs in as: \"([^\"]*)\"")
    public void userLogsInAs(String userType) {
        pages.getLogInPage().logInUserAndRememberMe(UserType.valueOf(userType), false);
    }

    @When("User selects Log in button")
    public void userSelectsLogInButton() {
        pages.getLogInPage().clickLogInButton();
    }

    @Then("User is logged in and taken to the dashboard page")
    public void userIsLoggedInAndTakenToTheDashboardPage() {
        Assert.assertTrue(pages.getDashboardPage().isInitialized());
    }

    @When("User selects Logout button")
    public void userSelectsLogoutButton() {
        pages.getDashboardPage().getMainHeaderSection().logOut();
    }

    @Then("User cannot access the dashboard")
    public void userCannotAccessTheDashboard() {
        pages.getDashboardPage().goTo();
        Assert.assertTrue(pages.getLogInPage().isInitialized());
    }

    @When("User does not provide the credentials")
    public void userDoesNotProvideTheCredentials() {
        pages.getLogInPage().clickLogInButton();
    }

    @Then("User cannot login")
    public void userCannotLogin() {
        Assert.assertTrue(pages.getLogInPage().isInitialized());
    }

    @Then("\"([^\"]*)\" message shows up")
    public void messageShowsUp(String alert) {
        Assert.assertEquals(alert, pages.getLogInPage().getAlertText());
    }

    @When("User selects Forgot Password button")
    public void userSelectsForgotPasswordButton() {
        pages.getLogInPage().clickForgotPasswordButton();
    }


    @When("User selects Sign up now button")
    public void userSelectsSignUpNowButton() {
        pages.getLogInPage().clickSignUpNowButton();
    }
}
