package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties = new Properties();

    static {
        try (InputStream input = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("❌ config.properties not found in src/test/resources");
            } else {
                properties.load(input);
                System.out.println("✅ config.properties loaded");
            }
        } catch (IOException e) {
            System.out.println("❌ Error loading config.properties: " + e.getMessage());
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        String value = properties.getProperty(key);
        return (value != null) ? value : defaultValue;
    }
}
