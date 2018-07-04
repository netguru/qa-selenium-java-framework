package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public final class Driver {

    private static WebDriver driver = null;
    private static final Logger log = LogManager.getLogger(Logger.class.getName());

    public static void initializeDriver() {
        Properties props = new Properties();
        try {
            props.load( new FileInputStream("initConfig.properties") );
        } catch (IOException e) {
            log.warn("Failed to load initConfig.properties file.");
        }

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
    }

    public static void maximize(){
        driver.manage().window().maximize();
        log.info("Maximizing browser window");
    }

    public static void setImplicitWait(int seconds) {
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
        log.debug("Setting implicit wait to " + seconds + "seconds");
    }

    public static void quit() {
        if(driver != null) {
            driver.quit();
            log.info("Quitting the browser");
        }
    }

    public static WebDriver getDriver() {
        if(driver == null)
            log.error("Driver not initialized!");
        return driver;
    }
}
