package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.LoginCredentialsData;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC91523
//Test Case Name: Task 24794: 35287 Customer Info_CP_BP_Validate contact information section
//Created By: Gabriela
//Created On: 23-Jul-2019
//Reviewed By: Salim Ansari
//Reviewed On: 29-Jul-2019
//**********************************************************************************************

public class TC91523 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "FreeSpirit" , "NonStop" , "BookIt" , "PassengerInfoSignIn" , "PassengerInformationUI"})
    public void Customer_Info_CP_BP_Validate_contact_information_section(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91523 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant variables
        final String LANGUAGE                   = "English";
        final String JOURNEY_TYPE               = "Flight";
        final String LOGIN_ACCOUNT              = "FSEmail";
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
        final String FIRST_NAME_LABEL           = "First Name";
        final String LAST_NAME_LABEL            = "Last Name";
        final String ADDRESS_LABEL              = "Address";
        final String CITY_LABEL                 = "City";
        final String EMAIL_LABEL                = "Email";
        final String STATE_LABEL                = "State";
        final String ZIP_POSTAL_LABEL           = "Zip / Postal Code";
        final String COUNTRY_LABEL              = "Country";
        final String CONFIRM_EMAIL_LABEL        = "Confirm Email";
        final String PHONE_COUNTRY_CODE_LABEL   = "Phone Number Country Code";
        final String PRIMARY_PHONE_LABEL        = "Primary Phone Number";
        final String FREE_SPIRIT_VERBIAGE       = "Yes, I want to become a";

        //declare variable used in validation
        String asteriskColor = null;
        String asteriskSym 	 = null;
        final String RED_COLOR 		            = "rgb(220, 0, 0)";
        final String ASTERISK_SYMBOL            = "*";

        //open browser
        openBrowser(platform);

//-- Step 1: Land on Passenger info page
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
//-- Step 2: URL Validation
        ValidationUtil.validateTestStep("Validating Passenger Info Page URL",
                getDriver().getCurrentUrl(), PASSENGER_URL);

//-- Step 3
        //Contact section
        ValidationUtil.validateTestStep("Validating Contact section is displayed",
                pageObjectManager.getPassengerInfoPage().getContactPersonFirstNameLabel().getText(),FIRST_NAME_LABEL);

//-- Step 4 and 5
        //Primary Pax check box validation
        ValidationUtil.validateTestStep("Validating Primary Check box is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getPrimaryPassengerIstheContactPersonCheckBox()));

//-- Step 4 and 6
        ValidationUtil.validateTestStep("Validating FS inline enrollment banner under the contact block",
                pageObjectManager.getPassengerInfoPage().getYesIwantToJoinFSCheckBox().getText(),FREE_SPIRIT_VERBIAGE);

//-- Step 4 and 7
        LoginCredentialsData loginCredentialsData = FileReaderManager.getInstance().getJsonReader().getCredentialsByUserType(LOGIN_ACCOUNT);

        pageObjectManager.getPassengerInfoPage().getUserNameInLineLogInTextBox().sendKeys(loginCredentialsData.userName);
        pageObjectManager.getPassengerInfoPage().getPasswordInLineLogInTextBox().sendKeys(loginCredentialsData.password);
        pageObjectManager.getPassengerInfoPage().getLogInButtonInLineLogInButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());

        //Validating info is auto populated on contact fields after log in
        ValidationUtil.validateTestStep("Validating First Name is auto populated on contact fields after log in",
                pageObjectManager.getPassengerInfoPage().getContactPersonFirstNameTextBox().get(0).getAttribute("value"),
                pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0).getAttribute("value"));

        //Validating info is auto populated on contact fields after log in
        ValidationUtil.validateTestStep("Validating Last Name is auto populated on contact fields after log in",
                pageObjectManager.getPassengerInfoPage().getContactPersonLastNameTextBox().get(0).getAttribute("value"),
                pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(0).getAttribute("value"));

        //Validating Address field is auto populated on contact fields after log in
        ValidationUtil.validateTestStep("Validating Address field is auto populated on contact fields after log in",
                !pageObjectManager.getPassengerInfoPage().getContactPersonAddressTextBox().getAttribute("value").isEmpty());

        //Validating State field is auto populated on contact fields after log in
        ValidationUtil.validateTestStep("Validating State field is auto populated on contact fields after log in",
                !pageObjectManager.getPassengerInfoPage().getContactPersonStateDropDown().getAttribute("value").isEmpty());

        //Validating Zip Code field is auto populated on contact fields after log in
        ValidationUtil.validateTestStep("Validating Zip Code field is auto populated on contact fields after log in",
                !pageObjectManager.getPassengerInfoPage().getContactPersonZipPostalCodeTextBox().getAttribute("value").isEmpty());

        //Validating Country field is auto populated on contact fields after log in
        ValidationUtil.validateTestStep("Validating Country field is auto populated on contact fields after log in",
                !pageObjectManager.getPassengerInfoPage().getContactPersonCountryDropDown().getAttribute("value").isEmpty());

        //Validating Email field is auto populated on contact fields after log in
        ValidationUtil.validateTestStep("Validating Email field is auto populated on contact fields after log in",
                !pageObjectManager.getPassengerInfoPage().getContactPersonEmailTextBox().getAttribute("value").isEmpty());

        //Validating Confirm Email field is auto populated on contact fields after log in
        ValidationUtil.validateTestStep("Validating Confirm Email field is auto populated on contact fields after log in",
                !pageObjectManager.getPassengerInfoPage().getContactPersonConfirmEmailTextBox().getAttribute("value").isEmpty());

        //Validating Phone Number Country Code field is auto populated on contact fields after log in
        ValidationUtil.validateTestStep("Validating Phone Number Country Code field is auto populated on contact fields after log in",
                !pageObjectManager.getPassengerInfoPage().getContactPersonPhoneCountryCodeDropDown().getAttribute("value").isEmpty());

        //Validating Primary Phone Number field is auto populated on contact fields after log in
        ValidationUtil.validateTestStep("Validating Primary Phone Number field is auto populated on contact fields after log in",
                !pageObjectManager.getPassengerInfoPage().getContactPersonPhoneNumberTextBox().getAttribute("value").isEmpty());

