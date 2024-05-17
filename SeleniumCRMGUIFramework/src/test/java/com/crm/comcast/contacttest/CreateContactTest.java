package com.crm.comcast.contacttest;

import org.testng.annotations.Test;

import com.crm.comcast.generic.baseutility.BaseClass;

public class CreateContactTest extends BaseClass {

	@Test
	public void createContact() {
		System.out.println("create contact and verify");
	}
	
	@Test
	public void createContactWithDate() {
	System.out.println("create contact with date and verify");
	}
}
