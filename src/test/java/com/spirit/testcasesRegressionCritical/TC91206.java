package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.PassengerInfoData;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC91206
//Test Case Name: Task 24793: 35286 Customer Info_CP_BP_Vacation Flight + Car Booking - insufficient age flight
//Created By: Gabriela
//Created On: 24-Jul-2019
//Reviewed By: Salim Ansari
//Reviewed On: 25-Jul-2019
//**********************************************************************************************

public class TC91206 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath" ,"RoundTrip", "FlightCar" , "DomesticDomestic" , "Outside21Days" , "Child" , "Guest" , "BookIt" , "MandatoryFields" , "PassengerInfoSignIn" , "PassengerInformationUI"})
    public void Customer_Info_CP_BP_Vacation_Flight_Car_Booking_insufficient_age_flight(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91206 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE               = "English";
        final String JOURNEY_TYPE           = "Vacation";

        final String TRIP_TYPE              = "Flight+Car";
        final String DEP_AIRPORTS           = "AllLocation";
        final String DEP_AIRPORT_CODE       = "FLL";
        final String ARR_AIRPORTS           = "AllLocation";
        final String ARR_AIRPORT_CODE       = "LAS";
        final String DEP_DATE               = "25";
        final String ARR_DATE               = "30";
        final String ADULT                  = "1";
        final String CHILD                  = "0";
        final String INFANT_LAP             = "0";
        final String INFANT_SEAT            = "0";
        final String DRIVER_AGE             = "25+";

        //flight availability page constant value
        final String UPGRADE_VALUE          = "BookIt";

        //Passenger Info page constant value
        final String PASSENGER_INFO         = "FSUMNR";

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

//-- Step 3: Enter Info for all Pax
        PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest(PASSENGER_INFO);

        pageObjectManager.getPassengerInfoPage().getAdultTitleListDropDown().get(0).sendKeys(passengerInfoData.title);
        pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0).sendKeys(passengerInfoData.firstName);
        pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(0).sendKeys(passengerInfoData.lastName);

//-- Step 4:
        // DOB under 21
        pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(0).sendKeys(passengerInfoData.dob);

        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

        //Trying to continue
        pageObjectManager.getPassengerInfoPage().getContinueToBagsButton().click();

        //Validating insufficient Age pop up
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Validating Insufficient Age pop up displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getInsufficientAgePopUpHeaderText()));

        pageObjectManager.getPassengerInfoPage().getInsufficientAgePopUpCloseButton().click();

//-- Step 5: Try to select a primary pax in the drop down
        ValidationUtil.validateTestStep("Validating user cannot select any option on Primary Driver drop down",
                !pageObjectManager.getPassengerInfoPage().getPrimaryDriverDropDown().isEnabled());
    }
}