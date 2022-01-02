package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

// **********************************************************************************************
// TODO: [IN:24226] CP: BP: Passenger Info Page PI: Non-US residents are not allowed to continue without fill the state field even when this one is not a mandatory field for Non-US residents
/**10/21/19 test case passed, removed active bug tag**/
// Test Case ID: TC90704
// Test Name   : CP_BP_Payment Page_Travel Guard_Non-U.S Residents
// Description : Verify TG section for Non-Us resident
// Created By  : Kartik Chauhan
// Created On  : 13-May-2019
// Reviewed By : Salim Ansari
// Reviewed On : 15-May-2019
// **********************************************************************************************
public class TC90704 extends BaseClass {

    @Parameters ({"platform"})
    @Test (groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "Within21Days" , "Adult" , "Guest" , "NonStop" ,
                     "BookIt" , "ContactInformation" , "NoBags" , "NoSeats" , "CheckInOptions" , "PaymentUI" , "PassengerInformationUI"})
    public void CP_BP_Payment_Page_Travel_Guard_Non_US_Residents(@Optional("NA")String platform) {
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90704 under SMOKE Suite on " + platform + " Browser", true);
        }

        /******************************************************************************
         ****************************Navigate to Passenger Info Page*****************
         ******************************************************************************/
        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "Oneway";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LAS";
        final String DEP_DATE 			= "10";
        final String ARR_DATE 			= "NA";
        final String ADULTS				= "1";
        final String CHILDS				= "0";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "Nonstop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";
        final String JOURNEY            = "Dep";

        //Option page constant
        final String OPTION_VALUE		= "CheckInOption:MobileFree";

//Step--1
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

        //wait till page is load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType(JOURNEY, DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //wait till page is load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageObjectManager.getPassengerInfoPage().getContactPersonCountryDropDown().sendKeys("peru");
        WaitUtil.untilTimeCompleted(1200);
//        getDriver().findElements(By.xpath("//input[@id='provinceState']")).get(0).sendKeys("aaa");

        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seat Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Option Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
        WaitUtil.untilPageLoadComplete(getDriver());
//STEP--2
        //verify user land on payment page
        final String PAYMENT_URL = "book/payment";

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify User is redirected on payment Page",getDriver().getCurrentUrl(),PAYMENT_URL);
//STEP--3
        //Verify TG on Payment Page
        ValidationUtil.validateTestStep("Travel Guard Section is not displaying for Non-Us resident on Payment Page",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getTravelMoreWorryLessText()));
    }
}