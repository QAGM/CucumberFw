package com.amazon.pages;

import com.amazon.util.MysqlUtil;
import junit.framework.Assert;
import com.amazon.common.CommonLibrary;


public class ProductDetailPage extends CommonLibrary {
    String priceXpath="//span[@id='our_price_display']";
    public ProductDetailPage verifyPrice(String productName)
    {
        try{Thread.sleep(3000);}catch(Exception e){}
        String priceFromApp = getText(priceXpath);
        String priceFromDB = MysqlUtil.getPriceFromDB(productName);
        printLog("Price from APP "+priceFromApp);
        printLog("Price from DB "+priceFromDB);
        Assert.assertEquals(priceFromApp.contains(priceFromDB),true);
        return  this;
    }

}
