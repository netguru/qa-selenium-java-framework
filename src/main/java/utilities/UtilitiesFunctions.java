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

    public static Properties loadProperties() {
        if (isCircleCI()) {
            return loadSystemProperties();
        } else {
            return loadFromFile();
        }
    }

    public static boolean isCircleCI() {
        return Boolean.parseBoolean(System.getProperty("CIRCLECI"));
    }

    private static Properties loadSystemProperties() {
        Properties properties = new Properties();
        for (EnvironmentVariables variable : EnvironmentVariables.values()) {
            if (System.getProperty(variable.name()) != null) {
                properties.put(variable.name(), System.getProperty(variable.name()));
            }
        }
        return properties;
    }

    private static Properties loadFromFile() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("initConfig.properties"));
        } catch (IOException e) {
            log.warn("Failed to load 'initConfig.properties' file.");
        }
        return properties;
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
