package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC91233
//Description: CP_Header_Español_Book, My Trips, Check-In, Flight Status_Link
//Created By: Kartik chauhan
//Created On: 1-August-2019
//Reviewed By: Salim Ansari
//Reviewed On: 1-August-2019
//**********************************************************************************************

public class TC91233 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups={"BookPath", "MyTrips", "CheckIn", "RoundTrip", "InternationalDomestic", "WithIn7Days", "Adult", "HomeUI", "Spanish","Header"})

    public void CP_Header_Español_Book_My_Trips_CheckIn_Flight_Status_Link(@Optional("NA") String platform) {
        /******************************************************************************
         ***************************Navigate to Payment Page***************************
         ******************************************************************************/

        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91233 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "Spanish";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "CUN";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "BWI";

        //open browser
        openBrowser(platform);
//STEP--1
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
//STEP--2
        //Change language to spanish
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);
//STEP--3
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //wait till page load completely
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);
//STEP--4
        //Click on Spirit Logo
        pageObjectManager.getHeader().getSpiritLogoImage().click();

        //wait till page load completely
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //wait till page load completely
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);
//STep--5
        //validate user land to FA page
        ValidationUtil.validateTestStep("FA page is displaying",
                pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityPageHeaderText().isDisplayed());
//STEP--6
        //click on My Trip link
        pageObjectManager.getHeader().getMyTripsLink().click();
//STEP--7
        //Click on Spirit Logo
        pageObjectManager.getHomePage().getBookPathLink().click();

        //wait till page load completely
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //Home Page method
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //wait till page load completely
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //clcik on My Trip Link
        pageObjectManager.getHeader().getMyTripsLink().click();

        //validate passenger Last name Textbox is displaying
        ValidationUtil.validateTestStep("Under my Trip- passenger Last name Textbox is displaying under Spanish culture",
                pageObjectManager.getHomePage().getMyTripLastNameTextBox().isDisplayed());

        //validate Confirmation Code Textbox is displaying
        ValidationUtil.validateTestStep("Under my Trip- Confirmation Code Textbox is displaying under Spanish culture",
                pageObjectManager.getHomePage().getMyTripConfirmationCodeTextBox().isDisplayed());

        //validate Continue Button is displaying
        ValidationUtil.validateTestStep("Under my Trip- Continue Button is displaying under Spanish culture",
                pageObjectManager.getHomePage().getMyTripContinueButton().isDisplayed());
//STEP--8
        //Click on Spirit Logo
        pageObjectManager.getHomePage().getBookPathLink().click();

        //wait till page load completely
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //Home Page method
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //wait till page load completely
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //user click on Check In Link
        pageObjectManager.getHeader().getCheckInLink().click();

        //validate passenger Last name Textbox is displaying
        ValidationUtil.validateTestStep("Under Check-In path- passenger Last name Textbox is displaying under Spanish culture",
                pageObjectManager.getHomePage().getCheckInLastNameTextBox().isDisplayed());

        //validate Confirmation Code Textbox is displaying
        ValidationUtil.validateTestStep("Under Check-In path- Confirmation Code Textbox is displaying under Spanish culture",
                pageObjectManager.getHomePage().getCheckInConfirmationCodeTextBox().isDisplayed());

        //validate Continue Button is displaying
        ValidationUtil.validateTestStep("Under Check-In path- Continue Button is displaying under Spanish culture",
                pageObjectManager.getHomePage().getCheckInButton().isDisplayed());
//STEP--9
        //Click on Spirit Logo
        pageObjectManager.getHomePage().getBookPathLink().click();

        //wait till page load completely
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //Home page method
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //wait till page load completely
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //click on Flight Status Link
        pageObjectManager.getHeader().getFlightStatusLink().click();

        //wait till page load completely
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //validate Check by Destination option is displaying
        ValidationUtil.validateTestStep("Under Flight Status- Check by Destination option is displaying under Spanish culture",
                pageObjectManager.getHomePage().getCheckByDestinationLabel().isDisplayed());

        //validate Check by Flight Number option is displaying
        ValidationUtil.validateTestStep("Under Flight Status- Check by Flight Number is displaying under Spanish culture",
                pageObjectManager.getHomePage().getCheckByFlightNumberLabel().isDisplayed());

    }

}