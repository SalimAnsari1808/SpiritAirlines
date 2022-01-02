package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.LoginCredentialsData;
import com.spirit.dataType.PassengerInfoData;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC90786
//Test Case Name: Task 24774: 35278 Customer Info_CP_BP _ Have a PAX with a apostrophe
//Created By: Gabriela
//Created On: 25-Jul-2019
//Reviewed By: Salim Ansari
//Reviewed On: 26-Jul-2019
//**********************************************************************************************

public class TC90786 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath","OneWay","DomesticDomestic","WithIn7Days","Adult","NineDFC","BookIt","MandatoryFields",
                    "NonStop","PassengerInfoSignIn","MandatoryFields","NoBags","NoSeats","CheckInOptions","MasterCard",
                    "PaymentUI","ConfirmationUI"})
    public void Customer_Info_CP_BP_Have_a_PAX_with_a_apostrophe(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90786 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
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

//-- Step 1: Prerequisites Book a Flight with any number of PAX and continue to the customer information page
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
//-- Step 2: Verify customer Info page loads
        ValidationUtil.validateTestStep("Validating Passenger Info Page URL",
                getDriver().getCurrentUrl(), PASSENGER_URL);

//-- Step 3: If logged in as a FS or 9DFC member make sure primary PAX info is auto populate
        LoginCredentialsData loginCredentialsData = FileReaderManager.getInstance().getJsonReader().getCredentialsByUserType("NineDFCEmail");
        PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("NineFCMember");

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
                pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(0).getAttribute("value"), passengerInfoData.lastName);

        ValidationUtil.validateTestStep("Validating DOB Was auto populated correctly after sign in",
                pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(0).getAttribute("value"), passengerInfoData.dob);

//-- Step 3: Enter information for all PAX
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();

//-- Step 4: For a randomly selected PAX enter apostrophe in for first name, last name, and middle name
        String title  = pageObjectManager.getPassengerInfoPage().getAdultTitleListDropDown().get(1).getAttribute("value");
        pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(1).sendKeys("'r");
        String firstName =  pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(1).getAttribute("value");

        pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(1).sendKeys("'m");
        String lastName =  pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(1).getAttribute("value");

        pageObjectManager.getPassengerInfoPage().getAdultMiddleNameListTextBox().get(1).sendKeys("Joe's");

//-- Step 5: Not valid step

//-- Step 6: Continue with the booking process
//        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //*********************************Bags Page Methods*************************************************/
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //*********************************Seats Page Methods*************************************************/
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //*********************************Options Page Methods*************************************************/
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //*********************************Payment Page Methods*************************************************/
        ValidationUtil.validateTestStep("Validating apostrophe information on payment page",
                pageObjectManager.getPaymentPage().getPassengerNameText().get(1).getText(), title + ". " + firstName + " " + lastName);

        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //*********************** Confirmation Page Method************************/
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //verify user is navigated to confirmation page
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page",
                getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText().trim().toUpperCase(),BOOKING_STATUS);

        ValidationUtil.validateTestStep("Validating apostrophe information on confirmation page",
                pageObjectManager.getConfirmationPage().getPassengerSectionNamesText().get(1).getText(), title + ". " + firstName + " " + lastName);
    }
}