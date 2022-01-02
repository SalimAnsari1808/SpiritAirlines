package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.*;
import org.testng.annotations.Optional;

//**********************************************************************************************
//Test Case ID: TC91169
//Title       : CP_CI_Seats Modal_Add Seats
//Description : Validate the functionality of the Seats Modal on the check In Path
//Created By  : Anthony Cardona
//Created On  : 04-Apr-2019
//Reviewed By : Salim Ansari
//Reviewed On : 17-Apr-2019
//**********************************************************************************************
public class TC91169 extends BaseClass {

    @Parameters({"platform"})
    @Test (groups = {"CheckIn" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "NonStop" , "BookIt" ,
            "NoBags" , "CheckInOptions" , "MasterCard" , "Standard" , "AddEditSeats","ReservationUI"})
    public void CP_CI_Seats_Modal_Add_Seats(@Optional("NA") String platform) {

        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91169 under SMOKE Suite on " + platform + " Browser", true);
        }

        //open browser and redirect tot the application
        openBrowser(platform);

//Step 1
        /******************************************************************************
         ******************************Navigate to Check-In Page***********************
         ******************************************************************************/
        //Home Page Constant Values
        final String LANGUAGE               = "English";
        final String JOURNEY_TYPE           = "Flight";
        final String TRIP_TYPE 		    	= "Oneway";
        final String DEP_AIRPORTS 		    = "AllLocation";
        final String DEP_AIRPORT_CODE 	    = "FLL";
        final String ARR_AIRPORTS 	  	    = "AllLocation";
        final String ARR_AIRPORT_CODE  	    = "LGA";
        final String DEP_DATE 	    		= "0";
        final String ARR_DATE 	    		= "NA";
        final String ADULTS 		       	= "1";
        final String CHILDREN		      	= "0";
        final String INFANT_LAP 	     	= "0";
        final String INFANT_SEAT	     	= "0";

        //Flight Availability Page Constant Values
        final String SORTING_TYPE           = "Departure";
        final String DEP_FLIGHT 	    	= "Nonstop";
        final String JOURNEY                = "Dep";
        final String FARE_TYPE 	    		= "Standard";
        final String UPGRADE_VALUE 	  	    = "BookIt";

        //Options Page Constant Value
        final String OPTIONS_VALUE          = "CheckInOption:MobileFree";

        //Payment Page Constant Value
        final String CARD_TYPE              = "MasterCard";
        final String TRAVEL_GAURD           = "NotRequired";


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
        //pageMethodManager.getFlightAvailabilityMethods().selectSortingOption("Dep",SORTING_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType(JOURNEY, DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats page methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options page methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment page methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GAURD);

        //confirmation page
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
//Step 2
        //click on check-in
        pageMethodManager.getHomePageMethods().loginToCheckIn();

//Step 3
        //Check In button
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getReservationSummaryPageMethods().clickCheckInAndGetBoardingPass();

//Step 4
        /******************************************************************************
         ******************************Validation on Check-In Page*********************
         ******************************************************************************/
        //declare constant used in validation
        final String MYTRIP_OPTION_URL      = "extras";
        final String MYTRIP_SEATS_URL       = "seats";
        final String MYTRIP_BOARDING_URL    = "boarding-pass";
        final String CHOOSE_SEAT_SUB_HEADER = "Don't forget to pick your seats! Or, if you're tired of making decisions, get some random ones at check-in.";
        final String SAVE_ON_BAGS_SUB_HEADER= "Best time to buy bags is now to get the lowest price.";

        //reservation Page constant
        final String MYTRIP_BAGS_POPUP      = "DontPurchase";
        final String MYTRIP_SEATS_POPUP     = "DontPurchase";
        final String MYTRIP_BUY_SEAT_POPUP  = "Purchase";
        final String MYTRIP_HAZMAT_POPUP    = "Accept";
        final String MYTRIP_BOARDING_POPUP  = "Print";

        //mytrip seat page constant
        //Seat page Constant Value
        final String MYTRIP_DEP_SEAT        = "Standard";

        //validate Bags Up-sell
        WaitUtil.untilTimeCompleted(2000);

        ValidationUtil.validateTestStep("The Bag Popup header is correctly displayed" , pageObjectManager.getReservationSummaryPage().getBagsPopupHeaderText().isDisplayed());

