package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.*;

// **********************************************************************************************
// TestCase   : My Account_CP_$9FC_Request Miles Credit Link (Neg_Flight Has Not Arrived)
// Description: Validate error message for request mileage credit when flight has not landed
// Created By : Anthony Cardona
// Created On : 15-Jul-2019
// Reviewed By: Salim Ansari
// Reviewed On: 24-Jul-2019
// **********************************************************************************************

public class TC91224 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "NineDFC" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "NonStop" , "BookIt" , "NoBags" , "NoSeats" , "CheckInOptions" , "AmericanExpress" , "AccountProfileUI"})
    public void My_Account_CP_$9FC_Request_Miles_Credit_Link_Neg_Flight_Has_Not_Arrived(@Optional("NA") String platform) {

        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91224 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE               = "English";
        final String LOGIN_EMAIL            = "NineDFCEmail";
        final String JOURNEY_TYPE           = "Flight";
        final String TRIP_TYPE              = "OneWay";
        final String DEP_AIRPORTS           = "AllLocation";
        final String DEP_AIRPORT_CODE       = "FLL";
        final String ARR_AIRPORTS           = "AllLocation";
        final String ARR_AIRPORT_CODE       = "ORD";
        final String DEP_DATE               = "2";
        final String ARR_DATE               = "NA";
        final String ADULTS                 = "1";
        final String CHILD                  = "0";
        final String INFANT_LAP             = "0";
        final String INFANT_SEAT            = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT             = "NonStop";
        final String FARE_TYPE              = "Standard";
        final String UPGRADE_VALUE          = "BookIt";

        //Options Page Constant Value
        final String OPTION_VALUE 	        = "CheckInOption:DecideLater";

        //Payment page constant values
        final String CARD_TYPE              = "AmericanExpressCard";
        final String TRAVEL_GUARD           = "NotRequired";

        //open browser
        openBrowser(platform);

//-- Step 1: create booking outside 24 hours
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_EMAIL);
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
        WaitUtil.untilPageLoadComplete(getDriver());

        //navigate to my account drop down menu
        WaitUtil.untilPageLoadComplete(getDriver(), (long) 300);
        pageObjectManager.getHeader().getUserDropDown().click();

//Step 2: Reach the request miles page
        //Click on my account
        pageObjectManager.getHeader().getMyAccountUserLink().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //wait for page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getAccountProfilePage().getLeftMenuRequestMileageCreditLink().click();

//Step 3: Inout the PNR on the confirmation code textBox
        String PNR = scenarioContext.getContext(Context.CONFIRMATION_CODE).toString();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getAccountProfilePage().getReqestMilesConfirmationTextBox().sendKeys(PNR);
        ValidationUtil.validateTestStep("The user inputs PNR \""+ PNR + "\" into the text box" , true);

//Step 4: Click on Go button
        pageObjectManager.getAccountProfilePage().getReqestMilesGoButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);

//Step 5: Validate red iBlock for text "Sorry, please wait until your flight has arrived before requesting mileage credit."

        String FlightNotLandedErrorMessage     = "Sorry, please wait until your flight has arrived before requesting mileage credit.";
        ValidationUtil.validateTestStep("The error message for flight that has not landed is correct" , pageObjectManager.getAccountProfilePage().getReqestWaitForFlightLandedAlertText().getText() , FlightNotLandedErrorMessage);

//Step 6: click on the white exit button on the error message
        pageObjectManager.getAccountProfilePage().CloseWaitForFlightAlertMessageButton().click();

    }
}