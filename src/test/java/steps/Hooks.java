package steps;

import base.BaseTest;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import managers.Context;
import org.monte.media.Format;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import static org.monte.media.AudioFormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

import utilities.SpecializedScreenRecorder;
import utilities.UtilitiesFunctions;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Hooks extends BaseTest {

    private SpecializedScreenRecorder screenRecorder;

    @Before
    public void setupTestCase() throws IOException, AWTException {
        GraphicsConfiguration gc = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        Rectangle captureSize = new Rectangle(0,0, width, height);

        this.screenRecorder = new SpecializedScreenRecorder(gc, captureSize,
                new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        DepthKey, 24, FrameRateKey, Rational.valueOf(15),
                        QualityKey, 1.0f,
                        KeyFrameIntervalKey, 15 * 60),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black",
                        FrameRateKey, Rational.valueOf(30)),
                null, new File("videos/"), "Failed test");
        log.debug(getClass().getName() + " -> Starting tests...");
        BaseTest.context = new Context();
        BaseTest.pages = context.pages;
        screenRecorder.start();
        context.driverManager.initDriver();
    }

    @After
    public void ceaseTestCase(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            UtilitiesFunctions.takePageSource(scenario);
            UtilitiesFunctions.takeScreenshot(scenario);
            screenRecorder.stop();
            for(File movie : screenRecorder.getCreatedMovieFiles()){
                System.out.println("New movie created: " + movie.getAbsolutePath());
            }
        }
        context.driverManager.quit();
        log.debug(getClass().getName() + " -> Ending tests...");
    }

}
