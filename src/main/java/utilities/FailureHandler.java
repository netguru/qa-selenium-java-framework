package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.monte.media.Format;
import org.monte.media.math.Rational;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import ws.schild.jave.Encoder;
import ws.schild.jave.EncoderException;
import ws.schild.jave.EncodingAttributes;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.VideoAttributes;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

import static org.monte.media.FormatKeys.EncodingKey;
import static org.monte.media.FormatKeys.FrameRateKey;
import static org.monte.media.FormatKeys.KeyFrameIntervalKey;
import static org.monte.media.FormatKeys.MIME_AVI;
import static org.monte.media.FormatKeys.MediaType;
import static org.monte.media.FormatKeys.MediaTypeKey;
import static org.monte.media.FormatKeys.MimeTypeKey;
import static org.monte.media.VideoFormatKeys.CompressorNameKey;
import static org.monte.media.VideoFormatKeys.DepthKey;
import static org.monte.media.VideoFormatKeys.ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE;
import static org.monte.media.VideoFormatKeys.QualityKey;

public class FailureHandler {

    private static final Logger log = LogManager.getLogger(Logger.class.getName());
    private final static String recordingVideoName = "Recorded";
    private static String videosDir = "videos/";
    private final static String pageSourcesDir = "pageSources/";
    private final static String screenshotsDir = "screenshots/";
    private final static String logsDir = "logs/";
    private SpecializedScreenRecorder screenRecorder;
    private WebDriver driver;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH.mm.ss");
    private Boolean isWindows = false;

    public FailureHandler(WebDriver driver) {
        this.driver = driver;
        String os = System.getProperty("os.name").toLowerCase();
        if (os.startsWith("Windows")) {
            isWindows = true;
        }
    }

    private String getPath(String text) {
        if (isWindows) {
            return text.replace("/", "\\");
        }
        return text;
    }

    public void takePageSource(String name) {
        String pageSource = driver.getPageSource();
        String timeToPrint = getCurrentTime();

        Path path = Paths.get(String.format("%s%s_%s.html", getPath(pageSourcesDir), name, timeToPrint));

        try {
            Files.createFile(path);
            Files.write(path, pageSource.getBytes());
            log.info("Saving page source");
        } catch (IOException e) {
            log.error("Failed to save page source");
            e.printStackTrace();
        }

    }

    public void takeScreenshot(String name) {
        String timeToPrint = getCurrentTime();

        try {
            File screenSource = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileHandler.copy(
                    screenSource,
                    new File(String.format("%s%s_%s.png", getPath(screenshotsDir), name, timeToPrint))
            );
            log.info("Taking the screenshot");
        } catch (Exception e) {
            log.error("Failed to take screenshot");
            e.printStackTrace();
        }
    }

    public void takeBrowserLogs(String name) {
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        String timeToPrint = getCurrentTime();

        Path path = Paths.get(String.format("%s%s_%s_browser.log", getPath(logsDir), name, timeToPrint));

        try {
            Files.createFile(path);
            for (LogEntry entry : logEntries) {
                LocalDateTime triggerTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(entry.getTimestamp()),
                        TimeZone.getDefault().toZoneId());
                String message = String.format("%s\t%s\t%s\n", dateTimeFormatter.format(triggerTime), entry.getLevel(), entry.getMessage());
                Files.write(path, message.getBytes());
            }
            log.info("Saving browser logs");
        } catch (IOException e) {
            log.error("Failed to save browser logs");
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
                    null, new File(getPath(videosDir)), recordingVideoName);
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

    public void stopVideoRecord() {
        try {
            screenRecorder.stop();
        } catch (IOException e) {
            log.error("Failed to stop video recording.");
            log.error(e.getStackTrace());
        }
    }

    public void removeVideo() {
        removeVideo(new File(String.format("%s%s.avi", getPath(videosDir), recordingVideoName)));
    }

    public void encodeVideoToFlv(String name) {
        String timeToPrint = getCurrentTime();

        File oldFile = new File(String.format("%s%s.avi", getPath(videosDir), recordingVideoName));
        File target = new File(String.format("%s%s_%s.mp4", getPath(videosDir), name, timeToPrint));

        try {
            target.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        VideoAttributes video = new VideoAttributes();
        video.setCodec("mpeg4");
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("mp4");
        attrs.setVideoAttributes(video);
        Encoder encoder = new Encoder();
        try {
            log.info(String.format("Trying to encode %s to %s", oldFile.getPath(), target.toPath()));
            encoder.encode(new MultimediaObject(oldFile), target, attrs);
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
        return dateTimeFormatter.format(LocalDateTime.now());
    }
}
