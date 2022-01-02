package com.spirit.windowMethods;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.enums.Context;
import com.spirit.managers.WindowObjectManager;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import com.spirit.windowObjects.SkySpeedFlightAvailabilityPage;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

public class SkySpeedFlightAvailabilityPageMethods {
    private WindowsDriver                   driver;
    private WindowObjectManager             windowObjectManager;
    private ScenarioContext                 scenarioContext;
    private SkySpeedFlightAvailabilityPage  skySpeedFlightAvailabilityPage;

    public SkySpeedFlightAvailabilityPageMethods(WindowsDriver driver, WindowObjectManager windowObjectManager, ScenarioContext scenarioContext){
        this.driver                     = driver;
        this.scenarioContext            = scenarioContext;
        this.windowObjectManager        = windowObjectManager;
        skySpeedFlightAvailabilityPage  = windowObjectManager.getSkySpeedFlightAvailabilityPage();
    }

    //**********************************************************************************************
    //Method Name: selectFlightByFlightType
    //Description: Method is used to select Flight by flight type like connecting, through, Nonstop, Autoselects
    //Input Arguments: String->tripType, String->flightType
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 22-Jan-2020
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void selectFlightByFlightType(String tripType, String flightType){
        //select flight by flight type
        selectFlight(tripType, flightType, "");

        //flight by type
        ValidationUtil.validateTestStep("Selected Flight by using Flight Type:"+flightType+ " on Flight Availability Page", true);
    }

    //**********************************************************************************************
    //Method Name: selectFlightByFlightNumber
    //Description: Method is used to select Flight by flight number
    //Input Arguments: String->tripType, String->flightNumber
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 22-Jan-2020
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void selectFlightByFlightNumber(String tripType, String flightNumber){
        //select flight by flight number
        selectFlight(tripType, "FlightNumber", flightNumber);

        //flight by number
        ValidationUtil.validateTestStep("Selected Flight by using Flight Number:"+flightNumber+ " on Flight Availability Page", true);
    }

    //**********************************************************************************************
    //Method Name: selectFlight
    //Description: Method is used to select Flight by flight number
    //Input Arguments: String->tripType, String->flightNumber, String->flightType
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 22-Jan-2020
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    private synchronized void selectFlight(String tripType, String flightType, String flightNumber){
        //declare varaible used in method
        String flightDetails        = "";
        String currentFlightType    = "";
        boolean flightFound         = false;
        List<WebElement> flightList = null;

        Actions actions = new Actions(driver);

        //check flight type
        if(tripType.equalsIgnoreCase("Dep")){
            //switch to first window
            TestUtil.switchToWindow(driver,0);

            //get departing flight list
            flightList = skySpeedFlightAvailabilityPage.getSourceFlightList();
        }else if(tripType.equalsIgnoreCase("Ret")){
            //get return flight list
            flightList = skySpeedFlightAvailabilityPage.getDestinationFlightList();
        }

        //loop through all flights
        for(int flightCounter=0;flightCounter<flightList.size();flightCounter++){
            //click on flight details
            flightList.get(flightCounter).click();

            //get flight details
            flightDetails = flightList.get(flightCounter).getText();

            //loop through all flight details and get flight numgert
            for(int flightDetailsCounter=0;flightDetailsCounter<flightDetails.split(" ").length;flightDetailsCounter++){
                if(flightDetails.split(" ")[flightDetailsCounter].toString().contains("NK-")){
                    //store flight number
                    currentFlightType = flightDetails.split(" ")[flightDetailsCounter];

                    //break loop
                    break;
                }
            }

            //select flight by type
            if(flightType.equalsIgnoreCase("AutoSelect")){
                //select any flight
                flightFound = true;

                //break loop
                break;
            }else if(flightType.equalsIgnoreCase("FlightNumber")){
                //check flight number
                if(!currentFlightType.contains(flightNumber)){
                    //continue loop in case flight number not found
                    continue;
                }else{
                    //select flight by number
                    flightFound = true;

                    //break loop
                    break;
                }
            }else if(flightType.equalsIgnoreCase("Connecting")){
                //check for connection flight with two flight numbers
                if(!currentFlightType.contains("/")){
                    continue;
                }else{
                    //connecting flight found
                    flightFound = true;

                    //break loop
                    break;
                }
            }else if(flightType.equalsIgnoreCase("Through") || flightType.equalsIgnoreCase("NonStop")){
                //find flight with single flight number
                if(currentFlightType.contains("/")){
                    continue;
                }else{
                    //check passed flight is through for connecting
                    if(!getSubFlightDetails(tripType, flightType, flightList.get(flightCounter), currentFlightType, flightCounter)){
                        continue;
                    }else{
                        //through or nonstop flight found
                        flightFound = true;

                        //break loop
                        break;
                    }
                }
            }
        }

        //check flight found flag
        if(flightFound){
            TestUtil.menuOptionSkySpeed("SelectFlight");
        }else if(tripType.equalsIgnoreCase("Dep")){
            ValidationUtil.validateTestStep("Unable to find " + flightType + " Flight in Outbound Flight Section on Flight Avaliability Page",false);
        }else if(tripType.equalsIgnoreCase("Ret")){
            ValidationUtil.validateTestStep("Unable to find " + flightType + " Flight in Return Flight Section on Flight Avaliability Page",false);
        }
    }

