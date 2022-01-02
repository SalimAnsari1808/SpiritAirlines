package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.*;
import com.spirit.dataType.*;
import com.spirit.managers.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;
import org.testng.annotations.Optional;

import java.awt.event.KeyEvent;
import java.util.*;

//**********************************************************************************************
// TODO: [IN:24115]: CP: CI: Online Check In Page: "Edit" Link is displayed instead "Add" link on Passenger section for KTN and REDRESS fields when the information wasn't is intro before
//Test Case ID: TC90900
//Test Name:  CP_CI_Add KTN
//Description:
//Created By : Sunny Sok
//Created On : 04-APR-2019
//Reviewed By: Salim Ansari
//Reviewed On: 15-Apr-2019
//**********************************************************************************************

public class TC90900 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"CheckIn" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "NonStop" ,"BookIt" ,
            "MandatoryFields" , "NoBags" , "NoSeats" , "CheckInOptions" , "MasterCard" ,"AddEditPassengerInfo", "ActiveBug","BoardingPassUI","ReservationUI"})
    public void CP_CI_Add_KTN(@Optional("NA") String platform) {
        /******************************************************************************
         **********************Navigate to Reservation Summary Page********************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90900 under SMOKE Suite on " + platform + " Browser", true);
        }

        //declare constant used in Navigation
        final int FIRST_PAX             = 0;

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "BWI";
        final String DEP_DATE           = "0";
        final String ARR_DATE           = "5";
        final String ADULTS             = "1";
        final String CHILDS             = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String ARR_Flight         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADEVALUE       = "BookIt";

        //Options Page constant values
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //Payment Page Constant Values

        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //open browser
        openBrowser(platform);

        //--Step 1
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
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADEVALUE);

        WaitUtil.untilPageLoadComplete(getDriver());
        //Passenger Info Methods
        //get passenger First and last name
        PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("Traveler1");
        TestUtil.selectDropDownUsingValue(pageObjectManager.getPassengerInfoPage().getAdultTitleListDropDown().get(FIRST_PAX),"MR");
        pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(FIRST_PAX).sendKeys(passengerInfoData.firstName);
        pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(FIRST_PAX).sendKeys(passengerInfoData.lastName);
        pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(FIRST_PAX).sendKeys(passengerInfoData.dob);
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
        WaitUtil.untilPageLoadComplete(getDriver());

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
        WaitUtil.untilPageLoadComplete(getDriver());

        //option Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //payment Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        pageMethodManager.getHomePageMethods().loginToCheckIn();

        /******************************************************************************
         ***********************Validation Checin page****************************
         ******************************************************************************/
        //--Step 2
//        //declare constant used in Validation
        final String Checkin_URL = "check-in/reservation-summary";
//
//        //validate Boarding Pass is appear
        ValidationUtil.validateTestStep("Verify user reached to Checkin Page",
                getDriver().getCurrentUrl().contains(Checkin_URL));

        //--Step 3
        //Reservation Page methods
        // TODO: [IN:24115]: CP: CI: Online Check In Page: "Edit" Link is displayed instead "Add" link on Passenger section for KTN and REDRESS fields when the information wasn't is intro before
        ValidationUtil.validateTestStep("Verify KTN Edit button is displayed",
                pageObjectManager.getReservationSummaryPage().getPassengerSectionKTNAddButton().get(0).isDisplayed());

        pageObjectManager.getReservationSummaryPage().getPassengerSectionKTNAddButton().get(0).click();

        ValidationUtil.validateTestStep("Verify text box opened after clicking KTN Edit button",
                pageObjectManager.getReservationSummaryPage().getPassengerSectionKTNTextBox().get(0).isDisplayed());

        //--Step 4
        pageObjectManager.getReservationSummaryPage().getPassengerSectionKTNTextBox().get(0).sendKeys(passengerInfoData.ktNumber);
        pageObjectManager.getReservationSummaryPage().getPassengerSectionKTNSaveButton().get(0).click();

        WaitUtil.untilPageLoadComplete(getDriver());

        //--Step 5
        ValidationUtil.validateTestStep("User Verify Check in and Print boarding pass button is displayed",
                pageObjectManager.getReservationSummaryPage().getCheckInAndGetBoardingPassButton().get(0).isDisplayed());

        //--Step6
        pageMethodManager.getReservationSummaryPageMethods().clickCheckInAndGetBoardingPass();
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSaveOnBagsPopup("DontPurchase");
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSelectYourSeatPopup("DontPurchase");
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseRentACarPopup("DontPurchase");

        WaitUtil.untilPageLoadComplete(getDriver());

        pageObjectManager.getOptionsPage().getContinueToPurchaseButton().click();
//
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(3000);

        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup("NotRequired");

        WaitUtil.untilPageLoadComplete(getDriver());

        pageMethodManager.getReservationSummaryPageMethods().acceptRejectHazardousMaterialPopup("Accept");
        pageMethodManager.getReservationSummaryPageMethods().printEmailYourBoardingPassPopup("Print","NA");

        /******************************************************************************
         ***********************Validation on Boarding pass****************************
         ******************************************************************************/
        //--Step 7
        //declare constant used in Validation
        final String BOARDING_PASS_URL = "check-in/boarding-pass";

        //wait for pagfe load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //close print popup
        TestUtil.closeBoardingPassPrintPopup();

        //validate Boarding Pass is appear
        ValidationUtil.validateTestStep("Verify user reached to Boarding Pass Page",
                getDriver().getCurrentUrl().contains(BOARDING_PASS_URL));

        ValidationUtil.validateTestStep("User Verify TSA PRECHECK Image appear on Boarding pass",
                TestUtil.verifyLinks(pageObjectManager.getBoardingPassPage().getBoardingPassTSAPrecheckImage().get(0).getAttribute("src")));

    }
}