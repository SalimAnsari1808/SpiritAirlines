package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.PassengerInfoData;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC90764
//Test Case Name: Task 24790: 35283 Customer Info_CP_BP_NEG_Have two PAX with the same name
//Created By: Gabriela
//Created On: 24-Jul-2019
//Reviewed By: Salim Ansari
//Reviewed On: 25-Jul-2019
//**********************************************************************************************

public class TC90764 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "BookIt" , "NonStop" , "MandatoryFields" , "PassengerInformationUI"})
    public void Customer_Info_CP_BP_NEG_Have_two_PAX_with_the_same_name(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90764 under REGRESSION-CRITICAL suite on " + platform + " Browser", true);
        }
        //Home Page Constant variables
        final String LANGUAGE                   = "English";
        final String JOURNEY_TYPE               = "Flight";
        final String TRIP_TYPE                  = "OneWay";
        final String DEP_AIRPORTS               = "AllLocation";
        final String DEP_AIRPORT_CODE           = "FLL";
        final String ARR_AIRPORTS               = "AllLocation";
        final String ARR_AIRPORT_CODE           = "MCO";
        final String DEP_DATE                   = "2";
        final String ARR_DATE                   = "NA";
        final String ADULT                      = "2";
        final String CHILD                      = "0";
        final String INFANT_LAP                 = "0";
        final String INFANT_SEAT                = "0";

        final String HOME_PAGE_URL              = "spirit.com";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT                 = "NonStop";
        final String FARE_TYPE                  = "Standard";
        final String UPGRADE_VALUE              = "BookIt";

        //Passenger Info Page Constant Values
        final String PASSENGER_URL              = "/book/passenger";

        //open browser
        openBrowser(platform);

//-- Step 1: Prerequisites Book a Flight with any number of PAX and continue to the customer information page
        /*********************************HOME PAGE******************************************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        /*********************************Flight Availability Methods*************************************************/
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        /*********************************Passenger Info Methods*************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
//-- Step 2: Verify customer Info page loads
        ValidationUtil.validateTestStep("Validating Passenger Info Page URL", getDriver().getCurrentUrl(), PASSENGER_URL);

//-- Step 3: Enter information for all PAX
        PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("Passenger1");

        //Entering Title for all pax
        for (int count = 0; count < pageObjectManager.getPassengerInfoPage().getAdultTitleListDropDown().size();count ++) {
            TestUtil.selectDropDownUsingValue(pageObjectManager.getPassengerInfoPage().getAdultTitleListDropDown().get(count), "MR");
        }

//-- Step 4: Make sure that two of your PAX have the same name
        //Entering First Name for all pax
        for (int count = 0; count < pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().size(); count ++) {
            pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(count).sendKeys(passengerInfoData.firstName);
        }

        //Entering Last Name for all pax
        for (int count = 0; count < pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().size(); count ++) {
            pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(count).sendKeys(passengerInfoData.lastName);
        }

        //EnteringDOB for all pax
        for (int count = 0; count < pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().size(); count ++) {
            pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(count).sendKeys(passengerInfoData.dob);
        }

        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

//-- Step 5: Try to continue to the following page
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        ValidationUtil.validateTestStep("Validating Duplicates error window is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getDuplicatePassengerHeaderText()));
    }
}