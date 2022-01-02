package com.spirit.testcasesProdBAT;

import com.spirit.baseClass.*;
import com.spirit.dataType.*;
import com.spirit.managers.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: PRODTC91118
//Test Case Name: Task 24787: 35263 Customer Info_CP_BP_Enter information for a 9DFC Pax then login
//Created By: Gabriela
//Created On: 24-Jul-2019
//Reviewed By: Salim Ansari
//Reviewed On: 25-Jul-2019
//**********************************************************************************************

public class PRODTC91118 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups="Production")

    public void Customer_Info_CP_BP_Enter_information_for_a_9DFC_Pax_then_login(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID PRODTC91118 under PRODUCTION suite on " + platform + " Browser", true);
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

        //Options Page Constant Value
        final String OPTIONS_VALUE              = "CheckInOption:MobileFree";

        //Payment page Constant values
        final String TRAVEL_GUARD		        = "NotRequired";
        final String CARD_TYPE			        = "MasterCard";

        //Confirmation Page Constant Values
        final String BOOKING_STATUS             = "CONFIRMED";
        final String CONFIRMATION_URL           = "book/confirmation";

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

//-- Step 4: Make sure you have entered information for your first PAX
        ValidationUtil.validateTestStep("Validating Title field for first passenger is not empty",
                !pageObjectManager.getPassengerInfoPage().getAdultTitleListDropDown().get(0).getAttribute("value").isEmpty());

        ValidationUtil.validateTestStep("Validating First Name field for first passenger is not empty",
                !pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0).getAttribute("value").isEmpty());

        ValidationUtil.validateTestStep("Validating Last Name field for first passenger is not empty",
                !pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(0).getAttribute("value").isEmpty());

        ValidationUtil.validateTestStep("Validating DOB field for first passenger is not empty",
                !pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(0).getAttribute("value").isEmpty());

//-- Step 5: Log in as a 9DFC member on the customer information page with the inline login
        LoginCredentialsData loginCredentialsData = FileReaderManager.getInstance().getJsonReader().getCredentialsByUserType("ProdNineDFCEmail");
        PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("ProdNineFCMember");

        pageObjectManager.getPassengerInfoPage().getUserNameInLineLogInTextBox().sendKeys(loginCredentialsData.userName);
        pageObjectManager.getPassengerInfoPage().getPasswordInLineLogInTextBox().sendKeys(loginCredentialsData.password);
        pageObjectManager.getPassengerInfoPage().getLogInButtonInLineLogInButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());

        //Validating info auto populated is correct on passenger information section
        ValidationUtil.validateTestStep("Validating Title Was auto populated correctly after sign in",
                pageObjectManager.getPassengerInfoPage().getAdultTitleListDropDown().get(0).getAttribute("value"), passengerInfoData.title.replace(".",""));

        ValidationUtil.validateTestStep("Validating First Name Was auto populated correctly after sign in",
                pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0).getAttribute("value"), passengerInfoData.firstName);

        ValidationUtil.validateTestStep("Validating Last Name Was auto populated correctly after sign in",
                pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(0).getAttribute("value"), "CARDONA");

        ValidationUtil.validateTestStep("Validating DOB Was auto populated correctly after sign in",
                pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(0).getAttribute("value"), passengerInfoData.dob);

        //Validating info auto populated is correct on Contact section
        ValidationUtil.validateTestStep("Validating Right First Name displayed on Contact section",
                pageObjectManager.getPassengerInfoPage().getContactPersonFirstNameTextBox().get(0).getAttribute("value"),passengerInfoData.firstName);

        ValidationUtil.validateTestStep("Validating Right Last Name displayed on Contact section",
                pageObjectManager.getPassengerInfoPage().getContactPersonLastNameTextBox().get(0).getAttribute("value"),"CARDONA");

        ValidationUtil.validateTestStep("Validating Address field is not empty",
                !pageObjectManager.getPassengerInfoPage().getContactPersonAddressTextBox().getAttribute("value").isEmpty());

        //Storing Address information for future validation
        String addressInfo  = pageObjectManager.getPassengerInfoPage().getContactPersonAddressTextBox().getAttribute("value");

        ValidationUtil.validateTestStep("Validating City field is not empty",
                !pageObjectManager.getPassengerInfoPage().getContactPersonCityTextBox().getAttribute("value").isEmpty());

        //Storing City information for future validation
        String cityInfo     = pageObjectManager.getPassengerInfoPage().getContactPersonCityTextBox().getAttribute("value");

        ValidationUtil.validateTestStep("Validating State field is not empty",
                !pageObjectManager.getPassengerInfoPage().getContactPersonStateDropDown().getAttribute("value").isEmpty());

        //Storing State information for future validation
        String stateInfo    = pageObjectManager.getPassengerInfoPage().getContactPersonStateDropDown().getAttribute("value");

        ValidationUtil.validateTestStep("Validating Zip Code field is not empty",
                !pageObjectManager.getPassengerInfoPage().getContactPersonZipPostalCodeTextBox().getAttribute("value").isEmpty());

        //Storing Zip Code information for future validation
        String zipCodeInfo      = pageObjectManager.getPassengerInfoPage().getContactPersonZipPostalCodeTextBox().getAttribute("value");

        ValidationUtil.validateTestStep("Validating Country field is not empty",
                !pageObjectManager.getPassengerInfoPage().getContactPersonCountryDropDown().getAttribute("value").isEmpty());

        //Storing Country information for future validation
        String countryInfo    = pageObjectManager.getPassengerInfoPage().getContactPersonCountryDropDown().getAttribute("value");

        ValidationUtil.validateTestStep("Validating email field is not empty",
                !pageObjectManager.getPassengerInfoPage().getContactPersonEmailTextBox().getAttribute("value").isEmpty());

        //Storing email information for future validation
        String emailInfo    = pageObjectManager.getPassengerInfoPage().getContactPersonEmailTextBox().getAttribute("value");

        ValidationUtil.validateTestStep("Validating ConfirmEmail field is not empty",
                !pageObjectManager.getPassengerInfoPage().getContactPersonConfirmEmailTextBox().getAttribute("value").isEmpty());

        //Storing ConfirmEmail information for future validation
        String confirmEmailInfo    = pageObjectManager.getPassengerInfoPage().getContactPersonConfirmEmailTextBox().getAttribute("value");

        ValidationUtil.validateTestStep("Validating Phone Country Code field is not empty",
                !pageObjectManager.getPassengerInfoPage().getContactPersonPhoneCountryCodeDropDown().getAttribute("value").isEmpty());

        //Storing Phone Country Code information for future validation
        String phoneCountryCodeInfo    = pageObjectManager.getPassengerInfoPage().getContactPersonPhoneCountryCodeDropDown().getAttribute("value");

        ValidationUtil.validateTestStep("Validating Primary Phone field is not empty",
                !pageObjectManager.getPassengerInfoPage().getContactPersonPhoneNumberTextBox().getAttribute("value").isEmpty());

        //Storing Primary Phone information for future validation
        String primaryPhoneInfo    = pageObjectManager.getPassengerInfoPage().getContactPersonPhoneNumberTextBox().getAttribute("value").replace("-","");

