package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC373230
//Description: Task 27172: TC373230 - 004 - CP - Car Verbiage - Hertz - Flight + Hotel + Car - Validate correct verbiage displays
//Created By:  Gabriela
//Created On:  11-Nov-2019
//Reviewed By: Salim Ansari
//Reviewed On: 18-Nov-2019
//**********************************************************************************************
public class TC373230 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "FlightAvailabilityUI","FlightHotelCar", "Outside21Days", "Adult", "Guest",
            "NonStop", "CarsUI"})
    public void CP_Car_Verbiage_Hertz_Flight_Hotel_Car_Validate_correct_verbiage_displays(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373230 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "132";
        final String ARR_DATE           = "133";
        final String ADULT              = "5";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE 		= "25+";
        final String ROOMS_VALUE        = "2 Rooms";

        //Car Page
        final String VEHICLE_DESCRIPTION1    = "Please Note";
        final String VEHICLE_DESCRIPTION2    = "A valid major credit card in the renter’s name must be presented with available credit in the deposit amount (if applicable). At select locations, major debit cards may be used to secure your rental. At most Airport Locations debit cards will be accepted to qualify for rental when the customer provides proof of return airline flight to coincide with the rental and two (2) valid forms of identification. For specific information related to debit card acceptance or other policies, contact your rental location.";
        final String VEHICLE_DESCRIPTION3    = "The following exceptions apply:";
        final String VEHICLE_DESCRIPTION4    = "Debit cards may not be accepted at time of rental in the following cities and surrounding areas, please contact the local office for details: New York Metropolitan (New York, New Jersey, Connecticut), Hartford, Connecticut; Philadelphia, Pennsylvania; Boston, Massachusetts; Manchester, New Hampshire; and Detroit, Michigan.";
        final String VEHICLE_DESCRIPTION5    = "A valid driver’s license is required to rent. HERTZ may conduct a review of your driver's license status and driving record. HERTZ can deny rental based on your driving history. Car make or model is not guaranteed and a similar or larger car may be substituted. Please check with the rental car facility in regards to any additional restrictions or optional services. Unused days of rental are not eligible for refund. Additional rental days may require additional payment upon return of vehicle.";
        final String MINIMUM_AGE_NOTE        = "In certain states drivers must be 25 years of age or older. When allowed, drivers under the age of 25 may be subject to additional surcharges payable directly to the rental car supplier at the time of rental.";

//- Step 1: Using Google Chrome, Access Spirit home page in test environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 2: Book Vacation F+H+C | DOM | 3 months out | 5 ADT |  25+ Driver
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectHotelRoom(ROOMS_VALUE);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 3: Select hotel and room and continue
        WaitUtil.untilPageLoadComplete(getDriver(),(long)120);
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("NA","NA");

//- Step 4: Search for Hertz Economy Car 2 or 4 door "Mitsubishi Mirage Or Similar," select "More "
        pageMethodManager.getCarPageMethods().filterCarByRentalAgency("Hertz");
        pageMethodManager.getCarPageMethods().filterCarByCarType("Economy");


        pageObjectManager.getCarPage().getCarsPageMoreInfoLink().get(0).click();
        WaitUtil.untilTimeCompleted(3000);

        //verify the vehicle description under more infor link
        ValidationUtil.validateTestStep("Validating first Section of Vehicle Description appear under More Info link on Car Page",
                pageObjectManager.getCarPage().getCarPopUpVehicleDescriptionText().get(0).getText(),VEHICLE_DESCRIPTION1);

        ValidationUtil.validateTestStep("Validating second Section of Vehicle Description appear under More Info link on Car Page",
                pageObjectManager.getCarPage().getCarPopUpVehicleDescriptionText().get(1).getText(),VEHICLE_DESCRIPTION2);

        ValidationUtil.validateTestStep("Validating third Section of Vehicle Description appear under More Info link on Car Page",
                pageObjectManager.getCarPage().getCarPopUpVehicleDescriptionText().get(2).getText(),VEHICLE_DESCRIPTION3);

        ValidationUtil.validateTestStep("Validating fourth Section of Vehicle Description appear under More Info link on Car Page",
                pageObjectManager.getCarPage().getCarPopUpVehicleDescriptionText().get(3).getText(),VEHICLE_DESCRIPTION4);

        ValidationUtil.validateTestStep("Validating fifth Section of Vehicle Description appear under More Info link on Car Page",
                pageObjectManager.getCarPage().getCarPopUpVehicleDescriptionText().get(4).getText(),VEHICLE_DESCRIPTION5);

//- Step 5: Verify Policies tab verbiage:
        pageObjectManager.getCarPage().getCarPageMoreInfoPoliciesLink().click();
        WaitUtil.untilTimeCompleted(3000);

//        ValidationUtil.validateTestStep("What's Included Text is displayed on Car PopUp under Policy Section",
//                pageObjectManager.getCarPage().getCarPopUpPoliciesWhatsIncludesText().getText(),"What's Included");

        ValidationUtil.validateTestStep("Rental inclusions Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesRentalInclusionsText().getText(),"Rental inclusions");

        ValidationUtil.validateTestStep("Rental exclusions Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesRentalExclusionsText().getText(),"Rental exclusions");

        ValidationUtil.validateTestStep("Rental rules Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesRentalRulesText().getText(),"Rental rules");

        ValidationUtil.validateTestStep("Child Seats Required by Law Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesChildSeatsRequiredText().get(0).getText(),"Child Seats Required by Law");

        ValidationUtil.validateTestStep("Child Seats Required by Law \"Yes\" Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesChildSeatsRequiredText().get(1).getText(),"Yes");

        ValidationUtil.validateTestStep("Check with the rental counter or state to verify requirements Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesChildSeatsRequiredText().get(2).getText(),
                "Check with the rental counter or state to verify requirements.");

        ValidationUtil.validateTestStep("Minimum Age to Rent Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesMinimumAgeText().get(0).getText(),
                "Minimum Age to Rent");

        ValidationUtil.validateTestStep("Minimum Age to Rent \"21\" Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesMinimumAgeText().get(1).getText(), "21");

        ValidationUtil.validateTestStep("Minimum Age to Rent Note Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesMinimumAgeText().get(2).getText(), MINIMUM_AGE_NOTE);

        ValidationUtil.validateTestStep("Refund for Unused Rental Days/Hours Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesRefundForUnusedText().get(0).getText(), "Refund for Unused Rental Days/Hours ");

        ValidationUtil.validateTestStep("Refund for Unused Rental Days/Hours \"NO\" Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesRefundForUnusedText().get(1).getText(), "No");

        ValidationUtil.validateTestStep("Valid Driver's License Required Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesLicenseRequiredText().get(0).getText(), "Valid Driver's License Required");

        ValidationUtil.validateTestStep("Valid Driver's License Required Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesLicenseRequiredText().get(1).getText(), "Yes");

        ValidationUtil.validateTestStep("Valid Driver's License Required Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesLicenseRequiredText().get(2).getText(), "The lead name on the reservation must match the primary driver and credit card holder.");
    }
}