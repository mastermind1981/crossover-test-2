package com.crossover.rajesh_ta_framework.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Res {

    private static final String[] DEFAULT_RESOURCE_PATHS = {null, "src/main/resources", "src/test/resources"};

    public static URL getResource(String propFile) {
        URL resource = Thread.currentThread().getContextClassLoader().getResource(propFile);

        if (resource == null) {
            for (String resourcePath : DEFAULT_RESOURCE_PATHS) {
                File resFile = new File(resourcePath, propFile);
                if (resFile.isFile() || resFile.isDirectory()) {
                    try {
                        resource = resFile.toURI().toURL();
                        break;
                    } catch (MalformedURLException ex) {
                        throw new RuntimeException("Unable to locate the csv file");
                    }
                }
            }
        }
        return resource;
    }

    public static InputStream getResourceAsStream(String propFile) {
        InputStream resource = Thread.currentThread().getContextClassLoader().getResourceAsStream(propFile);

        if (resource == null) {
            for (String resourcePath : DEFAULT_RESOURCE_PATHS) {
                File resFile = new File(resourcePath, propFile);
                if (resFile.isFile() || resFile.isDirectory()) {
                    try {
                        resource = new FileInputStream(resFile);
                        break;
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException("Unable to locate the csv file");
                    }
                }
            }
        }
        return resource;
    }
}
