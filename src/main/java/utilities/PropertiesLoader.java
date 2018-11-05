package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {
    private static final Logger log = LogManager.getLogger(Logger.class.getName());
    private Properties properties;
    private String propertiesPath;

    public PropertiesLoader() {
        propertiesPath = "initConfig.properties";
        loadProperties();
    }

    private Properties loadProperties() {
        return (isCI()) ? loadSystemProperties() : loadFromFile();
    }

    public boolean isCI() {
        return Boolean.parseBoolean(System.getProperty("CI"));
    }

    private Properties loadSystemProperties() {
        properties = new Properties();
        for (EnvironmentVariables variable : EnvironmentVariables.values()) {
            if (System.getProperty(variable.name()) != null) {
                properties.put(variable.name(), System.getProperty(variable.name()));
            }
        }
        return properties;
    }

    private Properties loadFromFile() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(propertiesPath));
        } catch (IOException e) {
            log.warn("Failed to load '" + propertiesPath + "' file.");
        }
        return properties;
    }

    public Properties getProperties() {
        return properties;
    }

    public String getBaseUrl() {
        return properties.getProperty("BASE_URL");
    }

    public String getBrowserType() {
        return properties.getProperty("BROWSER");
    }

    public String getLanguage() {
        return properties.getProperty("LANGUAGE");
    }

    public int getImplicitWait() {
        return Integer.parseInt(properties.getProperty("IMPLICIT_WAIT"));
    }

    public String getAdminEmail() {
        return properties.getProperty("ADMIN_EMAIL");
    }

    public String getBOEmail() {
        return properties.getProperty("BO_EMAIL");
    }

    public String getFDEmail() {
        return properties.getProperty("FD_EMAIL");
    }

    public String getBasicUserEmail() {
        return properties.getProperty("BASIC_USER_EMAIL");
    }

    public String getBONoRestaurantsEmail() {
        return properties.getProperty("BO_NO_RESTAURANTS_EMAIL");
    }

    public String getFDNoRestaurantsAndReviewsEmail() {
        return properties.getProperty("FD_NO_RESTAURANTS_AND_REVIEWS_EMAIL");
    }

    public String getCommonPassword() {
        return properties.getProperty("COMMON_PASSWORD");
    }
}
