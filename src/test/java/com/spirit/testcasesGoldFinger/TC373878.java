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

import java.util.List;

//**********************************************************************************************
//Test Case ID: TC373178
//Description: Flight Flex Packaging - Hub Packaging - Validate that Flight Flex is not removed when selected via Thrills Combo
//Created By:  Salim Ansari
//Created On:  20-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC373878 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "FlightCar", "DomesticDomestic", "Outside21Days", "Adult", "FreeSpirit", "BundleIt"})
    public void CP_New_Car_Page_Rules_NEGATIVE_Verify_that_an_Adult_under_the_age_of_21_does_not_receive_Car_Upsell(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373878 under GoldFinger Suite on " + platform + " Browser", true);
        }

        /******************************************************************************
         *******************************Navigate to Option Page************************
         ******************************************************************************/
        //Home Page Constant Values
        final String FS_LOGIN           = "FSEmail";
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "105";
        final String ARR_DATE           = "107";
        final String ADULT              = "2";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE 		= "25+";

        //Flight Availability Page Constant Values
        final String UPGRADE_VALUE      = "BundleIt";

        //Bags Page Constant Values
        final String BAGS_TYPE          = "Standard";

        //Seats Page Constant values
        final String DEP_SEATS          = "Standard|Standard||Standard|Standard";
        final String RET_SEATS          = "Standard|Standard||Standard|Standard";

        //Seats Page Constant Values


//- Step 1: Land on current test environment.
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();

//- Step 2: Sign into a FS account
        pageMethodManager.getHomePageMethods().loginToApplication(FS_LOGIN);

//- Step 3: Click on Vacation tab and select Flight + Car
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);

//- Step 4: Input the following: DOM_DOM | Any Date 3 months in the future | 2 PAX | Driver 25+ and click SEARCH VACATION
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 5:Select any car and proceed to the Options page with BUNDLE IT |  Defaulted / Included Bags that comes w/ BUNDLE IT | Any Free Seats
        //Flight+Car Page Methods
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();
        pageMethodManager.getCarPageMethods().selectCarOnCarPage("NA","NA");
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Page Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().selectPrimaryDriver();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
        WaitUtil.untilPageLoadComplete(getDriver());

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().selectBagsFare(BAGS_TYPE);

        //Seats Page Methoids
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEATS);
        pageMethodManager.getSeatsPageMethods().selectReturningSeats(RET_SEATS);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

//- Step 6: On the Options page, verify that Flight Flex is included in the booking due to BUNDLE IT and that it's within the Dynamic Shopping Cart under Options
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getHeader().getArrowOptionsItineraryImage().click();
        WaitUtil.untilTimeCompleted(1000);

        //verify Flight Flex is added to booking
        ValidationUtil.validateTestStep("Verify that Flight Flex is added to the booking via Dynamic Shopping Cart under Options",
                pageObjectManager.getHeader().getFlightFlexOptionItineraryLabel().isDisplayed());

        //verify Flight Flex is added to booking
        ValidationUtil.validateTestStep("Verify that Flight Flex header is marked with Selected test on Option Page",
                pageObjectManager.getOptionsPage().getFlightFlexCardSelectedLabel().getText(),"Selected");

    }
}