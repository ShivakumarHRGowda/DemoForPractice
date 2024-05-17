package practice.testng;

import java.io.FileInputStream;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GetProductInfoTest {
	@Test(dataProvider = "getData")
	public void getProductInfoTest(String brandName, String productName) {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.amazon.in/");

		// search product
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandName, Keys.ENTER);

		// capture product info
		String x = "//span[text()='"+productName+"']/../../../../div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/a/span/span[2]/span[2]";
		String price = driver.findElement(By.xpath(x)).getText();
		System.out.println("ProductName:"+productName+"  price:"+price);
		 driver.quit();
	}
	
	@DataProvider
	public Object[][] getData() throws Throwable{
		 FileInputStream fis=new FileInputStream("C:\\Users\\User\\Desktop\\Data\\productInfo.xlsx");
		 Workbook wb=WorkbookFactory.create(fis);
		 Sheet sheet=wb.getSheet("product1");
		 Row row=sheet.getRow(1);
		 
	     int rowCount= sheet.getLastRowNum();
		Object[][] objArr=new Object[rowCount][3];
		for(int i=0;i<rowCount;i++) {
			objArr[i][0]=row.getCell(0).getStringCellValue();
			objArr[i][1]=row.getCell(1).getStringCellValue();	
		}
		return objArr;
	}
}
