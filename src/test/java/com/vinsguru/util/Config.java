package com.vinsguru.util;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.ObjectInputFilter;
import java.util.Properties;

public class Config {
    private static final Logger log = LoggerFactory.getLogger(Config.class);
    private static final String DEFAULT_PROPERTIES = "config/default.properties";
    private static Properties properties;
    public static void initialized() {

        //load default properties:
        properties = loadProperties();

        //check for any override
        for (String key : properties.stringPropertyNames()) {
            if (System.getProperties().containsKey(key)) {
                properties.setProperty(key, System.getProperty(key));
            }
        }

        //print for debugging needs
        log.info("Test properties");
        log.info("---------------");
        for (String key : properties.stringPropertyNames()) {
            log.info("{}={}",key, properties.getProperty(key));
        }
        log.info("---------------");
    }

    public static String get(String key){
        return properties.getProperty(key);
    }


    public static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream stream = ResourceLoader.getResource(DEFAULT_PROPERTIES)) {
            properties.load(stream);
        } catch (Exception e) { log.error("unable to read the property file {}",DEFAULT_PROPERTIES,e); }
        return properties;
    }


}
