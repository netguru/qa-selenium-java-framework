package steps;

import base.TestBase;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import pages.LogInPage;

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

    // Scenario Outline: Successful login as <user_type>
    @When("^User provides correct email: \"([^\"]*)\" and password$")
    public void user_provides_correct_email_and_password(String email) {
        logInPage.provideEmail(email);
        logInPage.providePassword("password");
    }

    @When("^User selects Log in button$")
    public void user_selects_Log_in_button() {
        logInPage.clickLogInButton();
    }

    @Then("^User is taken to the dashboard page$")
    public void user_is_taken_to_the_dashboard_page() {
        // TODO: create dasbhoard page and get the url from the object
        Assert.assertTrue(driver.getCurrentUrl().equals("http://tutor24.ch.lemonfrog.staging.devguru.co/en/dashboard"));
    }

//    // Scenario: Successful logout
//    @Given("^User is logged in$")
//    public void user_is_logged_in() {
//        logInPage.logIn("consumer_unpaid@netguru.pl", "password", false);
//    }


}
