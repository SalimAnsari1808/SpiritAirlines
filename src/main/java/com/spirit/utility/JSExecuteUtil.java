package com.spirit.utility;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/*
 * pending -> getting element Id
 * pending -> getting element by attribute
 */

public class JSExecuteUtil {
	private static JavascriptExecutor javaScriptExecute = null;
	
	// **********************************************************************************************
	// Method : clickOnElement
	// Description: Method is used to click on element on the Web page
	// Input Arguments: 1. WebDriver  2. WebElement
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 13-Feb-2019
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	public static void clickOnElement(WebDriver driver,WebElement webElement) {
		javaScriptExecute = (JavascriptExecutor)driver;
		javaScriptExecute.executeScript("arguments[0].click();", webElement);
		
		//update extent report
		//BaseClass.test.log(LogStatus.INFO, "Clicked on WebElement using JavaScript");
	}
	
	// **********************************************************************************************
	// Method : scrollDown
	// Description: Method is used to scroll down in the Web page
	// Input Arguments: 1. WebDriver  2. vertical height
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 13-Feb-2019
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	public static void scrollDown(WebDriver driver,String vertical) {
		javaScriptExecute = (JavascriptExecutor)driver;
		javaScriptExecute.executeScript("window.scrollBy(0,"+vertical+")");
		
		//update extent report
		//BaseClass.test.log(LogStatus.INFO, "Scroll down using JavaScript");
	}

	// **********************************************************************************************
	// Method : scrollDownToElementVisible
	// Description: Method is used to scroll down in the Web page to make element visible
	// Input Arguments: 1. WebDriver  2. vertical height
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 13-Feb-2019
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	public static void scrollDownToElementVisible(WebDriver driver,WebElement element) {
		javaScriptExecute = (JavascriptExecutor)driver;

		javaScriptExecute.executeScript("arguments[0].scrollIntoView(true);", element);

		//update extent report
		//BaseClass.test.log(LogStatus.INFO, "Scroll down using JavaScript");
	}
	
	// **********************************************************************************************
	// Method : generateAlertPopup
	// Description: Method is used to generate Alert pop up with provided message of the Web page
	// Input Arguments: 1. WebDriver  2. Message
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 13-Feb-2019
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	public static void generateAlertPopup(WebDriver driver, String message) {
		javaScriptExecute = (JavascriptExecutor)driver;
		javaScriptExecute.executeScript("alert('"+message+"');");
		
		//update extent report
		//BaseClass.test.log(LogStatus.INFO, "Generated Alert message with value " + message+ " using JavaScript");
	}
	
	// **********************************************************************************************
	// Method : refreshBrowser
	// Description: Method is used to refresh the web page
	// Input Arguments: 1. WebDriver
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 13-Feb-2019
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	public static void refreshBrowser(WebDriver driver) {
		javaScriptExecute = (JavascriptExecutor)driver;
		javaScriptExecute.executeScript("history.go(0)");
		
		//update extent report
		//BaseClass.test.log(LogStatus.INFO, "Refresh Browser using JavaScript");
	}
	
	// **********************************************************************************************
	// Method : getElementInnerText
	// Description: Method is used to get all the inner element of the Web page
	// Input Arguments:1. WebDriver 
	// Return: String -> All Inner element
	// Created By : Salim Ansari
	// Created On : 13-Feb-2019
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	public static String getElementInnerText(WebDriver driver) {
		javaScriptExecute = (JavascriptExecutor)driver;
		
		//update extent report
		//BaseClass.test.log(LogStatus.INFO, "Get innertext using JavaScript");
		
		return javaScriptExecute.executeScript("return document.documentElement.innerText;").toString();
	}
	
	// **********************************************************************************************
	// Method : getElementTextValue
	// Description: Method is used to get all the inner element of the Web page
	// Input Arguments:1. WebDriver 
	// Return: String -> All Inner element
	// Created By : Salim Ansari
	// Created On : 13-Feb-2019
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	public static String getElementTextValue(WebDriver driver, WebElement webElement) {
		javaScriptExecute = (JavascriptExecutor)driver;
		
		//update extent report
		//BaseClass.test.log(LogStatus.INFO, "Getting element innertext using JavaScript");
		return javaScriptExecute.executeScript("return arguments[0].value", webElement).toString();
	}
	
