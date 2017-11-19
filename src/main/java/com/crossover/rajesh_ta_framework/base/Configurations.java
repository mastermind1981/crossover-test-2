package com.crossover.rajesh_ta_framework.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class Configurations {

    public static final String URL = getProp().getProperty("crossover.web.url");
    public static final String BROWSER = getProp().getProperty("crossover.browser");//"ANDROID";
    public static final boolean REMOTE = Boolean.parseBoolean(getProp().getProperty("crossover.remote"));//false;
    public static long TIME_OUT_SECONDS = Long.parseLong(getProp().getProperty("crossover.timeout.seconds"));
    public static String CHROME_DRIVER_EXE = getProp().getProperty("crossover.chrome.driver");
    public static String FIREFOX_DRIVER_EXE = getProp().getProperty("crossover.gecko.driver");
    public static String IE_DRIVER_EXE = getProp().getProperty("crossover.ie.driver");
    private static Properties prop;
    private static HashMap<String, String> urlMap;
    public static String SELENIUM_GRID_URL = "https://" + getProp().getProperty("crossover.hub.url") + ":4444/wd/hub";

    private static Properties getProp() {

        if (prop == null) {
            prop = new Properties();
            InputStream input = null;

            try {
                input = Res.getResourceAsStream("system.properties");
                prop.load(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return prop;
    }
}
