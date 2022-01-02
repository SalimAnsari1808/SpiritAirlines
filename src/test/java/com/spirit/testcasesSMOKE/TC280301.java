package com.spirit.testcasesSMOKE;
import com.spirit.baseClass.*;
import com.spirit.enums.Context;
import com.spirit.utility.*;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import org.testng.annotations.Optional;
import java.lang.String;

//**********************************************************************************************
//Test CaseID: TC280301
//Title      : 468. E2E_9DFC_OW DOM 1 ADT 1 Child +2_DirectFlight_EmotionalSupportAnimal_ThrillBags_Included seats_SB and FF CI web_TG and mastercard
//Description:
//Created By : Alex Rodriguez
//Created On : 23-Apr-2019
//Reviewed By: Salim Ansari
//Reviewed On: 2-May-2019
//**********************************************************************************************

/**BUG 27010 CP: MT/CI: When adding Special services or wheel chair SSR's on the reservation summary page the ssr are not being added to the pnr**/
/**10/21/2019 Bug closed, Removed ActiveBug tag**/

public class TC280301 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "Outside21Days" , "Adult" , "Child" , "NineDFC" , "NonStop" , "BundleIt" ,
            "DynamicShoppingCartUI","PassengerInfoSSR" , "CarryOn" , "CheckedBags","BagsUI" , "Standard" , "SeatsUI" , "FlightFlex" , "ShortCutBoarding",
            "OptionalUI" ,"TravelInsuranceBlock" , "MasterCard" ,"PaymentUI", "ConfirmationUI"})

    public void E2E_9DFC_OW_DOM_1_ADT_1_Child_2_DirectFlight_EmotionalSupportAnimal_ThrillBags_Included_seats_SB_and_FF_CI_web_TG_and_mastercard (@Optional("NA") String platform){
        /******************************************************************************
         ****************************Navigate to Confirmation  Page********************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280301 under Smoke Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE         = "English";
        final String JOURNEY_TYPE     = "Flight";
        final String EMAIL_LOGIN      = "NineDFCEmail";
        final String TRIP_TYPE        = "OneWay";
        final String DEP_AIRPORTS     = "AllLocation";
        final String DEP_AIRPORT_CODE = "FLL";
        final String ARR_AIRPORTS     = "AllLocation";
        final String ARR_AIRPORT_CODE = "DEN";
        final String DEP_DATE         = "25";
        final String ARR_DATE         = "NA";
        final String ADULTS           = "1";
        final String CHILDREN         = "1";
        final String INFANT_LAP       = "0";
        final String INFANT_SEAT      = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT       = "Nonstop";
        final String FARE_TYPE        = "Member";
        final String UPGRADE_VALUE    = "BundleIt";

        //Passenger Info Constant Values
        final String SSR_VALUE          = "EmotionalAnimal";

        //Bags Page Constant Values
        final String BUNDLE_ITINERARY   = "Bundle It Discount";
        final String CHECKED_BAG    	= "Included";

        //Seats Page Constant values
        final String DEP_SEATS        = "Standard|Standard";

        //Payment Page Constant Values
        final String SELECTED_OPTION  = "FlightFlex|ShortCutBoarding";
        final String TRAVEL_GUARD     = "Required";
        final String CREDIT_CARD      = "MasterCard";

        //Confirmation page Constant values
        final String STATUS           = "Confirmed";

        //open browser
        openBrowser(platform);
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(EMAIL_LOGIN);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().selectSSRPerPassenger(SSR_VALUE);
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

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithSelectingBags();

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEATS);

        //verify for continue without seat
        ValidationUtil.validateTestStep("Verify 'Continue Without Seat' is not displayed on Seat Page",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getSeatsPage().getContinueWithoutSeatButton()));


        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //option Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());

        //verify flight flex is selected
        ValidationUtil.validateTestStep("Verify Flight Flex is Selected on Options Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getFlightFlexCardSelectedLabel()));

        //verify Shortcut Boarding is selected
        ValidationUtil.validateTestStep("Verify ShortCut Boarding is Selected on Options Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getShortCutBoardingCardSelectedLabel()));

        //verify Check-In option is disabled
        ValidationUtil.validateTestStep("Verify Check-In Option is Disabled on Options Page",
                pageObjectManager.getOptionsPage().getCheckInOptionCardPanel().getAttribute("class"),"disabled");

        //Options Page Methods
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

        //Payment Page Methods
        pageMethodManager.getPaymentPageMethods().verifyOptionSectionSelectedOptions(SELECTED_OPTION);
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CREDIT_CARD);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        /******************************************************************************
         ****************************Validation on Confirmation  Page******************
         ******************************************************************************/
        final String SPECIAL_ASSISTANCE     = "Emotional/Psychiatric Support Animal";

        //Confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        ValidationUtil.validateTestStep("User confirms booking was completed successfully",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),STATUS);

        //verify Special Assistance status
        ValidationUtil.validateTestStep("User confirms Wheel Chair is added on Confirmation Page ",
                pageObjectManager.getConfirmationPage().getSpecialAssistanceText().get(0).getText(),SPECIAL_ASSISTANCE);

        //verify pre selected Shortcut boarding
        pageMethodManager.getPaymentPageMethods().verifyOptionSectionSelectedOptions(SELECTED_OPTION);
    }
}