//-- Step 6: Complete your booking
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /*********************************Bags PAGE******************************************************/
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        /*********************************Seats PAGE******************************************************/
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        /*********************************Options PAGE******************************************************/
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /*********************************Payment PAGE******************************************************/
        //Payment page methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
//        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
//        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);
//
//        /******************************************************************************
//         ***********************Validation to Confirmation Page************************
//         ******************************************************************************/
//        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
//
//
//        //verify user is navigated to confirmation page
//        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page",
//                getDriver().getCurrentUrl(),CONFIRMATION_URL);
//
//        //verify booking is confirmed
//        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
//                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText().trim().toUpperCase(),BOOKING_STATUS);
//
////-- Step 7: Click your 9DFC members name on the header
//        pageObjectManager.getHeader().getUserDropDown().click();
//
////-- Step 8: Click the My account link
//        pageObjectManager.getHeader().getMyAccountUserLink().click();
//
////-- Step 9: Click the Edit personal information link
//        WaitUtil.untilPageLoadComplete(getDriver());
//        pageObjectManager.getAccountProfilePage().getLeftMenuPersonalInformationLink().click();
//
////-- Step 10: Verify that all of your 9DFC members information is unchanged
//        WaitUtil.untilPageLoadComplete(getDriver());
//        ValidationUtil.validateTestStep("Validating Pax Title doesn't change",
//                pageObjectManager.getAccountProfilePage().getPersonalInfoTitleDropDown().getAttribute("value"),passengerInfoData.title.replace(".", ""));
//
//        ValidationUtil.validateTestStep("Validating Pax First and Last Name doesn't change",
//                pageObjectManager.getAccountProfilePage().getPersonalInfoNameText().getText(),passengerInfoData.firstName + " " + passengerInfoData.lastName);
//
//        ValidationUtil.validateTestStep("Validating Pax address doesn't change",
//                pageObjectManager.getAccountProfilePage().getPersonalInfoAddressTextBox().getAttribute("value"), addressInfo);
//
//        ValidationUtil.validateTestStep("Validating Pax city doesn't change",
//                pageObjectManager.getAccountProfilePage().getPersonalInfoCityTextBox().getAttribute("value"), cityInfo);
//
//        ValidationUtil.validateTestStep("Validating Pax state doesn't change",
//                pageObjectManager.getAccountProfilePage().getPersonalInfoStateDropDown().getAttribute("value"), stateInfo);
//
//        ValidationUtil.validateTestStep("Validating Pax Zip Code doesn't change",
//                pageObjectManager.getAccountProfilePage().getPersonalInfoZipCodeTextBox().getAttribute("value"), zipCodeInfo);
//
//        ValidationUtil.validateTestStep("Validating Pax Country doesn't change",
//                pageObjectManager.getAccountProfilePage().getPersonalInfoCountryDropDown().getAttribute("value"), countryInfo);
//
//        ValidationUtil.validateTestStep("Validating Pax Primary Phone doesn't change",
//                pageObjectManager.getAccountProfilePage().getPersonalInfoPrimaryPhoneTextBox().getAttribute("value").replace("-",""), primaryPhoneInfo);
//
//        ValidationUtil.validateTestStep("Validating Pax DOB doesn't change",
//                pageObjectManager.getAccountProfilePage().getPersonalInfoDOBTextBox().getAttribute("value"), passengerInfoData.dob);
    }
}