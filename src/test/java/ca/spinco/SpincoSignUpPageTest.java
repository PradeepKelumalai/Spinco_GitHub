package ca.spinco;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ca.spinco.SpincoLandingPage;
import ca.spinco.SpincoLogin_RegisterPage;
import ca.spinco.SpincoSignUpPage;
import io.github.bonigarcia.wdm.WebDriverManager;


public class SpincoSignUpPageTest {
	
	protected WebDriver driver = null;
	protected SpincoLandingPage spincoLandingPage;
	protected SpincoLogin_RegisterPage spincoLogin_RegisterPage;
	protected SpincoSignUpPage spincoSignUpPage;
	protected Properties browser = null;
	protected FileInputStream fis = null;
	
	
	@BeforeTest
	public void setUp() throws IOException {
		browser = new Properties();
		FileInputStream fis = new FileInputStream("browser.cfg");
		browser.load(fis);
		String browserType = browser.getProperty("BROWSER");
		
		if(browserType.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			//System.setProperty("webdriver.chrome.driver", "C:\\Users\\rajkumar\\Desktop\\Automation\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
			
		}else if(browserType.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			
		}else if(browserType.equalsIgnoreCase("IE")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();

		}

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
