package base;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.LogInPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public abstract class TestBase {

    // Static declaration of WebDriver variable to be used by test classes
    protected static WebDriver driver;

    protected void setupEnvironment() throws IOException {
        Properties props = new Properties();
        props.load( new FileInputStream("initConfig.properties") );

        // Initialize browser
        String browserType = props.getProperty("browser").toLowerCase();
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

    protected static void tearDown() {
        if(driver != null)
            driver.quit();
    }

    protected void logInAs(UserType userType) throws IOException {
        Properties props = new Properties();
        props.load( new FileInputStream("initConfig.properties") );

        String email = "";
        String password = props.getProperty("common_password");

        switch (userType) {
            case ADMIN:
                email = props.getProperty("admin_email");
                break;
            case PROVIDER:
                email = props.getProperty("provider_email");
                break;
            case CONSUMER_PAID:
                email = props.getProperty("paid_consumer_email");
                break;
            case CONSUMER_UNPAID:
                email = props.getProperty("unpaid_consumer_email");
                break;
            case CONSUMER_SPECIAL:
                email = props.getProperty("special_consumer_email");
                break;
        }

        LogInPage logInPage = new LogInPage(driver);
        logInPage.logIn(email, password, false);
    }
}

