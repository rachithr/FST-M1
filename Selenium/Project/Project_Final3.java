package TestNG_Project;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;

//import org.testng.annotations.Parameters;

public class Project_Final3 {
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
	public void Projectfinal3() {
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

			driver.findElement(By.xpath("//a[@id=\"menu_pim_viewPimModule\"]/b")).click();

			driver.findElement(By.xpath("//a[@id=\"menu_pim_viewEmployeeList\"]")).click();

			driver.findElement(By.xpath("//input[@id=\"btnAdd\"]")).click();

			driver.findElement(By.xpath("//input[@id=\"firstName\"]")).sendKeys("Bruce");

			driver.findElement(By.xpath("//input[@id=\"lastName\"]")).sendKeys("Banner");

			driver.findElement(By.xpath("//input[@id=\"btnSave\"]")).click();
			
			Thread.sleep(3000);

			driver.findElement(By.xpath("//a[@id=\"menu_pim_viewEmployeeList\"]")).click();

			Thread.sleep(2000);

			driver.findElement(By.xpath("//input[@id=\"empsearch_employee_name_empName\"]")).sendKeys("Bruce Banner" + Keys.TAB);

			Thread.sleep(3000);

			driver.findElement(By.xpath("//input[@id=\"searchBtn\"]")).click();

			Thread.sleep(3000);

			List<WebElement> employeetable = driver.findElements(By.xpath("//*[@id=\"resultTable\"]/tbody/tr"));

			System.out.println("Number of employees in emp table is : " + employeetable.size());
			Assert.assertTrue(employeetable.size()>0);

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
