package com.pageobjectmodel.ddf.util;

//http://relevantcodes.com/Tools/ExtentReports2/javadoc/index.html?com/relevantcodes/extentreports/ExtentReports.html

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.util.Date;
import java.io.File;
 
public class ExtentManager {
	private static ExtentHtmlReporter htmlReporter;
	private static ExtentReports extent;
	private static ExtentTest test;		
	
	private static ExtentHtmlReporter getHtmlReporter() {
		
		Date d = new Date();
		String fileName = d.toString().replace(":", "_").replace(" ", "_")+".html";
		String reportPath = Constants.REPORTS_PATH +fileName;	
        
        htmlReporter = new ExtentHtmlReporter(reportPath);        
        
        //htmlReporter.loadXMLConfig("./extent-config.xml");
        //htmlReporter.loadConfig("./extent-config.xml");        
        
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setDocumentTitle("Facebook Automation Report");
        htmlReporter.config().setReportName("Regression cycle");        
        return htmlReporter;
	}
	public static ExtentReports GetExtent(){
		if (extent == null){			
			extent = new ExtentReports();          		
			extent.attachReporter(getHtmlReporter());	
			extent.setSystemInfo("Selenium Version", "3.5.1");
			extent.setSystemInfo("Environment", "QA");
		}
		return extent;
	}
 
	
	/*
	public static ExtentTest createTest(String name, String description){
		test = extent.createTest(name, description);
		return test;
	}
	*/
}