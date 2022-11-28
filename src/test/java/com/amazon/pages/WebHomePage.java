package com.amazon.pages;

import com.amazon.common.CommonLibrary;
import com.amazon.common.ProjectConstant;
import org.apache.log4j.Logger;
import org.junit.Assert;

public class WebHomePage extends CommonLibrary {//
    Logger logger = Logger.getLogger("WebHOmePage.class");
    // declare all variables of webhome page like xpath string
    String womenTabXpath = "//a[@title='Women'] ";
    String cartXpath = "//a[@title='View my shopping cart']";
    String dressesTabXpath = "//div[@id='block_top_menu']/ul/li[2]/a";
    String TshirtTabXpath = "//div[@id='block_top_menu']/ul/li[3]/a";
    String contactUsXpath = "//a[@title='Contact Us']";
    String searchBoxXpath = "//input[@id='search_query_topsss']";
    String searchButtonXpath = "//button[@name='submit_search']";
    String newProductXpath = "//a[@title='New products']";
    String emailXpath = "//input[@id='newsletter-input']";

    public ContactUsPage navigateToContactUs(){
        click(contactUsXpath);
        //clickUsingJS(contactUsXpath);
        //clickUsingAction(contactUsXpath);
        //clickUsingEnterKey(contactUsXpath);
        return new ContactUsPage();
    }

 public void navigateToWomenTab (){

     click(womenTabXpath);

 }
 public WebHomePage verifyCartEmpty()
 {
     logger.info("Entered into verifycardempty");
     String textOfCart = getText(cartXpath);
     Assert.assertEquals(textOfCart.contains(ProjectConstant.CART_LABEL_WEB_HOME),true);
     Assert.assertEquals(textOfCart.contains(ProjectConstant.EMPTY_LABEL_WEB_HOME),true);
     logger.info("Exited into verifycardempty");
     return this;
 }

 public SearchPage searchProduct(String searchTerm)
 {
     enterText(searchBoxXpath,searchTerm);
     click(searchButtonXpath);
     return new SearchPage();
 }
 public void navigateToNewProducts()
 {
     scrollIntoElement(newProductXpath);
     click(newProductXpath);
 }
 public void dragNewProductToEmailField()
 {
     scrollIntoElement(newProductXpath);
     dragAndDrop(newProductXpath,emailXpath);
 }

}
