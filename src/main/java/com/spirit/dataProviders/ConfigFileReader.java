package com.spirit.dataProviders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.spirit.enums.DriverType;
import com.spirit.enums.EnvironmentType;
import com.spirit.enums.MobileType;
import com.spirit.managers.FileReaderManager;

public class ConfigFileReader {
	
	private Properties properties;
	private final String ConfigFilePath = "/src/main/resources/configs/Configuation.properties";
	
	public ConfigFileReader() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + ConfigFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + ConfigFilePath);
		}	
	}

	//Get Application URL from configuration property file
	public String getApplicationURL() {
		String applicationURL = properties.getProperty("url");
		if(applicationURL != null) {
			return applicationURL;
		}else {
			throw new RuntimeException("url not specified in the Configuration.properties file");
		}
	}

	public DriverType getWindowApplicationUnderTest(String windowParallel){
		String windowUnderTest;

		if(windowParallel.equalsIgnoreCase("NA")) {
			windowUnderTest = properties.getProperty("windowApp");
		}else {
			windowUnderTest = windowParallel;
		}

		if(windowUnderTest.equalsIgnoreCase("skyspeed")){
			return DriverType.SKYSPEED;
		}else if(windowUnderTest.equalsIgnoreCase("skyport")){
			return DriverType.SKYPORT;
		}else{
			throw new RuntimeException("Wiondow Application Under Test is not specified in the Configuration.properties file");
		}
	}
	
	public DriverType getBrowserUnderTest(String browserParallel) {
		String browserUnderTest;
		if(browserParallel.equalsIgnoreCase("NA")) {
			browserUnderTest = properties.getProperty("browser");
		}else {
			browserUnderTest = browserParallel;
		}

		if(browserUnderTest == null || browserUnderTest.equalsIgnoreCase("InternetExplorer")) {
			return DriverType.INTERNETEXPLORER;
		}else if(browserUnderTest.equalsIgnoreCase("Chrome")) {
			return DriverType.CHROME;
		}else if(browserUnderTest.equalsIgnoreCase("Firefox")) {
			return DriverType.FIREFOX;
		}else if(browserUnderTest.equalsIgnoreCase("Safari")) {
			return DriverType.SAFARI;
		}else if (browserUnderTest.equalsIgnoreCase("Edge")){
			return DriverType.EDGE;
		}else if(browserUnderTest.equalsIgnoreCase("Android")){
			return DriverType.ANDROID;
		}else if(browserUnderTest.equalsIgnoreCase("Ios")){
			return DriverType.IOS;
		}else {
			throw new RuntimeException("Browser under test is not specified in the Configuration.properties file");
		}
	}


	public DriverType getMobileDriver(String requiredDriver){
		if(requiredDriver.toLowerCase().contains("iphone")){
			//store andriod driver
			return DriverType.IOS;
		}else if(!requiredDriver.toLowerCase().equalsIgnoreCase("na")){
			//create ios driver
			return DriverType.ANDROID;
		}else if(requiredDriver.toLowerCase().equalsIgnoreCase("na")){
			//get value from config file
			if(properties.getProperty("mobile").equalsIgnoreCase("iphone")){
				return DriverType.IOS;
			}else {
				return DriverType.ANDROID;
			}
		}

		//throw new RuntimeException("Browser under test is not specified in the Configuration.properties file");
		return null;
	}


	public MobileType getMobileUnderTest(String mobileType){
		switch(mobileType.toLowerCase()){
			case "iphone7":
				return MobileType.iPhone7;
			case "iphone7plus":
				return MobileType.iPhone7Plus;
			case "iphone8":
				return MobileType.iPhone8;
			case "iphone8plus":
				return MobileType.iPhone8Plus;
			case "iphonex":
				return MobileType.iPhoneX;
			case "iphonexr":
				return MobileType.iPhoneXR;
			case "iphonexsmax":
				return MobileType.iPhoneXSMax;
			case "galaxys8":
				return MobileType.GalaxyS8;
			case "galaxys8plus":
				return MobileType.GalaxyS8Plus;
			case "galaxys9":
				return MobileType.GalaxyS9;
			case "galaxys9plus":
				return MobileType.GalaxyS9Plus;
			case "galaxys10":
				return MobileType.GalaxyS10;
			case "galaxys10plus":
				return MobileType.GalaxyS10Plus;
			case "galaxynote8":
				return MobileType.GalaxyNote8;
			case "galaxynote9":
				return MobileType.GalaxyNote9;
			case "na":
				//check mobile type from config file
				switch(properties.getProperty("mobile").toLowerCase()){
					case "iphone7":
						return MobileType.iPhone7;
					case "iphone7plus":
						return MobileType.iPhone7Plus;
					case "iphone8":
						return MobileType.iPhone8;
					case "iphone8plus":
						return MobileType.iPhone8Plus;
					case "iphonex":
						return MobileType.iPhoneX;
					case "iphonexr":
						return MobileType.iPhoneXR;
					case "iphonexsmax":
						return MobileType.iPhoneXSMax;
					case "galaxys8":
						return MobileType.GalaxyS8;
					case "galaxys8plus":
						return MobileType.GalaxyS8Plus;
					case "galaxys9":
						return MobileType.GalaxyS9;
					case "galaxys9plus":
						return MobileType.GalaxyS9Plus;
					case "galaxys10":
						return MobileType.GalaxyS10;
					case "galaxys10plus":
						return MobileType.GalaxyS10Plus;
					case "galaxynote8":
						return MobileType.GalaxyNote8;
					case "galaxynote9":
						return MobileType.GalaxyNote9;
				}
		}
		return null;
	}
	
	public String getUserName() {
		String UserName = properties.getProperty("username");
		if(UserName != null) {
			return UserName;
		}else {
			throw new RuntimeException("Application UserName is not specified in the Configuration.properties file");
		}
	}
	
	public String getPassword() {
		String Password = properties.getProperty("password");
		if(Password != null) {
			return Password;
		}else {
			throw new RuntimeException("Application Password is not specified in the Configuration.properties file");
		}
	}
	
	public Long getImplicitWait() {
		String implicitWait = properties.getProperty("implicitWait");
		if(implicitWait != null) {
			try {
				return Long.parseLong(implicitWait);
			}catch(NumberFormatException e) {
				throw new RuntimeException("Not able to parse value : " + implicitWait + " in to Long");
			}
		}else {
			throw new RuntimeException("Implicit Wait is not specified in the Configuration.properties file");
		}
	}
	
	public Long getExplicitWait() {
		String explicitWait = properties.getProperty("explicitWait");
		if(explicitWait != null) {
			try {
				return Long.parseLong(explicitWait);
			}catch(NumberFormatException e) {
				throw new RuntimeException("Not able to parse value : " + explicitWait + " in to Long");
			}
		}else {
			throw new RuntimeException("Explicit Wait is not specified in the Configuration.properties file");
		}
	}
	
	public Long getPageLoadTimeOut() {
		String pageLoadTimeOut = properties.getProperty("pageLoadTimeOut");
		if(pageLoadTimeOut != null) {
			try {
				return Long.parseLong(pageLoadTimeOut);
			}catch(NumberFormatException e) {
				throw new RuntimeException("Not able to parse value : " + pageLoadTimeOut + " in to Long");
			}
		}else {
			throw new RuntimeException("Page Load TimeOut is not specified in the Configuration.properties file");
		}
	}
	
	public EnvironmentType getEnvironment(String browserParallel) {
		String environmentType = properties.getProperty("environment");
		//|| environmentType.equalsIgnoreCase("local")
		//environmentType.equalsIgnoreCase("remote") ||
		if(environmentType == null || browserParallel.equalsIgnoreCase("NA")) {
			return EnvironmentType.LOCAL;
		}else if(!browserParallel.equalsIgnoreCase("NA")) {
			return EnvironmentType.REMOTE;
		}else {
			throw new RuntimeException("Environment Type is not specified in the Configuration.properties file");
		}
	}
	
	public Boolean getWindowSize() {
		String windowSize = properties.getProperty("windowMaximize");
		if(windowSize != null) {
			return Boolean.valueOf(windowSize);
		}else {
			return true;
		}
	}
	
	public String getReportExtentPath() {
		String reportExtentPath = properties.getProperty("reportExtentPath");
		if(reportExtentPath != null) {
			return reportExtentPath;
		}else {
			throw new RuntimeException("Extent Report xml file path is not specified in the Configuration.properties file");
		}
	}

    public String getMobileApplicationPath() {
        String mobileApplicationPath = properties.getProperty("mobileApplicationPath");
        if(mobileApplicationPath != null) {
            return mobileApplicationPath;
        }else {
            throw new RuntimeException("Mobile Application Path is not specified in the Configuration.properties file");
        }
    }

    public String getBrowserDriverPath() {
        String browserDriverPath = properties.getProperty("browserDriverPath");
        if(browserDriverPath != null) {
            return browserDriverPath;
        }else {
            throw new RuntimeException("Browser Drivers Path is not specified in the Configuration.properties file");
        }
    }
	
	public String getExcelDataPath() {
		String reportExcelDataPath = properties.getProperty("excelDataPath");
		if(reportExcelDataPath != null) {
			return reportExcelDataPath;
		}else {
			throw new RuntimeException("Base Line Report xls file path is not specified in the Configuration.properties file");
		}
	}
	

	public String getTestDataResourcePath(){
		String testDataResourcePath = properties.getProperty("testDataResourcePath");
		if(testDataResourcePath!= null) {
			return testDataResourcePath;
		}else {
			throw new RuntimeException("Test Data Resource Path not specified in the Configuration.properties file for the Key:testDataResourcePath");		
		}
	}

	public Boolean getROKTWitchValue(){
		String roktSwitch = properties.getProperty("switchROKT");
		if(roktSwitch!= null) {
			return Boolean.valueOf(roktSwitch);
		}else {
			throw new RuntimeException("ROKT Switch Value is not specified in the Configuration.properties file");
		}
	}

	public String getCarProviderURL(){
		String carProviderURL = properties.getProperty("carProviderURL");
		if(carProviderURL!= null) {
			return carProviderURL;
		}else {
			throw new RuntimeException("Car Provider URL Value is not specified in the Configuration.properties file");
		}
	}

	public String getHotelProviderURL(){
		String hotelProviderURL = properties.getProperty("hotelProviderURL");
		if(hotelProviderURL!= null) {
			return hotelProviderURL;
		}else {
			throw new RuntimeException("Hotel Provider URL Value is not specified in the Configuration.properties file");
		}
	}

}
