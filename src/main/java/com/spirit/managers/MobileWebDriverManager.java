package com.spirit.managers;

import com.spirit.enums.DriverType;
import com.spirit.enums.EnvironmentType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class MobileWebDriverManager {

    private WebDriver driver;
    private DriverType driverType;
    private EnvironmentType environmentType;
    private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
    private static final String INTERNETEXPLORER_DRIVER_PROPERTY = "webdriver.ie.driver";
    private static final String FIREFOX_DRIVER_PROPERTY = "webdriver.gecko.driver";
    private static final String EDGE_DRIVER_PROPERTY = "webdriver.edge.driver";


    public MobileWebDriverManager(String driverReq) {
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
        // URL_STRING = "http://" + ipAddress + ":4466/wd/hub";

        URL url;
        try{
            url = new URL(URL_STRING);
        }catch (MalformedURLException e){
            e.printStackTrace();
            throw new RuntimeException("");
        }

        DesiredCapabilities caps = new DesiredCapabilities();


        switch (driverType) {
            case FIREFOX:

                break;
            case CHROME:
                caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
                caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0");
                caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Andriod Emulator");
                caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
                caps.setCapability("noReset", false);
                break;
            case INTERNETEXPLORER:

                break;
            case SAFARI:

                break;
            case EDGE:

                break;
        }

        driver = new RemoteWebDriver(url, caps);

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

        final String URL_STRING = "http://127.0.0.1:4723/wd/hub";
        URL url;
        try{
            url = new URL(URL_STRING);
        }catch (MalformedURLException e){
            e.printStackTrace();
            throw new RuntimeException("");
        }

        DesiredCapabilities caps = new DesiredCapabilities();

        //switch according to driver type
        switch (driverType) {
            case FIREFOX:

                break;
            case CHROME:
                caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
                caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0");
                caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Andriod Emulator");
                caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
                caps.setCapability("noReset", false);
                break;
            case INTERNETEXPLORER:

                break;
            case SAFARI:
                // Do Nothing
                break;
            case EDGE:

                break;
        }

        driver = new RemoteWebDriver(url, caps);

        // implicit wait
        driver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigReader().getImplicitWait(), TimeUnit.SECONDS);

        // page load time
        driver.manage().timeouts().pageLoadTimeout(FileReaderManager.getInstance().getConfigReader().getPageLoadTimeOut(), TimeUnit.SECONDS);

        // return created driver
        return driver;
    }
}
