package steps;

import base.BaseTest;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import managers.ScenarioContext;
import org.junit.Assert;
import utilities.ContexType;
import utilities.UserType;
import utilities.UtilitiesFunctions;

import java.sql.Timestamp;

public class RegistrationSteps extends BaseTest {

    @When("^User registers? with (ADMIN|BO|FD|BASIC|BO_NO_RESTAURANTS|FD_NO_RESTAURANTS_AND_REVIEWS|BASIC_WITH_AVATAR) email$")
    public void userRegistersWithEmail(String user) {
        String email = UtilitiesFunctions.getUserEmail(UserType.valueOf(user));
        pages.getRegisterPage().registerUser(email, "ValidPassword1!");
    }

    @When("^User registers? with invalid email$")
    public void userRegistersWithInvalidEmail() {
        pages.getRegisterPage().registerUser("InvalidEmail", "ValidPassword1!");
    }

    @When("^User registers? with empty password$")
    public void userRegistersWithEmptyPassword() {
        pages.getRegisterPage().registerUser("ValidEmail@example.com", "");
    }

    @When("^User registers? with too short password$")
    public void userRegistersWithTooShortPassword() {
        pages.getRegisterPage().registerUser("ValidEmail@example.com", "short");
    }

    @When("^User registers? with wrong password confirmation$")
    public void userRegistersWithWrongPasswordConfirmation() {
        pages.getRegisterPage().registerUser("ValidEmail@example.com", "ValidPassword1!",
                "WrongPasswordConfirmation", true);
    }

    @When("^User registers? without accepting terms and privacy policy$")
    public void userRegistersWithoutAcceptingTermsAndPrivacyPolicy() {
        pages.getRegisterPage().registerUser("ValidEmail@example.com", "ValidPassword1!",
                "ValidPassword1!", false);
    }

    @When("^User registers? with valid credentials$")
    public void userRegistersWithValidCredentials() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        context.scenarioContext.setContext(ContexType.TIMESTAMP_TIME, timestamp.getTime());

        String email = "AutomationUser+"
                + context.scenarioContext.getContext(ContexType.TIMESTAMP_TIME) + "@example.com";
        pages.getRegisterPage().registerUser(email, "ValidPassword1!");
    }

    @Then("^User is not registered$")
    public void userIsNotRegistered() {
        Assert.assertTrue(pages.getRegisterPage().isInitialized());
    }

    @Then("^Email error message shows? up on register page: \"([^\"]*)\"$")
    public void emailErrorMessageShowsUpOnRegisterPage(String message) {
        Assert.assertEquals(message, pages.getRegisterPage().getEmailErrorMessage());
    }

    @Then("^Password error message shows? up on register page: \"([^\"]*)\"$")
    public void passwordErrorMessageShowsUpOnRegisterPage(String message) {
        Assert.assertEquals(message, pages.getRegisterPage().getPasswordErrorMessage());
    }

    @Then("^Password confirmation error message shows? up on register page: \"([^\"]*)\"$")
    public void passwordConfirmationErrorMessageShowsUpOnRegisterPage(String message) {
        Assert.assertEquals(message, pages.getRegisterPage().getPasswordConfirmationErrorMessage());
    }

    @Then("^Terms and privacy policy error message shows? up on register page: \"([^\"]*)\"$")
    public void termsAndPrivacyPolicyErrorMessageShowsUpOnRegisterPage(String message) {
        Assert.assertEquals(message, pages.getRegisterPage().getTermsAgreementForEmailRegistrationErrorMessage());
    }

    @Then("^Confirm your email message shows? up on register page$")
    public void confirmYouEmailMessageShowsUpOnRegisterPage() {
        Assert.assertTrue(pages.getRegisterPage().isEmailConfirmationMessageVisible());
    }
}
