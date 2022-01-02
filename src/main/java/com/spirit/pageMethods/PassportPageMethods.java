package com.spirit.pageMethods;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.dataType.PassengerInfoData;
import com.spirit.enums.Context;
import com.spirit.managers.FileReaderManager;
import com.spirit.managers.PageObjectManager;
import com.spirit.pageObjects.PassportPage;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.WebDriver;

public class PassportPageMethods {

    private WebDriver driver;
    private ScenarioContext scenarioContext;
    private PassportPage passportPage;


    public PassportPageMethods(WebDriver driver, PageObjectManager pageObjectManager, ScenarioContext scenarioContext) {
        this.driver				= driver;
        this.scenarioContext  	= scenarioContext;
        passportPage	        = pageObjectManager.getPassportPage();
    }

    //**********************************************************************************************
    // Method : fillPassportInformation
    // Description: Method is used to fill passport information for all passenger
    // Input Arguments: NA
    // Return: NA
    // Created By : Salim Ansari
    // Created On : 13-Apr-2019
    // Reviewed By: Kartik Chauhan
    // Reviewed On: 13-Apr-2019
    //**********************************************************************************************
    public synchronized void fillPassportInformation() {
        //declare variable used in method
        int totalPaxCount = Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_ADULT_COUNT).toString())+Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_CHILD_COUNT).toString())+Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_INFANTSEAT_COUNT).toString());

        //wait for page load is complete
        WaitUtil.untilPageLoadComplete(driver);

        //lopp through all passengers
        for(int paxCounter=0;paxCounter<=totalPaxCount-1;paxCounter++){
            //get passenger info data
            PassengerInfoData  passengerInfoData= FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("Passenger" + (paxCounter+1));

            //fill passport number
            passportPage.getPassengerPassportNumberTextBox().get(paxCounter).sendKeys(passengerInfoData.paasportNumber);

            //fill expiration date
            passportPage.getPassengerExpirationDateTextBox().get(paxCounter).sendKeys(passengerInfoData.expitaionDate);
        }

        //click on save button
        passportPage.getSaveChangesButton().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //click on continue without middle name
        passportPage.getContinueWithoutMiddleNameButton().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(1200);

        //wait for page load is complete
        WaitUtil.untilPageLoadComplete(driver);
    }
}
