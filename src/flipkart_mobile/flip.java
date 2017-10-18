package flipkart_mobile;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.lang.Object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class flip {
	public static AppiumDriver driver;
	public static void main(String[] args) throws Exception {

		DesiredCapabilities cap=new DesiredCapabilities();

		cap.setCapability("device", "Android");
		cap.setCapability("platformVersion", "6.0.1");
		cap.setCapability("appPackage", "com.flipkart.android");
		//cap.setCapability("appPackage", "net.one97.paytm");
		//cap.setCapability("appActivity", "com.android.contacts.activities.PeopleActivity");
		cap.setCapability("appActivity", "com.flipkart.android.SplashActivity");
		driver=new AndroidDriver(new URL("http://127.0.0.1:4720/wd/hub"),cap);

		driver.findElement(By.className("android.widget.ImageButton")).click();
		driver.findElement(By.id("com.flipkart.android:id/flyout_parent_title")).click();
		//	driver.findElement(By.xpath("//android.widget.TextView[contains(text(),'Mobiles')]")).click();

		Thread.sleep(10000);
		//	WebElement mobilesSelect=driver.findElement(By.xpath("//android.widget.TextView[contains(text(),‘Mobiles’)]"));
		TouchAction action=new TouchAction(driver);
		//action.longPress((mobilesS
		//action.longPress(mobilesSelect).perform().release();
		//action.longPress(720, 380 , 10).perform().release();
		action.tap(720,380).perform().release();

		//	driver.findElementByAndroidUIAutomator(new UiSelector().textContains("Mobiles")).click();
		//driver.findElement(By.xpath("//android.widget.TextView[1]")).click();

		List<WebElement> segmentsOfMobilecategory=driver.findElements(By.id("com.flipkart.android:id/banner_image"));
		System.out.println("segments size: "+segmentsOfMobilecategory.size());
		
		//driver.scrollTo("SMARTPHONES JUST GOT SMARTER");

		//top picks in mobile category
		//driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'com.flipkart.android:id/captionViewLeft')]")).click();

	//	driver.scrollTo("NEW LAUNCHES");
		//driver.scrollToExact("NEW LAUNCHES");
	//new launches layout
	//	driver.swipe(arg0, arg1, arg2, arg3, arg4);
		//xpath("//android.widget.LinearLayout[@index='1']/android.widget.Button[@index='1']")

		//to scroll the element to up by using swipe
		WebElement scrollElement=driver.findElement(By.xpath("//android.widget.FrameLayout[@index='3']"));
		int wide  = scrollElement.getSize().width;
		int hgt = scrollElement.getSize().height;
		
		int startx=wide/2;
		int endx=wide/2;
		
		int starty = (int) (hgt * (4));
		int endy = (int)(hgt *(0.2));
		driver.swipe(startx, starty, endx, endy, 1000);
		Thread.sleep(3000);
		
		//Get the total mobiles list for single layout
		List<WebElement> newLaunches=driver.findElements(By.xpath("//android.widget.LinearLayout[@index='3']//android.widget.ImageView"));
		
		//Device Specifications
		for(int i=0;i<newLaunches.size();i++){
			newLaunches.get(i).click();
			Thread.sleep(3000);
		//	String deviceName=driver.findElement(By.xpath("//android.widget.TextView[@content-desc='product title']")).getText();
		//	System.out.println("device details: "+deviceName);
			
//			System.out.println("----------------------------------------------------------------------------------");
//			System.out.println(driver.findElement(By.xpath("//android.view.ViewGroup[@index='1']/android.view.ViewGroup/android.widget.TextView")).getText());
//			System.out.println("----------------------------------------------------------------------------------");
			List<WebElement> deviceSpecification=driver.findElements(By.xpath("//android.view.ViewGroup[@index='0']/android.view.ViewGroup/android.widget.TextView"));
			
			System.out.println("device specifications : ");
			
			for(int k=0;k<deviceSpecification.size();k++){
				String deviceSpec=deviceSpecification.get(k).getText();
				formatDevSpec(deviceSpec);
			}
			driver.navigate().back();
			System.out.println("----------------------------------------------------------------------------------");
		}
	}
	
	public static void formatDevSpec(String devSpecData){
		
		System.out.println(devSpecData);
		
		
	}

}
