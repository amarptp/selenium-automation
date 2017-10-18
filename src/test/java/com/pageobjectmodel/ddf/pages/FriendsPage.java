/**
 * 
 */
package com.pageobjectmodel.ddf.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.pageobjectmodel.ddf.base.BasePage;
import com.pageobjectmodel.ddf.util.Constants;

/**
 * @author AmarPratap
 *
 */
public class FriendsPage extends BasePage {
	
	//constructor
	public FriendsPage(WebDriver driver, ExtentTest test){
				
		super(driver, test);
	}
		
	//locators
	@FindBys(@FindBy(xpath = Constants.FRIENDSPAGE_FRIENDS_LIST))	
	public List<WebElement> FrndList;
	
	//method - 1
	public FriendsPage getFriendsList() throws InterruptedException{
		
		//store total number of loaded friends into a variable newFriends
	    //declare & initialize oldFriends as zero
	    int newFriends = FrndList.size();	    
	    int oldFriends = 0;
	    
	    //run the loop until newFriends & oldFriends become equal
	    while(newFriends!=oldFriends)
	    {
	    	oldFriends = newFriends;
	    	//find the location of last loaded friend 
	    	WebElement lastFriend = FrndList.get(FrndList.size()-1);
	    	int location = lastFriend.getLocation().getY();	    	
	    	
	    	//scroll down the page till last friend loaded and wait for 5 seconds so that more friends can be loaded
	    	JavascriptExecutor jsx = (JavascriptExecutor)driver;
		    jsx.executeScript("window.scrollTo(0,"+location+")");		    
		    
		    System.out.println("Scroll down to the last loaded friend for loading more friends");
		    test.info("Scroll down to the last loaded friend for loading more friends");
		    Thread.sleep(5000);
		    
		    //FrndList = driver.findElements(By.xpath("//div[@class='fsl fwb fcb']/a"));
		    newFriends = FrndList.size();
		    System.out.println("Number of Facebook Friends Loaded--> " +FrndList.size()); 
		    test.info("Number of Facebook Friends Loaded--> " +FrndList.size());
		}
	    System.out.println("All " +FrndList.size()+ " friends loaded");
	    System.out.println("Total Final FaceBook Friends --> " +FrndList.size());
	    test.info("All " +FrndList.size()+ " friends loaded");
	    test.info("Total Final FaceBook Friends --> " +FrndList.size());
	    
	    //finally print all loaded friends 
	    System.out.println("List of Facebook Friends:");
	    test.info("List of Facebook Friends:");
	    
	    for(int i=0; i<FrndList.size(); i++){	    	
	    	System.out.println(FrndList.get(i).getText());
	    	test.info(FrndList.get(i).getText());
	    }
	    
	    FriendsPage friends = new FriendsPage(driver,test);
		PageFactory.initElements(driver, friends);
		
	    return friends;
	    
	}	
	
	
	

}
