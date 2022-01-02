package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.List;

// **********************************************************************************************
// TestCase ID: TC90791
// TestCase : Flight Availability_CP_BP_Flight Only_Stops link populating the Flight Information popup_Wireframe and Functionality
// Created By : Kartik Chauhan
// Created On : 26-July-2019
// Reviewed By: Salim Ansari
// Reviewed On: 29-July-2019
// **********************************************************************************************
public class TC90791 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "FlightAvailabilityUI" })
    public void Flight_Availability_CP_BP_Flight_Only_Stops_link_populating_the_Flight_Information_popup_Wireframe_and_Functionality(@Optional("NA") String platform) {

        /******************************************************************************
         *************************Navigate to optional service Page********************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90791 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "3";
        final String ARR_DATE           = "NA";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String NONSTOP            = "NonStop";
        final String SINGLE_STOP        = "1 Stop";
        final String TWO_STOP           = "2 Stops";
        final String FOUR_STOP          = "4 Stops";


        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        /****************************************************************************
         * *************Flight Availability Page Methods*****************************
         ****************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());

        //create list array
        List<WebElement> allstopList = pageObjectManager.getFlightAvailabilityPage().getDepartingNumebrOfStopsButton();

        //create loop
        for (int counter = 0; counter < allstopList.size(); counter++) {
//STEP--4
            //for Non-Stop flight
            if (pageObjectManager.getFlightAvailabilityPage().getDepartingNumebrOfStopsButton().get(counter).getText().contains(NONSTOP)) {
                //make click on Number of Step
                pageObjectManager.getFlightAvailabilityPage().getDepartingNumebrOfStopsButton().get(counter).click();

                verifyFlightInformationPopup(0);

//STEP--5
                //Close pop-up
                pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();

                //Validate All non Stop info
                ValidationUtil.validateTestStep("All NonStop Info on pop-up is displaying correct..",true);
            }
//STEP--6
            //for 1-Stop flight
            else if (pageObjectManager.getFlightAvailabilityPage().getDepartingNumebrOfStopsButton().get(counter).getText().contains(SINGLE_STOP)) {
                //make click on Number of Step
                pageObjectManager.getFlightAvailabilityPage().getDepartingNumebrOfStopsButton().get(counter).click();

                //loop through all blocks
                for(int flightLegCounter=0;flightLegCounter<=1;flightLegCounter++){
                    verifyFlightInformationPopup(flightLegCounter);

                    if(flightLegCounter<1){
                        //Validate Layover time
                        ValidationUtil.validateTestStep("Verify Layover Time text is correctly displayed on Flight Information Popup",
                                pageObjectManager.getFlightAvailabilityPage().getStopsPopUpLayoverTimeText().get(flightLegCounter).getText().length()>5);

                    }
                }
//STEP--7
                //Close pop-up
                pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();

                //Validate All 1 Stop info
                ValidationUtil.validateTestStep("All 1 Stop Info on pop-up is displaying correct..",true);

            }
//STEP--8
            //for 2-Stop flight
            else if (pageObjectManager.getFlightAvailabilityPage().getDepartingNumebrOfStopsButton().get(counter).getText().contains(TWO_STOP)) {

                //make click on Number of Step
                pageObjectManager.getFlightAvailabilityPage().getDepartingNumebrOfStopsButton().get(counter).click();

                //loop through all blocks
                for(int flightLegCounter=0;flightLegCounter<=2;flightLegCounter++){
                    verifyFlightInformationPopup(flightLegCounter);

                    if(flightLegCounter<2){
                        //Validate Layover time
                        ValidationUtil.validateTestStep("Verify Layover Time text is correctly displayed on Flight Information Popup",
                                pageObjectManager.getFlightAvailabilityPage().getStopsPopUpLayoverTimeText().get(flightLegCounter).getText().length()>5);

                    }
                }

                //validate Lay over Time
                ValidationUtil.validateTestStep("Flight LayOver time is displaying",
                        pageObjectManager.getFlightAvailabilityPage().getStopsPopUpLayoverTimeText().get(0).isDisplayed());
//STEP--9
                //Close pop-up
                pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();

                //Validate All 2 Stop info
                ValidationUtil.validateTestStep("All 2 Stop Info on pop-up is displaying correct..",true);
            }

            else if(pageObjectManager.getFlightAvailabilityPage().getDepartingNumebrOfStopsButton().get(counter).getText().contains(FOUR_STOP)){
                //Validate All 2 Stop info
                ValidationUtil.validateTestStep("4 Stops is Flight Info on pop-up is cannot be displayed",false);
            }
        }
    }

    private void verifyFlightInformationPopup(int flightBlockCount){
        //declare constant used in method
        final String FLIGHT_INFO_HEADER     = "Flight Information";
        final String FLIGHT_NUMBER          = "Flight NK";

        //declare variable use din method
        String flightNumber;
        int flightNumberValue;

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        if(flightBlockCount==0){
            //validate for Flight Info header
            ValidationUtil.validateTestStep("Flight Information header.. is displaying",
                    pageObjectManager.getFlightAvailabilityPage().getStopsPopUpHeaderText().getText(), FLIGHT_INFO_HEADER);

            //Validate Flight Number
            try{
                flightNumber = pageObjectManager.getFlightAvailabilityPage().getStopsPopUpFlightsNumberText().get(flightBlockCount).getText().replace(FLIGHT_NUMBER,"").trim();

                flightNumberValue = Integer.parseInt(flightNumber);

                //Validate flight number
                ValidationUtil.validateTestStep("Flight Number.. is displaying correctly on Flight Information Popup", true);
            }catch(Exception e){
                //Validate flight number
                ValidationUtil.validateTestStep("Flight Number.. is displaying correctly on Flight Information Popup", false);
            }

            //Validate Aircraft Type
            ValidationUtil.validateTestStep("Verify Aircraft Number is displayed on Flight Information Popup",
                    pageObjectManager.getFlightAvailabilityPage().getStopsPopUpAircraftTypeText().get(flightBlockCount).getText().length()>=3);
        }

//Departure Details
        //Validate Departure label
        ValidationUtil.validateTestStep("Verify Departure Label is correctly displayed on Flight Information Popup",
                TestUtil.verifyElementDisplayed(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpDepartureLabel().get(flightBlockCount)));

        //Validation Departure Airport
        ValidationUtil.validateTestStep("Verify Departure Airport text is correctly displayed on Flight Information Popup",
                pageObjectManager.getFlightAvailabilityPage().getStopsPopUpDepartureAirportsText().get(flightBlockCount).getText().length()>5);

        //Validate Departure Time
        ValidationUtil.validateTestStep("Verify Departure Time text is correctly displayed on Flight Information Popup",
                pageObjectManager.getFlightAvailabilityPage().getStopsPopUpDepartureTimeText().get(flightBlockCount).getText().length()>5);

//Arrival Details
        //Validate Arrival label
        ValidationUtil.validateTestStep("Verify Arrival Label is correctly displayed on Flight Information Popup",
                TestUtil.verifyElementDisplayed(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpArrivalLabel().get(flightBlockCount)));

        //Validation Arrival Airport
        ValidationUtil.validateTestStep("Verify Arrival Airport text is correctly displayed on Flight Information Popup",
                pageObjectManager.getFlightAvailabilityPage().getStopsPopUpArrivalAirportsText().get(flightBlockCount).getText().length()>5);

        //Validate Arrival Time
        ValidationUtil.validateTestStep("Verify Arrival Time text is correctly displayed on Flight Information Popup",
                pageObjectManager.getFlightAvailabilityPage().getStopsPopUpArrivalTimeText().get(flightBlockCount).getText().length()>5);

//Flight Duration
        //Validate Flight Duration label
        ValidationUtil.validateTestStep("Verify Flight Duration Label is correctly displayed on Flight Information Popup",
                TestUtil.verifyElementDisplayed(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpDurationLabel().get(flightBlockCount)));

        //Validation Flight Duration
        ValidationUtil.validateTestStep("Verify Flight Duration Value text is correctly displayed on Flight Information Popup",
                pageObjectManager.getFlightAvailabilityPage().getStopsPopUpFlightDurationTimeText().get(flightBlockCount).getText().length()>5);

//Flight On-Time
        //Validate Flight On-Time label
        ValidationUtil.validateTestStep("Verify Flight On-Time Label is correctly displayed on Flight Information Popup",
                TestUtil.verifyElementDisplayed(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpOnTimeLabel().get(flightBlockCount)));

        //Validation Flight On-Time Value
        ValidationUtil.validateTestStep("Verify Flight On-Time Value text is correctly displayed on Flight Information Popup",
                pageObjectManager.getFlightAvailabilityPage().getStopsPopUpOnTimeText().get(flightBlockCount).getText().length()>5);

//Flight Late
        //Validate Flight Late label
        ValidationUtil.validateTestStep("Verify Flight Late Label is correctly displayed on Flight Information Popup",
                TestUtil.verifyElementDisplayed(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpLateLabel().get(flightBlockCount)));

        //Validation Flight Late Value
        ValidationUtil.validateTestStep("Verify Flight Late Value text is correctly displayed on Flight Information Popup",
                pageObjectManager.getFlightAvailabilityPage().getStopsPopUpLateTimeText().get(flightBlockCount).getText().length()>5);
    }
}