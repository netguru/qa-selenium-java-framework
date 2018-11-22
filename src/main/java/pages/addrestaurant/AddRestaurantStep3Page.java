package pages.addrestaurant;

import base.BasePage;
import ngelements.NGTextBlock;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class AddRestaurantStep3Page extends BasePage {

    @FindBy(xpath = "//h3[contains(text(),'Opening hours')]")
    private NGTextBlock title;

    public AddRestaurantStep3Page(WebDriver driver) {
        super(driver, "/add#3");
    }

    @Override
    public boolean isInitialized() {
        return title.isDisplayed();
    }
}
