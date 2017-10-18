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

import com.pageobjectmodel.ddf.base.BasePage;
import com.pageobjectmodel.ddf.base.BaseTest;
import com.pageobjectmodel.ddf.common.TopMenu;
import com.pageobjectmodel.ddf.pages.HomePage;
import com.pageobjectmodel.ddf.pages.LaunchPage;
import com.pageobjectmodel.ddf.pages.LoginPage;
import com.pageobjectmodel.ddf.pages.SecurityPage;
import com.pageobjectmodel.ddf.pages.SettingPage;
import com.pageobjectmodel.ddf.util.Constants;
import com.pageobjectmodel.ddf.util.DataUtil;

/**
 * @author AmarPratap
 * Verify Change Password functionality.
 *
 */
public class ChangePasswordTest extends BaseTest {
	
	static String testCaseName = "ChangePasswordTest";
		
	@DataProvider
	public static Object[][] getChangePasswordData() throws IOException{	
		System.out.println("Hello Amar! Data Provider is loading.");
		return DataUtil.getData(testCaseName);
	}	
	
	//test for change password
	@Test(dataProvider = "getChangePasswordData")
	public void changePswd(String email, String oldPassword, String newPassword) throws InterruptedException, IOException{			
		
		//starting the test case
		test = extent.createTest("changePasswordTest", "Verify change password functionality");
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
		
		//Login to application
		Object page = login.loginToApplication(email, oldPassword);	
		
		//check user is logged in or not
		if(page instanceof HomePage)
			test.info("Logged in successfully");
		else 
			reportFailure("Logged in unsuccess");
		
		//if logged in
		home = (HomePage)page;
		
		//routing setting page
		setting = home.goToSettingPage();
		test.info("Routed to Setting Page");		
		
		//routing security page
		security = setting.goToSecurityPage();
		test.info("Routed to Security Page");		
		
		//change password
		security = security.changePassword(oldPassword, newPassword);
		Thread.sleep(2000);		
		reportPass("Password changed");
		
		//logout application
		login = home.goToLogout();
		test.info("Routed to Login Page");
		
	}	
	
}
