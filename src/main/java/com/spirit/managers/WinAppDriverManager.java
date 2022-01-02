package com.spirit.managers;

import com.spirit.enums.DriverType;
import com.spirit.enums.EnvironmentType;
import com.spirit.utility.WaitUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class WinAppDriverManager {

    private WindowsDriver driver;
    private DriverType driverType;
    private EnvironmentType environmentType;

    //This method stores the application to be opened (GoNow/Skyspeed)
    public WinAppDriverManager(String application) {
        // Get Environment Type(e.g. Local/Remote)
        environmentType = FileReaderManager.getInstance().getConfigReader().getEnvironment(application);

        //taking driver type from config file
        driverType = FileReaderManager.getInstance().getConfigReader().getWindowApplicationUnderTest(application);
    }

    // **********************************************************************************************
    // Method : getDriver
    // Description: Method is used to get Window Driver
    // Input Arguments: NA
    // Return: WindowsDriver
    // Created By : Salim Ansari
    // Created On : 2-Jan-2020
    // Reviewed By:
    // Reviewed On:
    // **********************************************************************************************
    public WindowsDriver getDriver () throws MalformedURLException
    {
        if (driver == null) {
            return createNewDriver();
        } else {
            return driver;
        }
    }

    // **********************************************************************************************
    // Method : createNewDriver
    // Description: Method is used to create new WindowsDriver
    // Input Arguments: NA
    // Return: WindowsDriver
    // Created By : Salim Ansari
    // Created On : 2-Jan-2020
    // Reviewed By:
    // Reviewed On:
    // **********************************************************************************************
    private synchronized WindowsDriver createNewDriver() {
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
    // Return: WindowsDriver
    // Created By : Salim Ansari
    // Created On : 2-Jan-2020
    // Reviewed By:
    // Reviewed On:
    // **********************************************************************************************
    private synchronized WindowsDriver createRemoteDriver()  {

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

        DesiredCapabilities appCapabilities = new DesiredCapabilities();
        appCapabilities.setCapability("platformName", "Windows");
        appCapabilities.setCapability("platformVersion", "10");
        appCapabilities.setCapability("deviceName", "WindowsPC");

        //creating node
        Node = "http://" + ipAddress + ":4455/wd/hub";
        //  Node = "http://" + ipAddress + ":4466/wd/hub";

        switch (driverType) {
            case SKYSPEED:
                appCapabilities.setCapability ("app", "C:\\Program Files (x86)\\Navitaire\\NewSkies\\R4.2\\Spirit testr4x\\SkySpeed\\SkySpeed\\UI.Win.SkySpeed.exe");
                break;
            case SKYPORT:
                appCapabilities.setCapability ("app", "C:\\Program Files (x86)\\Spirit\\testr4x\\GoNow-2\\4.2.1.6\\GoNow Native1WayCommunication\\GoNow.exe");
                break;
        }

        try{
            driver = new WindowsDriver<>(new URL(Node), appCapabilities);
        }catch (MalformedURLException e){
            e.printStackTrace();
            throw new RuntimeException("");
        }

        //implicit wait
        driver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigReader().getImplicitWait(), TimeUnit.SECONDS);

        // return created driver
        return driver;
    }

    // **********************************************************************************************
    // Method : createLocalDriver
    // Description: Method is used to create new Local Driver
    // Input Arguments: NA
    // Return: WindowsDriver
    // Created By : Salim Ansari
    // Created On : 2-Jan-2020
    // Reviewed By:
    // Reviewed On:
    // **********************************************************************************************
    private synchronized WindowsDriver createLocalDriver()  {

        final String URL_STRING = "http://127.0.0.1:4723/wd/hub";
        URL url;
        try{
            url = new URL(URL_STRING);
        }catch (MalformedURLException e){
            e.printStackTrace();
            throw new RuntimeException("");
        }

        DesiredCapabilities appCapabilities = new DesiredCapabilities();
        appCapabilities.setCapability("platformName", "Windows");
        appCapabilities.setCapability("platformVersion", "10");
        appCapabilities.setCapability("deviceName", "WindowsPC");

        switch (driverType) {
            case SKYSPEED:
                appCapabilities.setCapability ("app", "C:\\Program Files (x86)\\Navitaire\\NewSkies\\R4.2\\Spirit testr4x\\SkySpeed\\SkySpeed\\UI.Win.SkySpeed.exe");
                break;
            case SKYPORT:
                appCapabilities.setCapability ("app", "C:\\Program Files (x86)\\Spirit\\testr4x\\GoNow-2\\4.2.1.6\\GoNow Native1WayCommunication\\GoNow.exe");
                break;
        }

        driver = new WindowsDriver<>(url, appCapabilities);

        // implicit wait
        driver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigReader().getImplicitWait(), TimeUnit.SECONDS);

        // return created driver
        return driver;
    }
}
