package pages;

import base.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import sections.MainHeaderSection;

import java.io.IOException;

public class DashboardPage extends PageBase {

    @FindBy(id = "q_place")
    private WebElement searchInput;

    private MainHeaderSection mainHeaderSection;

    public DashboardPage(WebDriver driver) throws IOException {
        super(driver);

        relativeUrl = "dashboard";

        mainHeaderSection = new MainHeaderSection(this.driver);
    }

    @Override
    public boolean isInitialized() {
        return searchInput.isDisplayed();
    }

    public MainHeaderSection getMainHeaderSection() {
        return mainHeaderSection;
    }
}
