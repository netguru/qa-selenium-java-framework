package steps;

import base.BaseTest;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import sections.editrestaurant.ContactInformationSection;
import utilities.ContextType;

import java.sql.Timestamp;

public class AddingRestaurantStep2Steps extends BaseTest {

    @When("User submits? form with required data on Add restaurant - step 2")
    public void userSubmitRequiredData() {
        pages.getAddRestaurantStep2Page().submitFormWithRequiredData();
    }

    @When("^User submits? form with all data on Add restaurant - step 2$")
    public void userSubmitsAllData() {
        context.scenarioContext.setContext(ContextType.TIMESTAMP_TIME, new Timestamp(System.currentTimeMillis()).getTime());
        pages.getAddRestaurantStep2Page().submitFormWithMaximumData(context.scenarioContext.getContext(ContextType.TIMESTAMP_TIME).toString());
    }

    @When("User submits? empty form on Add restaurant - step 2")
    public void userSubmitsEmptyForm() {
        pages.getAddRestaurantStep2Page().clickNextButton();
    }

    @When("User submits? form with all correct data except \"([^\"]*)\" on Add restaurant - step 2")
    public void userSubmitsFormWithAllDataExceptField(String field) {
        ContactInformationSection section = pages.getAddRestaurantStep2Page().getContactInformationSection();
        Long timestamp = new Timestamp(System.currentTimeMillis()).getTime();
        if (!field.equals("email")) {
            section.provideEmailInput(String.format("test+%d@gmail.com", timestamp));
        } else {
            section.triggerValidationEmailInput();
        }
        if (!field.equals("phone")) {
            section.providePhoneNumber("france", String.format("%d", 111222333));
        }
        if (!field.equals("website")) {
            section.provideWebsiteInput(String.format("http://%d.com", timestamp));
        } else {
            section.triggerValidationWebsiteInput();
        }
        if (!field.equals("facebook")) {
            section.provideFacebookInput(String.format("https://facebook.com/%d", timestamp));
        } else {
            section.triggerValidationFacebookInput();
        }
        if (!field.equals("instagram")) {
            section.provideInstagram(String.format("https://instagram.com/%d", timestamp));
        } else {
            section.triggerValidationInstagramInput();
        }
        pages.getAddRestaurantStep2Page().clickNextButton();
    }

    @When("User submits form with phone number \"([^\"]*)\" in \"([^\"]*)\" on Add restaurant - step 2")
    public void userSubmitsFormWithPhoneAndCountryPrefix(String phoneNumber, String country) {
        pages.getAddRestaurantStep2Page().getContactInformationSection().providePhoneNumber(country, phoneNumber);
        pages.getAddRestaurantStep2Page().clickNextButton();
    }

    @Then("User sees? the error message of \"([^\"]*)\" on Add restaurant - step 2")
    public void userSeesErrorMessageOfField(String field) {
        ContactInformationSection section = pages.getAddRestaurantStep2Page().getContactInformationSection();
        switch (field) {
            case "email":
                Assert.assertTrue(section.getEmailInput().getValidator().isDisplayed());
                break;
            case "phone":
                Assert.assertTrue(section.getPhoneInput().getValidator().isDisplayed());
                break;
            case "website":
                Assert.assertTrue(section.getWebsiteInput().getValidator().isDisplayed());
                break;
            case "facebook":
                Assert.assertTrue(section.getFacebookInput().getValidator().isDisplayed());
                break;
            case "instagram":
                Assert.assertTrue(section.getInstagramInput().getValidator().isDisplayed());
                break;
        }
    }
}
