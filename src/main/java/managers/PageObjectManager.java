package managers;

import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.StagingPage;

public class PageObjectManager {
    private WebDriver driver;
    private HomePage homePage;
    private StagingPage stagingPage;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage getHomePage() {
        return (homePage == null) ? homePage = new HomePage(this.driver) : homePage;
    }

    public StagingPage getStagingPage() {
        return (stagingPage == null) ? stagingPage = new StagingPage(this.driver) : stagingPage;
    }


}