    //**********************************************************************************************
    //Method Name: getSubFlightDetails
    //Description: Method is used to select through or nonstop flight from flight leg information
    //Input Arguments: String->tripType, String->flightNumber, String->flightType
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 22-Jan-2020
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    private synchronized boolean getSubFlightDetails(String tripType, String flightType, WebElement element, String currentFlightType, int flightCounter){
        Actions actions = new Actions(driver);
        actions.doubleClick(element).perform();

        List<WebElement> subFlightList = null;

        if(tripType.equalsIgnoreCase("Dep")){
            TestUtil.switchToWindow(driver,0);
            subFlightList = skySpeedFlightAvailabilityPage.getSourceFlightList();
        }else if(tripType.equalsIgnoreCase("Ret")){
            subFlightList = skySpeedFlightAvailabilityPage.getDestinationFlightList();
        }

        int flightLegCounter = 0;

        for(int subFlightCounter=flightCounter;subFlightCounter<flightCounter+4;subFlightCounter++){

            if(subFlightList.get(subFlightCounter).getText().contains(currentFlightType)){
                flightLegCounter +=1;
            }
        }

        actions.doubleClick(element).perform();

        if(flightType.equalsIgnoreCase("Through") && flightLegCounter==3){
            return true;
        }else if(flightType.equalsIgnoreCase("NonStop") && flightLegCounter==2){
            return true;
        }else{
            return false;
        }
    }

    //**********************************************************************************************
    //Method Name: clickRightPaneButtons
    //Description: Method is used to click on right pane buttons
    //Input Arguments: String->buttonType
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 23-Jan-2020
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void clickRightPaneButtons(String buttonType){

        switch(buttonType.toLowerCase()){
            case "sort":
                //click on sort button
                skySpeedFlightAvailabilityPage.getSortButton().click();

                break;
            case "reset":
                //click on reset button
                skySpeedFlightAvailabilityPage.getResetButton().click();

                break;
            case "overbook":
                //click on OverBook button
                skySpeedFlightAvailabilityPage.getOverBookButton().click();

                break;
            case "standby":
                //click on Stand By button
                skySpeedFlightAvailabilityPage.getStandByButton().click();

                break;
            case "priority":
                //clicked on priority button
                skySpeedFlightAvailabilityPage.getPriorityButton().click();

                break;
            case "manifest":
                //clicked on Manifest button
                skySpeedFlightAvailabilityPage.getManifestButton().click();

                break;
            case "salechannel":
                //clicked on Sales Channel button
                skySpeedFlightAvailabilityPage.getSaleChannelButton().click();
                break;
        }

        //validate right pane button
        ValidationUtil.validateTestStep("Clicked on " + buttonType + " Button on Right Pane on Flight Avaliability Page",true);
    }

