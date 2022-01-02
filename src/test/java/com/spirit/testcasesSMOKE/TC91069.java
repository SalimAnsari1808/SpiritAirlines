package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************e
//Test Case ID: TC91069
//Test Name: Task 22961: 31277 Customer Info_CP_BP _NEG_Entering incorrect information for FS number
//Description: Validating right information is displayed when attempting to input an incorrect FS#
//Created By : Gabriela
//Created On : 13-MAY-2019
//Reviewed By: Salim Ansari
//Reviewed On: 14-May-2019
//**********************************************************************************************

public class TC91069 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Child" , "InfantLap" ,
                     "Guest" , "NonStop" , "BookIt","PassengerInformationUI"})
    public void Customer_Info_CP_BP_NEG_Entering_incorrect_information_for_FS_number(@Optional("NA") String platform) {
        //*******************************************************************
        //*************Navigate to CoPassenger Info Page*********************
        //*******************************************************************
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91069 under SMOKE Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "Oneway";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "ORD";
        final String DEP_DATE           = "5";
        final String ARR_DATE           = "NA";
        final String ADULTS             = "2";
        final String CHILD              = "1";
        final String INFANT_LAP         = "1";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        final String PASSENGER_INFO_URL = "book/passenger";

        //open browser
        openBrowser(platform);

//-- Step 1:  Book a Flight with Multi PAX continue to the customer information page.
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//-- Step 2: Verify customer Info page loads
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating the user land on the Passenger Information Page",
                getDriver().getCurrentUrl(),PASSENGER_INFO_URL);

//-- Step 3: If any children in the booking verify that DOB is auto populated
        ValidationUtil.validateTestStep("Verifying infant in lap DOB is displayed",
                !pageObjectManager.getPassengerInfoPage().getInfantDOBListTextBox().get(0).getAttribute("value").isEmpty());

//-- Step 4: For a randomly chosen PAX enter less than 9 digits for the FS number and try to continue to the next page (NEG)
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactNonUSResident();


        //*******************************************************************
        //*************Validation On Passenger Info Page*********************
        //*******************************************************************
        //Passenger Info Page Constant Values
        final String FS_NUM_LESS            = "12345";
        final String FS_NUM_LESS_ERROR      = "The customer information and the Free Spirit number do not match. Please update the form with the customer's Free Spirit number, or remove it completely if you don't know it.";
        final String FS_NUM_MORE            = "12345678987654";
        final String FS_NUM_MORE_ERROR      = "The customer information and the Free Spirit number do not match. Please update the form with the customer's Free Spirit number, or remove it completely if you don't know it.";
        final String FS_NUM_LETTERS         = "ABCDEFG";
        final String FS_NUM_LETTERS_ERROR   = "The customer information and the Free Spirit number do not match. Please update the form with the customer's Free Spirit number, or remove it completely if you don't know it.";
        final String FS_NUM_SYMBOL          = "*&^%$#@!";
        final String FS_NUM_SYMBOL_ERROR    = "The customer information and the Free Spirit number do not match. Please update the form with the customer's Free Spirit number, or remove it completely if you don't know it.";
        final String FS_NUM_WRONG           = "0123456789";
        final String FS_NUM_WRONG_ERROR     = "The customer information and the Free Spirit number do not match. Please update the form with the customer's Free Spirit number, or remove it completely if you don't know it.";
        final String FS_NUM_INVALID         = "The Free Spirit Number is invalid.";
        final String FS_NUM_CHARACTER       = "Only numbers are allowed.";

        pageObjectManager.getPassengerInfoPage().getAdultFSNumberListTextBox().get(0).sendKeys(FS_NUM_LESS);

        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Validating Red Iblock is displayed when FS Number length is less on Passenger info Page",
                pageObjectManager.getCommon().getIBlockVerbiageText().getText(),FS_NUM_LESS_ERROR);

        ValidationUtil.validateTestStep("Validating the error displayed in FS Number test box when FS Number Length is less on Passenger info Page",
                pageObjectManager.getCommon().getErrorMessageLabel().getText(),FS_NUM_INVALID);

//-- Step 5: For a randomly chosen PAX enter more than 10 digets for the FS number and try to continue to the next page (NEG)
        JSExecuteUtil.refreshBrowser(getDriver());
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactNonUSResident();
        pageObjectManager.getPassengerInfoPage().getAdultFSNumberListTextBox().get(0).sendKeys(FS_NUM_MORE);

        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Validating Red Iblock is displayed when FS Number length is more on Passenger info Page",
                pageObjectManager.getCommon().getIBlockVerbiageText().getText(),FS_NUM_MORE_ERROR);


        ValidationUtil.validateTestStep("Validating the error displayed in FS Number test box when FS Number Length is more on Passenger info Page",
                pageObjectManager.getCommon().getErrorMessageLabel().getText(),FS_NUM_INVALID);

//-- Step 6: For a randomly chosen PAX enter Letters into the FS number text box and try to continue to the next page
        JSExecuteUtil.refreshBrowser(getDriver());
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactNonUSResident();
        pageObjectManager.getPassengerInfoPage().getAdultFSNumberListTextBox().get(0).sendKeys(FS_NUM_LETTERS);

        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
        WaitUtil.untilPageLoadComplete(getDriver());

        //ValidationUtil.validateTestStep("Validating Red Iblock is displayed when FS Number is alphabetic character on Passenger info Page",
        //        pageObjectManager.getCommon().getIBlockVerbiageText().getText(),FS_NUM_LETTERS_ERROR);

        ValidationUtil.validateTestStep("Validating the error displayed in FS Number test box when FS Number is alphabetic character on Passenger info Page",
                pageObjectManager.getCommon().getErrorMessageLabel().getText(),FS_NUM_CHARACTER);

//-- Step 7: For a randomly chosen PAX enter symbols for the FS number text box and try to continue to the next page
        JSExecuteUtil.refreshBrowser(getDriver());
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactNonUSResident();
        pageObjectManager.getPassengerInfoPage().getAdultFSNumberListTextBox().get(0).sendKeys(FS_NUM_SYMBOL);

        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
        WaitUtil.untilPageLoadComplete(getDriver());

        //ValidationUtil.validateTestStep("Validating Red Iblock is displayed when FS Number is special character on Passenger info Page",
        //        pageObjectManager.getCommon().getIBlockVerbiageText().getText(),FS_NUM_SYMBOL_ERROR);

        ValidationUtil.validateTestStep("Validating the error displayed in FS Number test box when FS Number is special character on Passenger info Page",
                pageObjectManager.getCommon().getErrorMessageLabel().getText(),FS_NUM_CHARACTER);

//-- Step 8: For a randomly chosen PAX enter a FS number that does not match the name you have for your PAX
        JSExecuteUtil.refreshBrowser(getDriver());
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactNonUSResident();
        pageObjectManager.getPassengerInfoPage().getAdultFSNumberListTextBox().get(0).sendKeys(FS_NUM_WRONG);

        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Validating Red Iblock is displayed when FS Number is wrong on Passenger info Page",
                pageObjectManager.getCommon().getIBlockVerbiageText().getText(),FS_NUM_WRONG_ERROR);

        ValidationUtil.validateTestStep("Validating the error displayed in FS Number test box when FS Number is wrong on Passenger info Page",
                pageObjectManager.getCommon().getErrorMessageLabel().getText(),FS_NUM_INVALID);

    }
}