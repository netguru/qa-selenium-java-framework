package base;

import managers.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utilities.PropertiesLoader;

public abstract class BasePage {
    protected static final Logger log = LogManager.getLogger(Logger.class.getName());
    private static String baseUrl;
    private static String language;
    protected String relativeUrl;
    private PropertiesLoader propertiesLoader;


    public BasePage(WebDriver driver) {
        propertiesLoader = new PropertiesLoader();
        baseUrl = propertiesLoader.getBaseUrl();
        language = propertiesLoader.getLanguage();

        PageFactory.initElements(driver, this);
        log.debug(getClass().getName() + " -> Initializing elements");
    }

    public abstract boolean isInitialized();

    public void goTo() {
        Driver.getDriver().navigate().to(getUrl());
        log.info("Navigating to: " + getClass().getName());
    }

    public String getUrl() {
        return baseUrl + "/" + language + "/" + relativeUrl;
    }
}