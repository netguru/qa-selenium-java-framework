package steps;

import base.TestingBase;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import managers.Context;
import utilities.UtilitiesFunctions;

public class Hooks extends TestingBase {

    @Before
    public void setupTestCase() {
        log.debug(getClass().getName() + " -> Starting tests...");
        TestingBase.context = new Context();

        context.driver.initDriver();
    }

    @After
    public void ceaseTestCase(Scenario scenario) {
        if (scenario.isFailed())
            UtilitiesFunctions.takeScreenshot(scenario);
        context.driver.quit();
        log.debug(getClass().getName() + " -> Ending tests...");
    }

}
