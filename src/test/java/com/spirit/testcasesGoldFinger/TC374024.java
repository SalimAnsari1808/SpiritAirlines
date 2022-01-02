package com.spirit.testcasesGoldFinger;


import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC374024
//Description: Flight Flex Packaging - Flight + Car - Validate that Flight Flex cannot be added to a Package Booking
//Created By: Salim Ansari
//Created On: 26-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC374024  extends BaseClass {

    @Parameters("platform")
    @Test(groups = {"BookPath" , "FlightCar" , "DomesticDomestic" , "Outside21Days" , "Adult" , "NonStop" , "BookIt" , "OptionalUI"})
    public void Flight_Flex_Packaging_Verbiage_Spanish_Flight_Car_Validate_verbiage_for_Flight_Flex_on_the_Hub_Page(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC374024 under GoldFinger Suite on " + platform + " Browser", true);
        }

        /******************************************************************************
         ****************************Navigate to Passenger Info Page*****************
         ******************************************************************************/
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String FS_EMAIL           = "FSEmail";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "93";
        final String ARR_DATE           = "95";
        final String ADULT              = "3";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE         = "21-24";

        //Flight Availability Page Constant Values
        final String UPGRADE_VALUE      = "BookIt";

//Step1--Land on current test environment.
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//STEP--2 Login as a FS member
        pageMethodManager.getHomePageMethods().loginToApplication(FS_EMAIL);

//STEP--3 Click on Vacation tab and select Flight + Car
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);

//STEP--3 Input the following: DOM_DOM | Any Date 3 months in the future | 3 PAX | Driver 21-24 and click SEARCH VACATION
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//STEP--4 Select any car and proceed to the Options page with BOOK IT |  No Bags | No Seats
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();
        pageMethodManager.getCarPageMethods().selectCarOnCarPage("NA", "NA");
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().selectPrimaryDriver();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();


//STEP--6 Verify that Flight Flex is available
        ValidationUtil.validateTestStep("Verify Flight Flex is available on Option Page",
                pageObjectManager.getOptionsPage().getFlightFlexCardAddButton().isDisplayed());
    }

}
