package com.w2a.listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.relevantcodes.extentreports.LogStatus;
import com.w2a.base.TestBase;
import com.w2a.utilities.TestUtilities;

public class CustomListeners extends TestBase implements ITestListener {

	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult arg0) {
		
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		try {
			TestUtilities.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		test.log(LogStatus.FAIL, arg0.getName().toUpperCase()+" Failed with exception: "+arg0.getThrowable());
		test.log(LogStatus.FAIL, test.addScreenCapture(TestUtilities.screenshotName));
		
		Reporter.log("Click on the image below to see the full size:");
		Reporter.log("<br><a target='_blank' href="+TestUtilities.screenshotName+"><img src="+TestUtilities.screenshotName+" height=200 width=350></img></a>");
		rep.endTest(test);
		rep.flush();
	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestStart(ITestResult arg0) {
		
		test = rep.startTest(arg0.getName().toUpperCase());
		//Runmodes - Y
		if(!TestUtilities.isTestRunnable(arg0.getName(), excel))	{
			test.log(LogStatus.ERROR, "Skipping the test"+arg0.getName().toUpperCase()+"as the Run mode is NO");
			throw new SkipException("Skipping the test"+arg0.getName().toUpperCase()+"as the Run mode is NO");
		}
	}

	public void onTestSuccess(ITestResult arg0) {
		
		test.log(LogStatus.PASS, arg0.getName().toUpperCase()+" PASS");
		rep.endTest(test);
		rep.flush();
	}

}
