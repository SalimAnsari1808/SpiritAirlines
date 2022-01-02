package com.spirit.baseClass;


import com.aventstack.extentreports.*;
import com.spirit.enums.*;
import com.spirit.managers.*;
import com.spirit.mobileMethods.*;
import com.spirit.utility.*;
import io.appium.java_client.*;
import io.appium.java_client.android.*;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.*;
import org.testng.*;
import org.testng.annotations.*;

import java.io.*;
import java.net.MalformedURLException;
import java.util.*;


public class BaseClass {
	//declare class used in BaseClass
	public DeskTopWebDriverManager deskTopWebDriverManager;
	public WindowObjectManager windowObjectManager;
	public WindowMethodManager windowMethodManager;
	public MobileWebDriverManager mobileWebDriverManager;
	public PageObjectManager pageObjectManager;
	public PageMethodManager pageMethodManager;
	public MobileDriverManager mobileDriverManager;
	public WinAppDriverManager winAppDriverManager;
	public MobileObjectManager mobileObjectManager;
	public MobileMethodManager mobileMethodManager;
	public ScenarioContext scenarioContext;
	public ExcelSpreadSheetManager excelSpreadSheetManager;
	public AppiumServer appiumServer;

	private ExtentReports extent;
	private ActiveNodeDeterminer activeNodeDeterminer;
											   
	//declare ThreadLocal to maintain multiple threads
	public static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>();
	public static ThreadLocal<ExtentTest> extReport  = new ThreadLocal<ExtentTest>();
	/*
	 * Declare getter and setter for web driver  
	 */
	public WebDriver getDriver() {
		return threadDriver.get();
	}
	
	public void setDriver(WebDriver driver) {
		threadDriver.set(driver);
	}

	/*
	 * Declare getter and setter for Extent Report  
	 */
	public synchronized ExtentTest getExtText() {
		return extReport.get();
	}
	
	public synchronized void setExtTest(ExtentTest et) {
		extReport.set(et);
	}

	public synchronized void initializeAPITesting(){
		pageMethodManager = new PageMethodManager(getDriver(),pageObjectManager,scenarioContext);
	}

	// **********************************************************************************************
	// Method : openBrowser
	// Description: Method is used to open Browser on DeskTop Platform
	// Input Arguments: NA
	// Return: WebDriver
	// Created By : Salim Ansari
	// Created On : 12-Feb-2019
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	public synchronized void openBrowser(String platform) {
		activeNodeDeterminer = new ActiveNodeDeterminer("localhost", 4455);

		//Create Instance of WebDriver Manager
		deskTopWebDriverManager = new DeskTopWebDriverManager(platform);
		
		//Get Driver instance from WebDriver manager Class
		setDriver(deskTopWebDriverManager.getDriver());
		
		//initialize page objects
		pageObjectManager = new PageObjectManager(getDriver());

//		//create global variable hashmap
//		scenarioContext = new ScenarioContext();
		
		//initialize page methods
		pageMethodManager = new PageMethodManager(getDriver(),pageObjectManager,scenarioContext);
		
		excelSpreadSheetManager = new ExcelSpreadSheetManager();

		if(platform.equalsIgnoreCase("NA")){
			//set browser under test
			scenarioContext.setContext(Context.HOMEPAGE_BROWSER,FileReaderManager.getInstance().getConfigReader().getBrowserUnderTest(platform));
		}else{
			//set browser under test
			scenarioContext.setContext(Context.HOMEPAGE_BROWSER,platform);

			//get node machine name
			ValidationUtil.validateTestStep("*********** Test Case is running on " + activeNodeDeterminer.getNodeInfoForSession(((RemoteWebDriver)getDriver()).getSessionId()) + " ***********", true);
		}
	}

