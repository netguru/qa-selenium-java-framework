package pages;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class TutorProfilePage extends BasePage {

    @FindBy(xpath = "//div[@class='profile__featured-values-holder clearfix']")
    private WebElement tutorBasicInfo;

    @Override
    public boolean isInitialized() {
        return tutorBasicInfo.isDisplayed();
    }
}
