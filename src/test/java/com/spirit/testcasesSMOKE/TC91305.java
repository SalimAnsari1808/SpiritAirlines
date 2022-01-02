package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

// **********************************************************************************************
// Test Case ID: TC91305
// Test Name   : CP_BP_Payment Page_Travel Guard_Vacation
// Description : verify all Travel Guard prices for vacation on Payment page
// Created By  : Kartik Chauhan
// Created On  : 10-May-2019
// Reviewed By : Salim Ansari
// Reviewed On : 15-May-2019
// **********************************************************************************************

public class TC91305 extends BaseClass {
    @Parameters ({"platform"})
    @Test(groups = {"Guest","BookPath","FlightCar","DomesticDomestic","Outside21Days","Adult","BookIt",
                "NoBags","NoSeats","CheckInOptions","MasterCard","PaymentUI","TravelInsuranceBlock","ActiveBug"})
    public void CP_BP_Payment_Page_Travel_Guard_Vacation(@Optional("NA")String platform) {
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91305 under SMOKE Suite on " + platform + " Browser", true);
        }

        //**************************Navigate to Passenger Info Page**************************************/
        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Vacation";
        final String TRIP_TYPE 			= "Flight+Car";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LAS";
        final String DEP_DATE 			= "90";
        final String ARR_DATE 			= "93";
        final String ADULTS				= "1";
        final String CHILDS				= "0";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT		= "0";
        final String DRIVER_AGE 		= "25+";

        //Flight Availability Page Constant Values
        final String UPGRADE_VALUE      = "BookIt";

        //Option page constant
        final String OPTION_VALUE		= "CheckInOption:MobileFree";

        //payment page constant value
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_GAURD       = "required";

//Step1-
        //open browser
        openBrowser( platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //wait till page is load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //Click on Book Car Button
        pageObjectManager.getCarPage().getCarsPageBookButton().get(0).click();
//        pageObjectManager.getCarPage().getBookCarButton().get(0).click();

        //wait till page is load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //verify if activity will display, continue without it
        if(getDriver().getCurrentUrl().contains("activi")) {
            //Select continue without Activity
            pageObjectManager.getActivityPage().getContinueWithoutActivityButton().click();
        }

        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //wait till page is load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

        //select Driver Dropdown
        pageObjectManager.getPassengerInfoPage().getPrimaryDriverDropDown().click();
        Select driverInfoDropDown = new Select(pageObjectManager.getPassengerInfoPage().getPrimaryDriverDropDown());
        List<WebElement> allOptionList =driverInfoDropDown.getOptions();
        ArrayList<String> allOptionString = new ArrayList<String>();
        for (WebElement element:allOptionList) {
            allOptionString.add(element.getText().trim());
        }
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPassengerInfoPage().getPrimaryDriverDropDown(),allOptionString.get(1));

        WaitUtil.untilTimeCompleted(1000);

        //click with continue button
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seat Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Option Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
        WaitUtil.untilPageLoadComplete(getDriver());

//STEP--2,3,4

        List<String> textBeingValidated = new ArrayList<String>();
        textBeingValidated.add("100% Trip Cost Cancellation");
        textBeingValidated.add("125% Trip Cost Trip Interruption");
        textBeingValidated.add("$500 Missed Connection");
        textBeingValidated.add("$500 Trip Delay");
        textBeingValidated.add("$500 Baggage & Personal Effects");

        //method to check all the verbiages and link under TG section on payment page
        pageMethodManager.getPaymentPageMethods().travelGuardVerbiagesAndLink(textBeingValidated);

//STEP--5
        //Select TG
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GAURD);
//STEP--6
        //complete payment
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

//STEP--7
        //*********************Validation to Confirmation Page**************************/
        //declare constant used in validation
        final String BOOKING_STATUS     = "Confirmed";
        final String CONFIRMATION_URL   = "book/confirmation";

        try {
        //Confirmation Page Methods
        //close ROKT pop-up
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page",
                getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);

        //Cancelling Car reservation
            pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
        }catch (AssertionError fail){
            pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
            pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
            ValidationUtil.validateTestStep("Test Case failed after Payment: " + fail.getMessage() , false);
        }catch (Exception fail){
            pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
            pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
            ValidationUtil.validateTestStep("Test Case failed after Payment: " + fail.getMessage() , false);
        }

    }
}