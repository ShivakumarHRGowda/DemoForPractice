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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CreateOrganizationWithIndustriesTest {
	public static void main(String[] args) throws IOException, InterruptedException {
		FileInputStream fis = new FileInputStream("C:\\Users\\User\\Desktop\\Data\\commandata.properties");
		Properties pObj = new Properties();
		pObj.load(fis);
		String BROWSER = pObj.getProperty("browser");
		String URL = pObj.getProperty("url");
		String USERNAME = pObj.getProperty("username");
		String PASSWORD = pObj.getProperty("password");

		// generate the random number
		Random ran = new Random();
		int randNum = ran.nextInt(1000);

		// Excel-reading test script data
		FileInputStream fis1 = new FileInputStream("C:\\Users\\User\\Desktop\\Data\\textScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sheet = wb.getSheet("org");
		Row row = sheet.getRow(4);
		String orgName = row.getCell(2).getStringCellValue() + randNum;
		String industries = row.getCell(3).getStringCellValue();
		String type = row.getCell(4).getStringCellValue();
		wb.close();

		WebDriver driver = null;
		// cross browser testing
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		}

		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		driver.findElement(By.linkText("Organizations")).click();

		// TC_02
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		
		WebElement wbsele=driver.findElement(By.name("industry"));
		Select select=new Select(wbsele);
		select.selectByVisibleText(industries);
		
		WebElement wbsele2=driver.findElement(By.name("accounttype"));
		Select select2=new Select(wbsele2);
		select2.selectByVisibleText(type);
		
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();

		Thread.sleep(3000);

		// verify Header message expected result
		
		// verify Header orgName info Expected Result
		String actualIndustrie = driver.findElement(By.id("dtlview_Industry")).getText();
		if (actualIndustrie.contains(orgName) ) {
			System.out.println(industries + " information is Created==PASS");
		} else {
			System.out.println(industries+ " information is Created==FAIL");
		}
		
		String actualType = driver.findElement(By.id("dtlview_Type")).getText();
		if (actualType.contains(type) ) {
			System.out.println(type + " information is Created==PASS");
		} else {
			System.out.println(type+ " information is Created==FAIL");
		}

		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).click()
				.build().perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
	}
}
