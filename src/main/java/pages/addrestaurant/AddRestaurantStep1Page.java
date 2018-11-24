package pages.addrestaurant;

import base.BasePage;
import ngelements.NGButton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import sections.editrestaurant.BasicInformationSection;

public class AddRestaurantStep1Page extends BasePage {

    private BasicInformationSection basicInformationSection = new BasicInformationSection(driver);

    @FindBy(xpath = "//button[contains(@type, 'submit')]")
    private NGButton nextPageButton;

    public AddRestaurantStep1Page(WebDriver driver) {
        super(driver, "/add#1");
    }

    @Override
    public boolean isInitialized() {
        return (basicInformationSection.isInitialized() && nextPageButton.isDisplayed());
    }

    public void submitFormWithMinimumData(String timestamp) {
        basicInformationSection.provideMinimumData(timestamp);
        clickNextPageButton();
    }

    public void submitFormWithMaximumData(String timestamp) {
        basicInformationSection.provideMaximumData(timestamp);
        clickNextPageButton();
    }

    public void clickNextPageButton() {
        nextPageButton.click();
    }

    public BasicInformationSection getBasicInformationSection() {
        return this.basicInformationSection;
    }
}
