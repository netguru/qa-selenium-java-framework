package utilities;

import java.util.Properties;

public class PropertiesLoader {
    private Properties properties;

    public PropertiesLoader() {
        String propertiesPath = "initConfig.properties";
        properties = UtilitiesFunctions.loadFile(propertiesPath);
    }

    public Properties getProperties() {
        return properties;
    }

    public String getBaseUrl() {
        return properties.getProperty("base_url");
    }

    public String getLanguage() {
        return properties.getProperty("language");
    }

    public String getCookieName() {
        return properties.getProperty("cookie_name");
    }

    public String getCookieValue() {
        return properties.getProperty("cookie_value");
    }

    public String getBrowserType() {
        return properties.getProperty("browser");
    }
}
