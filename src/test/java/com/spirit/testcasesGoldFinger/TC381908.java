package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC381908
//Description: Task 27830: TC381908- 005 - CP - Flight Flex Packaging Verbiage - Flight + Hotel - Validate verbiage for Flight Flex on the Thrills Pop-Up
//Created By: Gabriela
//Created On: 21-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC381908 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "FlightHotel","Cars", "Outside21Days", "Adult", "Guest","FlightAvailabilityUI"})
    public void CP_Flight_Flex_Packaging_Verbiage_Flight_Hotel_Validate_verbiage_for_Flight_Flex_on_the_Thrills_Pop_Up(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC381908 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "132";
        final String ARR_DATE           = "133";
        final String ADULT              = "2";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Save and Upgrade Pop Up Constant Values
        final String  FLIGHT_FLEX       = "Flight Flex - Modify your flight once for free!";

//- Step 7: Land on current test environment.
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 1: Click on Vacation tab and select Flight + Hotel
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);

//- Step 2: Input the following: DOM_DOM | Any Date 3 months in the future | 2 PAX | 1 Room and click SEARCH VACATION
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 3: Select any hotel and proceed to options/cars page
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("NA" , "NA");

//- Step 4: Book any car by clicking BOOK CAR
        pageMethodManager.getCarPageMethods().selectCarOnCarPage("NA","NA");

//- Step 5: Click CONTINUE at the bottom for the cars page
        //Invalid Step

//- Step 6: Verify the Bundle It verbiage contains:
        boolean Flag = false;

        for(WebElement bundleItBenfits : pageObjectManager.getFlightAvailabilityPage().getBundleTileBenefitsList()){
            if(bundleItBenfits.isDisplayed()){
                if(bundleItBenfits.getText().equalsIgnoreCase("Flight Flex - Modify your flight once for free! (fare difference may apply)")){
                    Flag = true;
                }
            }
        }

        ValidationUtil.validateTestStep("Verify the Flight Flex - Modify your flight once for free! (fare difference may apply) verbiage in the Bundle it section of the pop up on Flight Availability Page", Flag);

    }
}