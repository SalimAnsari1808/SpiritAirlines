package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.*;
import com.spirit.enums.Context;
import com.spirit.utility.*;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import org.testng.annotations.Optional;

//**********************************************************************************************
//Test CaseID: TC281059
//Title      : 31392 035. E2E_Guest_OW DOM 1 ADT_Thru Flight_Standard_Bundle It [Tier 3] Bags_Thrills Seats_No Extras CI Web_Credit Card
//Description: Validate user can complete booking by using parameters from the title
//Created By : Alex Rodriguez
//Created On : 2-May-2019
//Reviewed By: Salim Ansari
//Reviewed On: 3-May-2019
//**********************************************************************************************
public class TC281059 extends BaseClass {

    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "OneWay" ,"Through", "DomesticDomestic" , "Within21Days" , "Adult" ,
            "Guest", "NonStop" , "BundleIt" , "DynamicShoppingCartUI", "CarryOn" , "CheckedBags" , "Standard" ,
            "FlightFlex" , "ShortCutBoarding" ,"CheckInOptions" , "Visa","BagsUI"})
    public void E2E_Guest_OW_DOM_1_ADT_Thru_Flight_Standard_Bundle_It_Bags_Thrills_Seats_No_Extras_CI_Web_Credit_Card(@Optional("NA") String platform) {
        /******************************************************************************
         ****************************Navigate to Bags Page*****************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC281059 under Smoke Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE         = "English";
        final String JOURNEY_TYPE     = "Flight";
        final String TRIP_TYPE        = "OneWay";
        final String DEP_AIRPORTS     = "AllLocation";
        final String DEP_AIRPORT_CODE = "DEN";
        final String ARR_AIRPORTS     = "AllLocation";
        final String ARR_AIRPORT_CODE = "FLL";
        final String DEP_DATE         = "20";
        final String ARR_DATE         = "NA";
        final String ADULTS           = "1";
        final String CHILDREN         = "0";
        final String INFANT_LAP       = "0";
        final String INFANT_SEAT      = "0";

        //Flight Availability Page Constant Values

        final String DEP_FLIGHT       = "Through";
        final String FARE_TYPE        = "Standard";
        final String UPGRADE_VALUE    = "BundleIt";

        //Bags Page Constant
        final String BUNDLE_ITINERARY   = "Bundle It Discount";
        final String CHECKED_BAG    	= "Included";

        //Seats Page Constant Values
        final String SEATS            = "Standard";

        //Options Page Constant Values
        final String OPTIONS          = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String TRAVEL_GUARD     = "NotRequired";
        final String CREDIT_CARD      = "VisaCard";

        //Confirmation page Constant values
        final String STATUS           = "Confirmed";

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

        //Bags Page Methods

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
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(SEATS);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //Options Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment Page Methods
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CREDIT_CARD);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        WaitUtil.untilTimeCompleted(3000);

        //Confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        ValidationUtil.validateTestStep("User confirms booking was completed successfully", pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText().contains(STATUS));
    }
}