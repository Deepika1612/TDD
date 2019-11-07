
Feature: Harvester Homepage

  @Hybrid
  Scenario Outline: Harvester Home Page dynamic validations
    Given The user navigates to Harvester home page "<scenario>"
   #Then The user validates the homepage navigation options
    #And The user validates the booking page controls

    Examples:
      | scenario |
      | 2 |

  @Sanity
  Scenario Outline: Harvester Search Restaurants - Positive path
   Given The user is on the Harvester home page "<scenario>"
    When The user searches for a Restaurant with postcode
    Then The nearest outlet is listed correctly

    Examples:
      | scenario |
      | 2 |

#  SCENARIO	POSTCODE	OUTLETNAME	OUTLETKEY	DATE	ADULTS	CHILDREN	MEAL	TIME
#  1	B31 JP	harvesterstarcitybirmingham	STAR	01/05/2018	2	1	Lunch	130000
#  2	B31 JP	pcttestlab	LAB	30/06/2018	2	1	Lunch	140000