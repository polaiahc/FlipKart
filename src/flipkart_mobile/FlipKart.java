package flipkart_mobile;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.lang.Object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class FlipKart {
	public static AppiumDriver driver;

	//method to execute before every @Test method
	@BeforeMethod
	public void setUp() throws MalformedURLException, InterruptedException{

		//Capabilities declared
		DesiredCapabilities cap=new DesiredCapabilities();

		cap.setCapability("device", "Android");
		cap.setCapability("platformVersion", "6.0.1");
		cap.setCapability("appPackage", "com.flipkart.android");
		cap.setCapability("appActivity", "com.flipkart.android.SplashActivity");

		//iniating Android driver 
		driver=new AndroidDriver(new URL("http://127.0.0.1:4720/wd/hub"),cap);
	}

	//test method 
	@Test
	public void printNewLaunchesDeviceSpecifitaions() throws InterruptedException{


		//Navigating to mobiles from menu->electronics
		driver.findElement(By.className("android.widget.ImageButton")).click();
		driver.findElement(By.id("com.flipkart.android:id/flyout_parent_title")).click();

		Thread.sleep(5000);

		//action class using to select   mobiles tab
		TouchAction action=new TouchAction(driver);
		action.tap(720,380).perform().release();


		//Scroll down the screen to "new launches" section
		WebElement scrollElement=driver.findElement(By.xpath("//android.widget.FrameLayout[@index='4']"));
		int wide  = scrollElement.getSize().width;
		int hgt = scrollElement.getSize().height;

		int startx=wide/2;
		int endx=wide/2;

		int starty = (int) (hgt * (4));
		int endy = (int)(hgt *(0.2));
		driver.swipe(startx, starty, endx, endy, 1000);
		Thread.sleep(3000);

		//Get the number of models from "New launches"
		List<WebElement> newLaunches=driver.findElements(By.xpath("//android.widget.LinearLayout[@index='3']//android.widget.ImageView"));

		//Print device specification details 
		for(int i=0;i<newLaunches.size();i++){
			newLaunches.get(i).click();
			Thread.sleep(3000);

			List<WebElement> deviceSpecification=driver.findElements(By.xpath("//android.view.ViewGroup[@index='0']/android.view.ViewGroup/android.widget.TextView"));
			System.out.println("------------------------------------------------------------------");
			System.out.println("Device Specifications : ");

			for(int k=0;k<deviceSpecification.size();k++){
				String deviceSpec=deviceSpecification.get(k).getText();
				formatDevSpec(deviceSpec);
			}


			driver.navigate().back();
			System.out.println("------------------------------------------------------------------");

		}
	}

	@AfterMethod
	public void tearDown(){
		driver.quit();
	}

	public static void formatDevSpec(String devSpecData){

		System.out.println(devSpecData);


	}
}
