package com.amazon.pages;

import com.amazon.common.CommonLibrary;
import com.amazon.common.ProjectConstant;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import junit.framework.Assert;

public class ContactUsPage extends CommonLibrary {
    Logger logger = Logger.getLogger("ContactUsPage.class");
    String headerXpath = "//*[@class='page-heading bottom-indent']";
    By homeBy = By.id("header_logo");


    public void verifyHeader()
    {
        isVisible(headerXpath);
        String headerText = getText(headerXpath);
        printLog("Header Text ="+headerText.trim());
        Assert.assertEquals(headerText.equals(ProjectConstant.CONTACT_US_HEADER_LABEL_CONTACT_US),true);
    }

    public WebHomePage navigateToHome()
    {
        logger.info("navigateToHOme:ENTER");
        click(homeBy);
        return new WebHomePage();
    }





}
