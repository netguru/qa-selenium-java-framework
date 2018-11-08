package pages;

import base.BasePage;
import ngelements.NGTextBlock;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ResetPasswordPage extends BasePage {

    @FindBy(xpath = "//*[contains(@class, 'styled__Message-sc-1mxkpa2-3')]")
    private NGTextBlock styledMessage;

    public ResetPasswordPage(WebDriver driver) {
        super(driver, "/reset-password");
    }

    @Override
    public boolean isInitialized() {
        return styledMessage.isDisplayed();
    }
}
