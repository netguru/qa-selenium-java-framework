package pages;

import base.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.io.IOException;

public class HomePage extends PageBase {

    @FindBy(how = How.LINK_TEXT, using = "LOGIN")
    WebElement loginButton;

    public HomePage(WebDriver driver) throws IOException {
        super(driver);

        relativeUrl = ""; // It's supposed to be empty
    }

    @Override
    public boolean isInitialized() {
        return loginButton.isDisplayed() ? true : false;
    }
}
