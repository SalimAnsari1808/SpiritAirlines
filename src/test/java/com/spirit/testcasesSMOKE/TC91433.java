package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC91433
//Title       : CP_BP_Payment Page_Travel Guard_Res Credit_not used for TG purchase
//Description : Validate that Reservation credit cannot be used to cover TG price
//Created By  : Anthony Cardona
//Created On  : 22-Apr-2019
//Reviewed By : Salim Ansari
//Reviewed On : 25-Apr-2019
//**********************************************************************************************

public class TC91433 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "Connecting" , "BookIt" ,
            "NoBags" , "NoSeats" , "MasterCard" , "TravelInsuranceBlock" , "ReservationCredit" , "PaymentUI", "CheckInOptions"})
    public void CP_BP_Payment_Page_Travel_Guard_Res_Credit_not_used_for_TG_purchase(@Optional("NA") String platform) {

        /******************************************************************************
         ************Navigate to Confirmation Page to Cancel Reservation Page**********
         ******************************************************************************/
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91433 under SMOKE Suite on " + platform + " Browser", true);
        }
        //Reservation Credit Path Constant variables
        final String LANGUAGE                           = "English";
        final String JOURNEY_TYPE                       = "Flight";
        final String TRIP_TYPE                          = "Oneway";
        final String DEP_AIRPORTS                       = "AllLocation";
        final String DEP_AIRPORT_CODE                   = "FLL";
        final String ARR_AIRPORTS                       = "AllLocation";
        final String ARR_AIRPORT_CODE                   = "LAX";
        final String DEP_DATE                           = "5";
        final String ARR_DATE                           = "NA";
        final String ADULTS                             = "1";
        final String CHILDREN                           = "0";
        final String INFANT_LAP                         = "0";
        final String INFANT_SEAT                        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT                         = "Connecting";
        final String FARE_TYPE                          = "Standard";
        final String UPGRADE_VALUE                      = "BookIt";
        final String JOURNEY                            = "Dep";

        //Bags Page Constant Value
        final String DEPARTING_BAGS                     = "Carry_1|Checked_3";
        final String MEMBER_FARE_TYPE                   = "Standard";

        //Options Page Constant Value
        final String OPTIONS_VALUE                      = "CheckInOption:MobileFree";

        //payment page constant value
        final String CARD_TYPE                          = "MasterCard";
        final String TRAVEL_GUARD                       = "Required";
        final String TRAVEL_GUARD_RESERVATION_CREDIT    = "Notrequired";

        //confirmation page constant value
        final String CONFIRMATION_PAGE_URL              = "book/confirmation";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
//Step 1
        //Create res credit and continue to the payment page
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType(JOURNEY, DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEPARTING_BAGS);
        pageMethodManager.getBagsPageMethods().selectBagsFare(MEMBER_FARE_TYPE);
        //Seats page methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
        //Options page methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
        //Payment page methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD_RESERVATION_CREDIT);
        //confirmation page
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        WaitUtil.untilPageLoadComplete(getDriver());

        pageMethodManager.getHomePageMethods().loginToMyTrip();
        pageObjectManager.getReservationSummaryPage().getCancelItineraryButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getCancelReservationPage().getCancelReservationButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getCancelReservationPage().getReservationCancellationPopUpCancelReservationButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        double resCredit  = Double.parseDouble(pageObjectManager.getCancelReservationPage().getCreditSummaryValesAmountText().getText().trim().replace("$",""));
        String confirmationCode = pageObjectManager.getCancelReservationPage().getConfirmationCodeText().getText();

//        System.out.println(resCredit);

        pageObjectManager.getCancelReservationPage().getContinueToHomePageButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHomePage().getSpiritLogoImage().click();
        pageObjectManager.getHomePage().getBookPathLink().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        /******************************************************************************
         ***************************Navigate to Payment Page***************************
         ******************************************************************************/

        //Home Page Methods
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
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

//Step 2

//Step 3
        //select yes on travel guard
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);

//Step 4
        //on payment page click on redeem voucher
        pageObjectManager.getPaymentPage().getRedeemVoucherOrCreditLink().click();

//Step 5

        pageObjectManager.getPaymentPage().getRedeemVoucherConfirmationCodeTextBox().sendKeys(confirmationCode);
        pageObjectManager.getPaymentPage().getRedeemVoucherGoButton().click();
        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getPaymentPage().getRedeemVoucherApplyCreditButton().click();
        WaitUtil.untilTimeCompleted(1200);

//Step 6
        //Click on the total drop down
        WaitUtil.untilTimeCompleted(2000);
        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();
        WaitUtil.untilTimeCompleted(2000);
        String totalDue_WITH_TG = pageObjectManager.getPaymentPage().getTotalDuePriceText().getText().trim().replace("$", "");
        String amountOfTravelInsurance = pageObjectManager.getPaymentPage().getTotalDueTravelInsurancePriceText().getText().replace("$", "");
        //validate deduction on drop down
        ValidationUtil.validateTestStep("The Deduction is present on the Total Due Drop Down" , totalDue_WITH_TG,amountOfTravelInsurance);

//Step 7
        //input CC information
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

//Step 8
        //Validate the user is taken to the confirmation page
        ValidationUtil.validateTestStep("The user is correctly taken to the payment page" , getDriver().getCurrentUrl(),CONFIRMATION_PAGE_URL);
    }
}