@E2E
Feature: Take Away Scenario

  Scenario Outline: Harvester Table Booking - Positive path
    Given The user opens the Harvester home page "<scenario>"
    And The user navigates to Take Away page
    When The user searches for a Takeaway Restaurant
    Then User can see the Harvester search results
    When The user selects the nearest outlet for takeaway
    And The user selects the takeaway time
    And The user selects Items to takeaway
    And The user enters the personal details
    And The user enters the credit card details
    Then The user should see the takeaway confirmation

    Examples: 
      | scenario |
      | 3 |
  #  SCENARIO	POSTCODE	OUTLETNAME	OUTLETKEY	DATE	ADULTS	CHILDREN	MEAL	TIME
  #  1	B31 JP	harvesterstarcitybirmingham	STAR	01/05/2018	2	1	Lunch	130000
  #  2	B31 JP	pcttestlab	LAB	30/06/2018	2	1	Lunch	140000
