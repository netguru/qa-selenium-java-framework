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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ZAPManager {
    private static final Logger log = LogManager.getLogger(Logger.class.getName());
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

    public void spiderScan(String targetUrl) {
        try {
            log.info("Stared spider scan for " + targetUrl + " please wait");
            ApiResponse resp = clientApi.spider.scan(targetUrl, null, null, null, null);

            // The scan now returns a scan id to support concurrent scanning
            String scanid = ((ApiResponseElement) resp).getValue();
            int progress;

            // Poll the status until it completes
            while (true) {
                Thread.sleep(1000);
                progress = Integer.parseInt(((ApiResponseElement) clientApi.spider.status(scanid)).getValue());
                if (progress >= 100) {
                    break;
                }
            }

            // Give the passive scanner a chance to complete
            Thread.sleep(2000);
            log.info("Spider scan for " + targetUrl + " completed");
            saveHTMLReport(new String(clientApi.core.htmlreport(), StandardCharsets.UTF_8));
        } catch (ClientApiException | InterruptedException e) {
            log.error(e);
        }
    }

    private String getCurrentTime() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss");
        LocalDateTime currentTime = LocalDateTime.now();
        return dateTimeFormatter.format(currentTime);
    }

    private String getReportDirectory() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.startsWith("Windows")) {
            return "reports\\";
        } else {
            return "reports/";
        }
    }

    private void saveHTMLReport(String result) {
        String timeToPrint = getCurrentTime();
        File target = new File(getReportDirectory() + "owasp_" + timeToPrint + ".html");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(target));
            writer.write(result);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
