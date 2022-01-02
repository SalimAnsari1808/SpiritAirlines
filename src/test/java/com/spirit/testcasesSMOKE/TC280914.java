package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


//**********************************************************************************************
//Test Case ID: TC280914
//Test Name:  Task 23106: 31400 590. E2E_FSMC_OW DOM 1 ADT 2 Infant (1 lap 1 with seat)_Bundle Direct_Bundle Bags_Free Seats_No Extras Web CI_Credit Card
//Description: Validating FSMC member with 1 infant in lap and other with seat, can select bundle options and finish the
//              booking satisfactory
//Created By : Gabriela
//Created On : 25-APR-2019
//Reviewed By: Salim Ansari
//Reviewed On: 26-APR-2019
//**********************************************************************************************

public class TC280914 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "Within21Days" , "Adult" , "InfantSeat" ,
            "InfantLap" , "FSMasterCard" ,"NonStop" , "BundleIt" ,"FlightAvailabilityUI", "PassengerInformationUI" ,
            "BagsUI" , "CarryOn" ,"Standard", "CheckedBags" , "SeatsUI" , "ShortCutBoarding" , "FlightFlex" , "Visa"})
    public void E2E_FSMC_OW_DOM_1_ADT_2_Infant_1_lap_1_with_seat__Bundle_Direct_Bundle_Bags_Free_Seats_No_Extras_Web_CI_Credit_Card(@Optional("NA") String platform) {
        //*******************************************************************
        //****************Navigate to Confirmation Page**********************
        //*******************************************************************
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280914 under SMOKE Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String LOGIN_ACCOUNT      = "FSMCEmail";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "ATL";
        final String DEP_DATE           = "15";
        final String ARR_DATE           = "NA";
        final String ADULTS             = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "1";
        final String INFANT_SEAT        = "1";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         ="NonStop";
        final String FARE_TYPE          ="Standard";
        final String UPGRADE_VALUE      ="BundleIt";

        //Seats Page Constant Values
        final String DEP_SEATS			= "Standard|Standard|";

        //payment page constant
        final String CARD_TYPE          = "VisaCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //Confirmation Page Constant value
        final String BOOKING_STATUS     = "Confirmed";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);

        //*******************************************************************
        //************************Upgrade and Save Pop Up********************
        //*******************************************************************
        //Verifying Bundle saving is higher than Boost it
        WaitUtil.untilPageLoadComplete(getDriver());

        //Converting Bundle Saving from sting to double
        String BundleSaveUpto = pageObjectManager.getFlightAvailabilityPage().getBundleItSaveUpToPriceText().getText();
        String BundleSaveUptoSub = BundleSaveUpto.replace("Save Up To $", "").replace(",","");
        double BundleSaveDouble = Double.parseDouble(BundleSaveUptoSub);

        //Converting Bundle Saving from sting to double
        String BoostSaveUpto = pageObjectManager.getFlightAvailabilityPage().getBoostItSaveUpToPriceText().getText();
        String BoostSaveUptoSub = BoostSaveUpto.replace("Save Up To $", "").replace(",","");
        double BoostSaveDouble = Double.parseDouble(BoostSaveUptoSub);
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Verifying Bundle saving is higher than Boost it",
                BundleSaveDouble > BoostSaveDouble);

        //Selecting Bundle It Options
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //*******************************************************************
        //************************Passenger Information page*****************
        //*******************************************************************
        //Verifying Bundle saving match in this page, infant DOB
        WaitUtil.untilPageLoadComplete(getDriver());

        //Verifying infant DOB fields are present
        ValidationUtil.validateTestStep("Verifying infant in lap DOB is displayed",
                !pageObjectManager.getPassengerInfoPage().getInfantDOBListTextBox().get(0).getAttribute("value").isEmpty());

        ValidationUtil.validateTestStep("Verifying infant with seat DOB is displayed",
                !pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(1).getAttribute("value").isEmpty());

        //Selecting Traveling with a car seat
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getPassengerInfoPage().getInfantTravelingWithCarSeatCheckBox().get(0).click();

        //Filling fields and continue
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //*******************************************************************
        //*******************************Bags page***************************
        //*******************************************************************
        //Verifying city pair is correct
        ValidationUtil.validateTestStep("Verifying departure city is correct",
                pageObjectManager.getBagsPage().getDepartureCityText().getText(),DEP_AIRPORT_CODE);

        ValidationUtil.validateTestStep("Verifying arrival city is correct",
                pageObjectManager.getBagsPage().getDepartureCityText().getText(),ARR_AIRPORT_CODE);

        //Verifying there only 2 passengers present on Bags Page
        ValidationUtil.validateTestStep("Validating only 2 passengers can select bags",
                pageObjectManager.getBagsPage().getDepartingPassengerFlightContainerListText().size() <= 2);

        pageMethodManager.getBagsPageMethods().verifySelectedBaseFareBags(UPGRADE_VALUE);

        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

        //*******************************************************************
        //****************************Seats Page*****************************
        //*******************************************************************
        //Selecting standard seats
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEATS);
        pageMethodManager.getSeatsPageMethods().verifySelectedBaseFareSeats(UPGRADE_VALUE,DEP_SEATS,"NA");
        //continue to Options page
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //*******************************************************************
        //****************************Options Page***************************
        //*******************************************************************
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //*******************************************************************
        //****************************Payment Page***************************
        //*******************************************************************
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //*******************************************************************
        //****************Validation on Confirmation Page********************
        //*******************************************************************
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
    }
}