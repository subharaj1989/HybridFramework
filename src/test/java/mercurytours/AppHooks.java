package mercurytours;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;

import DriverFactory.WebDriverFactory;
import util.ConfigReader;
import util.ExcelReader;

public class AppHooks {

	public static WebDriver driver;
	public static Properties prop;
	public static List<Map<String,String>> l;
	public static Sheet sheet;
	@BeforeTest
	public void DriverInitialise()
	{
	   WebDriverFactory df=new WebDriverFactory();
	   ConfigReader con=new ConfigReader();
	   prop=con.PropertyLoad();
	   df.intializeDriver(prop.getProperty("browser"));
		this.driver=df.getDriver();
		SetFiles();
		launchBrowser();
	}
	
	
	
	  public void SetFiles() { ExcelReader read= new ExcelReader(); 
	
	  try {
		sheet=read.getSheetName(prop.getProperty("excelpath"), "Registration");
	} catch (EncryptedDocumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  	  
	  
	  }
	 

	public void launchBrowser()
	{
		driver.get(prop.getProperty("URL"));
	}
}
