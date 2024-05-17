package practice.advcancereporting;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentsReportWithScreenshot {
	@Test
	public void takeSCreenShot() {
      WebDriver driver=new ChromeDriver();
      driver.get("http://localhost:8888");
      
      ExtentSparkReporter spark=new ExtentSparkReporter("./AdvanceReport/reportScreenshot.html");
      spark.config().setDocumentTitle("attach screen shot to extent report");
      spark.config().setReportName("Screenshot report");
      spark.config().setTheme(Theme.DARK);
      
      ExtentReports report=new ExtentReports();
      report.attachReporter(spark);
      report.setSystemInfo("OS", "Windows-10");
      report.setSystemInfo("BROWSER", "Chrome-100");
      
      ExtentTest test=report.createTest("takeSCreenShot");
      
      TakesScreenshot screenshot=(TakesScreenshot)driver;
      String fileSRC=screenshot.getScreenshotAs(OutputType.BASE64);
      
      if("Login".equals("HomePage")) {
    	  test.log(Status.PASS, " element is matching");
      }else {
    	  test.log(Status.FAIL, "element is not matching");
    	  test.addScreenCaptureFromBase64String(fileSRC, "ErrorFile");
      }
      report.flush();
      driver.close();
	}
}
