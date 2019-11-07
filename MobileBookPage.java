package com.mab.test.pageobjects.mab.harvester;
/**
 * Created by Mitchells & Butlers plc.
 * Author: Jabez James
 * Date: 30/02/18
 * Base class which encapsulates the locators and methods for Restaurants Home Page
 */

import com.mab.test.pageobjects.mab.PageObject;
import com.mab.test.framework.helpers.ReportHelper;
import com.mab.test.framework.helpers.utils.DateUtil;
import com.mab.test.framework.helpers.utils.RandomGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MobileBookPage extends PageObject {

    //private static final Logger LOG = LoggerFactory.getLogger(RestaurantsPage.class);

    private By mealOption = By.name("mealtime");
    private By adults =By.xpath("//select[@id='adults-tablebooking']");
    private By calenderNext = By.xpath("//a[contains(@class,'ui-datepicker-next ui-corner-all')]");
    private By mealTime = By.xpath("//a[@class='main-btn forward-btn show-available-times']");
    private By review = By.xpath("//a[@class='main-btn forward-btn continue-to-review']");

    private By proceed =By.xpath("//a[@class='main-btn forward-btn continue-to-details']");
    private By order =By.name("placeOrder");

    private By firstName = By.name("firstName");
    private By lastName = By.name("lastName");
    private By emailAddress = By.name("email");
    private By contactNumber = By.name("contactNumber");
    private By landlineNumber = By.name("landlineNumber");
    private By specialInstructions = By.name("specialInstructions");
    private By emailCheckbox = By.id("mailingList_6");
    private By smsCheckbox = By.id("mailingList_7");
    private By remember = By.xpath("//label[contains(@for,'rememberMe_tablebooking')]");

    private WebElement we;

    /*public boolean verifypubSearchTerm() {
        return waitForExpectedElement(pubSearchTerm).isDisplayed();
    }
    
    public boolean verifySearchText(String query) {
    	
    	return waitForExpectedElement(pubSearchTerm).getText().trim().matches(query);
        
    }*/

    public void selectDate(String bdate) throws Exception {

        log(bdate);
        int mon = DateUtil.MonthsFromToday(bdate);
        int day1 = DateUtil.DayOfDate(bdate);

        log(Integer.toString(mon));
        log(Integer.toString(day1));

        WebDriverWait waitForDatePicker = new WebDriverWait(getWebDriver(), 5);
        waitForDatePicker.until(ExpectedConditions.elementToBeClickable(calenderNext));
        //WebElement calNextMonth = driver.findElement(By.xpath("//a[contains(@class,'ui-datepicker-next ui-corner-all')]"));

        for (int i = 0; i < mon; i++) {

            sleep(1000);
            waitForDatePicker.until(ExpectedConditions.elementToBeClickable(calenderNext));
            waitForExpectedElement(calenderNext).click();
            //getWebDriver().findElement(calenderNext).click();
            log(Integer.toString(i + 1));
        }

        sleep(2000);

        try {

            WebElement dayField = getWebDriver().findElement(By.linkText(Integer.toString(day1)));
            scrollToBottom();
            scrollToElement(dayField);
            if (dayField.isEnabled())
                dayField.click();
            else {
                log("Booking is not possible for the given date");
                System.exit(1);

            }
        } catch (NoSuchElementException exp) {
            log("Booking is not possible for the chosen date");
            System.exit(0);
        }
        getScreenShot("DateSelection");
    }

    public void selectMeal(String meal) throws InterruptedException {

        sleep(2000);
        WebDriverWait waitForMealtime = new WebDriverWait(getWebDriver(), 30);
        waitForMealtime.until(ExpectedConditions.elementToBeClickable(mealOption));
        Select mealtimeSelect = new Select(getWebDriver().findElement(mealOption));
        //listSelectOptions(mealtimeSelect);
        mealtimeSelect.selectByValue(meal);
        sleep(3000);
        getScreenShot("MealSelection");
    }

    public void selectGuests(String num) throws InterruptedException {

        sleep(1000);
        WebDriverWait waitForMealtime = new WebDriverWait(getWebDriver(), 30);
        waitForMealtime.until(ExpectedConditions.elementToBeClickable(adults));

        //WebElement guests = getWebDriver().findElement(adults);
        //guests.sendKeys(num);
        waitForExpectedElement(adults).sendKeys(num);
        //Select mealtimeSelect = new Select(getWebDriver().findElement(adults));
        //listSelectOptions(mealtimeSelect);
       // mealtimeSelect.selectByValue(num);
        getScreenShot("GuestsSelection");
        sleep(2000);
    }

    public void selectMealTime(String time) throws InterruptedException {

        WebElement showAvailableTimes = getWebDriver().findElement(mealTime);
        showAvailableTimes.click();

        sleep(3000);
        scrollToBottom();
        scrollToElement(waitForExpectedElement(By.xpath("//label[contains(@for,'tablebooking_timeslot_" + time + "')]")));
        WebElement timeslot = getWebDriver().findElement(By.xpath("//label[contains(@for,'tablebooking_timeslot_" + time + "')]"));
        timeslot.click();
        sleep(3000);
        getScreenShot("MealTimeSelection");
    }

    public void reviewBooking() throws InterruptedException {
        WebElement reviewBookingButton = getWebDriver().findElement(review);
        reviewBookingButton.click();
        sleep(2000);
        getScreenShot("Booking review");
    }

    public void proceedToBooking() throws InterruptedException {
        WebElement proceedToBookingButton = getWebDriver().findElement(proceed);
        proceedToBookingButton.click();
        sleep(2000);
    }

   /* public void enterPersonaldetails(String fName, String lName, String emailOptIn, String smsOptIn, String mobile, String landline, String email, String instructions) throws InterruptedException {

        String Rgen = RandomGenerator.randomString(2);
        WebElement firstName = getWebDriver().findElement(By.name("firstName"));
            firstName.sendKeys(fName+Rgen);

            WebElement lastName = getWebDriver().findElement(By.name("lastName"));
            lastName.sendKeys(lName);

            log("Entering email");
            WebElement emailAddress = getWebDriver().findElement(By.name("email"));
            //String recipientEmail = "mbplc_" + System.currentTimeMillis() + "@gmail.com";
            //emailAddress.sendKeys(recipientEmail);
            emailAddress.sendKeys(email);

            log("Entering mobile");
            //TODO Use somebody else's mobile number (or make this configurable?), or do not use a mobile number.
            WebElement contactNumber = getWebDriver().findElement(By.name("contactNumber"));
            //contactNumber.sendKeys("07795978888");
            contactNumber.sendKeys(mobile);

            log("Entering landline");
            WebElement landlineNumber = getWebDriver().findElement(By.name("landlineNumber"));
            //landlineNumber.sendKeys("02076052000");
            landlineNumber.sendKeys(landline);

            log("Entering instructions");
            sleep(2000);
            WebElement specialInstructions = getWebDriver().findElement(By.name("specialInstructions"));
            //specialInstructions.sendKeys("This is an automated test booking. Please do not actually reserve this table.");
            specialInstructions.sendKeys(instructions);

            WebElement remember = getWebDriver().findElement(By.xpath("//label[contains(@for,'rememberMe_tablebooking')]"));
            remember.click();
            sleep(2000);

            // Email OptIn
            WebElement emailCheckbox = getWebDriver().findElement(By.id("mailingList_6"));
            switch (emailOptIn)
            {
                case "yes":
                    if(!emailCheckbox.isSelected()) {
                        log("Email OptIn to be selected");
                        sleep(2000);
                        jClick(emailCheckbox);
                    }
                    break;
                case "no":
                    if(emailCheckbox.isSelected()) {
                        sleep(2000);
                        log("Email OptIn to be deselected");
                        jClick(emailCheckbox);
                    }
                    break;
            }

            // SMS OptIn
            WebElement smsCheckbox = getWebDriver().findElement(By.id("mailingList_7"));
            switch (smsOptIn)
            {
                case "yes":
                    if(!smsCheckbox.isSelected()) {
                        log("SMS OptIn to be selected");
                        sleep(2000);
                        jClick(smsCheckbox);
                    }
                    break;
                case "no":
                    if(smsCheckbox.isSelected()) {
                        log("SMS OptIn to be deselected");
                        sleep(2000);
                        jClick(smsCheckbox);
                    }
                    break;
            }
}*/
   public void enterPersonaldetails(String fName, String lName, String emailOptIn, String smsOptIn, String mobile, String landline, String email, String instructions, String rememberDetails) throws InterruptedException {

       String Rgen = RandomGenerator.randomString(2);

       log(" email: " + emailOptIn + " sms: " + smsOptIn);
       log("Entering first name");
       waitForExpectedElement(firstName).sendKeys(fName+Rgen);

       log("Entering last name");
       waitForExpectedElement(lastName).sendKeys(lName);

       log("Entering email");
       waitForExpectedElement(emailAddress).sendKeys(email);

       log("Entering mobile");
       waitForExpectedElement(contactNumber).sendKeys(mobile);

       log("Entering landline");
       waitForExpectedElement(landlineNumber).sendKeys(landline);

       getScreenShot("Personal Details");

       log("Entering special instructions");
       waitForExpectedElement(specialInstructions).sendKeys(instructions);

       sleep(2000);

       // Remember my details
       //WebElement remember = getWebDriver().findElement(By.xpath("//label[contains(@for,'rememberMe_tablebooking')]"));
       //waitForExpectedElement(remember).click();

       // Email OptIn
       WebElement emailCheck = getWebDriver().findElement(emailCheckbox);//(By.id("mailingList_6"));
       switch (emailOptIn)
       {
           case "yes":
               log("emailOptIn - inside case yes");
               //if(!waitForExpectedElement(emailCheckbox).isSelected()) {
               if(!emailCheck.isSelected()){
                   log("Email OptIn to be selected");
                   sleep(2000);
                   jClick(emailCheck);
               }
               break;

           case "no":
               log("emailOptIn - inside case no");
               //if(waitForExpectedElement(emailCheckbox).isSelected()) {
               if(emailCheck.isSelected()){
                   sleep(2000);
                   log("Email OptIn to be deselected");
                   jClick(emailCheck);
               }
               break;
       }

       // SMS OptIn
       WebElement smsCheck = getWebDriver().findElement(smsCheckbox);//(By.id("mailingList_6"));
       log(" Before switch: smsOptIn value: " + smsOptIn);
       switch (smsOptIn)
       {
           case "yes":
               log("SMS OptIn - inside case yes");
               if(!smsCheck.isSelected()){
                   log("SMS OptIn to be selected");
                   sleep(2000);
                   jClick(smsCheck);
               }
               break;

           case "no":
               log("SMS OptIn - inside case no");
               if(smsCheck.isSelected()){
                   sleep(2000);
                   log("SMS OptIn to be deselected");
                   jClick(smsCheck);
               }
               break;
       }

       // Remember Details Checkbox
       WebElement rememberCheck = getWebDriver().findElement(remember);
       log("Before switch: rememberDetails value: " + rememberDetails + " checkbox status: " + rememberCheck.isSelected());
       switch (rememberDetails)
       {
           case "yes":
               log("RememberDetails - inside case yes");
               if(!rememberCheck.isSelected()){
                   log("Remember Details to be selected");
                   sleep(2000);
                   jClick(smsCheck);
               }
               break;

           case "no":
               log("Remember Details - inside case no");
               if(rememberCheck.isSelected()){
                   sleep(2000);
                   log("Remember Details to be deselected");
                   jClick(smsCheck);
               }
               break;
       }
   }


    public void placeOrder() throws InterruptedException {
        WebElement placeOrderButton = getWebDriver().findElement(order);
        placeOrderButton.click();
        sleep(2000);
    }


    public void getConfirmationdetails() {

        String reference = waitForExpectedElement(By.xpath("//span[@class='tablebooking--ref-number']")).getText();

        String cdate = waitForExpectedElement(By.xpath("//em[@class='tablebooking--confirmation-date']")).getText();

        String ctime = waitForExpectedElement(By.xpath("//em[@class='tablebooking--confirmation-time']")).getText();

        String csize = waitForExpectedElement(By.xpath("//em[@class='tablebooking-confirmation-partysize']")).getText();

        log("Reference Number ="+ reference);
        log("Confirmation date ="+ cdate);
        log("Confirmation time ="+ ctime);
        log("Party size ="+ csize);
        getScreenShot("ConfirmationDetails");
        ReportHelper.Log("Booking Reference Number : "+ reference, "PASS");
        ReportHelper.Log("Booking Date : "+ cdate, "PASS");
        ReportHelper.Log("Booking Time : "+ ctime, "PASS");

    }
}
