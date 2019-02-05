package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;
import utilities.PropertiesLoader;

public abstract class BasePage extends LoadableComponent<BasePage> {
    protected static final Logger log = LogManager.getLogger(Logger.class.getName());
    private static String baseUrl;
    protected String relativeUrl;
    protected PropertiesLoader propertiesLoader;
    protected WebDriver driver;

    public BasePage(WebDriver driver, String relativeUrl) {
        propertiesLoader = new PropertiesLoader();
        baseUrl = propertiesLoader.getBaseUrl();
        this.relativeUrl = validateAndFormatRelativeUrl(relativeUrl);
        this.driver = driver;

        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);

        log.debug(getClass().getName() + " -> Initializing elements");
    }

    public BasePage(WebDriver driver) {
        this(driver, "");
    }

    @Override
    public BasePage get() {
        log.info("Navigating to: " + getClass().getName());
        return super.get();
    }

    public String getUrl() {
        return baseUrl + relativeUrl;
    }

    private String validateAndFormatRelativeUrl(String relativeUrl) {
        if (relativeUrl.contains("\\")) {
            // Relative url: "relativeUrl" in class "className" contains "\" instead of "/". Make sure it's valid!
            log.warn("Relative url: \"" + relativeUrl + "\" in class \"" + getClass().getName() +
                    "\" contains \"\\\" instead of \"/\". Make sure it's valid!");
            relativeUrl.replace("\\", "/");
        }
        if (!relativeUrl.startsWith("/")) {
            // Relative url: "relativeUrl" in class "className" does not start with "/". Make sure it's valid!
            log.warn("Relative url: \"" + relativeUrl + "\" in class \"" + getClass().getName() +
                    "\" does not start with \"/\". Make sure it's valid!");
            relativeUrl = "/" + relativeUrl;
        }
        if (relativeUrl.endsWith("/")) {
            log.warn("Relative url: \"" + relativeUrl + "\" in class \"" + getClass().getName() +
                    "ends with a slash. Make sure it's valid!");
            relativeUrl = relativeUrl.substring(0, relativeUrl.length() - 1);
        }

        return relativeUrl;
    }
}
