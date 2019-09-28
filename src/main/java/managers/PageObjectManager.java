package managers;

import base.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.HomePage;

import java.lang.reflect.Method;

public class PageObjectManager {

    protected static final Logger log = LogManager.getLogger(Logger.class.getName());

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
        String methodName = "get" + pageName + "Page";

        BasePage page = null;

        try {
            Method method = PageObjectManager.class.getMethod(methodName);
            page = (BasePage) method.invoke(this);
        } catch (Exception e) {
            log.error("There is no such method: " + methodName);
            Assert.fail(e.getMessage());
        }

        return page;
    }
}
