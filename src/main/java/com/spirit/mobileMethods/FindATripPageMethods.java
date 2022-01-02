package com.spirit.mobileMethods;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.enums.Context;
import com.spirit.managers.MobileObjectManager;
import com.spirit.utility.WaitUtil;
import io.appium.java_client.AppiumDriver;

public class FindATripPageMethods {

    private AppiumDriver driver;
    private MobileObjectManager mobileObjectManager;
    private ScenarioContext scenarioContext;

    public FindATripPageMethods(AppiumDriver driver, MobileObjectManager mobileObjectManager, ScenarioContext scenarioContext){
        this.driver = driver;
        this.mobileObjectManager = mobileObjectManager;
        this.scenarioContext = scenarioContext;
    }


    // **********************************************************************************************
    // Method : addTripFromHeader
    // Description: Method is used to add trip on the Find a Trip Screen
    // Input Arguments: NA
    // Return:
    // Created By : Salim Ansari
    // Created On : 22-Oct-2019
    // Reviewed By: Kartik Chauhan
    // Reviewed On: 22-Oct-2019
    // ***********************************************************************************************
    public void addTripFromHeader(){

        //wait for find a trip mobile screen appear
        WaitUtil.untilElementIsClickable(driver,mobileObjectManager.getFindATripPage().getFindATripHeaderText());

        //enter last name
        mobileObjectManager.getFindATripPage().getLastNameTextBox().sendKeys(scenarioContext.getContext(Context.CONFIRMATION_LASTNAME).toString());

        //enter pnr number
        mobileObjectManager.getFindATripPage().getConfirmationCodeTextBox().sendKeys(scenarioContext.getContext(Context. CONFIRMATION_CODE).toString());

        //click find trip
        mobileObjectManager.getFindATripPage().getFindTripRetrievePNRButton().click();


    }
}
