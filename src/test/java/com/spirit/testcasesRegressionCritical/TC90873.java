package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.PassengerInfoData;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC90873
//Title       : 35400 Misc_CP_CI_SinglePAX _ Edit-Add Passport_apostrophe in middle name
//Created By  : Anthony Cardona
//Created On  : 23-Jul-2019
//Reviewed By : Salim Ansari
//Reviewed On : 25-Jul-2019
//**********************************************************************************************

public class TC90873 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"CheckIn" , "OneWay" , "DomesticColombia" , "WithIn7Days" , "Adult" , "Guest" , "Connecting" , "BookIt" , "NoBags" , "NoSeats" , "CheckInOptions" , "Visa" , "AddEditPassportInfo" , "PassportUI"})
    public void Misc_CP_CI_SinglePAX_Edit_Add_Passport_apostrophe_in_middle_name(@Optional("NA") String platform) {

        /******************************************************************************
         *************************Navigate to Boarding Pass Page***********************
         ******************************************************************************/

        //mention the browser
        if(!platform.equals("NA")){
            ValidationUtil.validateTestStep("Starting Test Case ID TC90873 under REGRESSION-CRITICAL suite on " + platform +" Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE               = "English";
        final String JOURNEY_TYPE           = "Flight";
        final String TRIP_TYPE 		    	= "OneWay";
        final String DEP_AIRPORTS 		    = "AllLocation";
        final String DEP_AIRPORT_CODE 	    = "FLL";
        final String ARR_AIRPORTS 	  	    = "AllLocation";
        final String ARR_AIRPORT_CODE  	    = "BOG";
        final String DEP_DATE 	    		= "0";
        final String ARR_DATE 	    		= "NA";
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
        final String CARD_TYPE			    = "VisaCard";

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
        final String RES_SUMMARY_URL	= "check-in/reservation-summary";

        //verify user is navigated to confirmation page
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page",
                getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText().trim().toUpperCase(),BOOKING_STATUS);

        //Store all the basic info
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //Homepage method to check in
        pageMethodManager.getHomePageMethods().loginToCheckIn();

        //Reservation Summary Page Constant value
        final String PASSENGER_HEADER     = "PASSENGER";

        //verify user on Reservation summary Page
        ValidationUtil.validateTestStep("Verify Passenger header is displaying on Reservation page,",
                pageObjectManager.getPassportPage().getPassengerBlockHeader().getText().trim().toUpperCase(),PASSENGER_HEADER);

        //Click on 'Edit/view Passport Link
        pageObjectManager.getPassportPage().getAddEditPassportInfoLink().click();

        WaitUtil.untilPageLoadComplete(getDriver());

//Step 5: Validate user lands on the passenger information page
        ValidationUtil.validateTestStep("The user correctly lands on the passport page" , getDriver().getCurrentUrl(),"check-in/travel-docs");

        PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("Passenger1");

//Step 1: input middle name
        pageObjectManager.getPassportPage().getPassengerMiddleNameTextBox().get(0).sendKeys("D'Angelo");

//Step 2: Enter Valid Passport number and expiration date
        //enter valid passport
        pageObjectManager.getPassportPage().getPassengerPassportNumberTextBox().get(0).sendKeys(passengerInfoData.paasportNumber);

        //put wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //Set Greater then Today's Expiration Date
        String expirationDate = TestUtil.getStringDateFormat("200", "MM/dd/yyyy");

        //click on Passport Expiration Date Text Box
        pageObjectManager.getPassportPage().getPassengerExpirationDateTextBox().get(0).click();

        //Send specific date to Expiration date Text box
        pageObjectManager.getPassportPage().getPassengerExpirationDateTextBox().get(0).sendKeys(expirationDate);

//Step 3: Click on save changes
        //click on Save Changes Button
        pageObjectManager.getPassportPage().getSaveChangesButton().click();

        //put wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);
        WaitUtil.untilPageLoadComplete(getDriver());

//Step 4: Passport Changes saved and taken back to the reservation summary page
        //verify user is navigated to confirmation page
        ValidationUtil.validateTestStep("Verify User is directed to check in reservation Summary Page",
                getDriver().getCurrentUrl(),RES_SUMMARY_URL);

    }
}