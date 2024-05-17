package practice.orgtest;

import java.io.FileInputStream;
import java.io.IOException;
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

public class CreateOrganizationTest {
	public static void main(String[] args) throws IOException, InterruptedException  {
		FileInputStream fis = new FileInputStream("C:\\Users\\User\\Desktop\\Data\\commandata.properties");
	    Properties pObj=new Properties();
	    pObj.load(fis);
	    String BROWSER=pObj.getProperty("browser");
	    String URL=pObj.getProperty("url");
	    String USERNAME=pObj.getProperty("username");
	    String PASSWORD=pObj.getProperty("password");
	    
	  //generate the random number
	  		Random ran = new Random();
	  		int randNum = ran.nextInt(1000);
	  		
	  		//Excel-reading test script data
	  		FileInputStream fis1=new FileInputStream("C:\\Users\\User\\Desktop\\Data\\textScriptData.xlsx");
	  		Workbook wb=WorkbookFactory.create(fis1);
	  		Sheet sheet=wb.getSheet("org");
	  		Row row=sheet.getRow(1);
	  		String orgName=row.getCell(2).getStringCellValue()+randNum;
	  		//System.out.println(orgName);
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
	    
	   // TC_01
	    driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
	    
		Thread.sleep(3000);
		
		//verify Header message expected result
		String headerInfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(headerInfo.contains(orgName)) {
			System.out.println(orgName+" is Created==PASS");
		}else {
			System.out.println(orgName+" is Created==FAIL");
		}
		
		//verify  Header orgName info Expected Result
		String actualOrgNameInfo=driver.findElement(By.id("dtlview_Organization Name")).getText();
		if(actualOrgNameInfo.contains(orgName)) {
			System.out.println(orgName+" information is Created==PASS");
		}else {
			System.out.println(orgName+" information is Created==FAIL");
		}
		
		//TC_02
		
		
	    Actions actions=new Actions(driver);
	    actions.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).click().build().perform();
	    driver.findElement(By.linkText("Sign Out")).click();
	    driver.quit();
	}
}
