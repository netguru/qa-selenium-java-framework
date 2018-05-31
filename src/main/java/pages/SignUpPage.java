package pages;

import base.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;

public class SignUpPage extends PageBase {

    @FindBy(id = "new_user")
    WebElement signUpButton;

    public SignUpPage(WebDriver driver) throws IOException {
        super(driver);

        relativeUrl = "register/new";
    }

    @Override
    public boolean isInitialized() {
        return signUpButton.isDisplayed() ? true : false;
    }
}
