package managers;

import base.BasePage;
import exceptions.PageNotFoundException;
import org.openqa.selenium.WebDriver;
import pages.HomePage;

public class PageObjectManager {
    private WebDriver driver;
    private HomePage homePage;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage getHomePage() {
        return (homePage == null) ? homePage = new HomePage(this.driver) : homePage;
    }

    public BasePage getPageByName(String pageName) throws PageNotFoundException {
        BasePage page = null;

        switch (pageName.toLowerCase()) {
            case "home page":
                page = getHomePage();
                break;
            default:
                throw new PageNotFoundException("There is no such page: " + pageName);
        }

        return page;
    }
}
