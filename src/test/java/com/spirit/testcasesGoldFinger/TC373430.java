package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

//**********************************************************************************************
//Test Case ID: TC373430
//Description: Task 27873: TC373430 - 005 - CP - Room Rate - Hotel Upsell - Validate Room Rates for a booking with an International Origin
// Created By: Gabriela
//Created On: 30-Nov-2019
//Reviewed By: Anthony Cardona
//Reviewed On: 05-Dec-2019
//**********************************************************************************************
public class TC373430 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip" , "RedEye" , "InternationalDomestic" , "Outside21Days", "Adult", "Guest", "NonStop", "HotelsUI" , "OptionalUI"})
    public void CP_Room_Rate_Hotel_Upsell_Validate_Room_Rates_for_a_booking_with_an_International_Origin(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373430 under GoldFinger Suite on " + platform + " Browser", true);
        }
        // Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "SAP";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "135";
        final String ARR_DATE           = "139";
        final String ADULT              = "2";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 	    = "Autoselect";
        final String ARR_Flight 	    = "Autoselect";
        final String FARE_TYPE 	    	= "Standard";
        final String UPGRADE_VALUE 	  	= "BookIt";

//- Step 1: Access GoldFinger test environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();

//- Step 2: Create a RT 2 ADT INT- DOM
        //*** Home Page Methods **/
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 3: select first available RED EYE flight , continue to next page
        //*** Flight Availability Page **/
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);

//- Step 4: If Upgrade & Save pop up is displayed,  select book it
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//- Step 5: Enter all info for pax
        //*** Passenger Information Page Methods **/
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Step 6: leave all bags at 0 and select continue on standard pricing
        //*** Bags Page Methods **/
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//- Step 7: continue without seats
        //*** Seats Page Methods **/
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//- Step 8: on options page, select view all hotels under hotels for less
        //*** Options Page Methods **/
        pageObjectManager.getHotelPage().getViewAllHotelsButton().click();

//- Step 9: on the hotel availability page, click "SELECT ROOM" on any hotel
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHotelPage().getHotelViewButton().get(0).click();
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getHotelPage().getHotelPopUpSelectRoomsFromButton().click();

//- Step 10: validate same room types have different prices and the reason why room rates are different ( whats included)
        List<WebElement> priceList = pageObjectManager.getHotelPage().getHotelPopUpRoomprice();
        List<WebElement> nameList = pageObjectManager.getHotelPage().getHotelPopUpRoomTypeText();
        List<String> roomName = new ArrayList<>();
        List<String> roomPrice = new ArrayList<>();

        for (int i = 0; i < pageObjectManager.getHotelPage().getHotelPopUpSelectRoomButton().size(); i ++)
        {
            roomPrice.add(priceList.get(i).getText());
            roomName.add(nameList.get(i).getText());
        }
        for (int i = 0; i < roomName.size(); i ++)
        {
            for (int j = 0 ; j < roomName.size(); j++)
            {
                if (i == j) continue; //skip when index is the same number
                if (roomName.get(i).equals(roomName.get(j))) {
                    ValidationUtil.validateTestStep("Comparing " + roomName.get(i) + " with price " + roomPrice.get(i) + " and " + roomName.get(j) + " with price " + roomPrice.get(j),
                            !roomPrice.get(i).equals(roomPrice.get(j)));
                }
            }
        }

//- Step 11: access HBG website
        //TODO: Method to validate on HBG web site, needs to be implemented
//- Step 12: input same city pair and pax info select search
        //TODO: Method to validate on HBG web site, needs to be implemented
//- Step 13: select same hotel and room and verify info are matching
        //TODO: Method to validate on HBG web site, needs to be implemented
    }
}