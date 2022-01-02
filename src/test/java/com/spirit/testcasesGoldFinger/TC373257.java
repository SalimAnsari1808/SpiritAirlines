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

import java.util.List;

//**********************************************************************************************
//Test Case ID: TC373257
//Description: Task 27205: TC373257 - 009 - CP - Manage Travel Tab - No Modifications - Flight + Hotel + Car - Validate a customer can retrieve a booking for a NonStop Flight
//Created By: Gabriela
//Created On: 27-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC373257 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "FlightHotelCar", "Outside21Days", "Adult", "FreeSpirit", "NonStop", "BookIt", "NoBags", "NoSeats", "CheckInOptions", "Visa", "ReservationUI"})
    public void CP_Manage_Travel_Tab_No_Modifications_Flight_Hotel_Car_Validate_a_customer_can_retrieve_a_booking_for_a_NonStop_Flight(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373257 under GoldFinger Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String LOGIN_ACCOUNT      = "FSEmail";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "132";
        final String ARR_DATE           = "133";
        final String ADULT              = "2";
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
        /*** Home Page Method **/
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);

//- Step 3: Book a | flight + hotel + car | RT | DOM | NonStop |2 Adt | No bags | No seats | No extras | outside 48 hrs | record pax last name and PNR
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        /*** Flight + Hotel Page Methods **/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getDepartureFlightEditButton().get(1).click();
        WaitUtil.untilTimeCompleted(1200);
        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getFlightAvailabilityPage().getReturningJourneyEditButton().get(1));
        WaitUtil.untilTimeCompleted(1200);

        //Selecting departure NonStop flight
        for (int i =0; i < pageObjectManager.getFlightAvailabilityPage().getDepartingNumebrOfStopsButton().size(); i ++)
        {
            if (pageObjectManager.getFlightAvailabilityPage().getDepartingNumebrOfStopsButton().get(i).getText().equals("Nonstop") && pageObjectManager.getFlightAvailabilityPage().getDepartingStandardFarePriceText().get(i).isDisplayed())
            {
                    pageObjectManager.getFlightAvailabilityPage().getDepartingStandardFarePriceText().get(i).click();
                    break;
            }
        }

        //Selecting returning NonStop flight
        WaitUtil.untilTimeCompleted(1200);
        List<WebElement> retStops = getDriver().findElements(By.xpath("(//div[@data-qa='journey-results'])[2]//button[@data-qa='journey-stops']"));
        for (int i =0; i < retStops.size(); i ++)
        {
            if (retStops.get(i).getText().equals("Nonstop") && pageObjectManager.getFlightAvailabilityPage().getReturningStandardFarePriceText().get(i).isDisplayed())
            {
                    pageObjectManager.getFlightAvailabilityPage().getReturningStandardFarePriceText().get(i).click();
                    break;
            }
        }
        //Storing flight information
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();

        //Selecting hotel
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("Universal","NA");
        //Click cContinue button
        pageObjectManager.getHotelPage().getContinueButton().click();
        /*** Car PAge Method **/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getCarPageMethods().selectCarOnCarPage("NA","NA");
        /*** Save & Upgrade pop up **/
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);
        /*** Passenger Information Page Method **/
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().selectPrimaryDriver();
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
//        //Storing confirmation codes information for next validations
//        String pnrCode = pageObjectManager.getConfirmationPage().getBookingSectionTopConfirmationCode().get(0).getText();
//        String hbgCode = pageObjectManager.getConfirmationPage().getBookingSectionTopHotelConfirmationCodeText().get(0).getText();
//        String carnectCode = pageObjectManager.getConfirmationPage().getBookingSectionTopCarConfirmationCodeText().get(0).getText();
//
//
////- Step 4: Open goldfinger website test environment on consumer portal
////- Step 5: Click on "My Trips" tab
////- Step 6: Input pax last name and PNR then click "CONTINUE"
//        pageMethodManager.getHomePageMethods().loginToMyTrip();
//
////- Step 7: Verify the Flight, Hotel, and Car Confirmation number
//        ValidationUtil.validateTestStep("Validating Booking Confirmation Code is displayed on Reservation Summary Page",
//                pageObjectManager.getReservationSummaryPage().getConfirmationCodeNumberText().get(0).getText(),pnrCode);
//
//        ValidationUtil.validateTestStep("Validating HBG Confirmation Code is displayed on Reservation Summary Page",
//               pageObjectManager.getReservationSummaryPage().getHotelConfirmationCodeNumberText().get(0).getText(),hbgCode);
//
//        ValidationUtil.validateTestStep("Validating HBG Confirmation Code is displayed on Reservation Summary Page",
//                pageObjectManager.getReservationSummaryPage().getCarConfirmationCodeNumberText().get(0).getText(),carnectCode);
//
////- Step 8: Verify Red Warning above the flight information "To make changes to your itinerary, please contact Customer Service directly at 1.954.698.0125."
//        ValidationUtil.validateTestStep("Validating Red Warning information",
//                getDriver().findElement(By.xpath("//div[contains(@class,'card modify-vacation-banner')]")).getText(),WARNING_INFO);

//- Step 9: Verify all flight Information is correct
//- Step 10: Verify all hotel information is correct
//- Step 11: Verify all car information is correct
//        pageMethodManager.getReservationSummaryPageMethods().verifyCarSectionDetails(); //Having issues with car pick up address
//        pageMethodManager.getReservationSummaryPageMethods().verifyHotelSectionDetails(); //Having issues with room validation
//
//        //Canceling Package booking
//        pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
//        pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();

    }

}

/****************** Reservation Summary Page XPATH **********************************/
//*******************************************************************
//***************************Header Section**************************
//*******************************************************************
//public final String xpath_CarConfirmationCodeNumberText = "//Strong[contains(text(),'Car Confirmation Code:') or contains(text(),'Clave de Confirmación del Auto:')]/..";
//    @FindBy(xpath = xpath_CarConfirmationCodeNumberText)
//    private List<WebElement> txt_CarConfirmationCodeNumber;
//public List<WebElement> getCarConfirmationCodeNumberText(){return txt_CarConfirmationCodeNumber;}