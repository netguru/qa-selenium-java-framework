package steps;

import base.BaseTest;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class NavigationSteps extends BaseTest {

    @Given("^User is on \"([^\"]*)\" page$")
    public void userIsOnPage(String page) {
        switch (page.toLowerCase()) {
            case "login":
                pages.getLoginPage().goTo();
                break;
            case "register":
                pages.getRegisterPage().goTo();
                break;
            case "reset password":
                pages.getResetPasswordPage().goTo();
                break;
            case "home":
                pages.getHomePage().goTo();
                break;
            case "search":
                pages.getSearchPage().goTo();
                break;
            case "edit profile - basic information":
                pages.getEditProfileBasicInformationPage().goTo();
                break;
            case "edit profile - become a detective":
                pages.getEditProfileBecomeADetectivePage().goTo();
            case "add restaurant - step 1":
                pages.getAddRestaurantStep1Page().goTo();
            default:
                log.error("There is no such page as: " + page);
                break;
        }
    }

    @Then("^User is redirected to \"([^\"]*)\" page$")
    public void userIsRedirectedToPage(String page) {
        switch (page.toLowerCase()) {
            case "login":
                Assert.assertTrue(pages.getLoginPage().isInitialized());
                break;
            case "register":
                Assert.assertTrue(pages.getRegisterPage().isInitialized());
                break;
            case "reset password":
                Assert.assertTrue(pages.getResetPasswordPage().isInitialized());
                break;
            case "home":
                Assert.assertTrue(pages.getHomePage().isInitialized());
                break;
            case "search":
                Assert.assertTrue(pages.getSearchPage().isInitialized());
            case "edit profile - basic information":
                Assert.assertTrue(pages.getEditProfileBasicInformationPage().isInitialized());
                break;
            case "edit profile - become a detective":
                Assert.assertTrue(pages.getEditProfileBecomeADetectivePage().isInitialized());
                break;
            case "add restaurant - step 1":
                Assert.assertTrue(pages.getAddRestaurantStep1Page().isInitialized());
                break;
            default:
                log.error("There is no such page as: " + page);
                Assert.fail();
                break;
        }
    }

    @Then("^Snackbar message shows? up: \"([^\"]*)\"$")
    public void snackbarMessageShowsUp(String message) {
        Assert.assertEquals(message, pages.getLoginPage().getSnackbarMessageAndCloseSnackbar());
    }
}
