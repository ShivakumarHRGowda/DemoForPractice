package practice.datadriventesting;

import org.testng.annotations.Test;

public class ReadRuntimeMavenParameterTest {
 @Test
 public void runtimepametersTest() {
	 String URL=System.getProperty("url");
	 String BROWSER=System.getProperty("browser");
	 String USERNAME=System.getProperty("username");
	 String PASSWORD=System.getProperty("password");
	 
	 System.out.println("Env data==> URL ====>"+URL);
	 System.out.println("Env data==> BROWSER ====>"+BROWSER);
	 System.out.println("Env data==> USERNAME ====>"+USERNAME);
	 System.out.println("Env data==> PASSWORD ====>"+PASSWORD);
	 
 }
}
