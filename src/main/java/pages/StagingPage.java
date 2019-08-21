package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

public class StagingPage extends BasePage {
    @FindBy(xpath = "//input[@type='password']")
    private HtmlElement passwordInput;
    @FindBy(xpath = "//button[@type='submit']")
    private HtmlElement submitButton;

    public StagingPage(WebDriver driver) {
        super(driver, "/");
    }

    @Override
    protected void load() {
        driver.get(getUrl());
    }

    @Override
    protected void isLoaded() throws Error {
        Assert.assertEquals(getUrl(), driver.getCurrentUrl());
    }

    public void loginIntoStaging() {
        String password = propertiesLoader.getStagingPassword();
        if (password == null) {
            log.error("Trying to login into staging without password");
            return;
        }
        passwordInput.sendKeys(password);
        submitButton.click();
        log.info("Logged in into staging");
    }
}
