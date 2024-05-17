package practice.datadriventesting;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
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
import org.openqa.selenium.interactions.Actions;

public class CreateOrgTestUsingExcelAndPropertyFile {
	public static void main(String[] args) throws Exception {
        //propertyfile-reading common data
		FileInputStream fis=new FileInputStream("C:\\Users\\User\\Desktop\\commandata.properties");
		Properties pObj=new Properties();
		pObj.load(fis);
		String BROWSER=pObj.getProperty("browser");
		String URL=pObj.getProperty("url");
		String USERNAME=pObj.getProperty("username");
		String PASSWORD=pObj.getProperty("password");
		//System.out.println(BROWSER+" "+URL+" "+USERNAME+" "+PASSWORD);
		
		//generate the random number
		Random ran = new Random();
		int randNum = ran.nextInt(1000);
		
		//Excel-reading test script data
		FileInputStream fis1=new FileInputStream("C:\\Users\\User\\Desktop\\ExcellDataForSelenium\\textScriptData.xlsx");
		Workbook wb=WorkbookFactory.create(fis1);
		Sheet sheet=wb.getSheet("org");
		Row row=sheet.getRow(1);
		String cellData=row.getCell(2).getStringCellValue()+randNum;
		//System.out.println(cellData);
		wb.close();
		
		WebDriver driver=null;
		
		if(BROWSER.equals("chrome")) {
			driver=new ChromeDriver();
		}else if(BROWSER.equals("firefox")) {
			driver=new FirefoxDriver();
		}else if(BROWSER.equals("edge")) {
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
		Thread.sleep(2000);
		Actions actions=new Actions(driver);
		 actions.moveToElement(ele).click().perform();
		 driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		
		driver.close();
	}
}
