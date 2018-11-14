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
        try {
            return logoButton.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
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
        // Waiting until the dropdown extends
        logOutButton.waitUntilIsClickable(5);
    }

    public void clickLogOutButton() {
        logOutButton.click();
    }

    public boolean isUserLoggedIn() {
        try {
            return profileButton.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public boolean isUserLoggedOut() {
        try {
            return logInButton.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
}