//-- Step 8
        pageObjectManager.getPassengerInfoPage().getPrimaryPassengerIstheContactPersonCheckBox().click();
        WaitUtil.untilTimeCompleted(1000);

        ValidationUtil.validateTestStep("Validating First name collapsed after click on the check box",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getContactPersonFirstNameTextBox()));

        ValidationUtil.validateTestStep("Validating Last name collapsed after click on the check box",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getContactPersonLastNameTextBox()));

//-- Step 9
        pageObjectManager.getPassengerInfoPage().getPrimaryPassengerIstheContactPersonCheckBox().click();
        WaitUtil.untilTimeCompleted(1000);

        ValidationUtil.validateTestStep("Validating First name expanded after click on the check box",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getContactPersonFirstNameTextBox()));

        ValidationUtil.validateTestStep("Validating Last name expanded after click on the check box",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getContactPersonLastNameTextBox()));

//-- Step 10
        pageObjectManager.getPassengerInfoPage().getContactPersonFirstNameTextBox().get(0).clear();
        pageObjectManager.getPassengerInfoPage().getContactPersonLastNameTextBox().get(0).clear();
        pageObjectManager.getPassengerInfoPage().getContactPersonAddressTextBox().clear();
        pageObjectManager.getPassengerInfoPage().getContactPersonZipPostalCodeTextBox().clear();
        pageObjectManager.getPassengerInfoPage().getContactPersonEmailTextBox().clear();
        pageObjectManager.getPassengerInfoPage().getContactPersonConfirmEmailTextBox().clear();
        pageObjectManager.getPassengerInfoPage().getContactPersonPhoneNumberTextBox().clear();

//-- Step 11
        //get the asterisk symbol and color for First Name Label
        asteriskColor = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getPassengerInfoPage().getContactPersonFirstNameLabel(), "color");
        asteriskSym = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getPassengerInfoPage().getContactPersonFirstNameLabel(), "content");

        //verify First Name label should have a asterisk to show that it is required
        ValidationUtil.validateTestStep("First Name verbiage is displaying with asterisk", asteriskSym,ASTERISK_SYMBOL);

        //verify First Name label color of asterisk
        ValidationUtil.validateTestStep("First Name verbiage is displaying with red color of asterisk", asteriskColor,(RED_COLOR));

        ValidationUtil.validateTestStep("Validating First Name right labeled",
                pageObjectManager.getPassengerInfoPage().getContactPersonFirstNameLabel().getText(),FIRST_NAME_LABEL);

//-- Step 12
        //get the asterisk symbol and color for Last Name Label
        asteriskColor = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getPassengerInfoPage().getContactPersonLastNameLabel(), "color");
        asteriskSym = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getPassengerInfoPage().getContactPersonLastNameLabel(), "content");

        //verify Last Name label should have a asterisk to show that it is required
        ValidationUtil.validateTestStep("Last Name verbiage is displaying with asterisk", asteriskSym,ASTERISK_SYMBOL);

        //verify Last Name label color of asterisk
        ValidationUtil.validateTestStep("Last Name verbiage is displaying with red color of asterisk", asteriskColor,(RED_COLOR));

        ValidationUtil.validateTestStep("Validating Last Name right labeled",
                pageObjectManager.getPassengerInfoPage().getContactPersonLastNameLabel().getText(),LAST_NAME_LABEL);

