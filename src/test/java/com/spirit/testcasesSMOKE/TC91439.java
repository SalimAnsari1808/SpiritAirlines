package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.*;
import com.spirit.enums.Context;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC91439
//Test Name:   CP_BP_Payment Page_9DFC savings banner
//Description:
//Created By : Sunny Sok
//Created On : 15-Apr-2019
//Reviewed By: Salim Ansari
//Reviewed On: 23-Apr-2019
//**********************************************************************************************
public class TC91439 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath , OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "NineDFC" , "Through" ,
            "BookIt" ,"CarryOn" , "CheckedBags" , "NoSeats" , "PaymentUI","CheckInOptions"})
    public void CP_BP_Payment_Page_9DFC_savings_banner(@Optional("NA") String platform) {
        /******************************************************************************
         ****************************Navigate to Payment Page**************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91439 under SMOKE Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String LOGIN_EMAIL 		= "NineDFCEmail";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "Oneway";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "5";
        final String ARR_DATE           = "NA";
        final String ADULTS				= "1";
        final String CHILDS				= "0";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String SORT_BY            = "Departure";
        final String DEP_FLIGHT         = "through";
        final String FARE_TYPE          = "Member";
        final String UPGRADEVALUE       = "Bookit";

        //Bags Page
        final String DEP_BAGS           = "Carry_1|Checked_2";

        //Options Page constant values
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //STEP--1
        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_EMAIL);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectSortingOption("Dep", SORT_BY);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);

        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADEVALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().continueWithSelectingBags();

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //option Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /******************************************************************************
         ***********************Validation on Payment Page*****************************
         ******************************************************************************/
        //declare constant used in validation
        final String PAYMENT_URL    = "book/payment";
        final String SAVING_TEXT    = "$9 Fare Club Discount Savings";

        ValidationUtil.validateTestStep("Verify User Navigated to payment page",
                getDriver().getCurrentUrl(),PAYMENT_URL);

        ValidationUtil.validateTestStep("Verify $9 FARE CLUB DISCOUNT SAVINGS pencil banner",
                pageObjectManager.getPaymentPage().getFareClubSavingVerbiageText().getText(),SAVING_TEXT);

        ValidationUtil.validateTestStep("Verify $9 FARE CLUB DISCOUNT SAVINGS amount",
                pageObjectManager.getPaymentPage().getFareClubSavingPriceText().getText(),scenarioContext.getContext(Context.BAGS_9DFC_SAVING).toString());

    }
}
