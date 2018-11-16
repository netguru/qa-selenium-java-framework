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

    @When("^User provides? all required data on Add Restaurant - Step 1 page except" +
            " (name|country|post code|city|street name|types|cuisines|food and drinks|perfect for)$")
    public void userProvidesAllRequiredDataOnAddRestaurantStep1PageExcept(String missingField) {
        missingField = missingField.toLowerCase();

        if (!missingField.equals("name")) {
            pages.getAddRestaurantStep1Page().getBasicInformationSection().provideName("Name");
        }
        if (!missingField.equals("country")) {
            pages.getAddRestaurantStep1Page().getBasicInformationSection().selectCountry("Poland");
        }
        if (!missingField.equals("post code")) {
            pages.getAddRestaurantStep1Page().getBasicInformationSection().providePostCode("PostCode");
        }
        if (!missingField.equals("city")) {
            pages.getAddRestaurantStep1Page().getBasicInformationSection().provideCity("City");
        }
        if (!missingField.equals("street name")) {
            pages.getAddRestaurantStep1Page().getBasicInformationSection().provideStreetName("StreetName");
        }
        if (!missingField.equals("types")) {
            pages.getAddRestaurantStep1Page().getBasicInformationSection().selectTypesCheckboxes(1);
        }
        if (!missingField.equals("cuisines")) {
            pages.getAddRestaurantStep1Page().getBasicInformationSection().selectCuisines(1);
        }
        if (!missingField.equals("food and drinks")) {
            pages.getAddRestaurantStep1Page().getBasicInformationSection().selectFoodAndDrinks(1);
        }
        if (!missingField.equals("perfect for")) {
            pages.getAddRestaurantStep1Page().getBasicInformationSection().selectPerfectFors(3);
        }
    }

    @When("^User submits? form on Add Restaurant - Step 1 page$")
    public void userSubmitsFormOnAddRestaurantStep1Page() {
        pages.getAddRestaurantStep1Page().clickNextPageButton();
    }

    @When("^User submits? form with (\\d+) items? selected in" +
            " (types|cuisines|food and drinks|perfect for) on Add Restaurant - Step 1 page$")
    public void userSubmitsFormWithItemsSelectedInOnAddRestaurantStep1Page(int amount, String field) {
        switch (field.toLowerCase()) {
            case "types":
                pages.getAddRestaurantStep1Page().getBasicInformationSection().selectTypesCheckboxes(amount);
                break;
            case "cuisines":
                pages.getAddRestaurantStep1Page().getBasicInformationSection().selectCuisines(amount);
                break;
            case "food and drinks":
                pages.getAddRestaurantStep1Page().getBasicInformationSection().selectFoodAndDrinks(amount);
                break;
            case "perfect for":
                pages.getAddRestaurantStep1Page().getBasicInformationSection().selectPerfectFors(amount);
                break;
        }

        pages.getAddRestaurantStep1Page().clickNextPageButton();
    }

    @When("^User submits? an? empty form on Add Restaurant - Step 1 page$")
    public void userSubmitsAnEmptyFormOnAddRestaurantStep1Page() {
        pages.getAddRestaurantStep1Page().clickNextPageButton();
    }

    @When("^User provides? name field on Add Restaurant - Step 1 page$")
    public void userProvidesFieldOnAddRestaurantStep1Page() {
        pages.getAddRestaurantStep1Page().getBasicInformationSection().provideName("Name");
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

    @Then("^(name|country|post code|city|street name|types|cuisines|food and drinks|perfect for)" +
            " error message shows up on Add Restaurant - Step 1 page: \"([^\"]*)\"$")
    public void errorMessageShowsUpOnAddRestaurantStep1Page(String field, String expectedMessage) {
        switch (field.toLowerCase()) {
            case "name":
                Assert.assertEquals(expectedMessage,
                        pages.getAddRestaurantStep1Page().getBasicInformationSection().getNameErrorMessage());
                break;
            case "country":
                Assert.assertEquals(expectedMessage,
                        pages.getAddRestaurantStep1Page().getBasicInformationSection().getCountryErrorMessage());
                break;
            case "post code":
                Assert.assertEquals(expectedMessage,
                        pages.getAddRestaurantStep1Page().getBasicInformationSection().getPostCodeErrorMessage());
                break;
            case "city":
                Assert.assertEquals(expectedMessage,
                        pages.getAddRestaurantStep1Page().getBasicInformationSection().getCityErrorMessage());
                break;
            case "street name":
                Assert.assertEquals(expectedMessage,
                        pages.getAddRestaurantStep1Page().getBasicInformationSection().getStreetNameErrorMessage());
                break;
            case "types":
                Assert.assertEquals(expectedMessage,
                        pages.getAddRestaurantStep1Page().getBasicInformationSection().getTypesErrorMessage());
                break;
            case "cuisines":
                Assert.assertEquals(expectedMessage,
                        pages.getAddRestaurantStep1Page().getBasicInformationSection().getCuisinesErrorMessage());
                break;
            case "food and drinks":
                Assert.assertEquals(expectedMessage,
                        pages.getAddRestaurantStep1Page().getBasicInformationSection().getFoodAndDrinksErrorMessage());
                break;
            case "perfect for":
                Assert.assertEquals(expectedMessage,
                        pages.getAddRestaurantStep1Page().getBasicInformationSection().getPerfectForsErrorMessage());
                break;
        }
    }
}
