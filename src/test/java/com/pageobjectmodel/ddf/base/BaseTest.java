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
import org.openqa.selenium.remote.Augmenter;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.pageobjectmodel.ddf.pages.FriendsPage;
import com.pageobjectmodel.ddf.pages.HomePage;
import com.pageobjectmodel.ddf.pages.LoginPage;
import com.pageobjectmodel.ddf.pages.ProfilePage;
import com.pageobjectmodel.ddf.pages.SecurityPage;
import com.pageobjectmodel.ddf.pages.SettingPage;
import com.pageobjectmodel.ddf.util.Constants;
import com.pageobjectmodel.ddf.util.ExtentManager;
import com.pageobjectmodel.ddf.util.Xls_Reader;



public class BaseTest {
	
	public static WebDriver driver;	
	public SoftAssert softAssert;
	
	public ExtentReports extent = ExtentManager.GetExtent();
	public static ExtentTest test;		
	
	public LoginPage login;	
	public HomePage home;	
	public ProfilePage profile;
	public FriendsPage friends;
	public SettingPage setting;
	public SecurityPage security;
	
	
	@BeforeMethod
	public void init(){
		softAssert = new SoftAssert();
	}	
	
	@AfterMethod()
	public void quit(){		
		try{
			softAssert.assertAll();
		}catch(Error e){			
			test.fail(e.getMessage());
			e.getMessage();
		}	
		
		if(driver!=null)
			closeAllBrowsers();
		
		if(extent!=null)			
			extent.flush();				
	}		
	
	public WebDriver openBrowser(String browserName){		
		
		if(browserName.equalsIgnoreCase("Firefox")){
			test.info("Opening browser - " +browserName);
			System.setProperty("webdriver.gecko.driver", Constants.GECKO_DRIVER_EXE);
			driver = new FirefoxDriver();
			test.info("Browser Launched - " +browserName);
		}
		else if(browserName.equalsIgnoreCase("Chrome")){
			test.info("Opening browser - " +browserName);
			System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_EXE);			
			
			// Create object of HashMap Class
			Map<String, Object> prefs = new HashMap<String, Object>();
				      
			// Set the notification setting it will override the default setting
			prefs.put("profile.default_content_setting_values.notifications", 2);
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);

			// Create object of ChromeOption class
			ChromeOptions options = new ChromeOptions();

			// Set the experimental option
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--start-maximized");
			options.addArguments("--test-type");
			options.addArguments("--disable-web-security");
			options.addArguments("--no-proxy-server");
			options.addArguments("disable-extensions");

			driver = new ChromeDriver(options);	
			test.info("Browser Launched - " +browserName);
			}		
		else if (browserName.equalsIgnoreCase("IE")){
			test.info("Opening browser - " +browserName);
			System.setProperty("webdriver.ie.driver", Constants.IE_DRIVER_EXE);
			driver = new InternetExplorerDriver();
			test.info("Browser Launched - " +browserName);
		}			
		return driver;		
	}	

	public void navigate(String url){
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		test.info("Application launched");
	}
	
	
	public void closeBrowser(){		
		driver.close();	
		test.info("Browser closed");
	}
	
	public void closeAllBrowsers(){
		driver.quit();
		test.info("All browsers closed");
	}
	
	//finding element and returning it
	public WebElement getElement(String locatorKey) throws IOException{
		WebElement e=null;
		try{
			e = driver.findElement(By.xpath(locatorKey));
			
			}catch(Exception ex){
				//fail the test and report the error
				reportFailure(ex.getMessage());
				ex.printStackTrace();
				Assert.fail("Failed the test - "+ex.getMessage());
				}
				return e;
			}
			
/***********************Validations***************************/
	
	public static boolean isElementPresent(String locator){
		test.info("Trying to find element --> " +locator);
		int s = driver.findElements(By.xpath(locator)).size();
		if(s==0){
			test.info("Element -->" +locator+"  not found");
			return false;
		}
		else{
			test.info("Element --> "+locator+"  found");
			return true;
		}			
	}	
	
	public boolean verifyTitle(String expectedTitle){
		String actualTitle = driver.getTitle().trim();
		
		if(actualTitle.endsWith(expectedTitle))
			return true;
		else		
			return false;		
	}
	
	public boolean verifyText(String locatorKey, String expectedText, String failedMessage) throws IOException{
		String actualText=getElement(locatorKey).getText().trim();
		System.out.println("Actual: " +actualText);
		System.out.println("Expected: " +expectedText);
		if(actualText.equals(expectedText)){			
			return true;
		}
		else {
			reportFailure(failedMessage);
			return false;
		}
		
	}
	/*****************************Reporting********************************/
	
	
	public void reportPass(String msg) throws IOException{		
		test.pass(msg);
		takeScreenShotOnPass();
	}

	public void reportFailure(String msg) throws IOException{		
		test.fail(msg);
		takeScreenShotOnFail();
		Assert.fail(msg);
	}

	public void takeScreenShotOnPass() throws IOException{		
		//fileName & filePath of the screenshot
		Date d = new Date();
		String fileName = d.toString().replace(":", "_").replace(" ", "_")+".jpg";
		String filePath = Constants.SCREENSHOT_PATH_PASS +fileName;
		
		//store screenshot in that file		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(filePath));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		//put screenshot file in reports		
		test.addScreenCaptureFromPath(filePath);
	}
	
	public void takeScreenShotOnFail() throws IOException{		
		//fileName & filePath of the screenshot
		Date d = new Date();
		String fileName = d.toString().replace(":", "_").replace(" ", "_")+".jpg";
		String filePath = Constants.SCREENSHOT_PATH_FAIL +fileName;
		
		//store screenshot in that file		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(filePath));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		//put screenshot file in reports		
		test.addScreenCaptureFromPath(filePath);
	}
		
		
}
