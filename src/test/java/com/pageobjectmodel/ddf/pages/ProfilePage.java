package com.pageobjectmodel.ddf.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.pageobjectmodel.ddf.base.BasePage;
import com.pageobjectmodel.ddf.util.Constants;

public class ProfilePage extends BasePage {
	
	//constructor
	public ProfilePage(WebDriver driver, ExtentTest test){
			
		super(driver, test);
	}
	
	//locators
	@FindBy(xpath = Constants.PROFILEPAGE_FRIENDS_LINK)
	@CacheLookup
	WebElement friends_Link;
	
	//method - 1
	public LoginPage goToLogout() throws InterruptedException{	
		test.info("Routing to the login page");
		return getTopMenu().logout();		
			}
	
	//method - 2
	public FriendsPage goToFriendsPage(){
		test.info("Going to friends page");		
		friends_Link.click();
		test.info("Friends link clicked");
		FriendsPage friends = new FriendsPage(driver,test);
		PageFactory.initElements(driver, friends);
		return friends;
		
	}
		
		

}
