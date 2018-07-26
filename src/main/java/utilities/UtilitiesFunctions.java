package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
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
}
