package TestFW.TestComponent;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{

	int maxRun=2;
	int count=0;
	@Override
	public boolean retry(ITestResult result) {
		if(count<maxRun) {
			count++;
			return true;
		}
		return false;
	}

}
