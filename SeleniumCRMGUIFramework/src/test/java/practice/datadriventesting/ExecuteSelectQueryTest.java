package practice.datadriventesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class ExecuteSelectQueryTest {
	public static void main(String[] args) throws Throwable {
       //step 1: load/register the database driver
		Driver driverRef=new Driver();
	   DriverManager.registerDriver(driverRef);
	   
	   //step 2: connect to database
	   Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
	   System.out.println("=====DONE=====");
	   
	   //step 3:create sql statement
		Statement statement=connection.createStatement();
		
		//step 4:execute SQL query and get result
		ResultSet result = statement.executeQuery("select * from project");
//	    result.next();
//	    String data=result.getString(1);
//	    System.out.println(data);
		
		while(result.next()) {
			System.out.println(result.getString(1)+"\t"+result.getString(2)+"\t"+result.getString(3)+"\t"+result.getString(4));
		}
	    
	    //step5 : close the connection
	    connection.close();
	}
}
