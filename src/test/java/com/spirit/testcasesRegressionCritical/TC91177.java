package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

// **********************************************************************************************
// TestCase   : CP_CI_Seats Modal_Add Seats_Layover
// Description: Validate Seats upsell during checkin does not display if seat was already purchased
// Created By : Anthony Cardona
// Created On : 16-Jul-2019
// Reviewed By:
// Reviewed On:
// **********************************************************************************************

public class TC91177 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"CheckIn" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "Connecting" , "BookIt" , "NoBags" , "Standard" , "CheckInOptions" , "AmericanExpress" ,"ReservationUI", "SeatsUI" , "BoardingPassUI"})
    public void CP_CI_Seats_Modal_Add_Seats_Layover(@Optional("NA") String platform) {
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91177 under REGRESSION CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAX";
        final String DEP_DATE           = "0";
        final String ARR_DATE           = "NA";
        final String ADULTS             = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "Connecting";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Seats page constant variables
        final String SEAT_DEP           = "Standard||NoSeat";

        //Options Page Constant Value
        final String OPTION_VALUE       = "CheckInOption:DecideLater";

        //Payment page constant values
        final String CARD_TYPE          = "AmericanExpressCard";
        final String TRAVEL_GUARD       = "NotRequired";

        final String CHECKIN_BAGS_POPUP = "DontPurchase";

        final String CHECKIN_HAZMAT     = "Accept";

        final String CHECKIN_EMAIL      = "NA";

        final String MYTRIP_BOARDING_POPUP  = "Print";

        //open browser
        openBrowser(platform);

//-- Step 1: create booking outside 24 hours
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
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Customer Info Page Method
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags page
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
        WaitUtil.untilPageLoadComplete(getDriver());

        //Seats Page
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(SEAT_DEP);

        //Store seat number
        List<WebElement> seatsSelect = pageObjectManager.getSeatsPage().getPassengerSeatText();
        List<String> seatsList = new ArrayList<>();

        seatsList.add( pageObjectManager.getSeatsPage().getPassengerSeatText().get(0).getText());

        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        WaitUtil.untilPageLoadComplete(getDriver());

        //Option Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment page Methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //Confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

//Step 2: User starts check in process
        pageMethodManager.getHomePageMethods().loginToCheckIn();

//Step 3: Click on Check in
        pageMethodManager.getReservationSummaryPageMethods().clickCheckInAndGetBoardingPass();
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSaveOnBagsPopup(CHECKIN_BAGS_POPUP);


//Step 5: Seat up sell should display since the second segment does not have a seat selected
        ValidationUtil.validateTestStep("The seats modal is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getChooseYourSeatGetRandomSeatButton()));

//Step 6: Select a seat for the second segment
        pageObjectManager.getReservationSummaryPage().getChooseYourSeatSelectSeatButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("The previously selected seat " + seatsList.get(0) + " is correct" , pageObjectManager.getSeatsPage().getPassengerSeatText().get(0).getText() , seatsList.get(0) );
        pageObjectManager.getSeatsPage().getFlightLegsText().get(1).click();//click on the second segment

        for(WebElement seat : pageObjectManager.getSeatsPage().getStandardSeatsGridView())
        {
            if(seat.getText().contains("$"))
            {
                seat.click();
                break;
            }
        }

        seatsList.add( pageObjectManager.getSeatsPage().getPassengerSeatText().get(1).getText());

        pageMethodManager.getSeatsPageMethods().continueWithSeats();
        WaitUtil.untilPageLoadComplete(getDriver());

//Step 7: Finish check in and verify boarding pass
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getReservationSummaryPageMethods().acceptRejectHazardousMaterialPopup(CHECKIN_HAZMAT);

        //print boarding pass
        pageMethodManager.getReservationSummaryPageMethods().printEmailYourBoardingPassPopup(MYTRIP_BOARDING_POPUP,CHECKIN_EMAIL);

        //validate user taken to the boarding pass page
        WaitUtil.untilPageLoadComplete(getDriver());

        WaitUtil.untilTimeCompleted(2000);

        //validate seat number on boarding pass page
        ValidationUtil.validateTestStep("The seat number is correctly displayed on the first boarding pass"
                , pageObjectManager.getBoardingPassPage().getSeatNumberText().get(0).getText(), seatsList.get(0));

        ValidationUtil.validateTestStep("The seat number is correctly displayed on the second boarding pass"
                , pageObjectManager.getBoardingPassPage().getSeatNumberText().get(1).getText(), seatsList.get(1));
    }
}