    //**********************************************************************************************
    //Method Name: selectStandByPriorityCodePopUp
    //Description: Method is used to select priority code from drop down
    //Input Arguments: String->buttonType
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 23-Jan-2020
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void selectStandByPriorityCodePopUp(){
        //switch to first window
        TestUtil.switchToWindow(driver,1);

        String codeValue = "";

        if(scenarioContext.getContext(Context.SKYSPEED_FARE_TYPE).toString().equalsIgnoreCase("LowFare")){
            ValidationUtil.validateTestStep("Stand By priority Code is not application for Low Fare",false);
        }

        //
        if(scenarioContext.getContext(Context.SKYSPEED_FARE_TYPE).toString().equalsIgnoreCase("NRSA")){
            //'Select code for NRSA
            codeValue =  "Eligible Travelers & BP w/TM (S4)";
        }else if(scenarioContext.getContext(Context.SKYSPEED_FARE_TYPE).toString().equalsIgnoreCase("NROA")){
            //'Select code for NROA
            codeValue = "OAL Jumpseat/Cabin Seat Riders (S9)";
        }else if(scenarioContext.getContext(Context.SKYSPEED_FARE_TYPE).toString().equalsIgnoreCase("NRLP")){
            //'Select code for NRLP
            codeValue = "Eligible Travelers & BP w/TM (S0)";
        }else if(scenarioContext.getContext(Context.SKYSPEED_FARE_TYPE).toString().equalsIgnoreCase("NRSP")){
            //'Select code for NRSP
            codeValue = "Eligible Travelers & BP w/TM (S1)";
        }

        //select value in standby priority code popup
        skySpeedFlightAvailabilityPage.getStandByPriorityCodePopUpSelectDropDown().sendKeys(codeValue);

        //flight by type
        ValidationUtil.validateTestStep("Selected Stand By priority Code:"+scenarioContext.getContext(Context.SKYSPEED_FARE_TYPE).toString()+ " on Flight Availability Page", true);

        //click o=k button on standby priority code popup
        skySpeedFlightAvailabilityPage.getStandByPriorityCodePopUpOkButton().click();
    }

    //**********************************************************************************************
    //Method Name: flightListSorting
    //Description: Method is used to select priority code from drop down
    //Input Arguments: String->buttonType
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 23-Jan-2020
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void sortingFlightList(String depSortingType, String retSortingType){
        //switch to first window
        //TestUtil.switchToWindow(driver,1);

        String sortingType = "";

        if(depSortingType.equalsIgnoreCase("ServiceType")){
            //value for Service type
            sortingType = "Service Type";
        }else if(depSortingType.equalsIgnoreCase("ShortTravel")){
            //value for Shortest Travel Time
            sortingType ="Shortest Travel Time";
        }else if(depSortingType.equalsIgnoreCase("LowFare")){
            //value for Lowest Fare
            sortingType = "Lowest Fare";
        }else if(depSortingType.equalsIgnoreCase("HighFare")){
            //value for Highest Fare
            sortingType = "Highest Fare";
        }else if(depSortingType.equalsIgnoreCase("EarlyDep")){
            //value for Earliest departure
            sortingType = "Earliest Departure";
        }else if(depSortingType.equalsIgnoreCase("LateDep")){
            //value for Sertype Latest departure
            sortingType =	"Latest Departure";
        }else if(depSortingType.equalsIgnoreCase("EarlyArrive")){
            //value for Earliest arrival
            sortingType = "Earliest Arrival";
        }else if(depSortingType.equalsIgnoreCase("LateArrive")){
            //value for Latest arrival
            sortingType = "Latest Arrival";
        }

        //select dep sorting type
        skySpeedFlightAvailabilityPage.getAvailabilitySortOrderPopupDepartureDropDown().sendKeys(sortingType);

        //flight by type
        ValidationUtil.validateTestStep("Departing Flights are sorted as:"+sortingType+ " on Flight Availability Page", true);

        if(!retSortingType.equalsIgnoreCase("NA")){
            if(retSortingType.equalsIgnoreCase("ServiceType")){
                //value for Service type
                sortingType = "Service Type";
            }else if(depSortingType.equalsIgnoreCase("ShortTravel")){
                //value for Shortest Travel Time
                sortingType ="Shortest Travel Time";
            }else if(depSortingType.equalsIgnoreCase("LowFare")){
                //value for Lowest Fare
                sortingType = "Lowest Fare";
            }else if(depSortingType.equalsIgnoreCase("HighFare")){
                //value for Highest Fare
                sortingType = "Highest Fare";
            }else if(depSortingType.equalsIgnoreCase("EarlyDep")){
                //value for Earliest departure
                sortingType = "Earliest Departure";
            }else if(depSortingType.equalsIgnoreCase("LateDep")){
                //value for Sertype Latest departure
                sortingType =	"Latest Departure";
            }else if(depSortingType.equalsIgnoreCase("EarlyArrive")){
                //value for Earliest arrival
                sortingType = "Earliest Arrival";
            }else if(depSortingType.equalsIgnoreCase("LateArrive")){
                //value for Latest arrival
                sortingType = "Latest Arrival";
            }

            skySpeedFlightAvailabilityPage.getAvailabilitySortOrderPopupReturnDropDown().sendKeys(sortingType);

            //flight by type
            ValidationUtil.validateTestStep("Returning Flights are sorted as:"+sortingType+ " on Flight Availability Page", true);
        }

        //click on sorting button
        skySpeedFlightAvailabilityPage.getAvailabilitySortOrderPopupOkButton().click();
    }

