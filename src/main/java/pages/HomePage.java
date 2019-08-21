package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

public class HomePage extends BasePage {
    @FindBy(xpath = "//a[@href='https://www.netguru.com']")
    private HtmlElement logo;

    public HomePage(WebDriver driver) {
        super(driver, "/");
    }

    public HtmlElement getLogo() {
        return logo;
    }

    @Override
    protected void load() {
        driver.get(getUrl());
    }

    @Override
    protected void isLoaded() throws Error {
        Assert.assertEquals(getUrl(), driver.getCurrentUrl());
    }
}
