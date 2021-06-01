package Project;


import org.openqa.selenium.Point;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.TouchAction.*;
import io.appium.java_client.android.AndroidDriver;
import static org.testng.Assert.assertEquals;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChromePopuplogin {
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
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.training-support.net/selenium");
		driver.findElement(MobileBy.AndroidUIAutomator("UiScrollable(UiSelector().scrollable(true)).flingForward().scrollIntoView(text(\"Popups\"))")).click();
		driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().className(\"android.widget.Button\").text(\"Sign In\")")).click();	
	}
	
	@Test
	public void TestCorrectLoginpop(){
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		MobileElement Username=driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().className(\"android.widget.EditText\").resourceId(\"username\")"));		
		MobileElement Passwd=driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().className(\"android.widget.EditText\").resourceId(\"password\")"));		
		Username.click();
		Username.sendKeys("admin");
		Passwd.sendKeys("password");		
		driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().className(\"android.widget.Button\").text(\"Log in\")")).click();	
		String logmessage=driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().className(\"android.view.View\").resourceId(\"action-confirmation\")")).getText();		
		assertEquals(logmessage,"Welcome Back, admin");
	}
	@Test(enabled = false)
	public void TestincorrectLoginpop(){
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		MobileElement Username=driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().className(\"android.widget.EditText\").resourceId(\"username\")"));		
		MobileElement Passwd=driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().className(\"android.widget.EditText\").resourceId(\"password\")"));		
		Username.sendKeys("admin");
		Passwd.sendKeys("asdsa");		
		driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().className(\"android.widget.Button\").text(\"Log in\")")).click();	
		String logmessage=driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().className(\"android.view.View\").resourceId(\"action-confirmation\")")).getText();		
		assertEquals(logmessage,"Invalid Credentials");
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
