package utilities;

import java.util.Properties;

public class PropertiesLoader {
    private Properties properties;

    public void loadProperties(String filename) {
        properties = UtilitiesFunctions.loadFile(filename);
    }

    public Properties getProperties() {
        return properties;
    }
}
