package com.mab.test.pageobjects.mab.harvester;
/**
 * Created by Mitchells & Butlers plc.
 * Author: Jabez James
 * Date: 30/02/18
 * Base class which encapsulates the locators and methods for Restaurants Home Page
 */

import com.mab.test.pageobjects.mab.PageObject;
import com.mab.test.framework.helpers.UrlBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class TakeAwayPage extends PageObject {

    //private static final Logger LOG = LoggerFactory.getLogger(RestaurantsPage.class);



    private By searchTerm =By.xpath("//input[@id='findatakeawaySearchTerm']");;
    private By searchButton =By.xpath("//a[@id='findatakeawaySearch']");
    private By firstResult = By.xpath("//a[@class='findOnMap minus']");
    private By selectTime = By.xpath("//select[@id='time-takeaway']");
    private By continueToOrder = By.xpath("//a[contains(@class,'main-btn continue-to-create')]");
    private By first = By.xpath("(//a[@class='main-btn add-to-order'])[1]");
    private By second = By.xpath("(//a[@class='main-btn add-to-order'])[2]");
    private By third = By.xpath("(//a[@class='main-btn add-to-order'])[4]");
    private By continueToReview = By.xpath("//a[contains(.,'Continue to review order')]");
    private By enterDetails = By.xpath("//a[contains(.,'Confirm & enter details')]");

    private By fName = By.xpath("//input[@id='firstName-takeaway']");
    private By lName = By.xpath("//input[@id='lastName-takeaway']");
    private By email = By.xpath("//input[@id='email-takeaway']");
    private By mobile = By.xpath("//input[@id='contactNumber-takeaway']");

    private By credit = By.xpath("//input[@id='cardBrand-VISA-takeaway']");

    private By address = By.xpath("//input[@id='addressLine1-takeaway']");

    private By town = By.xpath("//input[@id='addressTown-takeaway']");

    private By postcode = By.xpath("//input[@id='addressPostcode-takeaway']");

    private By terms = By.xpath("//input[@id='termsAndCon-takeaway']");

    private By emailInfo =By.xpath("//input[@data-list-type='EMAIL']");
    private By smsInfo =By.xpath("//input[@data-list-type='SMS']");
    private By placeorder =By.xpath("//button[@name='placeOrder']");

    private By cardnumber = By.xpath("//input[@id='payment-cardnumber']");
    private By cardholder = By.xpath("//input[@id='payment-cardholdername']");
    private By eMonth=  By.xpath("//select[@id='payment-expirydate-month']");
    private By eYear = By.xpath("//select[@id='payment-expirydate-year']");
    private By cvc = By.xpath("//input[@id='payment-cvc']");
    private By paymentSubmit=  By.xpath("//input[@id='payment-submit']");
    private By frame = By.id("payment_frame_frame");

    private By confirm = By.xpath("//p[contains(.,'Thank you, jabez')]");

    private WebElement we;

    /*public boolean verifypubSearchTerm() {
        return waitForExpectedElement(pubSearchTerm).isDisplayed();
    } */

    public boolean verifySearchResult(String result) {

        boolean ret = waitForExpectedElement(firstResult).getText().trim().contains(result);
        getScreenShot("Search Results Page");
        return ret;

    }

    public void FindATakeAwayHarvester(String query) {

        waitForExpectedElement(searchTerm).clear();
        waitForExpectedElement(searchTerm).sendKeys(query);
        waitForExpectedElement(searchButton).click();

    }

    public void selectNearestTakeAway(String outletname) {

        sleep(2000);

        String url = UrlBuilder.harvesterUrl + "restaurants/eastandwestmidlands/"+outletname+"/takeaway";
        log(url);
        clickLinkbyURL(url);


        sleep(2000);
        getScreenShot("Nearest Takeaway Menu");

    }

    public void selectTime(String time) throws InterruptedException {

        sleep(2000);

        Select mealtimeSelect = new Select( waitForExpectedElement(selectTime));
        //listSelectOptions(mealtimeSelect);
        mealtimeSelect.selectByVisibleText(time);
        sleep(2000);
        waitForExpectedElement(continueToOrder).click();
        getScreenShot("TimeSelection");
    }

    public void selectItems() throws InterruptedException {
        waitForExpectedElement(first).click();
        scrollAndClick(second);
        scrollAndClick(third);
        sleep(3000);
        getScreenShot("ItemsSelection");
        scrollAndClick(continueToReview);
        sleep(3000);
    }

    public void enterPersonalDetails() throws InterruptedException {

        scrollAndClick(enterDetails);
        waitForExpectedElement(fName).sendKeys("jabez");
        waitForExpectedElement(lName).sendKeys("James");
        waitForExpectedElement(email).sendKeys("jamesja@mbplc.com");
        waitForExpectedElement(mobile).sendKeys("7508638414");

        getScreenShot("Personal Details-1");
        waitForExpectedElement(credit).click();
        waitForExpectedElement(address).sendKeys("190 Wharf Lane");
        waitForExpectedElement(town).sendKeys("Solihull");
        waitForExpectedElement(postcode).sendKeys("B912UN");
        scrollAndClick(terms);
        scrollAndClick(emailInfo);
        scrollAndClick(smsInfo);
        getScreenShot("Personal Details-2");
        scrollAndClick(placeorder);
        sleep(3000);
    }

    public void enterCreditCard() throws InterruptedException {

        sleep(3000);
        //scrollToBottom();
        switchFrame(frame);
        scrollToElement(waitForExpectedElement(cardnumber));
        waitForExpectedElement(cardnumber).sendKeys("4111111111111111");
        scrollToElement(waitForExpectedElement(cardholder));
        waitForExpectedElement(cardholder).sendKeys("Jabez");
        waitForExpectedElement(eMonth).sendKeys("8");
        waitForExpectedElement(eYear).sendKeys("2021");
        waitForExpectedElement(cvc).sendKeys("123");
        getScreenShot("Credit Card Details");
        waitForExpectedElement(paymentSubmit).click();
        sleep(5000);

    }

    public void takeawayConfirmation() throws InterruptedException {

        waitForExpectedElement(confirm);
        getScreenShot("Confirmation Screen");

    }


}
