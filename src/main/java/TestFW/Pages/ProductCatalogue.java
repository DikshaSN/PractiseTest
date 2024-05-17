package TestFW.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import TestFW.AbstractComponent.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{

	WebDriver driver;
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> productList;
	
	 @FindBy(css=".ng-animating")
	 WebElement spinner;
	
	 By productappear = By.cssSelector(".mb-3");
	 By toastMessage = By.id("toast-container");
	By addToCartButton = By.cssSelector(".card-body button:last-of-type");
	
	public  List<WebElement> getProductList() {
		waitForElementToAppear(productappear);
		return productList;
	}
	
	public WebElement getProduct(String product) {
		WebElement productName = getProductList().stream().filter(s->s.findElement(By.tagName("b")).getText().equals(product)).findFirst().orElse(null);
		return productName;
	}
	
   public void addToCart(String product) throws InterruptedException {
	   WebElement productName = getProduct(product);
	   productName.findElement(addToCartButton).click();
	   waitForElementToAppear(toastMessage);
	   waitForElementToDisappear(spinner);
   }

}
