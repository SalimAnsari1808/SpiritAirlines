package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC91098
//Description : CP_CI_Ancillaries_Modify Seats After Check-In_Emergency Exit
//Created By : Anthony Cardona
//Created On : 22-JUL-2019
//Reviewed By: Salim Ansari
//Reviewed On: 24-JUL-2019
//**********************************************************************************************

public class TC91098 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"CheckIn" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "NonStop" , "BookIt" , "NoBags" , "Premium" , "CheckInOptions" , "MasterCard" , "SeatsUI" , "AddEditSeats","BoardingPassUI"})
    public void CP_CI_Ancillaries_Modify_Seats_After_Check_In_Emergency_Exit(@Optional("NA") String platform) {

        /******************************************************************************
         ***********************Navigate to Payment Page*******************************
         ******************************************************************************/

        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91098 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
//Step 1: Create booking within 24 hours with seats already checked in
        //Home Page Constant Values
        final String LANGUAGE               = "English";
        final String JOURNEY_TYPE           = "Flight";
        final String TRIP_TYPE              = "OneWay";
        final String DEP_AIRPORTS           = "AllLocation";
        final String DEP_AIRPORT_CODE       = "FLL";
        final String ARR_AIRPORTS           = "AllLocation";
        final String ARR_AIRPORT_CODE       = "ATL";
        final String DEP_DATE               = "0";
        final String ARR_DATE               = "NA";
        final String ADULTS                 = "1";
        final String CHILDS                 = "0";
        final String INFANT_LAP             = "0";
        final String INFANT_SEAT            = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT             = "Nonstop";
        final String FARE_TYPE              = "Standard";
        final String UPGRADE_FARE           = "BookIt";

        //seat page constant value
        final String SEAT                   = "Premium";

        //Options Page constant values
        final String OPTION_VALUE           = "CheckInOption:MobileFree";

        //Payment Page Constant values
        final String TRAVEL_GUARD           = "NotRequired";
        final String VALID_CARD_TYPE        = "MasterCard";

        //Seats pop up

        final String CHECK_IN_BAGS          = "DontPurchase";
        final String CHECK_IN_PAYMENT_URL   = "spirit.com/check-in/payment";

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
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_FARE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //wait until Bags page appear on web
        WaitUtil.untilPageLoadComplete(getDriver());

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //seats
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(FARE_TYPE);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //options
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment Page Method
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(VALID_CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        //Confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getHomePageMethods().loginToCheckIn();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getReservationSummaryPageMethods().clickCheckInAndGetBoardingPass();
        //No Bags
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);

        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSaveOnBagsPopup(CHECK_IN_BAGS);


        WaitUtil.untilPageLoadComplete(getDriver());

        /*********************************Options Page Methods*************************************************/
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);
        pageMethodManager.getReservationSummaryPageMethods().acceptRejectHazardousMaterialPopup("Accept");
        pageMethodManager.getReservationSummaryPageMethods().printEmailYourBoardingPassPopup("Print","NA");

        WaitUtil.untilPageLoadComplete(getDriver());

//Step 2: Begin check in process while already checked in
        pageMethodManager.getHomePageMethods().loginToCheckIn();

//Step 3: Click on Edit Seats
        pageObjectManager.getReservationSummaryPage().getPassengerSectionEditSeatsButton().get(0).click();
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);

//Step 4: upgrade passenger seat to big front seat
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(SEAT);
        String seatPrice = pageObjectManager.getSeatsPage().getSeatsTotalPriceText().getText();
        String seatNumber = pageObjectManager.getSeatsPage().getPassengerSeatText().get(0).getText();

        pageMethodManager.getSeatsPageMethods().continueWithSeats();
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(2000);

//Step 5: Continue to the payment page
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnBagsPopup(CHECK_IN_BAGS);
        WaitUtil.untilTimeCompleted(1200);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//Step 7: Validate that the user lands on the payment page
        ValidationUtil.validateTestStep("The user lands on the payment page",
                getDriver().getCurrentUrl(),CHECK_IN_PAYMENT_URL);
        ValidationUtil.validateTestStep("The amount charged is correct" ,
                pageObjectManager.getPaymentPage().getTotalDuePriceText().getText(), seatPrice);

        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(VALID_CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        WaitUtil.untilPageLoadComplete(getDriver(),(long)300);

//Step 8: Click to print boarding pass
        pageMethodManager.getReservationSummaryPageMethods().acceptRejectHazardousMaterialPopup("Accept");
        pageMethodManager.getReservationSummaryPageMethods().printEmailYourBoardingPassPopup("Print","NA");

        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);

        //IF Big Front seat number is A or F, boarding pass will show " Window "
        //IF Big Front seat number is C or D, boarding pass will show " Aisle "
        if(seatNumber.contains("A") || seatNumber.contains("F")) {
            seatNumber = seatNumber + "\nWindow";
        }

        else if(seatNumber.contains("C") || seatNumber.contains("D")) {
            seatNumber = seatNumber + "\nAisle";
        }

        ValidationUtil.validateTestStep("The New seat number is displayed correctly" , seatNumber,
                pageObjectManager.getBoardingPassPage().getSeatNumberText().get(0).getText().trim());
    }
}