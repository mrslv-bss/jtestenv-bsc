package com.w2a.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.w2a.listeners.CustomListeners;
import com.w2a.utilities.ExcelReader;
import com.w2a.utilities.ExtentManager;
import com.w2a.utilities.TestUtilities;

public class TestBase {
	
	
	/*
	 * WebDriver
	 * Properties
	 * Logs
	 * ExtentReports
	 * DB
	 * Excel
	 * Mail
	 * ReportNG, ExtentReports
	 * Jenkins
	 * 
	 */
	
	
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger(TestBase.class.getName());
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\excel\\testdata.xlsx");
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	
	
	@BeforeSuite
	public void setUp()	{
		PropertyConfigurator.configure(System.getProperty("user.dir")+"\\src\\test\\java\\log4j.properties");
		if(driver==null)	{
			
			
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.info("Config file loaded.");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.info("OR config file loaded.");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(config.getProperty("browser").equals("firefox"))	{
				
				//System.setProperty("webdriver.gecko.driver", "gecho.exe")
				driver = new FirefoxDriver();
				
			} else if(config.getProperty("browser").equals("chrome"))	{
				
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\chromedriver.exe");
				driver = new ChromeDriver();
				log.info("Chrome launched.");
				
			} 
			
			driver.get(config.getProperty("testsiteurl"));
			log.info("Navigated to : "+config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
			
		}
		
	}
	
//	*
//	click('button_CSS')
//	*
	public void click(String Locator)	{
		
		if (Locator.endsWith("_CSS"))	{
			driver.findElement(By.cssSelector(OR.getProperty(Locator))).click();
		} else if (Locator.endsWith("_XPATH"))	{
			driver.findElement(By.xpath(OR.getProperty(Locator))).click();
		}
		test.log(LogStatus.INFO, "Click on: "+Locator);

	}
	
//	*
//	sendKeys('input_CSS','Hello')
//	*
	public void sendKeys(String Locator, String Value)	{
		
		if (Locator.endsWith("_CSS"))	{
			driver.findElement(By.cssSelector(OR.getProperty(Locator))).sendKeys(Value);
		} else if (Locator.endsWith("_XPATH"))	{
			driver.findElement(By.xpath(OR.getProperty(Locator))).sendKeys(Value);
		}
		test.log(LogStatus.INFO, "Typing in: "+Locator+" value as: "+Value);
	}
	
	static WebElement dropdown;
	
//	*
//	select('dropdown_CSS','element')
//	*
	public void select(String locator, String value){
		if (locator.endsWith("_CSS"))	{
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
		} else if (locator.endsWith("_XPATH"))	{
			dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));
		}
		Select tosel = new Select(dropdown);
		tosel.selectByVisibleText(value);
		
		test.log(LogStatus.INFO, "Selecting element: "+locator+" value as: "+value);
		
	}
	
	public boolean isElementPresent(By by)	{
		
		try{
			driver.findElement(by);
			return true;
		}catch(NoSuchElementException e){
			return false;
		}
		
	}
	
//	*
//	verifyEquals("asd","asd");
//	*
	public static void verifyEquals(String expected, String actual) throws IOException {

		try {

			Assert.assertEquals(actual, expected);

		} catch (Throwable t) {

			TestUtilities.captureScreenshot();
			// ReportNG
			Reporter.log("<br>" + "Verification failure : " + t.getMessage() + "<br>");
			Reporter.log("<a target=\"_blank\" href=" + TestUtilities.screenshotName + "><img src=" + TestUtilities.screenshotName
					+ " height=200 width=200></img></a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			// Extent Reports
			test.log(LogStatus.FAIL, "Verification failed with exception : " + t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(TestUtilities.screenshotName));
//			CustomListeners.testReport.get().log(Status.FAIL, " Verification failed with exception : " + t.getMessage());
//			CustomListeners.testReport.get().fail("<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>",
//					MediaEntityBuilder.createScreenCaptureFromPath(TestUtil.screenshotName)
//					.build());

		}

	}

	
	@AfterSuite
	public void tearDown()	{
		
		if(driver!=null){
			driver.quit();
		}
		
		log.info("Test execution completed.");
		
	}
}
