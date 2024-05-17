package practice.testng;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateContact_DP_Test1 {

	@Test(dataProvider = "getData")
	public void createContactTest(String firstName,String lastName,long phonenumber) {
		System.out.println("FistName:"+firstName+" , LastName:"+lastName+" , Phonenumber:"+phonenumber);
	}
	
	@DataProvider
	public Object[][] getData(){
		Object[][] objArr=new Object[3][3];
		objArr[0][0]="Deepak";
		objArr[0][1]="HR";
		objArr[0][2]=9876565656L;
		
		objArr[1][0]="Shivakumar";
		objArr[1][1]="HR";
		objArr[1][2]=8876565656L;
		
		objArr[2][0]="Pavam";
		objArr[2][1]="Kumar";
		objArr[2][2]=7876565656L;
		
		return objArr;
		
	}
	
	
}
