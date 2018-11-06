package pages;

import base.BasePage;
import ngelements.NGButton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HeaderSection extends BasePage {

    @FindBy(xpath = "//*[contains(@class, 'styled__LogoImage-sc-1luft72-7')]")
    private NGButton logoButton;

    @FindBy(xpath = "//*[contains(@class, 'styled__Link-sc-1luft72-2') and contains(text(), 'Log in')]")
    private NGButton logInButton;

    @FindBy(xpath = "//*[contains(@class, 'styled__Profile-sc-1luft72-4')]")
    private NGButton profileButton;

    @FindBy(xpath = "//*[contains(@class, 'styled__Link') and contains(text(), 'Log out')]")
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
