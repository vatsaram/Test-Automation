@GETTest @AllTest
Feature: Validate that GET request is succesful for the API under test. 

Scenario: Testing of GET Request of all users 
	Given connection request is prepared for API under test 
	Then validate that API responds with success and provides the list of users 

	

Scenario Outline: Testing of GET Request for users based on query Parameters 
	Given connection request is prepared for API under test with query paramaters
	| <Query_Param> | <value> |
	Then validate that API responds with success and provides the list of users based on query parameters
Examples:
		| Query_Param | value |
		| last_name | O'Conner |
		| last_name | Rice |
		| last_name | Lind | 
		

Scenario Outline: Testing of GET Request for users based on ID 
	Given connection request is prepared for API by querying with UserID
	| <UserID>  |
	Then validate that API responds with success and provides the list of users 
	Examples: 
		| UserID |
		| 1838 |
		| 1839 |
		| 1840 |		
		
