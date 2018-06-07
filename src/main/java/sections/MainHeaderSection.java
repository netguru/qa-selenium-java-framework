package sections;

import base.SectionBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainHeaderSection extends SectionBase {

    @FindBy(css = ".nav-link-holder--user-details .dropdown-toggle")
    WebElement profileDropdown;

    @FindBy(xpath = "//ul[@class='dropdown-menu']/li/a[contains(text(), 'Logout')]")
    WebElement logOutButton;

    public MainHeaderSection(WebDriver driver) {
        super(driver);
    }

    public void logOut() {
        profileDropdown.click();
        logOutButton.click();
    }
}
