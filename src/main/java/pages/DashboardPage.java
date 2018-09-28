package pages;

import base.BasePage;
import ngelements.NGButton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage {

  @FindBy(xpath = "//div[@class='btn-icon__wrap']")
  private NGButton searchButton;


  public DashboardPage(WebDriver driver) {
    super(driver);
  }

  @Override
  public boolean isInitialized() {
    return searchButton.isDisplayed();
  }
}
