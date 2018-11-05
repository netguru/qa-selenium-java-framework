package pages;

import base.BasePage;
import ngelements.NGTextInput;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

  @FindBy(xpath = "//*[contains(@class, 'styled__Container-ela1z7-0')]")
  private NGTextInput searchBar;

  public HomePage(WebDriver driver) {
    super(driver);
  }

  @Override
  public boolean isInitialized() {
    return searchBar.isDisplayed();
  }
}