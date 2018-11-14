package pages.addrestaurant;

import base.BasePage;
import ngelements.NGButton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import sections.editrestaurant.BasicInformationSection;

public class AddRestaurantStep1Page extends BasePage {

    private BasicInformationSection basicInformationSection = new BasicInformationSection(getDriver());

    @FindBy(xpath = "//button[contains(@type, 'submit')]")
    private NGButton nextPageButton;

    public AddRestaurantStep1Page(WebDriver driver) {
        super(driver, "/add#1");
    }

    @Override
    public boolean isInitialized() {
        try {
            return (basicInformationSection.isInitialized() && nextPageButton.isDisplayed());
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void submitFormWithMinimumData(String timestamp) {
        basicInformationSection.provideName("AutomationBusiness+" + timestamp);
        basicInformationSection.selectCountry("Poland");
        basicInformationSection.providePostCode("PostCode+" + timestamp);
        basicInformationSection.provideCity("City+" + timestamp);
        basicInformationSection.provideStreetName("StreetName+" + timestamp);

        basicInformationSection.selectTypesCheckboxes(1);
        basicInformationSection.selectCuisines(1);
        basicInformationSection.selectFoodAndDrinks(1);
        basicInformationSection.selectPerfectFors(3);

        clickNextPageButton();
    }

    public void submitFormWithMaximumData(String timestamp) {
        basicInformationSection.provideName("AutomationBusiness+" + timestamp);
        basicInformationSection.provideTagline("Tagline+" + timestamp);
        basicInformationSection.selectCountry("Poland");
        basicInformationSection.selectRegion("Mazowieckie");
        basicInformationSection.providePostCode("PostCode+" + timestamp);
        basicInformationSection.provideCity("City+" + timestamp);
        basicInformationSection.provideStreetName("StreetName+" + timestamp);
        basicInformationSection.provideStreetNumber("StreetNumber+" + timestamp);

        basicInformationSection.selectTypesCheckboxes(3);
        basicInformationSection.selectCuisines(5);
        basicInformationSection.selectFoodAndDrinks(6);
        basicInformationSection.selectPerfectFors(10);
        basicInformationSection.selectDiets(1);

        basicInformationSection.provideOwnerRole("OwnerRole+" + timestamp);
        basicInformationSection.provideBio("Bio+" + timestamp);

        clickNextPageButton();
    }

    public void clickNextPageButton() {
        nextPageButton.click();
    }

    public BasicInformationSection getBasicInformationSection() {
        return this.basicInformationSection;
    }
}
