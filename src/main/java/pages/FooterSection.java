package pages;

import base.BasePage;
import ngelements.NGButton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class FooterSection extends BasePage {

    @FindBy(xpath = "//button[contains(text(), 'Register your business')]")
    private NGButton registerYourBusinessButton;

    @FindBy(xpath = "//button[contains(text(), 'Become a Detective')]")
    private NGButton becomeADetectiveButton;

    public FooterSection(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isInitialized() {
        return registerYourBusinessButton.isDisplayed();
    }

    public void clickRegisterYourBusinessButton() {
        registerYourBusinessButton.click();
    }

    public void clickBecomeADetectiveButton() {
        becomeADetectiveButton.click();
    }

    public boolean isBecomeADetectiveButtonDisplayed() {
        try {
            return becomeADetectiveButton.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }

    }
}
