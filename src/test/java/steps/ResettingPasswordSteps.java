package steps;

import base.BaseTest;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class ResettingPasswordSteps extends BaseTest {

    @When("^User submits? invalid email in reset password form$")
    public void userSubmitsInvalidEmailInResetPasswordForm() {
        pages.getResetPasswordPage().submitEmail("InvalidEmail");
    }

    @When("^User submits? empty email in reset password form$")
    public void userSubmitsEmptyEmailInResetPasswordForm() {
        pages.getResetPasswordPage().submitEmail("");
    }

    @When("^User submits? a? valid email in reset password form$")
    public void userSubmitsValidEmailInResetPasswordForm() {
        pages.getResetPasswordPage().submitEmail("ValidEmail@example.com");
    }

    @Then("^Email error message shows? up on reset password page: \"([^\"]*)\"$")
    public void emailErrorMessageShowsUpOnResetPasswordPage(String message) {
        Assert.assertEquals(message, pages.getResetPasswordPage().getEmailErrorMessage());
    }

    @Then("^Reset password email is not sent$")
    public void resetPasswordEmailIsNotSent() {
        Assert.assertTrue(pages.getResetPasswordPage().isInitialized());
    }
}
