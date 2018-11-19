package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class PropertyLoader {

    private static Properties properties = new Properties();

    public static String loadValueByPropertyName(String propertyName) throws IOException {
        properties.load(getPropertyFile());
        return properties.getProperty(propertyName);
    }

    private static InputStream getPropertyFile() {
        return Objects.requireNonNull(PropertyLoader.class
                .getClassLoader()
                .getResourceAsStream("browser.properties"));
    }
}
