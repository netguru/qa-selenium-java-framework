package pages;

import base.BasePage;
import ngelements.NGButton;
import ngelements.NGTextInput;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class AddRestaurantStep1Page extends BasePage {

    @FindBy(xpath = "//textarea[contains(@name, 'bio')]")
    private NGTextInput bioInput;

    @FindBy(xpath = "//button[contains(@type, 'submit')]")
    private NGButton nextPageButton;

    public AddRestaurantStep1Page(WebDriver driver) {
        super(driver, "/add#1");
    }

    @Override
    public boolean isInitialized() {
        try {
            return (bioInput.isDisplayed() && nextPageButton.isDisplayed());
        } catch (org.openqa.selenium.NoSuchElementException e1) {
            return false;
        }
    }
}
