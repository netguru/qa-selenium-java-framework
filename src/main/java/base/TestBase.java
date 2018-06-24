package base;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.LogInPage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public abstract class TestBase {

    // Static declaration of WebDriver variable to be used by test classes
    protected static WebDriver driver;
    private static final Logger log = LogManager.getLogger(Logger.class.getName());

    protected void setupEnvironment() throws IOException {
        log.debug(getClass().getName() + " -> Starting tests...");

        Properties props = new Properties();
        props.load( new FileInputStream("initConfig.properties") );

        // Initialize browser
        String browserType = props.getProperty("browser").toLowerCase();
        log.info("Initializing browser: " + browserType);
        switch(browserType) {
            // TODO: add cases for safari and edge?
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver");
                driver = new FirefoxDriver();
                break;
            default:
                System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
                driver = new ChromeDriver();
                log.warn("Wrong browser type \"" + browserType + "\". Initializing Chrome");
                break;
        }
        driver.manage().window().maximize();

        // Manage timeouts
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Add cookie to bypass rack password
        String baseUrl = props.getProperty("base_url").toLowerCase();
        String cookieName = props.getProperty("cookie_name");
        String cookieValue = props.getProperty("cookie_value");
        Cookie ck = new Cookie(cookieName, cookieValue);
        driver.navigate().to(baseUrl);
        driver.manage().addCookie(ck);
    }

    protected void tearDown() {
        if(driver != null) {
            driver.quit();
            log.info("Quitting the browser");
        }

        log.debug(getClass().getName() + " -> Ending tests...");
    }

    protected void logInAs(UserType userType, boolean rememberMe) throws IOException {
        LogInPage logInPage = new LogInPage(driver);
        logInPage.logIn(userType, rememberMe);
    }
}
