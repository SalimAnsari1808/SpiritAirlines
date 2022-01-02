package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC280976
//Test Name: 31715 714. E2E_FSMC_RT DOM 1 ADT_SW Change Date Bare Fare Connecting Flight_MIL_RT 1CO 2CB_No Seats_No Extras_CI Web_Travel Guard_Credit Card
//Created By : Kartik
//Created On : 18-Jun-2019
//Reviewed By: Salim Ansari
//Reviewed On: 18-Jun-2019
//**********************************************************************************************
public class TC280976 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "RoundTrip", "DomesticDomestic" , "Adult" ,"Outside21Days", "FSMasterCard" , "Military" ,
                    "NewFlightSearch" , "Connecting", "BookIt" , "CarryOn" , "CheckedBags" , "NoSeats" , "FlightFlex" ,
                    "ShortCutBoarding","CheckInOptions", "Discover" , "TravelInsurancePopUp","ActiveBug","ConfirmationUI","PaymentUI"})
    public void E2E_FSMC_RT_DOM_1_ADT_SW_Change_Date_Bare_Fare_Connecting_Flight_MIL_RT_1CO_2CB_No_Seats_No_Extras_CI_Web_Travel_Guard_Credit_Card(@Optional("NA") String platform) {
        /******************************************************************************
         ***********************Navigation to Confirmation Page************************
         ******************************************************************************/
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280976 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE               = "English";
        final String LOGIN_ACCOUNT          = "MilitaryFreeSpiritMasterCard";
        final String JOURNEY_TYPE           = "Flight";
        final String TRIP_TYPE              = "RoundTrip";
        final String DEP_AIRPORTS           = "AllLocation";
        final String DEP_AIRPORT_CODE       = "FLL";
        final String ARR_AIRPORTS           = "AllLocation";
        final String ARR_AIRPORT_CODE       = "LAS";
        final String DEP_DATE               = "15";
        final String DEP_DATE1              = "30";
        final String ARR_DATE               = "18";
        final String ARR_DATE1              = "34";
        final String ADULTS                 = "1";
        final String CHILD                  = "0";
        final String INFANT_LAP             = "0";
        final String INFANT_SEAT            = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT             = "Connecting";
        final String RET_FLIGHT             = "Connecting";
        final String FARE_TYPE              = "Standard";
        final String UPGRADE_VALUE          = "BookIt";

        //Bags Page Constant Vales

        //Options Page Constant Value
        final String OPTION_VALUE 		    = "FlightFlex|CheckInOption:MobileFree";

        //payment Page constant values
        final String SELECTED_OPTION        = "FlightFlex|ShortCutBoarding";
        final String CARD_TYPE              = "DiscoverCard1";
        final String TRAVEL_GUARD           = "Required";

        //Confirmation Page Constant value
        final String BOOKING_STATUS         = "Confirmed";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        /*Select new Flight Search Button*/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE1, ARR_DATE1);

        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Customer Info Page Method
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getPassengerInfoPage().getActiveMilitaryPersonnelListCheckBox().get(1).click();
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //******************************************************************
        //*****************************Bags Page ***************************
        //******************************************************************
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getBagsPageMethods().verifySelectedMilitaryBags();
        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

        //Seats page Method
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //******************************************************************
        //***************************Options Page***************************
        //******************************************************************
        final String OPTION_TEXT_ENGLISH	= "SELECTED";
        //verify Shortcut Boarding is pre-selected
        ValidationUtil.validateTestStep("Validating Shortcut Boarding is preselected on Options Page",
                pageObjectManager.getOptionsPage().getShortCutBoardingCardSelectedLabel().getText().toUpperCase().trim(),OPTION_TEXT_ENGLISH);

        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //******************************************************************
        //***************************Payment Page***************************
        //******************************************************************
        pageMethodManager.getPaymentPageMethods().verifyMilitaryPassengerLoginDetails();
        pageMethodManager.getPaymentPageMethods().verifyOptionSectionSelectedOptions(SELECTED_OPTION);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        /******************************************************************************
         ***********************Validation on Confirmation Page************************
         ******************************************************************************/
        //Confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
        pageMethodManager.getConfirmationPageMethods().verifyOptionSectionSelectedOptions(SELECTED_OPTION);

    }
}