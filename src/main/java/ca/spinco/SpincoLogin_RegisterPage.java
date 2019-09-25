package ca.spinco;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SpincoLogin_RegisterPage extends BasePageClass {
	
	public SpincoLogin_RegisterPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css = "#signinbox h2")
	protected WebElement loginPageText;
	
	public String getLoginPageText() {
		return loginPageText.getText();
	}
	@FindBy(css = "#signupbox .btn")
	protected WebElement signUpButton;
	
	public void selectSignUp() {
		signUpButton.click();
	}
	


}
