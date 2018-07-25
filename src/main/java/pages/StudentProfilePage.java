package pages;

import base.BasePage;
import ngelements.NGTextBlock;
import org.openqa.selenium.support.FindBy;

public final class StudentProfilePage extends BasePage {

    @FindBy(id = "consumer-teaching-group")
    private NGTextBlock studentProfileDescription;

    @Override
    public boolean isInitialized() {
        return studentProfileDescription.isDisplayed();
    }
}
