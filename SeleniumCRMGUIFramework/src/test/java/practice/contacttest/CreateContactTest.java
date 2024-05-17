package practice.contacttest;


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

public class CreateContactTest{
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
		Sheet sheet = wb.getSheet("contact");
		Row row = sheet.getRow(1);
		String lastName = row.getCell(2).getStringCellValue() + randNum;
			
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
        //TC_04
		driver.findElement(By.linkText("Contacts")).click();

		
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys(lastName);
	
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();

		Thread.sleep(3000);


		// verify Header phoneNumber info Expected Result
		String actualLatName = driver.findElement(By.id("dtlview_Last Name")).getText();
		if (actualLatName.contains(lastName)) {
			System.out.println(lastName + " information is Created==PASS");
		} else {
			System.out.println(lastName + " information is Created==FAIL");
		}
		
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).click()
				.build().perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
	}
}
