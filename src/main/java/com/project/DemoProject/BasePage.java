package com.project.DemoProject;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BasePage extends ExtentManager
{
	public static WebDriver driver;
	public static String path="./config.properties";
	public static Properties prop;
	public static String screenshotFileName=null;
	
	//Extent Report Object
	public ExtentReports rep=ExtentManager.getInstance();
	public ExtentTest test;
	
	

	
	static 
	{
		Date dt=new Date();
		screenshotFileName = dt.toString().replace(":", "_").replace(" ", "_")+".png";
	}
	
	
	
	public static void OpenBrowser(String browser) throws Exception
	{
		if(prop==null)
		{
			prop=new Properties();
			File f=new File(path);
			FileInputStream fis=new FileInputStream(f);
			prop.load(fis);
			
		}
		
		if(prop.getProperty(browser).equalsIgnoreCase("CHROME"))
		{
			//System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, System.getProperty("user.dir")+"//drivers//chromedriver.exe");
			System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,prop.getProperty("chromedriver_exe") );
			driver =new ChromeDriver();
		}
		else if (prop.getProperty(browser).equalsIgnoreCase("FF"))
		{
			//System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"//drivers//geckodriver.exe" );
			System.setProperty("webdriver.gecko.driver",prop.getProperty("firefoxdriver_exe"));
			FirefoxOptions option=new FirefoxOptions();
			
			//binary
			option.setBinary("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
			driver=new FirefoxDriver(option);
		}
		else if(prop.getProperty(browser).equalsIgnoreCase("IE"))
		{
			//System.setProperty(InternetExplorerDriverService.IE_DRIVER_EXE_PROPERTY,System.getProperty("user.dir")+"//drivers//IEDriverServer.exe" );
			System.setProperty(InternetExplorerDriverService.IE_DRIVER_EXE_PROPERTY,prop.getProperty("iedriver_exe"));
			driver=new InternetExplorerDriver();
		}
		else if(prop.getProperty(browser).equalsIgnoreCase("EDGE"))
		{
			//System.setProperty(EdgeDriverService.EDGE_DRIVER_EXE_PROPERTY,System.getProperty("user.dir")+"//drivers//MicrosoftWebDriver.exe" );
			System.setProperty(EdgeDriverService.EDGE_DRIVER_EXE_PROPERTY,prop.getProperty("edgedriver_exe"));
			driver=new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		
		String log4jConfPath="log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		
		
	}
	
	public static void navigate(String urlKey)
	{
		driver.get(prop.getProperty(urlKey));
	}
	
	
	public void type(String locatorKey, String data) throws Exception
	{
		//driver.findElement(By.id(prop.getProperty(idElementKey))).sendKeys(data);
		getElement(locatorKey).sendKeys(data);
	}
	
	
	public void click(String locatorKey) throws Exception
	{
		//driver.findElement(By.xpath(prop.getProperty(xpathElementKey))).click();
		getElement(locatorKey).click();
	}
	
	public WebElement getElement(String locatorKey) throws Exception
	{
		WebElement e=null;
		try {
			if(locatorKey.endsWith("_id"))	
				e=driver.findElement(By.id(prop.getProperty(locatorKey)));
			else if(locatorKey.endsWith("_name"))
				e=driver.findElement(By.name(prop.getProperty(locatorKey)));
			else if(locatorKey.endsWith("_xpath"))
				e=driver.findElement(By.xpath(prop.getProperty(locatorKey)));
			else
			{
				reportFailure("Locator not correct -- " + locatorKey);
				Assert.fail("Locator not correct -- " + locatorKey);
			}
			
		} catch (Exception ex) {
			
			//fail the test & report the error
			reportFailure(ex.getMessage());
			ex.printStackTrace();
			Assert.fail("Failed the test --" + ex.getMessage());
		}
		return e;
	}
	
	//********************* Validation Function *****************
	
	public boolean verifyTitle()
	{
		return false;
	}
	
	public boolean isElementPresent(String locatorKey) throws Exception
	{
		List<WebElement> elementList=null;
		
		if(locatorKey.endsWith("_id"))	
			elementList=driver.findElements(By.id(prop.getProperty(locatorKey)));
		else if(locatorKey.endsWith("_name"))
			elementList=driver.findElements(By.name(prop.getProperty(locatorKey)));
		else if(locatorKey.endsWith("_xpath"))
			elementList=driver.findElements(By.xpath(prop.getProperty(locatorKey)));
		else
		{
			reportFailure("Locator not correct -- " + locatorKey);
			Assert.fail("Locator not correct -- " + locatorKey);
		}
		
		if(elementList.size()==0)
			return false;
		else
			return true;
	}
	
	public boolean verifyText(String locatorKey,String expectedTextKey) throws Exception
	{
		String actualText=getElement(locatorKey).getText().trim();
		String expectedText=prop.getProperty(expectedTextKey);
		if(actualText.equals(expectedText))
			return true;
		else
			return false;
	}
	
	
	
	//********************* Reporting Functions *****************
	
	public void reportPass(String msg)
	{
		test.log(LogStatus.PASS, msg);
	}
	
	public void reportFailure(String msg) throws Exception
	{
		test.log(LogStatus.FAIL, msg);
		takeScreenShot();
		Assert.fail(msg);
	}
	
	public void takeScreenShot() throws Exception
	{
		Date dt=new Date();
		String screenshotFileName = dt.toString().replace(":", "_").replace(" ", "_")+".png";
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		//FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"//screenshots//"+screenshotFileName));
		FileHandler.copy(scrFile, new File(System.getProperty("user.dir")+"//failure//"+screenshotFileName));
		
		//put screen shot file in extent reports
		test.log(LogStatus.INFO, "Screenshot --> "+ test.addScreenCapture(System.getProperty("user.dir"))+"//failure//"+screenshotFileName);
	}

}
