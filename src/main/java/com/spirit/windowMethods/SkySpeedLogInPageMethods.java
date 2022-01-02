package com.spirit.windowMethods;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.dataType.SkySpeedLoginCredentialsData;
import com.spirit.managers.FileReaderManager;
import com.spirit.managers.WindowObjectManager;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import com.spirit.windowObjects.SkySpeedLogInPage;
import io.appium.java_client.windows.WindowsDriver;


import java.util.ArrayList;

public class SkySpeedLogInPageMethods {
    private WindowsDriver       driver;
    private WindowObjectManager windowObjectManager;
    private ScenarioContext     scenarioContext;
    private SkySpeedLogInPage   skySpeedLogInPage;

    public SkySpeedLogInPageMethods(WindowsDriver driver, WindowObjectManager windowObjectManager, ScenarioContext scenarioContext){
        this.driver              = driver;
        this.scenarioContext     = scenarioContext;
        this.windowObjectManager = windowObjectManager;
        skySpeedLogInPage        = windowObjectManager.getSkySpeedLogInPage();

    }

    //**********************************************************************************************
    //Method Name: loginToSkySpeedApplication
    //Description: Method is used to login on SkySpeed
    //Input Arguments: String->loginType
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 21-Jan-2020
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void loginToSkySpeedApplication(String loginType){
        //get teh skyspeed login details
        SkySpeedLoginCredentialsData skySpeedLoginCredentialsData = FileReaderManager.getInstance().getJsonReader().getSkySpeedCredentialsByUserType(loginType);

        //enter username
        skySpeedLogInPage.getUserNameTextBox().sendKeys(skySpeedLoginCredentialsData.userName);

        //enter password
        skySpeedLogInPage.getPasswordTextBox().sendKeys(skySpeedLoginCredentialsData.password);

        //click on Login button
        skySpeedLogInPage.getLoginButton().click();

        //validate Login to SkySpeed
        ValidationUtil.validateTestStep("Login to SkySpeed with " + loginType + " on SkySpeed Login Page",true);

        WaitUtil.untilTimeCompleted(10000);

        //switch to SkySpeed window
        TestUtil.switchToWindow(driver,0);

        //maximize window
        TestUtil.menuOptionSkySpeed("MaximumWindow");

        //start new booking
        TestUtil.menuOptionSkySpeed("NewBooking");
    }
}
