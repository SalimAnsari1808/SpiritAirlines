package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

//**********************************************************************************************
//Test Case ID: TC373412
//Description : Task 27868: TC373412 - 008 - CP - Manage Travel Tab - No Modifications - Flight + Hotel + Car - Validate a customer can retrieve a booking for a Thru Flight
// Created By : Gabriela
//Created On  : 30-Nov-2019
//Reviewed By :
//Reviewed On :
//**********************************************************************************************
public class TC373412 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"MyTrips", "RoundTrip", "DomesticDomestic", "FlightHotelCar", "Outside21Days", "Adult", "Guest",
                    "Through", "BookIt", "NoBags", "NoSeats", "CheckInOptions", "Visa", "ReservationUI"})
    public void CP_Manage_Travel_Tab_No_Modifications_Flight_Hotel_Car_Validate_a_customer_can_retrieve_a_booking_for_a_Thru_Flight(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373412 under GoldFinger Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "CLE";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "132";
        final String ARR_DATE           = "133";
        final String ADULT              = "2";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE         = "25+";

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

//- Step 2: As a guest book a | flight + hotel + car | RT | DOM | Thru Flight | 2 Adt | No bags | No seats | No extras | outside 48 hrs | record pax last name and PNR
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        // Flight Availability Page Method
        WaitUtil.untilElementIsClickable(getDriver(),pageObjectManager.getFlightAvailabilityPage().getDepartureFlightEditButton().get(1));
        WaitUtil.untilTimeCompleted(1200);
        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getFlightAvailabilityPage().getReturningJourneyEditButton().get(1));
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
        System.out.println("departingFlightList:" + departingFlightList.size());
        //Selecting departure through flight
        for (int i =0; i < pageObjectManager.getFlightAvailabilityPage().getDepartingNumebrOfStopsButton().size(); i ++)
        {
            if (pageObjectManager.getFlightAvailabilityPage().getDepartingNumebrOfStopsButton().get(i).getText().equals("1 Stop"))
            {
                pageObjectManager.getFlightAvailabilityPage().getDepartingNumebrOfStopsButton().get(i).click();
                WaitUtil.untilPageLoadComplete(getDriver());

                if (pageObjectManager.getFlightAvailabilityPage().getStopsPopUpFlightsNumberText().get(0).getText().equals(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpFlightsNumberText().get(1).getText()))
                {
                    pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();
                    WaitUtil.untilTimeCompleted(1200);
                    JSExecuteUtil.clickOnElement(getDriver(),departingFlightList.get(i-1));
                    break;
                }
                pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();
                WaitUtil.untilTimeCompleted(1200);

            }
        }
        //Storing displayed flights in a list
        List<WebElement> arrFlightList  = new ArrayList<>();
        for (int i = 0; i < pageObjectManager.getFlightAvailabilityPage().getReturningStandardFarePriceText().size(); i ++)
        {
            if (pageObjectManager.getFlightAvailabilityPage().getReturningStandardFarePriceText().get(i).isDisplayed())
            {
                arrFlightList.add(pageObjectManager.getFlightAvailabilityPage().getReturningStandardFarePriceText().get(i));
            }
        }
        List<WebElement> retStops = getDriver().findElements(By.xpath("(//div[@data-qa='journey-results'])[2]//button[@data-qa='journey-stops']"));
        for (int i =0; i < retStops.size(); i ++)
        {
            if (retStops.get(i).getText().equals("1 Stop"))
            {
                retStops.get(i).click();
                WaitUtil.untilPageLoadComplete(getDriver());
                if (pageObjectManager.getFlightAvailabilityPage().getStopsPopUpFlightsNumberText().get(0).getText().equals(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpFlightsNumberText().get(1).getText()))
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
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("Universal","NA");
        WaitUtil.untilPageLoadComplete(getDriver());
        if (pageObjectManager.getHotelPage().getContinueButton().isDisplayed())
        {
            pageObjectManager.getHotelPage().getContinueButton().click();
        }
        // Car PAge Method
        pageMethodManager.getCarPageMethods().storeCarInformationOnCarPage("NA","NA");
        pageMethodManager.getCarPageMethods().selectCarOnCarPage("NA","NA");

        // Save & Upgrade pop up
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);
        // Passenger Information Page Method
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().selectPrimaryDriver();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
        // Bags Page Method
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
        // Seats Page Method
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
        // Options Page Method

        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
        // Payment Page Method
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);
        //Confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

//- Step 3: Click on "My Trips" tab
//- Step 4: Input pax last name and PNR then click "CONTINUE"
            pageMethodManager.getHomePageMethods().loginToMyTrip();

        try {
//- Step 5: Verify all flight Information is correct
            ValidationUtil.validateTestStep("Validating Red Warning information",
                    getDriver().findElement(By.xpath("//div[contains(@class,'card modify-vacation-banner')]")).getText(),WARNING_INFO);

//- Step 6: Verify all hotel information is correct
            pageMethodManager.getReservationSummaryPageMethods().verifyHotelSectionDetails();

//- Step 7: Verify all car information is correct
            pageMethodManager.getReservationSummaryPageMethods().verifyCarSectionDetails();

            //Canceling Hotel
            pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
            //Canceling CAR
            pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
        }catch (AssertionError fail){
            //Canceling Hotel
            pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
            //Canceling CAR
            pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
            ValidationUtil.validateTestStep("Test Case failed verifying hotel or car information on Reservation Summary Page " + fail.getMessage() , false);
        }catch (Exception fail){
            //Canceling Hotel
            pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
            //Canceling CAR
            pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
            ValidationUtil.validateTestStep("Test Case failed verifying hotel or car information on Reservation Summary Page " + fail.getMessage() , false);
        }
    }
}