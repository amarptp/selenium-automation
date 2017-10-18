/**
 * 
 */
package com.pageobjectmodel.ddf.testcases;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.pageobjectmodel.ddf.base.BaseTest;
import com.pageobjectmodel.ddf.pages.HomePage;
import com.pageobjectmodel.ddf.pages.LoginPage;
import com.pageobjectmodel.ddf.util.Constants;
import com.pageobjectmodel.ddf.util.DataUtil;
import com.pageobjectmodel.ddf.util.ExtentManager;

/**
 * @author AmarPratap
 * Verify login functionality of application for valid user.
 *
 */
public class LoginTest extends BaseTest {
	
	static String testCaseName = "LoginTest";
		
	@DataProvider
	public static Object[][] getLoginData() throws IOException{	
		System.out.println("Hello Amar! Data Provider is loading");
		return DataUtil.getData(testCaseName);
	}
	
	//test for login
	@Test(dataProvider = "getLoginData")
	public void doLogin(String email, String password, String ProfileName, String TestType) throws InterruptedException, IOException{			
        
		//starting the test case
		test = extent.createTest("LoginTest", "Verify login functionality");
		test.info("Starting test case - " +testCaseName);		

		if(!DataUtil.isTestExecutable(testCaseName)){
			test.skip("Skipping the test as Rnumode is N");
			throw new SkipException("Skipping the test as Rnumode is N");
		}
				
		//open browser & launch the application
		driver = openBrowser(Constants.CHROME_BROWSER);	
		navigate(Constants.PROD_LOGINPAGE_URL);			
		
		//initialize login page
		LoginPage login =new LoginPage(driver,test);
		PageFactory.initElements(driver, login);
		test.info("LoginPage Initialized");
		
		//verify login page title				
		softAssert.assertTrue(verifyTitle(Constants.TITLE_LOGIN_PAGE), "LoginPage Title did not match");		
		
		//Login to application
		Object page = login.loginToApplication(email, password);		
		
		//check user is logged in or not
		String actualResult="";
		if(page instanceof HomePage)			
			actualResult="Positive";		
		else
			actualResult="Negative";
		
		//Check whether test case is positive or negative
		if(!actualResult.equalsIgnoreCase(TestType))			
			reportFailure("LoginTest Failed");		
		else
			reportPass("LoginTest Passed");			
			
		//if logged in then check title and logout
		if(page instanceof HomePage){
			
			softAssert.assertTrue(verifyTitle(Constants.TITLE_HOME_PAGE), "HomePage Title did not match");
			
			HomePage home = new HomePage(driver,test);
			PageFactory.initElements(driver, home);
			
			login = home.goToLogout();		
			test.info("Routed to Login Page");			
		}
		
		
	}	

}
