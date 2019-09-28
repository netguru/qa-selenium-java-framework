package utilities;

import managers.Context;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Set;

public class UtilitiesFunctions {

    private static final Logger log = LogManager.getLogger(Logger.class.getName());

    public static WebElement getWebElementBy(By by) {
        return Context.driverManager.getDriver().findElement(by);
    }

    public static void switchToOtherTab() {
        WebDriver driver = Context.driverManager.getDriver();

        String currentTab = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        if (handles.size() < 2) {
            log.error("There is only one tab opened");
        }

        for (String tab : handles) {
            if (!tab.equals(currentTab)) {
                driver.switchTo().window(tab);
            }
        }
    }

}
