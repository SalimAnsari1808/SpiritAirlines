package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

//**********************************************************************************************
//Test Case ID: TC373760
//Description: Task 27181: TC373760 - US 20607 - 001 - CP - New Hotel Page Feature - Hotel Upsell - Validate default sorting and filter settings for a standard booking
//Created By: Gabriela
//Created On: 18-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC373760 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "Outside21Days", "Adult","Child", "Guest", "NonStop", "BookIt", "ContactInformation", "NoBags", "NoSeats", "HotelsUI"})
    public void CP_New_Hotel_Page_Feature_Hotel_Upsell_Validate_default_sorting_and_filter_settings_for_a_standard_booking(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373760 under GoldFinger Suite on " + platform + " Browser", true);
        }
        // Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "135";
        final String ARR_DATE           = "139";
        final String ADULT              = "5";
        final String CHILD              = "2";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String RET_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Hotel Page Constant Values
        final String RECOMMENDED_COLOR  = "rgba(0, 0, 0, 1)";

//- Step 1: Using Google Chrome, Access Spirit home page in test environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 2: Initiate a search on the "Book" tab of the booking widget for the following flight: RT DOM-DOM| Outside 48 hrs | 5 ADT 2 CHD
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 3: Fill DOB for all child PAX, select "Continue"
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

//- Step 4: Select first flights, click "Continue"
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);

//- Step 5: Select "BOOK IT"
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//- Step 6: Fill in all Pax info
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

//- Step 7: Select "CONTINUE"
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Step 8: Scroll to the bottom of the page and select "Continue Without Adding Bags"
//- Step 9: select "I DON'T NEED BAGS"
        //Step 8 & 9
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//- Step 10: Select "Continue without Selecting Seats"
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//- Step 11: Validate that Hotels options are available on the Options page
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating hotel option is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelCarouselTitleText()));

//- Step 12: Select the "View All Hotels" hyperlink under the Hotel carousel
        pageObjectManager.getHotelPage().getViewAllHotelsButton().click();

//- Step 13: Scroll down to the bottom of the page. Verify that "SHOW MORE" button is present
        double hotelCounter = Double.parseDouble(pageObjectManager.getHotelPage().getHotelCounterText().getText().replace(" Hotels",""));
        System.out.println("hotelCounter: " +hotelCounter);
        if (hotelCounter > 20)
        {
            ValidationUtil.validateTestStep("Validating 'SHOW MORE' button is displayed when more that 20 hotels are available",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getShowMoreButton()));
        }

//- Step 14: Validate that the Recommended hotel are displayed first by default.
        System.out.println("recommended color is " + pageObjectManager.getHotelPage().getRecommendedButton().getCssValue("color"));
        ValidationUtil.validateTestStep("Validating Recommended tab is selected by default",
                pageObjectManager.getHotelPage().getRecommendedButton().getCssValue("color"),RECOMMENDED_COLOR);

//- Step 15: Validate that the Non Featured Hotels default sort display pricing from Low to High
        pageObjectManager.getHotelPage().getPriceButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        List<Double> hotelPrice = new ArrayList<>();
        String hotelPriceText = "";
        for (int i = 0; i < pageObjectManager.getHotelPage().getHotelCard().size(); i++)
        {
            hotelPriceText = pageObjectManager.getHotelPage().getStartingFromPricePerPersonText().get(i).getText().replace("$","");
            hotelPrice.add(Double.parseDouble(hotelPriceText));
        }
        System.out.println("carPrice: " + hotelPrice );

        boolean hotelPriceFlag = true;
        for (int i = 0; i < hotelPrice.size(); i ++)
        {
            if (i==hotelPrice.size()-1)
            {
                break;
            }
            if (hotelPrice.get(i)>hotelPrice.get(i+1))
            {
                hotelPriceFlag = false;
            }
        }

        ValidationUtil.validateTestStep("Validating the default car sorting should be Sorted By price Low to Hight.",
                hotelPriceFlag);
    }
}
