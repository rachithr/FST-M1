package TestNG_Project;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;

//import org.testng.annotations.Parameters;

public class Project_Final8 {
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
	public void Projectfinal8() {
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
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"menu_pim_viewMyDetails\"]/b")).click();

			driver.findElement(By.xpath("//*[@id=\"sidenav\"]/li[3]/a")).click();
			
			//*[@id="emgcontact_list"]/tbody/tr[1]/td[2]

			
			//List<WebElement> emergencycontacttable= driver.findElements(By.xpath("//*[@id=\"emgcontact_list\"]/tbody/tr"));
			
			List<WebElement> emergencycontacttable = driver.findElements(By.className("emgContactName"));
			//Looping through the list
			for(WebElement column : emergencycontacttable) {
			    System.out.println(column.getText());
			}
			
			List<WebElement> emergencycontacttable2= driver.findElements(By.xpath("//*[@id=\"emgcontact_list\"]/tbody/tr"));
			for(int i=1; i<=emergencycontacttable2.size(); i++) {
			    WebElement row = driver.findElement(By.xpath("//*[@id=\"emgcontact_list\"]/tbody/tr[" + i + "]"));
			    System.out.println("Row " + i + ": " + row.getText());
			}
			
			
			
			
			Thread.sleep(9000);
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
