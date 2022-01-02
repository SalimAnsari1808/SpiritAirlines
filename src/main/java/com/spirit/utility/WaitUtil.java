package com.spirit.utility;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.util.function.Function;

import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.spirit.managers.FileReaderManager;

import javax.swing.*;

/**********************************************************************************************
 * 
 * Class is used to provide the Wait of automation steps. 
 * 01.Method will hold the test execution till JQuery is running on Web Application.
 * 02.Method will hold the test execution till Spirit Site is Loading on Web.
 * 03.Method will hold the test execution till Angular is loading on Web Application
 *  
 ***********************************************************************************************/

public class WaitUtil {
	
	//**********************************************************************************************
	//Method Name:untilJqueryIsDone
	//Description: Method is used to call untilJqueryIsDone(overloaded) with implicit wait
	//Input Arguments:1.WebDriver->driver instance of browser
	//Return: NA
	//Created By : Salim Ansari
	//Created On : 10-Feb-2018
	//Reviewed By:
	//Reviewed On:
	//**********************************************************************************************
    public static void untilJqueryIsDone(WebDriver driver) {
    	//call method with implicit wait
    	untilJqueryIsDone(driver,FileReaderManager.getInstance().getConfigReader().getImplicitWait());
    }
    
	//**********************************************************************************************
	//Method Name:untilJqueryIsDone
	//Description: Method is used to hold script execution until all jQuery are completed
	//Input Arguments:1.WebDriver->driver instance of browser
    //                2.timeoutInSeconds->wait time in seconds
	//Return: NA
	//Created By : Salim Ansari
	//Created On : 10-Feb-2018
	//Reviewed By:
	//Reviewed On:
	//**********************************************************************************************    
    public static void untilJqueryIsDone(WebDriver driver,Long timeoutInSeconds) {
    	//create explicit wait instance with default pooling time
    	WebDriverWait webDriverWait = new WebDriverWait(driver, timeoutInSeconds);
		webDriverWait.withTimeout(Duration.ofSeconds(timeoutInSeconds));
    	webDriverWait.ignoring(NoSuchElementException.class);
    	
    	//create explicit function to check running jQuery on application
    	Function<WebDriver,Boolean> waitCondition = new Function<WebDriver,Boolean>(){
			@Override
			public Boolean apply(WebDriver driver) {
				//check jQuery active count is zero
				boolean isJqueryCallDone = (Boolean)((JavascriptExecutor) driver).executeScript("return jQuery.active==0");
				//check the boolean value for jQuery active count is zero
				if (!isJqueryCallDone) {
					//return from function JQuery call are completed
					//System.out.println("JQuery call is in Progress");
				}
				//System.out.println("JQuery call is Called");
				//return false 
				return isJqueryCallDone;
			}
    	};
    	
    	//call function to check jQuery active count on application
		try{
			webDriverWait.until(waitCondition);
		}catch (Exception e){
			//System.out.println(e.getMessage());
		}  
    }

	//**********************************************************************************************
	//Method Name:untilPageLoadComplete
	//Description: Method is used to call untilPageLoadComplete(overloaded) with implicit wait
	//Input Arguments:1.WebDriver->driver instance of browser
	//Return: NA
	//Created By : Salim Ansari
	//Created On : 10-Feb-2018
	//Reviewed By:
	//Reviewed On:
	//**********************************************************************************************
	public static void initialPageLoadComplete(WebDriver driver) {
		//call method with implicit wait
		long timeoutInSeconds = FileReaderManager.getInstance().getConfigReader().getPageLoadTimeOut();

		//create explicit wait instance with default pooling time
		WebDriverWait webDriverWait = new WebDriverWait(driver, timeoutInSeconds);
		webDriverWait.withTimeout(Duration.ofSeconds(timeoutInSeconds));
		webDriverWait.ignoring(NoSuchElementException.class);

		//create explicit function to check running page loading on application
		Function<WebDriver,Boolean> waitCondition = new Function<WebDriver,Boolean>(){
			public Boolean apply(WebDriver driver) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//check page loading is complete
				String isPageLoaded = driver.findElement(By.xpath("//div[@class='overlay']")).getAttribute("style");
				//System.out.println(isPageLoaded);
				//check the boolean value for jQuery active count is zero
				if (isPageLoaded.contains("none")) {
					//return from function JQuery call are completed
					//System.out.println("Document Loaded");
					return true;
				}else {
					//System.out.println("Document is loading.....");
					return false;
				}

			}
		};

