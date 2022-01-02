package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//TODO: [IN:25508]	Goldfinger R1: CP: BP: Fligt+Hotel: Availability Page: Unable to book selectees hotels when more than 1 infant are present in the booking
//Test Case ID: TC373444
//Description: Task 27878: TC373444 - 004 - CP - Payment Declined - Flight + Hotel - Free Spirit Mastercard
// Created By: Gabriela
//Created On: 30-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC373444 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"ActiveBug", "BookPath", "RoundTrip", "DomesticInternational","FlightHotel", "Outside21Days", "Adult","InfantSeat","InfantLap", "FSMasterCard", "NonStop", "BookIt", "NoBags", "NoSeats", "CheckInOptions","OptionalUI", "MasterCard", "PaymentUI", "HotelsUI"})
    public void CP_Payment_Declined_Flight_Hotel_Free_Spirit_Mastercard(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373444 under GoldFinger Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String LOGIN_ACCOUNT      = "FSMCEmail";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "CUN";
        final String DEP_DATE           = "132";
        final String ARR_DATE           = "133";
        final String ADULT              = "2";
        final String CHILD              = "0";
        final String INFANT_LAP         = "1";
        final String INFANT_SEAT        = "1";

        //Flight Availability Page Constant Values
        final String SAVE_UPGRADE       = "BookIt";

        //Options Page Constant Values
        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String DECLINED_MASTER    = "DeclinedMaster";
        final String TRAVEL_GUARD       = "NotRequired";
        final String DECLINED_CARD_ERROR   = "Were sorry, an unknown error has occurred. Your card has not been charged. Please check that all payment information below matches what is printed on your card (including cardholder name) or try another card.";

//- Step 1: Open GoldFinger test environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 2: Login as a FS Mastercard holder member with a Declined Free Spirit Mastercard
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);

//- Step 3: Go to Vacation tab select F+H
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);

//- Step 4: Create a DOM to INT booking 2 ADT + 1 Lap + 1 child, 1 Room, outside 3 months and select search
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 5: Fill DOB for all child PAX to be under 2yrs old, "Lap child and does not require a seat" and the Child, then select "Continue"
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

//- Step 6: Under review your flights, make sure you have a departing and returning flight preselected
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating Departing journey is selected ",
                TestUtil.verifyElementDisplayed(pageObjectManager.getFlightAvailabilityPage().getSelectedDepatureFlightBlockPanel()));

        ValidationUtil.validateTestStep("Validating Returning journey is selected ",
                TestUtil.verifyElementDisplayed(pageObjectManager.getFlightAvailabilityPage().getSelectedArrivalFlightBlockPanel()));

        //Storing journey information for validation
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();

//- Step 7: Before selecting a hotel, you must check HBG to make sure the hotel will be refundable with no cancellation fees.
//- Step 8: Select the ROOMS FROM $XXX.XX button and then select room
        //hotel pending to verification for INT city
        //TODO: [IN:25508]	Goldfinger R1: CP: BP: Fligt+Hotel: Availability Page: Unable to book selectees hotels when more than 1 infant are present in the booking
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("Fiesta Americana Condesa Cancun All Inclusive","NA");

//- Step 9: On the Car availability page, scroll down and select Continue without Car.
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getCarPage().getCarsPageContinueWithoutCarButton().click();

//- Step 10: Select Book it
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(SAVE_UPGRADE);

//- Step 11: Enter all information for other passenger(s), select primary driver and continue
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageObjectManager.getPassengerInfoPage().getInfantTravelingWithCarSeatCheckBox().get(0).click();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Step 12 & 13: Click on Continue without bags & Click on I dont need bags
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//- Step 14: Click on continue without selecting seats
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//- Step 15: Validate Hotel is shown selected, scroll down and select I'll check in at Spirit/Mobile for free and continue with booking
        ValidationUtil.validateTestStep("Validating hotel shown as selected on Options Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getHotelContainerSelectedText()));

        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//- Step 16: On payment page, verify flight and hotel info are correct, enter declined Mastercard credit card and book reservation
        //Validating HOtel information displayed on Payment page
        pageMethodManager.getPaymentPageMethods().verifyHotelSectionDetails();


        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        WaitUtil.untilTimeCompleted(2000);

        //Typing declined credit card
        if (TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getSelectCardDropDown()))
        {
            TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPaymentPage().getSelectCardDropDown(), "Use Another Card");
            WaitUtil.untilTimeCompleted(2000);
        }
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(DECLINED_MASTER);

//        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
//
//        WaitUtil.untilPageLoadComplete(getDriver());
//
//        String cardError = pageObjectManager.getCommon().getIBlockVerbiageText().getText();
//        cardError = cardError.replaceAll("[^\\p{ASCII}]", "");
//        ValidationUtil.validateTestStep("User Verify error message for Hot Card", cardError, DECLINED_CARD_ERROR);
//
//        pageObjectManager.getCommon().getIBlockCloseButton().click();
//        WaitUtil.untilTimeCompleted(1000);

//- Step 17: Open Skyspeed, use log in credentials from roles and credentials
        //Unable to automate
//- Step 18: Press F10 and enter first and last name
        //Unable to automate
//- Step 19: Validate payment section states Declined
        //Unable to automate
//- Step 20: Validate flight line is empty (this may take 5-30 minutes to update)
        //Unable to automate

//- Step 21: Open HBG website
//- Step 22: Search Hotel PNR on HBG search tool
//- Step 23: Validate HBG booking was created
//- Step 24: Check HBG again after 30 minutes to 2 hours to see if hotel has been automatically canceled.
    }
}