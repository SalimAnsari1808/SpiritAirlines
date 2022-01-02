package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.*;
import com.spirit.enums.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC281082
//Description:  E2E_Guest_OW DOM 1 ADT 1 Child 2-5_SW Change Flight, Bundle It [Tier 3] Combo, Direct Flight_Standard_Bundle It [Tier 3] Bags_Trills Seats_No Extras CI Web_Credit Card
//Created By : Sunny Sok
//Created On : 21-May-2019
//Reviewed By: Salim Ansari
//Reviewed On: 22-May-2019
//**********************************************************************************************

public class TC281082 extends BaseClass {

    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Child" , "Guest" ,
                     "NewFlightSearch" , "NonStop" , "BundleIt" , "DynamicShoppingCartUI" , "CarryOn" , "CheckedBags" , "BagsUI"
                    ,"Standard" , "SeatsUI" , "FlightFlex" , "ShortCutBoarding" , "CheckInOptions" , "OptionalUI" , "Visa" , "PaymentUI"})
    public void   E2E_Guest_OW_DOM_1_ADT_1_Child_2_to_5_SW_Change_Flight_Bundle_It_Tier_3_Combo_Direct_Flight_Standard_Bundle_It_Tier_3_Bags_Trills_Seats_No_Extras_CI_Web_Credit_Card(@Optional("NA") String platform) {
        /******************************************************************************
         ***************************Navigate to Confirmation Page**********************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC281082 under SMOKE Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			    = "English";
        final String JOURNEY_TYPE 		    = "Flight";
        final String TRIP_TYPE 			    = "OneWay";
        final String DEP_AIRPORTS 		    = "AllLocation";
        final String DEP_AIRPORT_CODE 	    = "FLL";
        final String ARR_AIRPORTS 		    = "AllLocation";
        final String ARR_AIRPORT_CODE 	    = "BWI";
        final String DEP_DATE 			    = "5";
        final String ARR_DATE 			    = "NA";
        final String ADULT  			    = "1";
        final String CHILD  			    = "1";
        final String INFANT_LAP 		    = "0";
        final String INFANT_SEAT		    = "0";

        //Flight Availability Page Constant Values
        final String NEW_DEP_AIRPORT_CODE 	= "DEN";
        final String NEW_ARR_AIRPORT_CODE 	= "FLL";
        final String DEP_FLIGHT 		    = "NonStop";
        final String FARE_TYPE 			    = "Standard";
        final String UPGRADEVALUE 		    = "BundleIt";

        //Bags Page Constant
        final String BUNDLE_ITINERARY       = "Bundle It Discount";
        final String CHECKED_BAG    	    = "Included";

        //Seat Page Constant
        final String DEP_SEAT               = "Standard|Standard";

        //Options page Constant Values
        final String OPTIONS_VALUE          = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String OPTION_VALUE           = "FlightFlex|ShortCutBoarding";
        final String CARD_TYPE              = "VisaCard";
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
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        WaitUtil.untilPageLoadComplete(getDriver());

        //Flight Availability Methods
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, NEW_DEP_AIRPORT_CODE, ARR_AIRPORTS, NEW_ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
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

        //continue without bags
        pageObjectManager.getBagsPage().getContinueWithStandardBagsContainerContinueButton().click();

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEAT);

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
        ValidationUtil.validateTestStep("Verify ShortCut Boarding is Selected on Options Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getShortCutBoardingCardSelectedLabel()));

        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);

        //continue with purchase
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
        pageMethodManager.getPaymentPageMethods().verifyOptionSectionSelectedOptions(OPTION_VALUE);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        /******************************************************************************
         ***********************Validation to Confirmation Page************************
         ******************************************************************************/
        //declare constant used in validation
        final String BOOKING_STATUS     = "Confirmed";
        final String CONFIRMATION_URL   = "book/confirmation";

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page", getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page", pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);

        pageMethodManager.getConfirmationPageMethods().verifyOptionSectionSelectedOptions(OPTION_VALUE);
    }
}