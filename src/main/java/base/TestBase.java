package base;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public abstract class TestBase {

    // Static declaration of WebDriver variable to be used by test classes
    protected static WebDriver driver;

    @Before
    public void setupDriver() throws IOException {
        Properties props = new Properties();
        props.load( new FileInputStream("initConfig.properties") );

        // Adds cookie to bypass rack password
        String baseUrl = props.getProperty("base_url").toLowerCase();
        String cookieName = props.getProperty("cookie_name");
        String cookieValue = props.getProperty("cookie_value");
        Cookie ck = new Cookie(cookieName, cookieValue);

        driver.navigate().to(baseUrl);
        driver.manage().addCookie(ck);

        // Initializes browser
        String browserType = props.getProperty("browser").toLowerCase();

        switch(browserType) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
                driver = new ChromeDriver();
                break;
            case "firefox":
                break;
            case "safari":
                break;
            default:
                System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
                driver = new ChromeDriver();
                break;
            // TODO: add case for other drivers
        }
        driver.manage().window().maximize();

        // Manage timeouts
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public static void tearDown() {
        if(driver != null)
            driver.quit();
    }

}
