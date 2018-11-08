package pages;

import base.BasePage;
import ngelements.NGTextBlock;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class EditProfileBasicInformationPage extends BasePage {

    @FindBy(xpath = "//img[contains(@alt, 'Basic information')]")
    private NGTextBlock sectionTitle;

    public EditProfileBasicInformationPage(WebDriver driver) {
        super(driver, "/profile?section=basicInformations");
    }

    @Override
    public boolean isInitialized() {
        return sectionTitle.isDisplayed();
    }
}
