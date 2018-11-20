package ngelements;

import managers.Context;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

class NGHtmlElement extends HtmlElement {

    protected static final Logger log = LogManager.getLogger(Logger.class.getName());

    public void moveToElementAndClick() {
        Actions actions = new Actions(Context.driverManager.getDriver());
        actions.moveToElement(getWrappedElement()).click().perform();
        log.info("Element: " + getName() + " was clicked");
    }

    public void waitUntilIsVisible(Integer secondsForTimeout) {
        WebDriverWait webDriverWait = new WebDriverWait(Context.driverManager.getDriver(), secondsForTimeout);
        webDriverWait.until(ExpectedConditions.visibilityOf(getWrappedElement()));
    }

    public void waitUntilIsClickable(Integer secondsForTimeout) {
        WebDriverWait webDriverWait = new WebDriverWait(Context.driverManager.getDriver(), secondsForTimeout);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(getWrappedElement()));
    }

    public void waitUntilIsNotVisible(Integer secondsForTimeout) {
        WebDriverWait webDriverWait = new WebDriverWait(Context.driverManager.getDriver(), secondsForTimeout);
        webDriverWait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(getWrappedElement())));
    }

    public void waitUntilIsNotClickable(Integer secondsForTimeout) {
        WebDriverWait webDriverWait = new WebDriverWait(Context.driverManager.getDriver(), secondsForTimeout);
        webDriverWait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(getWrappedElement())));
    }
}