	// **********************************************************************************************
	// Method : getWebPageDomain
	// Description: Method is used to get Web page Domain
	// Input Arguments:1. WebDriver 
	// Return: String -> Domain
	// Created By : Salim Ansari
	// Created On : 13-Feb-2019
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	public static String getWebPageDomain(WebDriver driver) {
		javaScriptExecute = (JavascriptExecutor)driver;
		return javaScriptExecute.executeScript("return document.domain;").toString();
	}
	
	// **********************************************************************************************
	// Method : getWebPageTitle
	// Description: Method is used to get Web page Title
	// Input Arguments:1. WebDriver 
	// Return: String -> Title
	// Created By : Salim Ansari
	// Created On : 13-Feb-2019
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	public static String getWebPageTitle(WebDriver driver) {
		javaScriptExecute = (JavascriptExecutor)driver;
		return javaScriptExecute.executeScript("return document.title;").toString();
	}
	
	// **********************************************************************************************
	// Method : getWebPageURL
	// Description: Method is used to get Web page URL
	// Input Arguments:1. WebDriver 
	// Return: String -> URL
	// Created By : Salim Ansari
	// Created On : 13-Feb-2019
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	public static String getWebPageURL(WebDriver driver) {
		javaScriptExecute = (JavascriptExecutor)driver;
		return javaScriptExecute.executeScript("return document.URL;").toString();
	}
	
	// **********************************************************************************************
	// Method : highlightElement
	// Description: Method is used to highlight web element
	// Input Arguments:1. WebDriver  2. WebElement
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 13-Feb-2019
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	public static void highlightElement(WebDriver driver, WebElement webElement) {
		javaScriptExecute = (JavascriptExecutor)driver;
		javaScriptExecute.executeScript("arguments[0].style.border='3px dotted blue'", webElement);
	}
	
	// **********************************************************************************************
	// Method : getBackGroundColor
	// Description: Method is used to get background color of webelement
	// Input Arguments:1. WebDriver  2. WebElement
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 13-Feb-2019
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	public static String getElementBackGroundColor(WebDriver driver, WebElement webElement) {
		javaScriptExecute = (JavascriptExecutor)driver;
		
		//update extent report
		//BaseClass.test.log(LogStatus.INFO, "Getting weblement back ground colour using JavaScript");
		return javaScriptExecute.executeScript("return window.getComputedStyle(arguments[0], ':after').getPropertyValue('background-color');",webElement).toString();
	}
	
	// **********************************************************************************************
	// Method : sendKeys
	// Description: Method is used to enter the value in the text box
	// Input Arguments:1. WebDriver  2. WebElement 3. text
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 13-Feb-2019
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	public static void sendKeys(WebDriver driver, WebElement webElement,String text) {
		javaScriptExecute = (JavascriptExecutor)driver;
		javaScriptExecute.executeScript("arguments[0].value='"+text+"';", webElement);
		
		//update extent report
		//BaseClass.test.log(LogStatus.INFO, "Sending value "+ text +  " using JavaScript");
	}
	
	// **********************************************************************************************
	// Method : getElementAfterProperty
	// Description: Method is used to get After property of WebElement
	// Input Arguments:1. WebDriver  2. WebElement 3. attributeValue
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 13-Feb-2019
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	public static String getElementAfterProperty(WebDriver driver, WebElement webElement,String attributeValue) {
		javaScriptExecute = (JavascriptExecutor)driver;
		
		//update extent report
		//BaseClass.test.log(LogStatus.INFO, "Getting webelement back ground color using JavaScript");
		return javaScriptExecute.executeScript("return window.getComputedStyle(arguments[0], ':after').getPropertyValue('"+attributeValue+"');",webElement).toString();
	}

	public static void openNewTabWithGivenURL(WebDriver driver, String navigateURL){
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("window.open('"+navigateURL+"', '_blank');");
	}
	
	// **********************************************************************************************
	// Method : getElementBeforeProperty
	// Description: Method is used to get Before property of WebElement
	// Input Arguments:1. WebDriver  2. WebElement 3. attributeValue
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 7-M-2019
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	public static String getElementBeforeProperty(WebDriver driver, WebElement webElement,String attributeValue) {
		javaScriptExecute = (JavascriptExecutor)driver;
		
		//update extent report
		//BaseClass.test.log(LogStatus.INFO, "Getting weblement back ground colour using JavaScript");
		return javaScriptExecute.executeScript("return window.getComputedStyle(arguments[0], ':before').getPropertyValue('"+attributeValue+"');",webElement).toString();
	}
}
