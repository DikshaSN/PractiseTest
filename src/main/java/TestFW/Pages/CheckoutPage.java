package TestFW.Pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import TestFW.AbstractComponent.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@FindBy(css = "input[placeholder='Select Country']")
	WebElement selectCountry;
	
	@FindBy(css=".list-group-item ")
	List<WebElement> countries;
	
	@FindBy(css=".action__submit")
	WebElement submitButton;
	
	public void SelectCountry(String country) {
		selectCountry.sendKeys(country);
		WebElement countryInd = countries.stream().filter(s->s.getText().equalsIgnoreCase(country)).findFirst().orElse(null);
		countryInd.click();
		
	}
	
	public void submitOrder() {
		submitButton.click();
	}

}
