/**
 * 
 */
package com.pageobjectmodel.ddf.base;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.pageobjectmodel.ddf.common.TopMenu;
import com.pageobjectmodel.ddf.pages.FriendsPage;
import com.pageobjectmodel.ddf.pages.HomePage;
import com.pageobjectmodel.ddf.pages.LoginPage;
import com.pageobjectmodel.ddf.pages.ProfilePage;
import com.pageobjectmodel.ddf.pages.SecurityPage;
import com.pageobjectmodel.ddf.pages.SettingPage;
import com.pageobjectmodel.ddf.util.Constants;
import com.pageobjectmodel.ddf.util.ExtentManager;


/**
 * @author AmarPratap
 *
 */
public class BasePage {
	
	public WebDriver driver;
	
	public LoginPage login;	
	public HomePage home;	
	public ProfilePage profile;
	public FriendsPage friends;
	public SettingPage setting;
	public SecurityPage security;
	
	public TopMenu menu;
	public ExtentReports extent = ExtentManager.GetExtent();
	public ExtentTest test;
	
	public BasePage(){}
	
	public BasePage(WebDriver driver, ExtentTest test){
		this.driver=driver;
		this.test=test;		
		
		if(menu == null) {
			menu = new TopMenu(driver, test);
			PageFactory.initElements(driver, menu);
			test.info("TopMenu Initialized");			
		}
	}
	/*
	public void init(){
		
	}
	*/
	
	public TopMenu getTopMenu(){
		return menu;
	}	
	

}
