package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class SectionBase {
    protected WebDriver driver;

    public SectionBase(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(this.driver, this);
    }
}
