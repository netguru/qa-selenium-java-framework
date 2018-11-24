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

    @FindBy(xpath = "//span[contains(@class, 'Step1__Error')]")
    private NGTextBlock typesErrorMessage;

    public BasicInformationSection(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isInitialized() {
        return bioInput.isDisplayed();
    }

    public void provideMinimumData(String timestamp) {
        provideName("AutomationBusiness+" + timestamp);
        selectCountry("Poland");
        providePostCode("PostCode+" + timestamp);
        provideCity("City+" + timestamp);
        provideStreetName("StreetName+" + timestamp);

        selectTypesCheckboxes(1);
        selectCuisines(1);
        selectFoodAndDrinks(1);
        selectPerfectFors(3);
    }

    public void provideMaximumData(String timestamp) {
        provideName("AutomationBusiness+" + timestamp);
        provideTagline("Tagline+" + timestamp);
        selectCountry("Poland");
        selectRegion("Mazowieckie");
        providePostCode("PostCode+" + timestamp);
        provideCity("City+" + timestamp);
        provideStreetName("StreetName+" + timestamp);
        provideStreetNumber("StreetNumber+" + timestamp);

        selectTypesCheckboxes(3);
        selectCuisines(5);
        selectFoodAndDrinks(6);
        selectPerfectFors(10);
        selectDiets(1);

        provideOwnerRole("OwnerRole+" + timestamp);
        provideBio("Bio+" + timestamp);
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
        return nameInput.getValidator().getText();
    }

    public String getCountryErrorMessage() {
        return countryInput.getValidator().getText();
    }

    public String getPostCodeErrorMessage() {
        return postCodeInput.getValidator().getText();
    }

    public String getCityErrorMessage() {
        return cityInput.getValidator().getText();
    }

    public String getStreetNameErrorMessage() {
        return streetNameInput.getValidator().getText();
    }

    public String getTypesErrorMessage() {
        return typesErrorMessage.getText();
    }

    public String getCuisinesErrorMessage() {
        return cuisinesInput.getValidator().getText();
    }

    public String getFoodAndDrinksErrorMessage() {
        return foodAndDrinksInput.getValidator().getText();
    }

    public String getPerfectForsErrorMessage() {
        return perfectForsInput.getValidator().getText();
    }
}
