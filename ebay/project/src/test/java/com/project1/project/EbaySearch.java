package com.project1.project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class EbaySearch {

	Properties prop;

	File file;

	static WebDriver driver;

	@BeforeTest
	public void Prop_load() throws IOException

	{

		file = new File("Resources/PropertyFile.properties");

		FileInputStream fileinput = null;

		try {

			fileinput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		prop = new Properties();
		try {

			prop.load(fileinput);

			// System.out.println(prop.getProperty("URL"));
		} catch (IOException e) {
			e.printStackTrace();

		}

	}

	@Test(priority=1)
	public void browserload() throws InterruptedException

	{

		System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get(prop.getProperty("URL"));
	}

	@Test(priority=2)
	public void search_context() {
		driver.findElement(By.cssSelector("input[type=text][placeholder='Search for anything']"))
				.sendKeys(prop.getProperty("Search_context"));

		driver.findElement(By.cssSelector("input[type=submit][id='gh-btn']")).click();

		driver.findElement(By.xpath("//nav/ul/li[4]")).click();

		List<WebElement> list = driver.findElements(By.cssSelector(".vip"));
		for (WebElement element : list) {
			String link = element.getAttribute("href");

			System.out.println(list.size());

			// System.out.println(element.getTagName() + "=" + link);

			Random ran = new Random();

			WebElement randomElement = list.get(ran.nextInt(list.size()));
			randomElement.getText();

			WebDriverWait wait = new WebDriverWait(driver, 20);

			wait.until(ExpectedConditions.elementToBeClickable(randomElement));

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			randomElement.click();

			driver.findElement(By.id("isCartBtn_btn")).click();

			driver.findElement(By.id("ptcBtnBottom")).click();

		}

	}

}
