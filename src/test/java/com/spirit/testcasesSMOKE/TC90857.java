package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test CaseID: TC90857
//Title      : Check In_CP_CI_Passport_Validate an error message shows when Passport Number field is left blank
//Created By : Kartik Chauhan
//Created On : 30-Apr-2019
//Reviewed By: Salim Ansari
//Reviewed On: 1-May-2019
//**********************************************************************************************
public class TC90857 extends BaseClass{
    @Parameters ({"platform"})
    @Test (groups = {"CheckIn" , "RoundTrip" , "DomesticColombia" , "WithIn7Days" , "Adult" , "Guest" , "NonStop","BookIt" ,
            "NoSeats" , "CheckInOptions" , "MasterCard" , "NoBags" , "AddEditPassportInfo" , "TravelInsurancePopUp","PassportUI"})
    public void Check_In_CP_CI_Passport_Validate_an_error_message_shows_when_Passport_Number_field_is_left_blank(@Optional("NA")String platform) {
        //******************Navigate to Bags Page*****************************
        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90857 under SMOKE Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String LANGUAGE 		    = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE 		    = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE   = "PTY";
        final String DEP_DATE 		    = "0";
        final String ARR_DATE 	        = "1";
        final String ADULTS	            = "1";
        final String CHILDREN	        = "0";
        final String INFANT_LAP		    = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 	    = "NonStop";
        final String ARR_Flight 	    = "NonStop";
        final String FARE_TYPE		    = "Standard";
        final String UPGRADE_VALUE		= "BookIt";

        //Option Page Constant Values
        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

        //Payment page Constant values
        final String TRAVEL_GUARD		= "Required";
        final String CARD_TYPE			= "MasterCard";

        //Confirmation Page Constant value
        final String BOOKING_STATUS     = "Confirmed";

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
        //no TG within 24 hours
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

        //**************TO be added by Salim*******************************************
//        //verify by default First name displaying on Passport page
//        ValidationUtil.validateTestStep("Verify By default First name is displaying on Passport Page",
//                JSExecuteUtil.getElementTextValue(getDriver(),pageObjectManager.getPassportPage().getPassportFirstNameText()),scenarioContext.getContext(Context.CONFIRMATION_FIRSTNAME).toString());


        //verify by default Last name displaying on Passport page
        ValidationUtil.validateTestStep("Verify By default Last name is displaying on Passport Page",
                JSExecuteUtil.getElementTextValue(getDriver(),pageObjectManager.getPassportPage().getPassportLastNameText()),scenarioContext.getContext(Context.CONFIRMATION_LASTNAME).toString());

        //Put wait for 3 second
        WaitUtil.untilTimeCompleted(3000);
//Step--6
        //Constant values on Passport page
        String strDepDate = "3";
        String strActualDepDate;

        //Convert the format of Expiration date
        strActualDepDate = TestUtil.getStringDateFormat(strDepDate, "MM/dd/yyyy");

        //click on Passport Expiration Date Text Box
        pageObjectManager.getPassportPage().getPassportExpirationDateTextBox().click();

        //Send specific date to Expiration date Text box
        pageObjectManager.getPassportPage().getPassportExpirationDateTextBox().sendKeys(strActualDepDate);
//Step--7
        //Click on Save button Passport page
        pageObjectManager.getPassportPage().getSaveChangesButton().click();

        //Create wait for 3 second
        WaitUtil.untilTimeCompleted(2000);

        //Click on Passport Without Middle Name link
        pageObjectManager.getPassportPage().getContinueWithoutMiddleNameButton().click();

        //Put wait for 2 second
        WaitUtil.untilTimeCompleted(2000);

        //Put the condition for pop-up requirement of 6 month for passport

        if(TestUtil.verifyElementDisplayed(pageObjectManager.getPassportPage().getPassportSixMonthText())) {
            pageObjectManager.getPassportPage().getUhOhOkButton().click();
        }else {
            System.out.println("No pop-up requirement of 6 month for passport is displayed");
        }

        //Put wait for 2 second
        WaitUtil.untilTimeCompleted(2000);

        //Reservation Summary Page Constant value
        final String PASSPORT_REQUIRED_ERROR     = "Passport Number is required";

        //Verify Passport required error is displaying on Passport page
        ValidationUtil.validateTestStep("Verify Passport required error is displaying on Passport Page",
                pageObjectManager.getPassportPage().getPassportRequiredErrorText().getText(),PASSPORT_REQUIRED_ERROR);

    }
}