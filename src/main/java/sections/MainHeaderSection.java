package sections;

import base.BaseSection;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class MainHeaderSection extends BaseSection {

    @FindBy(css = ".nav-link-holder--user-details .dropdown-toggle")
    private WebElement profileDropdown;

    @FindBy(xpath = "//ul[@class='dropdown-menu']/li/a[contains(text(), 'Logout')]")
    private WebElement logOutButton;

    public void logOut() {
        clickProfileDropdown();
        logOutButton.click();
        log.info("Selecting Log Out button");
    }

    public void clickProfileDropdown() {
        profileDropdown.click();
        log.info("Expanding profile dropdown");
    }
}
