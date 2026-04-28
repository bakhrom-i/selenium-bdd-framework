package com.portfolio.bdd.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver get() {
        if (driver.get() == null) init();
        return driver.get();
    }

    private static void init() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();
        String gridUrl = System.getProperty("grid.url", "");

        MutableCapabilities caps = "firefox".equals(browser) ? new FirefoxOptions() : new ChromeOptions();

        try {
            if (!gridUrl.isBlank()) {
                driver.set(new RemoteWebDriver(new URL(gridUrl), caps));
            } else if ("firefox".equals(browser)) {
                WebDriverManager.firefoxdriver().setup();
                driver.set(new org.openqa.selenium.firefox.FirefoxDriver((FirefoxOptions) caps));
            } else {
                WebDriverManager.chromedriver().setup();
                driver.set(new org.openqa.selenium.chrome.ChromeDriver((ChromeOptions) caps));
            }
            driver.get().manage().window().maximize();
        } catch (Exception e) {
            throw new RuntimeException("Driver init failed", e);
        }
    }

    public static void quit() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
