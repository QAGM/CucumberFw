package com.amazon.common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverInstance {
    public static WebDriver driver = null;
    public static WebDriver getDriver(String browser)
    {
        if(browser.equalsIgnoreCase(ProjectConstant.BROWSER_CHROME))
        {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        else if(browser.equalsIgnoreCase(ProjectConstant.BROWSER_EDGE))
        {
        	WebDriverManager.edgedriver().setup();
        	driver = new EdgeDriver();
        }
        else if(browser.equalsIgnoreCase(ProjectConstant.BROWSER_FIREFOX)|| browser.equalsIgnoreCase(ProjectConstant.BROWSER_MOZILLA))
        {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        return driver;
    }
}
