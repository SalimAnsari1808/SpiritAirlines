package com.spirit.managers;

import java.io.File;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.spirit.utility.ValidationUtil;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.spirit.enums.DriverType;
import com.spirit.enums.EnvironmentType;



public  class DeskTopWebDriverManager {

	private WebDriver driver;
	private DriverType driverType;
	private EnvironmentType environmentType;
	private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
	private static final String INTERNETEXPLORER_DRIVER_PROPERTY = "webdriver.ie.driver";
	private static final String FIREFOX_DRIVER_PROPERTY = "webdriver.gecko.driver";
	private static final String EDGE_DRIVER_PROPERTY = "webdriver.edge.driver";

	
	public DeskTopWebDriverManager(String driverReq) {
		// Get Environment Type(e.g. Local/Remote)
		environmentType = FileReaderManager.getInstance().getConfigReader().getEnvironment(driverReq);
		
		//taking driver type from config file
		driverType = FileReaderManager.getInstance().getConfigReader().getBrowserUnderTest(driverReq);
	}

	// **********************************************************************************************
	// Method : getDriver
	// Description: Method is used to get Driver 
	// Input Arguments: NA
	// Return: WebDriver
	// Created By : Salim Ansari
	// Created On : 12-Feb-2019
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	public synchronized WebDriver getDriver() {
		if (driver == null) {
			return createNewDriver();
		} else {
			return driver;
		}
	}

	// **********************************************************************************************
	// Method : createNewDriver
	// Description: Method is used to create new Driver 
	// Input Arguments: NA
	// Return: WebDriver
	// Created By : Salim Ansari
	// Created On : 12-Feb-2019
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	private synchronized WebDriver createNewDriver() {
		switch (environmentType) {
		case LOCAL:
			driver = createLocalDriver();
			break;
		case REMOTE:
			driver = createRemoteDriver();
			break;
		}
		return driver;
	}

	//***********************************************************************************************
	// Method : createRemoteDriver
	// Description: Method is used to create new Remote Driver 
	// Input Arguments: NA
	// Return: WebDriver
	// Created By : Salim Ansari
	// Created On : 12-Feb-2019
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	private synchronized WebDriver createRemoteDriver()  {
		
		//taking reference
		InetAddress localhost = null;
		DesiredCapabilities capabilities = null;
		String ipAddress = null;
		String Node = null;
		
		//getting information about local host
		try {
			localhost = InetAddress.getLocalHost();
		} catch (Exception e) {
			e.printStackTrace();
		}

		//getting IP address of the machine
		ipAddress = (localhost.getHostAddress()).trim();
		
		//creating node
		Node = "http://" + ipAddress + ":4455/wd/hub";
		// Node = "http://" + ipAddress + ":4466/wd/hub";

		 
		switch (driverType) {
		case FIREFOX:
			capabilities = DesiredCapabilities.firefox();
            capabilities.setBrowserName(BrowserType.FIREFOX);
			capabilities.setPlatform(Platform.ANY);
			break;
		case CHROME:
			capabilities = DesiredCapabilities.chrome();
            capabilities.setBrowserName(BrowserType.CHROME);
			capabilities.setPlatform(Platform.ANY);

			ChromeOptions options = new ChromeOptions();
			options.setPageLoadStrategy(PageLoadStrategy.NONE); // https://www.skptricks.com/2018/08/timed-out-receiving-message-from-renderer-selenium.html
			options.addArguments("start-maximized"); // https://stackoverflow.com/a/26283818/1689770
			options.addArguments("enable-automation"); // https://stackoverflow.com/a/43840128/1689770
			options.addArguments("--no-sandbox"); //https://stackoverflow.com/a/50725918/1689770
			options.addArguments("--disable-infobars"); //https://stackoverflow.com/a/43840128/1689770
			options.addArguments("--disable-dev-shm-usage"); //https://stackoverflow.com/a/50725918/1689770
			options.addArguments("--disable-browser-side-navigation"); //https://stackoverflow.com/a/49123152/1689770
			options.addArguments("--disable-gpu"); //https://stackoverflow.com/questions/51959986/how-to-solve-selenium-chromedriver-timed-out-receiving-message-from-renderer-exc
			//capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			break;
		case INTERNETEXPLORER:
            capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setBrowserName(BrowserType.IE);
            capabilities.setPlatform(Platform.WINDOWS);
            capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
            capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
            capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
			break;
		case SAFARI:
			capabilities = DesiredCapabilities.safari();
			capabilities.setBrowserName("safari");
			capabilities.setPlatform(Platform.MAC);
			capabilities.setCapability("javascriptEnabled", "false");
			break;
		case EDGE:
			capabilities = DesiredCapabilities.edge();
			capabilities.setBrowserName(BrowserType.EDGE);
			capabilities.setPlatform(Platform.WIN10);
			break;
		}
		 
/*		 DesiredCapabilities capabilities =  DesiredCapabilities.internetExplorer();
		 capabilities.setBrowserName("internet explorer");
		 capabilities.setPlatform(Platform.WINDOWS);
		 capabilities.setVersion("11");
		 capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		 capabilities.setCapability("acceptInsecureCerts", true);
		 capabilities.setCapability("acceptSslCerts",true);
		 capabilities.setAcceptInsecureCerts(true);
		 capabilities.acceptInsecureCerts();*/
		 
		try {
			driver = new RemoteWebDriver(new URL(Node), capabilities);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ValidationUtil.validateTestStep("Unable to create Remote driver",false);
		}
		 
		//Maximize window
		if(FileReaderManager.getInstance().getConfigReader().getWindowSize()) {
			driver.manage().window().maximize();
		}
		
		//implicit wait
		driver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigReader().getImplicitWait(), TimeUnit.SECONDS);
		
