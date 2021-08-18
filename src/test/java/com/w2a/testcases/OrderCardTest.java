package com.w2a.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.w2a.base.TestBase;

public class OrderCardTest extends TestBase {

	@Test
	public void orderacard() throws InterruptedException	{
		
		log.info("Inside Login Test.");
		driver.findElement(By.cssSelector(OR.getProperty("bmlBtn"))).click();
		Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("sendrequest"))), "Login not sucessful");
		log.info("Login successfully!");
	}
	
}
