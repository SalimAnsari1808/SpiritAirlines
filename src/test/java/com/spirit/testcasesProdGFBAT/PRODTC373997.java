package com.spirit.testcasesProdGFBAT;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: PRODTC373997
//Description: Task 27799: TC373997- 005 - CP - Price Display Shopping Cart - Hub Packaging - Remove Car from reservation on Car upsell
//Created By: Gabriela
//Created On: 29-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class PRODTC373997 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"Production"})

    public void CP_Price_Display_Shopping_Cart_Hub_Packaging_Remove_Car_from_reservation_on_Car_upsell(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID PRODTC373997 under GoldFinger Suite on " + platform + " Browser", true);
        }

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
        final String ADULT              = "2";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String ARR_Flight         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page Constant Values
        final String FLIGHT_TEXT        = "Flight";
        final String FLIGHT_CAR_TEXT    = "Flight + Car";
        final String OPTIONS_URL        = "/BOOK/OPTIONS";

//- Step 17: Access GoldFinger testing environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 1: create RT DOM-DOM | 2 ADT  3 months out
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 2: select first availible flights and select continue at the bottom of the page
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep",DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret",ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);

//- Step 3: select book it
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//- Step 4: enter info for all pax
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        String firstName = pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0).getAttribute("value").toUpperCase();
        String lastName = pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(0).getAttribute("value").toUpperCase();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Step 5 & 6: continue without bags & I Don't nee bags
        ValidationUtil.validateTestStep("Verify user lands on bags page",
                getDriver().getCurrentUrl().contains("bags"));
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//- Step 7: on seats page, continue without seats
        ValidationUtil.validateTestStep("Verify user lands on seats page",
                getDriver().getCurrentUrl().contains("seats"));
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//- Step 8: on options page locate the dynamic shopping cart on the right and select the drop down arrow
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Verify user lands on options page",
                getDriver().getCurrentUrl().contains("options"));
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getHeader().getFlightItineraryPanel().click();

//- Step 9: verify flight details is only displaying
        ValidationUtil.validateTestStep("Validating Flight only is displayed on the Dynamic Shopping Cart",
                pageObjectManager.getHeader().getFlightItineraryText().getText(),FLIGHT_TEXT);

//- Step 10: verify car carousel is displaying on options page
        int count = 0;
        for (int i=0; i < pageObjectManager.getCarPage().getCarsCarouselAddCarButton().size(); i ++)
        {
            if (pageObjectManager.getCarPage().getCarsCarouselAddCarButton().get(i).isDisplayed())
                count++;
        }

        ValidationUtil.validateTestStep("Validating Car's carousel is displayed on Options page",count!=0);

//- Step 11: select view on any car
        pageObjectManager.getCarPage().getCarsCarouselAddCarButton().get(0).click();
        WaitUtil.untilPageLoadComplete(getDriver());

//- Step 12: select a primary driver and select book car
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getCarPage().getCarPopUpPrimaryDriverDropDown(),firstName + " " + lastName);
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getCarPage().getWhoSDrivingVerifyAndBookThisCarButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating user is taken back to Options page after car selected",
                getDriver().getCurrentUrl(),(OPTIONS_URL));

//- Step 13: verify there is a remove selection i the selected car box
        ValidationUtil.validateTestStep("Validating 'Remove' button is displayed on the hotel box",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageCarRemoveButton()));

//- Step 14: verify the dynamic shopping cart now displays FLIGHT + CAR
        ValidationUtil.validateTestStep("Validating Flight + Hotel is displayed on the Dynamic Shopping Cart",
                pageObjectManager.getHeader().getFlightItineraryText().getText(),FLIGHT_CAR_TEXT);

//- Step 15: select remove
        pageObjectManager.getCarPage().getCarsPageCarRemoveButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        int count1 = 0;
        for (int i=0; i < pageObjectManager.getCarPage().getCarsCarouselAddCarButton().size(); i ++)
        {
            if (pageObjectManager.getCarPage().getCarsCarouselAddCarButton().get(i).isDisplayed())
                count1++;
        }

        ValidationUtil.validateTestStep("Validating Car's Carousel is back after click on Remove link", count1!=0 );

//- Step 16: select the drop down arrow on the dynamic shopping cart and verify it is showing flight only
        ValidationUtil.validateTestStep("Validating Flight only is displayed on the Dynamic Shopping Cart",
                pageObjectManager.getHeader().getFlightItineraryText().getText(),FLIGHT_TEXT);
    }
}