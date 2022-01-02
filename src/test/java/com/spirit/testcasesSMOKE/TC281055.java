package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.PassengerInfoData;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC281055
//Test Name: Task 24017: 31516 016. E2E_Guest_OW Dom-INT 2 ADT_Direct Flight_1 Military 1 Standard_Military bags
//           only_No Seats_No Extras CI Web_Visa
//Description:
//Created By : Gabriela
//Created On : 20-MAY-2019
//Reviewed By: Salim Ansari
//Reviewed On: 21-MAY-2019
//**********************************************************************************************

public class TC281055 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "Military" , "NonStop" , "BookIt" ,
                    "MandatoryFields" , "CarryOn" , "CheckedBags" , "BagsUI" , "NoSeat" ,"CheckInOptions" , "Visa","PaymentUI"})
    public void E2E_Guest_OW_Dom_INT_2_ADT_Direct_Flight_1_Military_1_Standard_Military_bags_only_No_Seats_No_Extras_CI_Web_Visa(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC281055 under SMOKE Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			    = "English";
        final String JOURNEY_TYPE 		    = "Flight";
        final String TRIP_TYPE 			    = "OneWay";
        final String DEP_AIRPORTS 		    = "AllLocation";
        final String DEP_AIRPORT_CODE 	    = "LAS";
        final String ARR_AIRPORTS 		    = "AllLocation";
        final String ARR_AIRPORT_CODE 	    = "DFW";
        final String DEP_DATE 			    = "1";
        final String ARR_DATE 			    = "NA";
        final String ADULTS 			    = "2";
        final String CHILD  			    = "0";
        final String INFANT_LAP 		    = "0";
        final String INFANT_SEAT		    = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		    = "Nonstop";
        final String FARE_TYPE 			    = "Standard";
        final String UPGRADE_VALUE 		    = "BookIt";

        //Bags Page Constant
        final String BAGS_MILITARY		    = "Carry_1|Checked_2";

        //Options Page Constant Value
        final String OPTION_VALUE 	        = "CheckInOption:DecideLater";

        //Payment Page Constant Values
        final String PAYMENT_URL            = "book/payment";
        final String CARD_TYPE              = "VisaCard";
        final String TRAVEL_GUARD           = "NotRequired";

        //Confirmation Page Constant Values
        final String BOOKING_STATUS         = "Confirmed";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Customer Info Page Method
        PassengerInfoData PassengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("MilitaryPax1");
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPassengerInfoPage().getAdultTitleListDropDown().get(0),PassengerInfoData.title);
        pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0).sendKeys(PassengerInfoData.firstName);
        pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(0).sendKeys(PassengerInfoData.lastName);
        pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(0).sendKeys(PassengerInfoData.dob);
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        WaitUtil.untilTimeCompleted(1300);
        pageObjectManager.getPassengerInfoPage().getActiveMilitaryPersonnelListCheckBox().get(1).click();

        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());

        //Verifying city pair is correct
        ValidationUtil.validateTestStep("Verifying departure city is correct",
                pageObjectManager.getBagsPage().getDepartureCityText().getText(),DEP_AIRPORT_CODE);

        ValidationUtil.validateTestStep("Verifying arrival city is correct",
                pageObjectManager.getBagsPage().getDepartureCityText().getText(),ARR_AIRPORT_CODE);

        ValidationUtil.validateTestStep("Validating the carry on for the non military passenger is available",
                pageObjectManager.getBagsPage().getDepartingCarryOnPlusButton().get(1).isEnabled());

        ValidationUtil.validateTestStep("Validating the checked bag for the non military passenger is available",
                pageObjectManager.getBagsPage().getDepartingCheckedBagPlusButton().get(1).isEnabled());

        pageMethodManager.getBagsPageMethods().selectDepartingBags(BAGS_MILITARY);

        WaitUtil.untilTimeCompleted(3000);
        pageObjectManager.getBagsPage().getContinueWithStandardBagsContainerContinueButton().click();

        //Seats Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());

        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options page Methods
        WaitUtil.untilPageLoadComplete(getDriver());

        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Validating Payment Page URL",
                getDriver().getCurrentUrl(),PAYMENT_URL);

        pageMethodManager.getPaymentPageMethods().verifyMilitaryPassengerLoginDetails();

        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //Confirmation Page Code
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
    }
}