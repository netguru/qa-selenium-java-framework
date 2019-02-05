package pages;

import base.BasePage;
import ngelements.NGButton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

  @FindBy(xpath = "//a[@href='https://www.netguru.com']")
  private NGButton logo;

  public HomePage(WebDriver driver) {
    super(driver);
  }

  @Override
  public boolean isInitialized() {
    return false;
  }

  public NGButton getLogo() {
    return logo;
  }
}