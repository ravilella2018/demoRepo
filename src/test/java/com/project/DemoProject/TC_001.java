package com.project.DemoProject;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class TC_001 extends BasePage
{
	public static final Logger log=Logger.getLogger(TC_001.class.getName());
	
	@Test
	public void testCase1() throws Exception 
	{
		OpenBrowser("chromebrowser");
		log.info("Opened the Browser with -- " + prop.getProperty("chromebrowser"));
		
		navigate("amazonurl");
		log.info("Navigated to URL with -- " + prop.getProperty("amazonurl"));
		
		type("amazonsearchtext_id","sony");
		log.info("Entered the text By using Locator -- " + prop.getProperty("amazonsearchtext_id"));
		
		click("amazonsearchbutton_xpath");
		log.info("Clicked on Amazon  Search Button By using Locator --" + prop.getProperty("amazonsearchbutton_xpath"));
		//verifyTitle();
		//reportFailure("title doesn't match....");
	}
}
