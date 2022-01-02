package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test CaseID: TC373221
//Title      : 001 - CP - Car Verbiage - National - Booking Upsell - Validate correct verbiage displays
//Created By : Kartik Chauhan
//Created On : 07-Nov-2019
//Reviewed By: Gabriela
//Reviewed On: 6-Dec-2019
//**********************************************************************************************

public class TC373221 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "Outside21Days", "Adult", "Guest","NonStop",
                    "BookIt","NoBags","NoSeats", "CarUI","OptionalUI"})
    public void Car_Verbiage_National_Booking_Upsell_Validate_correct_verbiage_displays(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373221 under GoldFinger Suite on " + platform + " Browser", true);
        }

        //Home Path Constant variables
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE2         = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE2          = "15";
        final String ARR_DATE2          = "19";
        final String ADULTS             = "5";
        final String CHILDREN           = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String RET_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        final String CAR_COMPANY        = "Thrifty";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE2);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE2, ARR_DATE2);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Step 4
        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Step 5
        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Step 6
        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Step 7
        //Seats page methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Step 8
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getCarPage().getViewAllCarsButton().click();

        pageMethodManager.getCarPageMethods().filterCarByRentalAgency(CAR_COMPANY);

        pageObjectManager.getCarPage().getCarsPageMoreInfoLink().get(0).click();
        WaitUtil.untilTimeCompleted(3000);
        pageMethodManager.getCarPageMethods().verifyCarDescriptionAndPolicies(CAR_COMPANY);
//Step--7
        //click on location tab
        pageMethodManager.getCarPageMethods().storeCarInformationOnCarPage(CAR_COMPANY,"NA");
        pageMethodManager.getCommonPageMethods().storeCarnetCarLocationInformation();
        pageMethodManager.getCarPageMethods().verifyLocationTabDetailsWithCarnetWebsite();

        // Validate that the "Estimated Taxes and Fees" is not displayed.
        ValidationUtil.validateTestStep("Validating \"Estimated Taxes and Fees\" is not displayed",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getEstimatedTaxesBodyText()));
    }
}