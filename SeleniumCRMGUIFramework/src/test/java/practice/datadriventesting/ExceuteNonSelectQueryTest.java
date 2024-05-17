package practice.datadriventesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class ExceuteNonSelectQueryTest {
	public static void main(String[] args) throws Throwable {
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
		Statement statement=connection.createStatement();
		String sqlQuery="insert into project values('varun','kumar',34,'male')";
		int result=statement.executeUpdate(sqlQuery);
		System.out.println(result);
		connection.close();
				
				
	}
}
