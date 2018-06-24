package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class PageBase {
    protected static String baseUrl;
    protected static String language;
    protected String relativeUrl;
    protected static final Logger log = LogManager.getLogger(Logger.class.getName());

    public PageBase() throws IOException {
        Properties props = new Properties();
        props.load( new FileInputStream("initConfig.properties") );

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