package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.*;
import com.spirit.dataType.PassengerInfoData;
import com.spirit.enums.Context;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.*;
import org.testng.annotations.*;
import org.testng.annotations.Optional;

//**********************************************************************************************
//Test Case ID: TC90977
//Test Name:  $9FC Booking _CP_CI_Flight Availability_ $9FC No thanks button
//Description:
//Created By : Sunny Sok
//Created On : 26-Mar-2019
//Reviewed By: Salim Ansari
//Reviewed On: 30-Mar-2019
//**********************************************************************************************

public class TC90977 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"CheckIn" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "NonStop" , "BookIt" , "NoBags" , "NoSeats","CheckInOptions" ,
            "Visa" , "ChangeFlight","NineDFC","PaymentUI"})
    public void $9FC_Booking_CP_CI_Flight_Availability_$9FC_No_thanks_button(@Optional("NA") String platform) {
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90977 under SMOKE Suite on " + platform + " Browser", true);
        }

        //declare constant used in Navigation
        final int FIRST_PAX             = 0;

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LGA";
        final String DEP_DATE           = "0";
        final String ARR_DATE           = "NA";
        final String ADULTS			    = "1";
        final String CHILDREN			= "0";
        final String INFANT_LAP		    = "0";
        final String INFANT_SEAT	    = "0";

        //Flight Availability Page Constant Values
        final String SORT_BY            = "Departure";
        final String DEP_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page Constant Values
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String CARD_TYPE          = "VisaCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //Check-In Flight Availability Page Constant Values
        final String CHECK_IN_DEP_FARE  = "9DFC";

        //STEP--1
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
        pageMethodManager.getFlightAvailabilityMethods().selectSortingOption("Dep", SORT_BY);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        //Passenger Info Methods
        //get passenger First and last name
        PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("NineFCMember");

        //fill first passenger detail
        WaitUtil.untilPageLoadComplete(getDriver());
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPassengerInfoPage().getAdultTitleListDropDown().get(FIRST_PAX),passengerInfoData.title);
        pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(FIRST_PAX).sendKeys(passengerInfoData.firstName);
        pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(FIRST_PAX).sendKeys(passengerInfoData.lastName);
        pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(FIRST_PAX).sendKeys(passengerInfoData.dob);
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //option Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //payment Page Methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //***********************************Start of Check-In Path******************************/
        //Home Page Method
        pageMethodManager.getHomePageMethods().loginToCheckIn();

        //Reservation Summary Page
        pageObjectManager.getReservationSummaryPage().getFlightSectionChangeFlightButton().click();

//        //wait till pop up appear on page
//        WaitUtil.untilPageLoadComplete(getDriver());
//
//        //click on Departing edit box
//        pageObjectManager.getReservationSummaryPage().getChangeFlightPopupDepEditLabel().click();
        pageMethodManager.getReservationSummaryPageMethods().changeFlightOnChangeFlightPopup("Dep","NA","NA","1");

        //click on continue button
        pageObjectManager.getReservationSummaryPage().getChangeFlightPopupContinueButton().click();

        //Flight Availability common Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightCheapCostlyType("dep",CHECK_IN_DEP_FARE,"Costly");

        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getFlightAvailabilityPage().getMemberFareButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1000);
//        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare("Member");

        //continue with Standard Fare
        pageObjectManager.getPassengerInfoPage().get9FCUpsellContinueWithStandardFareButton().click();
//
//        //Wait for Bags pop up
//        WaitUtil.untilPageLoadComplete(getDriver());
//
//        //wait for 2 sec
//        WaitUtil.untilTimeCompleted(2000);

//        //click on dont purchase Bags
//        pageObjectManager.getReservationSummaryPage().getBagsPopupDontPurchaseMyBagsButton().click();
//
//        //Wait for Bags pop up
//        WaitUtil.untilPageLoadComplete(getDriver());
//
//        //wait for 2 sec
//        WaitUtil.untilTimeCompleted(2000);
//
//        //Click on dont purchase seats
//        pageObjectManager.getReservationSummaryPage().getSeatsPopupDontPurchaseMySeatsButton().click();

        //do not select Bag
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnBagsPopup("DontPurchase");

        //do not select Seat
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnSeatsPopup("DontPurchase");

        //Wait for Bags pop up
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //Check In Option method
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        WaitUtil.untilPageLoadComplete(getDriver());

        //**********************Validation Check-In Payment Page************************/
        //Verify Standard flight prices
        ValidationUtil.validateTestStep("Verify that the Standard Flight Prices appear on Payment Page",
                Double.parseDouble(scenarioContext.getContext(Context.AVAILABILITY_FS_TOTAL_PRICE).toString())==Double.parseDouble(pageObjectManager.getPaymentPage().getTotalDuePriceText().getText().replace("$","")));

       //payment Page Methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //wait till page is load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //declare constant used in Validation
        final String CONFIRMATION_PASS_URL = "check-in/confirmation";

        //validate Boarding Pass is displayed
        ValidationUtil.validateTestStep("Verify user reached to Boarding Pass Page",
                getDriver().getCurrentUrl(),CONFIRMATION_PASS_URL);

    }
}
