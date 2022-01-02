package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC280982
//Description: Task 24332: 31717 720. E2E_FSMC_RT DOM 1 ADT 1 child +15 -18_SW Change Aiports Bundle Fare Connecting Flight_STD_RT 1CO 0CB_Any Seats_No Extras_CI Web_Credit Card
//Created By: Gabriela
//Created On: 14-Jun-2019
//Reviewed By: Kartik Chauhan
//Reviewed On:20-Jun-2019
//**********************************************************************************************

public class TC280982 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "RoundTrip", "DomesticDomestic" , "Adult" , "Child" , "WithIn7Days" , "FSMasterCard" ,
                    "NewFlightSearch" , "Connecting", "BundleIt" , "MandatoryFields" , "CarryOn" , "CheckedBags" , "Standard" ,
                    "FlightFlex", "ShortCutBoarding","CheckInOptions", "Visa" , "TravelInsurancePopUp" , "ReservationUI",
                    "DynamicShoppingCartUI","ConfirmationUI","PaymentUI"})
    public void E2E_FSMC_RT_DOM_1_ADT_1_child_15_18_SW_Change_Aiports_Bundle_Fare_Connecting_Flight_STD_RT_1CO_0CB_Any_Seats_No_Extras_CI_Web_Credit_Card(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280982 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String LOGIN_ACCOUNT      = "FSMCEmail";
        final String TRIP_TYPE 			= "RoundTrip";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "LAS";
        final String DEP_AIRPORT_CODE_1	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LAX";
        final String ARR_AIRPORT_CODE_1	= "BWI";
        final String DEP_DATE 			= "3";
        final String ARR_DATE 			= "5";
        final String ADULT  			= "1";
        final String CHILD  			= "1";
        final String INFANT_LAP 		= "0";
        final String INFANT_SEAT		= "0";
        final int CHILD_16YEARS         = -5840;

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		= "Connecting";
        final String RET_FLIGHT         = "Connecting";
        final String FARE_TYPE			= "Standard";
        final String UPGRADE_VALUE      = "BundleIt";

        //Seats Page Method
        final String DEP_SEAT           = "Standard|Standard||Standard|Standard";
        final String RET_SEAT           = "Standard|Standard||Standard|Standard";

        //Options Page Constant Values
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String CARD_TYPE          = "VisaCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //Confirmation Page Constant Values
        final String BOOKING_STATUS     = "Confirmed";
        final String CONFIRMATION_URL   = "book/confirmation";

        //open browser
        openBrowser(platform);

        /****************************************************************************
         * ************************Home Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //enter DOB of child 15-18 years
        pageObjectManager.getHomePage().getChildTravelerPanel().get(0).findElement(By.tagName("input")).sendKeys(TestUtil.getStringDateFormat((CHILD_16YEARS), "MM/dd/yyyy"));
        WaitUtil.untilTimeCompleted(3000);
        pageObjectManager.getHomePage().getChildPopUpCloseButton().click();

        /****************************************************************************
         * *************Flight Availability Page Methods*****************************
         ****************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE_1, ARR_AIRPORTS, ARR_AIRPORT_CODE_1);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //get the DOB box available
        pageObjectManager.getHomePage().getChildTravelerPanel().get(0).findElement(By.tagName("input")).sendKeys(TestUtil.getStringDateFormat((CHILD_16YEARS), "MM/dd/yyyy"));
        WaitUtil.untilTimeCompleted(3000);
        pageObjectManager.getHomePage().getChildPopUpCloseButton().click();

        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        /****************************************************************************
         * *****************Passenger Information Page Methods************************
         ****************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(1).sendKeys(TestUtil.getStringDateFormat((CHILD_16YEARS), "MM/dd/yyyy"));
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().verifySelectedBaseFarePassengerInfo(UPGRADE_VALUE);//Bare Fare
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /****************************************************************************
         * ************************Bags Page Methods*********************************
         ****************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getBagsPageMethods().verifySelectedBaseFareBags(UPGRADE_VALUE);//Bare Fare
        pageMethodManager.getBagsPageMethods().continueWithOutChangesBag();

        /****************************************************************************
         * ***********************Seats Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEAT);
        pageMethodManager.getSeatsPageMethods().selectReturningSeats(RET_SEAT);
        pageMethodManager.getSeatsPageMethods().verifySelectedBaseFareSeats(UPGRADE_VALUE,DEP_SEAT,RET_SEAT);//Bare Fare
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        /****************************************************************************
         * *********************Options Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getOptionsPageMethods().verifySelectedBaseFareOptions(UPGRADE_VALUE);//Bare Fare
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /****************************************************************************
         * *********************Payment Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getPaymentPageMethods().verifySelectedBaseFarePayment(UPGRADE_VALUE);//Bare Fare
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        /*************************************************************************************************************
         * *********************************Confirmation Page Method**************************************************
         *************************************************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //Verify reached on confirmation page
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page",
                getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //Verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);

        pageMethodManager.getConfirmationPageMethods().verifySelectedBaseFareConfirmation(UPGRADE_VALUE);//Bare Fare

    }
}