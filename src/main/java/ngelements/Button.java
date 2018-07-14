package ngelements;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

public class Button extends HtmlElement {
    protected static final Logger log = LogManager.getLogger(Logger.class.getName());

    @Override
    public void click() {
        this.getWrappedElement().click();
        log.info("Button: " + this.getName() + " was clicked");
    }
}