	// **********************************************************************************************
	// Method : openMobileBrowser
	// Description: Method is used to open Browser on Mobile Platform
	// Input Arguments: NA
	// Return: WebDriver
	// Created By : Salim Ansari
	// Created On : 30-Dec-2019
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	public synchronized void openMobileBrowser(String platform) {
		activeNodeDeterminer = new ActiveNodeDeterminer("localhost", 4455);
		//Create Instance of WebDriver Manager
		mobileWebDriverManager = new MobileWebDriverManager(platform);

		//Get Driver instance from WebDriver manager Class
		setDriver(mobileWebDriverManager.getDriver());

		//initialize page objects
		pageObjectManager = new PageObjectManager(getDriver());

//		//create global variable hashmap
//		scenarioContext = new ScenarioContext();

		//initialize page methods
		pageMethodManager = new PageMethodManager(getDriver(),pageObjectManager,scenarioContext);

		excelSpreadSheetManager = new ExcelSpreadSheetManager();

		if(platform.equalsIgnoreCase("NA")){
			//set browser under test
			scenarioContext.setContext(Context.HOMEPAGE_BROWSER,FileReaderManager.getInstance().getConfigReader().getBrowserUnderTest(platform));
		}else{
			//set browser under test
			scenarioContext.setContext(Context.HOMEPAGE_BROWSER,platform);

			System.out.println(((RemoteWebDriver)getDriver()).getSessionId());

			//get node machine name
			ValidationUtil.validateTestStep("*********** Test Case is running on " + activeNodeDeterminer.getNodeInfoForSession(((RemoteWebDriver)getDriver()).getSessionId()) + " ***********", true);
		}
	}

	// **********************************************************************************************
	// Method : openNativeApp
	// Description: Method is used to open Native Application on Mobile Platform
	// Input Arguments: NA
	// Return: WebDriver
	// Created By : Salim Ansari
	// Created On : 30-Dec-2019
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	public synchronized void openNativeApp(String platform) {
		activeNodeDeterminer = new ActiveNodeDeterminer("localhost", 4455);
		//Create Instance of Mobile Driver Manager
		mobileDriverManager = new MobileDriverManager(platform);

		//Get Driver instance from Mobile manager Class
		setDriver(mobileDriverManager.getDriver());

		//initialize mobile objects
		mobileObjectManager = new MobileObjectManager((AppiumDriver)getDriver());

//		//create global variable hashmap
//		scenarioContext = new ScenarioContext();

		//initialize mobile methods
		mobileMethodManager = new MobileMethodManager((AppiumDriver)getDriver(),mobileObjectManager,scenarioContext);

		excelSpreadSheetManager = new ExcelSpreadSheetManager();

		if(platform.equalsIgnoreCase("NA")){
			//set browser under test
			scenarioContext.setContext(Context.HOMEPAGE_MOBILE,FileReaderManager.getInstance().getConfigReader().getMobileUnderTest(platform));
		}else{
			//set browser under test
			scenarioContext.setContext(Context.HOMEPAGE_MOBILE,platform);

			//get node machine name
			ValidationUtil.validateTestStep("*********** Test Case is running on " + activeNodeDeterminer.getNodeInfoForSession(((RemoteWebDriver)getDriver()).getSessionId()) + " ***********", true);
		}

	}

	// **********************************************************************************************
	// Method : openWindowApplication
	// Description: Method is used to open Window Application on Window Platform
	// Input Arguments: NA
	// Return: WebDriver
	// Created By : Anthony C
	// Created On : 02-Jan-2020
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	public synchronized void openWindowApplication(String platform) {
		activeNodeDeterminer = new ActiveNodeDeterminer("localhost", 4455);

		//Create Instance of WinAppDriver Driver Manager
		winAppDriverManager = new WinAppDriverManager(platform);

		//Get Driver instance from WinAppDriver manager Class
		try {
			setDriver(winAppDriverManager.getDriver());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		//initialize WinAppDriver objects
		windowObjectManager = new WindowObjectManager((WindowsDriver) getDriver());

		//initialize WinAppDriver methods
		windowMethodManager = new WindowMethodManager((WindowsDriver) getDriver(), windowObjectManager, scenarioContext);

		//Check for Local and Remote environment
		if(platform.equalsIgnoreCase("NA")){
			//set browser under test
			scenarioContext.setContext(Context.WINDOW_APPPLICATION,FileReaderManager.getInstance().getConfigReader().getMobileUnderTest(platform));
		}else{
			//set browser under test
			scenarioContext.setContext(Context.WINDOW_APPPLICATION,platform);
				//get node machine name
			ValidationUtil.validateTestStep("*********** Test Case is running on " + activeNodeDeterminer.getNodeInfoForSession(((RemoteWebDriver)getDriver()).getSessionId()) + " ***********", true);
		}
		// ValidationUtil.validateTestStep("*********** " + platform + "opened on " + activeNodeDeterminer.getNodeInfoForSession(((RemoteWebDriver)getDriver()).getSessionId()) + " ***********", true);

	}

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}


	@BeforeSuite(alwaysRun = true)
	public synchronized void suiteSetup(ITestContext context) {
		String suiteName, appURL = null;
		//get the suite name from xml file
		suiteName = context.getCurrentXmlTest().getSuite().getName();
		System.out.println("This is the start of Suite " + suiteName);
		extent =  ExtentReportManager.getInstance();
		//System.out.println(TestUtil.getLatestReportFile().getName());
		//AppiumServer.appiumServer();
	}

