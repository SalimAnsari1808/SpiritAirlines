package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


//**********************************************************************************************
//Test Case ID: TC373351
//Description: Task 28102: TC373351 - 003 - CP - Car Verbiage - Thrifty - Flight + Car - Validate verbiage for a booking with an International Origin
//Created By: Anthony Cardona
//Created On: 20-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC373351 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "Outside21Days", "Adult", "Guest", "NonStop", "MandatoryFields" , "Cars" , "CarsUI"})
    public void Car_Verbiage_Thrifty_Flight_Car_Validate_verbiage_for_a_booking_with_an_International_Origin(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373351 under GoldFinger Suite on " + platform + " Browser", true);
        }
        // Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "BOG";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "FLL";
        final String DEP_DATE           = "135";
        final String ARR_DATE           = "139";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE         = "25+";

        //Car Page Constant Values
        final String CAR_COMPANY            = "Thrifty";
        final String CAR_VERBAGE1           = "Vehicle Description";


//- Step 1: Access GoldFinger test environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 2: Click on vacation tab, and click on Flight+Car
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);

//- Step 3: Book Vacation | INT-DOM | 3 months out | 1 ADT |  25+ Driver
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();


        ValidationUtil.validateTestStep("There are cars being offered on the Flight+Car page" , pageObjectManager.getCarPage().getCarsPageRentalPriceText().size() > 0);

//- Step 4:
//Search for Thrifty Economy Car 2 or 4 door "Mitsubishi Mirage Or Similar," select "More "
//
//Verify the following verbage under// "Vehicle Description":
//        int carsIterated = 0;
//        for (WebElement carCompany : pageObjectManager.getCarPage().getCarsPageCarTypeText()) {
//            if (carCompany.getText().toLowerCase().contains(CAR_COMPANY)) {
//                JSExecuteUtil.scrollDownToElementVisible(getDriver(), carCompany);
//
//                break;
//            }
//            carsIterated++;
//        }
        pageMethodManager.getCarPageMethods().filterCarByRentalAgency(CAR_COMPANY);
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getCarPage().getCarsPageMoreInfoLink().get(0).click();

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("The \"More\" button is clicked and Vehicle description tile is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarPopUpVehicleDescriptionTab()));

        ValidationUtil.validateTestStep("Verify "+CAR_VERBAGE1+" is displayed on Car Modal PopUp",
                pageObjectManager.getCarPage().getCarPopUpVehicleDescriptionTab().getText(),CAR_VERBAGE1);

       pageMethodManager.getCarPageMethods().verifyCarDescriptionAndPolicies(CAR_COMPANY);
    }
}
