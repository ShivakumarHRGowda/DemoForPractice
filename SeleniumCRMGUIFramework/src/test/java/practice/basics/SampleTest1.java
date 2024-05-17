package practice.basics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.mysql.cj.jdbc.Driver;

public class SampleTest1 {
	public static void main(String[] args) throws Throwable {
//		Connection connection = null;
//		try {
//			// step 1: load/register the database driver
//			Driver driverRef = new Driver();
//			DriverManager.registerDriver(driverRef);
//
//			// step 2: connect to database
//			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
//			System.out.println("=====DONE=====");
//
//			// step 3:create sql statement
//			Statement statement = connection.createStatement();
//
//			// step 4:execute SQL query and get result
//			ResultSet result = statement.executeQuery("select * from project");
//
//			while (result.next()) {
//				System.out.println(result.getString(1) + "\t" + result.getString(2) + "\t" + result.getString(3) + "\t"
//						+ result.getString(4));
//			}
//
//		} catch (Exception e) {
//			System.out.println("exception handled");
//		} finally {
//
//			// step5 : close the connection
//			connection.close();
//			System.out.println("connection closed");
//		}
		
		System.out.println(getRequiredDateYYYYMMDD(30));
	}
	public static String getRequiredDateYYYYMMDD(int days) {
		Date dateObj=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		sdf.format(dateObj);
		Calendar calendar=sdf.getCalendar();
		calendar.add(Calendar.DAY_OF_MONTH, days);
		String reqDate=sdf.format(calendar.getTime());
		return reqDate;	
	}
}
