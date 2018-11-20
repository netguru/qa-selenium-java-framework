package base;

import ngelements.NGButton;
import ngelements.NGTextBlock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;
import utilities.PropertiesLoader;

public abstract class BasePage {
    protected static final Logger log = LogManager.getLogger(Logger.class.getName());
    private static String baseUrl;
    private static String language;
    protected String relativeUrl;
    protected PropertiesLoader propertiesLoader;
    protected WebDriver driver;

    @FindBy(xpath = "//*[contains(@class, 'notification-message')]")
    NGTextBlock snackbarMessage;

    @FindBy(xpath = "//*[contains(@class, 'Button-sc-1emfup8-0')]")
    NGButton acceptCookiesButton;

    public BasePage(WebDriver driver, String relativeUrl) {
        propertiesLoader = new PropertiesLoader();
        baseUrl = propertiesLoader.getBaseUrl();
        language = propertiesLoader.getLanguage();
        this.relativeUrl = validateAndFormatRelativeUrl(relativeUrl);
        this.driver = driver;

        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);

        log.debug(getClass().getName() + " -> Initializing elements");
    }

    public BasePage(WebDriver driver) {
        this(driver, "/#");
    }

    public abstract boolean isInitialized();

    public void goTo() {
        driver.get(getUrl());
        log.info("Navigating to: " + getClass().getName());
    }

    public String getUrl() {
        return baseUrl + "/" + language + relativeUrl;
    }

    public void acceptCookies() {
        acceptCookiesButton.moveToElementAndClick();
    }

    public String getSnackbarMessageAndCloseSnackbar() {
        String message = getSnackbarMessage();
        closeSnackBar();
        return message;
    }

    public String getSnackbarMessage() {
        snackbarMessage.waitUntilIsVisible(5);
        return snackbarMessage.getText();
    }

    public void closeSnackBar() {
        snackbarMessage.waitUntilIsClickable(5);
        snackbarMessage.click();
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
