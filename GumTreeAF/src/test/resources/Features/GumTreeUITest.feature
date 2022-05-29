Feature: Check for opening Sample search results

Scenario: Open Gum Tree home page and search for results
Given I initialise the drivers and open Gum Tree home page
When I search for Category - "Electronics & Computer"
And search for the item - "Sennheiser Headphones"
And set the region as "Sydney Region, NSW"
And range as "20km"
Then click on search button to check the results

Scenario: Validate the search results 
Given search results are displayed on the web page from previous tests
Then open a random result from the search results
Then Verify the ad details page opens
And Verify a numeric ad id is displayed in the breadcrumbs
And Verify atleast one similar ad is displayed in the page
