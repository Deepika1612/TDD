package com.mab.test.stepdefinitions.mab.harvester.booking;
import com.mab.test.framework.helpers.ReportHelper;
import com.mab.test.framework.helpers.utils.ExcelUtils;
import com.mab.test.hybrid.ControlsLibrary;
import com.mab.test.hybrid.WebDriverGenerator;
import com.mab.test.pageobjects.mab.harvester.*;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.HashMap;


public class Harvester_Booking {
	
	public HomePage harvesterHomePage;
	public RestaurantsPage restaurantPage;
    public BookPage bookPage;
	String postcode,outletname,outletkey,bdate,adults,children,meal,time;
	String firstName, lastName, emailOptIn, smsOptIn, mobile, landline, email, instructions, rememberDetails;
  public Harvester_Booking(HomePage harvester,RestaurantsPage restaurant,BookPage bookPage) {
        this.harvesterHomePage = harvester;
        this.restaurantPage = restaurant;
        this.bookPage = bookPage;
    }

    public void getTestData(String sNum) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        ReportHelper.Initialize("Harvester");
        ExcelUtils EU = new ExcelUtils("./src/test/resources/testdata/TableBookingNew.xlsx");
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

    @Given("^The user navigates to Harvester home page$")
    public void the_user_navigates_to_Harvester_home_page() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("The user is on the Harvester home page");
    }
        
    @Given("^The user is on the Harvester home page \"(.*?)\"$")
    public void the_user_is_on_the_Harvester_home_page(String sNum) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        getTestData(sNum);
    	harvesterHomePage.gotoHomePage("Harvester");
        System.out.println("The user is on the Harvester home page");
    }
    @Given("^The user navigates to Harvester home page \"(.*?)\"$")
    public void the_user_navigates_to_Harvester_home_page(String sNum) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        getTestData(sNum);
        WebDriverGenerator.openBrowser("DesktopChrome");
        ControlsLibrary.InitializeLibrary();
        ReportHelper.Initialize("Harvester");
        ReportHelper.Log("Execution started for the dataset : ","PASS");
        ControlsLibrary.navigateToURL("https://stage-harvester.cms.mbplc.com/");
        ReportHelper.Log("Sucessfully navigated to the URL: ","PASS");
        ReportHelper.Finalize("Harvester");

    }


    @Then("^The user validates the homepage navigation options$")
    public void the_user_validates_the_homepage_menu_options() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        harvesterHomePage.executeDynamicImageValidations();
        harvesterHomePage.executeDynamicMenuValidations();
        harvesterHomePage.executeFieldValidations();
    }

    @Then("^The user validates the booking page controls$")
    public void the_user_validates_the_booking_page_controls() throws Throwable {
        bookPage.executeFieldValidations();
    }



    @Given("^The user is on the Harvester Booking page \"(.*?)\"$")
    public void the_user_is_on_the_Harvester_Booking_page(String sNum) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        getTestData(sNum);
        harvesterHomePage.gotoBookingPage();
        System.out.println("The user is on the Harvester booking page");

    }

    @When("^The user searches for a Restaurant with postcode$")
    public void the_user_searches_for_a_Restaurant_with_postcode() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
    	 harvesterHomePage.FindAHarvester(postcode);
    	System.out.println("The user has searched for the restaurant");
        ReportHelper.Log("Restaurant located on the postcode:"+postcode+ " has been searched","INFO");
    }

    @Then("^User can see the Restaurants page$")
    public void user_can_see_the_Restaurants_page() throws Throwable {
        // Write code here that turns the phrase above into concrete actions

    	if (restaurantPage.verifypubSearchTerm()) {
            System.out.println("The user has navigated to the Harvester search results");
            ReportHelper.Log("Harvester search results page is displayed", "PASS");
        }else {

            ReportHelper.Log("Harvester search results page is not displayed", "FAIL");
        }

    }

    @Then("^The nearest outlet is listed correctly$")
    public void the_nearest_outlet_is_listed_correctly() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Boolean success = restaurantPage.verifySearchResult(outletkey);
        if (success) {
            System.out.println("The nearest Harvester outlet is listed");
            ReportHelper.Log("Nearest Harvester outlet is sorted & listed", "PASS");

        }else {
            ReportHelper.Log("Nearest Harvester outlet is not sorted & listed ", "FAIL");
        }
        ReportHelper.Finalize("Harvester");
    }

    @When("^The user selects the nearest outlet for booking$")
    public void the_user_selects_the_nearest_outlet_for_booking() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
          restaurantPage.selectNearestOutlet(outletname);
          ReportHelper.Log("Nearest Harvester outlet is selected for booking", "PASS");
    }

    @When("^The user selects the number of guests$")
    public void the_user_selects_the_number_of_guests() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //throw new PendingException();
        bookPage.selectGuests(adults);
    }

    @When("^The user selects the booking date$")
    public void the_user_selects_the_booking_date() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        bookPage.selectDate(bdate);
        ReportHelper.Log("Booking date has been selected", "PASS");
    }

    @When("^The user selects meal and mealtime$")
    public void the_user_selects_meal_and_mealtimings() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        bookPage.selectMeal(meal);
        bookPage.selectMealTime(time);
        ReportHelper.Log("Meal Time has been selected", "PASS");

    }

    @When("^The user selects meal option$")
    public void the_user_selects_meal() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        bookPage.selectMeal(meal);
        ReportHelper.Log("Meal has been selected", "PASS");

    }

    @When("^The user reviews the booking$")
    public void the_user_reviews_the_booking() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        bookPage.reviewBooking ();
    }

    @Then("^The booking details are displayed for review$")
    public void the_booking_details_are_displayed_for_review() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
       // throw new PendingException();
    }

    @When("^The user proceeds to booking$")
    public void the_user_proceeds_to_booking() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        bookPage.proceedToBooking();
    }

    @When("^The user enters personal details$")
    public void the_user_enters_personal_details() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        bookPage.enterPersonaldetails(firstName, lastName, emailOptIn, smsOptIn, mobile, landline, email, instructions, rememberDetails);

    }

    @When("^The user places the order$")
    public void the_user_places_the_order() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        bookPage.placeOrder();
    }

    @Then("^Booking confirmation details are displayed$")
    public void booking_confirmation_details_are_displayed() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        bookPage.getConfirmationdetails();
    }

}
