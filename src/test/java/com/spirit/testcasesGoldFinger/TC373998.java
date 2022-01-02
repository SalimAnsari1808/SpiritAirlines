package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC373998
//Description: Task 27845: TC373998- 007 - CP - Price Display Shopping Cart - Vacation Path - Flight + Hotel + Car display
//Created By: Gabriela
//Created On: 26-Nov-2019
//Reviewed By: Anthony Cardona
//Reviewed On:  04-Dec-2019
//**********************************************************************************************
public class TC373998 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "FlightHotelCar", "Outside21Days", "Adult", "Guest", "BookIt", "DynamicShoppingCartUI"})
    public void CP_Price_Display_Shopping_Cart_Vacation_Path_Flight_Hotel_Car_display(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373998 under GoldFinger Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "132";
        final String ARR_DATE           = "133";
        final String ADULT              = "3";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String ROOMS_VALUE        = "2 Rooms";
        final String DRIVER_AGE 		= "25+";

        //Flight Availability Page Constant Values
        final String UPGRADE_VALUE      = "BookIt";

//- Step 10: Access GoldFinger testing environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 1: go to vacation tab and create F+H+C | dom- dom | 3 adt | 2 rooms | 25+
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectHotelRoom(ROOMS_VALUE);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 2: select view on any hotel
//- Step 3: select rooms from ##$$
//- Step 4: select room
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("NA","NA");

//- Step 5: select continue at the bottom of the page
        //Invalid Step

//- Step 6: select book car
        pageMethodManager.getCarPageMethods().selectCarOnCarPage("NA","NA");

//- Step 7: select book it
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//- Step 8: locate the dynamic shopping cart at the top right corner and select drop down arrow
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1000);

//- Step 9: verify that you see FLIGHT + HOTEL + CAR
        ValidationUtil.validateTestStep("User verifies dynamic shopping cart has been updated to Flight + Hotel + Car",
                TRIP_TYPE, pageObjectManager.getHeader().getFlightItineraryText().getText().replace(" ", ""));
    }
}