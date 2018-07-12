package steps;

import base.TestingBase;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import pages.*;
import utilities.UserType;

public class LogInSteps extends TestingBase {
    private LogInPage logInPage = new LogInPage();

    @Given("The User is on login page")
    public void theUserIsOnLoginPage() {
        logInPage.goTo();
    }


    @When("User provides email: {string} and password: {string}")
    public void userProvidesEmailAndPassword(String email, String password) {
        logInPage.provideEmail(email);
        logInPage.providePassword(password);
    }

    @When("User logs in as: {string}")
    public void userLogsInAs(String userType) {
        logInPage.logInUserAndRememberMe(UserType.valueOf(userType), false);
    }

    @When("User selects Log in button")
    public void userSelectsLogInButton() {
        logInPage.clickLogInButton();
    }

    @Then("User is logged in and taken to the dashboard page")
    public void userIsLoggedInAndTakenToTheDashboardPage() {
        DashboardPage dashboardPage = new DashboardPage();
        Assert.assertTrue(dashboardPage.isInitialized());
    }

    @When("User selects Logout button")
    public void userSelectsLogoutButton() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.getMainHeaderSection().logOut();
    }

    @Then("User cannot access the dashboard")
    public void userCannotAccessTheDashboard() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.goTo();

        Assert.assertTrue(logInPage.isInitialized());
    }

    @When("User does not provide the credentials")
    public void userDoesNotProvideTheCredentials() {
        logInPage.clickLogInButton();
    }

    @Then("User cannot login")
    public void userCannotLogin() {
        Assert.assertTrue(logInPage.isInitialized());
    }

    @Then("{string} message shows up")
    public void messageShowsUp(String alert) {
        Assert.assertEquals(alert,logInPage.getAlertText());
    }

    @When("User selects Forgot Password button")
    public void userSelectsForgotPasswordButton() {
        logInPage.clickForgotPasswordButton();
    }


    @When("User selects Sign up now button")
    public void userSelectsSignUpNowButton() {
        logInPage.clickSignUpNowButton();
    }
}
