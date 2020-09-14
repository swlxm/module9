package com.epam.java.selenium.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.util.Properties;

public class DriverSingleton {

    private static WebDriver driver;
    private DriverSingleton() {}

    @Parameters({"env", "browser"})
    public static WebDriver getDriver(String env, String browser) throws IOException {
        if(null == driver) {
            Properties envProp = new Properties();
            envProp.load(DriverSingleton.class.getResourceAsStream("/" + env + ".properties"));
            if(null == browser)
                browser = envProp.getProperty("browser");
            switch (browser) {
                case "ie":
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                default:
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--lang=en-us");
                    options.addArguments("--start-maximized");
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(options);

            }
        }
        return driver;
    }

    public static void close() {
        driver.quit();
        driver = null;
    }
}
