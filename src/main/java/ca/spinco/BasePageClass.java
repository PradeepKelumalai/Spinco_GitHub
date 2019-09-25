package ca.spinco;

import org.openqa.selenium.WebDriver;

public class BasePageClass {
	protected WebDriver driver = null;
	
	public BasePageClass(WebDriver driver){
		this.driver = driver;
	}

}
