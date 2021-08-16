package com.w2a.testcases;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.w2a.base.TestBase;

public class Deposite extends TestBase {

	@Test (dataProvider="getData")
	public void makeDeposite(String sum, String monthdep) throws InterruptedException	{
		// Click on month deposite
		driver.findElement(By.cssSelector(OR.getProperty("depositeinfo"))).click();
		
		// Deposit Replenishment checkbox
		driver.findElement(By.cssSelector(OR.getProperty("updep"))).click();

		// When we try remove at the same time all 4 numbers from input, 1 number remains anyway
		// TODO Rebuild to .clear
		driver.findElement(By.cssSelector(OR.getProperty("depsum"))).click();
		driver.findElement(By.cssSelector(OR.getProperty("depsum"))).sendKeys("\b\b\b");
		driver.findElement(By.cssSelector(OR.getProperty("depsum"))).click();
		driver.findElement(By.cssSelector(OR.getProperty("depsum"))).sendKeys("\b");

		// Deposite Amount
		driver.findElement(By.cssSelector(OR.getProperty("depsum"))).sendKeys(sum);
		// Monthly top up amount
		driver.findElement(By.cssSelector(OR.getProperty("monthdep"))).sendKeys(monthdep);
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
