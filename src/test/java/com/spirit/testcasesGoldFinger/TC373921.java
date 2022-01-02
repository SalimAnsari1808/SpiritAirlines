package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC373921
//Description: Task 27946: TC373921 - US 20590 - 013 - CP - Verbiage Car Payment T+C - Hertz - Validate that the correct T+C verbiage displays for a Booking Upsell
// Created By: Anthony Cardona
//Created On: 24-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC373921 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "Outside21Days", "Adult", "Guest", "NonStop", "BookIt", "NoBags", "NoSeats", "Cars", "PaymentUI"})
    public void Verbiage_Car_Payment_T_C_Hertz_Validate_that_the_correct_T_C_verbiage_displays_for_a_Booking_Upsell(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373921  under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "94";
        final String ARR_DATE           = "95";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT = "NonStop";
        final String ARR_Flight = "NonStop";
        final String FARE_TYPE = "Standard";
        final String UPGRADE_VALUE = "BookIt";

        //Options Constant Values
        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";


        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
//Step 1: create a RT DOM-DOM 1ADT direct flight / book it /no bags /no seats and land on options
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//Step 2: on the options select view all cars and book an Hertz car
        pageMethodManager.getCarPageMethods().selectCarOnOptionPage("Hertz" , "NA");
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//Step 3: verify flight and select car info displays correct, scroll down to terms and condition and validate correct verbiage for Rentals displays :
        WaitUtil.untilPageLoadComplete(getDriver());

        WebElement termsAndConditionsCarLine = getDriver().findElement(By.xpath("(//div[@class='terms-container']//div)[1]//span[contains(text(),'2.2.3 Rental Cars')]/..//following-sibling::div[1]"));
        String termsAndConditionsExpected = "Rental cars are available to drivers 21 years of age and older with a valid credit card and valid driver’s license – both in the name of the driver. In certain states drivers must be 25 years of age or older. Drivers under the age of 25 may be subject to additional surcharges which are not included in quoted rates and are payable directly to the rental car company. Customers may be subject to a credit check, credit card (must be in driver’s name) and age verification. Failure to comply may result in car rental refusal. Many rental car locations do not accept debit cards for car rental or may impose larger deposit requirements in the event they do accept them. Please contact specific car pick- up location to determine if they will accept a debit card and the associated restrictions. Remember that a deposit amount may be required for a rental car. Some locations may require a printed voucher in order to pick up your rental car.";
        ValidationUtil.validateTestStep("The terms and conditions contains the expected for car booking" , termsAndConditionsExpected , termsAndConditionsCarLine.getText());
    }
}
