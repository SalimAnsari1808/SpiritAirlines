package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC349843
//Description: Task 27817: TC349843 - 007 - CP - Flight Only - Options Page - NEG Validate no hotels are offered when booking more than 8 passengers
// Created By: Anthony Cardona
//Created On: 26-Nov-2019
//Reviewed By: kartik chauhan
//Reviewed On:10-Dec-2019
//**********************************************************************************************
public class TC349843 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "Outside21Days", "Adult", "Guest", "BookIt", "NonStop",
                    "NoBags", "NoSeats", "OptionalUI"})
    public void NEG_Validate_no_hotels_are_offered_when_booking_more_than_8_passengers(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC349843  under GoldFinger Suite on " + platform + " Browser", true);
        }

        final String ADULT9         = "9";
        final String ADULT7         = "7";

        openBrowser(platform);

        //Negative test for 9 pax: no hotel options on options page
        getToTheOptionsPage(ADULT9);
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("There is no Hotel options displayed for 9 pax on the options page: pass"  ,
                !TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelCarouselTitleText()));


        //Test for 7 pax: Hotel options displayed on options page
        getToTheOptionsPage(ADULT7);
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("There is no Hotel options displayed for 7 pax on the options page: pass"  ,
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelCarouselTitleText()));
    }

        private void getToTheOptionsPage (String adultPaxNum)
        {
            //Home Page Constant Values
            final String LANGUAGE           = "English";
            final String JOURNEY_TYPE       = "Flight";
            final String TRIP_TYPE          = "RoundTrip";
            final String DEP_AIRPORTS       = "AllLocation";
            final String DEP_AIRPORT_CODE   = "FLL";
            final String ARR_AIRPORTS       = "AllLocation";
            final String ARR_AIRPORT_CODE   = "MCO";
            final String DEP_DATE           = "132";
            final String ARR_DATE           = "133";
            final String ADULT              = adultPaxNum;
            final String CHILD              = "0";
            final String INFANT_LAP         = "0";
            final String INFANT_SEAT        = "0";

            //Flight Availability Page Constant Values
            final String DEP_FLIGHT         = "NonStop";
            final String ARR_Flight         = "NonStop";
            final String FARE_TYPE          = "Standard";
            final String UPGRADE_VALUE      = "BookIt";

//- Step 3: : Start booking a RT DOM flight only for N adults outside of 90 days.

            pageMethodManager.getHomePageMethods().launchSpiritApp();
            pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
            pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
            pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
            pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
            pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
            pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
            pageMethodManager.getHomePageMethods().clickSearchButton();

//Step 4: Continue with the booking until you reach the Options page
            pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
            pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
            pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
            pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

            pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
            pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
            pageMethodManager.getPassengerInfoMethods().clickContinueButton();

            pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

            pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
        }

}
