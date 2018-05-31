package steps;

import base.TestBase;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.testng.Assert;
import pages.PageLogIn;

import java.io.IOException;

public class LogInSteps extends TestBase {
    PageLogIn logInPage;

    @Before
    public void beforeScenario() throws IOException {
        setupDriver();

        logInPage = new PageLogIn(driver);
        logInPage.goTo();

        Assert.assertTrue(logInPage.isInitialized());
    }

    @After
    public void afterScenario() {
        tearDown();
    }
}
