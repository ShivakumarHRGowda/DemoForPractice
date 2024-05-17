package practice.testng;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateContact_DP_Test {

	@Test(dataProvider = "getData")
	public void createContactTest(String firstName,String lastName) {
		System.out.println("FistName:"+firstName+" , LastName:"+lastName);
	}
	
	@DataProvider
	public Object[][] getData(){
		Object[][] objArr=new Object[3][2];
		objArr[0][0]="Deepak";
		objArr[0][1]="HR";
		
		objArr[1][0]="Shivakumar";
		objArr[1][1]="HR";
		
		objArr[2][0]="Pavam";
		objArr[2][1]="Kumar";
		
		return objArr;
		
	}
	
	
}
