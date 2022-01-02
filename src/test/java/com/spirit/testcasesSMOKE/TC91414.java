package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC91414
//Test Name: Task 23071: 31366 CP_BP_Payment Page_ FS_use another card option after saving from past booking with res credit
//Description: Validating that a member with credit cards saved is allowed to use another one
//Created By : Gabriela
//Created On : 17-MAY-2019
//Reviewed By: Salim Ansari
//Reviewed On: 19-MAY-2019
//**********************************************************************************************

public class TC91414 extends BaseClass {

    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "FreeSpirit" , "NonStop", "BookIt" ,
            "NoBags" , "NoSeats" , "CheckInOptions" , "ReservationCredit" , "Visa","PaymentUI"})
    public void CP_BP_Payment_Page_FS_use_another_card_option_after_saving_from_past_booking_with_res_credit(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91414 under SMOKE Suite on " + platform + " Browser", true);
        }

        //Booking Path Constant variables
        final String LANGUAGE               = "English";
        final String LOGIN_ACCOUNT          = "FSSavedCards";
        final String JOURNEY_TYPE           = "Flight";
        final String TRIP_TYPE              = "Oneway";
        final String DEP_AIRPORTS           = "AllLocation";
        final String DEP_AIRPORT_CODE       = "FLL";
        final String ARR_AIRPORTS           = "AllLocation";
        final String ARR_AIRPORT_CODE       = "IAH";
        final String DEP_DATE               = "5";
        final String ARR_DATE               = "NA";
        final String ADULT                  = "1";
        final String CHILD                  = "0";
        final String INFANT_LAP             = "0";
        final String INFANT_SEAT            = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT             = "Nonstop";
        final String FARE_TYPE              = "Standard";
        final String UPGRADE_VALUE          = "BookIt";

        //Options Page Constant Value
        final String OPTIONS_VALUE          = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String CARD_TYPE              = "MasterCard";
        final String TRAVEL_GAURD           = "Notrequired";

//-- Step 1:Perquisites Book a Flight with any number of PAX continue to the payment page. Make sure you make the
// booking with a FS member who has a credit card saved already and that you have a res credit  Note: Make sure your
// res credit does not cover entire total of the flight. To save a credit card to your FS member you need to complete a
// booking and select the "Save this card for future bookings" check box on the payment page

        //Open Browser
        openBrowser(platform);

        //Land on Spirit home page
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);

        //create resCredit
        createResCredit();

        //Starting the booking and landing on Payment Section
        //Home Page Methods
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        String PaxName =  pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0).getText();
        String PaxLastName = pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(0).getText();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats page methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options page methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//-- Step 2: Verify the Itinerary section
        ValidationUtil.validateTestStep("Departure flight is displayed",
                pageObjectManager.getPaymentPage().getDepartingFlightCityNameText().get(0).isDisplayed());

        ValidationUtil.validateTestStep("Passenger name is correct",
                pageObjectManager.getPaymentPage().getPassengerNameText().get(0).getText(),PaxName + PaxLastName);
//-- Step 3: Select 'No" radio button to add the insurance
        pageObjectManager.getPaymentPage().getNoTravelGuardLabel().click();

//-- Step 4: Click the Redeem a voucher, credit or E-Gift card drop down
//-- Step 5: Enter your PNR number for the booking with the res credit or enter your 8 character travel voucher number
//-- Step 6: Verify the pricing on the total due by clicking the drop down
        pageMethodManager.getPaymentPageMethods().applyReservationCredit();

//-- Step 7: Click the credit card drop down
        pageObjectManager.getPaymentPage().getSelectCardDropDown().click();

//-- Step 8: Click the 'Other' option
        //Select savedCards = new Select(pageObjectManager.getPaymentPage().getSelectCardDropDown());
        //savedCards.selectByValue("0: Other");
        TestUtil.selectDropDownUsingValue(pageObjectManager.getPaymentPage().getSelectCardDropDown(),"0: Other");

//-- Step 9: Enter Information for the entire payment section
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails("VisaCard");

//-- Step 10: Complete your payment and retrieve your PNR
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

//-- Step 11: Reach Confirmation page - verify pricing and Res. Credit was deducted
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //TODO: Unable to verify the booking on SkySpeed
//-- Step 12: Retrieve PNR via SkySpeed - Verify Res. Credit deduction and booking details
    }
    private void createResCredit() {
        //Reservation Credit Path Constant variables
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "IAH";
        final String DEP_DATE           = "3";
        final String ARR_DATE           = "NA";
        final String ADULTS             = "1";
        final String CHILDREN           = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";
        //Options Page Constant Values
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";
        //payment page constant value
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_CARD        = "NotRequired";
        //Home Page Methods
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
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);
        //Passenger Info Methods
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
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_CARD);
        //confirmation page
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getHomePageMethods().loginToMyTrip();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getReservationSummaryPageMethods().createVoucherReservationCredit();
        WaitUtil.untilTimeCompleted(1200);
        WaitUtil.untilPageLoadComplete(getDriver());
    }
}