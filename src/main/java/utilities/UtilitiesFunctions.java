package utilities;

import cucumber.api.Scenario;
import managers.Context;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class UtilitiesFunctions {
    private static final Logger log = LogManager.getLogger(Logger.class.getName());
    private static PropertiesLoader propertiesLoader = new PropertiesLoader();

    public static void takeScreenshot(Scenario scenario) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss");
        LocalDateTime currentTime = LocalDateTime.now();
        String timeToPrint = dateTimeFormatter.format(currentTime);

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

    public static String getUserEmail(UserType userType) {
        String email = "";

        switch (userType) {
            case ADMIN:
                email = propertiesLoader.getAdminEmail();
                break;
            case BO:
                email = propertiesLoader.getBOEmail();
                break;
            case FD:
                email = propertiesLoader.getFDEmail();
                break;
            case BASIC:
                email = propertiesLoader.getBasicUserEmail();
                break;
            case BO_NO_RESTAURANTS:
                email = propertiesLoader.getBONoRestaurantsEmail();
                break;
            case FD_NO_RESTAURANTS_AND_REVIEWS:
                email = propertiesLoader.getFDNoRestaurantsAndReviewsEmail();
                break;
            case BASIC_WITH_AVATAR:
                email = propertiesLoader.getBasicWithAvatarEmail();
                break;
        }

        return email;
    }
}
