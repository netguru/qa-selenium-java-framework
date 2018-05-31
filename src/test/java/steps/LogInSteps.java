package steps;

import base.TestBase;
import cucumber.api.java.After;
import cucumber.api.java.Before;
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
        logInPage.goTo();

        Assert.assertTrue(logInPage.isInitialized());
    }

    @After
    public void afterScenario() {
        tearDown();
    }

    @When("^User provides email: \"([^\"]*)\" and password: \"([^\"]*)\"$")
    public void user_provides_email_and_password(String email, String password) {
        logInPage.provideEmail(email);
        logInPage.providePassword(password);
    }

    @When("^User selects Log in button$")
    public void user_selects_Log_in_button() {
        logInPage.clickLogInButton();
    }

    @Then("^User is logged in and taken to the dashboard page$")
    public void user_is_logged_in_and_taken_to_the_dashboard_page() throws IOException {
        DashboardPage dashboardPage = new DashboardPage(driver);

        Assert.assertTrue(dashboardPage.isInitialized());
    }

    @Given("^User is logged in$")
    public void user_is_logged_in() {
        logInPage.logIn("consumer_unpaid@netguru.pl", "password", false);
    }

    @When("^User selects Logout button$")
    public void user_selects_Logout_button() throws IOException {
        DashboardPage dashboardPage = new DashboardPage(driver);

        Assert.assertTrue(dashboardPage.isInitialized());

        dashboardPage.getMainHeaderSection().logOut();
    }

    @Then("^User is redirected to home page$")
    public void user_is_redirected_to_home_page() throws IOException {
        HomePage homePage = new HomePage(driver);

        Assert.assertTrue(homePage.isInitialized());
    }

    @Then("^User cannot access the dashboard$")
    public void user_cannot_access_the_dashboard() throws IOException {
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.goTo();

        Assert.assertTrue(logInPage.isInitialized());
    }

    @When("^User does not provide the credentials$")
    public void user_does_not_provide_the_credentials() {
        logInPage.clickLogInButton();
    }

    @Then("^User cannot login$")
    public void user_cannot_login() {
        Assert.assertTrue(logInPage.isInitialized());
    }

    @Then("^\"([^\"]*)\" alert shows up$")
    public void alert_shows_up(String alert) {
        Assert.assertTrue(alert.equals(logInPage.getAlertText()));
    }

    @Then("^\"([^\"]*)\" message shows up$")
    public void message_shows_up(String alert) {
        Assert.assertTrue(alert.equals(logInPage.getAlertText()));
    }

    @When("^User selects Forgot Password button$")
    public void user_selects_Forgot_Password_button() {
        logInPage.clickForgotPasswordButton();
    }

    @Then("^User is redirected to Forgot Password page$")
    public void user_is_redirected_to_Forgot_Password_page() throws IOException {
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);

        Assert.assertTrue(forgotPasswordPage.isInitialized());
    }

    @When("^User selects Sign up now button$")
    public void user_selects_Sign_up_now_button() {
        logInPage.clickSignUpNowButton();
    }

    @Then("^User is redirected to Register page$")
    public void user_is_redirected_to_Register_page() throws IOException {
        SignUpPage signUpPage = new SignUpPage(driver);

        Assert.assertTrue(signUpPage.isInitialized());
    }
}
