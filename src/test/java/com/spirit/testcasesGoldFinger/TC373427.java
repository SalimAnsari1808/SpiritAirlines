package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.*;

//**********************************************************************************************
//Test Case ID: TC373427
//Description: Task 27820: TC373427- 004 - CP - Hotel Widget Dynamic Selection - Validate the default Room Selection for 8 Passengers
// Created By: Gabriela
//Created On: 23-Nov-2019
//Reviewed By: Sunny Sok
//Reviewed On: 10-Dec-2019
//**********************************************************************************************

public class TC373427 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "FlightHotel", "Outside21Days", "Adult", "Guest", "HomeUI", "BookIt","NoBags","NoSeats","CheckInOptions","AmericanExpress"})
    public void CP_Hotel_Widget_Dynamic_Selection_Validate_the_default_Room_Selection_for_8_Passengers(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373427 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "132";
        final String ARR_DATE           = "133";
        final String ADULT              = "8";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Save and Upgrade pop up Constant Value
        final String F_H_URL            = "/book/flights-hotels";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page Constant Values
        final String OPTION_VALUE 	    = "CheckInOption:MobileFree";

        //Payment Page Constant values
        final String TRAVEL_GUARD 		= "NotRequired";
        final String CARD_DETAIL 		= "AmericanExpressCard";

//- Step 1: Open goldfinger website environment on consumer portal
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 2: Click on Vacation tab, select the Flight+ Hotel radio button
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);

//- Step 3: Select city pair, then select 8 ADT and validate that the rooms field defaults to 2 room
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);

        Select roomsDrp = new Select(pageObjectManager.getHomePage().getRoomsDropDown());
        ValidationUtil.validateTestStep("Validating the room field defaults to 2 room.",
                roomsDrp.getFirstSelectedOption().getText(),"2 Rooms");

//- Step 4: With 8 ADT selected validate the room tab allows up to 4 rooms
        List<WebElement>allRooms = roomsDrp.getOptions();
        ValidationUtil.validateTestStep("Validating there is up to 4 rooms option for 8 passengers available",
                allRooms.size()>= 4);

//- Step 5: Select dates at least 3 months out and click Search Vacation
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 6: Click on SELECT ROOM on any hotel and then select your room and continue
        ValidationUtil.validateTestStep("The user redirected to the correct page",
                getDriver().getCurrentUrl(),(F_H_URL));

        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("NA","NA");

        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getCarPage().getCarsPageContinueWithoutCarButton().click();

//- Step 7: Click on book it on bundle pop up
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
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//- Step 12: Fill out proper payment information and complete booking
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
//        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
//        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
//
//        //Confirmation page Methods
//        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
//        pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
    }
}