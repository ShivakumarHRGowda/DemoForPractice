package com.crm.comcast.generic.baseutility;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseClass {
	
	
	
	@BeforeSuite
	public void configBS() {
		System.out.println("==== DB connection and Report configuration");
	}
	
	@BeforeClass
	public void congigAC() {
		System.out.println("launch browser");
	}
	
	@BeforeMethod
	public void congifBM() {
		System.out.println("login");
	}
	
	@AfterMethod
	public void configAM() {
		System.out.println("logout");
	}
	@AfterClass
	public void configBC() {
		System.out.println("close browser");
	}
	@AfterSuite
	public void configAS() {
		System.out.println("==== close DB connection and Report backup");
	}
}
