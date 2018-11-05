package base;

import managers.PageObjectManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utilities.PropertiesLoader;

public abstract class BaseTest {

    protected static final Logger log = LogManager.getLogger(Logger.class.getName());
    protected static PageObjectManager pages;
    protected static PropertiesLoader propertiesLoader = new PropertiesLoader();

    protected void dismissCookiesModal() {
        pages.getHomePage().acceptCookies();
    }
}
