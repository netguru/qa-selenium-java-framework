import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase {

    // Static declaration of WebDriver variable to be used by test classes
    public static WebDriver driver;

    public static WebDriver setupDriver() {

        // TO-DO: add the method to choose the browser from initConfig.properties file
        String browserType = "Chrome";

        switch(browserType) {
            case "Chrome":
                System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
                driver = new ChromeDriver();
                break;
            // TO-DO: add case for other drivers and default case.
        }
        // TO-DO: add implicit wait functionality
        return driver;
    }

    // REMOVE this later - added only to test if driver works
    public static void main(String[] args) {
        setupDriver();
        driver.navigate().to("https://www.google.com/");
    }
}
