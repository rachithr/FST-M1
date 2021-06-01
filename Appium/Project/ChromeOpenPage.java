package Project;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import static org.testng.Assert.assertEquals;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChromeOpenPage {
	AndroidDriver<MobileElement> driver;
	WebDriverWait wait;

	@BeforeClass
	public void setUp() throws MalformedURLException {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("deviceName", "668353e5");
		desiredCapabilities.setCapability("platformName", "android");
		desiredCapabilities.setCapability("automationName", "UiAutomator2");
		desiredCapabilities.setCapability("noReset", true);
		desiredCapabilities.setCapability("appPackage", "com.android.chrome");
		desiredCapabilities.setCapability("appActivity", "com.google.android.apps.chrome.Main");

		URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

		driver = new AndroidDriver<MobileElement>(remoteUrl, desiredCapabilities);
		wait = new WebDriverWait(driver, 10);
	}

	@Test
	public void sampleTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.training-support.net/selenium");
		// driver.findElementByXPath("//android.view.View[@content-desc='To-Do List
		// Elements get added at run time']").click();
		driver.findElement(MobileBy.AndroidUIAutomator(
				"UiScrollable(UiSelector().scrollable(true)).flingForward().scrollIntoView(text(\"To-Do List\"))"))
				.click();
		String[] todotasks = { "Add tasks to list", "Get number of tasks", "Clear the list" };
		Thread.sleep(5000);
		//click on clear
		//wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AndroidUIAutomator("UiSelector().className(\"android.widget.TextView\").text(\"Clear List\")")));
		driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().className(\"android.widget.TextView\").instance(1)")).click();
		Thread.sleep(1000);
		for (String todotask : todotasks) {
			driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().resourceId(\"taskInput\")")).click();
			driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().resourceId(\"taskInput\")")).sendKeys(todotask);
			driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().className(\"android.widget.Button\").text(\"Add Task\")")).click();
			Thread.sleep(3000);
		}
		
		//check this for issue
		//List<MobileElement> todolist=driver.findElements(MobileBy.AndroidUIAutomator("UiSelector().resourceId(\"tasksList\").childSelector(new UiSelector().className(\"android.view.View\"))"));
	
		MobileElement Parentobj=driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().resourceId(\"tasksList\")"));
		List<MobileElement> todolist=Parentobj.findElements(MobileBy.AndroidUIAutomator("UiSelector().className(\"android.view.View\")"));
		
		assertEquals(todolist.size(), 3);	
		
		for(MobileElement todo : todolist) {
			Thread.sleep(2000);
			todo.click();
		}
		
		Thread.sleep(8000);
		driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().className(\"android.widget.TextView\").instance(1)")).click();

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
