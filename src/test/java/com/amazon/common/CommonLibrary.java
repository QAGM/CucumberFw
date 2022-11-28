package com.amazon.common;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

import java.io.File;
import java.time.Duration;
import java.util.Date;

import static com.amazon.common.WebDriverInstance.driver;

public class CommonLibrary {
    Logger logger = Logger.getLogger("CommonLibrary.class");
    public void click(String xpath) {

        WebElement webElement = driver.findElement(By.xpath(xpath));
        //Assert.assertEquals(webElement.isDisplayed(),true);
        //Assert.assertEquals(webElement.isEnabled(),true)
        webElement.click();
    }

    public void click(By byElm) {

        WebElement webElement = driver.findElement(byElm);
        Assert.assertEquals(webElement.isDisplayed(), true);
        Assert.assertEquals(webElement.isEnabled(), true);
        webElement.click();
    }

    public void doubleClick(String xpath) {

        WebElement webElement = driver.findElement(By.xpath(xpath));
        Assert.assertEquals(webElement.isDisplayed(), true);
        Assert.assertEquals(webElement.isEnabled(), true);
        Actions action = new Actions(driver);
        action.doubleClick(webElement).perform();
    }

    public void doubleClick(WebElement webElement) {
        Assert.assertEquals(webElement.isDisplayed(), true);
        Assert.assertEquals(webElement.isEnabled(), true);
        Actions action = new Actions(driver);
        action.doubleClick(webElement).perform();
    }

    public void doubleClick(By elemBy) {

        WebElement webElement = driver.findElement(elemBy);
        Assert.assertEquals(webElement.isDisplayed(), true);
        Assert.assertEquals(webElement.isEnabled(), true);
        Actions action = new Actions(driver);
        action.doubleClick(webElement).perform();
    }

    public void rightClick(String xpath) {

        WebElement webElement = driver.findElement(By.xpath(xpath));
        Assert.assertEquals(webElement.isDisplayed(), true);
        Assert.assertEquals(webElement.isEnabled(), true);
        Actions action = new Actions(driver);
        action.contextClick(webElement).perform();
    }

    public void isVisible(String xpath) {
        waitFluent(xpath,60,2);
        //waitExplicit(xpath,60);
        WebElement webElement = driver.findElement(By.xpath(xpath));
        Assert.assertEquals(webElement.isDisplayed(), true);
    }

    public String getText(String xpath) {
        WebElement webElement = driver.findElement(By.xpath(xpath));
        Assert.assertEquals(webElement.isDisplayed(), true);
        Assert.assertEquals(webElement.isEnabled(), true);
        return webElement.getText();
    }

    public String getTitle(String xpath) {
        WebElement webElement = driver.findElement(By.xpath(xpath));
        Assert.assertEquals(webElement.isDisplayed(), true);
        Assert.assertEquals(webElement.isEnabled(), true);
        return webElement.getAttribute("title");
    }

    public void enterText(String xpath, String text) {
        WebElement webElement = driver.findElement(By.xpath(xpath));
        Assert.assertEquals(webElement.isDisplayed(), true);
        Assert.assertEquals(webElement.isEnabled(), true);
        webElement.sendKeys(text);
    }

    public void hover(String xpath) {

        WebElement webElement = driver.findElement(By.xpath(xpath));
        Assert.assertEquals(webElement.isDisplayed(), true);
        Assert.assertEquals(webElement.isEnabled(), true);
        Actions actions = new Actions(driver);
        actions.moveToElement(webElement).build().perform();
    }

    public void clickUsingJS(String xpath)
    {
        WebElement webElement = driver.findElement(By.xpath(xpath));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();",webElement);

    }
    public void scrollIntoElement(String xpath)
    {
        WebElement webElement = driver.findElement(By.xpath(xpath));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView();",webElement);

    }
    public void clickUsingAction(String xpath) {

        WebElement webElement = driver.findElement(By.xpath(xpath));
        Assert.assertEquals(webElement.isDisplayed(), true);
        Assert.assertEquals(webElement.isEnabled(), true);
        Actions actions = new Actions(driver);
        actions.click(webElement).build().perform();
    }
    public void clickUsingEnterKey(String xpath) {

        WebElement webElement = driver.findElement(By.xpath(xpath));
        Assert.assertEquals(webElement.isDisplayed(), true);
        Assert.assertEquals(webElement.isEnabled(), true);
        webElement.sendKeys(Keys.ENTER);
    }
    public void dragAndDrop(String xpathFrom, String xpathTo) {

        WebElement webElementFrom = driver.findElement(By.xpath(xpathFrom));
        WebElement webElementTo = driver.findElement(By.xpath(xpathTo));
        Assert.assertEquals(webElementFrom.isDisplayed(), true);
        Assert.assertEquals(webElementFrom.isEnabled(), true);
        Assert.assertEquals(webElementTo.isDisplayed(), true);
        Assert.assertEquals(webElementTo.isEnabled(), true);
        Actions actions = new Actions(driver);
        actions.dragAndDrop(webElementFrom,webElementTo).build().perform();
    }

    public void waitExplicit(String xpath, int seconds)
    {
        WebDriverWait wait = new WebDriverWait(driver,seconds);
        WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

    }
    public void waitFluent(String xpath, int seconds,int interval)
    {
        new FluentWait<>(driver.findElement(By.xpath(xpath)))
                .withTimeout(Duration.ofSeconds(seconds))
                .pollingEvery(Duration.ofSeconds(interval))
                .ignoring(NoSuchElementException.class).until(we->we.isDisplayed());


    }

    public void takeScreenshot(String ...args)
    {
        try{
            TakesScreenshot srcScreen = (TakesScreenshot) driver;
            File srcFile = srcScreen.getScreenshotAs(OutputType.FILE);
            String fileName;
            if(args.length>0)
            {
                fileName = "screenshot/"+ args[0] +"_" + new Date().getTime() + ".jpeg";
            }
            else
            {
                fileName = "screenshot/"+ new Date().getTime() + ".jpeg";
            }
            File destFile = new File(fileName);
            FileUtils.copyFile(srcFile,destFile);
            printLog("Screenshot has been taken "+fileName);

        }
        catch (Exception e)
        {

        }
    }

    public void printLog(String message)
    {
        logger.info(message);
    }

}
