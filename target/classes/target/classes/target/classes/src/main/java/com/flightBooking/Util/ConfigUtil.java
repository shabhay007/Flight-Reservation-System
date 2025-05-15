
package com.flightBooking.Util;

import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {
    private static Properties properties = new Properties();

    static {
        try (InputStream input = ConfigUtil.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input != null) {
                properties.load(input);
            }
        } catch (Exception e) {
            System.err.println("Warning: Failed to load config.properties");
        }
    }

    public static String get(String key) {
        // Try in order: config.properties → System properties → Environment variables
        String value = properties.getProperty(key);
        
        if (value == null) {
            value = System.getProperty(key);
        }
        if (value == null) {
            value = System.getenv(key);
        }
        if (value == null) {
            throw new RuntimeException("Missing configuration for key: " + key);
        }
        return value;
    }
}

