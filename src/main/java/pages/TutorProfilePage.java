package pages;

import base.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TutorProfilePage extends PageBase {

    @FindBy(xpath = "//div[@class='profile__featured-values-holder clearfix']")
    private WebElement tutorBasicInfo;

    public TutorProfilePage() {
    }

    @Override
    public boolean isInitialized() {
        return tutorBasicInfo.isDisplayed();
    }
}
