package com.mab.test.stepdefinitions.mab.harvester.booking;

import com.mab.test.framework.helpers.ReportHelper;
import com.mab.test.framework.helpers.utils.ExcelUtils;
import com.mab.test.pageobjects.mab.harvester.BookPage;
import com.mab.test.pageobjects.mab.harvester.HomePage;
import com.mab.test.pageobjects.mab.harvester.MobileHomePage;
import com.mab.test.pageobjects.mab.harvester.MobileBookPage;
import com.mab.test.pageobjects.mab.harvester.RestaurantsPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.HashMap;


public class MobileHarvester_Booking {


    public MobileHomePage harvesterMobileHomePage;
    public MobileBookPage harvesterMobileBookPage;

	String postcode,outletname,outletkey,bdate,adults,children,meal,time;
	String firstName, lastName, emailOptIn, smsOptIn, mobile, landline, email, instructions,rememberDetails;


    public MobileHarvester_Booking( MobileHomePage harvestermobile,MobileBookPage harvesterbook) {

        this.harvesterMobileHomePage = harvestermobile;
        this.harvesterMobileBookPage = harvesterbook;

    }

    public void getTestData(String sNum) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        ReportHelper.Initialize("Mobile_HarvesterBooking");
        ExcelUtils EU = new ExcelUtils(".\\src\\test\\resources\\testdata\\TableBookingNew.xlsx");
        String key= "SCENARIO";
        HashMap<String,String> record = new HashMap<String, String>();
        //  Returns a row of record selected by its column name & its value ( key and value) combination.
        record = EU.readRecord(key,sNum);

       postcode = record.get("POSTCODE");
       outletname = record.get("OUTLETNAME");
       outletkey = record.get("OUTLETKEY");
       bdate = record.get("DATE");
       adults = record.get("ADULTS");
       children = record.get("CHILDREN");
       meal = record.get("MEAL");
       time = record.get("TIME");

       //Guest details page
       firstName = record.get("FIRSTNAME");
       lastName = record.get("LASTNAME");
       smsOptIn = record.get("SMSOPTIN");
       emailOptIn = record.get("EMAILOPTIN");
       mobile = record.get("MOBILE");
       landline = record.get("LANDLINE");
       email = record.get("EMAIL");
       instructions = record.get("INSTRUCTIONS");
        rememberDetails = record.get("REMEMBERDETAILS");
    }

    @Given("^The user is on the Harvester mobile home page \"(.*?)\"$")
    public void the_user_is_on_the_Harvester_mobile_home_page(String sNum) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        getTestData(sNum);
        harvesterMobileHomePage.gotoMobileHomePage();
        System.out.println("The user is on the Harvester home page");
        ReportHelper.Log("Nearest Harvester outlet is sorted and listed", "PASS");
    }


    @Given("^The user navigates to the Restaurants mobile search page$")
    public void the_user_navigates_to_the_Restaurants_mobilesearch_page() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        harvesterMobileHomePage.gotoRestaurantsSearchPage();
        System.out.println("The user is on the Restaurants search page");
        ReportHelper.Log("The user is on the Restaurants search page", "PASS");
    }

    @When("^The mobile user searches for a Restaurant with postcode$")
    public void the_mobile_user_searches_for_a_Restaurant_with_postcode() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
         harvesterMobileHomePage.searchAnOutlet(postcode);

    }


    @Then("^The nearest outlet is listed correctly in device$")
    public void the_nearest_outlet_is_listed_correctly_in_mobile() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Boolean sucess =harvesterMobileHomePage.verifySearchResults(outletkey,outletname);
        System.out.println(sucess);
        ReportHelper.Log("Nearest Harvester outlet is sorted and listed", "PASS");
        ReportHelper.Log("Navigated to the nearest Harvester outlet for booking", "PASS");
    }

    @When("^The user selects the number of guests in device$")
    public void the_user_selects_the_number_of_guests() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //throw new PendingException();
        harvesterMobileBookPage.selectGuests(adults);
    }

    @When("^The user selects the booking date in device$")
    public void the_user_selects_the_booking_date() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        harvesterMobileBookPage.selectDate(bdate);
        ReportHelper.Log("Booking date has been selected", "PASS");
    }

    @When("^The user selects meal and mealtime in device$")
    public void the_user_selects_meal_and_mealtimings() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        harvesterMobileBookPage.selectMeal(meal);
        harvesterMobileBookPage.selectMealTime(time);
        ReportHelper.Log("Meal Time has been selected", "PASS");

    }

    @When("^The user reviews the booking in device$")
    public void the_user_reviews_the_booking() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        harvesterMobileBookPage.reviewBooking ();
    }

    @Then("^The booking details are displayed for review in device$")
    public void the_booking_details_are_displayed_for_review() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // throw new PendingException();
    }

    @When("^The user proceeds to booking in device$")
    public void the_user_proceeds_to_booking() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        harvesterMobileBookPage.proceedToBooking();
    }

    @When("^The user enters personal details in device$")
    public void the_user_enters_personal_details() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        harvesterMobileBookPage.enterPersonaldetails(firstName, lastName, emailOptIn, smsOptIn, mobile, landline, email, instructions, rememberDetails);

    }

    @When("^The user places the order in device$")
    public void the_user_places_the_order() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        harvesterMobileBookPage.placeOrder();
    }

    @Then("^Booking confirmation details are displayed in device$")
    public void booking_confirmation_details_are_displayed() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        harvesterMobileBookPage.getConfirmationdetails();
    }


}
