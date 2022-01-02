package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.PassengerInfoData;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//BUg ID: 23950(ALL UMNR pax with FSMC user @ passenger info page)
//Test Case ID: TC280932
//Test Name: 31689 607. E2E_FSMC_OW DOM MAX PAX ALL UMNR_Thru Flight_Thrills Bags_Free Seats_No Extras Web CI_Credit Card
//Description:
//Created By : Kartik
//Created On : 14-June-2019
//Reviewed By: Salim Ansari
//Reviewed On: 17-June-2019
//**********************************************************************************************

public class TC280932 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "WithIn21Days" , "Child" , "FSMasterCard" , "Through" , "BundleIt" ,
                    "CarryOn" , "CheckedBags" , "Standard" , "FlightFlex" ,"ShortCutBoarding", "Discover","DynamicShoppingCartUI","OptionalUI",
                    "ConfirmationUI","PaymentUI"})
    public void E2E_FSMC_OW_DOM_MAX_PAX_ALL_UMNR_Thru_Flight_Thrills_Bags_Free_Seats_No_Extras_Web_CI_Credit_Card(@Optional("NA") String platform) {

        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280932 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant variables
        final String LANGUAGE               = "English";
        final String LOGIN_ACCOUNT          = "FreeSpiritMasterCardUMNR";
        final String JOURNEY_TYPE           = "Flight";
        final String TRIP_TYPE              = "OneWay";
        final String DEP_AIRPORTS           = "AllLocation";
        final String DEP_AIRPORT_CODE       = "DEN";
        final String ARR_AIRPORTS           = "AllLocation";
        final String ARR_AIRPORT_CODE       = "FLL";
        final String DEP_DATE               = "15";
        final String ARR_DATE               = "NA";
        final String ADULTS                 = "0";
        final String CHILDREN               = "9";
        final String INFANT_LAP             = "0";
        final String INFANT_SEAT            = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT             = "Through";
        final String FARE_TYPE              = "Standard";
        final String UPGRADE_VALUE          = "BundleIt";

        //Seat Page Constant
        final String DEP_SEAT               = "Standard|Standard|Standard|Standard|Standard|Standard|Standard|Standard|Standard";

        //Options page Constant Values
        final String OPTIONS_VALUE          = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String CARD_TYPE              = "DiscoverCard1";
        final String TRAVEL_GUARD           = "NotRequired";

        //open browser and redirect tot the application
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //get the DOB box available
        WebElement travellingChildSection = pageObjectManager.getHomePage().getChildTravelerPanel().get(0);

        //get adult mandatory details
        PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("FreeSpiritMasterCardUMNR");

        //convert date in required format
        travellingChildSection.findElement(By.tagName("input")).sendKeys(passengerInfoData.dob);

        travellingChildSection.click();

        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();
        pageMethodManager.getHomePageMethods().selectUMNRPopup();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);//Bare Fare

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().verifySelectedBaseFarePassengerInfo(UPGRADE_VALUE);//Bare Fare
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().verifySelectedBaseFareBagsWithVAT(UPGRADE_VALUE);//Bare Fare
        pageMethodManager.getBagsPageMethods().continueWithOutChangesBag();

        //Seats page methods
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEAT);
        pageMethodManager.getSeatsPageMethods().verifySelectedBaseFareSeats(UPGRADE_VALUE,DEP_SEAT,"NA");//Bare Fare
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //Options page methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getOptionsPageMethods().verifySelectedBaseFareOptions(UPGRADE_VALUE);//Bare Fare


        ValidationUtil.validateTestStep("Verify check-in options are disabled on because of UMNR on Options Page",
                pageObjectManager.getOptionsPage().getCheckInOptionCardPanel().getAttribute("class"),"disabled");
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment page methods
        pageMethodManager.getPaymentPageMethods().verifySelectedBaseFarePayment(UPGRADE_VALUE);//Bare Fare
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCVVDetailsModifyPath(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();


        /******************************************************************************
         *************************Validation on Confirmation Page**********************
         ******************************************************************************/
        //Confirmation Page Constant Values
        final String BOOKING_STATUS     = "Confirmed";

        WaitUtil.untilPageLoadComplete(getDriver());

        //Confirmation Code
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(), BOOKING_STATUS);


        pageMethodManager.getConfirmationPageMethods().verifySelectedBaseFareConfirmation(UPGRADE_VALUE);//Bare Fare
    }

}

