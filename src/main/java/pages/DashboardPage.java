package pages;

import base.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import sections.MainHeaderSection;

public final class DashboardPage extends PageBase {

    @FindBy(id = "q_place")
    private WebElement searchInput;

    private MainHeaderSection mainHeaderSection;

    public DashboardPage() {
        relativeUrl = "dashboard";

        mainHeaderSection = new MainHeaderSection();
    }

    @Override
    public boolean isInitialized() {
        return searchInput.isDisplayed();
    }

    public MainHeaderSection getMainHeaderSection() {
        return mainHeaderSection;
    }
}
