package com.project.DemoProject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TC_005 extends BasePage
{

	public static void main(String[] args) throws Exception 
	{
		
		OpenBrowser("chromebrowser");
		navigate("amazonurl");
		
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("sony");
		driver.findElement(By.id("twotabsearchtextbox")).clear();
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("samsung");
		
		driver.findElement(By.name("field-keywords")).clear();
		
		//driver.findElement(By.className("nav-input")).sendKeys("philips");
		
		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("philips");
		
		driver.findElement(By.cssSelector("#twotabsearchtextbox")).clear();
		
		//driver.findElement(By.linkText("Customer Service")).click();
		
		driver.findElement(By.partialLinkText("Customer Service")).click();
		
		List<WebElement> links = driver.findElements(By.tagName("a"));
		for(int i=0;i<links.size();i++)
			System.out.println(links.get(i).getText());
		
		
		
	}

}
