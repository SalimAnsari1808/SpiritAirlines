package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.LoginCredentialsData;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC91125
//Title: Flight_Availability_CP_BP_Successful_Login_to_use_MILES_Buy_Miles
//Description: Validating BUY MORE MILES button redirecting to the right URL
//Created By : Gabriela
//Created On : 09-May-2019
//Reviewed By: Salim Ansari
//Reviewed On: 11-May-2019
//**********************************************************************************************

public class TC91125 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "OneWay" , "Miles" , "DomesticDomestic" , "Within21Days" , "Adult" , "FSMasterCard" ,
                    "FlightAvailabilityUI"})
    public void  Flight_Availability_CP_BP_Successful_Login_to_use_MILES_Buy_Miles(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91125 under SMOKE Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "Oneway";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "ATL";
        final String DEP_DATE 			= "8";
        final String ARR_DATE 			= "NA";
        final String ADULTS 			= "1";
        final String CHILD  			= "0";
        final String INFANT_LAP 		= "0";
        final String INFANT_SEAT		= "0";

        //flight availability page
        final String FA_URL             = "book/flights";
        final String BUY_MILES          = "BUY MORE MILES";
        final String BUY_MILES_URL      = "storefront.points.com";
        final String ZERO_MILES         = "ZeroMiles";
//https://buy.points.com/marketing/spirit/landing-page/?product=buy%20page
        //open browser
        openBrowser(platform);

//--Step 1 & 2: Access test environment and start booking a flight
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//--Step 3: Verify user lands on Flight Availability page url:
        ValidationUtil.validateTestStep("Validating the user land on Flight Availability Page",
                getDriver().getCurrentUrl(),FA_URL);
        //Flight Availability Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());

//--Step 4 and 5: Locate and Click on Miles button
        //Switch to Miles View
        pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselMilesViewSwitchLabel().click();

        //User signs into the Log-In modal with No miles User
        LoginCredentialsData loginCredentialsData = FileReaderManager.getInstance().getJsonReader().getCredentialsByUserType(ZERO_MILES);

//--Step 6: Enter a valid email and password
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHomePage().getUserNameBox().sendKeys(loginCredentialsData.userName);
        pageObjectManager.getHomePage().getPasswordBox().sendKeys(loginCredentialsData.password);

        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getHomePage().getLoginButton().click();

//--Step 7: Select a Flight and click Continue
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getDepartingStandardFarePriceText().get(0).click();

        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getFlightAvailabilityPage().getStandardFareButton().click();

//--Step 8: Click on BUY MORE MILES
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating the Buy More Miles button on Insufficient Miles Popup on Flight Availability Page",
                pageObjectManager.getFlightAvailabilityPage().getInsufficientMilesPopUpBuyMoreMilesButton().getText(),BUY_MILES);

        //Click on Buy More MIles button
        pageObjectManager.getFlightAvailabilityPage().getInsufficientMilesPopUpBuyMoreMilesButton().click();

        //Validate the user is taken to the right URL
        TestUtil.switchToWindow(getDriver(),1);

        //validate buy miles url
        ValidationUtil.validateTestStep("Validating the user land on Buy More Miles Page",
                getDriver().getCurrentUrl(),BUY_MILES_URL);

        getDriver().close();

        //switch back to default page
        TestUtil.switchToWindow(getDriver(),0);
    }
}
