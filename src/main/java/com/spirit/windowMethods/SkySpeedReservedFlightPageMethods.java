package com.spirit.windowMethods;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.managers.WindowObjectManager;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import com.spirit.windowObjects.SkySpeedReservedFlightPage;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class SkySpeedReservedFlightPageMethods {
    private WindowsDriver               driver;
    private WindowObjectManager         windowObjectManager;
    private ScenarioContext             scenarioContext;
    private SkySpeedReservedFlightPage  skySpeedReservedFlightPage;

    public SkySpeedReservedFlightPageMethods(WindowsDriver driver, WindowObjectManager windowObjectManager, ScenarioContext scenarioContext){
        this.driver                 = driver;
        this.scenarioContext        = scenarioContext;
        this.windowObjectManager    = windowObjectManager;
        skySpeedReservedFlightPage  = windowObjectManager.getSkySpeedReservedFlightPage();
    }


    //**********************************************************************************************
    //Method Name: storeFlightDetails
    //Description: Method is used to store flight information
    //Input Arguments: String->FareType
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 22-Jan-2020
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void storeFlightDetails(String journetType){
        String reservedFlightDetails = "";
        String firstFlightLegDetails = "";
        String secondFlightLegDetails= "";
        WebElement requiredFlight    = null;

        Actions actions = new Actions(driver);

        List<WebElement> flightDetails = skySpeedReservedFlightPage.getReservedFlightListText();

        for(WebElement elment : flightDetails){
            reservedFlightDetails = reservedFlightDetails + elment.getText();
        }

        if(journetType.equalsIgnoreCase("Dep")){
            //switch to first window
            TestUtil.switchToWindow(driver,0);

            requiredFlight = flightDetails.get(0);
        }else if(journetType.equalsIgnoreCase("Ret")){

            requiredFlight = flightDetails.get(1);
        }

        actions.doubleClick(requiredFlight).perform();

        List<WebElement> flightLegDetails = skySpeedReservedFlightPage.getReservedFlightListText();

        for(WebElement elment : flightLegDetails){
            if(!reservedFlightDetails.contains(elment.getText()) && elment.getText().contains("NK-")){
                if(firstFlightLegDetails == ""){
                    firstFlightLegDetails = elment.getText();
                }else{
                    secondFlightLegDetails = elment.getText();
                }
            }
        }

        actions.doubleClick(requiredFlight).perform();

        System.out.println(firstFlightLegDetails);
        System.out.println(secondFlightLegDetails);

    }

    //**********************************************************************************************
    //Method Name: clickNextReservedFlightPage
    //Description: Method is used to store next button
    //Input Arguments: String->FareType
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 22-Jan-2020
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void clickNextReservedFlightPage(){
        //click on Next button
        skySpeedReservedFlightPage.getNextButton().click();

        //wait for page load complete
        WaitUtil.untilSkySpeedPageLoadComplete(driver);

        //click on next button
        ValidationUtil.validateTestStep("Clicked on Next Button on Reserved Flight Page", true);
    }
}
