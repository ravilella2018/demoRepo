package com.project.DemoProject;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

public class TC_003 extends BasePage
{
	//log4j Object
	public static final Logger log=Logger.getLogger(TC_003.class.getName());
	
	
	@Test
	public void testCase1() throws Exception 
	{
		test=rep.startTest("TC_003");
		test.log(LogStatus.INFO, "Starting the Test testCase1");
		
		
		OpenBrowser("firefoxbrowser");
		log.info("Opened the Browser with -- " + prop.getProperty("firefoxbrowser"));
		test.log(LogStatus.INFO, "Opened the -- "+ prop.getProperty("firefoxbrowser") + "Browser");
		
		
		navigate("amazonurl");
		log.info("Navigated to URL with -- " + prop.getProperty("amazonurl"));
		test.log(LogStatus.INFO, "Enter the URl with --" + prop.getProperty("amazonurl"));
		
		
		type("amazonsearchtext_id","sony");
		log.info("Entered the text By using Locator -- " + prop.getProperty("amazonsearchtext_id"));
		test.log(LogStatus.INFO, "Entered the product by using Locator -- " + prop.getProperty("amazonsearchtext_id"));
		
		
		click("amazonsearchbutton_xpath");
		log.info("Clicked on Amazon  Search Button By using Locator --" + prop.getProperty("amazonsearchbutton_xpath"));
		test.log(LogStatus.INFO, "Clicked on  Amazon Search Buton by using Locator -- " + prop.getProperty("amazonsearchbutton_xpath"));
		
		//verifyTitle();
		//reportFailure("title doesn't match....");
		test.log(LogStatus.PASS, "testCase1 is Passed");
	}
	
	
	@AfterMethod
	public void quit()
	{
		extent.endTest(test);
		extent.flush();
		driver.quit();
	}
	
	
	
}
