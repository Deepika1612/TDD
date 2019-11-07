@E2E
Feature: End to End table booking
  Scenario Outline: Harvester Table Booking - Positive path
    Given The user is on the Harvester home page "<scenario>"
    When The user searches for a Restaurant with postcode
    Then The nearest outlet is listed correctly
    When The user selects the nearest outlet for booking
    And The user selects the number of guests
    And The user selects the booking date
    And The user selects meal and mealtime
    And The user reviews the booking
    Then The booking details are displayed for review
    When The user proceeds to booking
    And The user enters personal details
    And The user places the order
    Then Booking confirmation details are displayed

    Examples: 
      | scenario |
      | 3 |

  #  SCENARIO	POSTCODE	OUTLETNAME	OUTLETKEY	DATE	ADULTS	CHILDREN	MEAL	TIME
  #  1	B31 JP	harvesterstarcitybirmingham	STAR	01/05/2018	2	1	Lunch	130000
  #  2	B31 JP	pcttestlab	LAB	30/06/2018	2	1	Lunch	140000
  @Book
  Scenario Outline: Harvester Table Booking - Continuation
    Given The user is on the Harvester Booking page "<scenario>"
    When The user selects the number of guests
    And The user selects the booking date
    And The user selects meal and mealtime
    And The user reviews the booking
    Then The booking details are displayed for review
    When The user proceeds to booking
    And The user enters personal details
    And The user places the order
    Then Booking confirmation details are displayed

    Examples:
      | scenario |
      | 3 |
