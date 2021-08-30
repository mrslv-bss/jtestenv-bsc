package com.w2a.testcases;

import java.util.Hashtable;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.w2a.base.TestBase;
import com.w2a.utilities.TestUtilities;

public class CreditCalcCard extends TestBase {

	@Test(dataProviderClass=TestUtilities.class,dataProvider="dp")
	public void creditCalcCard(Hashtable<String,String> data) throws InterruptedException {
		
		test.log(LogStatus.PASS, "Go to main page");
		click("mono_logo_CSS");
		
		// Cards selector not dropdown, so we cannot use: select(String locator, String value)
		test.log(LogStatus.PASS, "Expand card's selector");
		click("calc_card_unwarp_CSS");
		
		test.log(LogStatus.PASS, "Choose PLATINUM card");
		click("calc_card_CSS");
	}

}
