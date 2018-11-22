package pages.addrestaurant;

import base.BasePage;
import ngelements.NGButton;
import ngelements.NGTextBlock;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import sections.editrestaurant.ContactInformationSection;

public class AddRestaurantStep2Page extends BasePage {

    private ContactInformationSection contactInformationSection;

    @FindBy(xpath = "//h3[contains(text(),'Contact information')]")
    private NGTextBlock title;

    @FindBy(xpath = "//button[contains(text(), 'Previous step')]")
    private NGButton previousStepButton;

    @FindBy(xpath = "//button[contains(text(), 'Next step')]")
    private NGButton nextStepButton;

    public AddRestaurantStep2Page(WebDriver driver) {
        super(driver, "/add#2");
        contactInformationSection = new ContactInformationSection(driver);
    }

    @Override
    public boolean isInitialized() {
        try {
            return (title.isDisplayed());
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void submitFormWithRequiredData() {
        contactInformationSection.provideRequiredData();
        nextStepButton.click();
    }

    public void submitFormWithMaximumData(String timestamp) {
        contactInformationSection.provideMaximumData(timestamp);
        nextStepButton.click();
    }

    public void clickNextButton() {
        nextStepButton.click();
    }

    public void clickPreviousButton() {
        previousStepButton.click();
    }

    public ContactInformationSection getContactInformationSection() {
        return contactInformationSection;
    }

}
