package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class SectionBase {
    protected WebDriver driver;
    protected static final Logger log = LogManager.getLogger(Logger.class.getName());

    public SectionBase(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(this.driver, this);
        log.debug(getClass().getName() + " -> Initializing elements");
    }
}
