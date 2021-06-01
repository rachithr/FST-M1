package TestNG_Project;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;

public class Project_Final1 {
	WebDriver driver;

	@BeforeMethod
	public void beforeMethod() {
		// Create a new instance of the Firefox driver
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// Open browser
		driver.get("http://alchemy.hguy.co/orangehrm");
	}

	@Test
	@Parameters({ "sUsername", "sPassword" })
	public void Projectfinal1(String sUsername, String sPassword) {
		// Check the title of the page
		String title = driver.getTitle();

		// Print the title of the page
		System.out.println("Page title is: " + title);

		// Assertion for page title
		Assert.assertEquals("OrangeHRM", title);
		
		// Find the username and password fields
		WebElement username = driver.findElement(By.id("txtUsername"));
		WebElement password = driver.findElement(By.id("txtPassword"));

		// Enter credentials
		username.sendKeys(sUsername);
		password.sendKeys(sPassword);

		// Click login
		driver.findElement(By.id("btnLogin")).click();

		// Read login message
		//String loginMessage = driver.findElement(By.id("action-confirmation")).getText();
		//Assert.assertEquals("Welcome Back, admin", loginMessage);

	}

	@AfterMethod
	public void afterMethod() {
		// Close the browser
		driver.quit();;
	}

}
