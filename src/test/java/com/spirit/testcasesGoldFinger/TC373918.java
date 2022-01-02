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
//TODO: [IN:25167] GoldFinger R1: CP: BP: F+C+H: Payment Page PMT: Incorrect information displayed on Terms & Conditions on car section
//Test Case ID: TC373918
//Description: CP - Verbiage Car Payment T+C - Thrifty - Validate that the correct T+C Verbiage displays for a Flight + Car Booking
//Created By : Un Fai Chan
//Created On : 11/27/2019
//Reviewed By: Gabriela
//Reviewed On: 10-Dec-2019
//**********************************************************************************************

public class TC373918 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"ActiveBug", "BookPath", "RoundTrip", "DomesticDomestic", "Outside21Days", "Adult", "Guest", "NonStop",
            "BookIt", "NoBags", "NoSeats", "Cars", "PaymentUI"})
    public void CP_Verbiage_Car_Payment_T_C_Thrifty_Validate_that_the_correct_T_C_Verbiage_displays_for_a_Flight_Car_Booking(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373280 under GoldFinger Suite on " + platform + " Browser", true);
        }
        // Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "123";
        final String ARR_DATE           = "132";
        final String ADULT              = "1";
        final String CHILD              = "1";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE         = "25+";

        // Flight Availability Constant Values
        final String UPGRADE_VALUE      = "BookIt";

        //Options Constant Values
        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

        // Verbiage constant
        final String RENTALS_DISPLAYS_VERBIAGE = "Rental cars are available to drivers 21 years of age and older with a valid credit card and valid driver’s license – both in the name of the driver. In certain states drivers must be 25 years of age or older. Drivers under the age of 25 may be subject to additional surcharges which are not included in quoted rates and are payable directly to the rental car company. Customers may be subject to a credit check, credit card (must be in driver’s name) and age verification. Failure to comply may result in car rental refusal. Many rental car locations do not accept debit cards for car rental or may impose larger deposit requirements in the event they do accept them. Please contact specific car pick- up location to determine if they will accept a debit card and the associated restrictions. Remember that a deposit amount may be required for a rental car. Some locations may require a printed voucher in order to pick up your rental car.";

        // Step 4: go to the Goldfinger testing environment.
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();

        // Step 1: go to vacation tab and select flight + car radio buton
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);

//- Step 2: create a RT Dom-Dom 1 ADT 1 CHD (0-2) outside 48 hrs -  drivers age 25+, select Thrifty car rental- no bags/seats - no extras land on payment page
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        // Flight + Car Page methods
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();
        pageMethodManager.getCarPageMethods().selectCarOnCarPage("Thrifty","NA");
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        // Passenger Information Page Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPassengerInfoPage().getPrimaryDriverDropDown(),pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0).getAttribute("value") + " " + pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(0).getAttribute("value"));
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        // Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        // Seats Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        // Options Page Methods
        pageMethodManager.getCarPageMethods().verifySelectedCarOptionPage();
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        // Step 3: verify flight and select car info displays correct, scroll down to terms and condition and validate correct verbiage for Rentals displays under Rental Cars section :
        WaitUtil.untilPageLoadComplete(getDriver());

        // Getting the xpath within the terms and conditions under Car Rental Session
        WebElement termsAndConditionsCarLine = getDriver().findElement(By.xpath("(//div[@class='terms-container']//div)[1]//span[contains(text(),'2.2.3 Rental Cars')]/..//following-sibling::div[1]"));

        ValidationUtil.validateTestStep("The terms and conditions contains the expected for car booking" , RENTALS_DISPLAYS_VERBIAGE , termsAndConditionsCarLine.getText());

    }
}
