package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC369527
//Description: E2E_FSMC_OW DOM 1 ADT Miles_Red-Eye_Bundle Bags_Free Seat_No Extras Web CI_Credit Card
//Created By : Anthony Cardona
//Created On : 06-May-2019
//Reviewed By: Salim Ansari
//Reviewed On: 08-May-2019
/**10/21/19 test case passed, removed active bug tag**/
//**********************************************************************************************
public class TC369527 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "OneWay" , "Miles" , "NonStop", "DomesticDomestic" , "WithIn7Days" , "Adult" ,
            "FSMasterCard" ,"RedEye",  "BundleIt" , "DynamicShoppingCartUI", "CarryOn" , "CheckedBags" ,  "Standard" ,
            "ShortCutBoarding" , "CheckInOptions" , "MasterCard"})

    //Bug 26051: CP: BP: Flight Availability FA: User Receives red i block when trying to create a miles booking when logging in either on the homepage, or the FA page

    public void E2E_FSMC_OW_DOM_1_ADT_Miles_Red_Eye_Bundle_Bags_Free_Seat_No_Extras_Web_CI_Credit_Card(@Optional("NA") String platform) {
        //***************************Navigate to Confirmation Page**********************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC369527 under SMOKE Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			    = "English";
        final String LOGIN_ACCOUNT          = "FSMCEmail";
        final String JOURNEY_TYPE 		    = "Flight";
        final String TRIP_TYPE 			    = "OneWay";
        final String DEP_AIRPORTS 		    = "AllLocation";
        final String DEP_AIRPORT_CODE 	    = "LAS";
        final String ARR_AIRPORTS 		    = "AllLocation";
        final String ARR_AIRPORT_CODE 	    = "DFW";
        final String DEP_DATE 			    = "0";
        final String ARR_DATE 			    = "NA";
        final String ADULTS 			    = "1";
        final String CHILDS 			    = "0";
        final String INFANT_LAP 		    = "0";
        final String INFANT_SEAT		    = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		    = "214";
        final String FARE_TYPE 			    = "Standard";
        final String UPGRADEVALUE 		    = "BundleIt";

        //Bags Page Constant
        final String BUNDLE_ITINERARY       = "Bundle It Discount";
        final String CHECKED_BAG    	    = "Included";

        //Seat Page Constant
        final String DEP_SEAT               = "Standard";

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
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);

        //Select Miles pill switch
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHomePage().getMilesLabel().click();
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectMilesFlightNumberType("Dep", DEP_FLIGHT);
        pageObjectManager.getFlightAvailabilityPage().getStandardFareButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getEarlyDepartureContinueButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADEVALUE);

        //Passenger Info Methods
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
        if(TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getTotalDueBundleDiscountText())){
            //verify bundle fare price
            ValidationUtil.validateTestStep("Verify the Bundle It Discount price appear on Payment Page",
                    scenarioContext.getContext(Context.AVAILABILITY_BUNDLEIT_SAVEUPTO_PRICE).toString(),pageObjectManager.getPaymentPage().getTotalDueBundleDiscountPriceText().getText().replace("-","" ));
        }else{
            ValidationUtil.validateTestStep("Verify the Bundle It Discount appear on Payment Page",false);
        }

        //payment Page Methods
        pageMethodManager.getPaymentPageMethods().verifyOptionSectionSelectedOptions(SELECTED_OPTION_VALUE);
        pageMethodManager.getPaymentPageMethods().fillAnotherCardPaymentDetailsModifyPath(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //**********************Validation to Confirmation Page**************************/
        //declare constant used in validation
        final String BOOKING_STATUS = "Confirmed";
        final String CONFIRMATION_URL = "book/confirmation";

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page", getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page", pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);

        //verify selected option
        pageMethodManager.getConfirmationPageMethods().verifyOptionSectionSelectedOptions(SELECTED_OPTION_VALUE);
    }
}