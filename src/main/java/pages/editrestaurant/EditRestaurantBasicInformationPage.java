package pages.editrestaurant;

import base.BasePage;
import ngelements.NGButton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import sections.EditRestaurantColumnSection;
import sections.editrestaurant.BasicInformationSection;

import java.util.List;

public class EditRestaurantBasicInformationPage extends BasePage {

    private BasicInformationSection basicInformationSection = new BasicInformationSection(driver);
    private EditRestaurantColumnSection columnSection = new EditRestaurantColumnSection(driver);

    @FindBy(xpath = "//div[contains(@class, 'Header')]/div[contains(@class, 'ePrPrn')]")
    private NGButton restaurantsDropdownButton;

    @FindBy(xpath = "//*[contains(@role, 'option')]")
    private List<NGButton> dropdownOptions;

    public EditRestaurantBasicInformationPage(WebDriver driver) {
        super(driver, "/edit-restaurant");
    }

    @Override
    public boolean isInitialized() {
        return (basicInformationSection.isInitialized() && columnSection.isInitialized());
    }

    public void selectRestaurantByName(String restaurantName) {
        getRestaurantsDropdownButtonByName(restaurantName).click();
    }

    public boolean isRestaurantExist(String restaurantName) {
        NGButton restaurantButton = getRestaurantsDropdownButtonByName(restaurantName);
        closeRestaurantDropdown();

        if (restaurantButton != null) {
            return true;
        } else {
            return false;
        }
    }

    public BasicInformationSection getBasicInformationSection() {
        return this.basicInformationSection;
    }

    public EditRestaurantColumnSection getColumnSection() {
        return this.columnSection;
    }

    private NGButton getRestaurantsDropdownButtonByName(String restaurantName) {
        expandRestaurantsDropdown();

        return dropdownOptions.stream().filter(option -> option.getText().equals(restaurantName))
                .findFirst().orElse(null);
    }

    private void expandRestaurantsDropdown() {
        restaurantsDropdownButton.click();
    }

    private void closeRestaurantDropdown() {
        restaurantsDropdownButton.click();
    }
}
