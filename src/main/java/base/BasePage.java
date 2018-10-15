package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;
import utilities.PropertiesLoader;

public abstract class BasePage {
    protected static final Logger log = LogManager.getLogger(Logger.class.getName());
    private static String baseUrl;
    protected String relativeUrl;
    protected PropertiesLoader propertiesLoader;
    private WebDriver driver;


    public BasePage(WebDriver driver, String relativeUrl) {
        if (!relativeUrl.startsWith("/")) {
            log.warn("Relative url: \"" + relativeUrl + "\" in class \"" + getClass().getName() +
                    "\" does not start with " + "\"/\". Make sure it's valid!");
            relativeUrl = "/" + relativeUrl;
        }

        propertiesLoader = new PropertiesLoader();
        baseUrl = propertiesLoader.getBaseUrl();
        this.relativeUrl = relativeUrl;
        this.driver = driver;

        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);

        log.debug(getClass().getName() + " -> Initializing elements");
    }

    public abstract boolean isInitialized();

    public void goTo() {
        driver.navigate().to(getUrl());
        log.info("Navigating to: " + getClass().getName());
    }

    public String getUrl() {
        return baseUrl + "/" + relativeUrl;
    }
}
