package TestFW.TestComponent;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import TestFW.Pages.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landing;
	
	public void init() {
		
		String browserName = System.getProperty("browser");
		if(browserName.equalsIgnoreCase("chrome")) {
		driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("chrome")) {
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	public List<HashMap<String, String>> getJsonData(String filepath) throws IOException {
		String jsondata = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsondata, new TypeReference<List<HashMap<String,String>>>(){} );
		return data;
	}
	
	public String getScreenshot(String testcasename, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File src = new File("D:\\Selenium\\Scripts\\PracticeFramework\\Reports"+testcasename+".png");
		FileUtils.copyFile(source, src);
		return "D:\\Selenium\\Scripts\\PracticeFramework\\Reports"+testcasename+".png";
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage lauchApplication() {
		init();
		landing = new LandingPage(driver);
		landing.goToUrl();
		return landing;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.quit();
	}

}
