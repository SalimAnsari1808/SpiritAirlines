package com.spirit.testcasesProdBAT;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: PRODTC91066
//Description:  Customer Info_CP_BP_Validate Foreign Address in contact information
//Created By:   Salim Ansari
//Created On:   24-July-2019
//Reviewed By:  Kartik Chauhan
//Reviewed On:  24-July-2019
//**********************************************************************************************
public class PRODTC91066 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups="Production")

    public void Customer_Info_CP_BP_Validate_Foreign_Address_in_contact_information(@Optional("NA") String platform) {

        /******************************************************************************
         **********************Navigate to Passenger Information Page******************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID PRODTC91066 under PRODUCTION Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "3";
        final String ARR_DATE           = "5";
        final String ADULTS             = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String ARR_Flight         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

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

        //Flight Availability page methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);


        /******************************************************************************
         **********************Validation on Passenger Information Page****************
         ******************************************************************************/
        //declare constant used in validation
        final String PASSENGER_URL      = "book/passenger";
        final String DEFAULT_COUNTRY    = "United States of America";
        final String CANADA_COUNTRY     = "Canada";
        final String INDIA_COUNTRY      = "India, Republic of";
        final String REQUIRED_FIELD     = "*";
        final String NUMERICAL_VALUE    = "123";
        final String NUMBER_ERROR       = "Only letters are allowed";
        final String MORE_THAN_3_CHAR   = "Miami";

//Step 1
        //verify user reached on Passenger information page
        ValidationUtil.validateTestStep("Verify user reached on Passenger Information Page",
                getDriver().getCurrentUrl(),PASSENGER_URL);
//Step 2
        //Scroll down to the contact section
        pageObjectManager.getPassengerInfoPage().getContactPersonZipPostalCodeTextBox().click();
//Step 3
        //Verify that United States of America is selected by default in the country drop down
        //create drop down object
        Select drpdwnCountry = new Select(pageObjectManager.getPassengerInfoPage().getContactPersonCountryDropDown());

        //verify default selected country
        ValidationUtil.validateTestStep("Verify United States of America is selected default on Contact Section of Passenger Information Page",
                drpdwnCountry.getFirstSelectedOption().getText().trim(), DEFAULT_COUNTRY);
//Step 4
        //Verify that State is required fields
        ValidationUtil.validateTestStep("Verify State is required field on Contact Section of Passenger Information Page",
                JSExecuteUtil.getElementAfterProperty(getDriver(),pageObjectManager.getPassengerInfoPage().getContactPersonStateLabel(),"content"),REQUIRED_FIELD);

        //Verify that zip code is required fields
        ValidationUtil.validateTestStep("Verify Zip is required field on Contact Section of Passenger Information Page",
                JSExecuteUtil.getElementAfterProperty(getDriver(),pageObjectManager.getPassengerInfoPage().getContactPersonZipCodeLabel(),"content"),REQUIRED_FIELD);
//Step 5
        //Change the country to canada in the country field
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPassengerInfoPage().getContactPersonCountryDropDown(),CANADA_COUNTRY);

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);
//Step 6
        //Verify that State is required fields
        ValidationUtil.validateTestStep("Verify State is required field on Contact Section of Passenger Information Page",
                JSExecuteUtil.getElementAfterProperty(getDriver(),pageObjectManager.getPassengerInfoPage().getContactPersonStateLabel(),"content"),REQUIRED_FIELD);

        //Verify that zip code is required fields
        ValidationUtil.validateTestStep("Verify Zip is required field on Contact Section of Passenger Information Page",
                JSExecuteUtil.getElementAfterProperty(getDriver(),pageObjectManager.getPassengerInfoPage().getContactPersonZipCodeLabel(),"content"),REQUIRED_FIELD);
// Step 7
        //Change country to any other country that is not the USA or Canada
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPassengerInfoPage().getContactPersonCountryDropDown(),INDIA_COUNTRY);

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);
//Step 8
        //Verify that State is not required fields
        ValidationUtil.validateTestStep("Verify State is not required field on Contact Section of Passenger Information Page",
                !pageObjectManager.getPassengerInfoPage().getContactPersonStateLabel().getText().contains(REQUIRED_FIELD));

        //Verify that zip code is not required fields
        ValidationUtil.validateTestStep("Verify Zip is not required field on Contact Section of Passenger Information Page",
                !pageObjectManager.getPassengerInfoPage().getContactPersonZipCodeLabel().getText().contains(REQUIRED_FIELD));
//Step 9
        //Input some non alphabetical characters on to the state text bo
        pageObjectManager.getPassengerInfoPage().getContactPersonStateDropDown().sendKeys(NUMERICAL_VALUE);

        //click on zip code
        pageObjectManager.getPassengerInfoPage().getContactPersonZipPostalCodeTextBox().click();

        //error message saying numbers are not allowed
        ValidationUtil.validateTestStep("User verify error message saying numbers are not allowed for State field on Contact Section of Passenger Information Page ",
                pageObjectManager.getCommon().getErrorMessageLabel().getText(),NUMBER_ERROR);
//Step 10
        //Input more than 3 characters in the state text box
        pageObjectManager.getPassengerInfoPage().getContactPersonStateDropDown().sendKeys(MORE_THAN_3_CHAR);

        //click on zip code
        pageObjectManager.getPassengerInfoPage().getContactPersonZipPostalCodeTextBox().click();

        //Unable to input more than 3 characters
        ValidationUtil.validateTestStep("User verify Unable to input more than 3 characters for State field on Contact Section of Passenger Information Page ",
                Integer.toString(JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getPassengerInfoPage().getContactPersonStateDropDown()).length()),"3");
    }
}
