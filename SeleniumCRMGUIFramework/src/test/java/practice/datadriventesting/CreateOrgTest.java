package practice.datadriventesting;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateOrgTest {
	public static void main(String[] args) throws Throwable {
		FileInputStream fis = new FileInputStream("C:\\\\Users\\\\User\\\\Desktop\\\\commandata.properties");
	    Properties pObj=new Properties();
	    pObj.load(fis);
	    String BROWSER=pObj.getProperty("browser");
	    String URL=pObj.getProperty("url");
	    String USERNAME=pObj.getProperty("username");
	    String PASSWORD=pObj.getProperty("password");
	    
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
	    
	    Actions actions=new Actions(driver);
	    actions.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).click().build().perform();
	    driver.findElement(By.linkText("Sign Out")).click();
	    driver.quit();
	}
}
