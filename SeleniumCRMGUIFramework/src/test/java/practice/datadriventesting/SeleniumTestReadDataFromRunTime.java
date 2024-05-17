package practice.datadriventesting;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class SeleniumTestReadDataFromRunTime {
//	public static void main(String[] args) {
//
//	}
	
	@Test
	public void seleniumCRMOrgTest() throws Throwable {
		//Read commonData from CMD line
		String BROWSER=System.getProperty("browser");
		String URl=System.getProperty("url");
		String USERNAME=System.getProperty("username");
		String PASSWORD=System.getProperty("password");
		
		//generate randomNum
		Random random=new Random();
		int randNum=random.nextInt(1000);
		
		//read testScriptData from Exel
		FileInputStream fis1=new FileInputStream("C:\\Users\\User\\Desktop\\ExcellDataForSelenium\\textScriptData.xlsx");
		Workbook wb=WorkbookFactory.create(fis1);
		Sheet sheet=wb.getSheet("org");
		Row row=sheet.getRow(1);
		String cellData=row.getCell(2).getStringCellValue()+randNum;
		wb.close();
		
		WebDriver driver=null;
		
		if(BROWSER.equals("firefox")) {
			driver=new FirefoxDriver();
		}else if(BROWSER.equals("chrome")) {
			driver=new ChromeDriver();
		}else if(BROWSER.equals("edge")){
			driver=new EdgeDriver();
		}else {
			driver=new ChromeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("http://localhost:8888/");
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.linkText("Organizations")).click();
		
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(cellData);
		driver.findElement(By.name("button")).click();
		
		WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		System.out.println(ele.isDisplayed());
		
		driver.quit();
	}
}
