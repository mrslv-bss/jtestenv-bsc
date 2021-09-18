package com.w2a.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNewRelativeLocators {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.monobank.ua/");
		driver.manage().window().maximize();
		
		driver.findElement(By.xpath("(//button[@type='submit'])[1]")).click();
		
//		driver.findElement(RelativeLocator.withTagName("button").above(By.linkText("Sign?"))).click;
	}

}
