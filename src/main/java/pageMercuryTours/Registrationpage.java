package pageMercuryTours;

import java.util.List;
import java.util.Map;

import javax.swing.text.Document;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import util.ExcelReader;


public class Registrationpage {
	
	private WebDriver driver;
	public boolean flag_userregistered;

	@FindBy(xpath=("//a[text()=\"REGISTER\"]"))
	public WebElement lnkRegister;
	
	@FindBy(xpath="//input[@name=\"firstName\"]")
	public WebElement txtFirstName;
	
	@FindBy(xpath="//input[@name=\"lastName\"]")
	public WebElement txtLastName;
	
	@FindBy(xpath="//input[@name=\"phone\"]")
	public WebElement txtPhone;
	
	@FindBy(xpath="//input[@id=\"userName\"]")
	public WebElement txtEmail;
	
	@FindBy(xpath="//input[@name=\"address1\"]")
	public WebElement txtAddress;
	
	@FindBy(xpath="//input[@name=\"city\"]")
	public WebElement txtCity;
	
	@FindBy(xpath="//input[@name=\"state\"]")
	public WebElement txtState;
	
	@FindBy(xpath="//input[@name=\"postalCode\"]")
	public WebElement txtPostalCode;
	
	@FindBy(xpath="//select[@name=\"country\"]")
	public WebElement txtCoutry;
	
	@FindBy(xpath="//input[@id=\"email\"]")
	public WebElement txtUsername;
	
	@FindBy(xpath="//input[@name=\"password\"]")
	public WebElement txtpassword;
	
	@FindBy(xpath="//input[@name=\"confirmPassword\"]")
	public WebElement txtconfirmPassword;
	
	@FindBy(xpath="//input[@name=\"submit\"]")
	public WebElement btnSubmit;
	
	@FindBy(xpath="//span[starts-with(text(),\"PAssword\")]")
	public WebElement lblPasswordmismatch;
	
	@FindBy(xpath="//b[contains(text(),\"Your username is\")]")
	private WebElement lblUsernamedisplayed;
	
	public Registrationpage(WebDriver driver)
	{
		
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	public void clickRegisterlink()
	{
		lnkRegister.click();
	}
	public void RegistrationWithValidData(Map<String, String> map)
	{
		// ((JavascriptExecutor) driver).executeScript("document.getElementsByName(\"mercury\")[0].reset()");
		Select ddCountry=new Select(txtCoutry);
			txtFirstName.sendKeys(map.get("FirstName"));
			txtLastName.sendKeys(map.get("LastName"));
			txtPhone.sendKeys(map.get("Phone"));
			txtEmail.sendKeys(map.get("Email"));
			txtAddress.sendKeys(map.get("Address"));
			txtCity.sendKeys(map.get("City"));
			txtState.sendKeys(map.get("State"));
			txtPostalCode.sendKeys(map.get("PostalCode"));
			ddCountry.selectByVisibleText(map.get("Country").toUpperCase());
			txtUsername.sendKeys(map.get("UserName"));
			txtpassword.sendKeys(map.get("Password"));
			txtconfirmPassword.sendKeys(map.get("ConfirmPassword"));
			
			btnSubmit.click();
			if(!map.get("Password").equals(map.get("ConfirmPassword")))
			{
				Validation(txtpassword.getText());
			}
			
			else
			{
				//ExcelReader.write(null, null, map);
				 flag_userregistered=true;
			}
			
			
		}
	
	public void Validation(String FieldName)
	{
		if(FieldName==null)
		{
			
		}
		else
		{
			Assert.assertEquals("PAssword and con.password does not match", lblPasswordmismatch.getText().strip());
		}
	}
		 
		 
	}

