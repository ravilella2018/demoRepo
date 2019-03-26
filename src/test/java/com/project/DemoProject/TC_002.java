package com.project.DemoProject;

import org.apache.log4j.Logger;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

public class TC_002 extends BasePage
{
	//log4j Object
	public static final Logger log=Logger.getLogger(TC_002.class.getName());
	SoftAssert softAssert=new SoftAssert();
	
	
	
	@Test
	public void testCase1() throws Exception 
	{
		
		test=rep.startTest("TC_002");
		test.log(LogStatus.INFO, "Starting the Test testCase1");
		Reporter.log("Starting the Test testCase1");
				
		
		OpenBrowser("chromebrowser");
		/*log4j*/
		log.info("Opened the Browser with -- " + prop.getProperty("chromebrowser"));
		/*Extent Report*/		 
		test.log(LogStatus.INFO, "Opened the -- "+ prop.getProperty("chromebrowser") + "Browser");
		/*TestNG Reports*/
		Reporter.log("Opened the -- "+ prop.getProperty("chromebrowser") + " Browser");
		
		
		navigate("amazonurl");
		log.info("Navigated to URL with -- " + prop.getProperty("amazonurl"));
		test.log(LogStatus.INFO, "Enter the URl with --" + prop.getProperty("amazonurl"));
		Reporter.log("Enter the URl with --  " + prop.getProperty("amazonurl"));
		
		
		//verify Amazon text
		/*if(!verifyText("amazontext_xpath", "amazontext"))
			reportFailure("Text did not match");*/
		
		//softAssert.assertTrue(verifyText("amazontext_xpath", "amazontext"), prop.getProperty("amazontext")+" - Text is not equal");
		//softAssert.assertTrue(true, "error 1");
		//softAssert.assertTrue(false, "error 2");
				
		
		//check if amazon field is present
		//if(!isElementPresent("amazontext_xpath"))
			//reportFailure("Amazonfield not present");
		
		//reportFailure("Search bix not present");
		type("amazonsearchtext_id","sony");
		log.info("Entered the text By using Locator -- " + prop.getProperty("amazonsearchtext_id"));
		test.log(LogStatus.INFO, "Entered the product by using Locator -- " + prop.getProperty("amazonsearchtext_id"));
		Reporter.log("Entered the product by using Locator -- " + prop.getProperty("amazonsearchtext_id"));
		
		
		
		click("amazonsearchbutton_xpath");
		log.info("Clicked on Amazon  Search Button By using Locator --" + prop.getProperty("amazonsearchbutton_xpath"));
		test.log(LogStatus.INFO, "Clicked on  Amazon Search Buton by using Locator -- " + prop.getProperty("amazonsearchbutton_xpath"));
		Reporter.log("Clicked on  Amazon Search Buton by using Locator -- " + prop.getProperty("amazonsearchbutton_xpath"));
		
		
		test.log(LogStatus.PASS, "testCase1 is Passed");
		Reporter.log("testCase1 is Passed");
		
		//test.log(LogStatus.FAIL, "screenshot" + test.addScreenCapture("C:\\Users\\DELL\\Desktop\\test.png"));
		
		//screenshot
		//takeScreenShot();
	}
	
	
	@AfterMethod
	public void quit()
	{
		
		try{
			softAssert.assertAll();
		}catch(Error e){
			test.log(LogStatus.FAIL, e.getMessage());
		}
		extent.endTest(test);
		extent.flush();
		
		if(driver!=null)
		{
			driver.quit();
		}
		
	}
	
	
	
}