		//page load time
		driver.manage().timeouts().pageLoadTimeout(FileReaderManager.getInstance().getConfigReader().getPageLoadTimeOut(), TimeUnit.SECONDS);
		
	    // return created driver
		 return driver;
	}

	// **********************************************************************************************
	// Method : createLocalDriver
	// Description: Method is used to create new Local Driver 
	// Input Arguments: NA
	// Return: WebDriver
	// Created By : Salim Ansari
	// Created On : 12-Feb-2019
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	private synchronized WebDriver createLocalDriver()  {

		//switch according to driver type
		switch (driverType) {
		case FIREFOX:
			System.setProperty(FIREFOX_DRIVER_PROPERTY,
			System.getProperty("user.dir") + FileReaderManager.getInstance().getConfigReader().getBrowserDriverPath() + "geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		case CHROME:
//			System.setProperty(CHROME_DRIVER_PROPERTY,
//					System.getProperty("user.dir") + FileReaderManager.getInstance().getConfigReader().getBrowserDriverPath() + "chromedriver.exe");
//			Map<String, String> mobileEmulation = new HashMap<>();
//			mobileEmulation.put("deviceName", "Nexus 5");
//			ChromeOptions chromeOptions = new ChromeOptions();
//			chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
//
//			driver = new ChromeDriver(chromeOptions);

			System.setProperty(CHROME_DRIVER_PROPERTY,
			System.getProperty("user.dir") + FileReaderManager.getInstance().getConfigReader().getBrowserDriverPath() + "chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case INTERNETEXPLORER:
			System.setProperty(INTERNETEXPLORER_DRIVER_PROPERTY,
			System.getProperty("user.dir") + FileReaderManager.getInstance().getConfigReader().getBrowserDriverPath() + "IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			break;
		case SAFARI:
			// Do Nothing
			break;
		case EDGE:
			System.setProperty(EDGE_DRIVER_PROPERTY,
					System.getProperty("user.dir") + FileReaderManager.getInstance().getConfigReader().getBrowserDriverPath() + "MicrosoftWebDriver.exe");
			driver = new EdgeDriver();
			break;

		}

		// Maximize window
		if (FileReaderManager.getInstance().getConfigReader().getWindowSize()) {
			driver.manage().window().maximize();
		}
		
		// implicit wait
		driver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigReader().getImplicitWait(), TimeUnit.SECONDS);
		
		// page load time
		driver.manage().timeouts().pageLoadTimeout(FileReaderManager.getInstance().getConfigReader().getPageLoadTimeOut(), TimeUnit.SECONDS);

		// return created driver
		return driver;
	}


	public synchronized WebDriver createMobileDriver(){

		//taking reference
		InetAddress localhost = null;
		DesiredCapabilities capabilities = null;
		String ipAddress = null;
		String URL_STRING = null;

		//getting information about local host
		try {
			localhost = InetAddress.getLocalHost();
		} catch (Exception e) {
			e.printStackTrace();
		}

		//getting IP address of the machine
		ipAddress = (localhost.getHostAddress()).trim();

		//creating node
		URL_STRING = "http://" + ipAddress + ":4455/wd/hub";

		URL url;
		try{
			url = new URL(URL_STRING);
		}catch (MalformedURLException e){
			e.printStackTrace();
			throw new RuntimeException("");
		}

		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_2_XL_API_28");
		caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
		caps.setCapability("noReset", false);
		driver = new RemoteWebDriver(url, caps);

		// implicit wait
		driver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigReader().getImplicitWait(), TimeUnit.SECONDS);

		// page load time
		driver.manage().timeouts().pageLoadTimeout(FileReaderManager.getInstance().getConfigReader().getPageLoadTimeOut(), TimeUnit.SECONDS);

		// return created driver
		return driver;

	}
	
	// **********************************************************************************************
	// Method : closeDriver
	// Description: Method is used to close window and quit Driver 
	// Input Arguments: NA
	// Return: NA
	// Created By : Salim Ansari
	// Created On : 12-Feb-2019
	// Reviewed By:
	// Reviewed On:
	// **********************************************************************************************
	public void closeDriver() {
		// close current window
//		driver.close();
//		// Close all window
//		driver.quit();
	}	
}