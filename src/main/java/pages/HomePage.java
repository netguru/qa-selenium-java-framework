package pages;

import base.BasePage;
import ngelements.NGButton;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(id = "sign")
    private NGButton logo;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public NGButton getLogo() {
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
