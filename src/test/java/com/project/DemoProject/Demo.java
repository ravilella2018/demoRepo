package com.project.DemoProject;

import java.util.Date;

import org.testng.annotations.Test;

public class Demo {
  @Test
  public void f() 
  {
	  
	  Date dt=new Date();
		String fileName = dt.toString().replace(":", "_").replace(" ", "_");
		System.out.println(fileName);
  }
}
