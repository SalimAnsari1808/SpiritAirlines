package com.spirit.testcasesGoldFinger;


import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC373876
//Description: CP - Flight Flex Packaging - Hub Packaging - Validate that Flight Flex is removed after selecting a Car
//Created By : Salim Ansari
//Created On : 04-Nov-2019
//Reviewed By: Kartik Chauhan
//Reviewed On: 04-Nov-2019
//**********************************************************************************************
public class TC373876 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath","RoundTrip","DomesticDomestic","Outside21Days","Adult","Guest", "FlightCar", "NoBags", "NoSeats", "OptionalUI",
                    "Connecting", "BookIt", "FlightFlex", "OptionalUI"})
    public void Validate_that_Flight_Flex_is_removed_after_selecting_a_Car(@Optional("NA") String platform) {

        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373876 under GoldFinger Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "RoundTrip";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LAS";
        final String DEP_DATE 			= "100";
        final String ARR_DATE 			= "101";
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

//STEP--2 Input the following: RT | DOM_DOM | Any Date 3 months in the future | 1 PAX and click SEARCH FLIGHTS
        //Home Page Methods
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//STEP--3 Select any flight and proceed to the Options page with BOOK IT |  No Bags | No Seats
        //Flight Availability Methods
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

//STEP--4 On the Options page, click ADD within the Flight Flex tile
        //Option Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);

        //declare constant used in validation
        final String FIRST_PASSENGER = "1: 0";
        final int    THREE_SECOND    = 3000;
        final int    ONE_SECOND      = 1000;

//STEP--5 Verify that Flight Flex is added to the booking via Dynamic Shopping Cart under Options
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getHeader().getArrowOptionsItineraryImage().click();
        WaitUtil.untilTimeCompleted(1000);

        //verify Flight Flex is added to shopping cart
        ValidationUtil.validateTestStep("Verify that Flight Flex is added to the booking via Dynamic Shopping Cart under Options",
                pageObjectManager.getHeader().getFlightFlexOptionItineraryLabel().isDisplayed());

//STEP--6 Select VIEW for any car that is being offered
        //click on first add car button
        pageObjectManager.getCarPage().getCarcardViewLink().get(0).click();
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 3 sec
//        WaitUtil.untilTimeCompleted(THREE_SECOND);

//STEP--7 Add the Primary Driver and click BOOK CAR
//        TestUtil.selectDropDownUsingValue(pageObjectManager.getCarPage().getCarPopUpPrimaryDriverDropDown(),FIRST_PASSENGER);
//        pageObjectManager.getCarPage().getBookCarButton().get(0).click();

        //wait for 1 sec
//        WaitUtil.untilTimeCompleted(ONE_SECOND);

//STEP--8 Verify that Flight Flex is still purchased.
        //verify Flight Flex is still added after adding Car to booking
        ValidationUtil.validateTestStep("Verify that Flight Flex is still purchased after adding Car to the booking via Dynamic Shopping Cart under Options",
                pageObjectManager.getHeader().getFlightFlexOptionItineraryLabel().isDisplayed());
    }
}
