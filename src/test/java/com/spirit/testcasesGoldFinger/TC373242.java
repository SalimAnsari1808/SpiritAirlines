package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test CaseID: TC373242
//Title      : CP - Car Tax Verbiage for Flight + Car - Validate the verbiage for a booking on the Spanish Path
//Created By : Kartik Chauhan
//Created On : 11-Nov-2019
//Reviewed By: Salim Ansari
//Reviewed On: 18-Nov-2019
//**********************************************************************************************

public class TC373242 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath","RoundTrip","FlightCar","Guest","DomesticDomestic","WithIn21Days","Adult","Child","FlightAvailabilityUI","Spanish"})
    public void CP_Car_Tax_Verbiage_for_Flight_Car_Validate_the_verbiage_for_a_booking_on_the_Spanish_Path(@Optional("NA") String platform) {

        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373242 under GoldFinger Suite on " + platform + " Browser", true);
        }

        //Reservation Credit Path Constant variables
        final String LANGUAGE           = "Spanish";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "flight+car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "15";
        final String ARR_DATE           = "19";
        final String ADULTS             = "3";
        final String CHILDREN           = "1";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE         = "25+";

        final String RENTAL_CARS_SUBHEADER       = " El precio del alquiler del auto incluye los impuestos y cargos. Opciones adicionales tales como daños ilimitados o cobertura de responsabilidad, cuando se escogen, se pagan directamente a la agencia de alquiler de autos. ";
        final String CAR_COMPANY                 = "Hertz";

        //open browser
        openBrowser(platform);
//Step--1
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
//Step--2/3/4
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        //Home Page Methods
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        WaitUtil.untilPageLoadComplete(getDriver());
//Step--5
        //click on Car Taxes Link
        pageObjectManager.getCarPage().getIncludesCarTaxesLink().click();

        WaitUtil.untilTimeCompleted(2000);
//Step--6
        ValidationUtil.validateTestStep("Verify "+RENTAL_CARS_SUBHEADER+" is displayed on Car Modal PopUp",
                pageObjectManager.getCarPage().getIncludeCarTaxesPopupSubHeaderText().getText(),RENTAL_CARS_SUBHEADER);

        //Close pop-up
        pageObjectManager.getCarPage().getIncludeCarTaxesPopupCloseWindowButton().click();

        WaitUtil.untilTimeCompleted(2000);
//STep--7/8/9/10/11/12
//As per discussion, with onside link is not for Goldfinger, that was for epic.

        //Flight Availability Methods
        pageMethodManager.getCarPageMethods().clickCarMoreLinkPage(CAR_COMPANY, "NA");
//Step--5/6
        pageObjectManager.getCarPage().getCarsPageMoreInfoLink().get(0).click();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getCarPageMethods().verifyCarDescriptionAndPolicies(CAR_COMPANY);
    }
}