package managers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class JSExecutor {

    private static final Logger log = LogManager.getLogger(Logger.class.getName());

    private JavascriptExecutor js;

    public JSExecutor(WebDriver driver) {
        js = (JavascriptExecutor) driver;
    }

    public boolean waitForJQueryToLoad() {
        try {
            return (Long) executeScript("return jQuery.active") == 0;
        } catch (Exception e) {
            // no jQuery present
            return true;
        }
    }

    public boolean waitForJSToLoad() {
        return executeScript("return document.readyState").toString().equals("complete");
    }

    public void scrollToBottom() {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        log.info("Scrolled to bottom of the page");
    }

    public void scrollToTop() {
        js.executeScript("window.scrollTo(0, 0)");
        log.info("Scrolled to top of the page");
    }

    public void scrollTo(int pointX, int pointY) {
        js.executeScript(String.format("window.scrollTo(%d, %d)", pointX, pointY));
        log.info(String.format("Scrolled to point (%d, %d) on the page", pointX, pointY));
    }

    public Object executeScript(String script, Object... objects) {
        Object returnValue = js.executeScript(script);
        log.info(String.format("Executed script: \"%s\"", script));

        return returnValue;
    }

    public JavascriptExecutor getExecutor() {
        return js;
    }
}
