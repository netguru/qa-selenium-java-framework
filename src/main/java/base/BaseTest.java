package base;

import base.api.RequestBuilder;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import managers.DriverManager;
import managers.PageObjectManager;
import managers.ZAPManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utilities.FailureHandler;
import utilities.PropertiesLoader;

import java.lang.reflect.Method;

public class BaseTest {
    protected static final Logger log = LogManager.getLogger(Logger.class.getName());
    protected DriverManager driverManager;
    protected FailureHandler failureHandler;
    protected PageObjectManager pages;
    protected ZAPManager zapManager;

    @BeforeSuite(alwaysRun = true)
    public void baseSetup() {
        RestAssured.requestSpecification = getRequestSpecification();
    }

    public RequestSpecification getRequestSpecification() {
        return new RequestBuilder().getBasicRequestSpecBuilder()
                .setBaseUri(new PropertiesLoader().getBaseApiUrl())
                .setConfig(RequestBuilder.getBasicRequestConfig())
                .build();
    }
    @BeforeClass
    public void beforeClass() {

    }

}
