package steps;

import base.BaseTest;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class HeaderSteps extends BaseTest {

    @When("^User selects? Add Restaurant button in header$")
    public void userSelectsAddRestaurantButtonInHeader() {
        pages.getHeaderSection().clickAddRestaurantButton();
    }

    @Then("^Add Restaurant button in header is displayed$")
    public void addRestaurantButtonInHeaderIsDisplayed() {
        pages.getHeaderSection().waitForAddRestaurantButtonToDisappear();
        Assert.assertTrue(pages.getHeaderSection().isAddRestaurantButtonDisplayed());
    }

    @Then(("^Add Restaurant button in header is not displayed$"))
    public void addRestaurantButtonInHeaderIsNotDisplayed() {
        pages.getHeaderSection().waitForAddRestaurantButtonToDisappear();
        Assert.assertFalse(pages.getHeaderSection().isAddRestaurantButtonDisplayed());
    }
}
