package practice.datadriventesting;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CreateOrganizationTest_Assignment {

	public static void main(String[] args) throws IOException {
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
	    driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
	    driver.findElement(By.name("accountname")).sendKeys("AB_03");
	    driver.findElement(By.name("button")).click();
        driver.quit();
	}

}
