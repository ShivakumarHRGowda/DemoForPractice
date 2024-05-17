package practice.reporterclass;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class ReporterClassTestNg {

	@Test
	public void testScripts() {
		Assert.assertEquals("Home", "Home");
		Reporter.log("test validation done",true);
	}
}
