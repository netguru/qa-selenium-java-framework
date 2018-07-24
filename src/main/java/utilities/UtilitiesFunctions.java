package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
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

        for (String key : new String[]{
                "BROWSER"
                , "BASE_URL"
                , "LANGUAGE"
                , "COOKIE_NAME"
                , "COOKIE_VALUE"
                , "ADMIN_EMAIL"
                , "PROVIDER_EMAIL"
                , "PAID_CONSUMER_EMAIL"
                , "UNPAID_CONSUMER_EMAIL"
                , "SPECIAL_CONSUMER_EMAIL"
                , "COMMON_PASSWORD"}) {
            if (System.getProperty(key) != null) {
                props.put(key, System.getProperty(key));
                log.info("property key: " + key + " property value: " + System.getProperty(key));
            }
        }

        return props;
    }

    public static boolean isCircleCI() {
        boolean isCircleCI = false;
        if ("true".equals(System.getProperty("CIRCLECI").toLowerCase()))
            isCircleCI = true;
        return isCircleCI;
    }
}
