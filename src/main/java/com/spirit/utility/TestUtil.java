package com.spirit.utility;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

/**********************************************************************************************
 * 
 * Class is used to provide the Common Action/Method used during scripting. 
 * 01.checkInternetConnection
 * 02.checkMaintenanceProgess
 * 03.switchToWindow
 * 04.switchToFrame
 * 05.acceptAlertBox
 * 06.isAlertPresent
 * 07.takeScreenshotAtEndOfTest
 * 08.currentDateTimeString
 * 09.verifyLinks
 * 10.generateXPATH
 *  
 ***********************************************************************************************/

public class TestUtil {
	//**********************************************************************************************
	//Method Name:checkInternetConnection
	//Description: Method is check the internet connection and in case No-Connection is found then
	//             method will wait for the internet conection before executing test cases
	//Input Arguments:1.WebDriver->driver instance of browser
	//Return: NA
	//Created By : Salim Ansari
	//Created On : 9-Feb-2019
	//Reviewed By:
	//Reviewed On:
	//**********************************************************************************************
	public static void checkInternetConnection(WebDriver driver) {
		//create instance of java script executor
		JavascriptExecutor  jsExecutor = (JavascriptExecutor)driver;
		
		//run java script to check the internect connection
		boolean connectionFalg = (Boolean) jsExecutor.executeScript("return navigator.onLine");

		//if no connection is found the move into infinite loop
		if(!connectionFalg) {
			//start of infinite loop
			while(true) {
				//run java script to check the internect connection
				connectionFalg = (Boolean) jsExecutor.executeScript("return navigator.onLine");
				
				//check the internect connection
				if(connectionFalg) {
					//when connection is found refresh page to load all objects
					driver.navigate().refresh();
					
					//break loop
					break;
				}else {
					//wait for 2 seconds before moving to next loop
					try {
						Thread.sleep(2000);
					}catch(InterruptedException e) {
						e.printStackTrace();
					}//end of catch statement
				}//end of connectionFalg flag else within while loop
			}//end of while loop
		}//end of connectionFalg flag else
	}
	
	//**********************************************************************************************
	//Method checkMaintenanceProgess
	//Description: Method is check the Scheduled Maintenance and in case Scheduled Maintenance is found then
	//             method will wait for the Scheduled Maintenance before executing test cases
	//Input Arguments:1.WebDriver->driver instance of browser
	//Return: NA
	//Created By : Salim Ansari
	//Created On : 9-Feb-2019
	//Reviewed By:
	//Reviewed On:
	//**********************************************************************************************
	public static void checkMaintenanceProgess(WebDriver driver) {
		
		while(true){
			//get page title
			String pageTitle = driver.getTitle();
			
			//check ds is not under maintenance mode
			if(pageTitle.equals("Spirit Airlines Website under Scheduled Maintenance")) {
				
				//System.out.println("Scheduled Maintenance in Progress......");
				
				WaitUtil.untilPageLoadComplete(driver);
				
				//when connection is found refresh page to load all objects
				driver.navigate().refresh();
				
				//wait for 2 seconds before moving to next loop
				try {
					Thread.sleep(5000);
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}//end of catch statement
				
			}else {
				//break loop
				break;
			}
		}
	}
	

	//**********************************************************************************************
	//Method Name:switchToWindow
	//Description: Method is check the internet connection and in case No-Connection is found then
	//             method will wait for the internet conection before executing test cases
	//Input Arguments:1.WebDriver->driver instance of browser
	//                2.intWindowIndex-> index value to window
	//Return: NA
	//Created By : Salim Ansari
	//Created On : 9-Feb-2019
	//Reviewed By:
	//Reviewed On:
	//**********************************************************************************************
	public static void switchToWindow(WebDriver driver, int intWindowIndex) {
		
		try {
			Thread.sleep(5000);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}//end of catch statement
		
		ArrayList<String> ListIDValue = new ArrayList<String>(driver.getWindowHandles());

		//System.out.println(ListIDValue.size());

		//System.out.println("Window Switch");
		driver.switchTo().window(ListIDValue.get(intWindowIndex));
		//System.out.println(driver.getCurrentUrl());

		WaitUtil.untilTimeCompleted(10000);


		driver.manage().window().maximize();
		
		//update extent report
		//BaseClass.test.log(LogStatus.INFO, "Switch to window with index value as " + intWindowIndex);
	}
	
