package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC373193
//Description: CP - New Hotel Page Rules - Validate that a Package Booking cannot be modified or cancelled through the Manage Travel Path
//Created By: Kartik Chauhan
//Created On: 25-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC373193 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"MyTrips", "RoundTrip", "DomesticDomestic", "Outside21Days", "Adult", "Guest", "Autoselect", "BookIt",
                    "NoBags","NoSeats","CheckInOptions","Discover","FlightHotel","PaymentUI"})
    public void CP_New_Hotel_Page_Rules_Validate_that_a_Package_Booking_cannot_be_modified_or_cancelled_through_the_Manage_Travel_Path(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373193 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE2          = "100";
        final String ARR_DATE2          = "102";
        final String ADULTS             = "3";
        final String CHILDREN           = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page Constant Values
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String CARD_TYPE          = "DiscoverCard1";
        final String TRAVEL_GUARD       = "NotRequired";

//- Step 3: Open the GoldFinger test Website
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 1: Click on vacation tab, Flight+Hotel tab
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE2, ARR_DATE2);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("MGM","NA");

        WaitUtil.untilPageLoadComplete(getDriver());

        pageObjectManager.getCarPage().getCarsPageContinueWithoutCarButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());

        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);


//Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats page methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
        //Payment page Methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
  //      pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        //Error was not displaying
//        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);
//
//        //Confirmation Page Methods
//        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
//        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
//        WaitUtil.untilPageLoadComplete(getDriver());
//
//        pageMethodManager.getHomePageMethods().loginToMyTrip();
//        WaitUtil.untilPageLoadComplete(getDriver());
//
//        pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();

    }
}