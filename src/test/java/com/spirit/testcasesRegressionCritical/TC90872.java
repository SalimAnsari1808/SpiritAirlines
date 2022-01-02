package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.*;
import org.openqa.selenium.Keys;
import org.testng.annotations.*;

//**********************************************************************************************
//TODO: IN:24840 PROD:MP: Epic: CI: Timatic: Passport Pop-up
//Test Case ID: TC90872
//Title       : Misc_CP_CI_SinglePAX _ Edit-Add Pasport_Validate passport expiration date one day after return day
//Created By  : Anthony Cardona
//Created On  : 24-Jul-2019
//Reviewed By : Salim Ansari
//Reviewed On : 25-Jul-2019
//**********************************************************************************************

public class TC90872 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"ActiveBug" , "CheckIn" , "RoundTrip" , "DomesticInternational" , "WithIn7Days" , "Adult" , "Guest" , "Connecting" , "BookIt" , "NoBags" , "NoSeats" , "CheckInOptions" , "MasterCard" , "AddEditPassportInfo" , "PassportUI"})
    public void Misc_CP_CI_SinglePAX_Edit_Add_Pasport_Validate_passport_expiration_date_one_day_after_return_day(@Optional("NA") String platform) {

        /******************************************************************************
         *************************Navigate to Boarding Pass Page***********************
         ******************************************************************************/

        //mention the browser
        if(!platform.equals("NA")){
            ValidationUtil.validateTestStep("Starting Test Case ID TC90872 under REGRESSION-CRITICAL suite on " + platform +" Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE               = "English";
        final String JOURNEY_TYPE           = "Flight";
        final String TRIP_TYPE 		    	= "RoundTrip";
        final String DEP_AIRPORTS 		    = "AllLocation";
        final String DEP_AIRPORT_CODE 	    = "FLL";
        final String ARR_AIRPORTS 	  	    = "AllLocation";
        final String ARR_AIRPORT_CODE  	    = "CUN";
        final String DEP_DATE 	    		= "0";
        final String ARR_DATE 	    		= "10";
        final String ADULTS 		       	= "1";
        final String CHILDREN		      	= "0";
        final String INFANT_LAP 	     	= "0";
        final String INFANT_SEAT	     	= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 	    	= "AutoSelect";
        final String FARE_TYPE 	    		= "Standard";
        final String UPGRADE_VALUE 	  	    = "BookIt";

        //Options Page Constant Value
        final String OPTIONS_VALUE          = "CheckInOption:MobileFree";

        //Payment page Constant values
        final String TRAVEL_GUARD		    = "NotRequired";
        final String CARD_TYPE			    = "MasterCard";

        final String EXPIRATION_DATE_ERROR  = "Expiration Date is invalid.";
        final String RESERVATION_SUMMARY_URL= "check-in/reservation-summary";

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

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", DEP_FLIGHT);
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
        final String BOOKING_STATUS = "CONFIRMED";

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText().trim().toUpperCase(),BOOKING_STATUS);

        //Store all the basic info
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //Homepage method to check in
        pageMethodManager.getHomePageMethods().loginToCheckIn();

        //Click on 'Edit/view Passport Link
        pageObjectManager.getPassportPage().getAddEditPassportInfoLink().click();
        WaitUtil.untilPageLoadComplete(getDriver());

//Step 4: Validate the user is taken to the passport page
        ValidationUtil.validateTestStep("The user correctly lands on the passport page" , getDriver().getCurrentUrl().contains("check-in/travel-docs"));

        pageObjectManager.getPassportPage().getPassengerMiddleNameTextBox().get(0).sendKeys("TEST");
//Step 1: Input passport number using only numbers
        pageObjectManager.getPassportPage().getPassengerPassportNumberTextBox().get(0).sendKeys("123456789");

        WaitUtil.untilTimeCompleted(2000);

//Step 2: input returning date as expiration date
        String strActualDepDate;

        //Convert the format of Expiration date
        strActualDepDate = TestUtil.getStringDateFormat(Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_ARR_DATE).toString()) , "MM/dd/yyyy");

        //click on Passport Expiration Date Text Box
        pageObjectManager.getPassportPage().getPassengerExpirationDateTextBox().get(0).click();

        //Send specific date to Expiration date Text box
        pageObjectManager.getPassportPage().getPassengerExpirationDateTextBox().get(0).sendKeys(strActualDepDate + Keys.TAB);
        ValidationUtil.validateTestStep("Expiration date is invalid error message is correctly displayed" ,
                pageObjectManager.getCommon().getErrorMessageLabel().getText(),EXPIRATION_DATE_ERROR);

        pageObjectManager.getPassportPage().getPassengerExpirationDateTextBox().get(0).clear();


//Step 3: Input expiration date as one day after return flight date.
        strActualDepDate = TestUtil.getStringDateFormat(Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_ARR_DATE).toString()) + 1, "MM/dd/yyyy");

        //click on Passport Expiration Date Text Box
        pageObjectManager.getPassportPage().getPassengerExpirationDateTextBox().get(0).click();

        //Send specific date to Expiration date Text box
        pageObjectManager.getPassportPage().getPassengerExpirationDateTextBox().get(0).sendKeys(strActualDepDate);

        pageObjectManager.getPassportPage().getSaveChangesButton().click();
        WaitUtil.untilTimeCompleted(2000);

        //TODO: IN:24840 PROD:MP: Epic: CI: Timatic: Passport Pop-up
        ValidationUtil.validateTestStep("The Uh Oh Message is not displayed when passport expiration date is one day after the return date : Pass" , !TestUtil.verifyElementDisplayed(pageObjectManager.getPassportPage().getUhOhOkButton()));
        ValidationUtil.validateTestStep("The Middle Name modal is displayed on the passport page : Pass" , TestUtil.verifyElementDisplayed(pageObjectManager.getPassportPage().getMiddleNameNotFoundPopUpHeaderText()));

        //Click continue without middle name
        pageObjectManager.getPassportPage().getContinueWithoutMiddleNameButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        //user can continue to the reservation summary page
        //wait until page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //verify user is navigated to confirmation page
        ValidationUtil.validateTestStep("Verify User is directed to CHECK-IN reservation Summary Page",
                getDriver().getCurrentUrl(),RESERVATION_SUMMARY_URL);
    }
}