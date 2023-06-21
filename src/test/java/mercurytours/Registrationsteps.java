package mercurytours;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageMercuryTours.Registrationpage;
import util.ExcelReader;

public class Registrationsteps {

	Registrationpage pgRegister;
	
	
	@DataProvider(name="Registration_Users")
	public Object[] loadusers()
	{
		List<Map<String, String>> l=new ArrayList<Map<String, String>>();
		//Sheet sheet=AppHooks.sheet;
		l=ExcelReader.read(AppHooks.prop.getProperty("excelpath"),"Registration");
		int rowcount=l.size();
		Object[] obj=new Object[rowcount];
		for(int i=0;i<l.size();i++)
		{
			obj[i]=l.get(i);
			
		}
	//	Object[][] obj=new Object[rowcount][];
		
		return obj;
		
	}
	@Test(dataProvider="Registration_Users")
	public void ValidData(Map<String,String> map)
	{
		
		    pgRegister=new Registrationpage(AppHooks.driver);
		    pgRegister.clickRegisterlink();
			pgRegister.RegistrationWithValidData(map);
			if(pgRegister.flag_userregistered)
			{
				ExcelReader.write(AppHooks.prop.getProperty("excelpath"), "users", map);
				System.out.println("written in excel successfully");
			}
			
		}
}
	
