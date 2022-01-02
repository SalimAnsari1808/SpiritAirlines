package com.spirit.testcasesProdBAT;

import com.spirit.baseClass.*;
import com.spirit.enums.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC280446
//Test Name:   E2E_9DFC_OW INT Multi ADT_Direct Flight_STD_1CO 1CB_Any Seats_No Extras_CI Web_Credit Card
//Description:
////Created By : Sunny Sok
//Created On : 15-Apr-2019
//Reviewed By: Salim Ansari
//Reviewed On: 23-Apr-2019
//**********************************************************************************************
public class PRODTC90447 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups="Production")

    public void E2E_9DFC_OW_INT_Multi_ADT_Direct_Flight_STD_1CO_1CB_Any_Seats_No_Extras_CI_Web_Credit_Card(@Optional("NA") String platform) {
        /******************************************************************************
         *******************************Navigate to Home Page**************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID PRODTC280446 under PRODUCTION Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE               = "English";
        final String LOGIN_EMAIL 	        = "ProdNineDFCEmail";
        final String LOGIN_EMAIL_TEST       = "NineDFCEmail";
        final String JOURNEY_TYPE           = "Flight";
        final String TRIP_TYPE              = "Oneway";
        final String DEP_AIRPORTS           = "AllLocation";
        final String DEP_AIRPORT_CODE       = "BWI";
        final String ARR_AIRPORTS           = "AllLocation";
        final String ARR_AIRPORT_CODE       = "CUN";
        final String DEP_DATE               = "5";
        final String ARR_DATE               = "NA";
        final String ADULTS				    = "2";
        final String CHILDS				    = "0";
        final String INFANT_LAP			    = "0";
        final String INFANT_SEAT		    = "0";

        //Flight Availability Page Constant Values
        final String SORT_BY                = "Departure";
        final String DEP_FLIGHT             = "NonStop";
        final String FARE_TYPE              = "Member";
//        final String UPGRADEVALUE         = "BoostIt";
        final String UPGRADEVALUE           = "BundleIt";
        //Bags Page Constant
        final String BOOSTIT_INERARY        = "Boost It Discount";
        final String CHECKED_BAG    	    = "Included";

        //Seat Page Constant
        final String DEP_SEAT               = "Standard|Standard";

        //Options Page constant values
        final String OPTION_VALUE           = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String SELECTED_OPTION_VALUE  = "ShortCutBoarding";
        final String CARD_TYPE              = "MasterCard";
        final String TRAVEL_GUARD           = "NotRequired";

        //STEP--1
        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        if(getDriver().getCurrentUrl().equals("https://www.spirit.com/")) {
            pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_EMAIL);
        }else
        {
            pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_EMAIL_TEST);
        }
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().selectOneWayInternationalPopup();

        WaitUtil.untilPageLoadComplete(getDriver());

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectSortingOption("Dep", SORT_BY);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADEVALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //open dynamic shopping cart
//        pageObjectManager.getHeader().getArrowYourItineraryImage().click();

        //wait for 2 sec
//        WaitUtil.untilTimeCompleted(2000);

//        //verify bundle discount text
//        ValidationUtil.validateTestStep("Verify the Bundle It Discount text on Dynamic Shopping Cart",
//                pageObjectManager.getHeader().getBareFareDiscountItineraryText().getText(),BOOSTIT_INERARY);
//
//        //verify bundle discount text
//        ValidationUtil.validateTestStep("Verify the Bundle It Discount Price on Dynamic Shopping Cart",
//                scenarioContext.getContext(Context.AVAILABILITY_BOOSTIT_SAVEUPTO_PRICE).toString(), pageObjectManager.getHeader().getBareFareDiscountPriceItineraryText().getText().replace("-",""));


        //Bags Page Methods
        for(WebElement checkedBag : pageObjectManager.getBagsPage().getDepartingCheckedBagPriceText()){
            //verify checked is already included
            ValidationUtil.validateTestStep("Verify Checked Bag is included with Boost It on Flight Availiability Page",
                    checkedBag.getText(),CHECKED_BAG);
        }
        pageMethodManager.getBagsPageMethods().checkInContiueWithBag();

        //Seats Page Methods
        //seats
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEAT);

        //verify for continue without seat
        ValidationUtil.validateTestStep("Verify 'Continue Without Seat' is not displayed on Seat Page",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getSeatsPage().getContinueWithoutSeatButton()));

        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //option Page Methods
        //option Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());

        //verify Shortcut Boarding is selected
        ValidationUtil.validateTestStep("Verify ShortCut Boarding is Selected on Options Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getShortCutBoardingCardSelectedLabel()));

        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //open toatl due amount
        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //verify bundle fare discount
//        if(TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getTotalDueBoostDiscountText())){
//            //verify bundle fare price
//            ValidationUtil.validateTestStep("Verify the Bundle It Discount price appear on Payment Page",
//                    scenarioContext.getContext(Context.AVAILABILITY_BOOSTIT_SAVEUPTO_PRICE).toString(),pageObjectManager.getPaymentPage().getTotalDueBoostDiscountPriceText().getText().replace("-","" ));
//        }else{
//            ValidationUtil.validateTestStep("Verify the Bundle It Discount appear on Payment Page",false);
//        }

        //payment Page Methods
        pageMethodManager.getPaymentPageMethods().verifyOptionSectionSelectedOptions(SELECTED_OPTION_VALUE);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
//        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
//        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);
//
//        //confirmation Page Methods
//        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
//
//        /******************************************************************************
//         ***********************Validation to Confirmation Page************************
//         ******************************************************************************/
//        //declare constant used in validation
//        final String BOOKING_STATUS = "Confirmed";
//        final String CONFIRMATION_URL = "book/confirmation";
//
//        //verify booking is confirmed
//        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page", getDriver().getCurrentUrl(),CONFIRMATION_URL);
//
//        //verify booking is confirmed
//        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page", pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
//
//        //verify selected option
//        pageMethodManager.getConfirmationPageMethods().verifyOptionSectionSelectedOptions(SELECTED_OPTION_VALUE);

    }
}
