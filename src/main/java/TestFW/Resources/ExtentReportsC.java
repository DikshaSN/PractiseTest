package TestFW.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsC {
	
	public static ExtentReports getReportObject() {
		String path ="D:\\Selenium\\Scripts\\PracticeFramework\\Reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Pratice Test Framework");
		reporter.config().setDocumentTitle("Practice Report");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Diksha Kamdi");
		return extent;
		
	}

}