//-- Step 13
        //get the asterisk symbol and color for Address Label
        asteriskColor = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getPassengerInfoPage().getContactPersonAddressLabel(), "color");
        asteriskSym = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getPassengerInfoPage().getContactPersonAddressLabel(), "content");

        //verify Address label should have a asterisk to show that it is required
        ValidationUtil.validateTestStep("Address verbiage is displaying with asterisk", asteriskSym,ASTERISK_SYMBOL);

        //verify Address label color of asterisk
        ValidationUtil.validateTestStep("Address verbiage is displaying with red color of asterisk", asteriskColor,(RED_COLOR));

        ValidationUtil.validateTestStep("Validating Address right labeled",
                pageObjectManager.getPassengerInfoPage().getContactPersonAddressLabel().getText(),ADDRESS_LABEL);

//-- Step 14
        //get the asterisk symbol and color for City Label
        asteriskColor = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getPassengerInfoPage().getContactPersonCityLabel(), "color");
        asteriskSym = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getPassengerInfoPage().getContactPersonCityLabel(), "content");

        //verify City label should have a asterisk to show that it is required
        ValidationUtil.validateTestStep("City verbiage is displaying with asterisk", asteriskSym,ASTERISK_SYMBOL);

        //verify City label color of asterisk
        ValidationUtil.validateTestStep("City verbiage is displaying with red color of asterisk", asteriskColor,(RED_COLOR));

        ValidationUtil.validateTestStep("Validating City right labeled",
                pageObjectManager.getPassengerInfoPage().getContactPersonCityLabel().getText(),CITY_LABEL);

//-- Step 15
        //get the asterisk symbol and color for Email Label
        asteriskColor = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getPassengerInfoPage().getContactPersonEmailLabel(), "color");
        asteriskSym = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getPassengerInfoPage().getContactPersonEmailLabel(), "content");

        //verify Email label should have a asterisk to show that it is required
        ValidationUtil.validateTestStep("Email verbiage is displaying with asterisk", asteriskSym,ASTERISK_SYMBOL);

        //verify Email label color of asterisk
        ValidationUtil.validateTestStep("Email verbiage is displaying with red color of asterisk", asteriskColor,(RED_COLOR));

        ValidationUtil.validateTestStep("Validating Email right labeled",
                pageObjectManager.getPassengerInfoPage().getContactPersonEmailLabel().getText(),EMAIL_LABEL);

//-- Step 16
        //get the asterisk symbol and color for State Label
        asteriskColor = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getPassengerInfoPage().getContactPersonStateLabel(), "color");
        asteriskSym = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getPassengerInfoPage().getContactPersonStateLabel(), "content");

        //verify State label should have a asterisk to show that it is required
        ValidationUtil.validateTestStep("State verbiage is displaying with asterisk", asteriskSym,ASTERISK_SYMBOL);

        //verify State label color of asterisk
        ValidationUtil.validateTestStep("State verbiage is displaying with red color of asterisk", asteriskColor,(RED_COLOR));

        ValidationUtil.validateTestStep("Validating State right labeled",
                pageObjectManager.getPassengerInfoPage().getContactPersonStateLabel().getText(),STATE_LABEL);

//-- Step 17
        //get the asterisk symbol and color for Zip Postal Label
        asteriskColor = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getPassengerInfoPage().getContactPersonZipCodeLabel(), "color");
        asteriskSym = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getPassengerInfoPage().getContactPersonZipCodeLabel(), "content");

        //verify Zip Postal label should have a asterisk to show that it is required
        ValidationUtil.validateTestStep("Zip Postal verbiage is displaying with asterisk", asteriskSym,ASTERISK_SYMBOL);

        //verify Zip Postal label color of asterisk
        ValidationUtil.validateTestStep("Zip Postal verbiage is displaying with red color of asterisk", asteriskColor,(RED_COLOR));

        ValidationUtil.validateTestStep("Validating Zip Postal right labeled",
                pageObjectManager.getPassengerInfoPage().getContactPersonZipCodeLabel().getText(),ZIP_POSTAL_LABEL);

