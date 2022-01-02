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

//**********************************************************************************************
//TODO: [IN:25127] 	GoldFinger R1: CP: BP: F+C: Options Page: Incorrect car Pick Up information displayed.
//Test Case ID: TC373919
//Description: Task 27945: TC373919 - US 20590 - 007 - CP - Verbiage Car Payment T+C - Dollar - Validate that the correct T+C Verbiage displays for a Flight + Hotel Upsell
// Created By: Gabriela
//Created On: 1-Dec-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC373919 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"ActiveBug", "BookPath", "RoundTrip", "FlightHotel", "DomesticDomestic", "Outside21Days", "Adult", "Guest", "Cars", "BookIt", "NoBags","NoSeats",
            "CheckInOptions","CarsUI", "HotelsUI", "PaymentUI",})
    public void CP_Verbiage_Car_Payment_T_C_Dollar_Validate_that_the_correct_T_C_Verbiage_displays_for_a_Flight_Hotel_Upsell(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373919 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "133";
        final String ARR_DATE           = "134";
        final String ADULT              = "2";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String UPGRADE_VALUE      = "BookIt";

        //Options Constant Values
        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

//- Step 11: go to the Goldfinger testing environment.
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 1: go to vacation tab and select flight + hotel radio button
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);

//- Step 2: create a RT dom-dom 2 ADT 1 room 3 months out
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 3: flights are preselected, go down to choose your hotel and select view on any hotel and pick a room  and select continue at the bottom of the page
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating Departing journey is selected ",
                TestUtil.verifyElementDisplayed(pageObjectManager.getFlightAvailabilityPage().getSelectedDepatureFlightBlockPanel()));

        ValidationUtil.validateTestStep("Validating Returning journey is selected ",
                TestUtil.verifyElementDisplayed(pageObjectManager.getFlightAvailabilityPage().getSelectedArrivalFlightBlockPanel()));

        //Storing journey information for validation
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();

        //Selecting Hotel
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("Universal","NA");

//- Step 4: under pick your ride, select an dollar car and drivers age 25+
        pageMethodManager.getCarPageMethods().selectCarOnCarPage("Dollar","NA");

//- Step 5: on the thrills pop up select book it
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//- Step 6: enter all information for pax and select continue at the bottom of the page
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().selectPrimaryDriver();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Step 7: continue without bags
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//- Step 8: continue without seats
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//- Step 9: check in web
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//- Step 10: verify flight and selected hotel and  car info displays correct, scroll down to terms and condition and validate correct verbiage for Rentals displays :
        pageMethodManager.getPaymentPageMethods().verifyHotelSectionDetails();
        //TODO: [IN:25127] 	GoldFinger R1: CP: BP: F+C: Options Page: Incorrect car Pick Up information displayed.
        pageMethodManager.getPaymentPageMethods().verifyCarSectionDetails();

        WaitUtil.untilPageLoadComplete(getDriver());

        WebElement termsAndConditionsCarLine = getDriver().findElement(By.xpath("(//div[@class='terms-container']//div)[1]//span[contains(text(),'2.2.3 Rental Cars')]/..//following-sibling::div[1]"));
        String termsAndConditionsExpected = "Rental cars are available to drivers 21 years of age and older with a valid credit card and valid driver’s license – both in the name of the driver. In certain states drivers must be 25 years of age or older. Drivers under the age of 25 may be subject to additional surcharges which are not included in quoted rates and are payable directly to the rental car company. Customers may be subject to a credit check, credit card (must be in driver’s name) and age verification. Failure to comply may result in car rental refusal. Many rental car locations do not accept debit cards for car rental or may impose larger deposit requirements in the event they do accept them. Please contact specific car pick- up location to determine if they will accept a debit card and the associated restrictions. Remember that a deposit amount may be required for a rental car. Some locations may require a printed voucher in order to pick up your rental car.";
        ValidationUtil.validateTestStep("The terms and conditions contains the expected for car booking" , termsAndConditionsExpected , termsAndConditionsCarLine.getText());


    }
}