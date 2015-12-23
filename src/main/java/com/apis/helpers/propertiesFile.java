package com.apis.helpers;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class propertiesFile {
    public static String getTwitterSetting(String propertyName) throws IOException {
        Properties prop = new Properties();
        InputStream input = null;
        String filename = "src/test/resources/twitter.properties";
        input = new FileInputStream(filename);
        if (input == null)
            return "";
        prop.load(input);
        return prop.getProperty(propertyName);
    }

    public static String getSpotifySetting(String propertyName) throws IOException {
        Properties prop = new Properties();
        InputStream input = null;
        String filename = "src/test/resources/spotify.properties";
        input = new FileInputStream(filename);
        if (input == null)
            return "";
        prop.load(input);
        return prop.getProperty(propertyName);
    }

    public static void setLastTweetId(String tweetId) throws ConfigurationException {
        PropertiesConfiguration config = new PropertiesConfiguration("src/test/resources/twitter.properties");
        config.setProperty("lastTweetId", tweetId);
        config.save();
    }

    public static void setBearerToken(String bearerToken) throws ConfigurationException {
        PropertiesConfiguration config = new PropertiesConfiguration("src/test/resources/twitter.properties");
        config.setProperty("oauth.bearerToken", bearerToken);
        config.save();
    }
}
