package ca.spinco;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SpincoSignUpPage extends BasePageClass{
	protected Date date = null;
	protected Properties uName_Password =null;
	protected FileInputStream fis = null;
	
	public SpincoSignUpPage(WebDriver driver) throws IOException{
		super(driver);
		date = new Date();
		uName_Password = new Properties();
		fis = new FileInputStream("uNamePassword.cfg");
		uName_Password.load(fis);
	}

	@FindBy(css = ".page-header > h1")
	protected WebElement signUpText;
	
	public String getSignUpText() {
		return signUpText.getText();
	}
	
	public String username() {
		long uniqueNumber = date.getTime();
		String userEmail = uName_Password.getProperty("UserName");
		String email = userEmail+String.valueOf(uniqueNumber)+"@gmail.com";
		return email;
	
	}
	
	public String password() {
		String uPassword = uName_Password.getProperty("Password");
		return uPassword;
		
	}
	
	@FindBy(name = "username")
	protected WebElement email_field;
	
	@FindBy(name = "password")
	protected WebElement password_field;
	
	@FindBy(name = "passwordconfirm")
	protected WebElement confirmPassword_field;
	
	public void fillLoginDetails(String userName,String password) {
		email_field.sendKeys(userName);
		password_field.sendKeys(password);
		confirmPassword_field.sendKeys(password);
		
	}
	
	@FindBy(name = "firstname")
	protected WebElement firstName;
	
	@FindBy(name = "lastname")
	protected WebElement lastName;
	
	@FindBy(name = "phone")
	protected WebElement phoneNo;
	
	@FindBy(name = "address")
	protected WebElement address_field;
	
	@FindBy(name = "city")
	protected WebElement city_field;
	
	@FindBy(name = "zip")
	protected WebElement postalCode;
	
	public void fillPersonalDetails() {
		firstName.sendKeys("Peter");
		lastName.sendKeys("Thomas");
		phoneNo.sendKeys("5144435897");
		address_field.sendKeys("1095 St Aubin");
		city_field.sendKeys("Montreal");
		postalCode.sendKeys("H4R1T3");
		
	}
	
	@FindBy(name = "measure1")
	protected WebElement shoeSize;
	
	public void shoeDetails() {
		shoeSize.sendKeys("9.5");
	}
	
	@FindBy(name = "mailchimp_subscribe")
	protected WebElement mcSubscribe;
	
	public void mailChimpSubscribe(boolean select) {
		if(select == true) {
			mcSubscribe.click();
		}
		else {
			System.out.println("MailChimp Subscribe is not selected ");
		}
	}
	
	@FindBy(name = "agreeterms")
	protected WebElement spincoTerms;
	
	public void acceptConcernForm(boolean check) {
		if(check == true) {
			spincoTerms.click();
		}
	}
	
	@FindBy(name = "_submit")
	protected WebElement saveButton;
	
	public void completeSignUp() {
		saveButton.click();
		
	}

	@FindBy(css = "div.alert")
	protected WebElement signUpMsg;
	
	public String getSignUpMsg() {
		return signUpMsg.getText();
	}
	
	@FindBy(id = "username")
	protected WebElement userId;
	
	@FindBy(id = "password")
	protected WebElement userPassword;
	
	@FindBy(css = "button.btn")
	protected WebElement signInButton;
	
	public void fillSignIndetails(String email,String uPassword) {
		userId.sendKeys(email);
		userPassword.sendKeys(uPassword);
		signInButton.click();
		
	}
	
	@FindBy(css = "div.alert")
	protected WebElement signInMsg;
	
	public String getSignInMsg() {
		return signInMsg.getText();
	}
	
}
