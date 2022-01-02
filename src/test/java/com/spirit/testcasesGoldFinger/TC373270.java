package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC373270
//Description: Task 27209: TC373270 - 002 - CP - Hotel Widget Dynamic Selection - Validate the default Room Selection for 4 passengers
//Created By: Gabriela
//Created On: 13-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC373270 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "FlightHotel", "RoundTrip", "DomesticDomestic", "Outside21Days", "Adult", "Guest", "NonStop", "BookIt", "NoBags", "NoSeats", "CheckInOptions", "MasterCard"})
    public void CP_Hotel_Widget_Dynamic_Selection_Validate_the_default_Room_Selection_for_4_passengers(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373270 under GoldFinger Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "135";
        final String ARR_DATE           = "136";
        final String ADULT              = "4";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Constant Values
        final String UPGRADE_VALUE      = "BookIt";

        //Options Constant Values
        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

        //Payment Page Constant values
        final String TRAVEL_GUARD 		= "NotRequired";
        final String CARD_DETAIL 		= "MasterCard";

        //Confirmation Page Constant Values
        final String CONFIRMATION_URL   = "/book/confirmation";

//- Step 1: Open goldfinger website environment on consumer portal
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();

//- Step 2: On the Home page Select the Vacation tab, specific Flight + Hotel .
        /***Home Page Methods**/
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);

//- Step 3: Book a Dom-Dom | outside of 3 months | Select 4 ADT
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);

//- Step 4: Validate the room tab allows up to 4 rooms
        ValidationUtil.validateTestStep("Validating the room field defaults to 1 room.",
                pageObjectManager.getHomePage().getRoomsDropDown().getAttribute("value").equals("1"));

        ValidationUtil.validateTestStep("Validating there is 4 rooms option for 4 passengers available",
                getDriver().findElements(By.xpath("//select[contains(@id,'selectHotelRooms') or contains(@id,'hotelRoomCount')]//option")).size()==4);

//- Step 5: Click search vacation
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 6: Click on SELECT ROOM on any hotel and then select your room and continue
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("NA","NA");

        //Continue without car
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getCarPage().getCarsPageContinueWithoutCarButton().click();

//- Step 7: Click on book it on Upgrade & Save pop up
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//- Step 8: Fill out passenger info and continue
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Step 9: Click on continue without bags
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//- Step 10: Click on Continue without seats
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//- Step 11: Select check-in free option and continue
        /***Options Page Methods**/
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//- Step 12: Fill out proper payment information and complete booking
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
//        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
//        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

//        WaitUtil.untilPageLoadComplete(getDriver());
//        ValidationUtil.validateTestStep("Validating user is taken to the right URL",
//                getDriver().getCurrentUrl(),CONFIRMATION_URL);

//        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
//        pageMethodManager.getConfirmationPageMethods().verifyHotelSectionDetails();
//        pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
    }
}