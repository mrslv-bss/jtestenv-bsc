package com.w2a.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.w2a.base.TestBase;

public class DepositeTest extends TestBase {

	@Test (dataProvider="getData")
	public void makeDeposite(String sum, String monthdep) throws InterruptedException	{
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		
		// Click on month deposite
		Reporter.log("Go to deposite page!");
		click("depositeinfo_CSS");
		
		// Deposit Replenishment checkbox
		Reporter.log("<br>Check up month dep checkbox!");
		click("updep_CSS");

		
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
		
//		Assert.fail("Unsuccessful login!");
	}
	
	@DataProvider
	public Object[][] getData(){
		
		String sheetName = "Deposite";
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
		log.info(rows);
		log.info(cols);
		Object[][] data = new Object[rows-1][cols];
		
		for (int rowNum = 2; rowNum <= rows; rowNum++){
			for (int colNum = 0; colNum < cols; colNum++){
				
				data[rowNum - 2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
			}
			
		}
		return data;
	}
}
