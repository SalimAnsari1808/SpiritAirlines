package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

//**********************************************************************************************
//Test Case ID: TC373431
//Description: Task 27874: TC373431 - 009 - CP - Room Rate - Flight + Hotel - Validate Room Rates for a booking with an International Destination
// Created By: Gabriela
//Created On: 30-Nov-2019
//Reviewed By: Anthony Cardona
//Reviewed On: 06-Dec-2019
//**********************************************************************************************
public class TC373431 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "FlightHotel", "DomesticInternational", "Outside21Days", "Adult", "Guest", "NonStop", "HotelsUI", "FlightAvailabilityUI"})
    public void CP_Roo_Rate_Flight_Hotel_Validate_Room_Rates_for_a_booking_with_an_International_Destination(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373431 under GoldFinger Suite on " + platform + " Browser", true);
        }
        // Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "CUN";
        final String DEP_DATE           = "105";
        final String ARR_DATE           = "109";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

//- Step 1: Access GoldFinger test environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();

//- Step 2: Create Vacation booking F+H 1 ADT DOM-INT
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 3: flight should be pre selected, if unhappy with flight selection, select edit
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating Departing journey is selected ",
                TestUtil.verifyElementDisplayed(pageObjectManager.getFlightAvailabilityPage().getSelectedDepatureFlightBlockPanel()));

        ValidationUtil.validateTestStep("Validating Returning journey is selected ",
                TestUtil.verifyElementDisplayed(pageObjectManager.getFlightAvailabilityPage().getSelectedArrivalFlightBlockPanel()));

        //Storing journey information for validation
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();

//- Step 4: under " Choose your hotel"  you should list of hotels
        int count = 0;
        for (int i = 0; i < pageObjectManager.getHotelPage().getHotelCard().size(); i ++)
        {
            if (TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelNamesText().get(i)))
            {
                count++;
            }
        }
        ValidationUtil.validateTestStep("Validating Hotel list is displayed on Flight + Hotel Availability page", count!=0);

//- Step 5: Click "SELECT ROOM" button.
        JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getHotelPage().getHotelViewButton().get(0));
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);
        JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getHotelPage().getHotelPopUpSelectRoomsFromButton());

//- Step 6: validate same room types have different prices and the reason why room rates are different ( whats included)
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

//- Step 7: access HBG website
        //TODO: Method to validate needs to be implemented
//- Step 8: input same city pair and pax info select search
        //TODO: Method to validate needs to be implemented
//- Step 9: select same hotel and room and verify info are matching (Not Pricing. Pricing will be different)
        //TODO: Method to validate needs to be implemented
    }
}