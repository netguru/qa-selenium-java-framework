package steps;

import base.TestingBase;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import pages.HomePage;

public class HomePageSteps extends TestingBase {
  private HomePage homePage;
  @Given("User opens browser")
  public void userOpensBrowser() {
  }

  @When("User navigates to homepage")
  public void navigateToHomepageUrl() {
    pages.getHomePage().goTo();
  }

  @Then("User sees logo")
  public void logoIsVisible() {
    Assert.assertTrue(pages.getHomePage().getLogo().isDisplayed());
  }
}
