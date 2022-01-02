package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC91079
//Test Case Name: Task 24788: 35281 Customer Info_CP_BP_NEG_Enroll as a FS member invalid input
//Created By: Gabriela
//Created On: 24-Jul-2019
//Reviewed By: Salim Ansari
//Reviewed On: 25-Jul-2019
//**********************************************************************************************

public class TC91079 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "FreeSpirit" , "BookIt" , "NonStop" , "PassengerinfoSignUp" , "PassengerInformationUI"})
    public void Customer_Info_CP_BP_NEG_Enroll_as_a_FS_member_invalid_input(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91079 under REGRESSION-CRITICAL suite on " + platform + " Browser", true);
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

        final String HOME_PAGE_URL              = "spirit.com";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT                 = "NonStop";
        final String FARE_TYPE                  = "Standard";
        final String UPGRADE_VALUE              = "BookIt";

        //Passenger Info Page Constant Values
        final String PASSENGER_URL              = "/book/passenger";
        final String YES_I_WANT                 = "Yes, I want to become a ";
        String PASSWORD_TEXT                    = "Create a Password (8-16 characters include 1 upper, 1 lower, 1 special)";
        String CONFIRM_PASSWORD_TEXT            = "Confirm a Password (8-16 characters include 1 upper, 1 lower, 1 special)";
        String LESS_CHARACTERS                  = "A65%32$";
        String MORE_CHARACTERS                  = "A234%67887654321$";
        String ERROR                            = "Password must be at least 8 characters, no more than 16 characters, " +
                "must include minimum of 1 upper and lower case letter, a numeric digit," +
                " and a special character.";
        String UPPER_CASE_LC                    = "ASDFGHJKOIu";
        String UPPER_CASE_SC                    = "ASDFGHJKOI*";
        String UPPER_CASE                       = "LKJHGFDSERTY";
        String SPECIAL_C_LC                     = "z)(*&^%$#@";
        String LOWER_CASE                       = "lkjhgytrfdes";
        String SPECIAL_C                        = ")(*&^%$#@!(*";
        String MATCH_ERROR                      = "Must Match";


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

//-- Step 3: Enter information for all of your PAX
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

//-- Step 4: Scroll down to the bottom of the page where the FS Enroll inline banner is displayed
        ValidationUtil.validateTestStep("Validating FS enroll banner is displayed",
                pageObjectManager.getPassengerInfoPage().getYesIwantToJoinFSCheckBox().getText(),YES_I_WANT);

//-- Step 5: Click the Yes I want to become a Free Spirit member
        pageObjectManager.getPassengerInfoPage().getYesIwantToJoinFSCheckBox().click();
        WaitUtil.untilTimeCompleted(1000);

        ValidationUtil.validateTestStep("Validating password options expanded",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getFSEnrollmentPasswordTextBox()));

//-- Step 6: Verify the content inside the Inline Enroll
        ValidationUtil.validateTestStep("Validating right info displayed on create password field",
                pageObjectManager.getPassengerInfoPage().getFSEnrollmentPasswordLabel().getText(), PASSWORD_TEXT);

        ValidationUtil.validateTestStep("Validating right info displayed on confirm password field",
                pageObjectManager.getPassengerInfoPage().getFSEnrollmentConfirmPasswordLabel().getText(), CONFIRM_PASSWORD_TEXT);

//-- Step 7: Enter a password with less than 8 characters
        //Enter Password
        pageObjectManager.getPassengerInfoPage().getFSEnrollmentPasswordTextBox().sendKeys(LESS_CHARACTERS);

        //Confirm Password
        pageObjectManager.getPassengerInfoPage().getFSEnrollmentConfirmPasswordTextBox().sendKeys(LESS_CHARACTERS);

        ValidationUtil.validateTestStep("Validating error verbiage displayed",
                pageObjectManager.getCommon().getErrorMessageLabel().getText(),ERROR);

//-- Step 8: Enter a password with more than 16 characters
        //Enter Password
        TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getPassengerInfoPage().getFSEnrollmentPasswordTextBox());
        pageObjectManager.getPassengerInfoPage().getFSEnrollmentPasswordTextBox().sendKeys(MORE_CHARACTERS);

        //Confirm Password
        TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getPassengerInfoPage().getFSEnrollmentConfirmPasswordTextBox());
        pageObjectManager.getPassengerInfoPage().getFSEnrollmentConfirmPasswordTextBox().sendKeys(MORE_CHARACTERS);

        ValidationUtil.validateTestStep("Validating error verbiage displayed",
                pageObjectManager.getCommon().getErrorMessageLabel().getText(),ERROR);

