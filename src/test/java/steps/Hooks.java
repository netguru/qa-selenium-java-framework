package steps;

import base.BaseTest;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import managers.Context;
import managers.JSExecutor;
import utilities.FailureHandler;

public class Hooks extends BaseTest {

    private FailureHandler failureHandler = new FailureHandler();

    @Before
    public void setupTestCase() {
        failureHandler.setUpScreenRecorder();
        log.debug(String.format("%s -> Starting tests...", getClass().getName()));
        BaseTest.context = new Context();
        BaseTest.pages = context.pages;
        BaseTest.zapManager = context.zapManager;
        BaseTest.jsExecutor = context.jsExecutor;
        failureHandler.startVideoRecord();
        context.driverManager.initDriver();
    }

    @After
    public void ceaseTestCase(Scenario scenario) {
        if (scenario.isFailed()) {
            failureHandler.takePageSource(scenario);
            failureHandler.takeScreenshot(scenario);
        }
        failureHandler.stopVideoRecord(scenario);
        context.driverManager.quit();
        log.debug(String.format("%s -> Ending tests...", getClass().getName()));
    }

}
