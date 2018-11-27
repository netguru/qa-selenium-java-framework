package sections.editrestaurant;

import base.BasePage;
import ngelements.NGButton;
import ngelements.NGTextInput;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ContactInformationSection extends BasePage {

    @FindBy(css = "input[name=phone]")
    private NGTextInput phoneInput;

    @FindBy(xpath = "//label[text()='Country']//preceding-sibling::button")
    private NGButton phonePrefixCombobox;

    @FindBy(css = "input[name=email]")
    private NGTextInput emailInput;

    @FindBy(css = "input[name=website]")
    private NGTextInput websiteInput;

    @FindBy(css = "input[name=facebook]")
    private NGTextInput facebookInput;

    @FindBy(css = "input[name=instagram]")
    private NGTextInput instagramInput;

    @FindBy(xpath = "//div[@role='option'][2]")
    private NGButton firstNotNullOption;

    public ContactInformationSection(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isInitialized() {
        return phoneInput.isDisplayed();
    }

    public void provideMaximumData(String timestamp) {
        provideRequiredData();
        provideEmailInput(String.format("test+%s@gmail.com", timestamp));
        provideWebsiteInput(String.format("http://%s.com", timestamp));
        provideFacebookInput(String.format("https://facebook.com/%s", timestamp));
        provideInstagram(String.format("https://instagram.com/%s", timestamp));
    }

    public void provideRequiredData() {
        providePhoneNumber("france","111222333");
    }

    public void providePhoneNumber(String country, String phoneNumber) {
        providePhonePrefixByCountryName(country);
        providePhoneLocalPart(phoneNumber);
    }

    public void triggerValidationEmailInput() {
        emailInput.sendKeys("invalidEmail");
    }

    public void triggerValidationWebsiteInput() {
        websiteInput.sendKeys(Keys.SPACE);
    }

    public void triggerValidationFacebookInput() {
        facebookInput.sendKeys(Keys.SPACE);
    }

    public void triggerValidationInstagramInput() {
        instagramInput.sendKeys(Keys.SPACE);
    }

    private void providePhoneLocalPart(String localPart) {
        phoneInput.sendKeys(localPart);
    }

    private void providePhonePrefixByCountryName(String name) {
        phonePrefixCombobox.click();
        phonePrefixCombobox.sendKeys(name);
        firstNotNullOption.click();
    }

    public void provideEmailInput(String email) {
        emailInput.sendKeys(email);
    }

    public void provideWebsiteInput(String website) {
        websiteInput.sendKeys(website);
    }

    public void provideFacebookInput(String facebook) {
        facebookInput.sendKeys(facebook);
    }

    public void provideInstagram(String instagram) {
        instagramInput.sendKeys(instagram);
    }

    public NGTextInput getPhoneInput() {
        return phoneInput;
    }

    public NGTextInput getEmailInput() {
        return emailInput;
    }

    public NGTextInput getWebsiteInput() {
        return websiteInput;
    }

    public NGTextInput getFacebookInput() {
        return facebookInput;
    }

    public NGTextInput getInstagramInput() {
        return instagramInput;
    }
}
