package practice.datadriventesting;

import java.io.FileInputStream;
import java.util.Properties;

public class SampleDataDrivenTesting {
	public static void main(String[] args) throws Exception {

		//step 1 : get the java representation object of the physical file
		FileInputStream fis=new FileInputStream("C:\\Users\\User\\Desktop\\commandata.properties");
		
		//step 2: using Properties class, load all the keys
		Properties pObj=new Properties();
		pObj.load(fis);
		
		//step 3: get the value based on key
		System.out.println(pObj.getProperty("browser"));
	}
}
