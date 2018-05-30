import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public abstract class TestBase {

    // Static declaration of WebDriver variable to be used by test classes
    protected static WebDriver driver;

    @Before
    public static void setupDriver() {

        // TO-DO: add the method to choose the browser from initConfig.properties file
        String browserType = "Chrome";

        switch(browserType) {
            case "Chrome":
                System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
                driver = new ChromeDriver();
                break;
            // TO-DO: add case for other drivers and default case.
        }
        driver.manage().window().maximize();

        // Manage timeouts
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public static void tearDown() {
        driver.quit();
    }

}
