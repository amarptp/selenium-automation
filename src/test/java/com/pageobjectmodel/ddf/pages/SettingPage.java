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
public class SettingPage extends BasePage {
	
	//constructor
		public SettingPage(WebDriver driver, ExtentTest test){			
			super(driver, test);		
		}
			
	//locators
	@FindBy(xpath = Constants.SECURITY_LINK)
	@CacheLookup
	WebElement security_Link;
	
	//method - 1
	public SecurityPage goToSecurityPage(){
		security_Link.click();
		test.info("Security link clicked");
		
		SecurityPage security = new SecurityPage(driver, test);
		PageFactory.initElements(driver, security);
		test.info("SecurityPage initialized");	
		
		return security;		
		
	}

}
