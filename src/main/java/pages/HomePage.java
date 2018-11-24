package pages;

import base.BasePage;
import ngelements.NGTextInput;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(xpath = "//div[contains(@class, 'styled__Search')]/input")
    private NGTextInput searchBar;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isInitialized() {
        return searchBar.isDisplayed();
    }
}
