/**
 * 
 */
package com.pageobjectmodel.ddf.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.pageobjectmodel.ddf.pages.HomePage;
import com.pageobjectmodel.ddf.pages.LoginPage;
import com.pageobjectmodel.ddf.pages.SettingPage;
import com.pageobjectmodel.ddf.util.Constants;
import com.pageobjectmodel.ddf.util.ExtentManager;

/**
 * @author AmarPratap
 *
 */
public class TopMenu {
	
	public ExtentReports extent = ExtentManager.GetExtent();
	public ExtentTest test;
	WebDriver driver;
	
	public TopMenu(WebDriver driver, ExtentTest test){		
		this.driver = driver;
		this.test = test;
	}	
	

	@FindBy(xpath = Constants.NAVIGATION_LABEL)
	@CacheLookup
	WebElement navigationLabel;	
	
	@FindBy(xpath = Constants.LOGOUT)
	@CacheLookup
	WebElement logout;
	
	@FindBy(xpath = Constants.SETTINGS_LINK)
	@CacheLookup
	WebElement setting_Link;
	
	//method - 1
	public LoginPage logout() throws InterruptedException{		
		navigationLabel.click();
		test.info("Navigation link clicked");
		
		Thread.sleep(2000);
		
		logout.click();	
		test.info("Logout link clicked");
		
		LoginPage login =new LoginPage(driver,test);
		PageFactory.initElements(driver, login);
		
		test.info("LoginPage Initialized");	
		test.info("Logout successfull");
		return login;		
	}
	
	//method - 2
	public SettingPage setting() throws InterruptedException{
		navigationLabel.click();
		test.info("navigationLabel clicked");
		Thread.sleep(2000);
		
		setting_Link.click();
		test.info("Setting link clicked");		
		
		SettingPage setting = new SettingPage(driver, test);
		PageFactory.initElements(driver, setting);
		test.info("SettingPage Initialized");
		
		return setting; 				
	}

}
