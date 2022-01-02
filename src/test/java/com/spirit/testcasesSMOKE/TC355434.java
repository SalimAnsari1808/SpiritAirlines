package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.*;
import com.spirit.enums.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test CaseID: TC355434
//Test Name  : E2E_Guest_OW Dom Multi ADT_Connecting Flight_Standard_Boost It [Tier 2] Bags_Boost It [Tier 2] Seats_No Extras CI Web_Credit Card
//Description:
//Created By : Sunny Sok
//Created On : 20-May-2019
//Reviewed By: Salim Ansari
//Reviewed On: 21-May-2019
//**********************************************************************************************
public class TC355434 extends BaseClass {

    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "Connecting" ,
                     "BoostIt" , "DynamicShoppingCartUI" , "CheckedBags" , "BagsUI" , "Standard" , "ShortCutBoarding" ,
                     "CheckInOptions" , "MasterCard" , "PaymentUI"})
    public void E2E_Guest_OW_Dom_Multi_ADT_Connecting_Flight_Standard_Boost_It_Tier_2_Bags_Boost_It_Tier_2_Seats_No_Extras_CI_Web_Credit_Card(@Optional("NA") String platform) {
        /******************************************************************************
         ***************************Navigate to Confirmation Page**********************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC355434 under SMOKE Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			    = "English";
        final String JOURNEY_TYPE 		    = "Flight";
        final String TRIP_TYPE 			    = "OneWay";
        final String DEP_AIRPORTS 		    = "AllLocation";
        final String DEP_AIRPORT_CODE 	    = "FLL";
        final String ARR_AIRPORTS 		    = "AllLocation";
        final String ARR_AIRPORT_CODE 	    = "BWI";
        final String DEP_DATE 			    = "1";
        final String ARR_DATE 			    = "NA";
        final String ADULTS 			    = "3";
        final String CHILDREN			    = "0";
        final String INFANT_LAP 		    = "0";
        final String INFANT_SEAT		    = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		    = "Connecting";
        final String FARE_TYPE 			    = "Standard";
        final String UPGRADE_VALUE 		    = "BoostIt";

        //Bags Page Constant
        final String BOOST_ITINERARY        = "Boost It Discount";
        final String CHECKED_BAG    	    = "Included";

        //Seat Page Constant
        final String DEP_SEAT               = "Standard|Standard|Standard||Standard|Standard|Standard";

        //Options Page constant values
        final String OPTION_VALUE           = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String SELECTED_OPTION_VALUE  = "ShortCutBoarding";
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
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //open dynamic shopping cart
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //verify Boost it discount text
        ValidationUtil.validateTestStep("Verify the Boost it Discount text on Dynamic Shopping Cart",
                pageObjectManager.getHeader().getBareFareDiscountItineraryText().getText(),BOOST_ITINERARY);

        //verify Boost it discount text
        ValidationUtil.validateTestStep("Verify the Boost it Discount Price on Dynamic Shopping Cart",
                scenarioContext.getContext(Context.AVAILABILITY_BOOSTIT_SAVEUPTO_PRICE).toString(), pageObjectManager.getHeader().getBareFareDiscountPriceItineraryText().getText().replace("-",""));


        //Bags Page Methods
        for(WebElement checkedBag : pageObjectManager.getBagsPage().getDepartingCheckedBagPriceText()){
            //verify checked is already included
            ValidationUtil.validateTestStep("Verify Checked Bag is included with Boost It on Flight Availability Page",
                    checkedBag.getText(),CHECKED_BAG);
        }

        pageMethodManager.getBagsPageMethods().continueWithOutChangesBag();

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEAT);

        //verify for continue without seat
        ValidationUtil.validateTestStep("Verify 'Continue Without Seat' is not displayed on Seat Page",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getSeatsPage().getContinueWithoutSeatButton()));

        //continue with seat
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //option Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());

        //option Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //open total due amount
        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //verify bundle fare discount
        if(TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getTotalDueBoostDiscountPriceText())){
            //verify bundle fare price
            ValidationUtil.validateTestStep("Verify the Boost It Discount price appear on Payment Page",
                    scenarioContext.getContext(Context.AVAILABILITY_BOOSTIT_SAVEUPTO_PRICE).toString(),pageObjectManager.getPaymentPage().getTotalDueBoostDiscountPriceText().getText().replace("-","" ));
        }else{
            ValidationUtil.validateTestStep("Verify the Boost It Discount appear on Payment Page",false);
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