	@BeforeMethod(alwaysRun = true)
	public synchronized void setup(ITestResult result, ITestContext context) {
		System.out.println("Start of Before Method");
		//get the suite name from xml file
		String suiteName = context.getCurrentXmlTest().getSuite().getName();

		//extent =  ExtentReportManager.getInstance();
		setExtTest(extent.createTest(result.getMethod().getMethodName()));
		getExtText().assignCategory(suiteName);

		//create global variable hashmap
		scenarioContext = new ScenarioContext();

		ValidationUtil.validateTestStep("Computer Name: "+ getComputerName() , true );
	}

	public String getComputerName()
	{
		Map<String, String> env = System.getenv();
		if (env.containsKey("COMPUTERNAME"))
			return env.get("COMPUTERNAME");
		else if (env.containsKey("HOSTNAME"))
			return env.get("HOSTNAME");
		else
			return "Unknown Computer";
	}

	@AfterMethod(alwaysRun = true)
	public synchronized void tearDown(ITestResult result,ITestContext context) {
		//declare variable used in method
		String suiteName, appURL = null;
		System.out.println("Start of After Method");
		if(getDriver() instanceof WebDriver) {
			getDriver().switchTo().defaultContent();
		}else if(getDriver() instanceof AppiumDriver){
			//TODO: Do Something?
		}else if(getDriver() instanceof WindowsDriver){
			//TODO: Do Something?
		}else{
			//TODO Nothing
		}

		if(result.getStatus()==ITestResult.SUCCESS){
			getExtText().pass(getTestMethodName(result).toUpperCase() +  " is Passed");
		}else if(result.getStatus()==ITestResult.FAILURE){
			getExtText().fail(getTestMethodName(result).toUpperCase() +  " is Failed");
			getExtText().fail(result.getThrowable());
			try{
				if(getDriver() != null) {
					String base64Screenshot = TestUtil.getBase64Screenshot(getDriver());
					MediaEntityModelProvider mediaModel = MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build();
					getExtText().fail(" Failed ScreenShot", mediaModel);
				}
			}catch(IOException ex){
			}
			//get the suite name from xml file
			suiteName = context.getCurrentXmlTest().getSuite().getName();
			//check running suite is heart beat
			if(context.getCurrentXmlTest().getSuite().getName().contains("HeartBeat")){
				//send mail to recipient
				EmailUtil.sendEmailWithAttachReport(TestUtil.getLatestFailedSnapshotFile(),suiteName);
			}
		}else if(result.getStatus()==ITestResult.SKIP){
			getExtText().skip(getTestMethodName(result).toUpperCase() +  " is Skipped" + result.getThrowable());
			getExtText().skip(result.getThrowable());
			ExtentReportManager.getInstance().removeTest(getExtText());
		}
		//log out from application
		if(getDriver() instanceof WebDriver){
			if(scenarioContext.getContext(Context.HOMEPAGE_BROWSER).toString().equalsIgnoreCase("InternetExplorer") ||
					scenarioContext.getContext(Context.HOMEPAGE_BROWSER).toString().equalsIgnoreCase("Edge")){
				//logout from application
				pageMethodManager.getHomePageMethods().logoutFromApplication();
				//close current window

			}else if (getDriver() instanceof AppiumDriver) {
				AppiumDriver driver = (AppiumDriver) getDriver();
				//stop Appium server
				driver.closeApp();
				appiumServer.stopServer();
			}
			else if(scenarioContext.isContains(Context.WINDOW_APPPLICATION)){
				if(scenarioContext.getContext(Context.WINDOW_APPPLICATION).toString().equalsIgnoreCase("SkySpeed")){
				}
			}
			getDriver().quit();
			threadDriver.set(null);
		}
//		try {
//			Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe /T");
//			Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
//			Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe /T");
//			Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe /T");
//			Runtime.getRuntime().exec("taskkill /F /IM MicrosoftWebDriver.exe /T");
//		}catch (Exception e){
//			e.printStackTrace();
//		}
	}

	@AfterSuite(alwaysRun = true)
	public synchronized void cleanupSuite(ITestContext context) {
		String suiteName, appURL = null;
		System.out.println("Start of After Suite");
		//get the suite name from xml file
		suiteName = context.getCurrentXmlTest().getSuite().getName();
		extent.flush();
//		EmailUtil.sendEmailWithAttachReport(TestUtil.getLatestReportFile(),suiteName);
	}

}