//-- Step 18
        //get the asterisk symbol and color for Country Label
        asteriskColor = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getPassengerInfoPage().getContactPersonCountryLabel(), "color");
        asteriskSym = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getPassengerInfoPage().getContactPersonCountryLabel(), "content");

        //verify Country label should have a asterisk to show that it is required
        ValidationUtil.validateTestStep("Country verbiage is displaying with asterisk", asteriskSym,ASTERISK_SYMBOL);

        //verify Country label color of asterisk
        ValidationUtil.validateTestStep("Country verbiage is displaying with red color of asterisk", asteriskColor,(RED_COLOR));

        ValidationUtil.validateTestStep("Validating Country right labeled",
                pageObjectManager.getPassengerInfoPage().getContactPersonCountryLabel().getText(),COUNTRY_LABEL);

//-- Step 19
        //get the asterisk symbol and color for Confirm Email Label
        asteriskColor = JSExecuteUtil.getElementAfterProperty(getDriver(),  pageObjectManager.getPassengerInfoPage().getContactPersonConfirmEmailLabel(), "color");
        asteriskSym = JSExecuteUtil.getElementAfterProperty(getDriver(),  pageObjectManager.getPassengerInfoPage().getContactPersonConfirmEmailLabel(), "content");

        //verify Confirm Email label should have a asterisk to show that it is required
        ValidationUtil.validateTestStep("Confirm Email verbiage is displaying with asterisk", asteriskSym,ASTERISK_SYMBOL);

        //verify Confirm Email label color of asterisk
        ValidationUtil.validateTestStep("Confirm Email verbiage is displaying with red color of asterisk", asteriskColor,(RED_COLOR));

        ValidationUtil.validateTestStep("Validating Confirm Email right labeled",
                pageObjectManager.getPassengerInfoPage().getContactPersonConfirmEmailLabel().getText(),CONFIRM_EMAIL_LABEL);

//-- Step 20
        //get the asterisk symbol and color for Phone Country Code Label
        asteriskColor = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getPassengerInfoPage().getContactPersonPhoneCountryCodeLabel(), "color");
        asteriskSym = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getPassengerInfoPage().getContactPersonPhoneCountryCodeLabel(), "content");

        //verify Phone Country Code label should have a asterisk to show that it is required
        ValidationUtil.validateTestStep("Phone Country Code verbiage is displaying with asterisk", asteriskSym,ASTERISK_SYMBOL);

        //verify Phone Country Code label color of asterisk
        ValidationUtil.validateTestStep("Phone Country Code verbiage is displaying with red color of asterisk", asteriskColor,(RED_COLOR));

        ValidationUtil.validateTestStep("Validating Phone Country Code right labeled",
                pageObjectManager.getPassengerInfoPage().getContactPersonPhoneCountryCodeLabel().getText(),PHONE_COUNTRY_CODE_LABEL);

//-- Step 21
        //get the asterisk symbol and color for  Primary Phone Label
        asteriskColor = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getPassengerInfoPage().getContactPersonPhoneNumberLabel(), "color");
        asteriskSym = JSExecuteUtil.getElementAfterProperty(getDriver(),  pageObjectManager.getPassengerInfoPage().getContactPersonPhoneNumberLabel(), "content");

        //verify  Primary Phone label should have a asterisk to show that it is required
        ValidationUtil.validateTestStep(" Primary Phone verbiage is displaying with asterisk", asteriskSym,ASTERISK_SYMBOL);

        //verify  Primary Phone label color of asterisk
        ValidationUtil.validateTestStep(" Primary Phone verbiage is displaying with red color of asterisk", asteriskColor,(RED_COLOR));

        ValidationUtil.validateTestStep("Validating Primary Phone right labeled",
                pageObjectManager.getPassengerInfoPage().getContactPersonPhoneNumberLabel().getText(),PRIMARY_PHONE_LABEL);

//-- Step 22
        ValidationUtil.validateTestStep("Validating Terms & Conditions is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getTermsAndConditionsLink()));

        ValidationUtil.validateTestStep("Validating Privacy Policy is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getPrivacyPolicyLink()));

//-- Step 23
        ValidationUtil.validateTestStep("Validating Privacy Policy is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getContactPersonSMSNotificationsCheckBox()));

//-- Step 24: Click the Yes, I want to become a Free Spirit member
        pageMethodManager.getHomePageMethods().logoutFromApplication();
        WaitUtil.untilPageLoadComplete(getDriver());

        /*********************************HOME PAGE******************************************************/
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
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

        pageObjectManager.getPassengerInfoPage().getYesIwantToJoinFSCheckBox().click();

        ValidationUtil.validateTestStep("Password is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getFSEnrollmentPasswordTextBox()));

        ValidationUtil.validateTestStep("Password is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getFSEnrollmentConfirmPasswordTextBox()));
    }
}