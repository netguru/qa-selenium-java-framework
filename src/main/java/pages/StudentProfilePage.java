package pages;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class StudentProfilePage extends BasePage {

    @FindBy(id = "consumer-teaching-group")
    private WebElement studentProfileDescription;

    @Override
    public boolean isInitialized() {
        return studentProfileDescription.isDisplayed();
    }
}
