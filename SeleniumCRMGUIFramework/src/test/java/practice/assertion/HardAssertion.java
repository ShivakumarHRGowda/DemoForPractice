package practice.assertion;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class HardAssertion {

	@Test
	public void homePageText(Method mtd) {
		SoftAssert assertTest=new SoftAssert();
		System.out.println(mtd.getName()+" Test Start");
		System.out.println("step-1");
		assertTest.assertEquals("Home", "Home_page");
		System.out.println("step-2");
		assertTest.assertEquals("Home_page", "Home_page");
		System.out.println("step-3");
		assertTest.assertAll();
		System.out.println(mtd.getName()+" Test End");
	}
	
	@Test
	public void homePageLogo(Method mtd) {
		SoftAssert assertTest=new SoftAssert();
		System.out.println(mtd.getName()+" Test Start");
		System.out.println("step-1");
		assertTest.assertTrue(true);
		System.out.println("step-2");	
		assertTest.assertAll();
		System.out.println(mtd.getName()+" Test End");
	}
}
