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

    Properties loadProperties() {
        if (isCircleCI()) {
            return loadSystemProperties();
        } else {
            return loadFromFile();
        }
    }

    public boolean isCircleCI() {
        return Boolean.parseBoolean(System.getProperty("CIRCLECI"));
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

    public String getLanguage() {
        return properties.getProperty("LANGUAGE");
    }

    public String getCookieName() {
        return properties.getProperty("COOKIE_NAME");
    }

    public String getCookieValue() {
        return properties.getProperty("COOKIE_VALUE");
    }

    public String getBrowserType() {
        return properties.getProperty("BROWSER");
    }

    public String getCommonPassword() {
        return properties.getProperty("COMMON_PASSWORD");
    }

    public int getImplicitWait() {
        return Integer.parseInt(properties.getProperty("IMPLICIT_WAIT"));
    }

    public String getEmailByUserType(UserType userType) {
        String email = null;
        switch (userType) {
            case ADMIN:
                email = getAdminEmail();
                break;
            case PROVIDER:
                email = getProviderEmail();
                break;
            case CONSUMER_PAID:
                email = getConsumerPaidEmail();
                break;
            case CONSUMER_UNPAID:
                email = getConsumerUnpaidEmail();
                break;
            case CONSUMER_SPECIAL:
                email = getConsumerSpecialEmail();
                break;
            default:
                log.error("Wrong UserType. Accepted values are: ADMIN, PROVIDER, CONSUMER_PAID" +
                        "CONSUMER_UNPAID, CONSUMER_SPECIAL");
                break;
        }
        return email;
    }

    private String getAdminEmail() {
        return properties.getProperty("ADMIN_EMAIL");
    }

    private String getProviderEmail() {
        return properties.getProperty("PROVIDER_EMAIL");
    }

    private String getConsumerPaidEmail() {
        return properties.getProperty("PAID_CONSUMER_EMAIL");
    }

    private String getConsumerUnpaidEmail() {
        return properties.getProperty("UNPAID_CONSUMER_EMAIL");
    }

    private String getConsumerSpecialEmail() {
        return properties.getProperty("SPECIAL_CONSUMER_EMAIL");
    }
}
