package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

//**********************************************************************************************
//Test CaseID: TC280860
//Title: Task 23096: 31391 575. E2E_FSMC_OW INT to DOM 1 ADT 1 INFT_Thru Flight_No Bags_No Seats_No Extras Web CI_Reservation Credit + credit Card
//Description: Validating FSMC member with an infant can select an OW INT Thru flight and  with a Res Credit and CC
//Created By : Gabriela
//Created On : 8-May-2019
//Reviewed By: Salim Ansari
//Reviewed On: 9-May-2019
//**********************************************************************************************

public class TC280860 extends BaseClass {

    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "FSMasterCard","OneWay" ,"Through" , "DomesticInternational" , "WithIn7Days" ,
            "Adult","InfantLap","BookIt" ,"NoBags" , "NoSeats", "OptionalUI" , "Visa" , "ReservationCredit","PaymentUI"})
    public void E2E_FSMC_OW_INT_to_DOM_1_ADT_1_INFT_Thru_Flight_No_Bags_No_Seats_No_Extras_Web_CI_Reservation_Credit__credit_Card(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280860 under Smoke Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE         = "English";
        final String LOGIN_ACCOUNT    = "FSMCEmail";
        final String JOURNEY_TYPE     = "Flight";
        final String TRIP_TYPE        = "OneWay";
        final String DEP_AIRPORTS     = "AllLocation";
        final String DEP_AIRPORT_CODE = "BOS";
        final String ARR_AIRPORTS     = "AllLocation";
        final String ARR_AIRPORT_CODE = "FLL";
        final String ARR_AIRPORT_CODE1= "CUN";
        final String DEP_DATE         = "5";
        final String ARR_DATE         = "NA";
        final String ADULTS           = "1";
        final String CHILD            = "0";
        final String INFANT_LAP       = "0";
        final String INFANT_LAP1      = "1";
        final String INFANT_SEAT      = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT       = "Through";
        final String FARE_TYPE        = "Standard";
        final String UPGRADE_VALUE    = "BookIt";

        //Bags Page Constant Value
        final String DEPARTING_BAGS   = "Carry_1|Checked_3";

        //Options page Constant Values
        final String OPTIONS_VALUE    = "CheckInOption:MobileFree";

        //payment page constant value
        final String CARD_TYPE        = "VisaCard";
        final String TRAVEL_GAURD     = "NotRequired";

        //Confirmation Page Constant Values
        final String BOOKING_STATUS   = "Confirmed";

        //open browser
        openBrowser(platform);

        /******************************************************************************
         **************************Reservation Credit Section**************************
         ******************************************************************************/

        //Create res credit and continue to the payment page
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        WaitUtil.untilTimeCompleted(1200);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEPARTING_BAGS);
        pageObjectManager.getBagsPage().getContinueWithStandardBagsContainerContinueButton().click();

        //Seats page methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options page methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment page methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GAURD);

        //confirmation page
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //Log Into My Trips path Method
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getHomePageMethods().loginToMyTrip();

        //Selecting Cancel Button
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(3000);
        pageObjectManager.getReservationSummaryPage().getCancelItineraryButton().click();

        //Canceling reservation
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getCancelReservationPage().getCancelReservationButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getCancelReservationPage().getReservationCancellationPopUpCancelReservationButton().click();

        //Receiving Reservation Credit
        WaitUtil.untilPageLoadComplete(getDriver());
        double resCredit  = Double.parseDouble(pageObjectManager.getCancelReservationPage().getCreditSummaryValesAmountText().getText().trim().replace("$",""));
        String confirmationCode = pageObjectManager.getCancelReservationPage().getConfirmationCodeText().getText();
        System.out.println(confirmationCode);
        System.out.println(resCredit);

        pageObjectManager.getCancelReservationPage().getContinueToHomePageButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHomePage().getSpiritLogoImage().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHomePage().getBookPathLink().click();

        WaitUtil.untilPageLoadComplete(getDriver());

        /******************************************************************************
         ******************************Test Cse Section********************************
         ******************************************************************************/
        //Home page Methods
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE1);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP1);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().selectOneWayInternationalPopup();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        //Flight Availability Page Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Method
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating Check-in options are not available du the infant",
                pageObjectManager.getOptionsPage().getCheckInOptionCardPanel().getAttribute("class"),"disabled");


        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //*******************************************************************
        //***********************Payment Page Methods************************
        //*******************************************************************
        WaitUtil.untilPageLoadComplete(getDriver());
        //Saving the Total Due in a variable for future comparision
        String Total = pageObjectManager.getPaymentPage().getTotalDuePriceText().getText();
        String TotalSub = Total.replace("$" , "");
        String TotalSub1 = TotalSub.replace(",", "");
        double TotalDouble = Double.parseDouble(TotalSub1);
        System.out.println("TotalDouble" + TotalDouble);

        //click on Redeem Vouchers link
        pageObjectManager.getPaymentPage().getRedeemVoucherOrCreditLink().click();

        //Input valid Res Credit
        WaitUtil.untilTimeCompleted(3000);
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getPaymentPage().getRedeemVoucherConfirmationCodeTextBox().sendKeys(confirmationCode);
        pageObjectManager.getPaymentPage().getRedeemVoucherGoButton().click();
        pageObjectManager.getPaymentPage().getRedeemVoucherAmountToSpendTextBox().clear();
        pageObjectManager.getPaymentPage().getRedeemVoucherAmountToSpendTextBox().sendKeys("50");
        pageObjectManager.getPaymentPage().getRedeemVoucherApplyCreditButton().click();

        //Opening Total Due BreakDown for prices validation
        WaitUtil.untilTimeCompleted(3000);
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();

        //Opening Voucher & Credits BreakDown for prices validation
        WaitUtil.untilTimeCompleted(3000);
        pageObjectManager.getPaymentPage().getTotalDueVoucherAndCreditChevronLink().click();
        List<WebElement> creditAmount = pageObjectManager.getPaymentPage().getTotalDueVouchersAndCreditPriceListText();

        //Converting the resCredit price in double for calculation
        WaitUtil.untilTimeCompleted(3000);
        double resCreditAmount = Double.parseDouble(creditAmount.get(0).getText().replace("-$",""));
        System.out.println("resCreditAmount " + resCreditAmount);

        //Converting voucher and resCredit displayed to validate with the calculated one
        WaitUtil.untilTimeCompleted(3000);
        String vCTotal = pageObjectManager.getPaymentPage().getTotalDueVoucherAndCreditPriceText().getText().replace("-$", "");
        double vCAmount = Double.parseDouble(vCTotal);
        System.out.println("vCAmount " + vCAmount);

        ValidationUtil.validateTestStep("Validating the right discount amount is displayed",
                vCAmount == resCreditAmount);

        //Converting the new total amount to pay in a double for comparision
        String totalTemp = pageObjectManager.getPaymentPage().getTotalDuePriceText().getText().replace("$", "");
        String totalTempSub = totalTemp.replace(",", "");
        double totalEnd = Double.parseDouble(totalTempSub);
        System.out.println(totalEnd);

        ValidationUtil.validateTestStep("Validating the right discount was applied ",
                TotalDouble - vCAmount == totalEnd);

        //enter credit card
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GAURD);

        //Confirmation Code
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText().contains(BOOKING_STATUS));
    }
}