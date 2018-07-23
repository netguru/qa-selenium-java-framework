package steps;

import base.TestingBase;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks extends TestingBase {

    @Before
    public void setupTestCase() {
        setupEnvironment();
    }

    @After
    public void ceaseTestCase(Scenario scenario) {
        if (scenario.isFailed())
            takeScreenshot(scenario);
        tearDown();
    }

}
