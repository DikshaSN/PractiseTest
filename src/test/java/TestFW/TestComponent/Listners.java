package TestFW.TestComponent;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import TestFW.Resources.ExtentReportsC;

public class Listners extends BaseTest implements ITestListener{

	ExtentTest test;
	ExtentReports extent = ExtentReportsC.getReportObject();
	ThreadLocal<ExtentTest> extest = new ThreadLocal<ExtentTest> ();
	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extest.set(test);
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extest.get().log(Status.PASS, "Congratulations your Test is Success");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extest.get().fail(result.getThrowable());
		try {
			driver =(WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		String filepath = null;
		try {
			filepath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		extest.get().addScreenCaptureFromPath(filepath,result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
		
	}

}
