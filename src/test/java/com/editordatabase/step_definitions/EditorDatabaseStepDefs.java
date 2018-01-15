package com.editordatabase.step_definitions;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.editordatabase.pages.HomePage;
import com.editordatabase.utilities.ConfigurationReader;
import com.editordatabase.utilities.DBUtility;
import com.editordatabase.utilities.DBUtility.DBType;
import com.editordatabase.utilities.Driver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class EditorDatabaseStepDefs {

	WebDriver driver = Driver.getInstance();
	HomePage homePage = new HomePage();
	List<String[]> queryResult;

	@Given("^I connect to MySql Database$")
	public void i_connect_to_MySql_Database() throws Throwable {
		DBUtility.establishConnection(DBType.MYSQL);
	}

	@Given("^I execute following SQL query \"([^\"]*)\"$")
	public void i_execute_following_SQL_query(String myFeatureFileQuery) throws Throwable {
		// String myQuery = "SELECT first_name, last_name, job_id, city,
		// phone_number, "
		// + "hire_date,salary FROM employees e JOIN departments d ON "
		// + "e.department_id=d.department_id JOIN locations l ON
		// d.location_id=l.location_id ";
		queryResult = DBUtility.runSQLQuery(myFeatureFileQuery);
		DBUtility.closeConnections();
	}

	@Given("^I navigate to Editors Employees table$")
	public void i_navigate_to_Editors_Employees_table() throws Throwable {
		driver.navigate().to(ConfigurationReader.getProperty("url"));
	}

	@Then("^Employees table should be displayed$")
	public void employees_table_should_be_displayed() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(homePage.example));
		Assert.assertTrue(homePage.example.isDisplayed());

	}

	@When("^I create a new employee$")
	public void i_create_a_new_employee() throws Throwable {
		for (int i = 0; i < 5; i++) { // queryResult.size()
			// [ jack, smit, 3456, qa, newyorkn, 3456 ]
			// [ john, smit, 3456, qa, newyorkn, 3456 ]
			// [ matthew, smit, 3456, qa, newyorkn, 3456 ]

			homePage.newBtn.click();
			String[] resultArray = queryResult.get(i); // [ jack, smit, 3456,
														// qa, newyorkn, 3456 ]
			homePage.firstName.sendKeys(resultArray[0]);
			homePage.lastName.sendKeys(resultArray[1]);
			homePage.position.sendKeys(resultArray[2]);
			homePage.office.sendKeys(resultArray[3]);

			String myExtent = resultArray[4].substring(resultArray[4].length() - 4, resultArray[4].length());
			homePage.extention.sendKeys(myExtent);
			homePage.startDate.sendKeys(resultArray[5]);
			homePage.salary.sendKeys(resultArray[6]);
			homePage.createBtn.click();
			Thread.sleep(2000);
			// WebDriverWait wait=new WebDriverWait(driver, 10);
			// wait.until(ExpectedConditions.visibilityOf(homePage.newButton));
		}
	}

	@Then("^Webtable data and Database data should match$")
	public void webtable_data_and_Database_data_should_match() throws Throwable {
		for (int i = 0; i < 5; i++) { // queryResult.size()
			String[] myCompareResult = queryResult.get(i);

			String fullName = (myCompareResult[0] + " " + myCompareResult[1]);
			homePage.search.sendKeys(fullName);
			String webName = homePage.webFN.getText();
			String webPosition = homePage.webPosition.getText();
			String webOffice = homePage.webOffice.getText();
			String webExtention = homePage.webExtention.getText();
			String webStartDate = homePage.webStartDate.getText();
			String webSalary = homePage.webSalary.getText().replace("$", "").replace(",", "") + ".00";

			String myExtent = myCompareResult[4].substring(myCompareResult[4].length() - 4,
					myCompareResult[4].length());
			Assert.assertEquals((myCompareResult[0] + " " + myCompareResult[1]), webName);
			Assert.assertEquals(myCompareResult[2], webPosition);
			Assert.assertEquals(myCompareResult[3], webOffice);
			Assert.assertEquals(myExtent, webExtention);
			Assert.assertEquals(myCompareResult[5], webStartDate);
			Assert.assertEquals(myCompareResult[6], webSalary);
			homePage.search.clear();

			// List<String> compVal=new ArrayList<>();
			// for (int j = 0; j < myCompareResult.length; j++) {
			// compVal.add((myCompareResult[0]+" "+myCompareResult[1]));
			// compVal.add((myCompareResult);
			// compVal.add((myCompareResult);
			// compVal.add((myCompareResult[0]+" "+myCompareResult[1]));
			//
			//
			// Assert.assertEquals(compVal.get(j), actual);
			//
			// }
		}

	}

}
