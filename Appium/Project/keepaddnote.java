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

public class keepaddnote {
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
		driver.findElementById("editable_title").sendKeys("Project 2 Google keep");
		driver.findElementByAccessibilityId("Navigate up").click();

		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("index_note_title")));
		List<MobileElement> Noteadded = driver.findElementsById("index_note_title");
		//to find the top most note
		assertEquals(Noteadded.get(0).getText(), "Project 2 Google keep");


	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
