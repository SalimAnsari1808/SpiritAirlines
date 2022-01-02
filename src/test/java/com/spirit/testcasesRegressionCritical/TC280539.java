package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test CaseID: TC280539
//Title      :538. E2E_9DFC_RT DOM MAX PAX MIX_ThruFlight_Standard_ThrillBags_Included seats_NoExtras CI web_CreditCard
//Description: Validate user can complete booking by using parameters from the title
//Created By : Kartik chauhan
//Created On : 10-June-2019
//Reviewed By: Salim Ansari
//Reviewed On: 10-June-2019
//**********************************************************************************************
public class TC280539 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "Outside21Days" , "Adult" , "Child" , "InfantSeat" , "InfantLap" ,
                    "NewFlightSearch", "NineDFC" , "BundleIt" , "Through" ,"DynamicShoppingCartUI", "CarryOn" , "CheckedBags" ,
                    "BagsUI", "Standard" ,"SeatsUI", "FlightFlex" , "ShortCutBoarding" , "Visa" , "PaymentUI","ConfirmationUI"})
    public void E2E_9DFC_RT_DOM_MAX_PAX_MIX_ThruFlight_Standard_ThrillBags_Included_seats_NoExtras_CI_web_CreditCard(@Optional("NA") String platform) {
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280539 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant variables
        final String LANGUAGE           = "English";
        final String LOGIN_ACCOUNT      = "NineDFCEmail";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "DEN";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "FLL";
        final String DEP_DATE           = "25";
        final String ARR_DATE           = "28";
        final String DEP_DATE1          = "27";
        final String ARR_DATE1          = "30";
        final String ADULTS             = "5";
        final String CHILD              = "2";
        final String INFANT_LAP         = "1";
        final String INFANT_SEAT        = "1";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "Through";
        final String RET_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Member";
        final String UPGRADE_VALUE      = "BundleIt";

        // Bags page constant
        final String BAGS_PRESELECTED 	= "1";
        final String BAGS_INCLUDED		= "Included";
        final String BUNDLE_ITINERARY   = "Bundle It Discount";

        //seats page constant value
        final String DEP_SEATS          = "Standard|Standard|Standard|Standard|Standard|Standard|Standard|Standard";
        final String RET_SEATS          = "Standard|Standard|Standard|Standard|Standard|Standard|Standard|Standard";

        //payment page constant value
        final String SELECTED_OPTION    = "FlightFlex|ShortCutBoarding";
        final String CARD_TYPE          = "VisaCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //Confirmation Page Constant Values
        final String BOOKING_STATUS     = "Confirmed";

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
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(2000);
        //Flight Availability Methods
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();

        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE1, ARR_DATE1);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        WaitUtil.untilPageLoadComplete(getDriver(),(long)120);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageObjectManager.getPassengerInfoPage().getInfantTravelingWithCarSeatCheckBox().get(0).click();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Methods
        WaitUtil.untilPageLoadComplete(getDriver(),(long)120);

        //open dynamic shopping cart
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //verify bundle discount text
        ValidationUtil.validateTestStep("Verify the Bundle It Discount text on Dynamic Shopping Cart",
                pageObjectManager.getHeader().getBareFareDiscountItineraryText().getText(), BUNDLE_ITINERARY);

        //verify bundle discount text
        ValidationUtil.validateTestStep("Verify the Bundle It Discount Price on Dynamic Shopping Cart",
                scenarioContext.getContext(Context.AVAILABILITY_BUNDLEIT_SAVEUPTO_PRICE).toString(), pageObjectManager.getHeader().getBareFareDiscountPriceItineraryText().getText().replace("-", ""));

        WaitUtil.untilPageLoadComplete(getDriver());

        //Verify 1-Carry-On and 1-Checked Bags are Pre-Selected for all passengers on Bags Page
        for(int i = 0; i < pageObjectManager.getBagsPage().getDepartingCarryOnBagCounterTextBox().size(); i ++) {
            // Verifying Carry-on is preselected
            ValidationUtil.validateTestStep("Verifying Carry On is preselected",
                    JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingCarryOnBagCounterTextBox().get(i)),BAGS_PRESELECTED);

            //Verifying Carry On price is included
            ValidationUtil.validateTestStep("Verifying Carry On price is included",
                    pageObjectManager.getBagsPage().getDepartingCarryOnPriceText().get(i).getText(),BAGS_INCLUDED);

            // Verifying Checked Bag is preselected
            ValidationUtil.validateTestStep("Verifying 1 checked bag is preselected",
                    JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingCheckedBagCounterTextBox().get(i)),BAGS_PRESELECTED);

            //Verifying Checked Bag price is included
            ValidationUtil.validateTestStep("Verifying Checked Bag price is included",
                    pageObjectManager.getBagsPage().getDepartingCheckedBagPriceText().get(i).getText(),BAGS_INCLUDED);
        }

        pageObjectManager.getBagsPage().getContinueWithBagsButton().click();

        //Seats page methods
        WaitUtil.untilPageLoadComplete(getDriver(),(long)120);
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEATS);
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(RET_SEATS);

        //verify for continue without seat
        ValidationUtil.validateTestStep("Verify 'Continue Without Seat' is not displayed on Seat Page",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getSeatsPage().getContinueWithoutSeatButton()));

        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //Options page methods
        WaitUtil.untilPageLoadComplete(getDriver(),(long)120);

        //Option Page Methods
        ValidationUtil.validateTestStep("Verify Shortcut Boarding is Selected on Option Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getShortCutBoardingCardSelectedLabel()));

        ValidationUtil.validateTestStep("Verify Shortcut Boarding Remove button is not visible on Option Page",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getShortCutBoardingCardRemoveButton()));

        ValidationUtil.validateTestStep("Verify Check-In Option is disabled  on Option Page",
                !pageObjectManager.getOptionsPage().getCheckInOptionCardBodySelectDropDown().isEnabled());


        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment page methods
        WaitUtil.untilPageLoadComplete(getDriver(),(long)120);
        pageMethodManager.getPaymentPageMethods().verifyOptionSectionSelectedOptions(SELECTED_OPTION);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        /******************************************************************************
         *************************Validation on Confirmation Page**********************
         ******************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());

        //Confirmation Code
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(), BOOKING_STATUS);

        pageMethodManager.getConfirmationPageMethods().verifyOptionSectionSelectedOptions(SELECTED_OPTION);

    }
}

