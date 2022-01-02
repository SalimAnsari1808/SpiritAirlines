package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test CaseID: TC90883
//Title      : Check In_CP_CI_Passport_Validate Passport required fields value acceptance RT INT
// Created By : Kartik Chauhan
//Created On : 01-may-2019
//Reviewed By: Salim Ansari
//Reviewed On: 1-May-2019
/**10/21/19 test case passed, removed active bug tag**/
//**********************************************************************************************
public class TC90883 extends BaseClass{

    @Parameters ({"platform"})
    @Test (groups = {"CheckIn" , "RoundTrip" , "DomesticColombia" , "WithIn7Days" , "Adult" , "Guest" , "Connecting" , "BookIt" ,
            "NoBags" , "NoSeats" , "CheckInOptions" , "PassportUI" , "TravelInsurancePopUp" , "MasterCard" , "AddEditPassportInfo"})
    public void Check_In_CP_CI_Passport_Validate_Passport_required_fields_value_acceptance_RT_INT(@Optional("NA")String platform) {
        //**************************Navigate to Bags Page*****************************
        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90883 under SMOKE Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String LANGUAGE 		    = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE 		    = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "LAS";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE   = "BOG";
        final String DEP_DATE 		    = "0";
        final String ARR_DATE 	        = "1";
        final String ADULTS	            = "1";
        final String CHILDREN	        = "0";
        final String INFANT_LAP		    = "0";
        final String INFANT_SEAT        = "0";
        final String RED_COLOR 		    = "rgb(220, 0, 0)";
        final String ASTERISK_SYMBOL    = "*";
        final String ASTERISK_NONE	    = "none";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 	    = "Connecting";
        final String ARR_Flight 	    = "Connecting";
        final String FARE_TYPE		    = "Standard";
        final String UPGRADE_VALUE		= "BookIt";

        //Option Page Constant Values
        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

        //Payment page Constant values
        final String TRAVEL_GUARD		= "NotRequired";
        final String CARD_TYPE			= "MasterCard";

        //Confirmation Page Constant value
        final String BOOKING_STATUS     = "Confirmed";

        //declare variable used in validation
        String asteriskColor = null;
        String asteriskSym 	 = null;
//Step--1
        //open browser
        openBrowser( platform);

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
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags page
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
        WaitUtil.untilPageLoadComplete(getDriver());

        //Seats Page
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Option Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment page
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
//        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //Confirmation page closing ROKT Popup
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //Coping Confirmation Code for retrieve on MT
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //Verifying booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page", pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
//Step--2
        //login to checkIn Path
        pageMethodManager.getHomePageMethods().loginToCheckIn();

        //Reservation Summary Page Constant value
        final String PASSENGER_HEADER     = "Passenger";

        //verify user on Reservation summary Page
        ValidationUtil.validateTestStep("Verify Passenger header is displaying on Reservation page,",
                pageObjectManager.getPassportPage().getPassengerBlockHeader().getText(),PASSENGER_HEADER);
//STep--4
        //Click on 'Edit/view Passport Link
        pageObjectManager.getPassportPage().getAddEditPassportInfoLink().click();

//STEP--5

        //get the asterisk symbol and color
        asteriskColor = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getPassportPage().getTitletext(), "color");
        asteriskSym = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getPassportPage().getTitletext(), "content");

        //verify First Name label should have a asterisk to show that it is required
        ValidationUtil.validateTestStep("Title verbiage is displaying with asterisk", asteriskSym,ASTERISK_SYMBOL);

        //verify First Name label color of asterisk
        ValidationUtil.validateTestStep("Title verbiage is displaying with red color of asterisk", asteriskColor,(RED_COLOR));


        //get the asterisk symbol and color
        asteriskColor = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getPassportPage().getCountryOfResidencetext(), "color");
        asteriskSym = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getPassportPage().getCountryOfResidencetext(), "content");

        //verify First Name label should have a asterisk to show that it is required
        ValidationUtil.validateTestStep("Country of Residence verbiage is displaying with asterisk", asteriskSym,ASTERISK_SYMBOL);

        //verify First Name label color of asterisk
        ValidationUtil.validateTestStep("Country of Residence verbiage is displaying with red color of asterisk", asteriskColor,(RED_COLOR));

        //get the asterisk symbol and color
        asteriskColor = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getPassportPage().getPassportNumbertext(), "color");
        asteriskSym = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getPassportPage().getPassportNumbertext(), "content");

        //verify First Name label should have a asterisk to show that it is required
        ValidationUtil.validateTestStep("Passport Number verbiage is displaying with asterisk", asteriskSym,ASTERISK_SYMBOL);

        //verify First Name label color of asterisk
        ValidationUtil.validateTestStep("Passport Number verbiage is displaying with red color of asterisk", asteriskColor,(RED_COLOR));

        //get the asterisk symbol and color
        asteriskColor = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getPassportPage().getIssuingCountrytext(), "color");
        asteriskSym = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getPassportPage().getIssuingCountrytext(), "content");

        //verify First Name label should have a asterisk to show that it is required
        ValidationUtil.validateTestStep("Issuing Country verbiage is displaying with asterisk", asteriskSym,ASTERISK_SYMBOL);

        //verify First Name label color of asterisk
        ValidationUtil.validateTestStep("Issuing Country verbiage is displaying with red color of asterisk", asteriskColor,(RED_COLOR));

        //get the asterisk symbol and color
        asteriskColor = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getPassportPage().getExpirationDatetext(), "color");
        asteriskSym = JSExecuteUtil.getElementAfterProperty(getDriver(), pageObjectManager.getPassportPage().getExpirationDatetext(), "content");

        //verify First Name label should have a asterisk to show that it is required
        ValidationUtil.validateTestStep("Expiration Date verbiage is displaying with asterisk", asteriskSym,ASTERISK_SYMBOL);
