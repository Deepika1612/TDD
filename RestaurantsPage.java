package com.mab.test.pageobjects.mab.harvester;
/**
 * Created by Mitchells & Butlers plc.
 * Author: Jabez James
 * Date: 30/02/18
 * Base class which encapsulates the locators and methods for Restaurants Home Page
 */
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.mab.test.pageobjects.mab.PageObject;
import com.mab.test.framework.helpers.UrlBuilder;

public class RestaurantsPage extends PageObject {
	
	//private static final Logger LOG = LoggerFactory.getLogger(RestaurantsPage.class);
 
    private By cookiePolicyButton = By.xpath("//a[contains(.,'Accept & Close')]");
    private By pubSearchTerm = By.xpath("//input[@id='findAPubSearchTerm']");
    private By pubSearch = By.xpath("//input[@id='findAPubSearch']");
    private By firstResult = By.xpath("//a[@class='findOnMap minus']");
       
    private WebElement we ;

    public boolean verifypubSearchTerm() {

        sleep(3000);
        return waitForExpectedElement(pubSearchTerm).isDisplayed();
    }
    

    
    public boolean verifySearchText(String query) {

        sleep(3000);
    	return waitForExpectedElement(pubSearchTerm).getText().trim().matches(query);
        
    }

    public boolean verifySearchResult(String result) {

        sleep(7000);
        String nOutlet = waitForExpectedElement(firstResult).getText().trim();
        log (nOutlet);
        log(result);
        boolean ret = nOutlet.contains(result);
        getScreenShot("Search Results Page");
    	return ret;

    }

    public void selectNearestOutlet(String outletname) {

        sleep(2000);

            String url = UrlBuilder.harvesterUrl + "restaurants/eastandwestmidlands/"+outletname+"/tablebooking";
            log(url);
            clickLinkbyURL(url);


        sleep(2000);
    }

    public void selectNearestToby(String outletname) {

        sleep(2000);

        String url = UrlBuilder.tobyUrl + "restaurants/east-and-west-midlands/"+outletname+"/tablebooking";
        log(url);
        clickLinkbyURL(url);


        sleep(2000);
    }

    public void selectNearestMC(String outletname) {

        sleep(2000);

        String url = UrlBuilder.mcUrl + "restaurants/midlands/"+outletname+"/tablebooking";
        log(url);
        clickLinkbyURL(url);


        sleep(2000);
    }


}
