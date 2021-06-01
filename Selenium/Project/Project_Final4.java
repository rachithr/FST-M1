package TestNG_Project;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;

//import org.testng.annotations.Parameters;

public class Project_Final4 {
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
	public void Projectfinal4() {
		// Check the title of the page
		String title = driver.getTitle();

		// Print the title of the page
		System.out.println("Page title is: " + title);

		// Assertion for page title
		Assert.assertEquals("OrangeHRM", title);

		WebElement logolnk = driver.findElement(By.xpath("//*[@id=\"divLogo\"]/img"));
		String linktext = logolnk.getAttribute("src");
		System.out.println("Logo link is :  " + linktext);

		// Find the username and password fields
		WebElement username = driver.findElement(By.id("txtUsername"));
		WebElement password = driver.findElement(By.id("txtPassword"));

		// Enter credentials
		username.sendKeys("orange");
		password.sendKeys("orangepassword123");

		// Click login

		try {
			driver.findElement(By.id("btnLogin")).click();

			Thread.sleep(3000);

			driver.findElement(By.xpath("//*[@id=\"menu_pim_viewMyDetails\"]/b")).click();

			driver.findElement(By.xpath("//*[@id=\"btnSave\"]")).click();

			// driver.findElement(By.xpath("//input[@id=\"btnAdd\"]")).click();
			driver.findElement(By.xpath("//*[@id=\"personal_txtEmpFirstName\"]")).clear();
			driver.findElement(By.xpath("//*[@id=\"personal_txtEmpFirstName\"]")).sendKeys("Chacha");
			driver.findElement(By.xpath("//*[@id=\"personal_txtEmpLastName\"]")).clear();
			driver.findElement(By.xpath("//*[@id=\"personal_txtEmpLastName\"]")).sendKeys("Chaudhary");

			driver.findElement(By.xpath("//*[@id=\"personal_optGender_1\"]")).click();
			WebElement nationality = driver.findElement(By.xpath("//*[@id=\"personal_cmbNation\"]"));

			Select List = new Select(nationality);
			List.selectByVisibleText("Indian");

			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id=\"personal_DOB\"]")).clear();
			driver.findElement(By.xpath("//*[@id=\"personal_DOB\"]")).sendKeys("1995-12-10" + Keys.TAB);

			driver.findElement(By.xpath("//*[@id=\"btnSave\"]")).click();

			Thread.sleep(2000);

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	@AfterMethod
	public void afterMethod() {
		// Close the browser
		driver.quit();
		;
	}

}
