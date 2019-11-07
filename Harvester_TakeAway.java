package com.mab.test.stepdefinitions.mab.harvester.booking;

import com.mab.test.framework.helpers.ReportHelper;
import com.mab.test.framework.helpers.utils.ExcelUtils;
import com.mab.test.pageobjects.mab.harvester.BookPage;
import com.mab.test.pageobjects.mab.harvester.HomePage;
import com.mab.test.pageobjects.mab.harvester.RestaurantsPage;
import com.mab.test.pageobjects.mab.harvester.TakeAwayPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.HashMap;


public class Harvester_TakeAway {

	public HomePage harvesterHomePage;
	public RestaurantsPage restaurantPage;
    public BookPage bookPage;
    public TakeAwayPage takeawayPage;

	String postcode,outletname,outletkey,bdate,adults,children,meal,time;
	String firstName, lastName, emailOptIn, smsOptIn, mobile, landline, email, instructions, rememberDetails;
    public Harvester_TakeAway(HomePage harvester, RestaurantsPage restaurant, BookPage bookPage, TakeAwayPage takeawayPage) {
        this.harvesterHomePage = harvester;
        this.restaurantPage = restaurant;
        this.bookPage = bookPage;
        this.takeawayPage = takeawayPage;
    }

    public void getTestData(String sNum) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
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

    @Given("^The user opens the Harvester home page \"(.*?)\"$")
    public void the_user_is_on_the_Harvester_home_page(String sNum) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        getTestData(sNum);
    	harvesterHomePage.gotoHomePage("Harvester");
        System.out.println("The user is on the Home page");
    }

    @Given("^The user navigates to Take Away page$")
    public void The_user_navigates_to_Take_Away_page() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        harvesterHomePage.gotoTakeAwayPage();
        System.out.println("The user is on the Take away page");
    }

    @When("^The user searches for a Takeaway Restaurant$")
    public void the_user_searches_for_a_Takeaway_Restaurant() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        takeawayPage.FindATakeAwayHarvester(postcode);
        ReportHelper.Log("Searched for the nearest harvester", "PASS");
    }

    @Then("^User can see the Harvester search results$")
    public void user_can_see_the_Harvester_search_results() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        takeawayPage.verifySearchResult(outletkey);
    }

    @When("^The user selects the nearest outlet for takeaway$")
    public void the_user_selects_the_nearest_outlet_for_takeaway() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        takeawayPage.selectNearestTakeAway(outletname);
        ReportHelper.Log("Navigated to the nearest take away", "PASS");
    }

    @When("^The user selects the takeaway time$")
    public void The_user_selects_the_takeaway_time() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        takeawayPage.selectTime("19:00");
        ReportHelper.Log("Take away time has been selected", "PASS");
    }

    @When("^The user selects Items to takeaway$")
    public void The_user_selects_the_Items() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        takeawayPage.selectItems();
        ReportHelper.Log("Items has been selected from the take away menu", "PASS");
    }

    @When("^The user enters the personal details$")
    public void The_user_enters_the_personal_details() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        takeawayPage.enterPersonalDetails();
        ReportHelper.Log("Personal details has been entered", "PASS");
    }

    @When("^The user enters the credit card details$")
    public void The_user_enters_the_creditcard_details() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        takeawayPage.enterCreditCard();
        ReportHelper.Log("Credit card details are confirmed and an order has been placed", "PASS");
    }



    @Then("^The user should see the takeaway confirmation$")
    public void The_user_should_see_the_takeaway_confirmation() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        takeawayPage.takeawayConfirmation();
        ReportHelper.Log("Take Away confirmation details are displayed", "PASS");
    }



}
