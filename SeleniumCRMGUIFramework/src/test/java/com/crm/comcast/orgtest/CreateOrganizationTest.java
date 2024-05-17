package com.crm.comcast.orgtest;

import org.testng.annotations.Test;

import com.crm.comcast.generic.baseutility.BaseClass;

public class CreateOrganizationTest extends BaseClass {
	@Test
	public void createOrganization() {
		System.out.println("create Organization and verify");
	}
	
	@Test
	public void createOrganizationWithIndustries() {
	System.out.println("create Organization With Industries and verify");
	}
}
