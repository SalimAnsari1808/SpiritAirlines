package com.spirit.mobileMethods;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.dataType.LoginCredentialsData;
import com.spirit.enums.Context;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import io.appium.java_client.AppiumDriver;
import com.spirit.managers.MobileObjectManager;


public class TripsPageMethods {

    private AppiumDriver driver;
    private MobileObjectManager mobileObjectManager;
    private ScenarioContext scenarioContext;

    public  TripsPageMethods(AppiumDriver driver, MobileObjectManager mobileObjectManager, ScenarioContext scenarioContext){
        this.driver = driver;
        this.mobileObjectManager = mobileObjectManager;
        this.scenarioContext = scenarioContext;
    }

    // **********************************************************************************************
    // Method : clickTripHeaderSection
    // Description: Method is used to click on the header section of Trips Screen
    // Input Arguments: headerValue
    // Return: Null
    // Created By : Salim Ansari
    // Created On : 21-Oct-2019
    // Reviewed By: Kartik Chauhan
    // Reviewed On: 21-Oct-2019
    // ***********************************************************************************************
    public void clickTripHeaderSection(String headerValue){
        //check the passed value
        switch(headerValue.toLowerCase()){
            case "signin":
                //click on sign-in button
                mobileObjectManager.getTripsPage().getTripsHeaderSignInButton().click();

                //validate sign-in is clicked
                ValidationUtil.validateTestStep("User click on Sign-In Button on Trip Header Screen",
                        mobileObjectManager.getSignInPage().getSpiritLogoImage().isDisplayed());

                //break
                break;
            case "addtrip":
                //click on add trip plus button
                mobileObjectManager.getTripsPage().getTripsHeaderPlusButton().click();

                //validate sign-in is clicked
                ValidationUtil.validateTestStep("User click on Add trip Plus Button on Trip Header Screen",
                        mobileObjectManager.getFindATripPage().getFindATripHeaderText().isDisplayed());
        }

    }

    // **********************************************************************************************
    // Method : clickTripMiddleSection
    // Description: Method is used to click on the middle section of Trips Screen
    // Input Arguments: headerValue
    // Return: Null
    // Created By : Salim Ansari
    // Created On : 21-Oct-2019
    // Reviewed By: Kartik Chauhan
    // Reviewed On: 21-Oct-2019
    // ***********************************************************************************************
    public void clickTripMiddleSection(String middleValue){
        //declare constant used in method
        final String HEADER = "Book";

        //check the passed value
        switch(middleValue.toLowerCase()){
            case "bookatrip":
                //click on book a trip button
                mobileObjectManager.getTripsPage().getMiddleContentBookATripButton().click();

                //validate book a trip is clicked
                ValidationUtil.validateTestStep("User click on Book A Trip Button on Trip Header Screen",
                        mobileObjectManager.getBookPage().getBookHeaderText().getAttribute("text").equalsIgnoreCase(HEADER));

                //break
                break;
            case "findatrip":
                //click on add find a trip button
                mobileObjectManager.getTripsPage().getMiddleContentFindTripButton().click();

                //validate find a trip is clicked
                ValidationUtil.validateTestStep("User click on Find trip Button on Trip Header Screen",
                        mobileObjectManager.getFindATripPage().getFindATripHeaderText().isDisplayed());
        }
    }
}
