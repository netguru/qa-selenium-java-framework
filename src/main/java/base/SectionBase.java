package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

public abstract class SectionBase {
    protected static final Logger log = LogManager.getLogger(Logger.class.getName());

    public SectionBase() {
        PageFactory.initElements(Driver.getDriver(), this);
        log.debug(getClass().getName() + " -> Initializing elements");
    }
}
