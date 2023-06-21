package pageMercuryTours;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage {

	WebDriver driver;
	@FindBy(xpath="//input[@name=\"userName\"]")
	private WebElement txtusername;
	
	@FindBy(xpath="//input[@name=\"password\"]")
	private WebElement txtpassword;
	@FindBy(xpath="//input[@name=\"submit\"]")
	private WebElement btnSubmit;
	
	@FindBy(xpath="//b[contains(text(),\"Thank\")]")
	private WebElement lblLoginSuccess;
	
	
	
	@FindBy(xpath="//span[contains(text(),\"Enter your\")]")
	public WebElement lblIncorrectUser;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void loginvalidation(String username,String password, List<Map<String, String>> l)
	{
		txtusername.sendKeys(username);
		txtpassword.sendKeys(password);
		btnSubmit.click();
		
		for (int i=0;i<l.size();i++)
		{
			if(username.equals(l.get(i).get("UserName")))
			{
				if(password.equals(l.get(i).get("Password")))
				{
					Assert.assertTrue(lblLoginSuccess.isDisplayed());
				}
				
				else
				{
					Assert.assertTrue(lblIncorrectUser.isDisplayed());
				}
			}
		}
		
	}
	
}
