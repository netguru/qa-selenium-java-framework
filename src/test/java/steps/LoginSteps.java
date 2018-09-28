package steps;

import base.TestingBase;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class LoginSteps extends TestingBase {



  @Given("The user is on login page")
  public void theUserIsOnLoginPage(){
      pages.getLoginPage().goTo();
  }

  @When("^User logs in as \"([^\"]*)\"$")
  public void userLogsInAsUser(String user) {
    switch (user) {
      case "PROVIDER" :
        pages.getLoginPage().provideEmail(pages.getLoginPage().getProvider());
        break;
      case "CUSTOMER_PAID" :
        pages.getLoginPage().provideEmail(pages.getLoginPage().getProvider());
        break;
      default:
          pages.getLoginPage().provideEmail(pages.getLoginPage().getProvider());

    }

    pages.getLoginPage().providePassword(pages.getLoginPage().getPassword());

    pages.getLoginPage().clickLoginButton();
  }

  @Then("^User is logged in$")
  public void userIsLoggedIn() {
    Assert.assertTrue(pages.getDashboardPage().isInitialized());
  }
}
