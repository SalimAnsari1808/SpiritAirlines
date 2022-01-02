package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.*;
import com.spirit.enums.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;
import org.testng.annotations.Optional;


//@Listeners(com.spirit.testNG.Listener.class)

//**********************************************************************************************
//Test Case ID: TC91483
//Title       : Flight Only  1 CHD  14 years old on Domestic Connecting Flights
//Description : Confirm Unaccompanied minor is unable to move past the passenger information
//              page when selecting a connecting flight
//Created By  : Alex Rodriguez
//Created On  : 27-Mar-2019
//Reviewed By : Salim Ansari
//Reviewed On : 28-Mar-2019
//**********************************************************************************************
public class TC91483 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "Outside21Days" , "Child" , "Guest"
            , "NonStop","Connecting", "BookIt","PassengerInformationUI"})
    public void Flight_Only_1_CHD_14_years_old_on_Domestic_Connecting_Flights(@Optional("NA") String platform) {
        /******************************************************************************
         *************************Navigate to Passenger Info Page**********************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91483 in Smoke Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String LANGUAGE               = "English";
        final String JOURNEY_TYPE           = "Flight";
        final String TRIP_TYPE 		    	= "RoundTrip";
        final String DEP_AIRPORTS 		    = "AllLocation";
        final String DEP_AIRPORT_CODE 	    = "FLL";
        final String ARR_AIRPORTS 	  	    = "AllLocation";
        final String ARR_AIRPORT_CODE  	    = "LGA";
        final String DEP_DATE 	    		= "2";
        final String ARR_DATE 	    		= "30";
        final String ADULTS 		       	= "0";
        final String CHILDREN		      	= "1";
        final String INFANT_LAP 	     	= "0";
        final String INFANT_SEAT	     	= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 	    	= "Nonstop";
        final String ARR_Flight 	    	= "Connecting";
        final String FARE_TYPE 	    		= "Standard";
        final String UPGRADE_VALUE 	  	    = "BookIt";
//Step 1
        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        //wait till UMNR popup appears
        WaitUtil.untilPageLoadComplete(getDriver());

        //accept UMNR Fee popup and continue to Flight Availability page
        pageObjectManager.getHomePage().getUMNRAcceptButton().click();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//Step 2
        //******************************************************************************
        //********************Validation Customer Information Page**********************
        //*****************************************************************************/
        //declare variable used in validation
        final String HOME_URL   = "spirit.com";

        //validate UMNR Connecting flight modal is present
        ValidationUtil.validateTestStep("User verifies \"We're sorry, Unaccompanied Minor Service is not permitted on connecting flights.\" modal is displayed and minor is unable to continue",
                pageObjectManager.getPassengerInfoPage().getUnaccompaniedMinorPopupReturnToHomepageButton().isDisplayed());

        //click on return to Home page
        pageObjectManager.getPassengerInfoPage().getUnaccompaniedMinorPopupReturnToHomepageButton().click();

        //validate UMNR is redirected to home page after selecting return to homepage button
        ValidationUtil.validateTestStep("User verifies they are redirected to the home page", getDriver().getCurrentUrl().contains(HOME_URL));
    }
}

