package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

// **********************************************************************************************
// TestCase :003 - CP - Hub Packaging - View All Cars - NEGATIVE - Validate that a UNMR does not receive View All Cars link
// Description:Cars section are not displaying for UMNR
// Created By : Kartik Chauhan
// Created On : 07-Nov-2019
// Reviewed By: Salim Ansari
// Reviewed On: 18-Nov-2019
// **********************************************************************************************

public class TC373708 extends BaseClass {

    @Parameters ({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "Outside21Days", "Child","HomeUI","NonStop","BookIt",
                    "NoBags","NoSeats","OptionalUI","PassengerInformationUI"})
    public void CP_Hub_Packaging_View_All_Cars_NEGATIVE_Validate_that_a_UNMR_does_not_receive_View_All_Cars_link (@Optional("NA")String platform){

        if(!platform.equals("NA")){
            ValidationUtil.validateTestStep("Starting Test Case ID TC373708 under GoldFinger suite on " + platform +" Browser", true);
        }
        /******************************************************************************
         ****************************Navigate to Passenger Info Page*****************
         ******************************************************************************/
        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "RoundTrip";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LAS";
        final String DEP_DATE 			= "25";
        final String ARR_DATE 			= "30";
        final String ADULTS				= "0";
        final String CHILDS				= "1";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 	    	= "Nonstop";
        final String ARR_Flight 	    	= "Nonstop";
        final String FARE_TYPE 	    		= "Standard";
        final String UPGRADE_VALUE 	  	    = "BookIt";

//Step1/2/3
        //open browser
        openBrowser( platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        //wait till page is load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //accept UMNR Fee popup and continue to Flight Availability page
        pageObjectManager.getHomePage().getUMNRAcceptButton().click();

        //wait till page is load is complete
        WaitUtil.untilPageLoadComplete(getDriver());
//Step4/5/6
        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //wait till page is load is complete
        WaitUtil.untilPageLoadComplete(getDriver());
//Step--7
        //Passenger Info Methods
        ValidationUtil.validateTestStep("Verify date fields are auto polpulated",
                pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(0).getAttribute("class").contains("ng-valid"));

        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
//Step--8/9/10
        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
//Step--11
        //Seat Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
//Step--12
        //Option Page Methods
        ValidationUtil.validateTestStep("Validating saving on car options is not displaying for pax under 21 year ",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPanel()));

    }
}