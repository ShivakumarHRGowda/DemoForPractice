package practice.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;

public class createContactWithDateFormatTest {
	public static void main(String[] args) throws IOException, InterruptedException {
       
		//read common data
		FileInputStream fis=new FileInputStream("C:\\Users\\User\\Desktop\\Data\\commandata.properties");
		Properties pObj=new Properties();
		pObj.load(fis);
		
		String browser=pObj.getProperty("browser");
		String url=pObj.getProperty("url");
		String username=pObj.getProperty("username");
		String password=pObj.getProperty("password");
		
		//generate randomNum
		Random random=new Random();
		int randInt=random.nextInt(1000);
		
		//read testScript data
		FileInputStream fis1=new FileInputStream("C:\\Users\\User\\Desktop\\Data\\textScriptData.xlsx");
		Workbook wb=WorkbookFactory.create(fis1);
		Sheet sheet=wb.getSheet("contact");
		Row row=sheet.getRow(5);
		String lastName=row.getCell(2).getStringCellValue()+randInt;
		wb.close();
		
		WebDriver driver=null;
		// cross browser testing
		if (browser.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equals("edge")) {
			driver = new EdgeDriver();
		} else if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		}

		driver.get(url);
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		
	    Date date=new Date();
	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	    String actDate =sdf.format(date);
	    Calendar cal=sdf.getCalendar();
	    cal.add(Calendar.DAY_OF_MONTH, 30);
	    String actDateAfter30Dyas=sdf.format(cal.getTime());
	    
		
		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(actDate);
		
		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(actDateAfter30Dyas);
		
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		
		
		String actDate1=driver.findElement(By.id("dtlview_Support Start Date")).getText();
		if(actDate1.contains(actDate)) {
			System.out.println(actDate+" is created ==PASS");
		}else {
			System.out.println(actDate+" is created ==FAIL");
		}
		
		String actDateAfter30Day=driver.findElement(By.id("dtlview_Support End Date")).getText();
		if(actDateAfter30Day.contains(actDateAfter30Dyas)) {
			System.out.println(actDateAfter30Day+" is created ==PASS");
		}else {
			System.out.println(actDateAfter30Day+" is created ==FAIL");
		}
		
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).click()
				.build().perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
	}
	
}
