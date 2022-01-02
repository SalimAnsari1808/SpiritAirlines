package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.LoginCredentialsData;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC91207
//Test Case Name: Task 24785: 35264 Customer Info_CP_BP_As a 9DFC member Car Booking insufficient age flight
//Created By: Gabriela
//Created On: 24-Jul-2019
//Reviewed By: Salim Ansari
//Reviewed On: 25-Jul-2019
//**********************************************************************************************

public class TC91207 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "OneWay" , "FlightHotelCar" , "DomesticDomestic" , "Outside21Days" , "Child" , "NineDFC" , "BookIt" , "PassengerInfoSignIn" , "PassengerUI"})
    public void Customer_Info_CP_BP_As_a_9DFC_member_Car_Booking_insufficient_age_flight(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91207 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE               = "English";
        final String JOURNEY_TYPE           = "Vacation";
        final String LOGIN_TYPE             = "UMNR9FC";
        final String TRIP_TYPE              = "Flight+Car";
        final String DEP_AIRPORTS           = "AllLocation";
        final String DEP_AIRPORT_CODE       = "FLL";
        final String ARR_AIRPORTS           = "AllLocation";
        final String ARR_AIRPORT_CODE       = "LAS";
        final String DEP_DATE               = "40";
        final String ARR_DATE               = "55";
        final String ADULT                  = "1";
        final String CHILD                  = "0";
        final String INFANT_LAP             = "0";
        final String INFANT_SEAT            = "0";
        final String DRIVER_AGE             = "25+";

        //flight page constant value
        final String UPGRADE_VALUE          = "BookIt";

        //passenger page constant value
        final String INSUFFICIENT_AGE       = "Insufficient Age";


//Step 1: Start Vacation booking on test environment
        //open browser
        openBrowser(platform);

        /*********************************HOME PAGE******************************************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        /*********************************Flight Availability Methods*************************************************/
        //wait till page is load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //Click on Book Car Button
        pageObjectManager.getCarPage().getCarsPageBookButton().get(0).click();

        //wait till page is load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        /*********************************Passenger Info Methods*************************************************/
        //wait till page is load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

//-- Step 3: Log in as a FS member through inline login
        LoginCredentialsData loginCredentialsData = FileReaderManager.getInstance().getJsonReader().getCredentialsByUserType(LOGIN_TYPE);

        pageObjectManager.getPassengerInfoPage().getUserNameInLineLogInTextBox().sendKeys(loginCredentialsData.userName);
        pageObjectManager.getPassengerInfoPage().getPasswordInLineLogInTextBox().sendKeys(loginCredentialsData.password);
        pageObjectManager.getPassengerInfoPage().getLogInButtonInLineLogInButton().click();


//-- Step 4: Pax Information auto populated
        //Trying to continue
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Validating insufficient Age pop up
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Validating Insufficient Age pop up displayed",
                pageObjectManager.getPassengerInfoPage().getInsufficientAgePopUpHeaderText().getText(),INSUFFICIENT_AGE);

        pageObjectManager.getPassengerInfoPage().getInsufficientAgePopUpCloseButton().click();

//-- Step 5: Try to select a primary pax in the drop down
        ValidationUtil.validateTestStep("Validating user cannot select any option on Primary Driver drop down",
                !pageObjectManager.getPassengerInfoPage().getPrimaryDriverDropDown().isEnabled());
    }
}