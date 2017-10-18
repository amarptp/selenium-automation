/**
 * 
 */
package com.pageobjectmodel.ddf.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.aventstack.extentreports.ExtentTest;
import com.pageobjectmodel.ddf.base.BasePage;
import com.pageobjectmodel.ddf.base.BaseTest;
import com.pageobjectmodel.ddf.util.Constants;

/**
 * @author AmarPratap
 * 
 * This class will store all the locators & methods of Login page.
 *
 */


public class LoginPage extends BasePage {	
	//constructor
	public LoginPage(WebDriver driver, ExtentTest test){			
		super(driver, test);		
	}
		
	//locators
	@FindBy(xpath = Constants.LOGIN_USERNAME)
	@CacheLookup
	WebElement email_Input;
	
	@FindBy(xpath = Constants.LOGIN_PASSWORD)
	@CacheLookup
	WebElement password_Input;
	
	@FindBy(xpath = Constants.LOGIN_BUTTON)
	@CacheLookup
	WebElement login_Button;	
	
	//method - 1
	public Object loginToApplication(String email, String password){
		
		test.info("Logging in as - " +email +"/" +password);
		email_Input.sendKeys(email);
		password_Input.sendKeys(password);
		login_Button.click();
		test.info("email & password entered and login button clicked");
		
		// login - validate
		boolean loginSuccess = BaseTest.isElementPresent(Constants.PROFILEPAGE_LINK);		
		
		if(loginSuccess){
			test.info("Login success");				
			HomePage home = new HomePage(driver,test);
			PageFactory.initElements(driver, home);			
			test.info("HomePage Initialized");				
			return home;
		}
		else{	
			test.info("Login unsuccess");			
			LoginPage login =new LoginPage(driver,test);
			PageFactory.initElements(driver, login);
			test.info("LoginPage Initialized");						
			return login;
		}
		
	}	
	
	
}
