package practice.advcancereporting;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class SampleAdvanceReporting_1 {

	@Test
	public void testAdvanceReporting() {
		ExtentSparkReporter spark=new ExtentSparkReporter("./AdvanceReport/report2.html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Application");
		spark.config().setTheme(Theme.DARK);
		
		ExtentReports report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS","Windows-10");
		report.setSystemInfo("BROWSER","Chrome-100");
		
		ExtentTest test=report.createTest("testAdvanceReporting");
		if("HDFC".equals("HFDC")) {
			test.log(Status.PASS, " data equals");
		}else {
			test.log(Status.FAIL, " data not equals");
		}
		
		report.flush();
	}
}
