package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC90959
//Description:  Flight Flex_CP_BP_RT_Multi Pax_only Departure within 24 hrs
//Created By:   Salim Ansari
//Created On:   24-July-2019
//Reviewed By:  Kartik Chauhan
//Reviewed On:  24-July-2019
//**********************************************************************************************
public class TC90959 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "NonStop" , "BookIt" , "NoBags" , "NoSeats" ,"FlightFlex","CheckInOptions", "PaymentUI"})
    public void Flight_Flex_CP_BP_RT_Multi_Pax_only_Departure_within_24_hrs(@Optional("NA") String platform) {
        /******************************************************************************
         ***************************Navigate to Purchase Page**************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90959 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "0";
        final String ARR_DATE           = "5";
        final String ADULTS             = "4";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String ARR_Flight         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page Constant Value
        final String OPTION_VALUE       = "FlightFlex|CheckInOption:DecideLater";

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

        //Flight Availability page methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags page
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options page
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /******************************************************************************
         ***************************Validation on purchase Page************************
         ******************************************************************************/
        final String PAYMENT_URL        = "book/payment";
        final String SELECTED_OPTION    = "FlightFlex";

        ValidationUtil.validateTestStep("Verify User Navigated to Payment Page",
                getDriver().getCurrentUrl(), PAYMENT_URL);

        pageMethodManager.getPaymentPageMethods().verifyOptionSectionSelectedOptions(SELECTED_OPTION);
    }
}