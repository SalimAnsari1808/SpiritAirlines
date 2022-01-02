package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC91163
//Description: Options CP MT Shortcut Boarding
//Created By : Salim Ansari
//Created On : 29-Apr-2019
//Reviewed By: Kartik Chauhan
//Reviewed On: 29-Apr-2019
//**********************************************************************************************

public class TC91163 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"MyTrips" , "RoundTrip" , "DomesticDomestic" , "Outside21Days" , "Adult" ,"Guest", "Connecting" , "BookIt" ,
            "CarryOn" ,"CheckedBags" , "NoSeats", "CheckInOptions" , "OptionalUI" ,"AddEditBags", "MasterCard", "PaymentUI" , "ShortCutBoarding" })
    public void Options_CP_MT_Shortcut (@Optional("NA") String platform){
        //******************************************************************************
        //****************************Navigate to My-Trip Option Page*******************
        //******************************************************************************/
        if(!platform.equals("NA")){
            ValidationUtil.validateTestStep("Starting Test Case ID TC91163 under SMOKE suite on " + platform +" Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE                   = "English";
        final String JOURNEY_TYPE               = "Flight";
        final String TRIP_TYPE                  = "roundtrip";
        final String ARR_AIRPORTS               = "AllLocation";
        final String DEP_AIRPORT_CODE           = "FLL";
        final String DEP_AIRPORTS               = "AllLocation";
        final String ARR_AIRPORT_CODE           = "LAS";
        final String DEP_DATE                   = "25";
        final String ARR_DATE                   = "30";
        final String ADULTS                     = "1";
        final String CHILDS                     = "0";
        final String INFANT_LAP                 = "0";
        final String INFANT_SEAT                = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		        = "Connecting";
        final String ARR_FLIGHT 	    	    = "Connecting";
        final String FARE_TYPE                  = "Standard";
        final String UPGRADE_FARE               = "BookIt";
        final String JOURNEY1                   = "Dep";
        final String JOURNEY2                   = "Ret";

        //Option Page Constant Value
        final String SELECTED_OPTIONS           = "CheckInOption:MobileFree";

        //Payment page Constant values
        final String TRAVEL_GURAD		        = "NotRequired";
        final String CARD_TYPE			        = "MasterCard";

        //MyTrip constant value
        final String MYTRIP_BUY_BAG_SEAT        = "Bags";
        final String MYTRIP_BAG                 = "Carry_1|Checked_3";
        final String MYTRIP_SEAT_POPUP          = "DontPurchase";
        final String MYTRIP_OPTION_VALUE        = "ShortCutBoarding";
        final String MYTRIP_PAYMENT_PAGE_URL    = "my-trips/payment";

//Step 1
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
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType(JOURNEY1, DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType(JOURNEY2, ARR_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_FARE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats page methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Option page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(SELECTED_OPTIONS);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //payment page Methods
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GURAD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        //Confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //login to checkIn Path
        pageMethodManager.getHomePageMethods().loginToMyTrip();

        //bag popup on reservation summary page
        pageMethodManager.getReservationSummaryPageMethods().buyBagsSeatsPassengerSection(MYTRIP_BUY_BAG_SEAT);

        //select bags on bags page
        pageMethodManager.getBagsPageMethods().selectDepartingBags(MYTRIP_BAG);

        //continue with bags
        pageObjectManager.getBagsPage().getContinueWithStandardBagsContainerContinueButton().click();

        //wait for page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //seat popup
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnSeatsPopup(MYTRIP_SEAT_POPUP);

        //******************************************************************************
        //****************************Validation on My-Trip Option Page*****************
        //******************************************************************************/
        pageMethodManager.getOptionsPageMethods().selectOptions(MYTRIP_OPTION_VALUE);

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //verify selected option on shortcut security
        ValidationUtil.validateTestStep("User verify Shortcut Boarding is Selected on My-Trip Option Page",
                pageObjectManager.getOptionsPage().getShortCutBoardingCardSelectedLabel().isDisplayed());

        //verify remove button for shortcut security
        ValidationUtil.validateTestStep("User verify Shortcut Boarding Remove button appear on  on My-Trip Option Page",
                pageObjectManager.getOptionsPage().getShortCutBoardingCardRemoveButton().isDisplayed());

        //continue tp purchase page
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //verify user reached to payment page
        ValidationUtil.validateTestStep("Page is navigated to My-Trip Payment Page", JSExecuteUtil.getWebPageURL(getDriver()),MYTRIP_PAYMENT_PAGE_URL);

        //verify selected option on payment page
        pageMethodManager.getPaymentPageMethods().verifyOptionSectionSelectedOptions(MYTRIP_OPTION_VALUE);
    }
}
