package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC90708
//Title       : CP_CI_INT_Travel Guard_U.S. Residents_within 24HRs
//Description : Validate that the travel guard modal is correctly displayed for international
//              US resident
//Created By  : Anthony Cardona
//Created On  : 03-Apr-2019
//Reviewed By : Salim Ansari
//Reviewed On : 08-Apr-2019
//**********************************************************************************************
public class TC90708 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"CheckIn" , "RoundTrip" , "DomesticInternational" , "WithIn7Days" , "Adult" , "Guest" , "NonStop" ,"BookIt",
            "CarryOn" , "CheckedBags" ,"Standard" , "ShortCutBoarding","CheckInOptions", "MasterCard" , "TravelInsuranceBlock" ,"PaymentUI"})
    public void CP_CI_INT_Travel_Guard_US_Residents_within_24HRs(@Optional("NA") String platform) {

        //*************************Navigate to Boarding Pass Page**********************
        //mention the browser
        if(!platform.equals("NA")){
            ValidationUtil.validateTestStep("Starting Test Case ID TC90708 under SMOKE suite on " + platform +" Browser", true);
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
        final String ARR_DATE 	    		= "7";
        final String ADULTS 		       	= "1";
        final String CHILDREN		      	= "0";
        final String INFANT_LAP 	     	= "0";
        final String INFANT_SEAT	     	= "0";

        //Flight Availability Page Constant Values
        final String SORTING_TYPE           = "Departure";
        final String DEP_FLIGHT 	    	= "NonStop";
        final String ARR_Flight 	    	= "NonStop";
        final String FARE_TYPE 	    		= "Standard";
        final String UPGRADE_VALUE 	  	    = "BookIt";

        //Options Page Constant Value
        final String OPTIONS_VALUE          = "CheckInOption:MobileFree";

        //Payment page Constant values
        final String TRAVEL_GUARD		    = "NotRequired";
        final String CARD_TYPE			    = "MasterCard";


        //Check-In Bags Page constant values
        final String CHECKIN_BUY_BAGS       = "Purchase";
        final String CHECKIN_DEP_BAGS       = "Carry_1|Checked_2";
        final String CHECKIN_BAG_FARE       = "Standard";

        //checkin Seats Page constant value
        final String CHECKIN_BUY_SEATS      = "Purchase";
        final String CHECKIN_DEP_SEATS      = "Standard";

        //car page constant value
        final String CHECKIN_BUY_CAR        = "CancelCar";

        //payment page constant value
        final String CHECKIN_TRAVEL_GUARD   = "Required";

        //Boarding pass constant values
        final String CHECKIN_HAZMAT         = "Accept";
        final String CHECKIN_BP_PRINT       = "Print";
        final String CHECKIN_BP_EMAIL       = "NA";

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

        //Flight Availability Methods
//        pageMethodManager.getFlightAvailabilityMethods().selectSortingOption("Dep", SORTING_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
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
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

//Step 3, 4
        //Homepage method to check in
        pageMethodManager.getHomePageMethods().loginToCheckIn();
        pageMethodManager.getReservationSummaryPageMethods().clickCheckInAndGetBoardingPass();

//Step 5
        //insert passport information
        pageMethodManager.getPassportPageMethods().fillPassportInformation();

//Step 6
        //get bags pop up  and add a carry-on and 2 checked bags. Get the price
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSaveOnBagsPopup(CHECKIN_BUY_BAGS);
        pageMethodManager.getBagsPageMethods().selectDepartingBags(CHECKIN_DEP_BAGS);
        double bagsPrice = Double.parseDouble(pageObjectManager.getBagsPage().getBagsTotalContainerAmountTotalText().getText().replace("$","").trim());
        pageMethodManager.getBagsPageMethods().selectBagsFare(CHECKIN_BAG_FARE);

        //get the seats pop-up and select any available seat. Get the price
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSelectYourSeatPopup(CHECKIN_BUY_SEATS);
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(CHECKIN_DEP_SEATS);
        double seatsPrice = getSeatsPrice();
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //add prices together
        double ancillaryPrice = bagsPrice + seatsPrice;

        //check for last chance Car popup
        pageMethodManager.getCarPageMethods().lastChanceCarPopup(CHECKIN_BUY_CAR);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//Step 7
        //check final travel guard popup
        ValidationUtil.validateTestStep("Final Travel Guard Popup is display on Payment page Check-In path" , TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getTravelGuardPopupHeaderText()));
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);  //changed lbl_NoTravelGuardPopup to //label[contains(@for,'radio-travel-guard-no') or contains(@for,'radio-no')]

//Step 8
        //validate travel guard text on the Payment page is displayed
//        validateTextIsDisplayed("100% Trip Cost Trip Interruption");
//        validateTextIsDisplayed("$500 Baggage Delay");
//        validateTextIsDisplayed("$500 Baggage & Personal Effects");

        //payment page common method
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(CHECKIN_TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

//Accept and print boarding pass
        pageMethodManager.getReservationSummaryPageMethods().acceptRejectHazardousMaterialPopup(CHECKIN_HAZMAT);
        pageMethodManager.getReservationSummaryPageMethods().printEmailYourBoardingPassPopup(CHECKIN_BP_PRINT,CHECKIN_BP_EMAIL);

        WaitUtil.untilPageLoadComplete(getDriver());

        //************************Validation to Boarding Pass Page*********************
        //declare constant used validation
        final String BOARDING_PASS_URL     = "boarding-pass";

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //wait for page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //verify BP is printed
        ValidationUtil.validateTestStep("User redirected to the Boarding pass page" , getDriver().getCurrentUrl().contains(BOARDING_PASS_URL));
    }

    //returns the seat price
    public double getSeatsPrice()
    {
        double seatsPrice = 0;

        seatsPrice = Double.parseDouble(pageObjectManager.getSeatsPage().getSeatsTotalPriceText().getText().replace("$", "").trim());

        return seatsPrice;
    }

    //validate the text is displayed
    public void validateTextIsDisplayed(String text)
    {
        WaitUtil.untilPageLoadComplete(getDriver());
        boolean isDisplayed = TestUtil.verifyElementDisplayed(getDriver(),By.xpath("//p[contains(text(),'" + text + "')]"));
        ValidationUtil.validateTestStep("Travel Guard text is display" , isDisplayed);
    }
}
