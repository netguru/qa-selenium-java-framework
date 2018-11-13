package steps;

import base.BaseTest;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class FooterSteps extends BaseTest {

    @When("^User selects? (Become a Detective|Register your business) button in footer$")
    public void userSelectsButtonInFooter(String button) {
        switch (button.toLowerCase()) {
            case "become a detective":
                pages.getFooterSection().clickBecomeADetectiveButton();
                break;
            case "register your business":
                pages.getFooterSection().clickRegisterYourBusinessButton();
                break;
        }
    }

    @Then("^Footer is not displayed$")
    public void footerIsNotDisplayed() {
        Assert.assertFalse(pages.getFooterSection().isInitialized());
    }

    @Then("^Become a Detective button is displayed in footer$")
    public void becomeADetectiveButtonIsDisplayedInFooter() {
        pages.getFooterSection().waitForBecomeADetectiveButtonToDisappear();
        Assert.assertTrue(pages.getFooterSection().isBecomeADetectiveButtonDisplayed());

    }

    @Then("^Become a Detective button is not displayed in footer$")
    public void becomeADetectiveButtonIsNotDisplayedInFooter() {
        pages.getFooterSection().waitForBecomeADetectiveButtonToDisappear();
        Assert.assertFalse(pages.getFooterSection().isBecomeADetectiveButtonDisplayed());

    }
}
