package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC374027
//Description: Task 27828: TC374027- 011 - CP - Flight Flex Packaging - Flight + Hotel - Validate that Flight Flex is not removed when selected via Thrills Combo
//Created By: Gabriela
//Created On: 21-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC374027 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"ActiveBug", "BookPath", "RoundTrip", "DomesticDomestic", "FlightHotel", "Outside21Days", "Adult",
                    "FreeSpirit","BundleIt", "CarryOn","CheckedBags", "NoSeats", "OptionalUI","FlightFlex",
                    "ShortCutBoarding"})
    public void CP_Flight_Flex_Packaging_Flight_Hotel_Validate_that_Flight_Flex_is_not_emoved_when_selected_via_Thrills_Combo(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC374027 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String LOGIN_ACCOUNT      = "FSEmail";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "132";
        final String ARR_DATE           = "133";
        final String ADULT              = "3";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String UPGRADE_VALUE      = "BundleIt";

        //Seats Page Constant Values
        final String DEP_SEATS          = "Standard|Standard|Standard";

//- Step 1: Land on current test environment.
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 2: Login as a FS member
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);

//- Step 3: Click on Vacation tab and select Flight + Hotel
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);

//- Step 4: Input the following: DOM_DOM | Any Date 3 months in the future | 2 PAX | 1 Room and click SEARCH VACATION
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 5: Select any hotel and proceed to the Options page with No Car | BUNDLE IT |  Defaulted / Included Bags that comes w/ BUNDLE IT | Any Free Seats
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("NA" , "NA");

        //Selecting No Car
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getCarPage().getCarsPageContinueWithoutCarButton().click();

        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //***Passenger Information Page Methods **/
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //*** Bags Page Methods**/
        pageMethodManager.getBagsPageMethods().continueWithOutChangesBag();

        //*** Seats Page Methods **/
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEATS); //Method failing due not information is saved on FA Page
        pageMethodManager.getSeatsPageMethods().selectReturningSeats(DEP_SEATS);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

//- Step 6:Verify that Flight Flex is selected and is not grayed out
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Verifying Flight Flex is displayed as selected on Options page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getFlightFlexCardSelectedLabel()));
    }
}