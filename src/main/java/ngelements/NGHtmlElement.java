package ngelements;

import managers.Context;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

class NGHtmlElement extends HtmlElement {

    protected static final Logger log = LogManager.getLogger(Logger.class.getName());
    protected JavascriptExecutor js = Context.jsExecutor.getExecutor();

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

    public void moveToElementAndClick() {
        Actions actions = new Actions(Context.driverManager.getDriver());
        actions.moveToElement(getWrappedElement()).click().perform();
        log.info(String.format("Element: %s was clicked", getName()));
    }

    public void scrollTo() {
        js.executeScript("arguments[0].scrollIntoView();", this.getWrappedElement());
        log.info(String.format("Element: %s was scrolled into view", getName()));
    }

    public void setAttribute(String attributeName, String attributeValue) {
        js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);",
                this.getWrappedElement(), attributeName, attributeValue);
        log.info(String.format("%s attribute of %s element was changed to: %s", attributeName, getName(), attributeValue));
    }
}
