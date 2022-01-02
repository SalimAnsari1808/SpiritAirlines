package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

// ***************************************************************************************************
//Test Case ID: TC91416
// TestCase   : CP_BP_Payment Page_FS_multiple saved credit cards
// Description: Validate that FS member is able to see saved cards after selecting no to travel guard
// Created By : Sunny Sok
// Created On : 13-May-2019
// Reviewed By: Salim Ansari
// Reviewed On: 14-May-2019
// ****************************************************************************************************

public class TC91416 extends BaseClass {

    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "Within21Days" , "Adult" , "FreeSpirit" , "NonStop" , "BookIt" ,
            "NoBags" , "NoSeats" , "CheckInOptions" , "PaymentUI"})
    public void CP_BP_Payment_Page_FS_multiple_saved_credit_cards(@Optional("NA") String platform) {
        /******************************************************************************
         ****************************Navigate to Payment Page**************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91416 under SMOKE Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			    = "English";
        final String LOGIN_ACCOUNT 	        = "FSSavedCards";
        final String JOURNEY_TYPE 		    = "Flight";
        final String TRIP_TYPE 			    = "OneWay";
        final String DEP_AIRPORTS 		    = "AllLocation";
        final String DEP_AIRPORT_CODE       = "FLL";
        final String ARR_AIRPORTS 		    = "AllLocation";
        final String ARR_AIRPORT_CODE       = "LGA";
        final String DEP_DATE 			    = "10";
        final String ARR_DATE 			    = "NA";
        final String ADULTS				    = "1";
        final String CHILDREN			    = "0";
        final String INFANT_LAP			    = "0";
        final String INFANT_SEAT		    = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		    = "nonstop";
        final String FARE_TYPE			    = "Standard";
        final String UPGRADE_VALUE	  	    = "BookIt";

        //Option Page Constant Values
        final String OPTIONS_VALUE		    = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String TRAVEL_GUARD_NO       = "NotRequired";
        final String CARD_CVV              = "123";

        //Confirmation Page Constant value
        final String BOOKING_STATUS     = "Confirmed";
        final String CONFIRMATION_URL   = "book/confirmation";

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

        /******************************************************************************
         **************************Validation on Payment Page**************************
         ******************************************************************************/
        //Step 3--Select no to travel guard
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD_NO);

        ValidationUtil.validateTestStep("User verifies saved card option is visible after selecting no to travel guard",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getSelectCardDropDown()));

        //Step5--Click on saved cards drop
        pageObjectManager.getPaymentPage().getSelectCardDropDown().click();

        //Step6--Select Card
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPaymentPage().getSelectCardDropDown(),"XXXXXXXXXXXX4030");

        //Step7--Send CVV
        pageObjectManager.getMemberEnrollmentPage().getSecurityCodeTextBox().sendKeys(CARD_CVV);

        //Step 8--Complete booking
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page",
                getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
    }
}
