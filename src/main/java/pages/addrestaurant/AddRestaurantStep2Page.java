package pages.addrestaurant;

import base.BasePage;
import ngelements.NGButton;
import ngelements.NGTextInput;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class AddRestaurantStep2Page extends BasePage {

    @FindBy(xpath = "//input[contains(@name, 'phone')]")
    private NGTextInput phoneInput;

    @FindBy(xpath = "//button[contains(text(), 'Previous step')]")
    private NGButton previousStepButton;

    public AddRestaurantStep2Page(WebDriver driver) {
        super(driver, "/add#2");
    }

    @Override
    public boolean isInitialized() {
        try {
            return (phoneInput.isDisplayed() && previousStepButton.isDisplayed());
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    @Override
    public void goTo() {
        // TODO: get to step 1, complete the form, get to next step
    }
}
