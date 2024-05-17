package practice.datadriventesting;

import java.io.FileInputStream;
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
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

public class CreateOrgTestUsingXML {
   @Test
   public void seleinumTest(XmlTest test) throws Exception {
	   //read common data from XML file
	   String BROWSER=test.getParameter("browser");
	   String URL=test.getParameter("url");
	   String USERNAME=test.getParameter("username");
	   String PASSWORD=test.getParameter("password");
	   
	   //Genarate random number
	   Random random=new Random();
	   int randomInt=random.nextInt(1000);
	   
	   //read testSciptData from Excel sheet
	   FileInputStream fis=new FileInputStream("C:\\Users\\User\\Desktop\\ExcellDataForSelenium\\textScriptData.xlsx");
	   Workbook wb=WorkbookFactory.create(fis);
	   Sheet sheet=wb.getSheet("org");
	   Row row=sheet.getRow(1);
	   String data=row.getCell(2).getStringCellValue()+randomInt;
	   
	   WebDriver driver=null;
		  //cross browser testing
		    if(BROWSER.equals("chrome")) {
		    	driver=new ChromeDriver();
		    }else if(BROWSER.equals("edge")) {
		    	driver=new EdgeDriver();
		    }else if(BROWSER.equals("firefox")) {
		    	driver=new FirefoxDriver();
		    }else {
		    	driver=new ChromeDriver();
		    }
		    
		    driver.get(URL);
		    driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		    driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		    driver.findElement(By.id("submitButton")).click();
		    
		    driver.findElement(By.linkText("Organizations")).click();
		    driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
			driver.findElement(By.name("accountname")).sendKeys(data);
			driver.findElement(By.name("button")).click();
		  
			driver.quit();	   
   }
}
