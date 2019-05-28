package managers;

import org.openqa.selenium.WebDriver;
import pages.*;

public class PageObjectManager {
    private WebDriver driver;
    private HomePage homePage;
    private ChallengingDomPage challengingDomPage;
    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage getHomePage () {
      return (homePage == null) ? homePage = new HomePage(this.driver) : homePage;
    }

    //add new field with new page type
    public ChallengingDomPage getChallengingDomPage() {
        return (challengingDomPage == null) ? challengingDomPage = new ChallengingDomPage(this.driver) : challengingDomPage;
    }

}
