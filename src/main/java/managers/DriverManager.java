package managers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import utilities.PropertiesLoader;

import java.util.concurrent.TimeUnit;

public class DriverManager {

    private static final Logger log = LogManager.getLogger(Logger.class.getName());
    private WebDriver driver;
    private PropertiesLoader propertiesLoader = new PropertiesLoader();

    public DriverManager() {
        String browserType = propertiesLoader.getBrowserType().toLowerCase();
        log.info("Initializing browser: " + browserType);
        switch (browserType) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                // Chrome Options
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--incognito");
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                // Setup Proxy
                if (propertiesLoader.getZAPAddress() != null) {
                    log.info("Setup proxy for " + browserType + " on " + propertiesLoader.getZAPAddress() +
                            ":" + propertiesLoader.getZAPPort());
                    setupProxy(capabilities);
                }
                driver = new ChromeDriver(capabilities);
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
        if (!propertiesLoader.isCI()) {
            maximize();
        }
        setImplicitWait();
        setAndGoToBaseUrl();
    }

    public WebDriver getDriver() {
        if (driver == null)
            log.error("DriverManager not initialized!");
        return driver;
    }

    private void maximize() {
        driver.manage().window().maximize();
        log.info("Maximizing browser window");
    }

    private void setImplicitWait() {
        int seconds = propertiesLoader.getImplicitWait();
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
        log.debug("Setting implicit wait to " + seconds + "seconds");
    }

    public void quit() {
        if (driver != null) {
            driver.quit();
            log.info("Quitting the browser");
        }
    }

    private void setAndGoToBaseUrl() {
        String baseUrl = propertiesLoader.getBaseUrl().toLowerCase();
        driver.navigate().to(baseUrl);
    }

    private void setupProxy(DesiredCapabilities capabilities) {
        Proxy proxy = new Proxy();
        String server = propertiesLoader.getZAPAddress() + ":" + propertiesLoader.getZAPPort();
        proxy.setHttpProxy(server);
        proxy.setFtpProxy(server);
        proxy.setSslProxy(server);
        capabilities.setCapability("proxy", proxy);
    }

}
