package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.PassengerInfoData;
import com.spirit.enums.Context;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC280967
//Test Name: Task 23474: 31415 675. E2E_FSMC_MC DOM MAX PAX ALL UMNR_Thru Flight_STD_No Bags_No Seats_No Extras_CI Web_Mastercard
// Created By: Gabriela
//Created On : 07-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 09-Aug-2019
//**************************************************************************************************

public class TC280967 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "MultiCity" , "DomesticDomestic" , "WithIn7Days" , "Child" , "FSMasterCard" , "Through" , "BookIt" , "NoBags" , "NoSeats" , "OptionalUI" , "MasterCard"})
    public void E2E_FSMC_MC_DOM_MAX_PAX_ALL_UMNR_Thru_Flight_STD_No_Bags_No_Seats_No_Extras_CI_Web_Mastercard(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280967 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String LOGIN_ACCOUNT      = "FreeSpiritMasterCardUMNR";
        final String TRIP_TYPE          = "MultiCity";
        final String DEP_AIRPORTS       = "AllLocation|AllLocation";
        final String DEP_AIRPORT_CODE   = "PHX|BOS";
        final String ARR_AIRPORTS       = "AllLocation|AllLocation";
        final String ARR_AIRPORT_CODE   = "FLL|FLL";
        final String TRAVEL_DATE        = "3|5";
        final String ADULTS             = "0";
        final String CHILD              = "5";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "Through";
        final String ARR_Flight         = "Through";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page Constant Values
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String CARD_TYPE         = "MasterCard";
        final String TRAVEL_GUARD      = "NotRequired";

        //Confirmation Page Constant Values
        final String BOOKING_STATUS     = "Confirmed";
        final String CONFIRMATION_URL   = "book/confirmation";

        //open browser
        openBrowser(platform);

        //********************** Home Page Methods ********************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirportsMultiCity(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDatesMultiCity(TRAVEL_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Filling infant information
        PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("FreeSpiritMasterCardUMNR");

        int intChildCount  = Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_CHILD_COUNT).toString());
        int ChildCounter =1;

        for (int i = 1; i < pageObjectManager.getHomePage().getChildPopUpBirthBoxes().size(); i++) {
            WebElement travellingChildSection = pageObjectManager.getHomePage().getChildTravelerPanel().get(i);

            //check
            if (intChildCount > ChildCounter) {
                //convert date in required format
                travellingChildSection.findElement(By.tagName("input")).sendKeys(TestUtil.getStringDateFormat((-2190 - ChildCounter), "MM/dd/yyyy"));

                travellingChildSection.click();

                //increment child date
                ChildCounter = ChildCounter + 1;
            }
        }

        //Select specific date for the FSMC UNMR
        pageObjectManager.getHomePage().getChildPopUpBirthBoxes().get(0).sendKeys(passengerInfoData.dob);

        //close child popup
        pageObjectManager.getHomePage().getChildPopUpCloseButton().click();

        //accept UMNR popup
        pageMethodManager.getHomePageMethods().selectUMNRPopup();

        //********************** Flight Availability Page Methods ********************************/
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //********************** Passenger Information Page Methods ********************************/
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //***************************** Bags Page Methods *****************************************/
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //***************************** Seats Page Methods *****************************************/
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //***************************** Options Page Methods *****************************************/
//        ValidationUtil.validateTestStep("Validating there is not check in options available for selection for UNMR",
//                !pageObjectManager.getOptionsPage().getCheckInOptionCardBodySelectDropDown().isEnabled());

        ValidationUtil.validateTestStep("Verify Check-In Option is disabled on Options Page",
                pageObjectManager.getOptionsPage().getCheckInOptionCardPanel().getAttribute("class"),"disabled");

        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //***************************** Payment Page Methods *****************************************/
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //***************************** Confirmation Page Methods *****************************************/
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page",
                getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
    }
}