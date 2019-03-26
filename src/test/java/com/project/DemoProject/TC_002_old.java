package com.project.DemoProject;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

public class TC_002_old extends BasePage
{
	//log4j Object
	public static final Logger log=Logger.getLogger(TC_002_old.class.getName());
	
	
	@Test
	public void testCase1() throws Exception 
	{
		test=rep.startTest("TC_002");
		test.log(LogStatus.INFO, "Starting the Test testCase1");
		
		
		OpenBrowser("chromebrowser");
		log.info("Opened the Browser with -- " + prop.getProperty("chromebrowser"));
		test.log(LogStatus.INFO, "Opened the -- "+ prop.getProperty("chromebrowser") + "Browser");
		
		
		navigate("amazonurl");
		log.info("Navigated to URL with -- " + prop.getProperty("amazonurl"));
		test.log(LogStatus.INFO, "Enter the URl with --" + prop.getProperty("amazonurl"));
		
		//reportFailure("Search bix not present");
		type("amazonsearchtext_id","sony");
		log.info("Entered the text By using Locator -- " + prop.getProperty("amazonsearchtext_id"));
		test.log(LogStatus.INFO, "Entered the product by using Locator -- " + prop.getProperty("amazonsearchtext_id"));
		
		
		click("amazonsearchbutton_xpath");
		log.info("Clicked on Amazon  Search Button By using Locator --" + prop.getProperty("amazonsearchbutton_xpath"));
		test.log(LogStatus.INFO, "Clicked on  Amazon Search Buton by using Locator -- " + prop.getProperty("amazonsearchbutton_xpath"));
		
		//verifyTitle();
		//reportFailure("title doesn't match....");
		test.log(LogStatus.PASS, "testCase1 is Passed");
		
		//test.log(LogStatus.FAIL, "screenshot" + test.addScreenCapture("C:\\Users\\DELL\\Desktop\\test.png"));
		takeScreenShot();
	}
	
	
	@AfterMethod
	public void quit()
	{
		extent.endTest(test);
		extent.flush();
		driver.quit();
	}
	
	
	
}
