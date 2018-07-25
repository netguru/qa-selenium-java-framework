package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public abstract class BaseSection {
    protected static final Logger log = LogManager.getLogger(Logger.class.getName());

    public BaseSection() {
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(Driver.getDriver())), this);
        log.debug(getClass().getName() + " -> Initializing elements");
    }
}