	//**********************************************************************************************
	//Method Name:switchToFrame
	//Description: Method is check the internet connection and in case No-Connection is found then
	//             method will wait for the internet conection before executing test cases
	//Input Arguments:1.WebDriver->driver instance of browser
	//                2.WebElement-> frame element to switch
	//Return: NA
	//Created By : Salim Ansari
	//Created On : 9-Feb-2019
	//Reviewed By:
	//Reviewed On:
	//**********************************************************************************************
	public static void switchToFrame(WebDriver driver, WebElement element){
		//wait for 1 sec
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		//switch to frame
		try {
			driver.switchTo().frame(element);
			
			//update extent report
			//BaseClass.test.log(LogStatus.INFO, "Switch to frame within the window");
		} catch(NoSuchElementException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}
	
	//**********************************************************************************************
	//Method Name:acceptAlertBox
	//Description: Method is accept alert message in case it appear on screen
	//Input Arguments:1.WebDriver->driver instance of browser
	//Return: NA
	//Created By : Salim Ansari
	//Created On : 9-Feb-2019
	//Reviewed By:
	//Reviewed On:
	//**********************************************************************************************
	public static boolean acceptAlertBox(WebDriver driver){
		if(isAlertPresent(driver)){
			Alert alert = driver.switchTo().alert();
			System.out.println(alert.getText());
			
			alert.accept();
			
			//update extent report
			//BaseClass.test.log(LogStatus.INFO, "Accepted alert popup with message " + alert.getText());
			
			return true;
		}else {
			return false;
		}
	}
	
	//**********************************************************************************************
	//Method Name:isAlertPresent
	//Description: Method is used to check the aleart popup appear on web page
	//Input Arguments:1.WebDriver->driver instance of browser
	//Return: NA
	//Created By : Salim Ansari
	//Created On : 9-Feb-2019
	//Reviewed By:
	//Reviewed On:
	//**********************************************************************************************
	public static boolean isAlertPresent(WebDriver driver) {
		try {
			driver.switchTo().alert();
			
			//update extent report
			//BaseClass.test.log(LogStatus.INFO, "Alert is present on window"); 
			return true;
		}catch(NoAlertPresentException Ex) {
			System.out.println(Ex.getMessage());
			return false;
		}
	}
	
	//**********************************************************************************************
	//Method Name:takeScreenshotAtEndOfTest
	//Description: Method is used to create screen shot of window screen
	//Input Arguments:1.WebDriver->driver instance of browser
	//                2.listenerType-> which listner is calling this method
	//Return: string of Date and Time
	//Created By : Salim Ansari
	//Created On : 9-Feb-2019
	//Reviewed By:
	//Reviewed On:
	//**********************************************************************************************	
	public static String takeScreenshotAtEndOfTest(WebDriver driver) throws IOException {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			String screenShotFileName = (currentDateTimeString() + ".png");

			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "/screenshots/" + screenShotFileName));

			//update extent report
			//BaseClass.test.log(LogStatus.INFO, "Screen Shot taken using takeScreenshotAtEndOfTest method");

