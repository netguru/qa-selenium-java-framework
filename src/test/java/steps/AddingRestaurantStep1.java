package steps;

import base.BaseTest;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import utilities.ContextType;

import java.sql.Timestamp;

public class AddingRestaurantStep1 extends BaseTest {

    @When("^User submits? form with (minimum|maximum) data on Add Restaurant - Step 1 page$")
    public void userSubmitsFormWithDataOnAddRestaurantStep1Page(String fillingSetting) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        context.scenarioContext.setContext(ContextType.TIMESTAMP_TIME, timestamp.getTime());

        switch (fillingSetting.toLowerCase()) {
            case "minimum":
                pages.getAddRestaurantStep1Page().submitFormWithMinimumData
                        ((context.scenarioContext.getContext(ContextType.TIMESTAMP_TIME)).toString());
                break;
            case "maximum":
                pages.getAddRestaurantStep1Page().submitFormWithMaximumData
                        ((context.scenarioContext.getContext(ContextType.TIMESTAMP_TIME)).toString());
        }
    }

    @When("^User selects? a? country with regions? on Add Restaurant - Step 1 page$")
    public void userSelectsACountryWithRegionsOnAddRestaurantStep1Page() {
        pages.getAddRestaurantStep1Page().getBasicInformationSection().selectCountry("Poland");
    }

    @Then("^Restaurant's draft is created$")
    public void restaurantsDraftIsCreated() {
        String expectedRestaurant = "AutomationBusiness+" + context.scenarioContext.getContext(ContextType.TIMESTAMP_TIME);

        pages.getEditRestaurantBasicInformationPage().goTo();
        Assert.assertTrue(pages.getEditRestaurantBasicInformationPage().findAndSelectRestaurant(expectedRestaurant));
    }

    @Then("^Region field is not displayed on Add Restaurant - Step 1 page$")
    public void regionFieldIsNotDisplayedOnAddRestaurantStep1Page() {
        Assert.assertFalse(pages.getAddRestaurantStep1Page().getBasicInformationSection().isRegionFieldDisplayed());
    }

    @Then("^Region field is displayed on Add Restaurant - Step 1 page$")
    public void regionFieldIsDisplayedOnAddRestaurantStep1Page() {
        Assert.assertTrue(pages.getAddRestaurantStep1Page().getBasicInformationSection().isRegionFieldDisplayed());
    }
}
