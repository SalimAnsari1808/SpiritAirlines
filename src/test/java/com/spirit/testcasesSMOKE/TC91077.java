package com.spirit.testcasesSMOKE;


import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.*;
import org.testng.annotations.Optional;

//@Listeners(com.spirit.testNG.Listener.class)

//**********************************************************************************************
//Test Case ID: TC91077
//Title       : 31279 Customer Info_CP_BP _Verify 15 years or older UMNR is treated as a adult
//Description : Confirm child between 15 and 17 is listed as an adule passenger on the passenger
//              information page
// Created By : Alex Rodriguez
//Created On  : 02-Apr-2019
//Reviewed By : Salim Ansari
//Reviewed On : 04-Apr-2019
//**********************************************************************************************

public class TC91077 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "Child" , "Guest" , "NonStop" ,
            "Connecting" , "BookIt" , "MandatoryFields","PassengerInformationUI"})
    public void Customer_Info_CP_BP_Verify_15_years_or_older_UMNR_is_treated_as_an_adult(@Optional("NA") String platform) {
        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91077 in Smoke Suite on " + platform + " Browser"   , true);
        }

        //declare constant in navigation
        final int FIRST_PAX             = 0;

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE 		    = "RoundTrip";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 	  	= "AllLocation";
        final String ARR_AIRPORT_CODE  	= "LGA";
        final String DEP_DATE 	        = "2";
        final String ARR_DATE 	    	= "30";
        final String ADULTS 		    = "0";
        final String CHILDREN		    = "1";
        final String INFANT_LAP 	    = "0";
        final String INFANT_SEAT	    = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 	    = "Nonstop";
        final String ARR_Flight 	    = "Connecting";
        final String FARE_TYPE 	        = "Standard";
        final String UPGRADE_VALUE 	    = "BookIt";

        //declare constant used on Passenger Page
        final String PASSENGER_PAGE     = "book/passenger";

        //declare variable used in navigation
        int UMNRAge                     = -5750;

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

        //******************************************************************************
        //**********************Input age for UMNR as greater than 15*******************
        //******************************************************************************/

        pageObjectManager.getHomePage().getChildPopUpBirthBoxes().get(FIRST_PAX).click();

        //convert date in required format
        pageObjectManager.getHomePage().getChildPopUpBirthBoxes().get(FIRST_PAX).sendKeys(TestUtil.getStringDateFormat((UMNRAge), "MM/dd/yyyy"));

        //wait till UMNR popup appears
        WaitUtil.untilPageLoadComplete(getDriver());

        //close young Traveller Modal
        pageObjectManager.getHomePage().getChildPopUpCloseButton().click();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //******************************************************************************
        //********************Validation Customer Information Page**********************
        //*****************************************************************************/
        //declare constant used in validation
        final String HEADER_TEXT        = "Adult";

        //declare variable used in method
        String currentDOB;
        String newDOB;

     //Step 2
        //validate user taken to the Passenger Information Page
        ValidationUtil.validateTestStep("User reached the Passenger information Page", getDriver().getCurrentUrl(),PASSENGER_PAGE);

     //Step 3-4
        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();

        //get current DOB
        currentDOB = JSExecuteUtil.getElementTextValue(getDriver(),pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(FIRST_PAX));

        //clear text
        TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(FIRST_PAX));

        //modify DOB
        pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(FIRST_PAX).sendKeys(TestUtil.getStringDateFormat((UMNRAge), "MM/dd/yyyy"));

        //get new DOB
        newDOB = JSExecuteUtil.getElementTextValue(getDriver(),pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(FIRST_PAX));

        //verify DOB can be edited
        ValidationUtil.validateTestStep("User can change the DOB of UMNR which is treated as Adult", !currentDOB.equals(newDOB));

        //validate Adult passenger text present on Passenger Information page
        ValidationUtil.validateTestStep("User verifies child between ages of 15-17 is displayed as an Adult",
                pageObjectManager.getPassengerInfoPage().getPassengerHeaderText().get(FIRST_PAX).getText().contains(HEADER_TEXT) );

    //Step 5
        ValidationUtil.validateTestStep("User verifies Active duty checkbox is present", pageObjectManager.getPassengerInfoPage().getActiveMilitaryPersonnelListCheckBox().size()>0);

     //Step-6
        //Skip in this test case

    //Step-7

    }
}
