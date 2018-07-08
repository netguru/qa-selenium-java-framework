package base;

import cucumber.api.Scenario;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public abstract class TestBase {

    private static final Logger log = LogManager.getLogger(Logger.class.getName());

    protected void setupEnvironment() {
        log.debug(getClass().getName() + " -> Starting tests...");

        Driver.initializeDriver();
        Driver.maximize();
        Driver.setImplicitWait(10);

        // Add cookie to bypass rack password
        Properties props = new Properties();
        try {
            props.load( new FileInputStream("initConfig.properties") );
        } catch (IOException e) {
            log.warn("Failed to load initConfig.properties file.");
        }

        String baseUrl = props.getProperty("base_url").toLowerCase();
        String cookieName = props.getProperty("cookie_name");
        String cookieValue = props.getProperty("cookie_value");
        Cookie ck = new Cookie(cookieName, cookieValue);
        Driver.getDriver().navigate().to(baseUrl);
        Driver.getDriver().manage().addCookie(ck);
    }

    protected void tearDown() {
        Driver.quit();
        log.debug(getClass().getName() + " -> Ending tests...");
    }

    protected void takeScreenshot(Scenario scenario) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
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
