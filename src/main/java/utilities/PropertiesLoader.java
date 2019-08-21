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
            log.error(String.format("Failed to load `%s` file", propertiesPath));
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

    public int getImplicitWait() {
        return Integer.parseInt(properties.getProperty("IMPLICIT_WAIT"));
    }

    public String getZAPAddress() {
        return properties.getProperty("ZAP_ADDRESS");
    }

    public String getZAPPort() {
        return properties.getProperty("ZAP_PORT");
    }

    public String getZAPApiKey() {
        return properties.getProperty("ZAP_API_KEY");
    }

    public String getStagingPassword() {
        return properties.getProperty("STAGING_PASSWORD");
    }

}
