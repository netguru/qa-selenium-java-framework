package managers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import utilities.PropertiesLoader;

import java.util.concurrent.TimeUnit;

public final class Driver {

    private static final Logger log = LogManager.getLogger(Logger.class.getName());
    private static WebDriver driver = null;
    private static String baseUrl;
    private PropertiesLoader propertiesLoader = new PropertiesLoader();

    public Driver() {
        propertiesLoader.loadProperties("initConfig.properties");
        String browserType = propertiesLoader.getProperties().getProperty("browser").toLowerCase();
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

    public void initDriver() {
        maximize();
        setImplicitWait(10);
        setBaseUrl();
        cookieAddition();
    }

    public static WebDriver getDriver() {
        if (driver == null)
            log.error("Driver not initialized!");
        return driver;
    }

    public void maximize() {
        driver.manage().window().maximize();
        log.info("Maximizing browser window");
    }

    public void setImplicitWait(int seconds) {
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
        log.debug("Setting implicit wait to " + seconds + "seconds");
    }

    public void quit() {
        if (driver != null) {
            driver.quit();
            log.info("Quitting the browser");
        }
    }

    public void setBaseUrl() {
        baseUrl = propertiesLoader.getProperties().getProperty("base_url").toLowerCase();
        driver.navigate().to(baseUrl);
    }

    public void cookieAddition() {
        String cookieName = propertiesLoader.getProperties().getProperty("cookie_name");
        String cookieValue = propertiesLoader.getProperties().getProperty("cookie_value");
        Cookie cookie = new Cookie(cookieName, cookieValue);
        driver.manage().addCookie(cookie);
    }
}
