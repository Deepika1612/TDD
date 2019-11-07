package com.mab.test.pageobjects.mab.harvester;
/**
 * Created by Mitchells & Butlers plc.
 * Author: Jabez James
 * Date: 30/02/18
 * Time: 12:00 PM
 * Base class which encapsulates the locators and methods for Harvester Home Page
 */
import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mab.test.pageobjects.mab.PageObject;
import com.mab.test.framework.helpers.ReportHelper;
import com.mab.test.framework.helpers.UrlBuilder;

public class HomePage extends PageObject {
	
	private static final Logger LOG = LoggerFactory.getLogger(HomePage.class);
 
    private By cookiePolicyButton = By.xpath("//a[contains(.,'Accept & Close')]");
    private By findAHarvester = By.xpath("//input[@id='search']");
    private By goButton = By.xpath("//button[@type='submit']");
    private By bookATable = By.cssSelector("img[alt='Book a table at Harvester']");
    private By bookYourTable = By.cssSelector("img[alt='Book your table online']");
    private By findYourRestaurant = By.cssSelector("img[alt='Find your local restaurant']");

    private WebElement we ;

    public void gotoHomePage(String Brand) throws Exception {

        String url = null;
        LOG.info(" Navigating to Harvester HomePage \n");
        ReportHelper.Log("Navigating to Harvester HomePage", "INFO");
        System.out.println("Navigating to Harvester HomePage");
        getWebDriver().manage().deleteAllCookies();
        String parent_window = getWebDriver().getWindowHandle();
        getWebDriver().switchTo().window(parent_window);


        if (Brand.equalsIgnoreCase("Harvester"))
        {
        UrlBuilder.startHarvesterHomePage();
        url = UrlBuilder.harvesterUrl +"";
        }

        if (Brand.equalsIgnoreCase("Toby"))
        {
            UrlBuilder.startTobyCarveryHomePage();
            url = UrlBuilder.tobyUrl +"";
        }


        Thread.sleep(1000);
        setFocus();


        try {
            Boolean isCookiePolicyButtonPresent = webDriver.findElements(cookiePolicyButton).size() > 0;
            if (isCookiePolicyButtonPresent) {
                //  LOG.info("\n ****** cookies button has been closed ******");
                timeUnitWait(1);
                waitForElementClickable(cookiePolicyButton).click();
            }
        }

        catch (NoSuchElementException exp) {
            log("Cookie Policy option does not appear");
            }

        if (checkCurrentUrlContains(url)) {
            getScreenShot("Homepage");
            ReportHelper.Log("User has navigated to the Harvester homepage","PASS");
            ReportHelper.Log("URL : "+getCurrentUrl(),"INFO");
            ReportHelper.Log("Page Title : "+getCurrentPageTitle(),"INFO");

        }else{
                ReportHelper.Log("User could not navigate to the Harvester homepage", "FAIL");
                getScreenShot("Error during Homepage Navigation");
                System.exit(1);
         }
    }


    public void gotoBookingPage() throws Exception {

        LOG.info(" Navigating to Harvester BookingPage \n");
        ReportHelper.Log("Navigating to Harvester HomePage", "INFO");
        getWebDriver().manage().deleteAllCookies();
        String parent_window = getWebDriver().getWindowHandle();
        getWebDriver().switchTo().window(parent_window);
        UrlBuilder.startHarvesterBookingPage();
        Thread.sleep(1000);
        setFocus();

        getScreenShot("Bookingpage");
        ReportHelper.Log("User has navigated to the booking page","PASS");

    }
     
    public void clickOnBookYourTableOnline() {

        waitForExpectedElement(bookYourTable).click();
    }
    
    public void clickOnFindYourRestaurant() {

        waitForExpectedElement(findYourRestaurant).click();
        
    }
    
    public boolean verifyFindAHarvester() {
        return waitForExpectedElement(findAHarvester).isDisplayed();
    }

           public void FindAHarvester(String query) {

            waitForExpectedElement(findAHarvester).clear();
            waitForExpectedElement(findAHarvester).sendKeys(query);
            waitForExpectedElement(findAHarvester).submit();

    }

    public void gotoTakeAwayPage() {

        sleep(1000);

        String url = UrlBuilder.harvesterUrl +"takeaway";
        log(url);
        clickLinkbyURL(url);
        sleep(1000);
        if (checkCurrentUrlContains("takeaway")) {

            getScreenShot("TakeAwayPage");
            ReportHelper.Log("User has navigated to the take away page", "PASS");
            ReportHelper.Log("URL : "+getCurrentUrl(),"INFO");
            ReportHelper.Log("Page Title : "+getCurrentPageTitle(),"INFO");
        }else
             {
                 ReportHelper.Log("User could not navigate to the take away page", "FAIL");
                 getScreenShot("Error during Takeaway page Navigation");
                 System.exit(1);
             }

    }

    public void executeFieldValidations() {

        validateInputFields(findAHarvester,"Harvester Search");

    }

    public void executeDynamicImageValidations() {

        validateAnImage("Book a table at Harvester","tablebooking");
        getWebDriver().navigate().back();

        validateAnImage("Book your table online","tablebooking");
        getWebDriver().navigate().back();

        validateAnImage("Find your local restaurant","restaurants");
        getWebDriver().navigate().back();

        validateAnImage("Kids menu available at Harvester","kids");
        getWebDriver().navigate().back();

    }


    public void executeDynamicMenuValidations(){

        gotoSubPage("tablebooking");
        getWebDriver().navigate().back();

        gotoSubPage("restaurants");
        scrollSlowMotion();
        getWebDriver().navigate().back();

        gotoSubPage("ourmenus");
        scrollSlowMotion();
        getWebDriver().navigate().back();

        gotoSubPage("kids");
        scrollSlowMotion();
        getWebDriver().navigate().back();

        gotoSubPage("takeaway");
        scrollSlowMotion();
        getWebDriver().navigate().back();

        gotoSubPage("giftcards");
        scrollSlowMotion();
        getWebDriver().navigate().back();

        gotoSubPage("offers");
        scrollSlowMotion();
        getWebDriver().navigate().back();

        gotoSubPage("christmasbookings");
        scrollSlowMotion();
        getWebDriver().navigate().back();

        gotoSubPage("about");
        scrollSlowMotion();
        getWebDriver().navigate().back();

        gotoSubPage("contactus");
        scrollSlowMotion();
        getWebDriver().navigate().back();

        gotoSubPage("termsandconditions");
        scrollSlowMotion();
        getWebDriver().navigate().back();

        gotoSubPage("privacy");
        scrollSlowMotion();
        getWebDriver().navigate().back();

        gotoSubPage("accessibility");
        scrollSlowMotion();
        getWebDriver().navigate().back();

        gotoSubPage("faq");
        scrollSlowMotion();
        getWebDriver().navigate().back();

        gotoSubPage("jobs");
        scrollSlowMotion();
        getWebDriver().navigate().back();



    }





    

}
