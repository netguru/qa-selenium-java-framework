package managers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
    if (!propertiesLoader.isCI()) {
      maximize();
    }
    setImplicitWait();
    setPageWaits();
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

  private void setPageWaits() {
    driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
    driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
  }

  public void quit() {
    if (driver != null) {
      driver.quit();
      log.info("Quitting the browser");
    }
  }

  private void setAndGoToBaseUrl() {
    String baseUrl = propertiesLoader.getBaseUrl().toLowerCase();
    driver.get(baseUrl);
    log.info("Open base url");
  }

}
