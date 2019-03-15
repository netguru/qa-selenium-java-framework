package steps;

import base.BaseTest;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import managers.Context;
import utilities.FailureHandler;

public class Hooks extends BaseTest {


    @Before
    public void setupTestCase() {
        FailureHandler.setUpScreenRecorder();
        log.debug(getClass().getName() + " -> Starting tests...");
        BaseTest.context = new Context();
        BaseTest.pages = context.pages;
        FailureHandler.startVideoRecord();
        context.driverManager.initDriver();
    }

    @After
    public void ceaseTestCase(Scenario scenario) {
        if (scenario.isFailed()) {
            FailureHandler.takePageSource(scenario);
            FailureHandler.takeScreenshot(scenario);
            FailureHandler.stopVideoRecord(scenario);
        }
        context.driverManager.quit();
        log.debug(getClass().getName() + " -> Ending tests...");
    }

}
