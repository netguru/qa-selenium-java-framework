package pages;

import base.BasePage;
import ngelements.NGButton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage {

    @FindBy(xpath = "(//button[contains(text(), 'Clear refinements')])[1]")
    private NGButton clearRefinementsButton;

    public SearchPage(WebDriver driver) {
        super(driver, "/search");
    }

    @Override
    public boolean isInitialized() {
        return clearRefinementsButton.isDisplayed();
    }
}
