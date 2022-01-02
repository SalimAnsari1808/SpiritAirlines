package com.spirit.windowMethods;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.enums.Context;
import com.spirit.managers.WindowObjectManager;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import com.spirit.windowObjects.SkySpeedHomePage;
import io.appium.java_client.windows.WindowsDriver;

public class SkySpeedHomePageMethods {
    private WindowsDriver       driver;
    private WindowObjectManager windowObjectManager;
    private ScenarioContext     scenarioContext;
    private SkySpeedHomePage    skySpeedHomePage;

    public SkySpeedHomePageMethods(WindowsDriver driver, WindowObjectManager windowObjectManager, ScenarioContext scenarioContext){
        this.driver              = driver;
        this.scenarioContext     = scenarioContext;
        this.windowObjectManager = windowObjectManager;
        skySpeedHomePage         = windowObjectManager.getSkySpeedHomePage();

    }

    //**********************************************************************************************
    //Method Name: selectFareType
    //Description: Method is used to select Fare Type on Home Page
    //Input Arguments: String->FareType
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 21-Jan-2020
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void selectFareType(String strFareType){
        //declare variable used in method
        String fareTypeValue = "";

        //check fare type to be selected
        if(strFareType.equalsIgnoreCase("LowFare")){
            fareTypeValue = "Lowest Avail Fare (R)";
        }else if(strFareType.equalsIgnoreCase("NRSA")){
            fareTypeValue = "Non-Rev (SA) (E)";
        }else if(strFareType.equalsIgnoreCase("NRSA")){
            fareTypeValue = "Non-Rev (SP) (S)";
        }else if(strFareType.equalsIgnoreCase("InterLine")){
            fareTypeValue = "Interline (SA) (I)";
        }

        //set value in dropdown
        skySpeedHomePage.getFareTypeDropDown().sendKeys(fareTypeValue);

        //store fare type in EV
        scenarioContext.setContext(Context.SKYSPEED_FARE_TYPE,strFareType);

        ValidationUtil.validateTestStep("Fare Type dropdown is set with value:"+fareTypeValue,true);
    }

    //**********************************************************************************************
    //Method Name: selectNumberOfPassengers
    //Description: Method is used to select Passengers on Home Page
    //Input Arguments: String->adultCount, String->childCount, String->infantCount
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 21-Jan-2020
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized  void selectNumberOfPassengers(String adultCount, String childCount, String infantLapCount, String infantSeatCount){

        //check fare typoe is set
        if(!scenarioContext.isContains(Context.SKYSPEED_FARE_TYPE)){
            ValidationUtil.validateTestStep("Please call methods selectFareType first to set value of FareType",false);
        }

        switch(scenarioContext.getContext(Context.SKYSPEED_FARE_TYPE).toString().toLowerCase()){
            case "lowfare":
                for(int paxCounter=0;paxCounter<skySpeedHomePage.getPaxTypeColumnText().size();paxCounter++){
                    if(skySpeedHomePage.getPaxTypeColumnText().get(paxCounter).getText().equalsIgnoreCase("ADT")){
                        skySpeedHomePage.getPaxCountColumnText().get(paxCounter).click();
                        skySpeedHomePage.getPaxCountColumnText().get(paxCounter).sendKeys(adultCount);

                     //check
                    }else if(windowObjectManager.getSkySpeedHomePage().getPaxTypeColumnText().get(paxCounter).getText().equalsIgnoreCase("CHD")){
                        skySpeedHomePage.getPaxCountColumnText().get(paxCounter).click();
                        skySpeedHomePage.getPaxCountColumnText().get(paxCounter).sendKeys(childCount);
                    }else if(windowObjectManager.getSkySpeedHomePage().getPaxTypeColumnText().get(paxCounter).getText().equalsIgnoreCase("INF")){
                        skySpeedHomePage.getPaxCountColumnText().get(paxCounter).click();
                        skySpeedHomePage.getPaxCountColumnText().get(paxCounter).sendKeys(infantSeatCount);

                        //break loop after infant row
                        break;
                    }
                }

                break;
            //"NRSA","NRSP","NROA","NRLP"
            case "nrsa":
            case "nrsp":
            case "nroa":
            case "nrlp":

                ValidationUtil.validateTestStep("Passenger methods is not implemented", false);

                //Do nothing, Update in future
                break;
        }

        //store in global variables
        scenarioContext.setContext(Context.HOMEPAGE_ADULT_COUNT,adultCount);
        scenarioContext.setContext(Context.HOMEPAGE_CHILD_COUNT,childCount);
        scenarioContext.setContext(Context.HOMEPAGE_INFANTLAP_COUNT,infantLapCount);
        scenarioContext.setContext(Context.HOMEPAGE_INFANTSEAT_COUNT,infantSeatCount);

        //validate passenger information
        ValidationUtil.validateTestStep("Passengers are selected on Home Page", true);
    }

