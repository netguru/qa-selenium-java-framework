package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import utilities.UtilitiesFunctions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public final class Driver {

    private static WebDriver driver = null;
    private static final Logger log = LogManager.getLogger(Logger.class.getName());

    public static void initializeDriver() {
        Properties props = UtilitiesFunctions.loadFile("initConfig.properties");

        String browserType = props.getProperty("browser").toLowerCase();
        log.info("Initializing browser: " + browserType);
        switch (browserType) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "safari":
                driver = new SafariDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                log.warn("Wrong browser type \"" + browserType + "\". Initializing Chrome");
                break;
        }
    }

    public static void maximize() {
        driver.manage().window().maximize();
        log.info("Maximizing browser window");
    }

    public static void setImplicitWait(int seconds) {
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
        log.debug("Setting implicit wait to " + seconds + "seconds");
    }

    public static void quit() {
        if (driver != null) {
            driver.quit();
            log.info("Quitting the browser");
        }
    }

    public static WebDriver getDriver() {
        if (driver == null)
            log.error("Driver not initialized!");
        return driver;
    }
}
