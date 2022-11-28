package com.amazon.common;

import com.amazon.pages.WebHomePage;
import com.amazon.util.PropertyReaderUtil;
import org.apache.log4j.PropertyConfigurator;

import static com.amazon.common.WebDriverInstance.driver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
public class BasePage {

    @Before
    public void setup()
    {
    	PropertyReaderUtil.readProperties();
        driver = WebDriverInstance.getDriver(PropertyReaderUtil.auto.getProperty("browser"));
        System.out.println("@@@@baseurl"+ PropertyReaderUtil.auto.getProperty("url"));
        driver.get(PropertyReaderUtil.auto.getProperty("url"));
    }
    @After
    public void tearUp()
    {
    	try{Thread.sleep(3000);}catch (Exception e){}
        driver.quit();
    }

  
    public void readProperty()
    {
        PropertyReaderUtil.readProperties();
        System.out.println(System.getProperty("user.dir")+"/log4j.properties");
        PropertyConfigurator.configure(System.getProperty("user.dir")+"/log4j.properties");
    }

    public WebHomePage navigateToHome()
    {
        driver.get(PropertyReaderUtil.auto.getProperty("baseurl"));
        return  new WebHomePage();
    }


}
