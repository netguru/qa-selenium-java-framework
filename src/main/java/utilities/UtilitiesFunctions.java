package utilities;

import cucumber.api.Scenario;
import managers.Context;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class UtilitiesFunctions {
    private static final Logger log = LogManager.getLogger(Logger.class.getName());



    public static void takePageSource(Scenario scenario) {
        String pageSource = Context.driverManager.getDriver().getPageSource();

        String timeToPrint = getCurrentTime();

        Path path = Paths.get("pageSources/" + scenario.getName() + "_" + timeToPrint + ".html");

        try {
            Files.createFile(path);
            Files.write(path, pageSource.getBytes());
            scenario.embed(pageSource.getBytes(), "text/html");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void takeScreenshot(Scenario scenario) {
        String timeToPrint = getCurrentTime();

        try {
            File screenSource = ((TakesScreenshot) Context.driverManager.getDriver()).getScreenshotAs(OutputType.FILE);
            FileHandler.copy(
                    screenSource,
                    new File("screenshots/" + scenario.getName() + "_" + timeToPrint + ".png")
            );
            byte[] screenSource2 = ((TakesScreenshot) Context.driverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenSource2, "image/png");
        } catch (Exception e) {
            log.error("Failed to take screenshot");
            e.printStackTrace();
        }
    }

    private static String getCurrentTime() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss");
        LocalDateTime currentTime = LocalDateTime.now();
        return dateTimeFormatter.format(currentTime);
    }
}
