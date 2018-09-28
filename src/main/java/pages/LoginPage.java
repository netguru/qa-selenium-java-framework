package pages;

import base.BasePage;
import ngelements.NGButton;
import ngelements.NGTextInput;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

  @FindBy(id = "user_login")
  private NGTextInput emailInput;

  @FindBy(id="user_password")
  private NGTextInput passwordInput;

  @FindBy(id = "login-btn")
  private NGButton loginButton;

  public LoginPage(WebDriver driver) {
    super(driver);
    relativeUrl = "/en/sign_in";
  }

  @Override
  public boolean isInitialized() {
    return emailInput.isDisplayed();
  }

  public void provideEmail(String email){
    emailInput.sendKeys(email);
  }

  public void providePassword(String password){
    passwordInput.sendVulnerableData(password);
  }

  public void clickLoginButton(){
    loginButton.click();
  }

  public String getProvider() {
    return propertiesLoader.getProvider();
  }

  public String getPassword() {
    return propertiesLoader.getPassword();
  }


}