        ValidationUtil.validateTestStep("The Bag Popup Nope I Am Good Button is correctly displayed" , pageObjectManager.getReservationSummaryPage().getSaveOnBagsPopupNopeIAmGoodButton().isDisplayed());

        ValidationUtil.validateTestStep("The Bag Popup Buy Bag Now Button is correctly displayed" , pageObjectManager.getReservationSummaryPage().getSaveOnBagsPopupBuyBagsNowButton().isDisplayed());

        ValidationUtil.validateTestStep("The Bag Popup Close Button is correctly displayed" , pageObjectManager.getReservationSummaryPage().getSaveOnBagsPopupCloseButton().isDisplayed());

        ValidationUtil.validateTestStep("The Bags Upsell Modal is correctly displayed" , pageObjectManager.getReservationSummaryPage().getSaveOnBagsPopupSubHeaderText().getText().trim(),SAVE_ON_BAGS_SUB_HEADER);

        //Select no to Bags upsell
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSaveOnBagsPopup(MYTRIP_BAGS_POPUP);

        ValidationUtil.validateTestStep("The popup choose your seat header is displayed" , pageObjectManager.getReservationSummaryPage().getChooseYourSeatHeaderText().isDisplayed());

        ValidationUtil.validateTestStep("The popup choose your seat get random button is correctly displayed" , pageObjectManager.getReservationSummaryPage().getChooseYourSeatGetRandomSeatButton().isDisplayed());

        ValidationUtil.validateTestStep("The popup choose your seat Select Seat Button is correctly displayed" , pageObjectManager.getReservationSummaryPage().getChooseYourSeatSelectSeatButton().isDisplayed());

        ValidationUtil.validateTestStep("The popup choose your seat Close Button is correctly displayed" , pageObjectManager.getReservationSummaryPage().getChooseYourSeatCloseButton().isDisplayed());

        ValidationUtil.validateTestStep("The popup choose your seat Sub Header Text is correctly displayed" ,pageObjectManager.getReservationSummaryPage().getChooseYourSeatSubHeaderText().getText().trim(),CHOOSE_SEAT_SUB_HEADER);

//Step 5
        //The user selects  Ger Random seat and redirected to the correct page and -Restart Booking process
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSelectYourSeatPopup(MYTRIP_SEATS_POPUP);


        ValidationUtil.validateTestStep("The user is correctly redirected to the extras page" , getDriver().getCurrentUrl(),MYTRIP_OPTION_URL);
        pageObjectManager.getOptionsPage().getCancelButtonOnCheckInPathButton().click();

        WaitUtil.untilTimeCompleted(2000);

//Step 6
        //restart check in process and select Choose seat on the seats modal //validate seat on boardingPass
        pageMethodManager.getReservationSummaryPageMethods().clickCheckInAndGetBoardingPass();

        //Select no to Bags upsell
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSaveOnBagsPopup(MYTRIP_BAGS_POPUP);

        //select seat on popup
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSelectYourSeatPopup(MYTRIP_BUY_SEAT_POPUP);

        ValidationUtil.validateTestStep("The user is correctly redirected to the Seats page" , getDriver().getCurrentUrl(),MYTRIP_SEATS_URL);

        //Select Seat
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(MYTRIP_DEP_SEAT);
        String seatSelected = pageObjectManager.getSeatsPage().getPassengerSeatText().get(0).getText().trim();
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //continue on Extras page
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GAURD);

        //complete payment
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        //Accept and print boarding pass
        pageMethodManager.getReservationSummaryPageMethods().acceptRejectHazardousMaterialPopup(MYTRIP_HAZMAT_POPUP);

        //print boarding pass
        pageMethodManager.getReservationSummaryPageMethods().printEmailYourBoardingPassPopup(MYTRIP_BOARDING_POPUP,"NA");

        //validate user taken to the boarding pass page
        WaitUtil.untilPageLoadComplete(getDriver());

        WaitUtil.untilTimeCompleted(2000);

        TestUtil.closeBoardingPassPrintPopup();

        ValidationUtil.validateTestStep("user redirected to the Boarding pass page", getDriver().getCurrentUrl(),MYTRIP_BOARDING_URL);

//        WaitUtil.untilPageLoadComplete(getDriver());

        //validate seat number on boarding pass page
        ValidationUtil.validateTestStep("The seat number is correctly displayed on the boarding pass"
                , pageObjectManager.getBoardingPassPage().getSeatNumberText().get(0).getText(),seatSelected);
    }
}