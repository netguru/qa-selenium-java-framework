package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;
import utilities.UtilitiesFunctions;

import java.util.Properties;

public abstract class BasePage {
    private static String baseUrl;
    private static String language;
    protected String relativeUrl;
    protected static final Logger log = LogManager.getLogger(Logger.class.getName());

    public BasePage() {
        Properties props = UtilitiesFunctions.loadProperties();

        baseUrl = props.getProperty("BASE_URL").toLowerCase();
        language = props.getProperty("LANGUAGE").toLowerCase();

        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(Driver.getDriver())), this);

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