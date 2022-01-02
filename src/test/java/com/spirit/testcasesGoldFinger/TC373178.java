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
//Test Case ID: TC373178
//Description: Task 27134: TC373178 - 002 - CP - New Car Page Rules - NEGATIVE - Verify that an Adult under the age of 21 does not receive Car Upsell
//Created By:  Gabriela
//Created On:  01-Nov-2019
//Reviewed By: Salim Ansari
//Reviewed On: 07-Nov-2019
//**********************************************************************************************
public class TC373178 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "Outside21Days", "Adult", "Guest", "NonStop", "BookIt", "MandatoryFields",
                    "NoBags", "NoSeats", "OptionalUI"})
    public void CP_New_Car_Page_Rules_NEGATIVE_Verify_that_an_Adult_under_the_age_of_21_does_not_receive_Car_Upsell(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373178 under GoldFinger Suite on " + platform + " Browser", true);
        }

        /******************************************************************************
         *******************************Navigate to Option Page************************
         ******************************************************************************/
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "31";
        final String ARR_DATE           = "33";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String ARR_Flight         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //declare test case constant
        final int FIRST_PASSENGER       = 0;
        final int EIGHTEEN_YEAR_OLD     = -6570;

//- Step 1: Go to Spirit home page in test environment
        openBrowser(platform);

//- Step 2: Start creating a RT | DOM |1 Pax age 15 - 20 | No bags | No seats| booking
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 3: On flight Availability page select flight and click on "CONTINUE"
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
//- Step 4: Select " BOOK IT"
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//- Step 5: Fill in all passenger information make sure pax is between age 15- 20 and click on "CONTINUE"
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(FIRST_PASSENGER).clear();
        pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(FIRST_PASSENGER).sendKeys(TestUtil.getStringDateFormat((EIGHTEEN_YEAR_OLD), "MM/dd/yyyy"));

        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Step 6: Select " CONTINUE WITHOUT ADDING BAGS" "Are you sure?" pop up will appear select "I DON'T NEED BAGS"
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//- Step 7: Select "CONTINUE WITHOUT SELECTING SEATS"s
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();



//- Step 8: Verify there is no option for rental cars
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Validating Saving on Cars section is not displayed on Options page for passengers under 21 years old",
                !TestUtil.verifyElementDisplayed(getDriver(),By.xpath(pageObjectManager.getCarPage().xpath_CarCardNameText)));



    }
}