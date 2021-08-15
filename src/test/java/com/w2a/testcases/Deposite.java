package com.w2a.testcases;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.w2a.base.TestBase;

public class Deposite extends TestBase {

	@Test(dataProvider="getData")
	public void makeDeposite(String sum, String monthdep){
		//Click on month deposite
		driver.findElement(By.cssSelector(OR.getProperty("depositeinfo"))).click();
		driver.findElement(By.cssSelector(OR.getProperty("updep"))).click();
		driver.findElement(By.cssSelector(OR.getProperty("depsum"))).sendKeys();
		driver.findElement(By.cssSelector(OR.getProperty("monthdep"))).sendKeys();
	}
	
	@DataProvider
	public Object[][] getData(){
		
		String sheetName = "Deposite";
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
		log.info(cols);
		log.info(rows);
		
		Object[][] data = new Object[rows-1][cols];
		
		for (int rowNum = 2; rowNum <= rows; rowNum++){
			
			for (int colNum = 0; colNum < cols; colNum++){
				
				data[rowNum - 2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
				
			}
			
		}
		return data;
	}
}
