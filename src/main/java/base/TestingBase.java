package base;

import cucumber.api.Scenario;
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

    protected void setupEnvironment() {
        log.debug(getClass().getName() + " -> Starting tests...");

        Driver.initializeDriver();
        Driver.setImplicitWait(10);

        if (!UtilitiesFunctions.isCircleCI()) {
            Driver.maximize();
        }

        // Add cookie to bypass rack password
        Properties props = UtilitiesFunctions.loadProperties();

        String baseUrl = props.getProperty("BASE_URL").toLowerCase();
        String cookieName = props.getProperty("COOKIE_NAME");
        String cookieValue = props.getProperty("COOKIE_VALUE");
        Cookie ck = new Cookie(cookieName, cookieValue);
        Driver.getDriver().navigate().to(baseUrl);
        Driver.getDriver().manage().addCookie(ck);
    }

    protected void tearDown() {
        Driver.quit();
        log.debug(getClass().getName() + " -> Ending tests...");
    }

    protected void takeScreenshot(Scenario scenario) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss");
        LocalDateTime currentTime = LocalDateTime.now();
        String timeToPrint = dateTimeFormatter.format(currentTime);

        try {
            File screenSource = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.FILE);
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
