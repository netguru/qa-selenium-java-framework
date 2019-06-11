package managers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.zaproxy.clientapi.core.ApiResponse;
import org.zaproxy.clientapi.core.ApiResponseElement;
import org.zaproxy.clientapi.core.ClientApi;
import org.zaproxy.clientapi.core.ClientApiException;
import utilities.PropertiesLoader;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ZAPManager {
    private static final Logger log = LogManager.getLogger(ZAPManager.class.getName());
    private PropertiesLoader propertiesLoader = new PropertiesLoader();
    private ClientApi clientApi;

    public ZAPManager() {
        String zapAddress = propertiesLoader.getZAPAddress();
        String zapPort = propertiesLoader.getZAPPort();
        String zapApiKey = propertiesLoader.getZAPApiKey();
        if (zapAddress == null | zapPort == null | zapApiKey == null) {
            return;
        }
        this.clientApi = new ClientApi(zapAddress,
                Integer.parseInt(zapPort), zapApiKey);
        log.info("Proxy client Api created");
    }

    public ClientApi getClientApi() {
        return clientApi;
    }

    public void spiderScan(String targetUrl, int maxDepth) {
        try {
            //set it to prevent freezing scanner
            clientApi.spider.setOptionMaxDepth(maxDepth);
            log.info("Start scanning");
            ApiResponse resp = clientApi.spider.scan(targetUrl, null, null, null, null);

            // The scan now returns a scan id to support concurrent scanning
            String scanId = ((ApiResponseElement) resp).getValue();
            int progress = 0;
            int info = 0;

            // Poll the status until it completes
            while (progress < 100) {
                Thread.sleep(5000);
                progress = Integer.parseInt(((ApiResponseElement) clientApi.spider.status(scanId)).getValue());
                if (progress % 10 == 0 && info != progress) {
                    log.info("Current progress: " + progress);
                    info = progress;
                }
            }

            // Give the passive scanner a chance to complete
            Thread.sleep(2000);
            log.info("Spider scan for " + targetUrl + " completed");
            saveHTMLReport(new String(clientApi.core.htmlreport(), StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.error(e);
        }
    }

    private String getCurrentTime() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH.mm.ss");
        LocalDateTime currentTime = LocalDateTime.now();
        return dateTimeFormatter.format(currentTime);
    }

    private String getReportDirectory() {
        String currentPath = System.getProperty("reportDirectory");
        return Paths.get(currentPath, "reports").toString();
    }

    private void saveHTMLReport(String result) {
        String timeToPrint = getCurrentTime();
        File reportDirectory = new File(getReportDirectory());
        reportDirectory.mkdir();
        File target = new File(reportDirectory, String.format("owasp_%s.html", timeToPrint));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            writer.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
