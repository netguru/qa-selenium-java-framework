package base;

import managers.Context;
import managers.PageObjectManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utilities.PropertiesLoader;

import org.assertj.core.api.SoftAssertions;

public abstract class BaseTest {

    protected static final Logger log = LogManager.getLogger(Logger.class.getName());
    protected static PageObjectManager pages;
    protected static Context context;
    protected static PropertiesLoader propertiesLoader = new PropertiesLoader();

    protected SoftAssertions softAssertions;

    protected void dismissCookiesModal() {
        pages.getHomePage().acceptCookies();
    }
}
