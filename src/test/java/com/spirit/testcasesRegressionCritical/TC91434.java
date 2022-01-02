package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.LoginCredentialsData;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC91434
//Test Case Name: Task 24683: 35351 CP_BP_Payment Page_Passenger Info_Military with invalid Troop ID
//Created By: Gabriela
//Created On: 5-Jul-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC91434 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Military" , "FSMiles" , "NonStop" , "BookIt" , "CarryOn" , "CheckedBags" , "NoSeats" ,"ShortCutBoarding", "CheckInOptions" , "MasterCard" , "PaymentUI"})
    public void CP_BP_Payment_Page_Passenger_Info_Military_with_invalid_Troop_ID(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91434 under REGRESSION_CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant variables
        final String LANGUAGE                           = "English";
        final String LOGGIN_ACCOUNT                     = "MilitaryFSMiles";
        final String JOURNEY_TYPE                       = "Flight";
        final String TRIP_TYPE                          = "OneWay";
        final String DEP_AIRPORTS                       = "AllLocation";
        final String DEP_AIRPORT_CODE                   = "MCO";
        final String ARR_AIRPORTS                       = "AllLocation";
        final String ARR_AIRPORT_CODE                   = "FLL";
        final String DEP_DATE                           = "3";
        final String ARR_DATE                           = "NA";
        final String ADULT                              = "1";
        final String CHILD                              = "0";
        final String INFANT_LAP                         = "0";
        final String INFANT_SEAT                        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT                         = "NonStop";
        final String FARE_TYPE                          = "Standard";
        final String UPGRADE_VALUE                      = "BookIt";

        //Bags Page Constant Values
        final String DEP_BAGS                           = "Carry_1|Checked_2";

        //Options Page Constant Value
        final String OPTIONS_VALUE                      = "CheckInOption:MobileFree";

        //Payment page constant value
        final String PAYMENT_URL                        = "/book/payment";
        final String MILITARY_URL                       = "https://api.id.me/en/session/new";
        final String FAILED                             = "Verification Failed";
        final String TRAVEL_GUARD                       = "NotRequired";
        final String CARD_DETAIL                        = "MasterCard";

        //Confirmation Page Constant Value
        final String BOOKING_STATUS                     = "Confirmed";
        final String CONFIRMATION_URL                   = "book/confirmation";

        //open browser
        openBrowser(platform);
//--Step 1
        /****************************************************************************
         * *********************Navigate to Payment Page ****************************
         ****************************************************************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGGIN_ACCOUNT);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);

        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getSeletedDepatingFlightNatureButton().get(0).click();

        WaitUtil.untilPageLoadComplete(getDriver());
        String DEP_CITY_INFO = pageObjectManager.getFlightAvailabilityPage().getStopsPopUpDepartureAirportsText().get(0).getText();
        pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();
        String DEP_TIME = pageObjectManager.getFlightAvailabilityPage().getDepartingFlightBlockDepartTimeText().get(1).getText().trim();
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getPassengerInfoPage().getActiveMilitaryPersonnelListCheckBox().get(1).click();

        String passengerFirstName   = pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0).getAttribute("value");
        String passengerLastName    = pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(0).getAttribute("value");
        String passengerTitle       = pageObjectManager.getPassengerInfoPage().getAdultTitleListDropDown().get(0).getAttribute("value");

        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

        //Seats page methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options page methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /****************************************************************************
         * *********************Payment Page Validations ****************************
         ****************************************************************************/
//-- Step 1:
        WaitUtil.untilPageLoadComplete(getDriver());
        //URL Validation
        ValidationUtil.validateTestStep("Validating Payment Page URL",
                getDriver().getCurrentUrl(),PAYMENT_URL);

//-- Step 2:
        ValidationUtil.validateTestStep("Validating Departure City info",
                pageObjectManager.getPaymentPage().getDepartingFlightCityNameText().get(0).getText(),DEP_CITY_INFO);

        ValidationUtil.validateTestStep("Validating Departing Time Info",
                pageObjectManager.getPaymentPage().getDepartingFlightCityNameText().get(0).getText(),DEP_TIME);
//-- Step 3:
        ValidationUtil.validateTestStep("Validating the right name information is displayed",
                pageObjectManager.getPaymentPage().getPassengerNameText().get(0).getText(), passengerTitle + ". " + passengerFirstName +  " " + passengerLastName);

        ValidationUtil.validateTestStep("Validating Military container is displayed",
                getDriver().findElements(By.xpath("//section[@id='scroll-active-military']")).get(0).isDisplayed());

//-- Step 4
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getPaymentPage().getActiveMilitaryVerifyLink().get(0).click();

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("The user redirected to the correct page",
                getDriver().getCurrentUrl(),MILITARY_URL);

//-- Step 6: //Step 5 is next
        LoginCredentialsData loginCredentialsData = FileReaderManager.getInstance().getJsonReader().getCredentialsByUserType("MilitrayMIKESMITH");
        WaitUtil.untilTimeCompleted(1000);

        pageObjectManager.getPaymentPage().getActiveMilitaryEmailTextBox().sendKeys(loginCredentialsData.userName);
        WaitUtil.untilTimeCompleted(1000);

        pageObjectManager.getPaymentPage().getActiveMilitaryPasswordTextBox().sendKeys(loginCredentialsData.password);
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getPaymentPage().getActiveMilitarySignInButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating failed message information",
                pageObjectManager.getPaymentPage().getActiveMilitaryThankYouText().getText(),FAILED);

        //Click on Options Header link to reach into Options Page
        getDriver().findElements(By.xpath("(//i[@class='icon-checkmark ng-tns-c8-3 ng-star-inserted'])[10]")).get(0).click();

        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//-- Step 5:
        WaitUtil.untilPageLoadComplete(getDriver());
        LoginCredentialsData loginCredentialsData1 = FileReaderManager.getInstance().getJsonReader().getCredentialsByUserType("MilitrayJOHNDOE");

        pageObjectManager.getPaymentPage().getActiveMilitaryVerifyLink().get(0).click();
        WaitUtil.untilPageLoadComplete(getDriver());

        pageObjectManager.getPaymentPage().getActiveMilitaryEmailTextBox().sendKeys(loginCredentialsData1.userName);
        WaitUtil.untilTimeCompleted(1000);

        pageObjectManager.getPaymentPage().getActiveMilitaryPasswordTextBox().sendKeys(loginCredentialsData1.password);
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getPaymentPage().getActiveMilitarySignInButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        //Confirmation Page
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page",
                getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
    }
}