package com.editordatabase.step_definitions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.editordatabase.pages.HomePage;
import com.editordatabase.utilities.ConfigurationReader;
import com.editordatabase.utilities.Driver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DataValidation {

	private WebDriver driver;
	HomePage homePage = new HomePage();

	@Given("^Navigate to https://editor\\.datatables\\.net/examples/simple/simple\\.html$")
	public void navigate_to_https_editor_datatables_net_examples_simple_simple_html() throws Throwable {

		driver = Driver.getInstance();
		driver.get(ConfigurationReader.getProperty("url"));
	}

	@When("^Wait for the webtable to be visible$")
	public void wait_for_the_webtable_to_be_visible() throws Throwable {

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(homePage.example));
	}

	@When("^Assert that headers size is (\\d+)$")
	public void assert_that_headers_size_is(int arg1) throws Throwable {

		Assert.assertEquals(6, homePage.headers.size());
	}

	@When("^Assert headers are following$")
	public void assert_headers_are_following() throws Throwable {
		Map<Integer, String> headerValues = new HashMap<>();
		headerValues.put(5, "Name");
		headerValues.put(6, "Position");
		headerValues.put(7, "Office");
		headerValues.put(8, "Extn.");
		headerValues.put(9, "Start date");
		headerValues.put(10, "Salary");

		for (int i = 0; i < homePage.headers.size(); i++) {
			Assert.assertEquals(headerValues.get(i + 5), homePage.headers.get(i).getText());
		}

	}

	@When("^Find Bruno Nash in the table then verify that he is a Software Engineer and works in London$")
	public void find_Bruno_Nash_in_the_table_then_verify_that_he_is_a_Software_Engineer_and_works_in_London()
			throws Throwable {

		List<WebElement> rowsNames = driver.findElements(By.xpath("//table[@id='example']/tbody/tr/td[1]"));
		List<String> realNames = new ArrayList<>();

		for (WebElement names : rowsNames) {
			realNames.add(names.getText());
		}
		Assert.assertTrue(realNames.contains("Bruno Nash"));

		WebElement occupation = driver
				.findElement(By.xpath("//table[@id='example']/tbody/tr/td[.='Bruno Nash']/../td[2]"));
		Assert.assertEquals("Software Engineer", occupation.getText());

		WebElement place = driver.findElement(By.xpath("//table[@id='example']/tbody/tr/td[.='Bruno Nash']/../td[3]"));
		Assert.assertEquals("London", place.getText());
	}

	@When("^Print all webtable content in a similar looking format$")
	public void print_all_webtable_content_in_a_similar_looking_format() throws Throwable {

		List<WebElement> rows = driver.findElements(By.xpath("//table[@id='example']/tbody/tr"));

		for (int i = 1; i <= rows.size(); i++) {
			for (int j = 1; j <= homePage.headers.size(); j++) {
				System.out.print(
						homePage.example.findElement(By.xpath("//tr[" + i + "]/td[" + j + "]")).getText() + " | ");
			}
			System.out.println();
		}
	}

	// @When("^Click on NEW button$")
	public void click_on_NEW_button() throws Throwable {

	}

	// @When("^Add new Employee information$")
	public void add_new_Employee_information() throws Throwable {

	}

	// @When("^Search for the employee$")
	public void search_for_the_employee() throws Throwable {
		driver.findElement(By.cssSelector("input[type='search]")).sendKeys("Ashton Cox");
	}

	@Then("^Verify All data displayed in the webtable matched the data you entered in step (\\d+)$")
	public void verify_All_data_displayed_in_the_webtable_matched_the_data_you_entered_in_step(int arg1)
			throws Throwable {
		Assert.assertEquals("Ashton Cox", driver.findElement(By.cssSelector(".sorting_1")).getText());
		Assert.assertEquals("Junior Technical Author ",
				driver.findElement(By.cssSelector("td.sorting_1+td")).getText());
		Assert.assertEquals("San Francisco", driver.findElement(By.cssSelector(".sorting_1+td+td")).getText());
		Assert.assertEquals("1562", driver.findElement(By.cssSelector(".sorting_1+td+td+td")).getText());
		Assert.assertEquals("2009-01-12", driver.findElement(By.cssSelector(".sorting_1+td+td+td+td")).getText());
		Assert.assertEquals("$86,000", driver.findElement(By.cssSelector(".sorting_1+td+td+td+td")).getText());

		List<WebElement> nameValues = driver.findElements(By.cssSelector("#row_3>td"));

		List<String> stringNames = new ArrayList<>();

		for (WebElement name : nameValues) {
			stringNames.add(name.getText());
		}

		for (int i = 0; i <= nameValues.size(); i++) {
			Assert.assertEquals(ConfigurationReader.getProperty("" + (i + 1)), stringNames.get(i));
			// String.valueOf(i)
		}

		// for (int i = 0; i <= nameValues.size(); i++) {
		// Assert.assertEquals(ConfigurationReader.getProperty(""+(i+1)),
		// nameValues.get(i).getText());
		//
		// }

		// List<String> stringNames = new ArrayList<>();
		// int i=1;
		// for (WebElement name : nameValues) {
		// stringNames.add(name.getText());
		//
		// Assert.assertEquals(ConfigurationReader.getProperty("" + (i + 1)),
		// stringNames.get(i));
		// i++;
		// }

	}

}
