package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.LoginCredentialsData;
import com.spirit.dataType.PassengerInfoData;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC91094
//Test Case Name: Task 24789: 35282 Customer Info_CP_BP_NEG_Entering incorrect FS number on the FS text box
//Created By: Gabriela
//Created On: 24-Jul-2019
//Reviewed By: Salim Ansari
//Reviewed On: 25-Jul-2019
//**********************************************************************************************

public class TC91094 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "BookIt" , "NonStop" , "MandatoryFields" , "PassengerInformationUI"})
    public void Customer_Info_CP_BP_NEG_Entering_incorrect_FS_number_on_the_FS_text_box(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91094 under REGRESSION-CRITICAL suite on " + platform + " Browser", true);
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
        final String ADULT                      = "1";
        final String CHILD                      = "0";
        final String INFANT_LAP                 = "0";
        final String INFANT_SEAT                = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT                 = "NonStop";
        final String FARE_TYPE                  = "Standard";
        final String UPGRADE_VALUE              = "BookIt";

        //Passenger Info Page Constant Values
        final String PASSENGER_URL              = "/book/passenger";
        String WRONG_NAME_ERROR                 = "The customer information and the Free Spirit number do not match. " +
                "Please update the form with the customer's Free Spirit number, or " +
                "remove it completely if you don't know it.";
        String INVALID_FS                       = "The Free Spirit Number is invalid.";

        //Bags Page Constant Values
        final String BAG_URL                    = "/book/bags";

        //open browser
        openBrowser(platform);

//-- Step 1: Book a Flight with any number of PAX and continue to the customer information page
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
        ValidationUtil.validateTestStep("Validating Passenger Info Page URL",
                getDriver().getCurrentUrl(), PASSENGER_URL);

//--Step 3: Enter information for all of you PAX
        PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("FSMember");
        LoginCredentialsData loginCredentialsData = FileReaderManager.getInstance().getJsonReader().getCredentialsByUserType("FSSavedCards");

        TestUtil.selectDropDownUsingValue(pageObjectManager.getPassengerInfoPage().getAdultTitleListDropDown().get(0),"MR");
        pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(0).sendKeys(passengerInfoData.lastName);
        pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(0).sendKeys(passengerInfoData.dob);
        pageObjectManager.getPassengerInfoPage().getAdultFSNumberListTextBox().get(0).sendKeys(loginCredentialsData.fsNumber);

        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

//-- Step 4: Wrong Name, right FS number
        pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0).sendKeys("Automation");
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating Incorrect name error message displayed",
                pageObjectManager.getCommon().getIBlockVerbiageText().getText(),WRONG_NAME_ERROR);

        //Closing message
        pageObjectManager.getCommon().getIBlockCloseButton().click();
        WaitUtil.untilTimeCompleted(1000);

//-- Step 5: Right Name, wrong FS number
        //Name
        TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0));
        pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0).sendKeys(passengerInfoData.firstName);

        //FS number
        TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getPassengerInfoPage().getAdultFSNumberListTextBox().get(0));
        pageObjectManager.getPassengerInfoPage().getAdultFSNumberListTextBox().get(0).sendKeys("1111256497");

        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating wrong FS number error message displayed",
                pageObjectManager.getCommon().getIBlockVerbiageText().getText(),WRONG_NAME_ERROR);

        //Closing message
        pageObjectManager.getCommon().getIBlockCloseButton().click();
        WaitUtil.untilTimeCompleted(1000);


//--Step 6: Enter a FS number that is less then 9 digits and try to continue
        TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getPassengerInfoPage().getAdultFSNumberListTextBox().get(0));
        pageObjectManager.getPassengerInfoPage().getAdultFSNumberListTextBox().get(0).sendKeys("1256497");

        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating wrong FS number error message displayed",
                pageObjectManager.getCommon().getIBlockVerbiageText().getText(),WRONG_NAME_ERROR);

        ValidationUtil.validateTestStep("The Free Spirit Number is invalid Error message displayed", pageObjectManager.getCommon().getErrorMessageLabel().getText(), INVALID_FS);

        //Closing message
        pageObjectManager.getCommon().getIBlockCloseButton().click();
        WaitUtil.untilTimeCompleted(1000);

//-- Step 7: Enter a FS number that is more than 10 digits
        TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getPassengerInfoPage().getAdultFSNumberListTextBox().get(0));
        pageObjectManager.getPassengerInfoPage().getAdultFSNumberListTextBox().get(0).sendKeys("12564978569");

        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating wrong FS number error message displayed",
                pageObjectManager.getCommon().getIBlockVerbiageText().getText(),WRONG_NAME_ERROR);

        ValidationUtil.validateTestStep(INVALID_FS+" is displayed", pageObjectManager.getCommon().getErrorMessageLabel().getText(), INVALID_FS);

        //Closing message
        pageObjectManager.getCommon().getIBlockCloseButton().click();
        WaitUtil.untilTimeCompleted(1000);

        //Entering Valid information
        TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getPassengerInfoPage().getAdultFSNumberListTextBox().get(0));
        pageObjectManager.getPassengerInfoPage().getAdultFSNumberListTextBox().get(0).sendKeys(passengerInfoData.fsNumber);

        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Validating Bags Page URL",
                getDriver().getCurrentUrl(), BAG_URL);
    }
}