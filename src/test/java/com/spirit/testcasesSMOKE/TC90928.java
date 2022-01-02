package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.*;
import org.testng.annotations.Optional;

// ***************************************************************************************************
// TestCase   : CP_BP_Payment Page_Travel Guard_9DFC Saved Credit Card_Purchase TG
// Description: Validate that 9FC member is able to see saved cards after selecting no to travel guard
// Created By : Alex Rodriguez
// Created On : 10-April-2019
// Reviewed By: Salim Ansari
// Reviewed On: 18-April-2019
// ****************************************************************************************************

public class TC90928 extends BaseClass {

    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "Within21Days" , "Adult" , "NineDFC" , "NonStop" , "BookIt" ,
            "NoBags" , "NoSeats" , "CheckInOptions" , "TravelInsuranceBlock", "MasterCard","ConfirmationUI", "PaymentUI"})
    public void CP_BP_Payment_Page_Travel_Guard_9DFC_Saved_Credit_Card_Purchase_TG(@Optional("NA") String platform) {
        //*****************************Navigate to Payment Page**************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90928 under SMOKE Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			    = "English";
        final String LOGIN_ACCOUNT 	        = "NineDFCEmail";
        final String JOURNEY_TYPE 		    = "Flight";
        final String TRIP_TYPE 			    = "OneWay";
        final String DEP_AIRPORTS 		    = "AllLocation";
        final String DEP_AIRPORT_CODE       = "FLL";
        final String ARR_AIRPORTS 		    = "AllLocation";
        final String ARR_AIRPORT_CODE       = "LGA";
        final String DEP_DATE 			    = "53";
        final String ARR_DATE 			    = "NA";
        final String ADULTS				    = "1";
        final String CHILDREN			    = "0";
        final String INFANT_LAP			    = "0";
        final String INFANT_SEAT		    = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		    = "nonstop";
        final String FARE_TYPE			    = "Member";
        final String UPGRADE_VALUE	  	    = "BookIt";

        //Option Page Constant Values
        final String OPTIONS_VALUE		    = "CheckInOption:MobileFree";

        final String TRAVEL_GUARD_NO        = "NotRequired";
        final String TRAVEL_GUARD_YES		= "Required";

        //open browser
        openBrowser( platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seat Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Option method
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //**************************Validation on Payment Page***************************/
        //Payment Page Method
//Step 3 & 4
        //Payment page Constant values
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD_NO);

        ValidationUtil.validateTestStep("User verifies saved card option is visible after selecting no to travel guard",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getSelectCardDropDown()));

//Step 5,6
        WaitUtil.untilPageLoadComplete(getDriver(), (long) 1500);
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD_YES);

        ValidationUtil.validateTestStep("User verifies saved card option is suppressed after selecting yes to travel guard",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getSelectCardDropDown()));


//Step 7
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails("MasterCard");
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        //Close Rocket popup and Navigate to Account profile
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //Verify Travel Insurance Policy is present on Confirmation page
//        pageMethodManager.getConfirmationPageMethods().verifyTravelInsuranceSection();

    }
}
