package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

// **********************************************************************************************
// TestCase   : CP_CI_Validating Add seats link
// Description:
// Created By : Anthony Cardona
// Created On : 16-Jul-2019
// Reviewed By: Salim Ansari
// Reviewed On: 19-Jul-2019
// **********************************************************************************************
public class TC90858 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"CheckIn" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "NonStop" , "BookIt" , "NoBags" , "NoSeats" , "CheckInOptions" , "AmericanExpress" , "AddEditSeats" , "ReservationUI"})
    public void CP_CI_Validating_Add_seats_link(@Optional("NA") String platform) {
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90858 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "ORD";
        final String DEP_DATE           = "0";
        final String ARR_DATE           = "NA";
        final String ADULTS             = "3";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Seats page constant variables

        //Options Page Constant Value
        final String OPTION_VALUE       = "CheckInOption:DecideLater";

        //Payment page constant values
        final String CARD_TYPE          = "AmericanExpressCard";
        final String TRAVEL_GUARD       = "NotRequired";

        final String CHECKIN_SEAT       = "check-in/seats";

        //open browser
        openBrowser(platform);

//Create booking outside 24 hours
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
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
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

//User starts check in process
        pageMethodManager.getHomePageMethods().loginToCheckIn();

        ValidationUtil.validateTestStep("Add Seat link appear for each passenger on Reservation Summary Page",
                Integer.toString(pageObjectManager.getReservationSummaryPage().getPassengerSectionAddSeatsButton().size()),ADULTS);

        int seatCounter = pageObjectManager.getReservationSummaryPage().getPassengerSectionAddSeatsButton().size();
//Click on Add Seats
        for(int linkCounter=0;linkCounter<seatCounter;linkCounter++){
            //Click on Add Seat Link for each Passenger
            ValidationUtil.validateTestStep("The Add seats button is spelled correctly" , pageObjectManager.getReservationSummaryPage().getPassengerSectionAddSeatsButton().get(0).getText() , "ADD SEATS");
            pageObjectManager.getReservationSummaryPage().getPassengerSectionAddSeatsButton().get(linkCounter).click();

            WaitUtil.untilPageLoadComplete(getDriver());

            //Validate user taken to the check in Bags page
            ValidationUtil.validateTestStep("The user taken to the correct WebPage", getDriver().getCurrentUrl(),CHECKIN_SEAT);

            //navigate back to reservation summary Page
            getDriver().navigate().back();

            WaitUtil.untilPageLoadComplete(getDriver());

            WaitUtil.untilTimeCompleted(2000);
        }

    }
}
