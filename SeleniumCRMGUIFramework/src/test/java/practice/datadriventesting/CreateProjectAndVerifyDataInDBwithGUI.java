package practice.datadriventesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.mysql.jdbc.Driver;

public class CreateProjectAndVerifyDataInDBwithGUI {
	// url="http://106.51.90.215:8084/welcome"
	// http://106.51.90.215:8084/
	public static void main(String[] args) throws InterruptedException, Throwable {
		Random random = new Random();
		int randNum = random.nextInt(1000);

		
		
		
		// create project in GUI using selenium
		String projectName = "FaceBook_" + randNum;
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		driver.get("http://106.51.90.215:8084/");

		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();

		driver.findElement(By.linkText("Projects")).click();

		driver.findElement(By.xpath("//span[text()='Create Project']")).click();

		driver.findElement(By.name("projectName")).sendKeys(projectName);
		driver.findElement(By.name("createdBy")).sendKeys("shiva");

		Select select = new Select(driver.findElement(By.xpath("(//select[@name='status'])[2]")));
		select.selectByValue("Created");
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		// verify the project in BackEnd [DB] using JDBC
		boolean flag = false;
		Driver driver1 = new Driver();
		DriverManager.registerDriver(driver1);
		Connection connection = DriverManager.getConnection("jdbc:mysql://106.51.90.215:3333/projects", "root@%",
				"root");
		Statement statement = connection.createStatement();
		ResultSet resultset = statement.executeQuery("select * from project");
		while (resultset.next()) {
			String acutalData = resultset.getString(4);
			if (acutalData.equals(projectName)) {
				flag = true;
				System.out.println(projectName + " is available===PASS");
			}
		}

		if (flag == false) {
			System.out.println(projectName + " is not available===Fail");
		}

		driver.quit();
	}
}
