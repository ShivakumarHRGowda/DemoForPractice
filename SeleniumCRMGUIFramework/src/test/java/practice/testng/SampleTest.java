package practice.testng;

import org.testng.annotations.Test;

public class SampleTest {
    
	@Test(enabled = false)
	public void test() {
	   System.out.println("test executed");
	}
	
	@Test(invocationCount = 3)
	public void test1() {
	   System.out.println(10/0);
	}
	
	
}
