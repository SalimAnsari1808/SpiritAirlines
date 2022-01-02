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
//Test Case ID: TC373256
//Description: Task 27204: TC373256 - 005 - CP - Manage Travel Tab - No Modifications - Flight + Hotel - Validate a customer can retrieve a booking for a Thru Flight
//Created By: Gabriela
//Created On: 26-Nov-2019
//Reviewed By: Alex Rodriguez
//Reviewed On: 17-Dec-2019
//**********************************************************************************************

public class TC373256 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "FlightHotel", "Outside21Days", "Adult", "NineDFC","Through", "BookIt", "NoBags","NoSeats","CheckInOptions","Visa","ReservationUI"})
    public void CP_Manage_Travel_Tab_No_Modifications_Flight_Hotel_Validate_a_customer_can_retrieve_a_booking_for_a_Thru_Flight(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373256 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String LOGIN_ACCOUNT      = "NineDFCEmail";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "DTW";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "131";
        final String ARR_DATE           = "133";
        final String ADULT              = "2";
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

//- Step 2: Log in as a 9DFC member
       // Home Page Method
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);
//- Step 3: Book a | flight + hotel | RT | DOM | Thru flight | 2 Adt | outside 48 hrs | no bags | No seats | No extras | record pax last name and PNR
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        //Flight Availability Page Method
        WaitUtil.untilPageLoadComplete(getDriver());
        List<WebElement> editDepButton = new ArrayList<>();
        List<WebElement> editRetButton = new ArrayList<>();
        for (int i = 0; i < pageObjectManager.getFlightAvailabilityPage().getDepartureFlightEditButton().size(); i ++)
        {
            if (pageObjectManager.getFlightAvailabilityPage().getDepartureFlightEditButton().get(i).isDisplayed())
            {
                editDepButton.add(pageObjectManager.getFlightAvailabilityPage().getDepartureFlightEditButton().get(i));
            }
        }

        editDepButton.get(0).click();
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);

        //Storing displayed departure flight in a list
        List<WebElement> departingFlightList = new ArrayList<>();
        for (int i = 0; i < pageObjectManager.getFlightAvailabilityPage().getDeparting9FCFarePriceText().size(); i ++)
        {
            if (pageObjectManager.getFlightAvailabilityPage().getDeparting9FCFarePriceText().get(i).isDisplayed())
            {
                departingFlightList.add(pageObjectManager.getFlightAvailabilityPage().getDeparting9FCFarePriceText().get(i));
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
//                    departingFlightList.get(i-1).click();
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
        //Car Page Method
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getCarPage().getCarsPageContinueWithoutCarButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        //Save & Upgrade pop up
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);
        // Passenger Information Page Method
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
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
        // Confirmation Page Method
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //Storing confirmation codes information for next validations
        String pnrCode = pageObjectManager.getConfirmationPage().getBookingSectionTopConfirmationCode().get(0).getText();
        String hbgCode = pageObjectManager.getConfirmationPage().getBookingSectionTopHotelConfirmationCodeText().get(0).getText();

//- Step 4: Go to the homepage.
//- Step 5: Click on "My Trips" tab
// Step 6: Input pax last name and PNR then click "CONTINUE"
        pageMethodManager.getHomePageMethods().loginToMyTrip();

//- Step 7: Verify the Flight and Hotel Confirmation number
        ValidationUtil.validateTestStep("Validating Booking Confirmation Code is displayed on Reservation Summary Page",
                pageObjectManager.getReservationSummaryPage().getConfirmationCodeNumberText().get(0).getText(),pnrCode);

        ValidationUtil.validateTestStep("Validating HBG Confirmation Code is displayed on Reservation Summary Page",
               pageObjectManager.getReservationSummaryPage().getHotelConfirmationCodeNumberText().get(0).getText(),hbgCode);


//- Step 8: Verify Red Warning above the flight information "To make changes to your itinerary, please contact Customer Service directly at 1.954.698.0125."
        ValidationUtil.validateTestStep("Validating Red Warning information",
                getDriver().findElement(By.xpath("//div[contains(@class,'card modify-vacation-banner')]")).getText(),WARNING_INFO);

//- Step 9: Verify all flight Information is correct
//- Step 10: Verify all hotel information is correct
        pageMethodManager.getReservationSummaryPageMethods().verifyHotelSectionDetails(); //Having issues with room validation

        pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
    }
}

//***************** Reservation Summary Page XPATH **********************************/
//*******************************************************************
//***************************Header Section**************************
//*******************************************************************
//public final String xpath_HotelConfirmationCodeNumberText = "//Strong[contains(text(),'Hotel Confirmation Code: ')]/..";
//    @FindBy(xpath = xpath_HotelConfirmationCodeNumberText)
//    private List<WebElement> txt_HotelConfirmationCodeNumber;
//
//public List<WebElement> getHotelConfirmationCodeNumberText(){return txt_HotelConfirmationCodeNumber;}