package pages;

import base.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultsPage extends PageBase {

    @FindBy(xpath = "//span[@class='slider-form__description']")
    private WebElement distanceText;

    @FindBy(xpath = "//div[@class='slider-form-group clearfix']")
    private WebElement distanceSlider;

    @FindBy(xpath = "//div[@class='row redesign-search-results-row']")
    private List<WebElement> searchResults;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isInitialized() {
        return distanceSlider.isDisplayed();
    }

    public void openFirstSearchResult() {
        searchResults.get(0).click();
    }

    public String getMaxDistance(){
        return distanceText.getText();
    }
}
