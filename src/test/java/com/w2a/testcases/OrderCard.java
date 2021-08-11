package com.w2a.testcases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.w2a.base.TestBase;

public class OrderCard extends TestBase {

	@Test
	public void orderacard() throws InterruptedException	{
		
		log.info("Inside Login Test.");
		driver.findElement(By.cssSelector(OR.getProperty("bmlBtn"))).click();
		Thread.sleep(3000);
		
		log.info("Login successfully!");
	}
	
}
