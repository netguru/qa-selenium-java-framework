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
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import utilities.PropertiesLoader;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class DriverManager {

    private static final Logger log = LogManager.getLogger(Logger.class.getName());
    private WebDriver driver;
    private PropertiesLoader propertiesLoader = new PropertiesLoader();

    public DriverManager() {
        String browserType = propertiesLoader.getBrowserType().toLowerCase();
        log.info(String.format("Initializing browser `%s`", browserType));
        switch (browserType) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                DesiredCapabilities DCChrome = DesiredCapabilities.chrome();
                // Browser logs enabled
                LoggingPreferences logPrefs = new LoggingPreferences();
                logPrefs.enable(LogType.BROWSER, Level.ALL);
                DCChrome.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
                // Chrome Options
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--incognito");
//                options.addArguments("--headless");   //TODO: pass arguments in a better way
                DCChrome.setCapability(ChromeOptions.CAPABILITY, options);
                // Setup Proxy
                if (propertiesLoader.getZAPAddress() != null) {
                    setupProxy(DCChrome);
                }
                driver = new ChromeDriver(DCChrome);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "safari":
                driver = new SafariDriver();
                break;
            case "ie11":
                WebDriverManager.iedriver().setup();
                DesiredCapabilities DCIE = DesiredCapabilities.internetExplorer();
                DCIE.setJavascriptEnabled(true);
                DCIE.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                DCIE.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
                DCIE.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
                DCIE.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
                DCIE.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
                DCIE.setCapability("ignoreZoomSetting", true);
                driver = new InternetExplorerDriver(DCIE);
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                log.warn(String.format("Wrong browser type `%s`. Initializing Chrome by default.", browserType));
                break;
        }
        if (!propertiesLoader.isCI()) {
            maximize();
        }
        setImplicitWait();
    }

    public WebDriver getDriver() {
        if (driver == null)
            log.error("DriverManager not initialized!");
        return driver;
    }

    private void deleteCookies() {
        driver.manage().deleteAllCookies();
        log.info("Deleting the cookies");
    }

    private void maximize() {
        driver.manage().window().maximize();
        log.info("Maximizing browser window");
    }

    private void setImplicitWait() {
        int seconds = propertiesLoader.getImplicitWait();
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
        log.debug(String.format("Setting implicit wait to %d seconds", seconds));
    }

    public void quit() {
        if (driver != null) {
            driver.quit();
            log.info("Quitting the browser");
        }
    }

    private void setupProxy(DesiredCapabilities capabilities) {
        String server = String.format("%s:%s", propertiesLoader.getZAPAddress(), propertiesLoader.getZAPPort());
        log.info(String.format("Trying to setup proxy on %s", server));
        Proxy proxy = new Proxy();
        proxy.setHttpProxy(server);
        proxy.setFtpProxy(server);
        proxy.setSslProxy(server);
        capabilities.setCapability("proxy", proxy);
        log.info(String.format("Proxy set on %s", server));
    }

}
