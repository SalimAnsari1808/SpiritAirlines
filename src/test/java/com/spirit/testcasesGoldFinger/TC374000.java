package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC374000
//Description: Task 27800: TC374000- 009 - CP - Price Display Shopping Cart - Vacation Path - Flight + Car with Edit made to Car
//Created By: Gabriela
//Created On: 29-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC374000 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic","FlightCar", "Outside21Days", "Adult", "Guest", "NonStop", "BookIt",
            "FlightAvailabilityUI","DynamicShoppingCartUI", "CarsUI", "NoBags", "NoSeats", "Visa"})
    public void CP_Price_Display_Shopping_Cart_Vacation_Path_Flight_Car_with_Edit_made_to_Car(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC374000 under GoldFinger Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Car";
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
        final String DRIVER_AGE         = "25+";

        //Flight Availability Page Constant Values
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page Constant Values
        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

        //Payment Page Constant values
        final String TRAVEL_GUARD 		= "NotRequired";
        final String CARD_DETAIL 		= "VisaCard";

//- Step 10: Access GoldFinger testing environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 1: Create a vacation booking dom - dom | F+C | 2 adt | 25 + 3 months out
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 2: select book car
        pageMethodManager.getCarPageMethods().selectCarOnCarPage("Dollar","NA");

//- Step 3: scroll down to the bottom of the page and click continue
        //Invalid Step

//- Step 4: select book it
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//- Step 5: locate the dynamic shopping cart at the top right corner and select drop down arrow
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getHeader().getFlightItineraryPanel().click();

//- Step 6: select edit
        //click on F+C arrow
        pageObjectManager.getHeader().getArrowFlightItineraryImage().click();
        WaitUtil.untilTimeCompleted(1200);

        //click on edit link
        pageObjectManager.getHeader().getEditFlightItineraryButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

//- Step 7: click CONTINUE TO FLIGHTS
        //verify Are you sure popup is displayed
        ValidationUtil.validateTestStep("verify Are you sure popup is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getAreYouSurePopUpPanel()));

        //click button on popup
        pageObjectManager.getPaymentPage().getAreYouSurePopUpContinueToFlightButton().click();

//- Step 8: select a different flight and select continue
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getDepartingStandardFarePriceText().get(0).click();
        WaitUtil.untilPageLoadComplete(getDriver());
        JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getFlightAvailabilityPage().getReturningStandardFarePriceText().get(1));
        WaitUtil.untilPageLoadComplete(getDriver());

//- Step 9: select a different car and continue through confirmation page
        pageMethodManager.getCarPageMethods().selectCarOnCarPage("Hertz","NA");

        //Continue to Confirmation page
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);
        //passenger info page
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().selectPrimaryDriver();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
        //Bags page
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
        //Seats page
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
        //Options page
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
        //Payment Page
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        //Confirmation Page
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        //Cancel Car
        pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
    }
}