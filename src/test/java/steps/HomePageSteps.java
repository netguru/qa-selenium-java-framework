package steps;

import base.BaseTest;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class HomePageSteps extends BaseTest {
  @Given("User opens browser")
  public void userOpensBrowser (){
  }

  @When("User navigates to homepage")
  public void navigateToHomepageUrl () {
    pages.getHomePage().get();
  }

  @Then("User sees logo")
  public void logoIsVisible () {
    Assert.assertTrue(pages.getHomePage().getLogo().isDisplayed());
  }
}
