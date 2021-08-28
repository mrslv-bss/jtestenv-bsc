package com.w2a.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.w2a.base.TestBase;
import com.w2a.utilities.TestUtilities;

public class DepositeTest extends TestBase {

	@Test (dataProviderClass=TestUtilities.class,dataProvider="dp")
	public void makeDeposite(String sum, String monthdep) throws InterruptedException, IOException	{
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		
		// Click on month deposite
		Reporter.log("Go to deposite page!");
		click("depositeinfo_CSS");
		
		// Deposit Replenishment checkbox
		Reporter.log("<br>Check up month dep checkbox!");
		click("updep_CSS");

		verifyEquals("asd","asd");
		// When we try remove at the same time all 4 numbers from input, 1 number remains anyway
		// TODO Rebuild to .clear
		click("depsum_CSS");
		sendKeys("depsum_CSS", "\b\b\b");
		click("depsum_CSS");
		sendKeys("depsum_CSS", "\b");

		
		// Deposite Amount
		Reporter.log("<br>Dep amount: "+sum);
		sendKeys("depsum_CSS", sum);
		
		// Monthly top up amount
		Reporter.log("<br>Month dep amount: "+monthdep);
		sendKeys("monthdep_CSS", monthdep);
		Reporter.log("<br>Fill inputs successfully!");
		
		driver.close();
//		Assert.fail("Unsuccessful login!");

	}
	
}
