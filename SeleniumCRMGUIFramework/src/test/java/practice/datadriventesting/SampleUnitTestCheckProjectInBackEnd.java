package practice.datadriventesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;

public class SampleUnitTestCheckProjectInBackEnd {
	
	@Test
	public void sampleTest() throws Throwable {
		String expectedData="rama";
		boolean flag=false;
		Driver driver=new Driver();
		DriverManager.registerDriver(driver);
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root");
		Statement statement=connection.createStatement();
		ResultSet resultset=statement.executeQuery("select * from project1");
	   while(resultset.next()) {
		   String acutalData=resultset.getString(1);
		   //System.out.println(acutalData);
		   if(acutalData.equals(expectedData)) {
			   flag=true;
			   System.out.println(expectedData+" is available===PASS");
		   }
	   }
	   
	   if(flag==false) {
		   System.out.println(expectedData+" is available===Fail");
		   Assert.fail();
	   }
	}
}
