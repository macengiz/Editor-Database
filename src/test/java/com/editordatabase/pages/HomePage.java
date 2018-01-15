package com.editordatabase.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.editordatabase.utilities.Driver;

public class HomePage {
   
	public HomePage() {
		PageFactory.initElements(Driver.getInstance(), this);
	}

	@FindBy(id = "example") // even if you take it comment out if it is id and
							// tagname it will work
	public WebElement example;

	@FindBy(xpath = "//table[@id='example']/thead/tr/th")
	public List<WebElement> headers;

	@FindBy(xpath = "//table[@id='example']/tbody/tr/td[1]")
	public List<WebElement> rows;

	@FindBy(css = ".buttons-create")
	public WebElement newBtn;

	@FindBy(xpath = "//button[.='Create']")
	public WebElement createBtn;

	@FindBy(id = "DTE_Field_first_name")
	public WebElement firstName;

	@FindBy(id = "DTE_Field_last_name")
	public WebElement lastName;

	@FindBy(id = "DTE_Field_position")
	public WebElement position;

	@FindBy(id = "DTE_Field_office")
	public WebElement office;

	@FindBy(id = "DTE_Field_extn")
	public WebElement extention;

	@FindBy(id = "DTE_Field_start_date")
	public WebElement startDate;

	@FindBy(id = "DTE_Field_salary")
	public WebElement salary;

	@FindBy(css = "input[type='search']")
	public WebElement search;

	@FindBy(xpath = "//table/tbody/tr/td[1]")
	public WebElement webFN;

	@FindBy(xpath = "//table/tbody/tr/td[2]")
	public WebElement webPosition;

	@FindBy(xpath = "//table/tbody/tr/td[3]")
	public WebElement webOffice;

	@FindBy(xpath = "//table/tbody/tr/td[4]")
	public WebElement webExtention;

	@FindBy(xpath = "//table/tbody/tr/td[5]")
	public WebElement webStartDate;

	@FindBy(xpath = "//table/tbody/tr/td[6]")
	public WebElement webSalary;

}