//STEP--6
        //verify First Name label color of asterisk
        ValidationUtil.validateTestStep("Expiration Date verbiage is displaying with red color of asterisk", asteriskColor,(RED_COLOR));
//STEP--7
        //enter valid passport
        pageObjectManager.getPassportPage().getPassengerPassportNumberTextBox().get(0).sendKeys("ALDKJDI2");

        //put wait for 2 sec
        WaitUtil.untilTimeCompleted(3000);

//STEP--8
        //Set Greater then Today's Expiration Date
        String strDepDate = "3";
        String strActualDepDate;

        //Convert the format of Expiration date
        strActualDepDate = TestUtil.getStringDateFormat(strDepDate, "MM/dd/yyyy");

        //click on Passport Expiration Date Text Box
        pageObjectManager.getPassportPage().getPassengerExpirationDateTextBox().get(0).click();

        //Send specific date to Expiration date Text box
        pageObjectManager.getPassportPage().getPassengerExpirationDateTextBox().get(0).sendKeys(strActualDepDate);
//STEP--8
        //click on Save Changes Button
        pageObjectManager.getPassportPage().getSaveChangesButton().click();

        //put wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

//STEP--9
        //proceed without middle name update option
        pageObjectManager.getPassportPage().getContinueWithoutMiddleNameButton().click();
//STEP--10
        //click on Uh Ok Button
        pageObjectManager.getPassportPage().getUhOhOkButton().click();

        //click on Save Changes Button
        pageObjectManager.getPassportPage().getSaveChangesButton().click();

        //put wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //proceed without middle name update option
        pageObjectManager.getPassportPage().getContinueWithoutMiddleNameButton().click();

        //wait till page load completely
        WaitUtil.untilPageLoadComplete(getDriver());

        //put wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //Passport Page Constant value
        final String CHECKIN_SUMMARY_URL	= "check-in/reservation-summary";

        //verify user land on Check-in page
        ValidationUtil.validateTestStep("User land on Check-in page",getDriver().getCurrentUrl(), CHECKIN_SUMMARY_URL);

    }
}