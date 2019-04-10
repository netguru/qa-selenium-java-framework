package utilities;

import cucumber.api.Scenario;
import managers.Context;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.monte.media.Format;
import org.monte.media.math.Rational;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import ws.schild.jave.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.monte.media.FormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

public class FailureHandler {

    private static final Logger log = LogManager.getLogger(Logger.class.getName());
    private final static String recordingVideoName = "Recorded";
    private final static String videosDir = "videos/";
    private final static String pageSourcesDir = "pageSources/";
    private final static String screenshotsDir = "screenshots/";
    private static SpecializedScreenRecorder screenRecorder;

    public void takePageSource(Scenario scenario) {
        String pageSource = Context.driverManager.getDriver().getPageSource();

        String timeToPrint = getCurrentTime();

        Path path = Paths.get(pageSourcesDir + scenario.getName() + "_" + timeToPrint + ".html");

        try {
            Files.createFile(path);
            Files.write(path, pageSource.getBytes());
            scenario.embed(pageSource.getBytes(), "text/html");
        } catch (IOException e) {
            log.error("Failed to take screenshot");
            e.printStackTrace();
        }

    }

    public void takeScreenshot(Scenario scenario) {
        String timeToPrint = getCurrentTime();

        try {
            File screenSource = ((TakesScreenshot) Context.driverManager.getDriver()).getScreenshotAs(OutputType.FILE);
            FileHandler.copy(
                    screenSource,
                    new File(screenshotsDir + scenario.getName() + "_" + timeToPrint + ".png")
            );
            byte[] screenSource2 = ((TakesScreenshot) Context.driverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenSource2, "image/png");
        } catch (Exception e) {
            log.error("Failed to take screenshot");
            e.printStackTrace();
        }
    }

    public void setUpScreenRecorder() {
        GraphicsConfiguration gc = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        Rectangle captureSize = new Rectangle(0, 0, width, height);

        String os = System.getProperty("os.name").toLowerCase();
        String videosDir = null;
        if (os.startsWith("Windows")) {
            videosDir = "videos\\";
        } else {
            videosDir = "videos/";
        }


        try {
            screenRecorder = new SpecializedScreenRecorder(gc, captureSize,
                    new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                    new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                            CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                            DepthKey, 24, FrameRateKey, Rational.valueOf(15),
                            QualityKey, 1.0f,
                            KeyFrameIntervalKey, 15 * 60),
                    new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black",
                            FrameRateKey, Rational.valueOf(30)),
                    null, new File(videosDir), recordingVideoName);
        } catch (IOException e) {
            log.info("Failed to initialize video recording");
            e.printStackTrace();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void startVideoRecord() {
        try {
            screenRecorder.start();
        } catch (IOException e) {
            log.info("Failed to start video recording");
            e.printStackTrace();
        }
    }

    public void stopVideoRecord(Scenario scenario) {
        try {
            screenRecorder.stop();
        } catch (IOException e) {
            log.info("Failed to stop video recording");
            e.printStackTrace();
        }
        if(scenario.isFailed()) {
            encodeVideoToFlv(scenario);
        }
        removeVideo(new File(videosDir + recordingVideoName + ".avi"));
    }

    private void encodeVideoToFlv(Scenario scenario) {
        String timeToPrint = getCurrentTime();

        File oldFile = new File(videosDir + recordingVideoName + ".avi");
        File target = new File(videosDir + scenario.getName() + "_" + timeToPrint + ".mp4");

        try {
            target.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        MultimediaObject source = new MultimediaObject(oldFile);

        VideoAttributes video = new VideoAttributes();
        video.setCodec("mpeg4");
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("mp4");
        attrs.setVideoAttributes(video);
        Encoder encoder = new Encoder();
        try {
            encoder.encode(source, target, attrs);
        } catch (EncoderException e) {
            log.info("File couldn't be encoded to mp4");
            e.printStackTrace();
        }
    }

    private void removeVideo(File file) {
        try {
            file.delete();
        } catch (Exception e) {
            log.info("Record file wasn't deleted");
            e.printStackTrace();
        }
    }

    private String getCurrentTime() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss");
        LocalDateTime currentTime = LocalDateTime.now();
        return dateTimeFormatter.format(currentTime);
    }
}
