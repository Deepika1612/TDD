@Smoke
Feature: Restaurant Search

  Scenario Outline: Harvester Mobile Table Booking - Positive path
    Given The user is on the Harvester mobile home page "<scenario>"
    And The user navigates to the Restaurants mobile search page
    When The mobile user searches for a Restaurant with postcode
    Then The nearest outlet is listed correctly in device

    Examples:
      | scenario |
      | 2 |




#  SCENARIO	POSTCODE	OUTLETNAME	OUTLETKEY	DATE	ADULTS	CHILDREN	MEAL	TIME
#  1	B31 JP	harvesterstarcitybirmingham	STAR	01/05/2018	2	1	Lunch	130000
#  2	B31 JP	pcttestlab	LAB	30/06/2018	2	1	Lunch	140000