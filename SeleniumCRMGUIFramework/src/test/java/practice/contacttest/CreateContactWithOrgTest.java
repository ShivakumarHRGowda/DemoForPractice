package practice.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

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

public class CreateContactWithOrgTest {
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
		Row row = sheet.getRow(7);
		String orgName = row.getCell(2).getStringCellValue() + randNum;
		String lastName = row.getCell(3).getStringCellValue() + randNum;
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
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();

		Thread.sleep(3000);
		// verify Header message expected result
		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerInfo.contains(orgName)) {
			System.out.println(orgName + " is Created==PASS");
		} else {
			System.out.println(orgName + " is Created==FAIL");
		}

		// navigate to contact module
		driver.findElement(By.linkText("Contacts")).click();

		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys(lastName);

		// click on lookup window '+'
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();

		// switch to child window
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			String windowID=it.next();
           driver.switchTo().window(windowID);
           String actualURL=driver.getCurrentUrl();
           if(actualURL.contains("module=Accounts")) {
        	   break;
           }
		}

		driver.findElement(By.id("search_txt")).sendKeys(orgName);
		driver.findElement(By.name("search")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();

		// switch to parent window
		Set<String> set1 = driver.getWindowHandles();
		Iterator<String> it1 = set1.iterator();
		while (it1.hasNext()) {
			String windowID=it1.next();
           driver.switchTo().window(windowID);
           String actualURL=driver.getCurrentUrl();
           if(actualURL.contains("module=Contacts")) {
        	   break;
           }
		}
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();

		Thread.sleep(3000);

		// verify Header lastName info Expected Result
		String actorgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		if (actorgName.trim().contains(orgName)) {
			System.out.println(orgName + " information is Created==PASS");
		} else {
			System.out.println(orgName + " information is Created==FAIL");
		}

		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).click()
				.build().perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
	}
}
