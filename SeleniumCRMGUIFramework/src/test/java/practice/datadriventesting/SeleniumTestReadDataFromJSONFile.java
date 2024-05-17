package practice.datadriventesting;

import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class SeleniumTestReadDataFromJSONFile {
  @Test
  public void seleniumReadData() throws Exception {
	  //read common data from JSONfile
	  JSONParser jsonParser=new JSONParser();
	 Object object= jsonParser.parse(new FileReader("C:\\Users\\User\\Desktop\\appCommonData.json"));
	  JSONObject jsonObject=(JSONObject)object;
	  String BROWSER=jsonObject.get("browser").toString();
	  String URL=jsonObject.get("url").toString();
	  String USERNAME=jsonObject.get("username").toString();
	  String PASSWORD=jsonObject.get("password").toString();
	  
	  //Generate randomNumber
	  Random random=new Random();
	  int randomInt=random.nextInt(1000);
	  
	  //read testScriptData from EXCEL sheet
	  FileInputStream fis=new FileInputStream("C:\\Users\\User\\Desktop\\ExcellDataForSelenium\\textScriptData.xlsx");
	  Workbook wb=WorkbookFactory.create(fis);
	  Sheet sheet=wb.getSheet("org");
	  Row row=sheet.getRow(1);
	  String data=row.getCell(2).getStringCellValue()+randomInt;
	  wb.close();
	  
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
	   
	  
		driver.quit();
	  
	  
  }
}
