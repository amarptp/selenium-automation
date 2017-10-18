package com.pageobjectmodel.ddf.testcases;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.pageobjectmodel.ddf.base.BaseTest;
import com.pageobjectmodel.ddf.pages.HomePage;
import com.pageobjectmodel.ddf.pages.LaunchPage;
import com.pageobjectmodel.ddf.pages.LoginPage;
import com.pageobjectmodel.ddf.pages.ProfilePage;
import com.pageobjectmodel.ddf.util.Constants;
import com.pageobjectmodel.ddf.util.DataUtil;

public class ProfileTest extends BaseTest {
	
	static String testCaseName = "ProfileTest";
	    
	@DataProvider
	public static Object[][] getLoginData() throws IOException{	
		System.out.println("Hello Amar! Data Provider is loading.");
		return DataUtil.getData("LoginTest");
	}
	
	//test for profile verification	
	@Test(dataProvider = "getLoginData")
	public void verifyProfile(String email, String password, String ProfileName, String TestType) throws InterruptedException, IOException{			
		
		//starting the test case
		test = extent.createTest("ProfileTest", "Verify profile functionality");
		test.info("Starting test case - " +testCaseName);
		
		if(!DataUtil.isTestExecutable(testCaseName)){
			test.skip("Skipping the test as Rnumode is N");
			throw new SkipException("Skipping the test as Rnumode is N");
		}
		
		//open browser & launch the application
		driver = openBrowser(Constants.CHROME_BROWSER);	
		navigate(Constants.PROD_LOGINPAGE_URL);			
		
		//initialize login page
		LoginPage login = new LoginPage(driver,test);
		PageFactory.initElements(driver, login);
		test.info("LoginPage Initialized");				
	    
		//Login to application
		Object page = login.loginToApplication(email, password);	
		
		//check user is logged in or not
		if(page instanceof HomePage)
			test.info("Logged in successfully");
		else 
			reportFailure("Logged in unsuccess");
		
		//if logged in
		home = (HomePage)page;
		
		//routing profile page
		profile = home.gotoProfilePage();
		test.info("Routed to Profile page");
		
		//verify profile name
		test.info("Verifying profile name");
		Assert.assertTrue(verifyText(Constants.PROFILEPAGE_PROFILE_NAME, ProfileName, "Incorrect Login - User Name did not match"));	
		reportPass("Profile name verified - " +ProfileName);				
		
		//logout application
		login = profile.goToLogout();
		test.info("Routed to Login Page");	
	
	}
	
}
