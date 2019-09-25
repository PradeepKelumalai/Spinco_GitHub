package ca.spinco;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ca.spinco.SpincoLandingPage;
import ca.spinco.SpincoLogin_RegisterPage;
import ca.spinco.SpincoSignUpPage;

import java.net.URL;

public class SpincoSignUpPageBrowserStackTest {
	public static final String USERNAME = "peterthomas10";
	public static final String AUTOMATE_KEY = "iydVizbfqbZnJzy1AiG8";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
	
	protected WebDriver driver = null;
	protected SpincoLandingPage spincoLandingPage;
	protected SpincoLogin_RegisterPage spincoLogin_RegisterPage;
	protected SpincoSignUpPage spincoSignUpPage;
	protected Properties browser = null;
	protected FileInputStream fis = null;
	
	
	@BeforeTest
	public void setUp() throws IOException {
	   DesiredCapabilities caps = new DesiredCapabilities();
	    caps.setCapability("browser", "Chrome");
	    caps.setCapability("browser_version", "76.0");
	    caps.setCapability("os", "Windows");
	    caps.setCapability("os_version", "8");
	    caps.setCapability("resolution", "1024x768");
	    caps.setCapability("name", "Bstack-[Java] Sample Test");
	 
	    driver = new RemoteWebDriver(new URL(URL), caps);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.spinco.ca");
		spincoLandingPage = PageFactory.initElements(driver, SpincoLandingPage.class);
		
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority = 1)
	protected void landingpageVerification() {
		//spincoLandingPage = PageFactory.initElements(driver, SpincoLandingPage.class);
		Assert.assertEquals(spincoLandingPage.isLogoDisplayed(),true);
		spincoLandingPage.clickLoginRegister();
	}
	
	@Test(priority = 2,dependsOnMethods="landingpageVerification")
	protected void login_RegisterPageVerification() {
		spincoLogin_RegisterPage = PageFactory.initElements(driver, SpincoLogin_RegisterPage.class);
		Assert.assertEquals(spincoLogin_RegisterPage.getLoginPageText(), "SIGN IN");
		spincoLogin_RegisterPage.selectSignUp();
	}
	
	@Test(priority = 3,dependsOnMethods="login_RegisterPageVerification")
	protected void signUp() {
		spincoSignUpPage = PageFactory.initElements(driver, SpincoSignUpPage.class);
		Assert.assertEquals(spincoSignUpPage.getSignUpText(), "SIGN UP");
		spincoSignUpPage.fillLoginDetails(spincoSignUpPage.username(),spincoSignUpPage.password());
		spincoSignUpPage.fillPersonalDetails();
		spincoSignUpPage.shoeDetails();
		spincoSignUpPage.mailChimpSubscribe(true);
		spincoSignUpPage.acceptConcernForm(true);
		spincoSignUpPage.completeSignUp();
		Assert.assertEquals(spincoSignUpPage.getSignUpMsg(),"Please login to continue.");
		
	}
	@Test(priority = 4,dependsOnMethods="signUp")
	protected void signIn() {
		spincoSignUpPage.fillSignIndetails(spincoSignUpPage.username(),spincoSignUpPage.password());
		Assert.assertEquals(spincoSignUpPage.getSignInMsg(), "Welcome to Spinco.");
		
	}
}
