package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC91220
//Description:  CP_CI_Station Advisory
//Created By:   Kartik chauhan
//Created On:   22-July-2019
//Reviewed By:  Salim Ansari
//Reviewed On:  22-July-2019
//**********************************************************************************************
public class TC91220 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"CheckIn" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "NonStop" , "BookIt" , "NoBags" , "NoSeats" , "CheckInOptions" , "MasterCard" , "ReservationUI"})
    public void CP_CI_Station_Advisory(@Optional("NA") String platform) {
        /******************************************************************************
         *****************************Navigate to CheckIn Page*************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91220 under REGRESSION-CRITICAL Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String LANGUAGE               = "English";
        final String JOURNEY_TYPE           = "Flight";
        final String TRIP_TYPE              = "RoundTrip";
        final String DEP_AIRPORTS           = "AllLocation";
        final String DEP_AIRPORT_CODE       = "LAS";
        final String ARR_AIRPORTS           = "AllLocation";
        final String ARR_AIRPORT_CODE       = "DTW";
        final String DEP_DATE               = "0";
        final String ARR_DATE               = "2";
        final String ADULTS                 = "1";
        final String CHILD                  = "0";
        final String INFANT_LAP             = "0";
        final String INFANT_SEAT            = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT             = "NonStop";
        final String ARR_Flight             = "NonStop";
        final String FARE_TYPE              = "Standard";
        final String UPGRADE_VALUE          = "BookIt";

        //Options Page Constant Value
        final String OPTION_VALUE           = "CheckInOption:DecideLater";

        //Payment Page Constant Values
        final String CARD_TYPE              = "MasterCard";
        final String TRAVEL_GUARD           = "Required";

        //Confirmation Page Constant value
        final String BOOKING_STATUS         = "Confirmed";

        //open browser
        openBrowser(platform);
//STEP--1
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
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags page
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options page
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment page
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //Confirmation page closing ROKT Popup
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //Coping Confirmation Code for retrieve on CP
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //Verifying booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
//STEP--2
        /*********************************************Start OF Check-In Path**********************/
        //login to Check-In Path
        pageMethodManager.getHomePageMethods().loginToCheckIn();
        WaitUtil.untilPageLoadComplete(getDriver());
//STEP--3
        //Put a wait on Reservation summary page
        WaitUtil.untilTimeCompleted(2000);

        /******************************************************************************
         ***************************Validation on CheckIn Page*************************
         ******************************************************************************/
        //create constant
        final String TRAVEL_ADVISORY        = "Guests: Arrive early due to long lines Due to higher volume at the airport";
        final String TRAVEL_ADVISORY_URL    = "travel-advisory";
//STEP--4
        //put the condition for TA
        if(TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getTravelAdvisoryText())){

            //validate Hazmat Pop-up is displaying
            ValidationUtil.validateTestStep("Travel Advisory verbiage is displaying on reservation page",
                    pageObjectManager.getReservationSummaryPage().getTravelAdvisoryText().getText(),TRAVEL_ADVISORY);

            //validate Hazmat Pop-up is displaying
            ValidationUtil.validateTestStep("Travel Advisory link - 'Learn More' is displaying on reservation page",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getTravelAdvisoryLearnMoreLink()));

            //Click on travel Advisory link
            pageObjectManager.getReservationSummaryPage().getTravelAdvisoryLearnMoreLink().click();

            //wait for page load
            WaitUtil.untilPageLoadComplete(getDriver());

            //wait for 2 sec
            WaitUtil.untilTimeCompleted(2000);

            //validate Boarding Pass is appear
            ValidationUtil.validateTestStep("Travel Advisory link is redirected to current page",
                    getDriver().getCurrentUrl(),TRAVEL_ADVISORY_URL);

            //navigate back
            getDriver().navigate().back();

            //wait for page load
            WaitUtil.untilPageLoadComplete(getDriver());

            //wait for 2 sec
            WaitUtil.untilTimeCompleted(2000);

        }
        else{
            //validate Travel Advisory is not displaying
            ValidationUtil.validateTestStep("Travel Advisory is not displaying on reservation page", false);
        }
    }
}



