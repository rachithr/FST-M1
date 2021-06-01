package TestNG_Project;
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

public class Project_Final6 {
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
	public void Projectfinal6() {
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
			WebElement myinfotab=driver.findElement(By.xpath("//*[@id=\"menu_pim_viewMyDetails\"]/b"));
			Assert.assertTrue(myinfotab.isDisplayed());
			Assert.assertTrue(myinfotab.isEnabled());
			myinfotab.click();
			driver.findElement(By.xpath("//*[@id=\"sidenav\"]/li[9]/a")).click();					
			driver.findElement(By.xpath("//*[@id=\"addWorkExperience\"]")).click();
			driver.findElement(By.xpath("//*[@id=\"experience_employer\"]")).clear();
			driver.findElement(By.xpath("//*[@id=\"experience_employer\"]")).sendKeys("SHIELD" + Keys.TAB);
			driver.findElement(By.xpath("//*[@id=\"experience_jobtitle\"]")).clear();
			driver.findElement(By.xpath("//*[@id=\"experience_jobtitle\"]")).sendKeys("AVENGER" + Keys.TAB);
			
			driver.findElement(By.xpath("//*[@id=\"experience_from_date\"]")).clear();
			driver.findElement(By.xpath("//*[@id=\"experience_from_date\"]")).sendKeys("2005-12-10" + Keys.TAB);
			
			driver.findElement(By.xpath("//*[@id=\"experience_to_date\"]")).clear();
			driver.findElement(By.xpath("//*[@id=\"experience_to_date\"]")).sendKeys("2006-12-10" + Keys.TAB);
						
			driver.findElement(By.xpath("//*[@id=\"btnWorkExpSave\"]")).click();

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

