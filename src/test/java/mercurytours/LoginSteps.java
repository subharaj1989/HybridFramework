package mercurytours;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageMercuryTours.LoginPage;
import util.ExcelReader;

public class LoginSteps {
	
   @DataProvider (name="User_Data")
	public Object[] readUserData()
	{
		String filepath=AppHooks.prop.getProperty("excelpath");
		List<Map<String,String>> l=new ArrayList<Map<String,String>>();
		l=ExcelReader.read(filepath, "users");
	    Object[] obj=new Object[1];
		obj[0]=l;
		
		return obj;
	}
	
	@Test(dataProvider ="User_Data")
	public void LoginSteps(List<Map<String,String>> l)
	{
		
		LoginPage pgLogin=new LoginPage(AppHooks.driver);
		String username=AppHooks.prop.getProperty("username");
		String password=AppHooks.prop.getProperty("password");
		pgLogin.loginvalidation(username,password,l);
		
		
		
		
		
		
	}
}
