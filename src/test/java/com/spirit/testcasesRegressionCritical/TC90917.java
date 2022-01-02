package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.LoginCredentialsData;
import com.spirit.dataType.PassengerInfoData;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//TODO: [IN:26041]	Goldfinger R1: CP: BP: Passenger Info Page: when using an existing email and clicking "yes I
// want to become a free spirit member" checkbox on the passenger info page the user is getting 9fc log in pop up.

//Test Case ID: TC90917
//Test Case Name: Task 24792: 35285 Customer Info_CP_BP_Try to use already existing FS email
//Created By: Gabriela
//Created On: 24-Jul-2019
//Reviewed By: Salim Ansari
//Reviewed On: 25-Jul-2019
//**********************************************************************************************
//[IN:25531] Goldfinger R1: CP: BP: Passenger Info Page: when using an existing email and clicking "yes I want to become a free spirit member" checkbox on the passenger info page the user is getting 9fc log in pop up.
public class TC90917 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath","OneWay","DomesticDomestic","WithIn7Days","Adult","FreeSpirit","BookIt","NonStop",
                    "ContactInformation","PassengerInformationUI", "PassengerInfoSignIn" , "NoBags" , "NoSeats" ,
                    "CheckInOptions", "MasterCard","ActiveBug"})
    public void Customer_Info_CP_BP_Try_to_use_already_existing_FS_email(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90917 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
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
        final String ADULT                      = "3";
        final String CHILD                      = "0";
        final String INFANT_LAP                 = "0";
        final String INFANT_SEAT                = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT                 = "NonStop";
        final String FARE_TYPE                  = "Standard";
        final String UPGRADE_VALUE              = "BookIt";

        //Passenger Info Page Constant Values
        final String PASSENGER_URL              = "/book/passenger";

        //Payment page Constant values
        final String TRAVEL_GUARD		        = "NotRequired";
        final String CARD_TYPE			        = "MasterCard";

        //option page constant value
        final String SELECTED_OPTIONS           = "CheckInOption:MobileFree";

        //Confirmation Page Constant Values
        final String BOOKING_STATUS             = "CONFIRMED";
        final String CONFIRMATION_URL           = "book/confirmation";

        //open browser
        openBrowser(platform);

//-- Step 1: Land on Passenger info page
        //*********************************HOME PAGE******************************************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //*********************************Flight Availability Methods*************************************************/
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //*********************************Passenger Info Methods*************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
//-- Step 2: URL Validation
        ValidationUtil.validateTestStep("Validating Passenger Info Page URL",
                getDriver().getCurrentUrl(), PASSENGER_URL);

//-- Step 3: Enter information for all PAX
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();

//-- Step 4: On the contact section of the page fill out all information
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

        //Clear email and confirm email fields
        pageObjectManager.getPassengerInfoPage().getContactPersonEmailTextBox().clear();
        pageObjectManager.getPassengerInfoPage().getContactPersonConfirmEmailTextBox().clear();

        //put a FS member email address
        LoginCredentialsData loginCredentialsData = FileReaderManager.getInstance().getJsonReader().getCredentialsByUserType(LOGIN_ACCOUNT);

        pageObjectManager.getPassengerInfoPage().getContactPersonEmailTextBox().sendKeys(loginCredentialsData.userName);
        pageObjectManager.getPassengerInfoPage().getContactPersonConfirmEmailTextBox().sendKeys(loginCredentialsData.userName);

//-- Step 5: Click the Yes I want to become a Free Spirit member and sign up
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getPassengerInfoPage().getYesIwantToJoinFSCheckBox().click();

        //Validating error message
        WaitUtil.untilTimeCompleted(1000);
        ValidationUtil.validateTestStep("Email Address already in use error message displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getFSEnrollmentEmailAddressAlreadyInUseErrorText()));

//-- Step 6: Scroll to the inline login and enter email and password
        //TODO: [IN:26041]	Goldfinger R1: CP: BP: Passenger Info Page: when using an existing email and clicking "yes I
        // want to become a free spirit member" checkbox on the passenger info page the user is getting 9fc log in pop up.
        pageObjectManager.getPassengerInfoPage().getUserNameInLineLogInTextBox().sendKeys(loginCredentialsData.userName);
        pageObjectManager.getPassengerInfoPage().getPasswordInLineLogInTextBox().sendKeys(loginCredentialsData.password);
        pageObjectManager.getPassengerInfoPage().getLogInButtonInLineLogInButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());

        //Validating FS information auto populated on first pax
        PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("FSMember");

        ValidationUtil.validateTestStep("Validating FS PAX Title auto populated after sign in",
                pageObjectManager.getPassengerInfoPage().getAdultTitleListDropDown().get(0).getAttribute("value"),passengerInfoData.title.replace(".",""));

        ValidationUtil.validateTestStep("Validating FS PAX First Name auto populated after sign in",
                pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0).getAttribute("value"),passengerInfoData.firstName);

        ValidationUtil.validateTestStep("Validating FS PAX Last Name auto populated after sign in",
                pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(0).getAttribute("value"),passengerInfoData.lastName);

        ValidationUtil.validateTestStep("Validating FS PAX DOB auto populated after sign in",
                pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(0).getAttribute("value"),passengerInfoData.dob);

        ValidationUtil.validateTestStep("Validating FS Free Spirit Number Title auto populated after sign in",
                pageObjectManager.getPassengerInfoPage().getAdultFSNumberListTextBox().get(0).getAttribute("value"),loginCredentialsData.fsNumber);


//-- Step 7: Validating no banner is displayed
        ValidationUtil.validateTestStep("Validating Join FS banner is not displayed after sign in",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getYesIwantToJoinFSCheckBox()));

//-- Step 8: Continue with booking
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //*********************************Bags Page Methods*************************************************/
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //*********************************Seats Page Methods*************************************************/
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //*********************************Options Page Methods*************************************************/
        pageMethodManager.getOptionsPageMethods().selectOptions(SELECTED_OPTIONS);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //*********************************Payment Page Methods*************************************************/
        //Payment page methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //**********************Validation to Confirmation Page*************************/
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //verify user is navigated to confirmation page
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page",
                getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText().trim().toUpperCase(),BOOKING_STATUS);
    }
}