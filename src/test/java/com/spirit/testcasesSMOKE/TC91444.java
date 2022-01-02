package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.PassengerInfoData;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

// **********************************************************************************************
// Test Case ID: TC91444
// TestCase :CP_BP_Payment Page_Travel Guard_Vacation_EDIT Bags_Add TG
// Description: Create Vacation Booking, Add bag on Payment page and then verify the same in "YOUR ITINERARY' and then select tG and make booking
// Created By : Kartik Chauhan
// Created On : 16-May-2019
// Reviewed By: Salim Ansari
// Reviewed On: 17-May-2019
// **********************************************************************************************
public class TC91444 extends BaseClass {

    @Parameters ({"platform"})
    @Test(groups = {"BookPath" , "RoundTrip" , "FlightCar" , "DomesticDomestic" , "Outside21Days" , "Adult" ,
            "Guest" , "BookIt" , "CarryOn" , "CheckedBags" , "NoSeats" , "TravelInsuranceBlock" , "Discover"
            ,"CheckInOptions", "MandatoryFields","PaymentUI"})
    public void CP_BP_Payment_Page_Travel_Guard_Vacation_EDIT_Bags_Add_TG (@Optional("NA")String platform){

        if(!platform.equals("NA")){
            ValidationUtil.validateTestStep("Starting Test Case ID TC91444 under SMOKE suite on " + platform +" Browser", true);
        }
        /******************************************************************************
         ****************************Navigate to Passenger Info Page*****************
         ******************************************************************************/
        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Vacation";
        final String TRIP_TYPE 			= "Flight+Car";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LAS";
        final String DEP_DATE 			= "25";
        final String ARR_DATE 			= "30";
        final String ADULTS				= "1";
        final String CHILDS				= "0";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT		= "0";
        final String DRIVER_AGE 		= "25+";

        //Flight Availability Page Constant Values
        final String FARE_TYPE			= "Standard";
        final String UPGRADE_VALUE		= "BookIt";

        //All Bags Constant
        final String ALL_BAGS           = "1 Carry-On , 1 Checked Bag";

        //Optional Page constant values
        final String OPTION_VALUE		= "CheckInOption:MobileFree";

        //Payment page Constant values
        final String TRAVEL_GUARD		    = "NotRequired";
        final String CARD_TYPE			    = "DiscoverCard1";

//Step1
        //open browser
        openBrowser( platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //wait till page is load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //Click on Book Car Button
        pageObjectManager.getCarPage().getBookCarButton().get(0).click();

        //wait till page is load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //verify if activity will display, continue without it
        if(getDriver().getCurrentUrl().contains("activi")) {
            //Select continue without Activity
            pageObjectManager.getActivityPage().getContinueWithoutActivityButton().click();
        }

        //Hselect option on Bundle fare pop-up
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //wait till page is load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

        //get adult mandatory details
        PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("Passenger1");

        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPassengerInfoPage().getPrimaryDriverDropDown(),passengerInfoData.firstName+" "+passengerInfoData.lastName);

        //wait for 2 second
        WaitUtil.untilTimeCompleted(2000);

        //click with continue button
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seat Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Option Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//Step-4
        //Payment Page Methods
        pageObjectManager.getPaymentPage().getTotalDueText().click();

        //Put wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);
//STEP--5
        //Make click on Edit bags under Total Breakdown
        pageObjectManager.getPaymentPage().getTotalDueBagsEditText().click();

        //Bags Page constant values
        final String DEP_BAGS 			= "Carry_1|Checked_1";
//STEP--6
        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

        //Seat Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Option Page Methods
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
//STEP--7
        //Make a click on Passenger Arrow icon
        pageObjectManager.getPaymentPage().getYourItineraryPassengerInfoArrowIcon().click();

        //Put wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //validate verified bags on Purchase page
        ValidationUtil.validateTestStep("All Purchased Bags are displaying on Payment Page under 'YOUR Itinerary' section",
                pageObjectManager.getPaymentPage().getAllBagsInfoText().get(0).getText(),ALL_BAGS);

        //Put wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);
//STEP--8
        //Travel Guard selection
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
//STEP--9
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
//************************************************************************************************************************
//*****************************Below lines are commented as vacation pages are restricted*********************************
//************************************************************************************************************************

        pageObjectManager.getPaymentPage().getBookTripButton().click();
//STEP--10
		/******************************************************************************
		 ***********************Validation to Confirmation Page****************************
		 ******************************************************************************/
		//declare constant used in validation
		final String BOOKING_STATUS = "Confirmed";
		final String CONFIRMATION_URL = "book/confirmation";
		final String TERMS_ERROR = "You must agree to the Terms and Conditions in order to complete your reservation.";

		//validate error on Purchase page
		ValidationUtil.validateTestStep("Verify Terms and Condition error message appear on Payment Page",
				pageObjectManager.getCommon().getErrorMessageLabel().getText().trim().contains(TERMS_ERROR));

		//accept and complete the booking
		pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

		//Confirmation Page Methods
		//close ROKT pop-up
		pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

		//verify booking is confirmed
		ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page", getDriver().getCurrentUrl().contains(CONFIRMATION_URL));

		//verify booking is confirmed
		ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page", pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText().contains(BOOKING_STATUS));
//
    }
}