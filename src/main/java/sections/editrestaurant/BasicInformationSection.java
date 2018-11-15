package sections.editrestaurant;

import base.BasePage;
import ngelements.NGButton;
import ngelements.NGTextBlock;
import ngelements.NGTextInput;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BasicInformationSection extends BasePage {

    @FindBy(xpath = "//input[contains(@name, 'name')]")
    private NGTextInput nameInput;

    @FindBy(xpath = "//input[contains(@name, 'tagline')]")
    private NGTextInput taglineInput;

    @FindBy(xpath = "//input[contains(@name, 'countryCode')]")
    private NGTextInput countryInput;

    @FindBy(xpath = "//input[contains(@name, 'regionCode')]")
    private NGTextInput regionInput;

    @FindBy(xpath = "//input[contains(@name, 'postCode')]")
    private NGTextInput postCodeInput;

    @FindBy(xpath = "//input[contains(@name, 'city')]")
    private NGTextInput cityInput;

    @FindBy(xpath = "//input[@name='street']")
    private NGTextInput streetNameInput;

    @FindBy(xpath = "//input[contains(@name, 'streetNumber')]")
    private NGTextInput streetNumberInput;

    @FindBy(xpath = "//label[contains(@class, 'Checkbox')]")
    private List<NGButton> typesCheckboxes;

    @FindBy(xpath = "//input[contains(@name, 'cuisinesGroupsList')]")
    private NGTextInput cuisinesInput;

    @FindBy(xpath = "//input[contains(@name, 'foodAndDrinksGroupsList')]")
    private NGTextInput foodAndDrinksInput;

    @FindBy(xpath = "//input[contains(@name, 'quirksGroupsList')]")
    private NGTextInput perfectForsInput;

    @FindBy(xpath = "//input[contains(@name, 'dietsGroupsList')]")
    private NGTextInput dietsInput;

    @FindBy(xpath = "//input[contains(@name, 'ownerRole')]")
    private NGTextInput ownerRoleInput;

    @FindBy(xpath = "//textarea[contains(@name, 'bio')]")
    private NGTextInput bioInput;

    @FindBy(xpath = "//*[contains(@role, 'option')]")
    private List<NGButton> dropdownOptions;

    @FindBy(xpath = "(//div[contains(@class, 'sc-bwzfXH hjVfwW')])[1]/span[contains(@class, 'Input__Error')]")
    private NGTextBlock nameErrorMessage;

    @FindBy(xpath = "//div[contains(@role, 'combobox')]/span[contains(@class, 'Input__Error')]")
    private NGTextBlock countryErrorMessage;

    @FindBy(xpath = "(//div[contains(@class, 'sc-bwzfXH hjVfwW')])[3]/span[contains(@class, 'Input__Error')]")
    private NGTextBlock postCodeErrorMessage;

    @FindBy(xpath = "(//div[contains(@class, 'sc-bwzfXH hjVfwW')])[4]/span[contains(@class, 'Input__Error')]")
    private NGTextBlock cityErrorMessage;

    @FindBy(xpath = "(//div[contains(@class, 'sc-bwzfXH hjVfwW')])[5]/span[contains(@class, 'Input__Error')]")
    private NGTextBlock streetNameErrorMessage;

    @FindBy(xpath = "//span[contains(@class, 'Step1__Error')]")
    private NGTextBlock typesErrorMessage;

    @FindBy(xpath = "(//div[contains(@role, 'combobox')])[3]/span[contains(@class, 'MultipleSelect__Error')]")
    private NGTextBlock cuisinesErrorMessage;

    @FindBy(xpath = "(//div[contains(@role, 'combobox')])[4]/span[contains(@class, 'MultipleSelect__Error')]")
    private NGTextBlock foodAndDrinksErrorMessage;

    @FindBy(xpath = "(//div[contains(@role, 'combobox')])[5]/span[contains(@class, 'MultipleSelect__Error')]")
    private NGTextBlock perfectForsErrorMessage;

    public BasicInformationSection(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isInitialized() {
        try {
            return bioInput.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void provideName(String name) {
        nameInput.sendKeys(name);
    }

    public void provideTagline(String tagline) {
        taglineInput.sendKeys(tagline);
    }

    public void selectCountry(String country) {
        countryInput.sendKeys(country);
        dropdownOptions.get(0).click();
    }

    public void selectRegion(String region) {
        regionInput.sendKeys(region);
        dropdownOptions.get(0).click();
    }

    public void providePostCode(String postCode) {
        postCodeInput.sendKeys(postCode);
    }

    public void provideCity(String city) {
        cityInput.sendKeys(city);
    }

    public void provideStreetName(String streetName) {
        streetNameInput.sendKeys(streetName);
    }

    public void provideStreetNumber(String streetNumber) {
        streetNumberInput.sendKeys(streetNumber);
    }

    public void selectTypesCheckboxes(int amount) {
        for (int i = 0; i < amount; i++) {
            typesCheckboxes.get(i).click();
        }
    }

    public void selectCuisines(int amount) {
        for (int i = 0; i < amount; i++) {
            cuisinesInput.sendKeys(" " + Keys.BACK_SPACE);
            dropdownOptions.get(0).click();
        }
    }

    public void selectFoodAndDrinks(int amount) {
        for (int i = 0; i < amount; i++) {
            foodAndDrinksInput.sendKeys(" " + Keys.BACK_SPACE);
            dropdownOptions.get(0).click();
        }
    }

    public void selectPerfectFors(int amount) {
        for (int i = 0; i < amount; i++) {
            perfectForsInput.sendKeys(" " + Keys.BACK_SPACE);
            dropdownOptions.get(0).click();
        }
    }

    public void selectDiets(int amount) {
        for (int i = 0; i < amount; i++) {
            dietsInput.sendKeys(" " + Keys.BACK_SPACE);
            dropdownOptions.get(0).click();
        }
    }

    public void provideOwnerRole(String ownerRole) {
        ownerRoleInput.sendKeys(ownerRole);
    }

    public void provideBio(String bio) {
        bioInput.sendKeys(bio);
    }

    public boolean isRegionFieldDisplayed() {
        try {
            return regionInput.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public String getNameErrorMessage() {
        return nameErrorMessage.getText();
    }

    public String getCountryErrorMessage() {
        return countryErrorMessage.getText();
    }

    public String getPostCodeErrorMessage() {
        return postCodeErrorMessage.getText();
    }

    public String getCityErrorMessage() {
        return cityErrorMessage.getText();
    }

    public String getStreetNameErrorMessage() {
        return streetNameErrorMessage.getText();
    }

    public String getTypesErrorMessage() {
        return typesErrorMessage.getText();
    }

    public String getCuisinesErrorMessage() {
        return cuisinesErrorMessage.getText();
    }

    public String getFoodAndDrinksErrorMessage() {
        return foodAndDrinksErrorMessage.getText();
    }

    public String getPerfectForsErrorMessage() {
        return perfectForsErrorMessage.getText();
    }
}
