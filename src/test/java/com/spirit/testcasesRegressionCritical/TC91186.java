package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.PassengerInfoData;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC91186
//Title       : Check In_CP_CI_Timatic_Passport Pop-up
//Created By  : Kartik chauhan
//Created On  : 25-Jun-2019
//Reviewed By : Salim Ansari
//Reviewed On : 26-Jun-2019
//**********************************************************************************************
public class TC91186 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"CheckIn", "OneWay", "DomesticInternational", "WithIn7Days", "Adult", "Guest", "Nonstop", "BookIt", "NoBags", "NoSeats", "CheckInOptions" , "MasterCard", "AddEditPassportInfo" , "ReservationUI" , "PassportUI"})
    public void Check_In_CP_CI_Timatic_Passport_Popup(@Optional("NA") String platform) {

        /******************************************************************************
         *************************Navigate to Boarding Pass Page***********************
         ******************************************************************************/
        //mention the browser
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91186 under REGRESSION-CRITICAL suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "SAP";
        final String DEP_DATE           = "6";
        final String ARR_DATE           = "NA";
        final String ADULTS             = "1";
        final String CHILDREN           = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "Nonstop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page Constant Value
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //Payment page Constant values
        final String TRAVEL_GUARD       = "NotRequired";
        final String CARD_TYPE          = "MasterCard";
//Step 2
        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().selectOneWayInternationalPopup();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats page methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options page methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment page methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //confirmation page
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        /******************************************************************************
         ***********************Validation to Confirmation Page************************
         ******************************************************************************/
        //declare constant used in validation
        final String BOOKING_STATUS     = "CONFIRMED";
        final String CONFIRMATION_URL   = "book/confirmation";
        final String RES_SUMMARY_URL    = "my-trips/reservation-summary";

        //verify user is navigated to confirmation page
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page",
                getDriver().getCurrentUrl(), CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText().trim().toUpperCase(), BOOKING_STATUS);

        //Store all the basic info
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //Homepage method to check in
        pageMethodManager.getHomePageMethods().loginToMyTrip();

        //Reservation Summary Page Constant value
        final String PASSENGER_HEADER   = "PASSENGER";

        //verify user on Reservation summary Page
        ValidationUtil.validateTestStep("Verify Passenger header is displaying on Reservation page,",
                pageObjectManager.getPassportPage().getPassengerBlockHeader().getText().trim().toUpperCase(), PASSENGER_HEADER);

        //Click on 'Edit/view Passport Link
        pageObjectManager.getPassportPage().getAddEditPassportInfoLink().click();

        //wait until page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("Passenger1");

        //enter valid passport
        pageObjectManager.getPassportPage().getPassengerPassportNumberTextBox().get(0).sendKeys(passengerInfoData.paasportNumber);

        //put wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //Set Greater then Today's Expiration Date
        String strDepDate = "50";
        String strActualDepDate;

        //Convert the format of Expiration date
        strActualDepDate = TestUtil.getStringDateFormat(strDepDate, "MM/dd/yyyy");

        //click on Passport Expiration Date Text Box
        pageObjectManager.getPassportPage().getPassengerExpirationDateTextBox().get(0).click();

        //Send specific date to Expiration date Text box
        pageObjectManager.getPassportPage().getPassengerExpirationDateTextBox().get(0).sendKeys(strActualDepDate);
//STEP--2
        //click on Save Changes Button
        pageObjectManager.getPassportPage().getSaveChangesButton().click();

        //put wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //proceed without middle name update option
        pageObjectManager.getPassportPage().getContinueWithoutMiddleNameButton().click();

        //verify user on Reservation summary Page
        ValidationUtil.validateTestStep("Uh-OH Pop-up is displaying on Passport page,",
                pageObjectManager.getPassportPage().getUhOhOkButton().isDisplayed());
//STEP--3
        //click on Uh Ok Button
        pageObjectManager.getPassportPage().getUhOhOkButton().click();
//STEP--4
        //Verify Expiration date TextBox is displaying as red-invalid box
        ValidationUtil.validateTestStep("Passport Expiration DateTextBox is displaying as red-Invalid box",
                pageObjectManager.getPassportPage().getPassengerExpirationDateRedTextBox().isDisplayed());
//STEP--5
        //click on Save Changes Button
        pageObjectManager.getPassportPage().getSaveChangesButton().click();

        WaitUtil.untilTimeCompleted(2000);

        //proceed without middle name update option
        pageObjectManager.getPassportPage().getContinueWithoutMiddleNameButton().click();

        //wait until page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //verify user is navigated to confirmation page
        ValidationUtil.validateTestStep("Verify User is directed to My Trip reservation Summary Page",
                getDriver().getCurrentUrl(), RES_SUMMARY_URL);
    }
}