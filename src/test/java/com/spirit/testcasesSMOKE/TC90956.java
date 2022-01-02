package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.openqa.selenium.By;
import org.testng.annotations.*;

//@Listeners(com.spirit.testNG.Listener.class)
//**********************************************************************************************
//Test Case ID: TC90956
//Description:  Flight Flex_CP_BP_NEG_OW_within 24 hrs
//Created By : Sunny Sok
//Created On : 05-Apr-2019
//Reviewed By: Salim Ansari
//Reviewed On: 10-Apr-2019
//**********************************************************************************************
public class TC90956 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" ,"Connecting", "BookIt" ,"NoBags","NoSeats" , "OptionsUI"})
    public void Flight_Flex_CP_BP_NEG_OW_within_24_hrs(@Optional("NA") String platform) {
        /******************************************************************************
         *******************************Navigate to Home Page**************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90956 under SMOKE Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LGA";
        final String DEP_DATE           = "0";
        final String ARR_DATE           = "NA";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "Connecting";
        final String FARE_TYPE          = "Standard";
        final String UPGRADEVALUE       = "BookIt";

        //STEP--1
        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADEVALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
        WaitUtil.untilPageLoadComplete(getDriver());

        /******************************************************************************
         ************************Validation Options Page********************************
         ******************************************************************************/
        //declare constant used in Validation
        final String OPTIONS_URL    = "book/options";
        final String NOT_AVAILABLE  = "Not Available";

        //STEP--2
        //wait till url appear on web
        WaitUtil.untilPageURLVisible(getDriver(), OPTIONS_URL);

        //STEP--3
        //validate flight flex option contains not available
        ValidationUtil.validateTestStep("User verify Flight Flex option are Not Available",
                TestUtil.verifyElementDisplayed(getDriver(), By.xpath(pageObjectManager.getOptionsPage().xpath_FlightFlexCardNotAvailableText)) &&
                        pageObjectManager.getOptionsPage().getFlightFlexCardNotAvailableText().getText().contains(NOT_AVAILABLE));

    }
}
