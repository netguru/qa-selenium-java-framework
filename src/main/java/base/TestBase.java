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
}

