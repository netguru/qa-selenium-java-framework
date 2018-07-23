package base;

import cucumber.api.Scenario;
import managers.Context;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import utilities.UtilitiesFunctions;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public abstract class TestingBase {

    private static final Logger log = LogManager.getLogger(Logger.class.getName());
    protected static Context context;

    protected void setupEnvironment() {
        log.debug(getClass().getName() + " -> Starting tests...");
        TestingBase.context = new Context();

        context.driver.maximize();
        context.driver.setImplicitWait(10);

        // Add cookie to bypass rack password
        Properties props = UtilitiesFunctions.loadFile("initConfig.properties");

        String baseUrl = props.getProperty("base_url").toLowerCase();
        String cookieName = props.getProperty("cookie_name");
        String cookieValue = props.getProperty("cookie_value");
        Cookie ck = new Cookie(cookieName, cookieValue);
        context.driver.getDriver().navigate().to(baseUrl);
        context.driver.getDriver().manage().addCookie(ck);
    }

    protected void tearDown() {
        context.driver.quit();
        log.debug(getClass().getName() + " -> Ending tests...");
    }

    protected void takeScreenshot(Scenario scenario) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss");
        LocalDateTime currentTime = LocalDateTime.now();
        String timeToPrint = dateTimeFormatter.format(currentTime);

        try {
            File screenSource = ((TakesScreenshot) context.driver.getDriver()).getScreenshotAs(OutputType.FILE);
            FileHandler.copy(
                    screenSource,
                    new File("screenshots/" + scenario.getName() + "_" + timeToPrint + "_failScreen.png")
            );

        } catch (Exception e) {
            log.error("Failed to take screenshot on test fail");
            e.printStackTrace();
        }
    }
}
