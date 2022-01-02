package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
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
//Test Case ID: TC373407
//Description : Task 27864: TC373407  - 002 - CP - Manage Travel Tab - No Modifications - Flight + Car - Validate a customer can retrieve a booking for a Thru Flight
//Created By  : Anthony Cardona
//Created On  : 03-Dec-2019
//Reviewed By : Gabriela
//Reviewed On : 5-Dec-2019
//**********************************************************************************************
public class TC373407 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"MyTrips", "RoundTrip", "DomesticDomestic", "FlightCar", "Outside21Days", "Adult", "FreeSpirit",
            "Through", "BookIt", "NoBags","NoSeats","CheckInOptions", "Visa", "ReservationUI"})
    public void  Manage_Travel_Tab_No_Modifications_Flight_Car_Validate_a_customer_can_retrieve_a_booking_for_a_Thru_Flight(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373407  under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String LOGIN_ACCOUNT      = "FSEmail";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "CLE";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "132";
        final String ARR_DATE           = "133";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE 		= "25+";

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
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);

//- Step 3: Book a | flight + car | DOM | Thru flight | 1 Adt | outside 48 hrs | no bags | No seats | No extras | record pax last name and PNR
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        //FA Page
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getDepartureFlightEditButton().get(0).click();
        WaitUtil.untilTimeCompleted(1200);
        for (int i = 0; i < pageObjectManager.getFlightAvailabilityPage().getReturningJourneyEditButton().size(); i ++)
        {
            if(pageObjectManager.getFlightAvailabilityPage().getReturningJourneyEditButton().get(i).isDisplayed())
            {
                pageObjectManager.getFlightAvailabilityPage().getReturningJourneyEditButton().get(i).click();
                WaitUtil.untilTimeCompleted(1200);
            }
        }

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
                    departingFlightList.get(i-1).click();
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

        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();

        pageMethodManager.getCarPageMethods().selectCarOnCarPage("NA","NA");
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
        //Bags page
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
        //Seats Page
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
        //Options Page
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
        //Payment Page
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        try {
            pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
            pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

            //Confirmation Page Methods
            pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
            pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

//- Step 4: Click on "My Trips" tab
//- Step 5: Input pax last name and PNR then click "CONTINUE"
        pageMethodManager.getHomePageMethods().loginToMyTrip();

//- Step 6: Verify all flight Information is correct
            ValidationUtil.validateTestStep("Validating Red Warning information",
                getDriver().findElement(By.xpath("//div[contains(@class,'card modify-vacation-banner')]")).getText(),WARNING_INFO);

//- Step 7: Verify all car information is correct
        pageMethodManager.getReservationSummaryPageMethods().verifyCarSectionDetails();
        //Canceling CAR
        pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
        }catch (AssertionError fail){
            pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
            ValidationUtil.validateTestStep("Test Case failed after Payment: " + fail.getMessage() , false);
        }catch (Exception fail){
            pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
            ValidationUtil.validateTestStep("Test Case failed after Payment: " + fail.getMessage() , false);
        }

    }
}
