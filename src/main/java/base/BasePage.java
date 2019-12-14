package base;

import managers.Context;
import managers.JSExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
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
    protected JSExecutor jsExecutor;

    private By.ByXPath passwordInput = new By.ByXPath("//input[@type='password']");
    private By.ByXPath submitButton = new By.ByXPath("//button[@type='submit']");

    public BasePage(WebDriver driver, String relativeUrl) {
        propertiesLoader = new PropertiesLoader();
        jsExecutor = Context.jsExecutor;
        baseUrl = propertiesLoader.getBaseUrl();
        this.relativeUrl = validateAndFormatRelativeUrl(relativeUrl);
        this.driver = driver;

        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);

        log.debug(String.format("%s -> Initializing elements", getClass().getName()));
    }

    public BasePage(WebDriver driver) {
        this(driver, "/");
    }

    @Override
    public BasePage get() {
        log.info(String.format("Navigating to: %s", getClass().getName()));
        return super.get();
    }

    public String getUrl() {
        return baseUrl + relativeUrl;
    }

    public void loginIntoStaging() {
        String password = propertiesLoader.getStagingPassword();
        if (password == null) {
            log.warn("Trying to login into staging without password");
            return;
        }
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(submitButton).click();
        log.info("Logged in into staging");
    }

    private String validateAndFormatRelativeUrl(String relativeUrl) {
        if (relativeUrl.contains("\\")) {
            log.warn(String.format("Relative url: \"%s\" in class \"%s\" contains\"\\\" instead of \"/\". Make sure it's valid!",
                    relativeUrl, getClass().getName()));
            relativeUrl.replace("\\", "/");
        }
        if (!relativeUrl.startsWith("/")) {
            log.warn(String.format("Relative url: \"%s\" in class \"%s\" does not start with \"/\". Make sure it's valid!",
                    relativeUrl, getClass().getName()));
            relativeUrl = "/" + relativeUrl;
        }
        if (relativeUrl.endsWith("/")) {
            log.warn(String.format("Relative url: \"%s\" in class \"%s\" ends with a slash. Make sure it's valid!",
                    relativeUrl, getClass().getName()));
            relativeUrl = relativeUrl.substring(0, relativeUrl.length() - 1);
        }

        return relativeUrl;
    }
}
