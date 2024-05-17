package practice.testng;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestScriptForBroforeMethodAnnotation {

	@BeforeMethod
	public void testBeforeMethod() {
		System.out.println("Before Method");
	}
	
	@Test
	public void test() {
		System.out.println("test method");
	}
	
	@Test
	public void test1() {
		System.out.println("test1 method");
	}
	
	@AfterMethod
	public void testAfterMethod() {
		System.out.println("after method");
	}
}
