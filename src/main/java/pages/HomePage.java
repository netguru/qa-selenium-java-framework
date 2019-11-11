package pages;

import base.BasePage;
import ngelements.NGButton;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private By logoButton = By.xpath("//a[@href='https://www.netguru.com']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void load() {
        driver.get(getUrl());
    }

    @Override
    protected void isLoaded() throws Error {
        Assert.assertEquals(getUrl(), driver.getCurrentUrl());
    }

    public NGButton getLogo() {
        return (NGButton) driver.findElement(logoButton);
    }
}
