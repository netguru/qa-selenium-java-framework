package pages;

import base.BasePage;
import ngelements.NGButton;
import ngelements.NGSelect;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.TextInput;
import sections.MainHeaderSection;

public final class DashboardPage extends BasePage {

    @Name("search input")
    @FindBy(id = "q_place")
    private TextInput searchInput;

    @Name("search button")
    @FindBy(xpath = "//div[@class='col-xs-24 col-sm-6']")
    private NGButton searchButton;

    @Name("distance dropdown")
    @FindBy(id = "q_distance")
    private NGSelect distanceDropdown;

    private MainHeaderSection mainHeaderSection;

    public DashboardPage(WebDriver driver) {
        super(driver);
        relativeUrl = "dashboard";

        mainHeaderSection = new MainHeaderSection(driver);
    }

    @Override
    public boolean isInitialized() {
        return searchInput.isDisplayed();
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    /**
     * @param location - City or postal code
     */
    public void setLocation(String location) {
        searchInput.clear();
        searchInput.sendKeys(location);
    }

    /**
     * Select a distance from drop-down menu.
     *
     * @param distance - 1, 5, 10, 20, 50 or 100 [km]
     */
    public void setDistance(String distance) {
        distanceDropdown.selectByValue(distance);
    }

    public MainHeaderSection getMainHeaderSection() {
        return mainHeaderSection;
    }
}