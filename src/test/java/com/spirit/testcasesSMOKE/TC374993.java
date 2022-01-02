package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test CaseID: TC374993
//Title      : CP_BP_Payment Page_Voucher_Name Match Error
//Description: Validate error displaying after using Voucher number of other user
//Created By : Kartik Chauhan
//Created On : 15-May-2019
//Reviewed By: Salim Ansari
//Reviewed On: 15-May-2019
//**********************************************************************************************

public class TC374993 extends BaseClass {

    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "Within21Days" , "Adult" , "FreeSpirit", "NineDFC" , "NonStop" , "Through" , "BookIt" ,
            "NoBags" , "NoSeats" , "CheckInOptions" , "Voucher" , "MasterCard" , "PaymentUI"})
    public void  CP_BP_Payment_Page_Voucher_Name_Match_Error(@Optional("NA") String platform) {
        //****************************Navigate to Bags Page*****************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC374993 under Smoke Suite on " + platform + " Browser", true);
        }

        //Reservation Credit Path Constant variables
        final String LANGUAGE                           = "English";
        final String LOGIN_ACCOUNT                      = "FSEmail";
        final String LOGIN_ACCOUNT2                     = "NineDFCEmail";
        final String JOURNEY_TYPE                       = "Flight";
        final String TRIP_TYPE                          = "OneWay";
        final String TRIP_TYPE2                         = "RoundTrip";
        final String DEP_AIRPORTS                       = "AllLocation";
        final String DEP_AIRPORT_CODE                   = "FLL";
        final String ARR_AIRPORTS                       = "AllLocation";
        final String ARR_AIRPORT_CODE                   = "BOS";
        final String ARR_AIRPORT_CODE1                  = "ACY";
        final String DEP_DATE                           = "5";
        final String ARR_DATE                           = "NA";
        final String DEP_DATE1                          = "6";
        final String DEP_DATE2                          = "15";
        final String ARR_DATE2                          = "19";
        final String ADULTS                             = "1";
        final String CHILD                              = "0";
        final String CHILDREN                           = "0";
        final String INFANT_LAP                         = "0";
        final String INFANT_SEAT                        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT                         = "NonStop";
        final String FARE_TYPE                          = "Standard";
        final String FARE_TYPE1                         = "Member";
        final String RET_FLIGHT                         = "Through";
        final String UPGRADE_VALUE                      = "BookIt";

        //Options Page Constant Value
        final String OPTIONS_VALUE                      = "CheckInOption:MobileFree";

        //payment page constant value
        final String CARD_TYPE                          = "MasterCard";
        final String TRAVEL_GAURD                       = "NotRequired";

//STEP--1
        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();

        //******************************Voucher Section*********************************/
        //Home Page Methods
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE1);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE1, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

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
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        WaitUtil.untilPageLoadComplete(getDriver());

        WaitUtil.untilTimeCompleted(2000);
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        WaitUtil.untilPageLoadComplete(getDriver());

        //My Trips Path
        pageMethodManager.getHomePageMethods().loginToMyTrip();
        pageMethodManager.getReservationSummaryPageMethods().createVoucherReservationCredit();

        //***************************Navigate to Payment Page****************************/
        //Home Page Methods
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().logoutFromApplication();
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT2);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE2);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE2, ARR_DATE2);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE1);
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

//Step--2& STEP--3
//check redeem reservation credit link
        if(pageObjectManager.getPaymentPage().getRedeemVoucherOrCreditChevronLink().getAttribute("style").contains("-180deg")){
            pageObjectManager.getPaymentPage().getRedeemVoucherOrCreditChevronLink().click();
        }

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //wait for page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());
//STEP--4
        //emter voucher nukber
        pageObjectManager.getPaymentPage().getVoucherNumberTextBox().sendKeys(scenarioContext.getContext(Context.RESERVATION_VOUCHER_CODE).toString());


        //click on Go button
        pageObjectManager.getPaymentPage().getVoucherNumberGoButton().click();

        //put wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        final String ID_MISMATCH_ERROR = "The CustomerID on the voucher does not match with either the customer or any of the contact";

        ValidationUtil.validateTestStep("Customer Id on voucher does not match with customer",
                pageObjectManager.getCommon().getIBlockVerbiageText().getText(),ID_MISMATCH_ERROR);
    }
}