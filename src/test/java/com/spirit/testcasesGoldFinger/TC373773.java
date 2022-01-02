package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC373773
//Description : Task 27191: TC373773 - US 20855 - 005 - CP - Payment Error Car Verbiage - Flight + Car - Standard Booking
//Created By  : Kartik
//Created On  : 20-Nov-2019
//Reviewed By : Gabriela
//Reviewed On : 6-Dec-2019
//**********************************************************************************************

public class TC373773 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "Outside21Days", "Adult","Child", "Guest","BookIt",
            "ContactInformation","NoBags", "NoSeats","CheckInOptions", "OptionalUI","Mastercard","PaymentUI"})
    public void CP_Payment_Error_Car_Verbiage_Flight_Car_Standard_Booking(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373773 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant variables
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "135";
        final String ARR_DATE           = "139";
        final String ADULTS             = "4";
        final String CHILD              = "1";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String HOTELROOM          = "3 Rooms";

        //FA PAge
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page Constant Values:
        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String CARD_TYPE          = "Mastercard";
        final String TRAVEL_GUARD       = "NotRequired";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectHotelRoom(HOTELROOM);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("Universal","NA");

//Step 6: Select "Continue without car"
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getCarPage().getCarsPageContinueWithoutCarButton().click();

//Step 7: If the Bundle pop-up is displayed. Select "Book it"
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//- Step 8: On the contact information put a valid email address
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

        //Typing emailtesters email for next validation
        pageObjectManager.getPassengerInfoPage().getContactPersonEmailTextBox().clear();
        pageObjectManager.getPassengerInfoPage().getContactPersonEmailTextBox().sendKeys("emailtesters@spirit.com");

        pageObjectManager.getPassengerInfoPage().getContactPersonConfirmEmailTextBox().clear();
        pageObjectManager.getPassengerInfoPage().getContactPersonConfirmEmailTextBox().sendKeys("emailtesters@spirit.com");

//- Step 9: Fill out passenger info and click "Continue"
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Step 10: click "continue without bags" at the bottom of the page.
//- Step 11: Click "I Don't Need Bags"
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//- Step 12: Select "CONTINUE WITHOUT SELECTING SEATS"s
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//Step 13: The Selected Car should be displayed on the Options page.
       //Invalid Step
        //verify Hotel is selected on optionPage
        pageMethodManager.getHotelPageMethods().verifySelectedHotelOptionPage();

//- Step 14: On the Options page select the Check-in Option and click Continue.
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//Payment page
//Step--15
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
//        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

//Step 16: When inventory is unavailable, validate new iblock message with display following verbiage:
//       Unable to automate this step
    }
}