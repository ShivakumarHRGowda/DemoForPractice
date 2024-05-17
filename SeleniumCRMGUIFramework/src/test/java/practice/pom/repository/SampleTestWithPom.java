package practice.pom.repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class SampleTestWithPom {

	//for using elements in main method make referenceVar and to
	//use in testNG not required to make variables static
	@FindBy(name = "user_name")
	static WebElement ele1;

	@FindBy(name = "user_password")
	static WebElement ele2;

	@FindBy(id = "submitButton")
	static WebElement ele3;

//	@Test
//	public void sample() {
//		WebDriver driver = new ChromeDriver();
//		driver.get("http://localhost:8888");
//
//		SampleTestWithPom s=PageFactory.initElements(driver, SampleTestWithPom.class);
//		s.ele1.sendKeys("admin");
//		s.ele2.sendKeys("admin");
//
//		driver.navigate().refresh();
//		s.ele1.sendKeys("admin");
//		s.ele2.sendKeys("admin");
//		s.ele3.click();
//
//		driver.quit();
//	}
	
	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8888");

		SampleTestWithPom s=PageFactory.initElements(driver, SampleTestWithPom.class);
		s.ele1.sendKeys("admin");
		s.ele2.sendKeys("admin");

		driver.navigate().refresh();
		s.ele1.sendKeys("admin");
		s.ele2.sendKeys("admin");
		s.ele3.click();

		driver.quit();
	}
}
