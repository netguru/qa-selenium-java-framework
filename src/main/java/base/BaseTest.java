package base;

import managers.DriverManager;
import managers.PageObjectManager;
import managers.ZAPManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.FailureHandler;

import java.lang.reflect.Method;

public class BaseTest {

    protected static final Logger log = LogManager.getLogger(Logger.class.getName());
    protected DriverManager driverManager = new DriverManager();
    private FailureHandler failureHandler = new FailureHandler(driverManager.getDriver());
    protected PageObjectManager pages = new PageObjectManager(driverManager.getDriver());
    protected ZAPManager zapManager = new ZAPManager();

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Method method) {
        log.info(String.format("Starting test: `%s.%s`", this.getClass().getName(), method.getName()));
        failureHandler.startVideoRecord();
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result, Method method) {
        failureHandler.stopVideoRecord();
        if (result.getStatus() == ITestResult.FAILURE) {
            failureHandler.takePageSource(method.getName());
            failureHandler.takeScreenshot(method.getName());
            failureHandler.takeBrowserLogs(method.getName());
            failureHandler.encodeVideoToFlv(method.getName());
        }
        failureHandler.removeVideo();
    }

    @BeforeClass
    public void beforeClass() {
        failureHandler.setUpScreenRecorder();
    }

    @AfterClass
    public void afterClass() {
        driverManager.quit();
    }

}
