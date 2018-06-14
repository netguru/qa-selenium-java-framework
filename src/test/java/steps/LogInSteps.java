package steps;

import base.TestBase;
import base.UserType;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import pages.*;

import java.io.IOException;

public class LogInSteps extends TestBase {
    LogInPage logInPage;

    @Before
    public void beforeScenario() throws IOException {
        setupEnvironment();

        logInPage = new LogInPage(driver);
    }

    @After
    public void afterScenario() {
        tearDown();
    }

    @Given("^The User is on login page$")
    public void theUserIsOnLoginPage() {
        logInPage.goTo();
    }

    @Then("^They should see the Log in button$")
    public void theyShouldSeeTheLogInButton() {
        Assert.assertTrue(logInPage.isInitialized());
    }

    @When("^User provides email: \"([^\"]*)\" and password: \"([^\"]*)\"$")
    public void userProvidesEmailAndPassword(String email, String password) {
        logInPage.provideEmail(email);
        logInPage.providePassword(password);
    }

    @When("^User logs in as: \"([^\"]*)\"$")
    public void userLogsInAs(String userType) throws IOException {
        logInPage.logIn(UserType.valueOf(userType), false);
    }

    @And("^User selects Log in button$")
    public void userSelectsLogInButton() {
        logInPage.clickLogInButton();
    }

    @Then("^User is logged in and taken to the dashboard page$")
    public void userIsLoggedInAndTakenToTheDashboardPage() throws IOException {
        DashboardPage dashboardPage = new DashboardPage(driver);

        Assert.assertTrue(dashboardPage.isInitialized());
    }

    @Given("^User is logged in$")
    public void userIsLoggedIn() {
        logInPage.logIn("consumer_unpaid@netguru.pl", "password", false);
    }

    @When("^User selects Logout button$")
    public void userSelectsLogoutButton() throws IOException {
        DashboardPage dashboardPage = new DashboardPage(driver);

        Assert.assertTrue(dashboardPage.isInitialized());

        dashboardPage.getMainHeaderSection().logOut();
    }

    @Then("^User is redirected to home page$")
    public void userIsRedirectedToHomePage() throws IOException {
        HomePage homePage = new HomePage(driver);

        Assert.assertTrue(homePage.isInitialized());
    }

    @And("^User cannot access the dashboard$")
    public void userCannotAccessTheDashboard() throws IOException {
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.goTo();

        Assert.assertTrue(logInPage.isInitialized());
    }

    @When("^User does not provide the credentials$")
    public void userDoesNotProvideTheCredentials() {
        logInPage.clickLogInButton();
    }

    @Then("^User cannot login$")
    public void userCannotLogin() {
        Assert.assertTrue(logInPage.isInitialized());
    }

    @And("^\"([^\"]*)\" message shows up$")
    public void messageShowsUp(String alert) {
        Assert.assertTrue(alert.equals(logInPage.getAlertText()));
    }

    @When("^User selects Forgot Password button$")
    public void userSelectsForgotPasswordButton() {
        logInPage.clickForgotPasswordButton();
    }

    @Then("^User is redirected to Forgot Password page$")
    public void userIsRedirectedToForgotPasswordPage() throws IOException {
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);

        Assert.assertTrue(forgotPasswordPage.isInitialized());
    }

    @When("^User selects Sign up now button$")
    public void userSelectsSignUpNowButton() {
        logInPage.clickSignUpNowButton();
    }

    @Then("^User is redirected to Register page$")
    public void userIsRedirectedToRegisterPage() throws IOException {
        SignUpPage signUpPage = new SignUpPage(driver);

        Assert.assertTrue(signUpPage.isInitialized());
    }
}
