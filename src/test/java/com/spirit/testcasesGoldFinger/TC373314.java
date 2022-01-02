package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC373314
//Description: Task:27818 | TC373314: 002 - CP - New Hotel Page Rules - Validate that an HBG PNR cannot be used to retrieve a Vacation Booking
//Created By: Anthony Cardona
//Created On: 03-Dec-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC373314 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"FlightHotel", "DomesticDomestic", "Guest", "Outside21Days", "BookIt" , "Adult", "NoBags", "NoSeats", "Hotels", "Visa" , "HomeUI"})
    public void CP_New_Hotel_Page_Rules_Validate_that_an_HBG_PNR_cannot_be_used_to_retrieve_a_Vacation_Booking(@Optional("NA") String platform) {
        /******************************************************************************
         ***********************Navigate to Payment Page*******************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373314 under GOLDFINGER Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "flight+hotel";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "90";
        final String ARR_DATE           = "92";
        final String ADULTS             = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String HOTELROOM          = "1 Room";

        //Flight Availability Page Constant Values
        final String HOTEL_NAME         = "MGM";
        final String UPGRADE_VALUE      = "BookIt";

        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        final String TRAVEL_GUARD 		= "NotRequired";
        final String CARD_DETAIL 		= "VisaCard";

        //open browser
        openBrowser(platform);
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
//Step 1: Start creating a Vacation booking | Flight + Hotel | DOM-DOM | Outside 3 Months |1 Pax | No Bags | No Seats | booking
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectHotelRoom(HOTELROOM);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//Step 2: On select Your Hotel page click on the hotel name inside the box of the Hotel you choose.
//Step 3: Select "ROOM"
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage(HOTEL_NAME,"NA");


//Step 4: Select "CONTINUE"
        //invalid step

//Step 5: Select "CONTINUE WITHOUT CAR"

        pageObjectManager.getCarPage().getCarsPageContinueWithoutCarButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1000);

//Step 6: Select "BOOK IT"
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//Step 7: Fill in all customer information and click on " CONTINUE"
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//Step 8 and 9: Select "CONTINUE WITHOUT ADDING BAGS" && Select "I DON'T NEED BAGS"
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//Step 10: Select "CONTINUE WITHOUT SELECTING SEATS"
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//Step 11: Select "CONTINUE"
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//Step 12: Select no to travel insurance, fill out all payment info, agree to HAZMAT terms and condition. click on "BOOK TRIP"

        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
//        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
//
//        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
//        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
//
//
//
////Step 13: Verify HBG PNR cannot be used on Spirit.com to retrieve a booking
//
//        WaitUtil.untilPageLoadComplete(getDriver());
//
//        pageMethodManager.getHomePageMethods().loginToMyTripWithHotelBedsPNR();
//        pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
    }
}