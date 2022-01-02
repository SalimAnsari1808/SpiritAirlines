package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC373769
//Description : Task 27189: TC373769 - US 20854 - 005 - CP - Payment Error Hotel Verbiage - Flight + Hotel - Standard Booking
//Created By  : Kartik
//Created On  : 20-Nov-2019
//Reviewed By : Gabriela
//Reviewed On : 6-Dec-2019
//**********************************************************************************************

public class TC373769 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip","FlightHotel", "DomesticDomestic", "Outside21Days", "Adult","Child", "Guest","BookIt",
                   "ContactInformation", "NoBags", "NoSeats","Hotels", "CheckInOptions", "OptionalUI","Mastercard","PaymentUI"})
    public void CP_Payment_Error_Hotel_Verbiage_Flight_Hotel_Standard_Booking(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373769 under GoldFinger Suite on " + platform + " Browser", true);
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
        final String ADULT              = "4";
        final String CHILDREN           = "1";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String HOTELROOM          = "3 Rooms";

        //Flight Availability Page Constant Values
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
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectHotelRoom(HOTELROOM);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();
        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("Universal","NA");

        //Cars Page Method
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getCarPage().getCarsPageContinueWithoutCarButton().click();
        WaitUtil.untilTimeCompleted(1200);

//- Step 6: Select "BOOK IT"
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//- Step 7: Fill in all customer information and click on " CONTINUE"
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

        //Typing emailtesters email for next validation
        pageObjectManager.getPassengerInfoPage().getContactPersonEmailTextBox().clear();
        pageObjectManager.getPassengerInfoPage().getContactPersonEmailTextBox().sendKeys("emailtesters@spirit.com");

        pageObjectManager.getPassengerInfoPage().getContactPersonConfirmEmailTextBox().clear();
        pageObjectManager.getPassengerInfoPage().getContactPersonConfirmEmailTextBox().sendKeys("emailtesters@spirit.com");
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Step 8 & 9: Select "CONTINUE WITHOUT ADDING BAGS" & Select "I DON'T NEED BAGS"
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//- Step 10: Select "CONTINUE WITHOUT SELECTING SEATS"s
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//- Step 12: The Selected Hotel should be displayed on the Options page.
        pageMethodManager.getHotelPageMethods().verifySelectedHotelOptionPage();

//- Step 13: Click continue with purchase
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//Payment page
//Step--14
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
//        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

//Step 15: When inventory is unavailable, validate new iblock message with display following verbiage:
//       Unable to automate this step
    }
}