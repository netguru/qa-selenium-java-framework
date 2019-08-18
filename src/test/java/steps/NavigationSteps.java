package steps;

import base.BasePage;
import base.BaseTest;
import cucumber.api.java.en.Given;

public class NavigationSteps extends BaseTest {

    @Given("^User is on \"([^\"]*)\" page$")
    public void userIsOnPage(String pageName) {
        BasePage page = pages.getPageByName(pageName);
        page.get();
    }
}
