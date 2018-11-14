package sections;

import base.BasePage;
import ngelements.NGButton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class EditRestaurantColumnSection extends BasePage {

    @FindBy(xpath = "//div[contains(@class, 'MenuItem') and contains(text(), 'Basic Information')]")
    private NGButton basicInformationButton;

    public EditRestaurantColumnSection(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isInitialized() {
        try {
            return basicInformationButton.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
}
