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
//Test Case ID: TC373752
//Description: Task 27179: TC373752 - US 20590 - 017 - CP - Verbiage Car Payment T+C - Hertz - Validate that the correct T+C Verbiage displays for a Flight + Hotel + Car Booking
//Created By: Gabriela
//Created On: 12-Nov-2019
//Reviewed By: Kartik Chauhan
//Reviewed On: 18-Nov-2019
//**********************************************************************************************

public class TC373752 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath","FlightHotelCar", "RoundTrip", "DomesticDomestic", "Outside21Days", "Adult", "Guest",
            "BookIt", "NoBags", "NoSeats", "CheckInOptions", "PaymentUI","FlightAvailabilityUI","PassengerInformationUI","OptionalUI"})
    public void CP_Verbiage_Car_Payment_T_C_Hertz_Validate_that_the_correct_T_C_Verbiage_displays_for_a_Flight_Hotel_Car_Booking(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373752 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+hotel+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "105";
        final String ARR_DATE           = "106";
        final String ADULT              = "3";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE 		= "25+";

        //Flight Availability Constant Values
        final String UPGRADE_VALUE      = "BookIt";

        //Options Constant Values
        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

        // Verbiage constant
        final String RENTALS_DISPLAYS_VERBIAGE = "Rental cars are available to drivers 21 years of age and older with a valid credit card and valid driver’s license – both in the name of the driver. In certain states drivers must be 25 years of age or older. Drivers under the age of 25 may be subject to additional surcharges which are not included in quoted rates and are payable directly to the rental car company. Customers may be subject to a credit check, credit card (must be in driver’s name) and age verification. Failure to comply may result in car rental refusal. Many rental car locations do not accept debit cards for car rental or may impose larger deposit requirements in the event they do accept them. Please contact specific car pick- up location to determine if they will accept a debit card and the associated restrictions. Remember that a deposit amount may be required for a rental car. Some locations may require a printed voucher in order to pick up your rental car.";

//- Step 11: go to the Goldfinger testing environment.
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();

//- Step 1: go to vacation tab and select flight + hotel +car radio buton
        //***Home Page Methods**/
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);

//- Step 2: create a RT dom-dom multi pax / 1 room/ drivers age 25+ / outside 3 months
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 3:	flights are preselected, go down to choose your hotel and select view on any hotel and pick a room  and select continue at the bottom of the page
        //***Flight Availability Page Methods**/

        //FA page, store all flight information for vacation booking
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("MGM","NA");

//- Step 4: under pick your ride, select a Hertz car rental and select continue at the bottom of the page
        ValidationUtil.validateTestStep("Validating right info displayed on car page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPagePickYourRideText()));

        pageMethodManager.getCarPageMethods().selectCarOnCarPage("Hertz","NA");

//- Step 5: on the thrills pop up select book it
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//- Step 6: enter all information for pax and select continue at the bottom of the page
        //Passenger Information Page Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPassengerInfoPage().getPrimaryDriverDropDown(),
                pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0).getAttribute("value") + " " + pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(0).getAttribute("value"));
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Step 7: continue without bags
        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//- Step 8: continue without seats
        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//- Step 9: check in web
        //Options Page Methods
        pageMethodManager.getCarPageMethods().verifySelectedCarOptionPage();

        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//- Step 10: verify flight and selected hotel and  car info displays correct, scroll down to terms and condition and validate correct verbiage for Rentals displays:
        WaitUtil.untilPageLoadComplete(getDriver());

        // Getting the xpath within the terms and conditions under Car Rental Session
        WebElement termsAndConditionsCarLine = getDriver().findElement(By.xpath("(//div[@class='terms-container']//div)[1]//span[contains(text(),'2.2.3 Rental Cars')]/..//following-sibling::div[1]"));

        ValidationUtil.validateTestStep("The terms and conditions contains the expected for car booking" , RENTALS_DISPLAYS_VERBIAGE , termsAndConditionsCarLine.getText());

    }
}