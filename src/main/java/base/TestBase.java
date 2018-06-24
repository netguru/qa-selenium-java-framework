package base;

import org.openqa.selenium.Cookie;
import pages.LogInPage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class TestBase {

    private static final Logger log = LogManager.getLogger(Logger.class.getName());

    protected void setupEnvironment() throws IOException {
        log.debug(getClass().getName() + " -> Starting tests...");

        Driver.initializeDriver();
        Driver.maximize();
        Driver.setImplicitWait(10);

        // Add cookie to bypass rack password
        Properties props = new Properties();
        props.load( new FileInputStream("initConfig.properties") );

        String baseUrl = props.getProperty("base_url").toLowerCase();
        String cookieName = props.getProperty("cookie_name");
        String cookieValue = props.getProperty("cookie_value");
        Cookie ck = new Cookie(cookieName, cookieValue);
        Driver.getDriver().navigate().to(baseUrl);
        Driver.getDriver().manage().addCookie(ck);
    }

    protected void tearDown() {
        Driver.quit();
        log.debug(getClass().getName() + " -> Ending tests...");
    }

    protected void logInAs(UserType userType, boolean rememberMe) throws IOException {
        LogInPage logInPage = new LogInPage();
        logInPage.logIn(userType, rememberMe);
    }
}
