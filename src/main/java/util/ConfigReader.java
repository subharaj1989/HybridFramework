package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	public Properties PropertyLoad()
	{
		Properties prop=new Properties();
		try {
			FileInputStream i =new FileInputStream("C:\\Users\\Subha\\LiveProjects\\HybridFramework\\src\\test\\resources\\propertyfiles\\config.properties");
			prop.load(i);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}
}
