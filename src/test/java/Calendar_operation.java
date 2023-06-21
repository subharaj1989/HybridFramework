
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
public class Calendar_operation {
	
	public static WebElement MonthNavigation(WebElement wmonth,List<WebElement>MonthList,String month,WebDriver driver )
	{
		
		boolean flag=false;
	
		for(WebElement m:MonthList)
		{
			System.out.println("Month Name:"+m.getText());
			if(m.getText().contains(month))
			{
				 wmonth=m;
				flag=true;
				break;
				
			}
		}
		if(!flag)
		{
			driver.findElement(By.xpath("//*[@id=\"root\"]//div[@class=\"DayPicker-NavBar\"]/span[@aria-label=\"Next Month\"]")).click();
			wmonth=MonthNavigation(wmonth,MonthList,month,driver);
		}
		
		
		return wmonth;
		}
		
	

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
    
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		String Date="10/June/2023";
		
		String[] d=Date.split("/");
		String day=d[0];
		String month=d[1];
		String year=d[2];
		driver.get("https://www.makemytrip.com/");
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div[2]/div[1]/div[3]")).click();
		WebElement month_selected = null;
		WebElement month_returned=null;
		//boolean flag = false;
		
		List<WebElement> MonthList=driver.findElements(By.xpath("//*[@id=\"root\"]//div[@class=\"DayPicker-Caption\"]"));
		month_returned=MonthNavigation(month_selected,MonthList,month,driver);
		
			List<WebElement> Days=month_returned.findElements(By.xpath("//parent::div//parent::div//div[@class=\"DayPicker-Body\"]//div[contains(@aria-label,'"+month.substring(0, 3)+"')]//p[1]"));
			for(WebElement days:Days)
			{
				if(days.getText().equals(day))
						{
					      days.click();
					      break;
						}
			}
		
		
		
			}
		
		
	

}
