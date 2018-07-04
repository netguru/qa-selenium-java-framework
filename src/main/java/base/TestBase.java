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

    protected void setupEnvironment() {
        log.debug(getClass().getName() + " -> Starting tests...");

        Driver.initializeDriver();
        Driver.maximize();
        Driver.setImplicitWait(10);

        // Add cookie to bypass rack password
        Properties props = new Properties();
        try {
            props.load( new FileInputStream("initConfig.properties") );
        } catch (IOException e) {
            log.warn("Failed to load initConfig.properties file.");
        }

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
}
