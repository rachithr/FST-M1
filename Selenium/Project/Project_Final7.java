package TestNG_Project;


import java.util.List;
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

public class Project_Final7 {
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
	public void Projectfinal7() {
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

			driver.findElement(By.xpath("//*[@id=\"menu_dashboard_index\"]/b")).click();

			driver.findElement(By.xpath("//*[@id=\"dashboard-quick-launch-panel-menu_holder\"]/table/tbody/tr/td[4]/div")).click();

			WebElement leavetype=driver.findElement(By.xpath("//*[@id=\"applyleave_txtLeaveType\"]"));
			
			Select List = new Select(leavetype);
			List.selectByVisibleText("Paid Leave");		
			
			driver.findElement(By.xpath("//*[@id=\"applyleave_txtFromDate\"]")).clear();
			driver.findElement(By.xpath("//*[@id=\"applyleave_txtFromDate\"]")).sendKeys("2021-12-16"+ Keys.TAB);
			
			driver.findElement(By.xpath("//*[@id=\"applyleave_txtToDate\"]")).clear();
			driver.findElement(By.xpath("//*[@id=\"applyleave_txtToDate\"]")).sendKeys("2021-12-16"+ Keys.TAB);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"applyBtn\"]")).click();
						
			Thread.sleep(9000);

			driver.findElement(By.xpath("//*[@id=\"menu_leave_viewMyLeaveList\"]")).click();

			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"calFromDate\"]")).clear();
			driver.findElement(By.xpath("//*[@id=\"calFromDate\"]")).sendKeys("2021-12-16" + Keys.TAB);
			driver.findElement(By.xpath("//*[@id=\"calToDate\"]")).clear();
			driver.findElement(By.xpath("//*[@id=\"calToDate\"]")).sendKeys("2021-12-16" + Keys.TAB);
			Thread.sleep(3000);

			driver.findElement(By.xpath("//*[@id=\"btnSearch\"]")).click();


			Thread.sleep(5000);
			
			//*[@id="resultTable"]/tbody/tr[1]/td[6]/a
			List<WebElement> leavetable= driver.findElements(By.xpath("//*[@id=\"resultTable\"]/tbody/tr"));

			//Assert.assertTrue(leavetext.getText().contains("Pending Approval"));
			//List<String> text= new ArrayList<String>();
			for(int i=1; i<=leavetable.size(); i++) {
			    WebElement row = driver.findElement(By.xpath("//*[@id=\"resultTable\"]/tbody/tr[" + i + "]/td[6]/a"));
			    System.out.println("Row " + i + ": " + row.getText());
			    //text.add(row.getText());
			    if(row.getText().contains("Pending Approval")) { 	
			    	Assert.assertTrue(row.getText().contains("Pending Approval"));	
			    	break;
			    }			    		
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
