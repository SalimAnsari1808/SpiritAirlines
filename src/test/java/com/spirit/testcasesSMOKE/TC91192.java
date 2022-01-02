package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//TODO: Bug 26051: CP: BP: Flight Availability FA: User Receives red i block when trying to create a miles booking when logging in either on the homepage, or the FA page
//Test CaseID: TC91192
//Title: 23344: 31497 Flight Availability_CP_BP_Signing up and interacting with insufficient miles modal buttons_View in USD
//Description:
//Created By : Gabriela
//Created On : 10-May-2019
//Reviewed By: Salim Ansari
//Reviewed On: 14-May-2019
//**********************************************************************************************

public class TC91192 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "Within21Days" , "Adult" , "FlightAvailabilityUI" ,
            "Miles" , "FreeSpirit" , "NonStop" })
    public void Flight_Availability_CP_BP_Signing_up_and_interacting_with_insufficient_miles_modal_buttons_View_in_USD(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91192 under SMOKE suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "Oneway";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "ATL";
        final String DEP_DATE 			= "53";
        final String ARR_DATE 			= "NA";
        final String ADULTS 			= "1";
        final String CHILD  			= "0";
        final String INFANT_LAP 		= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Constant Values
        final String FA_URL             = "book/flights";

//--Step 1: Access test environment and start booking a flight
        //open browser
        openBrowser(platform);

        //Starting booking
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);

        //Continue to flight availability
        pageMethodManager.getHomePageMethods().clickSearchButton();

//--Step 2: Verify user lands on Flight Availability page url:
        ValidationUtil.validateTestStep("Validating the user land on Flight Availability Page",
                getDriver().getCurrentUrl(),FA_URL);

//--Step 3: Locate the slider button WEEK/MONTH and DOLLARS/MILES
        ValidationUtil.validateTestStep("Validating Dollar button present on Flight Availability Page",
                pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselDollarsViewSwitchLabel().isDisplayed());

        ValidationUtil.validateTestStep("Validating Miles button present on Flight Availability Page",
                pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselMilesViewSwitchLabel().isDisplayed());

        ValidationUtil.validateTestStep("Validating Months button present on Flight Availability Page",
                pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselMonthViewSwitchLabel().isDisplayed());

        ValidationUtil.validateTestStep("Validating Week button present on Flight Availability Page",
                pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselWeekViewSwitchLabel().isDisplayed());
//--Step 4: Click on Miles
        pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselMilesViewSwitchLabel().click();

//--Step 5: Click the "Sign Up Now" link
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHomePage().getSignUpNowLink().click();

//--Step 6: Create a FS member
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getMemberEnrollmentPageMethods().createNewFSMember();

//--Step 7: Click "Book a Trip" on the sign up confirmation popup
        pageObjectManager.getHomePage().getSpiritLogoImage().click();

//-- Step 8: Start a booking and reach flight availability
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//--Step 9: Click "Miles" then select a flight and click continue
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselMilesViewSwitchLabel().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getDepartingStandardFarePriceText().get(0).click();

        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getFlightAvailabilityPage().getStandardFareButton().click();

//--Step 10: Click on View in USD
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getInsufficientMilesPopUpViewInDollarsButton().click();

        //Validate the user is taken to the right URL
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating the user land on Flight Availability Page",
                getDriver().getCurrentUrl(),FA_URL);
    }
}