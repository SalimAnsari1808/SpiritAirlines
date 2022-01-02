package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.*;
import com.spirit.enums.*;
import com.spirit.managers.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC90835
//Description : Misc_CP_MT_SinglePAX _ Contact info_Verify cancel button works correctly
//Created By : Alex Rodriguez
//Created On : 24-JUL-2019
//Reviewed By: Salim Ansari
//Reviewed On: 29-JUL-2019
//**********************************************************************************************

public class TC90835 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"MyTrips" , "OneWay" , "DomesticDomestic" , "WithIn21Days" , "Adult" , "Guest" , "Nonstop" , "BookIt" , "NoBags" , "NoSeats" , "CheckInOptions" , "MasterCard" , "ReservationUI","AddEditPassengerInfo"})
    public void Misc_CP_MT_SinglePAX_Contact_info_Verify_cancel_button_works_correctly(@Optional("NA") String platform) {

        //************************************************************************************
        //*************************Navigate to Reservation Summary Page***********************
        //************************************************************************************
        //mention the browser
        if(!platform.equals("NA")){
            ValidationUtil.validateTestStep("Starting Test Case ID TC90835 under REGRESSION-CRITICAL suite on " + platform +" Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE 		    = "OneWay";
        final String DEP_AIRPORTS 	    = "AllLocation";
        final String DEP_AIRPORT_CODE 	= "DTW";
        final String ARR_AIRPORTS 	  	= "AllLocation";
        final String ARR_AIRPORT_CODE  	= "LAS";
        final String DEP_DATE 	        = "10";
        final String ARR_DATE 	        = "NA";
        final String ADULTS 		    = "1";
        final String CHILDREN		    = "0";
        final String INFANT_LAP 	    = "0";
        final String INFANT_SEAT	    = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 	    = "Nonstop";
        final String FARE_TYPE 	        = "Standard";
        final String UPGRADE_VALUE 	  	= "BookIt";

        //Options Page Constant Value
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //Payment page Constant values
        final String TRAVEL_GUARD	    = "NotRequired";
        final String CARD_TYPE		    = "MasterCard";

        //Reservation Summary Section Constant Value
        final String MT_PASS_EDIT   	= "my-trips/passenger";
        final String PASS_INFO_TYPE     = "Passenger1";

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
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

        //Store email
        String emailValue = pageObjectManager.getPassengerInfoPage().getContactPersonEmailTextBox().getAttribute("value");
        scenarioContext.setContext(Context.CUSTOMER_EMAIL, emailValue);
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
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //Homepage method to My Trips
        pageMethodManager.getHomePageMethods().loginToMyTrip();

        //Click on 'Edit' Passenger Info Link
        pageObjectManager.getReservationSummaryPage().getContactSectionEditButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());

//Step 1: Validate user lands on the Change Information page
        ValidationUtil.validateTestStep("The user correctly lands on the Change Information page" , getDriver().getCurrentUrl(),MT_PASS_EDIT);

//Step 2: Change all the Stored Contact Information
        pageObjectManager.getPassengerInfoPage().getContactPersonFirstNameTextBox().get(0).clear();
        pageObjectManager.getPassengerInfoPage().getContactPersonLastNameTextBox().get(0).clear();
        pageObjectManager.getPassengerInfoPage().getContactPersonAddressTextBox().clear();

        //create random first name
        String NameValue = TestUtil.getAlphaCharacterString(10);
        //first Name
        pageObjectManager.getPassengerInfoPage().getContactPersonFirstNameTextBox().get(0).sendKeys(NameValue);
        //Last Name
        pageObjectManager.getPassengerInfoPage().getContactPersonLastNameTextBox().get(0).sendKeys(NameValue);
        //Address field
        pageObjectManager.getPassengerInfoPage().getContactPersonAddressTextBox().sendKeys(NameValue);

        pageMethodManager.getPassengerInfoMethods().fillContactNonUSResident();

//Step 3 Click the Cancel Changes Button at bottom of the page

        pageObjectManager.getPassengerInfoPage().getCancelChangesButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());
//Step 4: Verify none of the changes made were stored on Reservation Summary Page
        PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest(PASS_INFO_TYPE);

        String contactName          = pageObjectManager.getReservationSummaryPage().getContactSectionNameText().getText().trim();
        String contactEmail         = pageObjectManager.getReservationSummaryPage().getContactSectionEmailText().getText().trim();

        //validation of data stored on Passenger information during Booking Path
        ValidationUtil.validateTestStep("The Contact Person name is correct" , contactName,passengerInfoData.firstName+" "+passengerInfoData.lastName);

        String messageExpected      =  "We'll keep you posted about any changes to this trip,\nso please double check your email and phone number.";
        String contactInfoMessage   = pageObjectManager.getReservationSummaryPage().getContactSectionVerbageText().getText().trim();

        ValidationUtil.validateTestStep("The Contact message is correct" , contactInfoMessage,messageExpected);
        ValidationUtil.validateTestStep("Contact email matches the passenger information page", emailValue, contactEmail);
    }
}