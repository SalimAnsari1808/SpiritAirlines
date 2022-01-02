package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

//**********************************************************************************************
//Test Case ID: TC373251
//Description: Task 27186: TC373251 - 006 - CP - Verbiage Car Driver Below 25 - Check-In Upsell - Hertz
//Created By: Gabriela
//Created On: 12-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC373251 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"CheckIn", "RoundTrip", "DomesticDomestic", "WithIn7Days", "Adult", "Child", "Guest", "NonStop", "BookIt","MandatoryFields","NoBags","NoSeats","CheckInOptions","Visa","PaymentUI"})
    public void CP_Verbiage_Car_Driver_Below_25_Check_In_Upsell_Hertz(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373251 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "0";
        final String ARR_DATE           = "1";
        final String ADULT              = "3";
        final String CHILD              = "3";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String ARR_Flight         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Constant Values
        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String TRAVEL_GUARD 		= "NotRequired";
        final String CARD_DETAIL 		= "VisaCard";

        //Check In Constant Value
        final String CHECK_IN_SEAT      = "DontPurchase";
        final String CHECK_IN_BAGS      = "DontPurchase";
        final String HERTZ              = "HERTZ";

//- Step 1: Open the Goldfinger testing Website
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();

//- Step 2: Pre req: Book RT DOM-DOM | Date within 24 hours | 3 ADT 3 CHD ( 1 ADT must be 21 years old) | No Bags / No Seats / No Bundle. Record Pax last name and PNR
        /***Home Page Methods**/
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        /***Flight Availability Page Methods**/
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        /***Passenger Information Page Methods**/
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();

        pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(0).clear();
        pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(0).sendKeys(TestUtil.getStringDateFormat(("-7738"), "MM/dd/yyyy"));

        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /***Bags Page Methods**/
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        /***Seats Page Methods**/
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        /***Options Page Methods**/
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /***Payment Page Methods**/
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        /***Confirmation Page Methods**/
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

//- Step 3: Open the GoldFinger testing Website //Invalid Step

//- Step 4 & 5: Select "Check-In" tab & Input Pax last name and PNR then select "CHECK-IN"
        /***Check In Pape Method**/
        pageMethodManager.getHomePageMethods().loginToCheckIn();

//- Step 6: Select "CHECK-IN AND GET BOARDING PASS"
        pageMethodManager.getReservationSummaryPageMethods().clickCheckInAndGetBoardingPass();
//- Step 7: Select "NOPE, I'M GOOD"
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSaveOnBagsPopup(CHECK_IN_BAGS);

//- Step 8: Select "GET RANDOM SEATS"
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSelectYourSeatPopup(CHECK_IN_SEAT);

//- Step 9: Select "VIEW ALL CARS"
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getCarPage().getLastChanceViewAllCarsButton().click();

//- Step 10 & 11: Locate any Hertz cars & Click on "Book Car" hyperlink on the Car carousel
        WaitUtil.untilPageLoadComplete(getDriver());
        String carType = "";
        for (int i = 0; i < pageObjectManager.getCarPage().getCheckInCarNameText().size(); i ++)
        {
            if (pageObjectManager.getCarPage().getCheckInCarNameText().get(i).getText().equals(HERTZ))
            {
                carType = pageObjectManager.getCarPage().getCheckInCarTypeText().get(i).getText();
                pageObjectManager.getCarPage().getCheckInCarsPageBookCarButton().get(i).click();
            }
        }

        //Continue to Payment from Options page
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //No to Travel Guard
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);
     }
}
