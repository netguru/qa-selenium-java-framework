package utilities;

import cucumber.api.Scenario;
import managers.Context;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public final class UtilitiesFunctions {
    private static final Logger log = LogManager.getLogger(Logger.class.getName());

    public static Properties loadFile(String fileName) {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(fileName));
        } catch (IOException e) {
            log.warn("Failed to load " + fileName + " file.");
        }

        return props;
    }

    public static void takeScreenshot(Scenario scenario) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss");
        LocalDateTime currentTime = LocalDateTime.now();
        String timeToPrint = dateTimeFormatter.format(currentTime);

        try {
            File screenSource = ((TakesScreenshot) Context.getDriverManager().getDriver()).getScreenshotAs(OutputType.FILE);
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
