package practice.advcancereporting;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class SampleReportTest {

	ExtentReports report;
	@BeforeSuite
	public void congifBS() {
		ExtentSparkReporter spark=new ExtentSparkReporter("./AdvanceReport/report1.html");
		spark.config().setDocumentTitle("CRM Test Suite Result");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);
		
		 report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-10");
		report.setSystemInfo("BROWSER", "chrome-100");
	}
	
	@AfterSuite
	public void configAS() {
		report.flush();
	}
	@Test
	public void createContactTest() {
		
		ExtentTest test=report.createTest("createContactTest");
		
		test.log(Status.INFO, "login to app");
		test.log(Status.INFO, "navigate to contact page");
		test.log(Status.INFO, "create contact");
		
		if("HDFC".equals("HDyFC")) {
			test.log(Status.PASS, "contact is created");
		}else{
			test.log(Status.FAIL, "contact is not created");
		}
		
		test.log(Status.INFO, "logout from app");	
	}
	
	@Test
	public void createContactWithPhoneNumberTest() {
		
		ExtentTest test=report.createTest("createContactTest");
		
		test.log(Status.INFO, "login to app");
		test.log(Status.INFO, "navigate to contact page");
		test.log(Status.INFO, "create contact");
		
		if("HDFC".equals("HDFC")) {
			test.log(Status.PASS, "contact with phonenumber is created");
		}else{
			test.log(Status.FAIL, "contact with phonenumbe is not created");
		}
		
		test.log(Status.INFO, "logout from app");	
	}
}
