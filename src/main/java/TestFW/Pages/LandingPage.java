package TestFW.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import TestFW.AbstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent{

	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="userEmail")
	WebElement emailBox;
	
	@FindBy(id="userPassword")
	WebElement passwordBox;
	
	@FindBy(id="login")
	WebElement loginButton;
	
	public void goToUrl() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	public ProductCatalogue loginApplication(String email,String password) {
		emailBox.sendKeys(email);
		passwordBox.sendKeys(password);
		loginButton.click();
		ProductCatalogue productPage = new ProductCatalogue(driver);
		return productPage;
	}
	
}