    //**********************************************************************************************
    //Method Name: selectFromToAirports
    //Description: Method is used to select Passengers on Home Page
    //Input Arguments: String->tripType, String->sourceAirports, String->destinationAirports
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 21-Jan-2020
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void selectFromToAirports(String tripType, String sourceAirports, String destinationAirports){

        //select departing airport
        skySpeedHomePage.getFromCityListDropDown().sendKeys(sourceAirports.split("\\|")[0]);

        //select returning airport
        skySpeedHomePage.getToCityListDropDown().sendKeys(destinationAirports.split("\\|")[0]);

        //stotre trip type
        scenarioContext.setContext(Context.HOMEPAGE_TRIP_TYPE,tripType);
        scenarioContext.setContext(Context.HOMEPAGE_DEP_AIRPORT,skySpeedHomePage.getFromCityListDropDown().getText());
        scenarioContext.setContext(Context.HOMEPAGE_ARR_AIRPORT,skySpeedHomePage.getToCityListDropDown().getText());

        if(tripType.equalsIgnoreCase("MultiCity")){
            //click on open jaws
            skySpeedHomePage.getOpenJawCheckBox().click();

            skySpeedHomePage.getReturnFromTextBox().sendKeys(sourceAirports.split("\\|")[1]);

            skySpeedHomePage.getReturnToTextBox().sendKeys(destinationAirports.split("\\|")[1]);

        }

        //dep airport
        ValidationUtil.validateTestStep("Departure Airport " + skySpeedHomePage.getFromCityListDropDown().getText() + " is selected on Home Page", true);

        //arr airport
        ValidationUtil.validateTestStep("Arrival Airport " + skySpeedHomePage.getToCityListDropDown().getText() + " is selected on Home Page", true);
    }

    //**********************************************************************************************
    //Method Name: selectJourneyDate
    //Description: Method is used to select journey dates on Home Page
    //Input Arguments: String->depDate, String->retDate
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 21-Jan-2020
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void selectJourneyDate(String depDate, String retDate){

        String dateValue = TestUtil.getStringDateFormat(depDate, "dd MMM yyyy");
        //System.out.println(skySpeedHomePage.getFromDateTextBox().getText());
        while(true){
            if(skySpeedHomePage.getFromDateTextBox().getText().equalsIgnoreCase(dateValue)){
                break;
            }else{
                skySpeedHomePage.getFromDateDownButton().click();
            }
        }

        //store departure date
        scenarioContext.setContext(Context.HOMEPAGE_DEP_DATE,depDate);

        //validate dep date
        ValidationUtil.validateTestStep("Departure Date "+ dateValue + " is Selected on Home Page",true);

        if(!scenarioContext.getContext(Context.HOMEPAGE_TRIP_TYPE).toString().equalsIgnoreCase("OneWay")){

            dateValue = TestUtil.getStringDateFormat(retDate, "dd MMM yyyy");

            //click on return date box
            skySpeedHomePage.getToDateTextBox().click();

            //set return date
            while(true){
                if(skySpeedHomePage.getToDateTextBox().getText().equalsIgnoreCase(dateValue)){
                    break;
                }else{
                    skySpeedHomePage.getToDateDownButton().click();
                }
            }

            //store return date
            scenarioContext.setContext(Context.HOMEPAGE_ARR_DATE,retDate);

            //validate dep date
            ValidationUtil.validateTestStep("Arrival Date "+ dateValue + " is Selected on Home Page",true);
        }
    }

    //**********************************************************************************************
    //Method Name: clickNextHomePage
    //Description: Method is used to click on Next button on Home Page
    //Input Arguments: NA
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 21-Jan-2020
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void clickNextHomePage(){
        //click on Next button
        skySpeedHomePage.getNextButton().click();

        //wait for page load complete
        WaitUtil.untilSkySpeedPageLoadComplete(driver);

        //click on next button
        ValidationUtil.validateTestStep("Clicked on Next Button on Home Page", true);
    }
}
