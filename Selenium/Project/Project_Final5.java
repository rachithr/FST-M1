package TestNG_Project;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;

//import org.testng.annotations.Parameters;

public class Project_Final5 {
	WebDriver driver;

	@BeforeMethod
	public void beforeMethod() {
		// Create a new instance of the Firefox driver
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		// Open browser
		driver.get("http://alchemy.hguy.co/orangehrm");
	}

	@Test
	public void Projectfinal5() {
		String title = driver.getTitle();
		System.out.println("Page title is: " + title);
		Assert.assertEquals("OrangeHRM", title);
		WebElement logolnk = driver.findElement(By.xpath("//*[@id=\"divLogo\"]/img"));
		String linktext = logolnk.getAttribute("src");
		System.out.println("Logo link is :  " + linktext);
		// Find the username and password fields
		WebElement username = driver.findElement(By.id("txtUsername"));
		WebElement password = driver.findElement(By.id("txtPassword"));
		username.sendKeys("orange");
		password.sendKeys("orangepassword123");

		try {
			driver.findElement(By.id("btnLogin")).click();
			Thread.sleep(3000);
			WebElement directorytab=driver.findElement(By.xpath("//*[@id=\"menu_directory_viewDirectory\"]/b"));
			Assert.assertTrue(directorytab.isDisplayed());
			Assert.assertTrue(directorytab.isEnabled());
			directorytab.click();
			WebElement headerdir=driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div[1]/h1"));					
			Assert.assertEquals(headerdir.getText(), "Search Directory");
			Thread.sleep(2000);

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	@AfterMethod
	public void afterMethod() {
		// Close the browser
		driver.quit();
	}

}

