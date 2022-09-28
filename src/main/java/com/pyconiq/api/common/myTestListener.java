package com.pyconiq.api.common;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;



public class myTestListener implements ITestListener {
	public static ExtentTest test;
	public static ExtentReports report;
	String reportPath = System.getProperty("user.dir")+"\\test-output\\ExtentReportResults.html";
	

	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		report.endTest(test);
		report.flush();
	}

	@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		report = new ExtentReports(reportPath);
		test = report.startTest("Hotel Booking");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		test.log(LogStatus.FAIL,"Test Failed : "+arg0.getName());
	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		// TODO Auto-generated method stub
		test.log(LogStatus.FAIL,"Test Failed : "+arg0.getName());
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		test.log(LogStatus.SKIP,"Test Skipped : "+arg0.getName());
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
		test.log(LogStatus.PASS,"Test Passed : "+arg0.getName());
	}
}
