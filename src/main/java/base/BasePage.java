package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import utilities.UtilitiesFunctions;

import java.util.Properties;

public abstract class BasePage {
    protected static String baseUrl;
    protected static String language;
    protected String relativeUrl;
    protected static final Logger log = LogManager.getLogger(Logger.class.getName());

    public BasePage() {
        Properties props = UtilitiesFunctions.loadFile("initConfig.properties");

        baseUrl = props.getProperty("base_url").toLowerCase();
        language = props.getProperty("language").toLowerCase();

        PageFactory.initElements(Driver.getDriver(), this);
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