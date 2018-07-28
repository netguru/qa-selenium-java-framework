package pages;

import base.BasePage;
import ngelements.NGTextBlock;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public final class SearchResultsPage extends BasePage {

    @FindBy(xpath = "//span[@class='slider-form__description']")
    private NGTextBlock distanceText;

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

    public String getMaxDistance() {
        return distanceText.getText();
    }
}
