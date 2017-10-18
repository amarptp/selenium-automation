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
import com.pageobjectmodel.ddf.util.Constants;

/**
 * @author AmarPratap
 *
 */
public class SecurityPage extends BasePage {
	
	//constructor
	public SecurityPage(WebDriver driver, ExtentTest test){			
		super(driver, test);		
		}
				
	//locators
	@FindBy(xpath = Constants.PASSWORD_CHANGE)
	@CacheLookup
	WebElement passwordChange_Link;
	
	@FindBy(xpath = Constants.OLD_PASSWORD)
	@CacheLookup
	WebElement oldPassword_Input;
	
	@FindBy(xpath = Constants.NEW_PASSWORD)
	@CacheLookup
	WebElement newPassword_Input;
	
	@FindBy(xpath = Constants.RETYPE_NEW_PASSWORD)
	@CacheLookup
	WebElement retypeNewPassword_Input;
	
	@FindBy(xpath = Constants.SAVE_CHANGES)
	@CacheLookup
	WebElement saveChange_Button;
	
	@FindBy(xpath = Constants.KEEP_SESSION)
	@CacheLookup
	WebElement keepSession_radio;
	
	@FindBy(xpath = Constants.CONTINUE_PASSWORD_CHANGE_BUTTON)
	@CacheLookup
	WebElement continuePswdChng_Button;
		
	//method - 1
	public SecurityPage changePassword(String oldPswd, String newPswd){
			
		passwordChange_Link.click();
		test.info("Password Change link clicked");
		
		oldPassword_Input.sendKeys(oldPswd);
		test.info("Old password entered");
		
		newPassword_Input.sendKeys(newPswd);
		test.info("New password entered");
		
		retypeNewPassword_Input.sendKeys(newPswd);
		test.info("New password reentered");
		
		saveChange_Button.click();
		test.info("Save Change button clicked");		
		
		keepSession_radio.click();		
		test.info("Keep Session radio button selected");
		
		continuePswdChng_Button.click();
		test.info("Continue Password Change button clicked");
		
		SecurityPage security = new SecurityPage(driver, test);
		PageFactory.initElements(driver, security);
		test.info("SecurityPage initialized");
		
		return security;		
			
	}
	
	

	

}