		//call function to check page loading status
		try{
			webDriverWait.until(waitCondition);
		}catch (Exception e){
			ValidationUtil.validateTestStep("Unable to Load Application Under Test in Browser",false);
		}
	}

	//**********************************************************************************************
	//Method Name:untilPageLoadComplete
	//Description: Method is used to call untilPageLoadComplete(overloaded) with implicit wait
	//Input Arguments:1.WebDriver->driver instance of browser
	//Return: NA
	//Created By : Salim Ansari
	//Created On : 10-Feb-2018
	//Reviewed By:
	//Reviewed On:
	//**********************************************************************************************  
    public static void untilPageLoadComplete(WebDriver driver) {
    	//call method with implicit wait
    	untilPageLoadComplete(driver, FileReaderManager.getInstance().getConfigReader().getPageLoadTimeOut());
    }
    
	//**********************************************************************************************
	//Method Name:untilPageLoadComplete
	//Description: Method is used to hold script execution until all page load completed
	//Input Arguments:1.WebDriver->driver instance of browser
    //                2.timeoutInSeconds->wait time in seconds
	//Return: NA
	//Created By : Salim Ansari
	//Created On : 10-Feb-2018
	//Reviewed By:
	//Reviewed On:
	//**********************************************************************************************  
    public static void untilPageLoadComplete(WebDriver driver, Long timeoutInSeconds) {
    	//create explicit wait instance with default pooling time
    	WebDriverWait webDriverWait = new WebDriverWait(driver, timeoutInSeconds);
		webDriverWait.withTimeout(Duration.ofSeconds(timeoutInSeconds));
    	webDriverWait.ignoring(NoSuchElementException.class);
    	
    	//create explicit function to check running page loading on application
        Function<WebDriver,Boolean> waitCondition = new Function<WebDriver,Boolean>(){
             public Boolean apply(WebDriver driver) {
                try {
                     Thread.sleep(1000);
                 } catch (InterruptedException e) {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
                 }
                 //check page loading is complete
                 String isPageLoaded = driver.findElement(By.xpath("//div[@class='overlay']")).getAttribute("style");
                 
                 //check the boolean value for jQuery active count is zero
                 if (isPageLoaded.contains("none")) {
                     //return from function JQuery call are completed
                     //System.out.println("Document Loaded");
                     return true;
                 }else {
                     //System.out.println("Document is loading.....");
                     return false;
                 }
                 
             }
        };
    	
    	//call function to check page loading status
		try{
			webDriverWait.until(waitCondition);
		}catch (Exception e){
			//System.out.println(e.getMessage());
		}  
    }
    
	//**********************************************************************************************
	//Method Name:untilAngularLoadComplete
	//Description: Method is used to call untilAngularLoadComplete(overloaded) with implicit wait
	//Input Arguments:1.WebDriver->driver instance of browser
	//Return: NA
	//Created By : Salim Ansari
	//Created On : 10-Feb-2018
	//Reviewed By:
	//Reviewed On:
	//********************************************************************************************** 
    public static void untilAngularLoadComplete(WebDriver driver) {
    	//call method with implicit wait
    	untilAngularLoadComplete(driver, FileReaderManager.getInstance().getConfigReader().getImplicitWait());
    }
   
	//**********************************************************************************************
	//Method Name:untilPageLoadComplete
	//Description: Method is used to hold script execution until all Angular load completed
	//Input Arguments:1.WebDriver->driver instance of browser
    //                2.timeoutInSeconds->wait time in seconds
	//Return: NA
	//Created By : Salim Ansari
	//Created On : 10-Feb-2018
	//Reviewed By:
	//Reviewed On:
	//**********************************************************************************************  
    public static void untilAngularLoadComplete(WebDriver driver, Long timeoutInSeconds) {
    	//create explicit wait instance
    	WebDriverWait webDriverWait = new WebDriverWait(driver, timeoutInSeconds);
		webDriverWait.withTimeout(Duration.ofSeconds(timeoutInSeconds));
    	webDriverWait.ignoring(NoSuchElementException.class);
    	
    	//create explicit function to check running page loading on application
    	Function<WebDriver,Boolean> waitCondition = new Function<WebDriver,Boolean>(){
			@Override
			public Boolean apply(WebDriver driver) {
				
				String angularReadyScript = "return angular.element(document).injector().get('$http').pendingRequests.length === 0";
				
				//check page loading is complete
				boolean isAngularCallDone = Boolean.valueOf(((JavascriptExecutor) driver).executeScript(angularReadyScript).toString());
				//check the boolean value for jQuery active count is zero
				if (!isAngularCallDone) {
					//return from function JQuery call are completed
					//System.out.println("ANGULAR is NOT Ready!");
				}
				//System.out.println("ANGULAR is Ready!");
				//return false 
				return isAngularCallDone;
			}
    	};
    	
    	//call function to check page loading status
		try{
			webDriverWait.until(waitCondition);
		}catch (Exception e){
			//System.out.println(e.getMessage());
		} 
    }
    
	//**********************************************************************************************
	//Method Name:untilPageURLVisible
	//Description: Method is used to hold script execution until URL appear on application
	//Input Arguments:1.WebDriver->driver instance of browser
    //                2.expectedURL->url to be appear in application
	//Return: NA
	//Created By : Salim Ansari
	//Created On : 15-Mar-2018
	//Reviewed By:
	//Reviewed On:
	//********************************************************************************************** 
    public static void untilPageURLVisible(WebDriver driver, String expectedURL) {
    	//call method with implicit wait
    	untilPageURLVisible(driver, expectedURL, FileReaderManager.getInstance().getConfigReader().getImplicitWait());
    }
    
	//**********************************************************************************************
	//Method Name:untilPageURLVisible
	//Description: Method is used to hold script execution until URL appear on application
	//Input Arguments:1.WebDriver->driver instance of browser
    //                2.expectedURL->url to be appear in application
    //				  3.timeoutInSeconds->wait time in seconds
	//Return: NA
	//Created By : Salim Ansari
	//Created On : 15-Mar-2018
	//Reviewed By:
	//Reviewed On:
	//********************************************************************************************** 
    public static void untilPageURLVisible(WebDriver driver, String expectedURL, Long timeoutInSeconds) {
    	//create explicit wait instance
    	WebDriverWait webDriverWait = new WebDriverWait(driver, timeoutInSeconds);
		webDriverWait.withTimeout(Duration.ofSeconds(timeoutInSeconds));
    	webDriverWait.ignoring(NoSuchElementException.class);
    	//System.out.println(expectedURL);
    	
    	//create explicit function to check running page loading on application
    	Function<WebDriver,Boolean> waitCondition = new Function<WebDriver,Boolean>(){
			@Override
			public Boolean apply(WebDriver driver) {
				//System.out.println(driver.getCurrentUrl());
				//check page loading is complete
				boolean isURLLoadDone = driver.getCurrentUrl().contains(expectedURL);
				//check the boolean value for jQuery active count is zero
				if (!isURLLoadDone) {
					//return from function JQuery call are completed
					//System.out.println("ANGULAR is NOT Ready!");
				}
				//System.out.println("ANGULAR is Ready!");
				//return false 
				return isURLLoadDone;
			}
    	};
    	
    	//call function to check page loading status
		try{
			webDriverWait.until(waitCondition);
		}catch (Exception e){
			//System.out.println(e.getMessage());
		} 
    }
    
    
	//**********************************************************************************************
	//Method Name:untilTimeCompleted
	//Description: Method is used to hold script execution until URL appear on application
	//Input Arguments:1.timeMilliSecond->driver instance of browser
	//Return: NA
	//Created By : Salim Ansari
	//Created On : 15-Mar-2018
	//Reviewed By:
	//Reviewed On:
	//********************************************************************************************** 
    public static void untilTimeCompleted(int timeMilliSecond) {
    	try {
			Thread.sleep(timeMilliSecond);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	//**********************************************************************************************
	//Method Name:untilJavaScriptPageLoadComplete
	//Description: Method is used to hold script execution till page load is complete
	//Input Arguments:1.driver->driver instance of browser
	//				  2.element->clickable element
	//Return: NA
	//Created By : Salim Ansari
	//Created On : 31-May-2019
	//Reviewed By:
	//Reviewed On:
	//**********************************************************************************************
	public static void untilJavaScriptPageLoadComplete(WebDriver driver){
		//call explicit wait method to wait for element to be clickable
		untilJavaScriptPageLoadComplete(driver, FileReaderManager.getInstance().getConfigReader().getPageLoadTimeOut());
	}

	//**********************************************************************************************
	//Method Name:untilJavaScriptPageLoadComplete
	//Description: Method is used to hold script execution till page load is complete
	//Input Arguments:1.driver->driver instance of browser
	//				  2.element->clickable element
	//Return: NA
	//Created By : Salim Ansari
	//Created On : 31-May-2019
	//Reviewed By:
	//Reviewed On:
	//**********************************************************************************************
	public static void untilJavaScriptPageLoadComplete(WebDriver driver, long timeOut){
		//call explicit wait method to wait for element to be clickable
		new WebDriverWait(driver, timeOut).until(
				webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
	}

	//**********************************************************************************************
	//Method Name:untilElementIsClickable
	//Description: Method is used to hold script execution till element to be clickable
	//Input Arguments:1.driver->driver instance of browser
	//				  2.element->clickable element
	//Return: NA
	//Created By : Salim Ansari
	//Created On : 31-May-2019
	//Reviewed By:
	//Reviewed On:
	//**********************************************************************************************
    public static void untilElementIsClickable(WebDriver driver, WebElement element){
    	//call explicit wait method to wait for element to be clickable
		untilElementIsClickable( driver, element, FileReaderManager.getInstance().getConfigReader().getExplicitWait());
	}

	//**********************************************************************************************
	//Method Name:untilElementIsClickable
	//Description: Method is used to hold script execution till element to be clickable
	//Input Arguments:1.driver->driver instance of browser
	//				  2.element->clickable element
	//				  3.timeOut->timeout to wait for element
	//Return: NA
	//Created By : Salim Ansari
	//Created On : 31-May-2019
	//Reviewed By:
	//Reviewed On:
	//**********************************************************************************************
	public static void untilElementIsClickable(WebDriver driver, WebElement element, long timeOut){
    	//create explicit wait object
		WebDriverWait explictWait = new WebDriverWait(driver,timeOut);

		//wait for element to be clickable
		explictWait.until(ExpectedConditions.elementToBeClickable(element));
		//explictWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='user_email']")));


	}

	//**********************************************************************************************
	//Method Name:untilElementIsClickable
	//Description: Method is used to hold script execution till element to be clickable
	//Input Arguments:1.driver->driver instance of browser
	//				  2.By->clickable element
	//Return: NA
	//Created By : Salim Ansari
	//Created On : 31-May-2019
	//Reviewed By:
	//Reviewed On:
	//**********************************************************************************************
	public static void untilElementIsClickable(WebDriver driver, By elementIdentifier){
		//call explicit wait method to wait for element to be clickable
		untilElementIsClickable( driver, driver.findElement(elementIdentifier), FileReaderManager.getInstance().getConfigReader().getExplicitWait());
	}

	//**********************************************************************************************
	//Method Name:untilElementIsClickable
	//Description: Method is used to hold script execution till element to be clickable
	//Input Arguments:1.driver->driver instance of browser
	//				  2.By->clickable element
	//				  3.timeOut->timeout to wait for element
	//Return: NA
	//Created By : Salim Ansari
	//Created On : 31-May-2019
	//Reviewed By:
	//Reviewed On:
	//**********************************************************************************************
	public static void untilElementIsClickable(WebDriver driver, By elementIdentifier, long timeOut){
		//create explicit wait object
		WebDriverWait explictWait = new WebDriverWait(driver,timeOut);

		//wait for element to be clickable
		//explictWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(elementIdentifier)));

		//create explicit wait instance with default pooling time
		WebDriverWait webDriverWait = new WebDriverWait(driver, timeOut);
		webDriverWait.withTimeout(Duration.ofSeconds(timeOut));
		webDriverWait.ignoring(NoSuchElementException.class);

		//create explicit function to check running jQuery on application
		Function<WebDriver,Boolean> waitCondition = new Function<WebDriver,Boolean>(){
			@Override
			public Boolean apply(WebDriver driver) {
				//check jQuery active count is zero
				boolean isJqueryCallDone = driver.findElements(elementIdentifier).size()>0;
				//check the boolean value for jQuery active count is zero
				if (!isJqueryCallDone) {
					//return from function JQuery call are completed
					//System.out.println("JQuery call is in Progress");
				}
				//System.out.println("JQuery call is Called");
				//return false
				return isJqueryCallDone;
			}
		};

		//call function to check jQuery active count on application
		try{
			webDriverWait.until(waitCondition);
		}catch (Exception e){
			//System.out.println(e.getMessage());
		}
	}

	public static void waitforWindowsElement(WindowsDriver driver, String IDType, String elementLocator, int timeOut) {
		boolean iterate = true;
		WindowsElement windowElement = null;


		for(int counterValue=0;counterValue<=timeOut;counterValue++) {
			try {
				switch (IDType) {
					case "id":
						windowElement = (WindowsElement) driver.findElementsByAccessibilityId(elementLocator);
						break;
					case "xpath":
						windowElement = (WindowsElement) driver.findElementsByXPath(elementLocator);
						break;
					case "name":
						windowElement = (WindowsElement) driver.findElementsByName(elementLocator);
				}
			} catch (Exception ex) {
				//Do Nothing
			}
			System.out.println(counterValue);

			if(windowElement!=null){
				System.out.println(counterValue);
				return;
			}
		}
	}

	public static void untilSkySpeedPageLoadComplete (WebDriver driver) {
		WaitUtil.untilTimeCompleted(500);

		while(driver.findElements(By.xpath("//Text[@AutomationId='_labelPleaseWait']")).size() > 0);{
			WaitUtil.untilTimeCompleted(1000);
		}
	}

}
