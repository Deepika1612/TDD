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
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookPage extends PageObject {

    //private static final Logger LOG = LoggerFactory.getLogger(RestaurantsPage.class);

    private By mealOption = By.name("mealtime");
    private By adults =By.xpath("//select[@id='adults-tablebooking']");
    private By children =By.xpath("//select[@id='children-tablebooking']");
    private By calenderNext = By.xpath("//a[contains(@class,'ui-datepicker-next ui-corner-all')]");
    private By month = By.xpath("//span[@class='ui-datepicker-month']");
    private By year = By.xpath("//span[@class='ui-datepicker-year']");
    private By day = By.xpath("//a[@class='ui-state-default ui-state-highlight ui-state-active ui-state-hover']");
    private By mealTime = By.xpath("//a[@class='main-btn forward-btn show-available-times']");
    private By review = By.xpath("//a[@class='main-btn forward-btn continue-to-review']");
    private By message = By.xpath("//p[contains(.,'Please select an outlet')]");
    private By mealError = By.xpath("//p[contains(.,'Please select the meal time')]");

    private By ContinueToDate = By.xpath("//a[@class='main-btn continue-to-date']");

    private By location = By.xpath("//input[@id='location-tablebooking']");
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

    //  private By emailCheckbox = By.xpath("//label[contains(.,'SMS')]");
    //private By smsCheckbox = By.xpath("//label[contains(.,'Email')]");

    private By remember = By.xpath("//label[contains(@for,'rememberMe_tablebooking')]");

    private WebElement we;

    /*public boolean verifypubSearchTerm() {
        return waitForExpectedElement(pubSearchTerm).isDisplayed();
    }
    
    public boolean verifySearchText(String query) {
    	
    	return waitForExpectedElement(pubSearchTerm).getText().trim().matches(query);
        
    }*/

    public void executeFieldValidations() throws Exception {

        gotoSubPage("tablebooking");
       /* validateInputFields(location,"Harvester Location");
        if (waitForExpectedElement(message)!= null){
            ReportHelper.Log("Mandatory Field Validation message appears  : "+ waitForExpectedElement(message).getText(),"PASS");
        }else {
            ReportHelper.Log("Mandatory Field Validation message does not appear ", "FAIL");
        }*/
       waitForExpectedElement(location).sendKeys("PCT TEST LAB");
        sleep(1000);
        //enterJText(location,"PCT TEST LAB");
        //waitForExpectedElement(location).sendKeys(Keys.ESCAPE);
        waitForExpectedElement(location).sendKeys(Keys.ARROW_DOWN);
        waitForExpectedElement(location).sendKeys(Keys.ENTER);
        sleep(3000);

        ReportHelper.Log(waitForExpectedElement(location).getText(),"PASS");
        waitForExpectedElement(ContinueToDate).click();
        if (waitForExpectedElement(adults)!= null){
            ReportHelper.Log("Navigated to the date selection page","PASS");
            validateSelectOptionCount(adults,12,"Adults");
            selectByValue(adults, "2");
            if (waitForExpectedElement(children)!= null) {
                validateSelectOptionCount(children,12,"Children");
                selectByValue(children, "2");
            }
            ReportHelper.Log("Calender is displayed with default day: "+ waitForExpectedElement(day).getText()+ " month: "+waitForExpectedElement(month).getText()+ " year: "+waitForExpectedElement(year).getText() ,"PASS");
            ReportHelper.Log("Today : "+ DateUtil.CurrentDay()+ " month: "+DateUtil.CurrentMonth()+ " year: "+DateUtil.CurrentYear() ,"PASS");

            validateElementText(month,DateUtil.CurrentMonth(),"Current Month");
            validateElementText(year,DateUtil.CurrentYear(),"Current Year");
            validateElementText(day,DateUtil.CurrentDay(),"Current Day");
            scrollAndClick(mealOption);
            validateSelectOptionCount(mealOption,4,"Meal Option");
            waitForExpectedElement(mealOption).sendKeys(Keys.TAB);
            if (waitForExpectedElement(mealError) != null) {
                ReportHelper.Log("Error message appears on empty meal selection" + waitForExpectedElement(mealError).getText(), "PASS");
                }
                else {
                ReportHelper.Log("Error message does not appear on empty meal selection"  , "FAIL");
                }
            selectByValue(mealOption, "Dinner");
            waitForExpectedElement(mealTime).click();

            sleep(2000);
            gotoSubPage("restaurants/eastandwestmidlands/pcttestlab");
            navigateToPreviousPageUsingBrowserBackButton();
            sleep(2000);

        }else {
            ReportHelper.Log("Date selection page is not shown", "FAIL");
        }
    }

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

    public void selectBookingOutlet(String outlet){
        gotoSubPage("tablebooking");
       /* validateInputFields(location,"Harvester Location");
        if (waitForExpectedElement(message)!= null){
            ReportHelper.Log("Mandatory Field Validation message appears  : "+ waitForExpectedElement(message).getText(),"PASS");
        }else {
            ReportHelper.Log("Mandatory Field Validation message does not appear ", "FAIL");
        }*/
       //outlet = "PCT TEST LAB"
        waitForExpectedElement(location).sendKeys(outlet);
        sleep(1000);
        //enterJText(location,"PCT TEST LAB");
        //waitForExpectedElement(location).sendKeys(Keys.ESCAPE);
        waitForExpectedElement(location).sendKeys(Keys.ARROW_DOWN);
        waitForExpectedElement(location).sendKeys(Keys.ENTER);
        sleep(3000);
        waitForExpectedElement(ContinueToDate).click();

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
        sleep(2000);
        getScreenShot("Guests Selection");
    }

    public void selectMealTime(String time) throws InterruptedException {

         By mealT = By.xpath("//label[contains(@for,'tablebooking_timeslot_" + time + "')]");

        WebElement showAvailableTimes = getWebDriver().findElement(mealTime);
        showAvailableTimes.click();
        sleep(5000);
        waitForExpectedElement(mealT);
        WebElement timeslot = getWebDriver().findElement(mealT);
        timeslot.click();
        sleep(3000);
        getScreenShot("Meal Time Selection");
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
            getScreenShot("Personal details1");

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
        getScreenShot("Personal details2");
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

        getScreenShot("Confirmation details");

        log("Reference Number ="+ reference);
        log("Confirmation date ="+ cdate);
        log("Confirmation time ="+ ctime);
        log("Party size ="+ csize);
        ReportHelper.Log("Booking Reference Number : "+ reference, "PASS");
        ReportHelper.Log("Booking Date : "+ cdate, "PASS");
        ReportHelper.Log("Booking Time : "+ ctime, "PASS");

    }
}
