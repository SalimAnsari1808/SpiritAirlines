package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
//**********************************************************************************************
//Test Case ID: TC91201
//Description:  My trips_CP_MT_ My Trip errors
//Created By:   Kartik chauhan
//Created On:   29-July-2019
//Reviewed By:  Salim Ansari
//Reviewed On:  30-July-2019
//**********************************************************************************************

public class TC91201 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"MyTrips" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "NonStop" , "BookIt" , "NoBags" , "NoSeats" , "CheckInOptions" , "MasterCard" , "HomeUI"})
    public void My_trips_CP_MT_My_Trip_errors(@Optional("NA") String platform) {

        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91201 under REGRESSION-CRITICAL Suite on " + platform + " Browser"   , true);
        }

        /******************************************************************************
         ***************************Navigate to My Trip Page***************************
         ******************************************************************************/
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "DFW";
        final String DEP_DATE           = "2";
        final String ARR_DATE           = "NA";
        final String ADULTS             = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page Constant Value
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //Payment page
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_GUARD       = "NotRequired";

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
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //continue without bags
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
//STEP--1
        //Options page methods
        WaitUtil.untilPageLoadComplete(getDriver());

        //select checkin  options
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);

        //Select Continue button
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment page Methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //Confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        WaitUtil.untilPageLoadComplete(getDriver());


        //click on header Spirit logo
        pageObjectManager.getHeader().getSpiritLogoImage().click();

        //wait till page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        /******************************************************************************
         ***************************Validation on My Trip Page*************************
         ******************************************************************************/
        //verify MyTrip tab
        ValidationUtil.validateTestStep("MY Trip tab is displaying",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getMyTripPathLink()));

        //click on My trip tab
        pageObjectManager.getHomePage().getMyTripPathLink().click();

        //verify Last Name TextBox
        ValidationUtil.validateTestStep("Last Name TextBox is displaying",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getMyTripsLastNameTextBox()));

        //verify PNR TextBox
        ValidationUtil.validateTestStep("PNR TextBox is displaying",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getMyTripsPNRTextBox()));

        //verify Confirmation Code Link
        ValidationUtil.validateTestStep("Confirmation Code Link is displaying",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getMyTripConfirmationCodeLink()));

//STEP--3
        //enter valid PNR
        pageObjectManager.getHomePage().getMyTripConfirmationCodeTextBox().sendKeys(scenarioContext.getContext(Context.CONFIRMATION_CODE).toString());

        //click on Continue button
        pageObjectManager.getHomePage().getMyTripContinueButton().click();

        //Wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        final String LASTNAME_BLANK_ERROR   = "Please enter Passenger's Last Name.";
        final String PNR_BLANK_ERROR        = "Please enter your Confirmation Code.";

        //verify error is displaying for Blank Last Name
        ValidationUtil.validateTestStep("Error is displaying on Leaving Last Name box blank",
                pageObjectManager.getHomePage().getErrorMessageText().getText(),LASTNAME_BLANK_ERROR);
//STEP--4
        //Refresh page
        JSExecuteUtil.refreshBrowser(getDriver());

        //wait till page load completely
        WaitUtil.untilPageLoadComplete(getDriver());

        //click on My trip tab
        pageObjectManager.getHomePage().getMyTripPathLink().click();

        //enter valid LAST Name
        pageObjectManager.getHomePage().getMyTripLastNameTextBox().sendKeys(scenarioContext.getContext(Context.CONFIRMATION_LASTNAME).toString());

        //click on Continue button
        pageObjectManager.getHomePage().getMyTripContinueButton().click();

        //Wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //verify error is displaying for Blank Last Name
        ValidationUtil.validateTestStep("Error is displaying on Leaving PNR box blank",
                pageObjectManager.getHomePage().getErrorMessageText().getText(),PNR_BLANK_ERROR);

//STEP--5
        //enter valid LAST Name
        pageObjectManager.getHomePage().getMyTripLastNameTextBox().sendKeys("ABCDEF");

        //enter valid PNR
        pageObjectManager.getHomePage().getMyTripConfirmationCodeTextBox().sendKeys("XXXXX");

        //click on Continue button
        pageObjectManager.getHomePage().getMyTripContinueButton().click();

        //Wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //verify error is displaying when all fields are invalid
        ValidationUtil.validateTestStep("Reservation not found pop-up is displaying",
                pageObjectManager.getHomePage().getReservationNotFoundHeaderTxt().isDisplayed());
//STEP--6
        //Close pop-up
        pageObjectManager.getHomePage().getReservationNotFoundCloseBtn().click();

        //verify MyTrip tab
        ValidationUtil.validateTestStep("MY Trip tab is displaying",
                pageObjectManager.getHomePage().getMyTripPathLink().isDisplayed());
    }
}