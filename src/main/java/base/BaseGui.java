package base;

import managers.DriverManager;
import managers.PageObjectManager;
import managers.ZAPManager;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilities.FailureHandler;

import java.lang.reflect.Method;

public class BaseGui extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Method method) {
        driverManager = new DriverManager();
        failureHandler = new FailureHandler(driverManager.getDriver());
        pages = new PageObjectManager(driverManager.getDriver());
        zapManager = new ZAPManager();
        failureHandler.setUpAndStartScreenRecorder(method.getName());
        log.info(String.format("Starting test: `%s.%s`", this.getClass().getName(), method.getName()));
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

    @AfterClass
    public void afterClass() {
        driverManager.quit();
    }

}
