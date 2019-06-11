package steps;

import base.BaseTest;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class HomePageSteps extends BaseTest {
    @Given("User opens browser")
    public void userOpensBrowser() {
    }

    @When("User navigates to homepage")
    public void navigateToHomepageUrl() {
        pages.getHomePage().get();
    }

    @When("User is login into staging")
    public void loginIntoStaging() {
        pages.getHomePage().loginIntoStaging();
    }

    @Then("User sees logo")
    public void logoIsVisible() {
        Assert.assertTrue(pages.getHomePage().getLogo().isDisplayed());
    }

    @Then("Spider scan is launched with Max Depth set to (\\d+)")
    public void spiderScanLaunched(int maxDepth) {
        zapManager.spiderScan(pages.getHomePage().getUrl(), maxDepth);
    }
}
