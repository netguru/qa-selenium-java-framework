package pages;

import base.BasePage;
import ngelements.NGButton;
import ngelements.NGSelect;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public final class MainHeaderPage extends BasePage {

    @FindBy(css = ".nav-link-holder--user-details .dropdown-toggle")
    private NGSelect profileDropdown;

    @FindBy(xpath = "//ul[@class='dropdown-menu']/li/a[contains(text(), 'Logout')]")
    private NGButton logOutButton;

    public MainHeaderPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isInitialized() {
        return false;
    }

    public void logOut() {
        clickProfileDropdown();
        logOutButton.click();
    }

    public void clickProfileDropdown() {
        profileDropdown.click();
    }
}
