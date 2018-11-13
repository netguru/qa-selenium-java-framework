package steps;

import base.BaseTest;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import utilities.UserType;

public class LoginSteps extends BaseTest {

    @Given("^User is logged in as (ADMIN|BO|FD|BASIC|BO_NO_RESTAURANTS|FD_NO_RESTAURANTS_AND_REVIEWS|BASIC_WITH_AVATAR)$")
    public void userIsLoggedInAs(String user) {
        pages.getLoginPage().logIn(UserType.valueOf(user));
    }

    @When("^User logs? in as (ADMIN|BO|FD|BASIC|BO_NO_RESTAURANTS|FD_NO_RESTAURANTS_AND_REVIEWS|BASIC_WITH_AVATAR)$")
    public void userLogsInAs(String user) {
        pages.getLoginPage().logIn(UserType.valueOf(user));
    }

    @When("^User logs? in with wrong password$")
    public void userLogsInWithWrongPassword() {
        pages.getLoginPage().logIn(propertiesLoader.getBasicUserEmail(), "WrongPassword1!");
    }

    @When("^User logs? in with empty password$")
    public void userLogsInWithEmptyPassword() {
        pages.getLoginPage().logIn(propertiesLoader.getBasicUserEmail(), "");
    }

    @When("^User logs? in with empty credentials$")
    public void userLogsInWithEmptyCredentials() {
        pages.getLoginPage().logIn("", "");
    }

    @When("^User logs? in with invalid email$")
    public void userLogsInWithInvalidEmail() {
        pages.getLoginPage().logIn("invalidEmail", "ValidPassword1!");
    }

    @When("^User logs? out$")
    public void userLogsOut() {
        pages.getHeaderSection().logOut();
    }

    @When("^User selects? \"([^\"]*)\" button on login page$")
    public void userSelectsButton(String button) {
        switch (button.toLowerCase()) {
            case "forgot your password?":
                pages.getLoginPage().clickForgotYourPasswordButton();
                break;
            case "create an account":
                pages.getLoginPage().clickCreateAnAccountButton();
                break;
        }
    }

    @Then("^User is logged in$")
    public void userIsLoggedIn() {
        Assert.assertTrue(pages.getHeaderSection().isUserLoggedIn());
    }

    @Then("^User is not logged in$")
    public void userIsNotLoggedIn() {
        Assert.assertTrue(pages.getHeaderSection().isUserLoggedOut());
    }

    @Then("^Email error message shows? up on login page: \"([^\"]*)\"$")
    public void emailErrorMessageShowsUpOnLoginPage(String message) {
        Assert.assertEquals(message, pages.getLoginPage().getEmailErrorMessage());
    }

    @Then("^Password error message shows? up on login page: \"([^\"]*)\"$")
    public void passwordErrorMessageShowsUpOnLoginPage(String message) {
        Assert.assertEquals(message, pages.getLoginPage().getPasswordErrorMessage());
    }
}
