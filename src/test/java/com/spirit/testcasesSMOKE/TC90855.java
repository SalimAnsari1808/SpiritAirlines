package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;
import org.testng.annotations.Optional;
import java.util.*;

//**********************************************************************************************
//Test Case ID: TC90855
//Description: Search Widget_ CP_BP_UMNR, DOM-INT
//Created By : Sunny Sok
//Created On : 09-May-2019
//Reviewed By: Salim Ansari
//Reviewed On: 10-May-2019
//**********************************************************************************************
public class TC90855 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "RoundTrip" , "DomesticInternational" , "WithIn7Days" ,"Outside21Days", "Child" , "Guest" , "HomeUI"})
    public void Search_Widget_CP_BP_UMNR_DOM_INT(@Optional("NA") String platform) {
        /******************************************************************************
         ***************************Navigate to Options Page***************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90855 under SMOKE Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "RoundTrip";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "CUN";
        final String DEP_DATE 			= "2";
        final String ARR_DATE 			= "30";
        final String ADULTS 			= "0";
        final String CHILDS 			= "1";
        final String INFANT_LAP 		= "0";
        final String INFANT_SEAT		= "0";

        //open browser
        openBrowser(platform);

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

        //wait for popup to appear
        WaitUtil.untilPageLoadComplete(getDriver());

        //Constants used for validation
        final String YOUNG_TRAVELERS_HEADER     = "Young Travelers";
        final String YOUNG_TRAVELER_LINE1       = "We're sorry, Unaccompanied Minor Service is not permitted to/from international destinations.";
        final String YOUNG_TRAVELER_LINE2       = "Unaccompanied Minors are children ages 5 through 14 who are not accompanied by an adult (at least 15 years or older).";
        final String YOUNG_TRAVELER_LINE3       = "Please review our Unaccompanied Minor acceptance policies.";

        //Validate Young Travelers popup is displayed
        ValidationUtil.validateTestStep("User Verify Young Travelers popup is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getYoungTravelerPopup()));

        //Validate Young Travelers popup Header
        ValidationUtil.validateTestStep("User Verify Young Travelers popup Header",
                pageObjectManager.getHomePage().getYoungTravelerPopupHeaderText().getText(),YOUNG_TRAVELERS_HEADER);

        //Validate Young Travelers popup Verbiage
        List<WebElement> youngTravelersVerbiage = pageObjectManager.getHomePage().getYoungTravelerPopupText();
        for(int i = 0; i < youngTravelersVerbiage.size(); i++)
        {
            String Verbiage[] = new String[]{YOUNG_TRAVELER_LINE1,YOUNG_TRAVELER_LINE2, YOUNG_TRAVELER_LINE3};
            ValidationUtil.validateTestStep("User validates Young Travelers verbiage",
                    youngTravelersVerbiage.get(i).getText(),Verbiage[i]);
        }

        //Validate Young Travelers popup close button is displayed
        ValidationUtil.validateTestStep("User verify Close button on Young Travelers popup is displayed",
                pageObjectManager.getHomePage().getYoungTravelerPopupCloseButton().isDisplayed());

        //Click Young Travelers popup close button
        pageObjectManager.getHomePage().getYoungTravelerPopupCloseButton().click();

        //Validate Young Travelers popup is not displayed
        ValidationUtil.validateTestStep("User verify Travelers popup is not displayed",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getYoungTravelerPopup()));
    }
}
