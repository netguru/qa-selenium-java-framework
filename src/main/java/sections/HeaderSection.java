package sections;

import base.BasePage;
import ngelements.NGButton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HeaderSection extends BasePage {

    @FindBy(xpath = "//img[contains(@class, 'styled__LogoImage')]")
    private NGButton logoButton;

    @FindBy(xpath = "//a[contains(@class, 'styled__Link') and contains(text(), 'Log in')]")
    private NGButton logInButton;

    @FindBy(xpath = "//div[contains(@class, 'styled__Profile')]")
    private NGButton profileButton;

    @FindBy(xpath = "//a[contains(@class, 'styled__Link') and contains(text(), 'Log out')]")
    private NGButton logOutButton;

    public HeaderSection(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isInitialized() {
        return logoButton.isDisplayed();
    }

    public void logOut() {
        clickProfileButton();
        clickLogOutButton();
    }

    public void clickLogoButton() {
        logoButton.click();
    }

    public void clickLogInButton() {
        logInButton.click();
    }

    public void clickProfileButton() {
        profileButton.click();
        waitUntilProfileDropdownExtends();
    }

    public void clickLogOutButton() {
        logOutButton.click();
    }

    public boolean isUserLoggedIn() {
        return profileButton.isDisplayed();
    }

    public boolean isUserLoggedOut() {
        return logInButton.isDisplayed();
    }

    private void waitUntilProfileDropdownExtends() {
        logOutButton.waitUntilIsClickable(5);
    }
}
