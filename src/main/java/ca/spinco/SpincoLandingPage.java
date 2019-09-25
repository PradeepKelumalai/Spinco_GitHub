package ca.spinco;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SpincoLandingPage extends BasePageClass{
	
	public SpincoLandingPage(WebDriver driver) {
		super(driver);
		
	}
	@FindBy(css = "div > h2 > img")
	protected WebElement sLogo;
	
	public boolean isLogoDisplayed() {
		return sLogo.isDisplayed();
	}

	@FindBy(css = "div .topnav.second a>span")
	protected WebElement login_RegisterButton;
	
	public void clickLoginRegister() {
		login_RegisterButton.click();
		
	}

}
