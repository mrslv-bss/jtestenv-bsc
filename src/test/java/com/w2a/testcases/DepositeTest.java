package com.w2a.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.w2a.base.TestBase;
import com.w2a.utilities.TestUtilities;
import java.util.Hashtable;

public class DepositeTest extends TestBase {

	@Test (dataProviderClass=TestUtilities.class,dataProvider="dp")
	public void depositeTest(Hashtable<String,String> data) throws InterruptedException, IOException	{
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		
		test.log(LogStatus.PASS, "Go to main page");
		click("mono_logo_CSS");
		
		// Click on month deposite
		test.log(LogStatus.PASS, "Go to deposite page");
		click("depositeinfo_CSS");
		
		// Deposit Replenishment checkbox
		click("updep_CSS");
		
		// When we try remove at the same time all 4 numbers from input, 1 number remains anyway
		// TODO Rebuild to .clear
		test.log(LogStatus.WARNING, "TODO: FIX");
		click("depsum_CSS");
		sendKeys("depsum_CSS", "\b\b\b");
		click("depsum_CSS");
		sendKeys("depsum_CSS", "\b");
		test.log(LogStatus.WARNING, "When we try remove at the same time all 4 numbers from input, 1 number remains anyway");
		
		// Deposite Amount
		sendKeys("depsum_CSS", data.get("sum"));
		
		// Monthly top up amount
		sendKeys("monthdep_CSS", data.get("monthdep"));
		
//		driver.close();
//		Assert.fail("Unsuccessful login!");

	}
	
}
