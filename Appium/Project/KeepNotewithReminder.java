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

public class KeepNotewithReminder {
	AndroidDriver<MobileElement> driver;
	WebDriverWait wait;

	@BeforeClass
	public void setUp() throws MalformedURLException {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("deviceName", "668353e5");
		desiredCapabilities.setCapability("platformName", "android");
		desiredCapabilities.setCapability("automationName", "UiAutomator2");
		desiredCapabilities.setCapability("noReset", true);
		desiredCapabilities.setCapability("appPackage", "com.google.android.keep");
		desiredCapabilities.setCapability("appActivity", ".activities.BrowseActivity");

		URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

		driver = new AndroidDriver<MobileElement>(remoteUrl, desiredCapabilities);
		wait = new WebDriverWait(driver, 5);
	}

	@Test
	public void sampleTest() {
		String Notetoadd = "Complete Activity with Google Keep";
		wait.until(ExpectedConditions.elementToBeClickable(MobileBy.id("new_note_button")));
		driver.findElementById("new_note_button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("edit_note_text")));
		driver.findElementById("edit_note_text").click();
		driver.findElementById("edit_note_text").sendKeys(Notetoadd);
		driver.findElementById("editable_title").click();
		driver.findElementById("editable_title").sendKeys("Project 3 Google keep with Reminder");
		driver.findElementById("menu_reminder").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("menu_text")));

		// Since the id is same, making list of id and selecting by text
		List<MobileElement> menutxts = driver.findElementsById("menu_text");
		for (MobileElement menutxt : menutxts) {
			System.out.println(menutxt.getText());
			if (menutxt.getText().contains("Pick a date & time")) {
				menutxt.click();
				break;
			}
		}

		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("time_spinner")));
		driver.findElementById("time_spinner").click();
		// Since the id is same, making list of id and selecting by text
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("text")));
		List<MobileElement> txts = driver.findElementsById("text");
		for (MobileElement txt : txts) {
			System.out.println(txt.getText());
			if (txt.getText().contains("Afternoon")) {
				txt.click();
				break;
			}
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("save")));
		driver.findElementById("save").click();

		driver.findElementByAccessibilityId("Navigate up").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Open navigation drawer")));
		driver.findElementByAccessibilityId("Open navigation drawer").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("title")));
		// Reminder is always at index 1, notes at 0
		driver.findElementsById("title").get(1).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("index_note_title")));
		List<MobileElement> Noteadded = driver.findElementsById("index_note_title");
		assertEquals(Noteadded.get(0).getText(), "Project 3 Google keep with Reminder");

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
