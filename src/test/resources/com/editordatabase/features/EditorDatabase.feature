@JDBCEDITOR
Feature: Editor Database

Scenario: User reads employee info from mySql database and create 
entries in editor database table and verify them if they are created.

	Given I connect to MySql Database 
	And I execute following SQL query "SELECT first_name, last_name, job_id, city, phone_number, hire_date,salary FROM employees e JOIN departments d ON e.department_id=d.department_id JOIN locations l ON d.location_id=l.location_id" 
	And I navigate to Editors Employees table 
	Then Employees table should be displayed 
	When I create a new employee 
	Then Webtable data and Database data should match
	