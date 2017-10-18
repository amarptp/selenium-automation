package com.pageobjectmodel.ddf.util;

import java.util.Hashtable;

public class Constants {
	public static final boolean GRID_RUN = true;
	
	//Driver paths
	public static final String CHROME_DRIVER_EXE = "E:\\Selenium\\Browser_Drivers\\chromedriver.exe";
	public static final String GECKO_DRIVER_EXE = "E:\\Selenium\\Selenium Download\\Drivers\\geckodriver.exe";
	public static final String IE_DRIVER_EXE = "E:\\Selenium\\Browser_Drivers\\IEDriverServer.exe";	
	
	//users
	//public static final String USER_AMAR = "Amar Pratap";
	//public static final String USER_RAJANI = "Rajani Singh";
	
	//password
	//public static final String CURRENT_PASSWORD = "";
	//public static final String NEW_PASSWORD = "";
	
	
	//Title
	public static final String TITLE_LOGIN_PAGE = "Facebook – log in or sign up";
	public static final String TITLE_HOME_PAGE = "Facebook";
	
	//Browser
	public static final String CHROME_BROWSER = "Chrome";
	public static final String Firefox_BROWSER = "Firefox";
	public static final String IE_BROWSER = "IE";
	
	// locators  
	public static final String LOGIN_USERNAME = "//*[@id='email']";
	public static final String LOGIN_PASSWORD = "//*[@id='pass']";
	public static final String LOGIN_BUTTON = "//*[@id='loginbutton']";
	public static final String PROFILEPAGE_LINK = "//*[@id='userNav']/ul/li/a/div";
	public static final String PROFILEPAGE_PROFILE_NAME = "//*[@id='fb-timeline-cover-name']";
	public static final String PROFILEPAGE_FRIENDS_LINK = "//div[@id='fbTimelineHeadline']/div[2]/ul/li[3]/a";
	public static final String FRIENDSPAGE_FRIENDS_LIST = "//div[@class='fsl fwb fcb']/a";
	public static final String NAVIGATION_LABEL = "//*[@id='userNavigationLabel']";
	public static final String LOGOUT = "//*[@id='BLUE_BAR_ID_DO_NOT_USE']/div/div/div[1]/div/div/ul/li[12]/a/span/span";
	public static final String SETTINGS_LINK = "//span[text()='Settings']";
	public static final String SECURITY_LINK = "//*[@id='navItem_security']/a/div[2]/div";	
	public static final String PASSWORD_CHANGE = "//*[@id='SettingsPage_Content']/div/div/div/div[3]/div[2]/table/tbody/tr/td[2]/span[1]";
	public static final String OLD_PASSWORD = "//*[@id='password_old']";
	public static final String NEW_PASSWORD = "//*[@id='password_new']";
	public static final String RETYPE_NEW_PASSWORD = "//*[@id='password_confirm']";
	public static final String SAVE_CHANGES = "//label[@class='submit uiButton uiButtonConfirm']";	
	public static final String KEEP_SESSION = "//div[text()='Stay logged in']";
	public static final String CONTINUE_PASSWORD_CHANGE_BUTTON = "//button[text()='Continue']";
	
	// URLs-prod
	public static final String PROD_LOGINPAGE_URL = "http://facebook.com";
	public static final String PROD_USERNAME = "its.thakur@gmail.com";
	public static final String PROD_PASSWORD = "Ashish@789";
	
	// URLs-uat
	public static final String UAT_LOGINPAGE_URL = "http://uat.facebook.com";
	public static final String UAT_USERNAME = "uat_its.thakur@gmail.com";
	public static final String UAT_PASSWORD = "uat_Ashish@123";
		
	
	public static final String ENV="PROD"; //PROD, UAT,SAT 
			

	//paths
	public static final String REPORTS_PATH = System.getProperty("user.dir") +"\\reports\\";
	public static final String SCREENSHOT_PATH_PASS = System.getProperty("user.dir") +"\\screenshots\\passed\\";
	public static final String SCREENSHOT_PATH_FAIL = System.getProperty("user.dir") +"\\screenshots\\failed\\";
	public static final String DATA_XLS_PATH = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData.xlsx";
	public static final String TESTDATA_SHEET = "TestData";
	public static final Object RUNMODE_COL = "Runmode";
	public static final String TESTCASES_SHEET = "TestCases";
	
	public static Hashtable<String,String> table;
	
	public static Hashtable<String,String> getEnvDetails(){
		if(table==null){
			table = new Hashtable<String,String>();
			if(ENV.equals("PROD")){
				table.put("url", PROD_LOGINPAGE_URL);
				table.put("username", PROD_USERNAME);
				table.put("password", PROD_PASSWORD);
			}else if(ENV.equals("UAT")){
				table.put("url", UAT_LOGINPAGE_URL);
				table.put("username", UAT_USERNAME);
				table.put("password", UAT_PASSWORD);
			}
			
		}
		return table;
		 
	}




	


	


	




	



	

}
