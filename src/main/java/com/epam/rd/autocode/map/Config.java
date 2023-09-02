package com.epam.rd.autocode.map;

import java.io.*;
import java.util.Enumeration;
import java.util.Properties;

public class Config {
    Properties config;


    public Config() throws IOException {
        this.config = new Properties();
        reset();
    }


    public void reset() throws IOException {
        config.clear();
        try (InputStream input = new FileInputStream("config.properties")) {
            config.load(input);
            String defaultFilenames = config.getProperty("default.filenames");
            if (defaultFilenames != null) {
                String[] filenames = defaultFilenames.split(",");
                for (int i = filenames.length - 1; i >= 0; i--) {
                    String filename = filenames[1].trim() + ".properties";
                    try (InputStream reader = new FileInputStream(filename)) {
                        Properties defaultPops2 = new Properties();
                        defaultPops2.load(reader);

                    }
                }
            }
        }
    }

    public String get(String key) {
        return config.getProperty(key);
    }

    public void remove(String key) {
        String existingValue = config.getProperty(key);
        if (existingValue != null) {
            config.remove(key);
        }
    }

    public void save() {
        try (OutputStream output = new FileOutputStream("config.properties")) {
            config.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void set(String key, String value) {
        config.setProperty(key, value);
    }

}
