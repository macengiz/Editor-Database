@DatabaseTests
Feature: Editor Data Webtable 
Scenario: Editor Data Webtable Test 
	Given Navigate to https://editor.datatables.net/examples/simple/simple.html 
	When Wait for the webtable to be visible 
	When Assert that headers size is 6
	And Assert headers are following
	And Find Bruno Nash in the table then verify that he is a Software Engineer and works in London 
#	And Print all webtable content in a similar looking format 
#	And Click on NEW button
#	And Add new Employee information 
	When Search for the employee
	Then Verify All data displayed in the webtable matched the data you entered in step 8