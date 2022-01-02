package com.spirit.windowMethods;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.managers.WindowObjectManager;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.windowObjects.SkySpeedCommentsPage;
import io.appium.java_client.windows.WindowsDriver;

public class SkySpeedCommentsPageMethods {
    private WindowsDriver           driver;
    private WindowObjectManager     windowObjectManager;
    private ScenarioContext         scenarioContext;
    private SkySpeedCommentsPage    skySpeedCommentsPage;

    public SkySpeedCommentsPageMethods(WindowsDriver driver, WindowObjectManager windowObjectManager, ScenarioContext scenarioContext){
        this.driver                 = driver;
        this.scenarioContext        = scenarioContext;
        this.windowObjectManager    = windowObjectManager;
        skySpeedCommentsPage       = windowObjectManager.getSkySpeedCommentsPage();
    }


    //**********************************************************************************************
    //Method Name: fillMandatoryComment
    //Description: Method is used to fill Mandatory Comments on Mandatory Comment page
    //Input Arguments: NA
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 28-Jan-2020
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void fillMandatoryComment(){
        skySpeedCommentsPage.getMandatoryCommentsTextBox().sendKeys("Booking is created by Automation at:"+TestUtil.currentDateTimeString());

        ValidationUtil.validateTestStep("Mandatory Comment is filled on Mandatory Comment Page", true);
    }

    //**********************************************************************************************
    //Method Name: clickNextMandatoryCommentPage
    //Description: Method is used to click next button on Mandatory Comment page
    //Input Arguments: NA
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 28-Jan-2020
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void clickNextMandatoryCommentPage(){
        //click on Next button
        skySpeedCommentsPage.getCommentNextButton().click();

        //click on next button
        ValidationUtil.validateTestStep("Clicked on Next Button on Mandatory Comment Page", true);
    }

    //**********************************************************************************************
    //Method Name: fillAddComment
    //Description: Method is used to fill Add Comments on Comment page
    //Input Arguments: NA
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 28-Jan-2020
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void fillAddComment(){
        //click on add button
        skySpeedCommentsPage.getCommentAddCommentsButton().click();

        //enter comments
        skySpeedCommentsPage.getCommentInputTextBox().sendKeys("Booking is created by Automation at:"+TestUtil.currentDateTimeString());

        ValidationUtil.validateTestStep("Comment is filled on Comment Page", true);

        //click on save button
        skySpeedCommentsPage.getCommentSaveCommentButton().click();
    }


    //**********************************************************************************************
    //Method Name: clickNextCommentPage
    //Description: Method is used to click next button on Comment page
    //Input Arguments: NA
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 28-Jan-2020
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void clickNextCommentPage(){
        //click on Next button
        skySpeedCommentsPage.getCommentNextButton().click();

        //click on next button
        ValidationUtil.validateTestStep("Clicked on Next Button on Comment Page", true);
    }

}
