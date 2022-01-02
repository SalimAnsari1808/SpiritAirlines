package com.spirit.testcasesProdBAT;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: PRODTC90982
//Description: Flight Flex_CP_BP_RT_Multi Pax_Thrills combo
//Created By:  Kartik chauhan
//Created On:  29-July-2019
//Reviewed By: Salim Ansari
//Reviewed On: 29-July-2019
//**********************************************************************************************
public class PRODTC90982 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups="Production")

    public void Flight_Flex_CP_BP_RT_Multi_Pax_Thrills_combo(@Optional("NA") String platform) {
        /******************************************************************************
         ***************************Navigate to Payment Page***************************
         ******************************************************************************/

        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID PRODTC90982 under PRODUCTION Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "DFW";
        final String DEP_DATE           = "4";
        final String ARR_DATE           = "6";
        final String ADULTS             = "2";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String ARR_Flight         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BundleIt";

        //Seat Page constant values
        final String DEP_SEAT1         = "Standard|Standard|Standard|Standard";

        //Options Page Constant Value
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //Payment Page Constant Value
        final String SELECTED_OPTION    = "FlightFlex|ShortCutBoarding";

        //open browser
        openBrowser(platform);
//STEP--1
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //continue without bags
        pageObjectManager.getBagsPage().getContinueWithStandardBagsContainerContinueButton().click();

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEAT1);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();
//STEP--1
        //Options page methods
        WaitUtil.untilPageLoadComplete(getDriver());
//STEP--2
        //verify flight flex is selected
        pageMethodManager.getOptionsPageMethods().verifySelectedBaseFareOptions(UPGRADE_VALUE);//Bare Fare

        //select checkin  options
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
//STEP--3
        //Select Continue button
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment page
        //verify user reach to Payment page
        ValidationUtil.validateTestStep("Verify Payment page is displaying",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getPaymentHeaderText()));

        pageMethodManager.getPaymentPageMethods().verifyOptionSectionSelectedOptions(SELECTED_OPTION);
    }
}