    //**********************************************************************************************
    //Method Name: overBookFlightByNumber
    //Description: Method is used to click on Next button
    //Input Arguments: NA
    //Return: NA
    //Created By : Anthony C
    //Created On : 23-Jan-2020
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void overBookFlightByNumber(String flightNumber){

        List<WebElement> newElements = new ArrayList<>();// new webElements of expanded flight train(includes fares, flight number, and segments)
        WebElement desiredFlight = null; //will store the desired flight
        Actions action = new Actions(driver);

        WaitUtil.untilSkySpeedPageLoadComplete(driver);
        //loop through flight results and double click on parameter DEP_FLIGHT_NUMBER
        for (WebElement flight : windowObjectManager.getSkySpeedFlightAvailabilityPage().getSourceFlightList()) {
            //System.out.println(flight.getText());

            flight.click();

            WaitUtil.untilTimeCompleted(500);

            //if flight result contains the parameter DEP_FLIGHT_NUMBER, double click
            if (flight.getText().contains(flightNumber)) {
                desiredFlight = flight;
                //Double click on the flightOption for this flight number
                action.doubleClick(flight).perform();
                //get expanded flight result information(includes fares, flight number, and segments)
                newElements = flight.findElements(By.tagName("TreeItem"));
                break;
            }
        }

        System.out.println("Tree Items:"+newElements);
        String lid_OfFlight = "";
        //loop though each of the new elements
        for (WebElement information : newElements) {
            //get string that contains "L:" , "S:" , and "C:"
            if (information.getText().contains("L:") && information.getText().contains("S:") && information.getText().contains("C:")) {
                System.out.println(information.getText());
                //get the "L:"
                if (information.getText().contains("L:")) {
                    int startLengthOfLid = information.getText().indexOf("L:");
                    String strStartingOnLid = information.getText().substring(startLengthOfLid);
                    int endLengthOfLid = strStartingOnLid.indexOf(" ") + startLengthOfLid;
                    lid_OfFlight = information.getText().substring(startLengthOfLid, endLengthOfLid);
                    System.out.print(lid_OfFlight + "\t\t");
                }
            }
        }
        //send space bar to the desired flight
        desiredFlight.sendKeys(" ");
        windowObjectManager.getSkySpeedFlightAvailabilityPage().getPassengerInputTextBox().sendKeys(lid_OfFlight);
        //Click okay on the Number of passenger PopUp
        windowObjectManager.getSkySpeedFlightAvailabilityPage().getPassengerOkayButton().click();

        WaitUtil.untilSkySpeedPageLoadComplete(driver);

    }

    //**********************************************************************************************
    //Method Name: clickNextFlightAvailabilityPage
    //Description: Method is used to click on Next button
    //Input Arguments: NA
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 23-Jan-2020
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void clickNextFlightAvailabilityPage(){
        //click on Next button
        skySpeedFlightAvailabilityPage.getNextButton().click();

        //wait for page load complete
        WaitUtil.untilSkySpeedPageLoadComplete(driver);

        //click on next button
        ValidationUtil.validateTestStep("Clicked on Next Button on Flight Availability Page", true);
    }

}
