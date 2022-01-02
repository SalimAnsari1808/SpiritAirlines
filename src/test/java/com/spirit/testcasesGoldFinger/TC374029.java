package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;


//**********************************************************************************************
//Test Case ID: TC374029
//Description: Flight Flex Packaging - Flight + Hotel + Car - Validate that Flight Flex is not removed when selected via Thrills Combo
//Created By: Salim Ansari
//Created On: 26-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC374029 extends BaseClass {

    @Parameters("platform")
    @Test(groups = {"BookPath" , "FlightCar" , "DomesticDomestic" , "Outside21Days" , "Adult" , "NonStop" , "BundleIt" ,
            "CarryOn" , "CheckedBags" , "Standard", "FlightFlex", "OptionalUI"})
    public void Flight_Flex_Packaging_Verbiage_Spanish_Flight_Car_Validate_verbiage_for_Flight_Flex_on_the_Hub_Page(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC374029 under GoldFinger Suite on " + platform + " Browser", true);
        }

        /******************************************************************************
         ****************************Navigate to Passenger Info Page*****************
         ******************************************************************************/
        //Home Page Constant Values
        final String LANGUAGE 			        = "English";
        final String LANGUAGE1 			        = "Spanish";
        final String JOURNEY_TYPE 		        = "Vacation";
        final String TRIP_TYPE 			        = "Flight+Hotel+Car";
        final String DEP_AIRPORTS 		        = "AllLocation";
        final String DEP_AIRPORT_CODE 	        = "FLL";
        final String ARR_AIRPORTS 		        = "AllLocation";
        final String ARR_AIRPORT_CODE 	        = "LAS";
        final String DEP_DATE 			        = "93";
        final String ARR_DATE 			        = "95";
        final String ADULT				        = "2";
        final String CHILD				        = "0";
        final String INFANT_LAP			        = "0";
        final String INFANT_SEAT		        = "0";
        final String DRIVER_AGE 		        = "21-24";

        //Flight Availability Page Constant Values
        final String UPGRADE_VALUE              = "BundleIt";

        //Bags page constant values
        final String BAGS_FARE_TYPE             = "Standard";
        //Seats Page Constant values
        final String DEP_SEATS                  = "Standard|Standard||Standard|Standard";
        final String RET_SEATS                  = "Standard|Standard||Standard|Standard";

        //Option page constant values
        final String OPTION_TEXT_FLIGHT_FLEX 	= "SELECTED";

//Step1--Land on current test environment.
        openBrowser( platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//STEP--2 Sign into a FSMC account
        pageMethodManager.getHomePageMethods().loginToApplication("FSMCEmail");

//STEP--3 Click on Vacation tab and keep the radio button defaulted to Flight + Hotel + Car
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);

//STEP--4 nput the following: DOM_DOM | Any Date 3 months in the future | 2 PAX | 1 Room | Driver 21-24 and click SEARCH VACATION
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//STEP--5 Select any hotel and car, and proceed to the Options page with BUNDLE IT |  Defaulted / Included Bags that comes w/ BUNDLE IT | Any Free Seats
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("NA","NA");
        pageMethodManager.getCarPageMethods().selectCarOnCarPage("NA","NA");
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().selectPrimaryDriver();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().selectBagsFare(BAGS_FARE_TYPE);

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEATS);
        pageMethodManager.getSeatsPageMethods().selectReturningSeats(RET_SEATS);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

//STEP--6 Verify that Flight Flex is selected and is not grayed out
        ValidationUtil.validateTestStep("Validating Flight Flex is preselected on Options Page",
                pageObjectManager.getOptionsPage().getFlightFlexCardSelectedLabel().getText().toUpperCase().trim(),OPTION_TEXT_FLIGHT_FLEX);

    }
}
