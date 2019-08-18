package managers;

import base.BasePage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.HomePage;

import java.lang.reflect.Method;

public class PageObjectManager {
    private WebDriver driver;
    private HomePage homePage;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage getHomePage() {
        return (homePage == null) ? homePage = new HomePage(this.driver) : homePage;
    }

    public BasePage getPageByName(String pageName) {
        pageName = pageName.replace(" ", "");

        try {
            Method method = PageObjectManager.class.getMethod("get" + pageName + "Page");
            return (BasePage) method.invoke(this);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
            return null;
        }
    }
}
