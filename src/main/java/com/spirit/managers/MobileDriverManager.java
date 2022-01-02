package com.spirit.managers;

import com.google.common.collect.ImmutableMap;
import com.spirit.enums.*;
import io.appium.java_client.*;
import io.appium.java_client.android.*;
import io.appium.java_client.remote.*;
import org.openqa.selenium.remote.*;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;


public class MobileDriverManager {

    private AppiumDriver  driver;
    private DriverType driverType;
    private MobileType mobileType;


    public MobileDriverManager(String requiredDriver){

        driverType = FileReaderManager.getInstance().getConfigReader().getMobileDriver(requiredDriver);

        mobileType = FileReaderManager.getInstance().getConfigReader().getMobileUnderTest(requiredDriver);

    }

    // **********************************************************************************************
    // Method : getDriver
    // Description: Method is used to get Appium Driver
    // Input Arguments: NA
    // Return: WebDriver
    // Created By : Salim Ansari
    // Created On : 15-Oct-2019
    // Reviewed By:
    // Reviewed On:
    // **********************************************************************************************
    public AppiumDriver getDriver(){
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
    // Created On : 15-Oct-2019
    // Reviewed By:
    // Reviewed On:
    // **********************************************************************************************
    private synchronized AppiumDriver createNewDriver() {
        switch (driverType) {
            case ANDROID:
                driver = createAndroidDriver();
                break;
            case IOS:
                driver = createiOSDriver();
                break;
        }
        return driver;
    }

    //***********************************************************************************************
    // Method : createAndroidDriver
    // Description: Method is used to create new Remote Driver
    // Input Arguments: NA
    // Return: WebDriver
    // Created By : Salim Ansari
    // Created On : 15-Oct-2019
    // Reviewed By:
    // Reviewed On:
    // **********************************************************************************************
    private synchronized AppiumDriver createAndroidDriver(){



        switch(mobileType){
            case GalaxyS8:
                final String URL_STRING = "http://127.0.0.1:4723/wd/hub";
                URL url;
                try{
                    url = new URL(URL_STRING);
                }catch (MalformedURLException e){
                    e.printStackTrace();
                    throw new RuntimeException("");
                }
//
                File app = new File(System.getProperty("user.dir") + FileReaderManager.getInstance().getConfigReader().getMobileApplicationPath() + "SpiritAndroidApp.apk");
                DesiredCapabilities caps = new DesiredCapabilities();
                //caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2"); //Appium (default) was not working. Set to UiAutomator2 or Espresso for Android or XCUITest for iOS
                caps.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID); //iOS, Android, or FirefoxOS
                caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator"); //iPhone Simulator, iPad Simulator, iPhone Retina 4-inch, Android Emulator, Galaxy S4, etc.... On iOS, this should be one of the valid devices returned by instruments with instruments -s devices. On Android this capability is currently ignored, though it remains required.
                caps.setCapability(MobileCapabilityType.APP, app.getAbsolutePath()); //The absolute local path or remote http URL to a .ipa file (IOS), .app folder (IOS Simulator), .apk file (Android) or .apks file (Android App Bundle)
                caps.setCapability("appPackage", "com.spirit.customerapp"); //[ADB] Found package: 'com.spirit.customerapp' and fully qualified activity name : 'com.spirit.enterprise.guestmobileapp.UI.Main.LandingActivity'
                caps.setCapability("appActivity", "com.spirit.enterprise.guestmobileapp.UI.Main.SplashActivity"); //Native App splashPage
                caps.setCapability("autoGrantPermissions", true); //To grant permission to notification, camera access, and location
                caps.setCapability(MobileCapabilityType.NO_RESET, true); //Default Stop and clear app data after test. Do not uninstall apk|| No Reset Do not stop app, do not clear app data, and do not uninstall apk. || Full Reset Stop app, clear app data and uninstall apk || https://github.com/appium/appium/blob/master/docs/en/writing-running-appium/other/reset-strategies.md
                caps.setCapability("appWaitActivity", "*"); //Wait for any activity within the application to be be complete to validate application successfully launched
                caps.setCapability("adbExecTimeout", "30000"); //Timeout after 30 seconds if emulator does not start

                driver = new AndroidDriver(url, caps);
        }

        //implicit wait
        driver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigReader().getImplicitWait(), TimeUnit.SECONDS);

        return driver;
    }

    //***********************************************************************************************
    // Method : createiOSDriver
    // Description: Method is used to create new Remote Driver
    // Input Arguments: NA
    // Return: WebDriver
    // Created By : Salim Ansari
    // Created On : 15-Oct-2019
    // Reviewed By:
    // Reviewed On:
    // **********************************************************************************************
    private synchronized AppiumDriver createiOSDriver(){

        return driver;
    }
}
