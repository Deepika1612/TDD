@AndroidHarvester
Feature: Mobile End to End table booking

  Scenario Outline: Harvester Table Booking - Positive path
    Given The user is on the Harvester mobile home page "<scenario>"
    And The user navigates to the Restaurants mobile search page
    When The mobile user searches for a Restaurant with postcode
    Then The nearest outlet is listed correctly in device
    And The user selects the number of guests in device
    And The user selects the booking date in device
    And The user selects meal and mealtime in device
    And The user reviews the booking in device
    Then The booking details are displayed for review in device
    When The user proceeds to booking in device
    And The user enters personal details in device
    And The user places the order in device
    Then Booking confirmation details are displayed in device

    Examples: 
      | scenario |
      | 3 |
  #  SCENARIO	POSTCODE	OUTLETNAME	OUTLETKEY	DATE	ADULTS	CHILDREN	MEAL	TIME
  #  1	B31 JP	harvesterstarcitybirmingham	STAR	01/05/2018	2	1	Lunch	130000
  #  2	B31 JP	pcttestlab	LAB	30/06/2018	2	1	Lunch	140000


