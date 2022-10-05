package com.epam.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    private final Properties properties;

    private PropertiesReader(String resourceName) {
        try {
            properties = new Properties();
            properties.load(getInputStream(resourceName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private InputStream getInputStream(String fileName) {
        return PropertiesReader.class.getClassLoader().getResourceAsStream(fileName);
    }

    public static PropertiesReader getInstance() {
        return new PropertiesReader("config.properties");
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