//-- Step 9: Enter a password between 8 - 16 characters with only a upper case and one lower case character
        //Enter Password
        TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getPassengerInfoPage().getFSEnrollmentPasswordTextBox());
        pageObjectManager.getPassengerInfoPage().getFSEnrollmentPasswordTextBox().sendKeys(UPPER_CASE_LC);

        //Confirm Password
        TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getPassengerInfoPage().getFSEnrollmentConfirmPasswordTextBox());
        pageObjectManager.getPassengerInfoPage().getFSEnrollmentConfirmPasswordTextBox().sendKeys(UPPER_CASE_LC);

        ValidationUtil.validateTestStep("Validating error verbiage displayed",
                pageObjectManager.getCommon().getErrorMessageLabel().getText(),ERROR);

//--Step 10: Enter a password between 8 - 16 characters with only a upper case and a special character
        //Enter Password
        TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getPassengerInfoPage().getFSEnrollmentPasswordTextBox());
        pageObjectManager.getPassengerInfoPage().getFSEnrollmentPasswordTextBox().sendKeys(UPPER_CASE_SC);

        //Confirm Password
        TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getPassengerInfoPage().getFSEnrollmentConfirmPasswordTextBox());
        pageObjectManager.getPassengerInfoPage().getFSEnrollmentConfirmPasswordTextBox().sendKeys(UPPER_CASE_SC);

        ValidationUtil.validateTestStep("Validating error verbiage displayed",
                pageObjectManager.getCommon().getErrorMessageLabel().getText(),ERROR);

//-- Step 11: Enter a password between 8 - 16 characters with only upper case characters
        //Enter Password
        TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getPassengerInfoPage().getFSEnrollmentPasswordTextBox());
        pageObjectManager.getPassengerInfoPage().getFSEnrollmentPasswordTextBox().sendKeys(UPPER_CASE);

        //Confirm Password
        TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getPassengerInfoPage().getFSEnrollmentConfirmPasswordTextBox());
        pageObjectManager.getPassengerInfoPage().getFSEnrollmentConfirmPasswordTextBox().sendKeys(UPPER_CASE);

        ValidationUtil.validateTestStep("Validating error verbiage displayed",
                pageObjectManager.getCommon().getErrorMessageLabel().getText(),ERROR);

//-- Step 12: Enter a password between 8 - 16 characters with only a lower case and special character
        //Enter Password
        TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getPassengerInfoPage().getFSEnrollmentPasswordTextBox());
        pageObjectManager.getPassengerInfoPage().getFSEnrollmentPasswordTextBox().sendKeys(SPECIAL_C_LC);

        //Confirm Password
        TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getPassengerInfoPage().getFSEnrollmentConfirmPasswordTextBox());
        pageObjectManager.getPassengerInfoPage().getFSEnrollmentConfirmPasswordTextBox().sendKeys(SPECIAL_C_LC);

        ValidationUtil.validateTestStep("Validating error verbiage displayed",
                pageObjectManager.getCommon().getErrorMessageLabel().getText(),ERROR);

//-- Step 13: Enter a password between 8 - 16 characters with only  lower case character
        //Enter Password
        TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getPassengerInfoPage().getFSEnrollmentPasswordTextBox());
        pageObjectManager.getPassengerInfoPage().getFSEnrollmentPasswordTextBox().sendKeys(LOWER_CASE);

        //Confirm Password
        TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getPassengerInfoPage().getFSEnrollmentConfirmPasswordTextBox());
        pageObjectManager.getPassengerInfoPage().getFSEnrollmentConfirmPasswordTextBox().sendKeys(LOWER_CASE);

        ValidationUtil.validateTestStep("Validating error verbiage displayed",
                pageObjectManager.getCommon().getErrorMessageLabel().getText(),ERROR);

//-- Step 14: Enter a password between 8 - 16 characters with only special characters
        //Enter Password
        TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getPassengerInfoPage().getFSEnrollmentPasswordTextBox());
        pageObjectManager.getPassengerInfoPage().getFSEnrollmentPasswordTextBox().sendKeys(SPECIAL_C);

        //Confirm Password
        TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getPassengerInfoPage().getFSEnrollmentConfirmPasswordTextBox());
        pageObjectManager.getPassengerInfoPage().getFSEnrollmentConfirmPasswordTextBox().sendKeys(SPECIAL_C);

        ValidationUtil.validateTestStep("Validating error verbiage displayed",
                pageObjectManager.getCommon().getErrorMessageLabel().getText(),ERROR);

//-- Step 15: Enter two different valid passwords for the password and confirm password text blocks
        //Enter Password
        TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getPassengerInfoPage().getFSEnrollmentPasswordTextBox());
        pageObjectManager.getPassengerInfoPage().getFSEnrollmentPasswordTextBox().sendKeys("Default1!");

        //Confirm Password
        TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getPassengerInfoPage().getFSEnrollmentConfirmPasswordTextBox());
        pageObjectManager.getPassengerInfoPage().getFSEnrollmentConfirmPasswordTextBox().sendKeys("Default2@");

        ValidationUtil.validateTestStep("Validating error verbiage displayed",
                pageObjectManager.getCommon().getErrorMessageLabel().getText(),MATCH_ERROR);
    }
}