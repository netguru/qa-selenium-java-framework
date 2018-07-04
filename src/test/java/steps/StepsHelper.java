package steps;

import base.TestBase;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class StepsHelper extends TestBase {
    @Before
    public void setupTestCase() {
        setupEnvironment();
    }

    @After
    public void ceaseTestCase() {
        tearDown();
    }
}
