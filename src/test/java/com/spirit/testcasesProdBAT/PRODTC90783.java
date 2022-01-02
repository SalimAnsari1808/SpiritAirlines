package com.spirit.testcasesProdBAT;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC90783
//Test Name: Task 23299: 31448 Bags_CP_BP_DOM_1PAX_Validate Embargo Restrictions and More Information links on verbiage at the end of the page
//Description:
//Created By : Gabriela
//Created On : 13-MAY-2019
//Reviewed By: Salim Ansari
//Reviewed On: 14-MAY-2019
//**********************************************************************************************

public class PRODTC90783 extends BaseClass {

    @Parameters ({"platform"})
    @Test(groups="Production")

    public void Bags_CP_BP_DOM_1PAX_Validate_Embargo_Restrictions_and_More_Information_links_on_verbiage_at_the_end_of_the_page (@Optional("NA")String platform){
        /******************************************************************************
         *********************Navigate to Flight Availability Bags Page****************
         ******************************************************************************/

        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID PRODTC90783 under PRODUCTION Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "Oneway";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "ORD";
        final String DEP_DATE           = "5";
        final String ARR_DATE           = "NA";
        final String ADULTS             = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Bags Page Constant Value
        final String BAGS_URL           = "book/bags";
        final String EMBARGO_URL        = "https://customersupport.spirit.com/hc/en-us/articles/202096466-Are-there-any-restrictions-on-how-many-bags-I-can-bring-";
        final String MORE_INFO_URL      = "https://customersupport.spirit.com/hc/en-us/articles/202096616-How-much-does-Spirit-charge-for-bags-";

        //open browser
        openBrowser(platform);

//-- Step 1: Start creating a DOM booking 1 ADT Pax Select any flight, continue with Bare Fare  //Continue to the Bags page
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability PageMethods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Information Page Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//-- Step 2: Land on bags Page. (http://qaepic01.spirit.com/book/bags)
        ValidationUtil.validateTestStep("Validating user is on Bags Page",
                getDriver().getCurrentUrl(),BAGS_URL);

//-- Step 3: Click on the "Embargo Restrictions" hyperlink on the verbiage at the bottom of the page.
        pageObjectManager.getBagsPage().getEmbargoRestrictionsLink().click();

        TestUtil.switchToWindow(getDriver(),1);
        ValidationUtil.validateTestStep("Validating the user land on Are there any restrictions on how many bags I can bring",
                getDriver().getCurrentUrl().equals(EMBARGO_URL));

//-- Step 4: Return to the Bags page.
        getDriver().close();
        TestUtil.switchToWindow(getDriver(),0);
        WaitUtil.untilPageLoadComplete(getDriver());

//-- Step 5: Click on the "More Information" hyper link at the bottom of the page.
        pageObjectManager.getBagsPage().getMoreInformationLink().click();
        TestUtil.switchToWindow(getDriver(),1);
        ValidationUtil.validateTestStep("Validating the user land on More Information like How much does Spirit charge for bags",
                getDriver().getCurrentUrl().equals(MORE_INFO_URL));

        getDriver().close();
        TestUtil.switchToWindow(getDriver(),0);
        WaitUtil.untilPageLoadComplete(getDriver());
    }
}
