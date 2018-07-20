package sections;

import base.BaseSection;
import ngelements.NGButton;
import ngelements.NGSelect;
import org.openqa.selenium.support.FindBy;

public final class MainHeaderSection extends BaseSection {

    @FindBy(css = ".nav-link-holder--user-details .dropdown-toggle")
    private NGSelect profileDropdown;

    @FindBy(xpath = "//ul[@class='dropdown-menu']/li/a[contains(text(), 'Logout')]")
    private NGButton logOutButton;

    public void logOut() {
        clickProfileDropdown();
        logOutButton.click();
    }

    public void clickProfileDropdown() {
        profileDropdown.click();
    }
}
