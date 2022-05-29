Feature: Test API results for given payload 

Scenario: Construct payload and review result 
	Given I construct a payload with end point "TestEndpoint" from file "RestEndPoints.json" 
	And query parameters defined in file "QueryParameters.json" and hit the end point 
	And response code as "400" 
	Then validate the response type as "application/json" 
	And validate the headers as per datatable 
		| Server | Content-Type | Transfer-Encoding |
		| rhino-core-shield | application/json | chunked |
	And verify content as per below table 
		| Status | Error | Path |
		| 400 | Bad Request | /api/papi/ads/search |