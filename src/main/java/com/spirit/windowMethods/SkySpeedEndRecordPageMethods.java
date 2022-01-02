package com.spirit.windowMethods;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.managers.WindowObjectManager;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.windowObjects.SkySpeedEndRecordPage;
import com.spirit.windowObjects.SkySpeedMiscellaneousPage;
import io.appium.java_client.windows.WindowsDriver;

public class SkySpeedEndRecordPageMethods {
    private WindowsDriver               driver;
    private WindowObjectManager         windowObjectManager;
    private ScenarioContext             scenarioContext;
    private SkySpeedEndRecordPage       skySpeedEndRecordPage;
    private SkySpeedMiscellaneousPage   skySpeedMiscellaneousPage;

    public SkySpeedEndRecordPageMethods(WindowsDriver driver, WindowObjectManager windowObjectManager, ScenarioContext scenarioContext){
        this.driver                 = driver;
        this.scenarioContext        = scenarioContext;
        this.windowObjectManager    = windowObjectManager;
        skySpeedEndRecordPage       = windowObjectManager.getSkySpeedEndRecordPage();
        skySpeedMiscellaneousPage   = windowObjectManager.getSkySpeedMiscellaneousPage();

    }


    //**********************************************************************************************
    //Method Name: clickNextEndRecordPage
    //Description: Method is used to click next button on contact information page
    //Input Arguments: NA
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 28-Jan-2020
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void clickNextEndRecordPage(){

        TestUtil.switchToWindow(driver, 0);

        //click on Next button
        //skySpeedEndRecordPage.getNextButton().click();
        driver.findElementByXPath("//*[@AutomationId = '_buttonContinue']").click();

        //click on next button
        ValidationUtil.validateTestStep("Clicked on Next Button on End Record Page", true);
    }

    //**********************************************************************************************
    //Method Name: clickNextEndRecordPage
    //Description: Method is used to click next button on contact information page
    //Input Arguments: NA
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 28-Jan-2020
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void fillCallerDetails(){
        //enter caller name
        skySpeedMiscellaneousPage.getCallerInformationNameTextBox().sendKeys("Spirit Automation");

        //enter caller number
        skySpeedMiscellaneousPage.getCallerInformationNumberTextBox().sendKeys("7834862764");

        //click ok button
        skySpeedMiscellaneousPage.getCallerInformationOkButton().click();

        //fill caller details
        ValidationUtil.validateTestStep("Caller details are filled on End Record Page", true);
    }


}
