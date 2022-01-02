package com.spirit.mobileMethods;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.managers.MobileObjectManager;
import com.spirit.mobileObjects.MyProfilePage;
import com.spirit.mobileObjects.SignInPage;
import com.spirit.mobileObjects.TripsPage;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;

public class MyProfilePageMethods {

    private AppiumDriver driver;
    private ScenarioContext scenarioContext;
    private MyProfilePage myProfilePage;
    private SignInPage signInPage;
    private TripsPage tripsPage;

    public MyProfilePageMethods(AppiumDriver driver, MobileObjectManager mobileObjectManager, ScenarioContext scenarioContext) {
        this.driver=driver;
        this.scenarioContext = scenarioContext;
        myProfilePage = mobileObjectManager.getMyProfilePage();
        signInPage = mobileObjectManager.getSignInPage();
        tripsPage = mobileObjectManager.getTripsPage();

    }

    public void logoutFromNativeApplication(){
        //wait for signin is visible
        WaitUtil.untilElementIsClickable(driver,signInPage.getLoggedUserImage(),(long)20);

        //click on logged user image
        //JSExecuteUtil.clickOnElement(driver,signInPage.getLoggedUserImage());
        System.out.println("Going to click now");
        signInPage.getLoggedUserImage().click();

        //click on sign out button
        myProfilePage.getSignOutButton().click();

        //wait for popup to appear on screen
        WaitUtil.untilElementIsClickable(driver,myProfilePage.getSignOutPopYesButton());

        //click on yes button on log out popup
        myProfilePage.getSignOutPopYesButton().click();

        //wait for user logged out from application
        WaitUtil.untilElementIsClickable(driver,tripsPage.getTripsHeaderSignInButton());

        ValidationUtil.validateTestStep("User Logged out from application",tripsPage.getTripsHeaderSignInButton().isDisplayed());


    }

}
