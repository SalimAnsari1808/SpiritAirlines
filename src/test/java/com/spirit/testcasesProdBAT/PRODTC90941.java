package com.spirit.testcasesProdBAT;

import com.spirit.baseClass.*;
import com.spirit.dataType.*;
import com.spirit.managers.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: PRODTC90941
//Test Case Name: Task 24782: 35258 Customer Info_CP_BP _NEG_Have two PAX with the same name while logged in as a FS member
//Created By: Gabriela
//Created On: 19-Jul-2019
//Reviewed By: Salim Ansari
//Reviewed On: 22-Jul-2019
//**********************************************************************************************

public class PRODTC90941 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups="Production")

    public void Customer_Info_CP_BP_NEG_Have_two_PAX_with_the_same_name_while_logged_in_as_a_FS_member(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID PRODTC90941 under PRODUCTION Suite on " + platform + " Browser", true);
        }
        //Home Page Constant variables
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "ATL";
        final String DEP_DATE           = "6";
        final String ARR_DATE           = "NA";
        final String ADULT              = "2";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Passenger Info Constant Values
        final String PASSENGER_URL      = "/book/passenger";

        //open browser
        openBrowser(platform);

//--Step 1
        /*********************************************************************************************************
         * ******************************************HOME PAGE****************************************************
         *********************************************************************************************************/
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
//-- Step 2 Validating Passenger Information URL
        ValidationUtil.validateTestStep("User verify Navigated to Passenger page",
                getDriver().getCurrentUrl(),PASSENGER_URL);

//-- Step 3
        LoginCredentialsData loginCredentialsData = FileReaderManager.getInstance().getJsonReader().getCredentialsByUserType("ProdFSEmail");
        PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("FSMember");

        pageObjectManager.getPassengerInfoPage().getUserNameInLineLogInTextBox().sendKeys(loginCredentialsData.userName);
        pageObjectManager.getPassengerInfoPage().getPasswordInLineLogInTextBox().sendKeys(loginCredentialsData.password);
        pageObjectManager.getPassengerInfoPage().getLogInButtonInLineLogInButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(3000);

        ValidationUtil.validateTestStep("Validating Address field is not empty after log in as a FS member",
                !pageObjectManager.getPassengerInfoPage().getContactPersonAddressTextBox().getAttribute("value").isEmpty());

        ValidationUtil.validateTestStep("Validating City field is not empty after log in as a FS member",
                !pageObjectManager.getPassengerInfoPage().getContactPersonCityTextBox().getAttribute("value").isEmpty());

        ValidationUtil.validateTestStep("Validating State field is not empty after log in as a FS member",
                !pageObjectManager.getPassengerInfoPage().getContactPersonStateDropDown().getAttribute("value").isEmpty());

        ValidationUtil.validateTestStep("Validating Zip Code field is not empty after log in as a FS member",
                !pageObjectManager.getPassengerInfoPage().getContactPersonZipPostalCodeTextBox().getAttribute("value").isEmpty());

        ValidationUtil.validateTestStep("Validating right email information is displayed",
                pageObjectManager.getPassengerInfoPage().getContactPersonEmailTextBox().getAttribute("value"),
                loginCredentialsData.userName);

        ValidationUtil.validateTestStep("Validating Confirm Email field matching with Email",
                pageObjectManager.getPassengerInfoPage().getContactPersonEmailTextBox().getAttribute("value"),
                pageObjectManager.getPassengerInfoPage().getContactPersonConfirmEmailTextBox().getAttribute("value"));

        ValidationUtil.validateTestStep("Validating Zip Code field is not empty after log in as a FS member",
                !pageObjectManager.getPassengerInfoPage().getContactPersonPhoneNumberTextBox().getAttribute("value").isEmpty());

//-- Step 4 and 5 : typing for the second passenger the same FS member info
        //Title
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPassengerInfoPage().getAdultTitleListDropDown().get(1), "Mr.");

        //DOB
        pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(1).sendKeys(passengerInfoData.dob);

        //First Name
        pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(1).sendKeys("JOE");

        //Last Name
        pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(1).sendKeys("FLYER");

//-- Step 6
        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getPassengerInfoPage().getContinueToBagsButton());

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating Duplicate message pops up",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getDuplicatePassengerHeaderText()));
    }
}