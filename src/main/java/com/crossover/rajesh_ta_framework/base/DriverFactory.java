package com.crossover.rajesh_ta_framework.base;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {

    private static Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();

    public static void createWebDriverInstance() throws MalformedURLException {
        String browserName = Configurations.BROWSER;
        createWebDriverInstance(browserName);
    }

    public static void createWebDriverInstance(String browserName) throws MalformedURLException {
        String thread = Thread.currentThread().getName();
        WebDriver driver = null;
        DesiredCapabilities capabilities = new DesiredCapabilities();
        ChromeOptions options = null;

        switch (browserName.toUpperCase()) {
            case "CHROME":
                capabilities = DesiredCapabilities.chrome();
                options = new ChromeOptions();
                options.addArguments("test-type", "start-maximized", "no-default-browser-check", "--disable-extensions");
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                capabilities.setPlatform(Platform.ANY);
                if (Configurations.REMOTE) {
                    driver = new RemoteWebDriver(new URL(Configurations.SELENIUM_GRID_URL), capabilities);
                } else {
                    System.out.println(Configurations.CHROME_DRIVER_EXE);
                    System.setProperty("webdriver.chrome.driver", Configurations.CHROME_DRIVER_EXE);
                    driver = new ChromeDriver(capabilities);
                }
                drivers.put(thread, driver);
                break;
            case "FIREFOX":
                capabilities = DesiredCapabilities.firefox();
                capabilities.setCapability("marionette", true);

                if (Configurations.REMOTE) {
                    driver = new RemoteWebDriver(new URL(Configurations.SELENIUM_GRID_URL), capabilities);
                } else {
                    System.out.println("Firefox instance.....");
                    System.setProperty("webdriver.gecko.driver", Configurations.FIREFOX_DRIVER_EXE);
                    driver = new FirefoxDriver();
                }
                driver = new FirefoxDriver();
                drivers.put(thread, driver);
                break;
            case "IE":
                System.setProperty("webdriver.ie.driver", Configurations.IE_DRIVER_EXE);
                driver = new InternetExplorerDriver();

                drivers.put(thread, driver);
                break;
            default:
                capabilities = DesiredCapabilities.chrome();
                options = new ChromeOptions();
                options.addArguments("test-type", "start-maximized", "no-default-browser-check", "--disable-extensions");
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                capabilities.setPlatform(Platform.ANY);
                if (Configurations.REMOTE) {
                    driver = new RemoteWebDriver(new URL(Configurations.SELENIUM_GRID_URL), capabilities);
                } else {
                    System.setProperty("webdriver.chrome.driver", Configurations.CHROME_DRIVER_EXE);
                    driver = new ChromeDriver(capabilities);
                }
        }
    }

    public static WebDriver getDriver() {
        return drivers.get(Thread.currentThread().getName());
    }
}
