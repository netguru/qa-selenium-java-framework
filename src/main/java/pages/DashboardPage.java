package pages;

import base.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import sections.MainHeaderSection;

public class DashboardPage extends PageBase {

    private final MainHeaderSection mainHeaderSection;
    @FindBy(id = "q_place")
    private WebElement searchInput;
    @FindBy(xpath = "//div[@class='col-xs-24 col-sm-6']")
    private WebElement searchButton;
    @FindBy(id="q_distance")
    private WebElement distanceDropdown;

    public DashboardPage(WebDriver driver) {
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

    public void clickSearchButton(){
        searchButton.click();
    }

    /**
     *
     * @param location - City or postal code
     */
    public void setLocation(String location){
        searchInput.clear();
        searchInput.sendKeys(location);
    }

    /**
     * Select a distance from drop-down menu.
     * @param distance - 1, 5, 10, 20, 50 or 100 [km]
     */
    public void setDistance(String distance) {
        new Select(distanceDropdown).selectByValue(distance);
    }
}
