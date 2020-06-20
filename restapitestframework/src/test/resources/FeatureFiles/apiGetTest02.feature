#@NegativeTest @AllTest
Feature: Validate that status code for unsuccessful GET request. 

Scenario Outline: Testing of GET Request for resource which does not exist
	Given connection request is prepared for API by querying with UserID
	| <UserID>  |
	Then validate that API responds with failure when resource does not exist 
	Examples: 
		| UserID |
		| 87865756 |
		| 5446345 |		