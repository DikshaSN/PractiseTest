package TestFW.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



import TestFW.Pages.CartPage;
import TestFW.Pages.CheckoutPage;
import TestFW.Pages.LandingPage;
import TestFW.Pages.ProductCatalogue;
import TestFW.TestComponent.BaseTest;
import TestFW.TestComponent.Retry;

public class Test1 extends BaseTest{
	
	@Test(dataProvider= "getData",retryAnalyzer=Retry.class)
	public void SubmitOrder(HashMap<String,String> input) throws InterruptedException {
		ProductCatalogue productPage  = landing.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> list =productPage.getProductList();
		productPage.addToCart(input.get("product"));
		CartPage cp =productPage.goToCart();
		Boolean match = cp.verifyCartitem(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkpage = cp.goToCheckoutPage();
		checkpage.SelectCountry("India");
		checkpage.submitOrder();
 
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String,String>> data =getJsonData("D:\\Selenium\\Scripts\\PracticeFramework\\src\\test\\java\\TestFW\\Data\\data.json");
		
		return new Object[][]{{data.get(0)},{data.get(1)}};
	}

}
