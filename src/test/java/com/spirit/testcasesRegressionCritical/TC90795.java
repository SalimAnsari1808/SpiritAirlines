package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.apache.poi.hssf.record.GroupMarkerSubRecord;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test CaseID: TC90795
//Title      : Flight Availability_CP_BP_Advanced Search Widget_Functionality_Validate items related to the Promo Code
//Created By : Kartik chauhan
//Created On : 25-July-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC90795 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "NewFlightSearch" , "FlightAvailabilityUI"})
    public void Flight_Availability_CP_BP_Advanced_Search_Widget_Functionality_Validate_items_related_to_the_Promo_Code (@Optional("NA") String platform) {
        /******************************************************************************
         ***************************Navigate to Confirmation Page**********************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90795 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant variables
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "5";
        final String ARR_DATE           = "8";
        final String ADULTS             = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();

        /******************************************************************************
         *******************************FA Page****************************************
         ******************************************************************************/
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
//Step--2
        //New sear on Search Widget is made and new dates are selected
        ValidationUtil.validateTestStep("Search Button is displaying",
                pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().isDisplayed());
//Step--3
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();
//Step--4
        //New sear on Search Widget is made and new dates are selected
        ValidationUtil.validateTestStep("Search panel is displaying on Flight Availability page",
                pageObjectManager.getFlightAvailabilityPage().getFANewSearchPanel().isDisplayed());
//STEP--5
        ValidationUtil.validateTestStep("PROMO CODE link is displaying on Flight Availability page",
                pageObjectManager.getHomePage().getPromoCodeLink().isDisplayed());

//STEP--6
        pageObjectManager.getHomePage().getOptionalServicesPopoverLink().click();

        ValidationUtil.validateTestStep("PROMO CODE pop-up is displaying after click on on Flight Availability page",
                pageObjectManager.getHomePage().getPromoSignUpNowLink().isDisplayed());
//STEP--7
        //click on PROMO sign link
        pageObjectManager.getHomePage().getPromoSignUpNowLink().click();

        //Wait till page load completely
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //create constant
        final String SIGNUP_URL   = "/account-enrollment";

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Sign up Link url is redirecting correctly",
                getDriver().getCurrentUrl(),SIGNUP_URL);

        //navigate back
        getDriver().navigate().back();

        //Wait till page load completely
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //Click on New Search Button
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();
//STEP--8
        //Click on Promo Code Link
        pageObjectManager.getHomePage().getPromoCodeLink().click();

        //PROMO CODE Textbox is displaying
        ValidationUtil.validateTestStep("PROMO CODE Textbox is displaying on Flight Availability page",
                pageObjectManager.getHomePage().getPromoCodeTextBox().isDisplayed());

    }
}