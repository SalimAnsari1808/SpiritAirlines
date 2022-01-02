package com.spirit.mobileMethods;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.managers.MobileObjectManager;
import com.spirit.utility.ValidationUtil;
import io.appium.java_client.AppiumDriver;

public class NavigationFooterPageMethods {

    private AppiumDriver driver;
    private MobileObjectManager mobileObjectManager;
    private ScenarioContext scenarioContext;

    public  NavigationFooterPageMethods(AppiumDriver driver, MobileObjectManager mobileObjectManager, ScenarioContext scenarioContext){
        this.driver = driver;
        this.mobileObjectManager = mobileObjectManager;
        this.scenarioContext = scenarioContext;
    }

    // **********************************************************************************************
    // Method : footerNavigation
    // Description: Method is used to navigate to different screen of Native Application
    // Input Arguments: navigateTo->to be Navigated screen value
    // Return: Null
    // Created By : Salim Ansari
    // Created On : 21-Oct-2019
    // Reviewed By: Kartik Chauhan
    // Reviewed On: 21-Oct-2019
    // ***********************************************************************************************
    public void footerNavigation(String navigateTo){

        switch(navigateTo.toLowerCase()){
            case "trips":
                //click on footer trips link
                mobileObjectManager.getNavigationFooterPage().getFootersTripsButton().click();

                //verify Trips screen is visible
                ValidationUtil.validateTestStep("Verify Application is navigated to Trips Screen",
                        mobileObjectManager.getTripsPage().getTripsHeaderPlusButton().isDisplayed());

                break;
            case "book":
                //click on footer book link
                mobileObjectManager.getNavigationFooterPage().getFootersBookButton().click();

                //verify Book screen is visible
                ValidationUtil.validateTestStep("Verify Application is navigated to Book Screen",
                        mobileObjectManager.getBookPage().getBookHeaderText().isDisplayed());

                break;
            case "checkin":
                //click on footer Check-In link
                mobileObjectManager.getNavigationFooterPage().getFootersCheckInButton().click();

                //verify Check-In screen is visible

                break;
            case "flightstatus":
                //click on footer Flight Status link
                mobileObjectManager.getNavigationFooterPage().getFootersFlightStatusButton().click();

                //verify Flight Status screen is visible

                break;
            case "more":
                //click on footer More link
                mobileObjectManager.getNavigationFooterPage().getFootersMoreButton().click();

                //verify more screen is visible

                break;
        }

    }

}
