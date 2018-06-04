package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class PageBase {
    protected WebDriver driver;
    protected static String baseUrl;
    protected static String language;
    protected String relativeUrl;

    public PageBase(WebDriver driver) throws IOException {
        Properties props = new Properties();
        props.load( new FileInputStream("initConfig.properties") );

        baseUrl = props.getProperty("base_url").toLowerCase();
        language = props.getProperty("language").toLowerCase();

        this.driver = driver;

        PageFactory.initElements(this.driver, this);
    }

    public abstract boolean isInitialized();

    public void goTo() {
        driver.navigate().to(getUrl());
    }

    public String getUrl() {
        return baseUrl + "/" + language + "/" + relativeUrl;
    }
}