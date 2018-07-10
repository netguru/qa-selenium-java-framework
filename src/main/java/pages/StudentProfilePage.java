package pages;

import base.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StudentProfilePage extends PageBase {

    @FindBy(id = "consumer-teaching-group")
    private WebElement studentProfileDescription;


    public StudentProfilePage() {
    }

    @Override
    public boolean isInitialized() {
        return studentProfileDescription.isDisplayed();
    }
}
