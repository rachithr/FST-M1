package Project;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import static org.testng.Assert.assertEquals;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleTask {
	AndroidDriver<MobileElement> driver;
	WebDriverWait wait;

	@BeforeClass
	public void setUp() throws MalformedURLException {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("deviceName", "668353e5");
		desiredCapabilities.setCapability("platformName", "android");
		desiredCapabilities.setCapability("automationName", "UiAutomator2");
		desiredCapabilities.setCapability("noReset", true);
		desiredCapabilities.setCapability("appPackage", "com.google.android.apps.tasks");
		desiredCapabilities.setCapability("appActivity", ".ui.TaskListsActivity");

		URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

		driver = new AndroidDriver<MobileElement>(remoteUrl, desiredCapabilities);
		wait = new WebDriverWait(driver, 5);
	}

	@Test
	public void sampleTest() {
		String[] taskstoadd = { "Complete Activity with Google Tasks", "Complete Activity with Google Keep",
				"Complete the second Activity Google Keep" };

		wait.until(ExpectedConditions.elementToBeClickable(MobileBy.id("tasks_fab")));
		for (String tasktoadd : taskstoadd) {
			driver.findElementById("tasks_fab").click();
			wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("add_task_title")));
			driver.findElementById("add_task_title").click();
			driver.findElementById("add_task_title").sendKeys(tasktoadd);
			driver.findElementById("add_task_done").click();
		}
		
		wait.until(ExpectedConditions.numberOfElementsToBe(MobileBy.id("task_name"), 3));
		List<MobileElement> tasksadded = driver.findElementsById("task_name");
		assertEquals(tasksadded.size(), 3);
		assertEquals(tasksadded.get(0).getText(), "Complete the second Activity Google Keep");
		assertEquals(tasksadded.get(1).getText(), "Complete Activity with Google Keep");
		assertEquals(tasksadded.get(2).getText(), "Complete Activity with Google Tasks");

		//Cleanup the created task for re-run
		for (String tasktodelete : taskstoadd) {
			wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId(tasktodelete)));
			driver.findElementByAccessibilityId(tasktodelete).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("delete_task_option")));
			driver.findElementById("delete_task_option").click();
		}
	}


	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
