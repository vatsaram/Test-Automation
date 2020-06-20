@POSTTest @AllTest
Feature: Validate that POST request is succesful for the API under test. 
Scenario Outline: Testing of POST Request for users by iterating over a set of users 
	Given Payload is prepared using jsonfile "postData.json" sheet "CreateUser" and scenarios as below 
		| <Scenario> | 
	Then validate that API responds with success for a post request
	Examples: 
		| Scenario | 
		| Test1 | 
		| Test2 | 
		| Test3 |