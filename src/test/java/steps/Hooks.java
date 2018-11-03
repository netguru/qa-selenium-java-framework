package steps;

import base.BaseTest;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import managers.Context;
import utilities.UtilitiesFunctions;

public class Hooks extends BaseTest {

    @Before
    public void setupTestCase() {
        log.debug(getClass().getName() + " -> Starting tests...");
        BaseTest.pages = Context.pages;
        Context.driverManager.initDriver();
    }

    @After
    public void ceaseTestCase(Scenario scenario) {
        if (scenario.isFailed())
            UtilitiesFunctions.takeScreenshot(scenario);
        Context.driverManager.quit();
        log.debug(getClass().getName() + " -> Ending tests...");
    }

}
