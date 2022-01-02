package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.EmailUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

// **********************************************************************************************
// TestCase :TC91100
// Test Title: BoardingPass_CP_CI_Email delivery option check one box
// Description: Validate that user gets Email Boarding pass
// Created By : Anthony Cardona
// Created On : 16-May-2019
// Reviewed By: Salim Ansari
// Reviewed On: 19-May-2019
// **********************************************************************************************
public class TC91100 extends BaseClass {

    @Parameters({"platform"})
    @Test (groups = {"CheckIn" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "Connecting" ,"BookIt" ,
            "NoBags" , "NoSeats" , "CheckInOptions" , "TravelInsuranceBlock" , "MasterCard","Email","BoardingPassUI"})
    public void BoardingPass_CP_CI_Email_delivery_option_check_one_box(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91100 under SMOKE suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "0";
        final String ARR_DATE           = "NA";
        final String ADULTS             = "1";
        final String CHILD             = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String SORTING_TYPE       = "Departure";
        final String DEP_FLIGHT         = "Connecting";
        final String FARE_TYPE          = "standard";
        final String UPGRADE_TYPE       = "BookIt";

        //Option Page Constant Values
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //Payment page Constant values
        final String TRAVEL_GUARD       = "NotRequired";
        final String CARD_TYPE          = "MasterCard";

        //Confirmation Page Constant value
        final String BOOKING_STATUS     = "Confirmed";

        //Check In Bags Page constant values
        final String CHECKIN_SEAT_POPUP = "Required";

        //CheckIn Seat Page
        final String CHECKIN_DEP_SEATS = "Standard||Standard";


//Step1
        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectSortingOption("Dep", SORTING_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_TYPE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //wait till page is load is complete
        WaitUtil.untilTimeCompleted(3000);

        //Seat Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Option method
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment Page Method
        //pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        //Confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(), BOOKING_STATUS);

        //login to checkIn Path
        pageMethodManager.getHomePageMethods().loginToCheckIn();
        WaitUtil.untilPageLoadComplete(getDriver());

        pageMethodManager.getReservationSummaryPageMethods().clickCheckInAndGetBoardingPass();
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSaveOnBagsPopup("DontPurchase");
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSelectYourSeatPopup("DontPurchase");

//        pageMethodManager.getReservationSummaryPageMethods().clickCheckInAndGetBoardingPass();//Click on Check In Boarding Pass
//        WaitUtil.untilPageLoadComplete(getDriver());
//        pageObjectManager.getReservationSummaryPage().getSaveOnBagsPopupNopeIAmGoodButton().click();//Bags page during check-in path
//        WaitUtil.untilTimeCompleted(2000);
//        pageObjectManager.getReservationSummaryPage().getChooseYourSeatGetRandomSeatButton().click();//Bags page during check-in path
//        WaitUtil.untilTimeCompleted(2000);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();//Check In Option method

        WaitUtil.untilPageLoadComplete(getDriver());

        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        pageMethodManager.getReservationSummaryPageMethods().acceptRejectHazardousMaterialPopup("Accept");//Make a click on Accept and Print Boarding Pass

        String boardingPassBody = "Choose to print your boarding pass now, have your boarding pass emailed to you so that you can display it on your mobile device, or both.";
        String boardingPassNote = "Please check your email on a mobile device to make sure the bar code shows properly.";
        String printBoardingPassLabel = "Print Boarding Pass Now";
        String emailBoardingPassLabel = "Email Boarding Pass to:";

        ValidationUtil.validateTestStep("Your Boarding Pass Header is displayed",pageObjectManager.getReservationSummaryPage().getYourBoardingPassPopupHeaderText().isDisplayed());
        ValidationUtil.validateTestStep("The Boarding pass modal body text is displayed" ,getDriver().findElement(By.xpath("//app-branded-modal//div[contains(text(),'" + boardingPassBody + "')]")).isDisplayed());
        ValidationUtil.validateTestStep("Print Boarding Pass Label is displayed",pageObjectManager.getReservationSummaryPage().getYourBoardingPassPopupPrintBoardingPassOptionsLabel().get(0).getText() , printBoardingPassLabel);
        ValidationUtil.validateTestStep("Email Boarding Pass Label is displayed",pageObjectManager.getReservationSummaryPage().getYourBoardingPassPopupPrintBoardingPassOptionsLabel().get(1).getText() , emailBoardingPassLabel);
        ValidationUtil.validateTestStep("The Boarding pass modal body text is displayed" ,getDriver().findElement(By.xpath("//app-branded-modal//div[contains(text(),'" + boardingPassNote + "')]")).isDisplayed());
        ValidationUtil.validateTestStep("The Boarding pass Finish Check-in Button is displayed" ,pageObjectManager.getReservationSummaryPage().getYourBoardingPassPopupEmailBoardingPassButton().isDisplayed());

        //email boarding pass
        pageObjectManager.getReservationSummaryPage().getYourBoardingPassPopupPrintBoardingPassOptionsLabel().get(1).click();

        //clear Email Field On Print Boarding pass and input another email
        TestUtil.clearTextBoxUsingSendKeys(getDriver(),pageObjectManager.getReservationSummaryPage().getYourBoardingPassPopupEmailBoardingPassTextBox());
        pageObjectManager.getReservationSummaryPage().getYourBoardingPassPopupEmailBoardingPassTextBox().sendKeys("emailtesters@spirit.com");
        pageObjectManager.getReservationSummaryPage().getYourBoardingPassPopupEmailBoardingPassButton().click();

        //get current time
        String startTime = TestUtil.getStringDateFormat("0","yyyy-MM-dd HH:mm:ss");

        WaitUtil.untilPageLoadComplete(getDriver());

        EmailUtil.openOutlookInNewTab(getDriver(),scenarioContext.getContext(Context.CONFIRMATION_CODE).toString(),startTime,"BoardingPassEmail");
    }
}
