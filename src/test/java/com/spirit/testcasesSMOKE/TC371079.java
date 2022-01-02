package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.*;
import com.spirit.enums.Context;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC371079
//Description: E2E_Guest_RT DOM 1 ADT_Red-eye Flight Bundle It (Tier 3)_Standard_Bundle It (Tier 3) Bags_Bundle It (Tier 3) Seats_No Extras CI Web_Credit Card
//Created By : Sunny Sok
//Created On : 18-Mar-2019
//Reviewed By: Salim Ansari
//Reviewed On: 24-Apr-2019
//**********************************************************************************************
public class TC371079 extends BaseClass {

    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" ,"Outside21Days", "Adult" , "Guest" , "RedEye" ,
            "BundleIt" , "DynamicShoppingCartUI","CarryOn" , "CheckedBags" , "BagsUI" , "Standard","FlightFlex" ,
            "ShortCutBoarding","OptionalUI" , "Visa","PaymentUI","ConfirmationUI"})
    public void E2E_Guest_RT_DOM_1_ADT_Red_eye_Flight_Bundle_It_Tier_3_Standard_Bundle_It_Tier_3B_ags_Bundle_It_Tier_3_Seats_No_Extras_CI_Web_Credit_Card(@Optional("NA") String platform) {
        /******************************************************************************
         ***************************Navigate to Confirmation Page**********************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC371079 under SMOKE Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			    = "English";
        final String JOURNEY_TYPE 		    = "Flight";
        final String TRIP_TYPE 			    = "RoundTrip";
        final String DEP_AIRPORTS 		    = "AllLocation";
        final String DEP_AIRPORT_CODE 	    = "MSP";
        final String ARR_AIRPORTS 		    = "AllLocation";
        final String ARR_AIRPORT_CODE 	    = "LAS";
        final String DEP_DATE 			    = "2";
        final String ARR_DATE 			    = "30";
        final String ADULTS 			    = "1";
        final String CHILDS 			    = "0";
        final String INFANT_LAP 		    = "0";
        final String INFANT_SEAT		    = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		    = "169";
        final String RET_FLIGHT 		    = "NonStop";
        final String FARE_TYPE 			    = "Standard";
        final String UPGRADEVALUE 		    = "BundleIt";

        //Bags Page Constant
        final String BUNDLE_ITINERARY       = "Bundle It Discount";
        final String CHECKED_BAG    	    = "Included";

        //Seat Page Constant
        final String DEP_SEAT               = "Standard";
        final String RET_SEAT               = "Standard";

        //Options Page constant values
        final String OPTION_VALUE           = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String SELECTED_OPTION_VALUE  = "FlightFlex|ShortCutBoarding";
        final String CARD_TYPE              = "MasterCard";
        final String TRAVEL_GUARD           = "NotRequired";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNumberType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADEVALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //open dynamic shopping cart
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //verify bundle discount text
        ValidationUtil.validateTestStep("Verify the Bundle It Discount text on Dynamic Shopping Cart",
                pageObjectManager.getHeader().getBareFareDiscountItineraryText().getText(),BUNDLE_ITINERARY);

        //verify bundle discount text
        ValidationUtil.validateTestStep("Verify the Bundle It Discount Price on Dynamic Shopping Cart",
                scenarioContext.getContext(Context.AVAILABILITY_BUNDLEIT_SAVEUPTO_PRICE).toString(), pageObjectManager.getHeader().getBareFareDiscountPriceItineraryText().getText().replace("-",""));


        //Bags Page Methods
        for(WebElement checkedBag : pageObjectManager.getBagsPage().getDepartingCheckedBagPriceText()){
            //verify checked is already included
            ValidationUtil.validateTestStep("Verify Checked Bag is included with Boost It on Flight Availiability Page",
                    checkedBag.getText(),CHECKED_BAG);
        }

        if(TestUtil.verifyElementDisplayed(pageObjectManager.getBagsPage().getContinueWithStandardBagsContainerContinueButton())){
            pageObjectManager.getBagsPage().getContinueWithStandardBagsContainerContinueButton().click();
        }else if(TestUtil.verifyElementDisplayed(pageObjectManager.getBagsPage().getContinueWithBagsButton())){
            pageObjectManager.getBagsPage().getContinueWithBagsButton().click();
        }


        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEAT);
        pageMethodManager.getSeatsPageMethods().selectReturningSeats(RET_SEAT);

        //verify for continue without seat
        ValidationUtil.validateTestStep("Verify 'Continue Without Seat' is not displayed on Seat Page",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getSeatsPage().getContinueWithoutSeatButton()));

        //continue with seat
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //option Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());

        //verify flight flex is selected
        ValidationUtil.validateTestStep("Verify Flight Flex is Selected on Options Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getFlightFlexCardSelectedLabel()));

        //verify Shortcut Boarding is selected
        ValidationUtil.validateTestStep("Verify ShortCut Boardinf is Selected on Options Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getShortCutBoardingCardSelectedLabel()));

        //option Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //open toatl due amount
        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //verify bundle fare discount
        if(TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getTotalDueBundleDiscountText())){
            //verify bundle fare price
            ValidationUtil.validateTestStep("Verify the Bundle It Discount price appear on Payment Page",
                    scenarioContext.getContext(Context.AVAILABILITY_BUNDLEIT_SAVEUPTO_PRICE).toString(),pageObjectManager.getPaymentPage().getTotalDueBundleDiscountPriceText().getText().replace("-","" ));
        }else{
            ValidationUtil.validateTestStep("Verify the Bundle It Discount appear on Payment Page",false);
        }

        //payment Page Methods
        pageMethodManager.getPaymentPageMethods().verifyOptionSectionSelectedOptions(SELECTED_OPTION_VALUE);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        /******************************************************************************
         ***********************Validation to Confirmation Page************************
         ******************************************************************************/
        //declare constant used in validation
        final String BOOKING_STATUS = "Confirmed";
        final String CONFIRMATION_URL = "book/confirmation";

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page",
                getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);

        //verify selected option
        pageMethodManager.getConfirmationPageMethods().verifyOptionSectionSelectedOptions(SELECTED_OPTION_VALUE);
    }
}
