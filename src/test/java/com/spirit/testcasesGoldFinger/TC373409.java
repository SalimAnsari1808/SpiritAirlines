package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
//**********************************************************************************************
//Test Case ID: TC373409
//Description: Task 27865: TC373409 - 004 - CP - Manage Travel Tab - No Modifications - Flight + Hotel - Validate a customer can retrieve a booking for a Connecting Flight
//Created By: Gabriela
//Created On: 30-Nov-2019
//Reviewed By: Kartik Chauhan
//Reviewed On: 17-Dec-2019
//**********************************************************************************************

public class TC373409 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "FlightHotel", "Outside21Days", "Adult", "FreeSpirit",
                    "Connecting", "BookIt", "NoBags", "NoSeats", "CheckInOptions", "Visa", "ReservationUI","FlightAvailabilityUI"})
    public void CP_Manage_Travel_Tab_No_Modifications_Flight_Hotel_Validate_a_customer_can_retrieve_a_booking_for_a_Connecting_Flight(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373409 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String LOGIN_ACCOUNT      = "FSEmail";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "132";
        final String ARR_DATE           = "133";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page Constant Values:
        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

        //Payment Page Constant values
        final String TRAVEL_GUARD 		= "NotRequired";
        final String CARD_TYPE   		= "VisaCard";

        //Reservation Summary Page Constant Values
        final String WARNING_INFO       = "To make changes to your itinerary, please contact Customer Service directly at 1.954.698.0125.";

//- Step 1: Open goldfinger website test environment on consumer portal
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 2: Log in as a FS member
        /*** Home Page Method **/
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);

//- Step 3: Book a | flight + hotel | DOM-DOM | Connecting |1 Adt | No bags | No seats | No extras | outside 48 hrs | record pax last name and PNR
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        /*** Flight Availability Page Methods **/
        WaitUtil.untilPageLoadComplete(getDriver());
        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getFlightAvailabilityPage().getDepartureFlightEditButton().get(0));

        WaitUtil.untilTimeCompleted(1200);
        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getFlightAvailabilityPage().getReturningJourneyEditButton().get(0));
        WaitUtil.untilTimeCompleted(1200);

        //Storing displayed departure flight in a list
        List<WebElement> departingFlightList = new ArrayList<>();
        for (int i = 0; i < pageObjectManager.getFlightAvailabilityPage().getDepartingStandardFarePriceText().size(); i ++)
        {
            if (pageObjectManager.getFlightAvailabilityPage().getDepartingStandardFarePriceText().get(i).isDisplayed())
            {
                departingFlightList.add(pageObjectManager.getFlightAvailabilityPage().getDepartingStandardFarePriceText().get(i));
            }
        }

        //Selecting departure Connecting flight
        for (int i =0; i < pageObjectManager.getFlightAvailabilityPage().getDepartingNumebrOfStopsButton().size(); i ++)
        {
            if (!pageObjectManager.getFlightAvailabilityPage().getDepartingNumebrOfStopsButton().get(i).getText().equals("Nonstop"))
            {
                //Validating stops information
                pageObjectManager.getFlightAvailabilityPage().getDepartingNumebrOfStopsButton().get(i).click();
                WaitUtil.untilPageLoadComplete(getDriver());

                //Verifying is a connecting flight
                if (!pageObjectManager.getFlightAvailabilityPage().getStopsPopUpFlightsNumberText().get(0).getText().equals(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpFlightsNumberText().get(1).getText()))
                {
                    //Close pop up
                    pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();
                    WaitUtil.untilTimeCompleted(1200);
                    //Selecting connecting flight
                    departingFlightList.get(i-1).click();
                    break;
                }
                //Close pop up
                pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();
                WaitUtil.untilTimeCompleted(1200);
            }
        }

        //Selecting returning through flight
        WaitUtil.untilTimeCompleted(1200);
        //Storing displayed flights in a list
        List<WebElement> arrFlightList  = new ArrayList<>();
        for (int i = 0; i < pageObjectManager.getFlightAvailabilityPage().getReturningStandardFarePriceText().size(); i ++)
        {
            if (pageObjectManager.getFlightAvailabilityPage().getReturningStandardFarePriceText().get(i).isDisplayed())
            {
                arrFlightList.add(pageObjectManager.getFlightAvailabilityPage().getReturningStandardFarePriceText().get(i));
            }
        }

        //Looping searching for a connecting flight
        List<WebElement> retStops = getDriver().findElements(By.xpath("(//div[@data-qa='journey-results'])[2]//button[@data-qa='journey-stops']"));
        for (int i =0; i < retStops.size(); i ++)
        {
            if (!retStops.get(i).getText().equals("Nonstop"))
            {
                retStops.get(i).click();
                WaitUtil.untilPageLoadComplete(getDriver());
                if (!pageObjectManager.getFlightAvailabilityPage().getStopsPopUpFlightsNumberText().get(0).getText().equals(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpFlightsNumberText().get(1).getText()))
                {
                    pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();
                    WaitUtil.untilTimeCompleted(1200);

                    arrFlightList.get(i-1).click();
                    break;
                }
                pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();
                WaitUtil.untilTimeCompleted(1200);
            }
        }

        //Storing flight information
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();

        //Selecting hotel
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("MGM","NA");

        //Continue without Car
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getCarPage().getCarsPageContinueWithoutCarButton().click();

        //Book It on Save and Upgrade pop up
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        /*** Passenger Information Page Method **/
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
        /*** Bags Page Method **/
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
        /*** Seats Page Method **/
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
        /*** Options Page Method **/
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
        /*** Payment Page Method **/
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
//        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
//        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);
//        /*** Confirmation Page Method **/
//        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
//        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
//
////- Step 4: Click on "My Trips" tab
////- Step 5: Input pax last name and PNR then click "CONTINUE"
//        pageMethodManager.getHomePageMethods().loginToMyTrip();
//
////- Step 6: Verify all flight Information is correct
////- Step 7: Verify all hotel information is correct
//        pageMethodManager.getReservationSummaryPageMethods().verifyHotelSectionDetails();
//
//        ValidationUtil.validateTestStep("Validating Red Warning information",
//                getDriver().findElement(By.xpath("//div[contains(@class,'card modify-vacation-banner')]")).getText(),WARNING_INFO);
//
//        //Canceling Hotel
//        pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
    }
}