package com.mab.test.pageobjects.mab.harvester;
/**
 * Created by Mitchells & Butlers plc.
 * Author: Jabez James
 * Date: 30/02/18
 * Time: 12:00 PM
 * Base class which encapsulates the locators and methods for Harvester Home Page
 */

import com.mab.test.pageobjects.mab.PageObject;
import com.mab.test.framework.helpers.UrlBuilder;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.appium.java_client.AppiumDriver;

public class MobileHomePage extends PageObject {
	
	private static final Logger LOG = LoggerFactory.getLogger(MobileHomePage.class);
 
    private By cookiePolicyButton = By.xpath("//a[contains(.,'Accept & Close')]");
    private By findAHarvester = By.xpath("//input[@id='search']");
    private By goButton = By.xpath("//button[@type='submit']");
   
    private By bookATable = By.cssSelector("img[alt='Book a table at Harvester']");
    
    private By bookYourTable = By.cssSelector("img[alt='Book your table online']");
    private By findYourRestaurant = By.cssSelector("img[alt='Find your local restaurant']");
    


    
    private WebElement we ;

    //To be merged
    public void gotoMobileHomePage() throws Exception {
        LOG.info(" Navigating to Harvester Mobile HomePage \n");
        //ReportHelper.Log("Navigating to Harvester Mobile HomePage", "INFO");
        UrlBuilder .startHarvesterMobileHomePage();
       // UrlBuilder.startHarvesterHomePage();

        Thread.sleep(10000);
        getScreenShot("Homepage");
        getWebDriver().findElement(By.xpath("//a[contains(.,'Accept & Close')]")).click();
        sleep(5000);

    }

    public void gotoRestaurantsSearchPage() throws Exception {
        LOG.info(" Navigating to Harvester Mobile Restaurants Search Page \n");
        //ReportHelper.Log("Navigating to Harvester Mobile HomePage", "INFO");

        WebElement tog = getWebDriver().findElement(By.className("burger-toggle"));
        tog.click();
        sleep(5000);
        String Environment  = "https://stage-harvester.cms.mbplc.com/";
        String url1 = Environment+"restaurants";
        clickLinkbyURL(url1);
        sleep(5000);
        try
        {
            log("selected restaurants page");
            String currentContext = ((AppiumDriver<WebElement>)getWebDriver()).getContext();
            ((AppiumDriver<WebElement>)getWebDriver()).context("NATIVE_APP");
            sleep(5000);
            log("Inside native app context");
            ((AndroidDriver) getWebDriver()).findElement(By.id("android:id/button1")).click();

            getWebDriver().findElement(MobileBy.xpath("//*[@class='android.widget.Button'][2]")).click();

            ((AppiumDriver<WebElement>)getWebDriver()).context(currentContext);}
            catch (Exception ne)
        {
            log("Native app popips does not appears");
        }
        getScreenShot("Searchpage");

    }

    public void searchAnOutlet(String postcode) {

        //search restaurant
        log ("postcode is "+postcode);
        getWebDriver().findElement(By.id("findAPubSearchTerm")).sendKeys(postcode);
        sleep(1000);
        //driver.findElement(By.id("findAPubSearchTerm")).submit();
        getWebDriver().findElement(By.id("findAPubSearch")).click();
        log("Title "+getWebDriver().getTitle());
        sleep(5000);
        getScreenShot("Search Results");
    }

    public boolean verifySearchResults(String outletkey,String outletname) {
        Boolean success = getWebDriver().findElement(By.xpath("//a[@class='findOnMap minus']")).getText().trim().contains(outletkey);
        log(getWebDriver().findElement(By.xpath("//a[@class='findOnMap minus']")).getText());
        sleep(5000);
        getScreenShot("Nearest outlet");
        if (success){

            String Environment  = "https://stage-harvester.cms.mbplc.com/";
            String url2 = Environment + "restaurants/eastandwestmidlands/"+outletname+"/tablebooking";
            clickLinkbyURL(url2);
        }

        sleep(3000);
        return success ;
    }

}
