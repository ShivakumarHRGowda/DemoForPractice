package practice.testng;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.Test;


public class ScreenShot {

	@Test
	public void takeScreenShot() throws IOException {
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https:www.amazon.com");
		EventFiringWebDriver edriver=new EventFiringWebDriver(driver);
		File srcFile =edriver.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File("./screenshots/screenshot.png"));
	    driver.quit();
	}
}
