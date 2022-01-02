package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


//**********************************************************************************************
//Test Case ID: TC373877
//Description: CP - Flight Flex Packaging - Hub Packaging - Validate that Flight Flex is removed after selecting a Hotel
//Created By : Salim Ansari
//Created On : 04-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC373877  extends BaseClass {


    @Parameters({"platform"})
    @Test(groups = {"BookPath","OneWay","DomesticDomestic","WithIn7Days","Adult","Guest","HomeUI","PromoCode","ActiveBug"})
    public void Validate_that_Flight_Flex_is_removed_after_selecting_a_Hotel(@Optional("NA") String platform) {

        /******************************************************************************
         *******************************Navigate to Option Page************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373877 under GoldFinger Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String FS_LOGIN           = "FSEmail";
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "RoundTrip";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LAS";
        final String DEP_DATE 			= "30";
        final String ARR_DATE 			= "33";
        final String ADULTS 			= "1";
        final String CHILD   			= "0";
        final String INFANT_LAP 		= "0";
        final String INFANT_SEAT 		= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 	    = "Connecting";
        final String ARR_Flight 		= "Connecting";
        final String FARE_TYPE 	    	= "Standard";
        final String UPGRADE_VALUE 	    = "BookIt";

        //Option Page Constant value
        final String OPTION_VALUE       = "FlightFlex";


//STEP--1 Land on current test environment.
        //open browser
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();

//STEP--2 Sign into a FS account
        pageMethodManager.getHomePageMethods().loginToApplication(FS_LOGIN);

//STEP--3 Input the following: RT | DOM_DOM | Any Date 3 months in the future | 2 PAX and click SEARCH FLIGHTS
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//STEP--4 Select any flight and proceed to the Options page with BOOK IT |  No Bags | No Seats
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats page methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//STEP--5 On the Options page, click ADD within the Flight Flex tile
        //Option Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);

//STEP--6 Verify that Flight Flex is added to the booking via Dynamic Shopping Cart under Options
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getHeader().getArrowOptionsItineraryImage().click();
        WaitUtil.untilTimeCompleted(1000);

        //verify Flight Flex is added to blloing
        ValidationUtil.validateTestStep("Verify that Flight Flex is added to the booking via Dynamic Shopping Cart under Options",
                pageObjectManager.getHeader().getFlightFlexOptionItineraryLabel().isDisplayed());

//STEP--7 Select VIEW for any hotel that is being offered
        pageObjectManager.getHotelPage().getViewAllHotelsButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

//STEP--8 Click the ROOMS FROM $XXX.XX button
//STEP--9 Click SELECT ROOM for any room being offered
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("NA","NA");

//STEP--10 Verify that Flight Flex is still purchased.
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getHeader().getArrowOptionsItineraryImage().click();
        WaitUtil.untilTimeCompleted(1000);

        //verify Flight Flex is still added after adding Hotel to booking
        ValidationUtil.validateTestStep("Verify that Flight Flex is still purchased after adding Hotel to the booking via Dynamic Shopping Cart under Options",
                pageObjectManager.getHeader().getFlightFlexOptionItineraryLabel().isDisplayed());

    }
}
