package com.w2a.testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestTabs {

	public static void main(String[] args){
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://google.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		System.out.println("First window : "+driver.getTitle());
		
//		driver.switchTo().newWindow(WindowType.TAB);
		
		driver.get("http://gmail.com");
		
		System.out.println("Second window : "+driver.getTitle());
		driver.findElement(By.linkText("Sign in")).click();
		
	}
	
}
