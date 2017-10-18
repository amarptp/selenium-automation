package com.pageobjectmodel.ddf.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.pageobjectmodel.ddf.base.BasePage;
import com.pageobjectmodel.ddf.common.TopMenu;
import com.pageobjectmodel.ddf.util.Constants;

public class HomePage extends BasePage {
	
	
	//constructor
	public HomePage(WebDriver driver, ExtentTest test){
		//this.driver = driver;	
		super(driver, test);
	}
	
	//locators
	@FindBy(xpath = Constants.PROFILEPAGE_LINK)
	@CacheLookup
	WebElement profileName_Link;		
	
	//method - 1
	public SettingPage goToSettingPage() throws InterruptedException{
		test.info("Going to setting page");
		return getTopMenu().setting();		
	}
	
	//method - 2
	public LoginPage goToLogout() throws InterruptedException{			
		return getTopMenu().logout();		
		}
	
	//method - 3
	public ProfilePage gotoProfilePage() {
		test.info("Going to profile page");		
		profileName_Link.click();
		test.info("Profile name link clicked");
		ProfilePage profile = new ProfilePage(driver,test);
		PageFactory.initElements(driver, profile);
		return profile;
	}


	
	

}