			return System.getProperty("user.dir") + "/screenshots/" + screenShotFileName;

		}


	public static String getBase64Screenshot(WebDriver driver) throws IOException {

		String encodedBase64 = null;
		FileInputStream fileInputStream = null;

		String screenShotFileName = (currentDateTimeString() + ".png");

		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/screenshots/" + screenShotFileName;
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);

		try {
			fileInputStream =new FileInputStream(finalDestination);
			byte[] bytes =new byte[(int)finalDestination.length()];
			fileInputStream.read(bytes);
			encodedBase64 = new String(Base64.encodeBase64(bytes));
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}

		return encodedBase64;
	}
	//**********************************************************************************************
	//Method Name:currentDateTimeString
	//Description: Method is used to create dynamic string based on system date and time
	//Input Arguments:NA
	//Return: string of Date and Time
	//Created By : Salim Ansari
	//Created On : 9-Feb-2019
	//Reviewed By:
	//Reviewed On:
	//**********************************************************************************************
	public static String currentDateTimeString() {
		//create date instance
		Date curdate = new Date();
		
		//create format instance in which need to be converted
		SimpleDateFormat newformat = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		
		
		//update extent report
		//BaseClass.test.log(LogStatus.INFO, "Date Time string is created with value " + newformat.format(curdate));
		
		//return current date and time after converting into format
		return newformat.format(curdate);
	}

	//**********************************************************************************************
	//Method Name:currentDateTimeStringForReport
	//Description: Method is used to create dynamic string based on system date and time fro report
	//Input Arguments:NA
	//Return: string of Date and Time in format yyyy_MM_dd_HH_mm_ss
	//Created By : Un Fai Chan
	//Created On : 1/7/2020
	//Reviewed By:
	//Reviewed On:
	//**********************************************************************************************
	public static String currentDateTimeStringForReport() {
		//create date instance
		Date curdate = new Date();

		//create format instance in which need to be converted
		SimpleDateFormat newformat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");


		//update extent report
		//BaseClass.test.log(LogStatus.INFO, "Date Time string is created with value " + newformat.format(curdate));

		//return current date and time after converting into format
		return newformat.format(curdate);
	}
	
	//**********************************************************************************************
	//Method Name:verifyLinks
	//Description: Method is used to check the link is not broken using HttpURLConnection
	//Input Arguments:NA
	//Return: Boolean Flag
	//Created By : Salim Ansari
	//Created On : 9-Feb-2019
	//Reviewed By:
	//Reviewed On:
	//**********************************************************************************************
	public static boolean verifyLinks(String Linkhref) {
	     //Sometimes we may face exception "java.net.MalformedURLException". Keep the code in try catch block to continue the broken link analysis
       try {
			 //Use URL Class - Create object of the URL Class and pass the urlLink as parameter 
			 URL link = new URL(Linkhref);
			 
			 // Create a connection using URL object (i.e., link)
			 HttpURLConnection httpConn =(HttpURLConnection)link.openConnection();
			 
			 //Set the timeout for 2 seconds
			 httpConn.setConnectTimeout(1800);
			 
			 //connect using connect method
			 httpConn.connect();
			 
			 System.out.println(httpConn.getResponseCode());
			 
			 //use getResponseCode() to get the response code. 
			 //200 – Valid Link
			 //404 – Link not found
			 //400 – Bad request
			 //401 – Unauthorized
			 //500 – Internal Error
			 if(httpConn.getResponseCode()== 200) { 
				//System.out.println(quickLinkhref+" - "+httpConn.getResponseMessage());
				 
					//update extent report
					//BaseClass.test.log(LogStatus.INFO, "Link "+ Linkhref + "is a valid link");
					
				 return true;
			 }
			 
			 //if(httpConn.getResponseCode()== 404) {
				// System.out.println(quickLinkhref+" - "+httpConn.getResponseMessage());
				 //return false;
			// }
		 
			 //disconnect using disconnect method
			 httpConn.disconnect();
		 }
		 //getResponseCode method returns = IOException - if an error occurred connecting to the server. 
		 catch (Exception e) {
			 e.printStackTrace();
			 System.out.println(e.getMessage());
		 }
       
		//update extent report
		//BaseClass.test.log(LogStatus.INFO, "Link "+ Linkhref + "is not a valid link");
		return false;
	}
	
	//**********************************************************************************************
	//Method Name:generateXPATH
	//Description: Method is used to generate Xpath of the passed Webelement
	//Input Arguments:1.WebElment->Element whose Xpath to be generated
	//				  2.String->Empty string used for nested recursion
	//Return: string of xpath
	//Created By : Salim Ansari
	//Created On : 9-Feb-2019
	//Reviewed By:
	//Reviewed On:
	//**********************************************************************************************
    public static String generateXPATH(WebElement childElement, String current) {
    	//get the tag of current element to check for html page tag
        String childTag = childElement.getTagName();
        
        //if reached the html page tag then reurun
        if(childTag.equals("html")) {
            return "/html[1]"+current;
        }
        
        //get the parent webelement
        WebElement parentElement = childElement.findElement(By.xpath("..")); 
        
        //get all child weblements of above parent element
        List<WebElement> childrenElements = parentElement.findElements(By.xpath("*"));
        
        //initialise counter for next value
        int count = 0;
        
        //loop tjrough all child elements
        for(int i=0;i<childrenElements.size(); i++) {
        	//get each child element
            WebElement childrenElement = childrenElements.get(i);
            
            //get tag of each child element
            String childrenElementTag = childrenElement.getTagName();
            
            //compare child element tag with passed weblement
            if(childTag.equals(childrenElementTag)) {
            	//increment count by one when passed and child element tag are same
                count++;
            }
            
            //check passed and child element
            if(childElement.equals(childrenElement)) {
            	//if both are same recall same function till html tag is reached
                return generateXPATH(parentElement, "/" + childTag + "[" + count + "]"+current);
            }
        }
        return null;
    }
    
	//**********************************************************************************************
	//Method Name:getStringDateFormat
	//Description: Method is used to generate date string based on date number and date format
	//Input Arguments:1.dateNumber->Number of which date is required
	//				  2.dateFormat->Empty string used for nested recursion
	//Return: string of date
	//Created By : Salim Ansari
	//Created On : 15-Feb-2019
	//Reviewed By:
	//Reviewed On:
	//**********************************************************************************************
    public static String getStringDateFormat(Object object,String dateFormat) {
    	//declare of class used in method
    	Calendar calender;
		Date date;
		SimpleDateFormat dateFormatter;
		
		//create calendar instance
		calender = Calendar.getInstance();
		
		//Increment calendar date to get departure date
		calender.add(Calendar.DATE, Integer.parseInt(object.toString()));

		//get date from calendar in correct format
		date = calender.getTime();
		
		//set format in which date is required
		dateFormatter = new SimpleDateFormat(dateFormat);
		
		//update extent report
		//BaseClass.test.log(LogStatus.INFO, dateFormatter.format(date) + "is the date string of day number " + dateFormat);
		
		//convert date in required format
		return dateFormatter.format(date);
    }
    
	//**********************************************************************************************
	//Method Name:convertStringToDate
	//Description: Method is used to convert string to date
	//Input Arguments:1.date->date in string format
	//				  2.dateFormat->Empty string used for nested recursion
	//Return: string of date
	//Created By : Salim Ansari
	//Created On : 15-Feb-2019
	//Reviewed By:
	//Reviewed On:
	//**********************************************************************************************
    public static Date convertStringToDate(String date,String dateFormat ) {
		DateFormat format = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
		try {
			//update extent report
			//BaseClass.test.log(LogStatus.INFO, dateFormat + " string is converted to time format");

			return format.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	//**********************************************************************************************
	//Method Name:convertDateToString
	//Description: Method is used to convert string to date
	//Input Arguments:1.date->date in string format
	//				  2.dateFormat->Empty string used for nested recursion
	//Return: string of date
	//Created By : Salim Ansari
	//Created On : 15-Feb-2019
	//Reviewed By:
	//Reviewed On:
	//**********************************************************************************************
	public static String convertDateToString(Date date,String dateFormat ) {

		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return (sdf.format(date)).toString();
	}

	
	//**********************************************************************************************
	//Method Name:clearTextBoxUsingAction
	//Description: Method is used to clear text meesage from text box using action
	//Input Arguments:1.driver->Webdriver reference
	//				  2.webElement->weblement of text box whose value need to clear
	//Return: string of date
	//Created By : Salim Ansari
	//Created On : 15-Feb-2019
	//Reviewed By:
	//Reviewed On:
	//**********************************************************************************************
    public static void clearTextBoxUsingAction(WebDriver driver,WebElement webElement) {
		//create action class
		Actions clearText = new Actions(driver);
		
		//clear text box
		clearText.click(webElement).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE).build().perform();
		
		//update extent report
		//BaseClass.test.log(LogStatus.INFO, "clear text of webelement using action for " + webElement);
    }
    
	//**********************************************************************************************
	//Method Name:clearTextBoxUsingSendKeys
	//Description: Method is used to clear text meesage from text box using selenium sendKeys
	//Input Arguments:1.driver->Webdriver reference
	//				  2.webElement->weblement of text box whose value need to clear
	//Return: string of date
	//Created By : Salim Ansari
	//Created On : 15-Feb-2019
	//Reviewed By:
	//Reviewed On:
	//**********************************************************************************************
    public static void clearTextBoxUsingSendKeys(WebDriver driver,WebElement webElement) {


		//wait for .5 sec
		WaitUtil.untilTimeCompleted(500);

    	//click on webelement
    	webElement.click();
    	
    	//select all the box text
    	webElement.sendKeys(Keys.CONTROL + "a");
    	
    	//delete box text
    	webElement.sendKeys(Keys.DELETE);

		//clear with selenium API
		webElement.clear();

		//update extent report
		//BaseClass.test.log(LogStatus.INFO, "clear text of webelement using sendkeys for " + webElement);
    }
    
	//**********************************************************************************************
	//Method Name:selectDropDownUsingValue
	//Description: Method is used to select value in drop down list using visible text
	//Input Arguments:1.driver->Webdriver reference
	//				  2.webElement->weblement of text box whose value need to clear
	//Return: string of date
	//Created By : Salim Ansari
	//Created On : 15-Feb-2019
	//Reviewed By:
	//Reviewed On:
	//**********************************************************************************************
    public static void selectDropDownUsingVisibleText(WebElement webElement, String visibleText) {
    	//create drop down object
    	Select drpdwn = new Select(webElement);

    	//select visible text
    	drpdwn.selectByVisibleText(visibleText);
    }
    
	//**********************************************************************************************
	//Method Name:selectDropDownUsingValue
	//Description: Method is used to select value in drop down list using value
	//Input Arguments:1.driver->Webdriver reference
	//				  2.webElement->weblement of text box whose value need to select
	//Return: string of date
	//Created By : Salim Ansari
	//Created On : 15-Feb-2019
	//Reviewed By:
	//Reviewed On:
	//**********************************************************************************************
    public static void selectDropDownUsingValue(WebElement webElement, String valueText) {
    	//create drop down object
    	Select drpdwn = new Select(webElement);
    	
    	//select visible text
    	drpdwn.selectByValue(valueText);
    }
    
	//**********************************************************************************************
	//Method Name:convertTo2DecimalValue
	//Description: Method is used to convert the double value to 2 decimal position
	//Input Arguments:1.driver->Webdriver reference
	//				  2.webElement->weblement of text box whose value need to select
	//Return: string of date
	//Created By : Salim Ansari
	//Created On : 15-Feb-2019
	//Reviewed By:
	//Reviewed On:
	//**********************************************************************************************
    public static String convertTo2DecimalValue(double number) {
    	//declare constant used in method
        final int DECIMAL_TO_CONSIDER = 2;
        
        //convert to big decimal
        BigDecimal bigDecimal = new BigDecimal(number);
        
        //round off value to 2 decimal position
        return bigDecimal.setScale(DECIMAL_TO_CONSIDER, BigDecimal.ROUND_HALF_UP).toString();
    }

	//**********************************************************************************************
	//Method Name:verifyElementDispayed
	//Description: Method is used to check is present on web
	//Input Arguments:1.WebDriver->driver
	//Input Arguments:2.By -> by Object
	//Return: string of date
	//Created By : Salim Ansari
	//Created On : 16-Sep-2019
	//Reviewed By: Kartik Chauhan
	//Reviewed On: 16-Sep-2019
	//**********************************************************************************************
	public static boolean verifyElementDisplayed(WebDriver driver,By by) {
		try {
			WaitUtil.untilPageLoadComplete(driver);

			WaitUtil.untilTimeCompleted(1000);
			return driver.findElement(by).isDisplayed();
		} catch (Exception e) {
			//return false
			return false;
		}
	}

	//**********************************************************************************************
	//Method Name:verifyElementDispayed
	//Description: Method is used to check element is prent on web
	//Input Arguments:1.webElement->weblement is displayed
	//Return: string of date
	//Created By : Sunny Sok
	//Created On : 2-Apr-2019
	//Reviewed By: Salim Ansari
	//Reviewed On: 2-Apr-2019
	//**********************************************************************************************
	public static boolean verifyElementDisplayed(Object element) {
		try {
			//check element is displayed
			WebElement myElement = (WebElement)element;

			myElement.isDisplayed();
			//return true
			return true;
		} catch (Exception e) {
			//return false
			return false;
		}
	}

	//**********************************************************************************************
	//Method Name:verifyElementDispayed
	//Description: Method is used to check first element of list is present on web
	//Input Arguments:1.webElement->weblement is displayed
	//Return: string of date
	//Created By : Sunny Sok
	//Created On : 2-Apr-2019
	//Reviewed By: Salim Ansari
	//Reviewed On: 2-Apr-2019
	//**********************************************************************************************
	public static boolean verifyElementDisplayed(List<WebElement> element) {

		if(element.size()==0){
			return false;
		}

		return verifyElementDisplayed( element.get(0));
	}

	//**********************************************************************************************
	//Method Name:closeBoardingPassPrintPopup
	//Description: Method is used to close boarding pass print popup
	//Input Arguments:NA
	//Return: NA
	//Created By : Sunny Sok
	//Created On : 2-Apr-2019
	//Reviewed By: Salim Ansari
	//Reviewed On: 2-Apr-2019
	//**********************************************************************************************
	public static void closeBoardingPassPrintPopup() {

		WaitUtil.untilTimeCompleted(10000);

		try{//create instance of robot class
			java.awt.Robot robot = new java.awt.Robot();

			System.out.println("Key press");

			//press escape button
			robot.keyPress(KeyEvent.VK_ESCAPE);

			//wait for 30 sec
			WaitUtil.untilTimeCompleted(500);

			//trelease escape button
			robot.keyRelease(KeyEvent.VK_ESCAPE);
		}catch(Exception e){

		}
	}

	//**********************************************************************************************
	//Method Name: verifyFileDownload
	//Description: Method is used to close boarding pass print popup
	//Input Arguments: 1. String-> fileNameWithExtention
	//Return: boolean
	//Created By : Salim Ansari
	//Created On : 26-Apr-2019
	//Reviewed By: Kartik
	//Reviewed On: 26-Apr-2019
	//**********************************************************************************************

	public static boolean verifyFileDownload(String fileName,String fileExtention){

		boolean statusFlag = false;

		//setting path of download location
		String path = System.getProperty("user.home") + "\\Downloads";

		//creating object of file having path as parameter
		File folder = new File(path);

		//creating an array of al file
		File[] listOfFiles = folder.listFiles();

		//accessing all file
		for (int count = 0; count < listOfFiles.length; count++) {

			//checking if it is file
			if (listOfFiles[count].isFile()) {

				if (listOfFiles[count].getName().contains(fileName)
						&& listOfFiles[count].getName().endsWith(fileExtention)) {
					statusFlag = true;
				}
			}
		}
		return statusFlag;

	}

	//**********************************************************************************************
	//Method Name: getLatestReportFile
	//Description: Method is return the latest Report File from Result Folder
	//Input Arguments:NA
	//Return: boolean
	//Created By : Salim Ansari
	//Created On : 3-May-2019
	//Reviewed By: Kartik
	//Reviewed On: 3-May-2019
	//**********************************************************************************************
	public static File getLatestReportFile(){
		//create dic intance of result folder
		File dir = new File(System.getProperty("user.dir")+"\\reports\\");

		//get all files from result folder
		File[] files = dir.listFiles();

		//check is present in folder
		if (files == null || files.length == 0) {
			return null;
		}

		//get firest file for comparision
		File lastModifiedFile = files[0];

		//loop through all files to get the latest file
		for (int i = 1; i < files.length; i++) {
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];
			}
		}
		//return latest file
		return lastModifiedFile;
	}

	//**********************************************************************************************
	//Method Name: getLatestFailedSnapshotFile
	//Description: Method is return the latest Report File from Result Folder
	//Input Arguments:NA
	//Return: boolean
	//Created By : Salim Ansari
	//Created On : 3-May-2019
	//Reviewed By: Kartik
	//Reviewed On: 3-May-2019
	//**********************************************************************************************
	public static File getLatestFailedSnapshotFile(){
		//create dic intance of result folder
		File dir = new File(System.getProperty("user.dir")+"\\screenshots\\");

		//get all files from result folder
		File[] files = dir.listFiles();

		//check is present in folder
		if (files == null || files.length == 0) {
			return null;
		}

		//get firest file for comparision
		File lastModifiedFile = files[0];

		//loop through all files to get the latest file
		for (int i = 1; i < files.length; i++) {
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];
			}
		}
		//return latest file
		return lastModifiedFile;
	}

	//**********************************************************************************************
	//Method Name: getAlphaCharacterString
	//Description: Method is return the latest Report File from Result Folder
	//Input Arguments:NA
	//Return: boolean
	//Created By : Anthony
	//Created On : 3-May-2019
	//Reviewed By: Salim Ansari
	//Reviewed On: 3-May-2019
	//**********************************************************************************************
	public static String getAlphaCharacterString(int stringLength){

		String characterString = "abcdefghijklmnopqrstuvxyz";

		// create StringBuffer size of AlphaString
		StringBuilder stringBuilder = new StringBuilder(stringLength);

		for (int indexValue = 0; indexValue < stringLength; indexValue++) {
			// generate a random number between
			// 0 to AlphaString variable length
			int index = (int)(characterString.length() * Math.random());

			// add Character one by one in end of sb
			stringBuilder.append(characterString.charAt(index));
		}

		return stringBuilder.toString();

	}

	//**********************************************************************************************
	//Method Name: removeSpecialCharacterFromNumber
	//Description: Method is remove all num non number from string
	//Input Arguments:NA
	//Return: boolean
	//Created By : Salim Ansari
	//Created On : 30-May-2019
	//Reviewed By: Kartik Chauhan
	//Reviewed On: 30-May-2019
	//**********************************************************************************************
	public static String removeSpecialCharacterFromNumber(String inputString){
		//declare variable used in method
		String newString = "";

		//loop through all character of string
		for (int counterValue=0;counterValue<inputString.length();counterValue++){
			//Ascci range for 0-9=(48,57), .=46 and $=36
			if ((inputString.charAt(counterValue)>47&&inputString.charAt(counterValue)<58) || inputString.charAt(counterValue)==46 || inputString.charAt(counterValue)==36){
				//added to new if not a special character
				newString+=inputString.charAt(counterValue);
			}
		}

		//return value
		return newString;
	}

	//**********************************************************************************************
	//Method Name: mouseClickOnElement
	//Description: Method is click on element using mouse action
	//Input Arguments:NA
	//Return: boolean
	//Created By : Salim Ansari
	//Created On : 13-June-2019
	//Reviewed By: Kartik Chauhan
	//Reviewed On: 13-June-2019
	//**********************************************************************************************
	public static void mouseClickOnElement(WebDriver driver, WebElement elementToClick){
		WaitUtil.untilTimeCompleted(2000);

		Actions builder = new Actions(driver);
		builder.click(elementToClick).build().perform();

	}

	//**********************************************************************************************
	//Method Name: removeSpecialCharacterFromNumber
	//Description: Method is remove all num non number from string
	//Input Arguments:NA
	//Return: boolean
	//Created By : Salim Ansari
	//Created On : 13-June-2019
	//Reviewed By: Kartik Chauhan
	//Reviewed On: 13-June-2019
	//**********************************************************************************************
	public static String removeNonAlphaNumericCharacterFromString(String inputString){
		//declare variable used in method
		String newString = "";

		//loop through all character of string
		for (int counterValue=0;counterValue<inputString.length();counterValue++){
			//Ascci range for 0-9=(48,57), .=46 and $=36
			if ((inputString.charAt(counterValue)>=32 && inputString.charAt(counterValue)<=125)){
				//added to new if not a special character
				newString+=inputString.charAt(counterValue);
			}
		}

		//return value
		return newString;
	}

	//**********************************************************************************************
	//Method Name: sendKeyUsingRobotClass
	//Description: Method is reused to send keys using robot calss, Useful while enter date in search widget on web page
	//Input Arguments:NA
	//Return: boolean
	//Created By : Salim Ansari
	//Created On : 30-June2019
	//Reviewed By: Kartik Chauhan
	//Reviewed On: 30-June-2019
	//**********************************************************************************************
	public static void sendKeyUsingRobotClass(String Keys){
		try{//create instance of robot class
			java.awt.Robot robot = new java.awt.Robot();

			//Press Ctrl+F to open search wighet on pdf
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_F);
			WaitUtil.untilTimeCompleted(500);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_F);

			//Press Ctrl+A select all value in search widget
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_A);
			WaitUtil.untilTimeCompleted(500);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_A);

			//preass Backspace clear search widget
			robot.keyPress(KeyEvent.VK_BACK_SPACE);
			WaitUtil.untilTimeCompleted(500);
			robot.keyRelease(KeyEvent.VK_BACK_SPACE);

			//loop though all key element
			for(String keyValue :  Keys.split("")){
				switch(keyValue){
					case "1":
						robot.keyPress(KeyEvent.VK_1);
						robot.keyRelease(KeyEvent.VK_1);

						break;
					case "2":
						robot.keyPress(KeyEvent.VK_2);
						robot.keyRelease(KeyEvent.VK_2);

						break;
					case "3":
						robot.keyPress(KeyEvent.VK_3);
						robot.keyRelease(KeyEvent.VK_3);

						break;
					case "4":
						robot.keyPress(KeyEvent.VK_4);
						robot.keyRelease(KeyEvent.VK_4);

						break;
					case "5":
						robot.keyPress(KeyEvent.VK_5);
						robot.keyRelease(KeyEvent.VK_5);

						break;
					case "6":
						robot.keyPress(KeyEvent.VK_6);
						robot.keyRelease(KeyEvent.VK_6);

						break;
					case "7":
						robot.keyPress(KeyEvent.VK_7);
						robot.keyRelease(KeyEvent.VK_7);

						break;
					case "8":
						robot.keyPress(KeyEvent.VK_8);
						robot.keyRelease(KeyEvent.VK_8);

						break;
					case "9":
						robot.keyPress(KeyEvent.VK_9);
						robot.keyRelease(KeyEvent.VK_9);

						break;
					case ".":

						robot.keyPress(KeyEvent.VK_PERIOD);
						robot.keyRelease(KeyEvent.VK_PERIOD);
				}

			}
		}catch(Exception e){

		}

		WaitUtil.untilTimeCompleted(2000);
	}

	//**********************************************************************************************
	//Method Name:verifyAttributePresent
	//Description:
	//Input Arguments:
	//Return:
	//Created By : Sunny Sok
	//Created On :
	//Reviewed By:
	//Reviewed On:
	//**********************************************************************************************
	public static boolean verifyAttributePresent(WebElement element,String attribute) {
		try {
			String value = element.getAttribute(attribute);
			if (value != null)
			//return true
			return true;
		} catch (Exception e) {}
			//return false
			return false;
	}

	//**********************************************************************************************
	//Method Name:swipeScreenOnMobile
	//Description:
	//Input Arguments:
	//Return:
	//Created By : Salim Ansari
	//Created On : 24-Oct-2019
	//Reviewed By:
	//Reviewed On:
	//**********************************************************************************************
	public static void swipeScreenOnMobile(AppiumDriver driver, MobileElement fromElement, MobileElement toElement){

		//get coordinates if first and last elements
		int xInitail    = fromElement.getLocation().getX() + fromElement.getSize().width/2;
		int yInitail    = fromElement.getLocation().getY() + fromElement.getSize().height/2;
		int xFinal      = toElement.getLocation().getX()   + toElement.getSize().width/2;
		int yFinal      = toElement.getLocation().getY()   + toElement.getSize().height/2;

		//create reference of touch action
		TouchAction action = new TouchAction(driver);

		//swipe screen
		action.press(PointOption.point(xInitail,yInitail))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
				.moveTo(PointOption.point(xFinal,yFinal)).release().perform();

	}

	public static Date addDays(Date date, int days)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days); //minus number would decrement the days
		return cal.getTime();
	}


	public static String getDaysFromStringDate(String dateFormat){
		Date passedDate = null;

		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		formatter.setTimeZone(TimeZone.getTimeZone("EST"));
		try{
			passedDate = formatter.parse(dateFormat);
		}catch (Exception e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}

		Date currentDate = TestUtil.convertStringToDate(TestUtil.getStringDateFormat("0","MM/dd/yyyy"),"MM/dd/yyyy");

		long difference = passedDate.getTime() - currentDate.getTime();
		long daysBetween = (difference / (1000*60*60*24));

		return Integer.toString(Integer.parseInt(Long.toString(daysBetween)));

	}


	public static String convert24HourTo12Hour(String _24HourTime){
		try {
			SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
			SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");
			Date _24HourDt = _24HourSDF.parse(_24HourTime);

			return _12HourSDF.format(_24HourDt);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}


	public static String getMonthNumberFromMonthName(String monthName){
		try {
			SimpleDateFormat inputFormat = new SimpleDateFormat("MMMM");
			Calendar cal = Calendar.getInstance();
			cal.setTime(inputFormat.parse(monthName));
			SimpleDateFormat outputFormat = new SimpleDateFormat("MM"); // 01-12

			return outputFormat.format(cal.getTime());
		}catch(Exception e){
			System.out.println(e);
		}

		return null;
	}

	//**********************************************************************************************
	//Method Name:selectDropDownUsingIndex
	//Description: Method is used to select value in drop down list using index
	//Input Arguments:1.webElement->web element reference
	//				  2.indexValue->index of option
	//Return: NA
	//Created By : Salim Ansari
	//Created On : 17-Dec-2019
	//Reviewed By:
	//Reviewed On:
	//**********************************************************************************************
	public static void selectDropDownUsingIndex(WebElement webElement, int indexValue) {
		//create drop down objec
		Select drpdwn = new Select(webElement);
//		List<WebElement> elements = drpdwn.getOptions();
//		selectDropDownUsingVisibleText(webElement,elements.get(indexValue-1).getText());
		drpdwn.selectByIndex(indexValue);
	}

	//**********************************************************************************************
	//Method Name:getSelectedOptionIndex
	//Description: Method is used to return index of option which is selected
	//Input Arguments:1.driver->Webdriver reference
	//Return: int-> index number
	//Created By : Salim Ansari
	//Created On : 17-Dec-2019
	//Reviewed By:
	//Reviewed On:
	//**********************************************************************************************
	public static int getSelectedOptionIndex(WebElement webElement) {
		int index = 0;
		//create drop down objec
		Select drpdwn = new Select(webElement);
		List<WebElement> elements = drpdwn.getOptions();

		for(int optionCount=0;optionCount<elements.size();optionCount++){
			if(elements.get(optionCount).getText().equalsIgnoreCase(drpdwn.getFirstSelectedOption().getText())){
				index = optionCount;
				break;
			}
		}
		return index;
	}

	//**********************************************************************************************
	//Method Name: sendKeyUsingRobotClass
	//Description: Method is reused to send keys using robot calss, Useful while enter date in search widget on web page
	//Input Arguments:NA
	//Return: boolean
	//Created By : Salim Ansari
	//Created On : 30-June2019
	//Reviewed By: Kartik Chauhan
	//Reviewed On: 30-June-2019
	//**********************************************************************************************
	public static void menuOptionSkySpeed(String menuOption){
		try{//create instance of robot class
			java.awt.Robot robot = new java.awt.Robot();
			//System.out.println(menuOption);
			//loop though all key element
			switch(menuOption.toLowerCase()){
				case "maximumwindow":
					//System.out.println("Dot");
					//Keys.Alt + Keys.Space + 'x'
					robot.keyPress(KeyEvent.VK_ALT);
					robot.keyPress(KeyEvent.VK_SPACE);
					robot.keyPress(KeyEvent.VK_X);

					robot.keyRelease(KeyEvent.VK_ALT);
					robot.keyRelease(KeyEvent.VK_SPACE);
					robot.keyRelease(KeyEvent.VK_X);

					break;
				case "newbooking":
					//Ctrl+N
					robot.keyPress(KeyEvent.VK_CONTROL);
					robot.keyPress(KeyEvent.VK_N);
					robot.keyRelease(KeyEvent.VK_CONTROL);
					robot.keyRelease(KeyEvent.VK_N);

					WaitUtil.untilTimeCompleted(1000);

					//Press Enter
					robot.keyPress(KeyEvent.VK_ENTER);
					robot.keyRelease(KeyEvent.VK_ENTER);

					break;
				case "selectflight":
					robot.keyPress(KeyEvent.VK_SPACE);
					robot.keyRelease(KeyEvent.VK_SPACE);

					break;
				case "4":
					robot.keyPress(KeyEvent.VK_4);
					robot.keyRelease(KeyEvent.VK_4);

					break;
				case "5":
					robot.keyPress(KeyEvent.VK_5);
					robot.keyRelease(KeyEvent.VK_5);

					break;
				case "6":
					robot.keyPress(KeyEvent.VK_6);
					robot.keyRelease(KeyEvent.VK_6);

					break;
				case "7":
					robot.keyPress(KeyEvent.VK_7);
					robot.keyRelease(KeyEvent.VK_7);

					break;
				case "8":
					robot.keyPress(KeyEvent.VK_8);
					robot.keyRelease(KeyEvent.VK_8);

					break;
				case "9":
					robot.keyPress(KeyEvent.VK_9);
					robot.keyRelease(KeyEvent.VK_9);

					break;
				case ".":

					robot.keyPress(KeyEvent.VK_PERIOD);
					robot.keyRelease(KeyEvent.VK_PERIOD);
			}
		}catch(Exception e){

		}

		WaitUtil.untilTimeCompleted(2000);
	}

}


