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
//Test Case ID: TC373379
//Description: Task 27790: TC373379- 003 - CP - Car Verbiage - Dollar - Flight + Car - Validate verbiage for a booking with an International Origin
//Created By: Anthony Cardona
//Created On: 21-Nov-2019
//Reviewed By: kartik Chauhan
//Reviewed On:10-Dec-2019
//**********************************************************************************************
public class TC373379 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "FlightCar", "DomesticDomestic", "Outside21Days", "Adult", "Guest", "Cars" , "CarsUI"})
    public void CP_Car_Verbiage_Dollars_Flight_Car_Validate_verbiage_for_a_booking_with_an_International_Origin(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373379 under GoldFinger Suite on " + platform + " Browser", true);
        }
        // Home Page Constant Values
        final String LANGUAGE               = "English";
        final String JOURNEY_TYPE           = "Vacation";
        final String TRIP_TYPE              = "Flight+Car";
        final String DEP_AIRPORTS           = "AllLocation";
        final String DEP_AIRPORT_CODE       = "CUN";
        final String ARR_AIRPORTS           = "AllLocation";
        final String ARR_AIRPORT_CODE       = "LAS";
        final String DEP_DATE               = "135";
        final String ARR_DATE               = "139";
        final String ADULT                  = "1";
        final String CHILD                  = "0";
        final String INFANT_LAP             = "0";
        final String INFANT_SEAT            = "0";
        final String DRIVER_AGE 		    = "25+";

        //Car PAge Constant Values
        final String CAR_COMPANY            = "Dollar";
        final String CAR_VERBAGE1           = "Vehicle Description";


        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 2: Click the Vacation button and select the radio button next to Flight + Car
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);

//- Step 3: Book INT-DOM | Date 3 months in the future  | 1 ADT | Driver 25+ and click Search Flight
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 4: More than 1 car is displayed
        ValidationUtil.validateTestStep("There are cars being offered on the Flight+Car page" , pageObjectManager.getCarPage().getCarsPageRentalPriceText().size() > 0);
//- Step 5: Search for Hertz  Economy Car 2 or 4 door "Mitsubishi Mirage Or Similar," select "More "
        int carsIterated = 0;
        for (WebElement carCompany : pageObjectManager.getCarPage().getCarsPageCarTypeText()) {
            if (carCompany.getText().toLowerCase().contains("dollar")) {
                JSExecuteUtil.scrollDownToElementVisible(getDriver(), carCompany);
                break;
            }
            carsIterated++;
        }
        pageObjectManager.getCarPage().getCarsPageMoreInfoLink().get(carsIterated).click();
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("The \"More\" button is clicked and Vehicle description tile is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarPopUpVehicleDescriptionTab()));

        ValidationUtil.validateTestStep("Verify "+CAR_VERBAGE1+" is displayed on Car Modal PopUp",
                pageObjectManager.getCarPage().getCarPopUpVehicleDescriptionTab().getText(),CAR_VERBAGE1);

        //verify the vehicle description under more info link
       pageMethodManager.getCarPageMethods().verifyCarDescriptionAndPolicies(CAR_COMPANY);

    }
}