package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.PassengerInfoData;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

// **********************************************************************************************
// Test Case ID: TC91386
// Test Name   : CP_BP_Travel_Guard_Vacation
// Description : verify Travel Guard Links and verbiages on payment page and on TG recommended pop-up
// Created By  : Kartik Chauhan
// Created On  : 09-May-2019
// Reviewed By : Salim Ansari
// Reviewed On : 09-May-2019
// **********************************************************************************************

public class TC91386 extends BaseClass {
    @Parameters ({"platform"})
    @Test (groups = {"BookPath" , "FlightCar" , "DomesticDomestic" , "Outside21Days" , "Adult" , "NonStop" , "BookIt" ,
                    "CarryOn" , "NoSeats" ,"ShortCutBoarding", "CheckInOptions" , "Discover" , "PaymentUI"})
    public void CP_BP_Travel_Guard_Vacation(@Optional("NA")String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91386 under Smoke Suite on " + platform + " Browser", true);
        }

        //****************************Navigate to Passenger Info Page*****************
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
        final String ADULT				= "1";
        final String CHILD				= "0";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT		= "0";
        final String DRIVER_AGE 		= "25+";

        //Flight Availability Page Constant Values
        final String FARE_TYPE			= "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Bags Page constant values
        final String DEP_BAGS 			= "Carry_1|Checked_0";

        //Option page constant
        final String OPTION_VALUE		= "CheckInOption:MobileFree";

        //payment page constant value
        final String CARD_TYPE          = "DiscoverCard1";

//Step1-//STEP--3
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
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //wait till page is load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //Click on Book Car Button
//        pageObjectManager.getCarPage().getBookCarButton().get(0).click();
        pageObjectManager.getCarPage().getCarsPageBookButton().get(0).click();

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

        //get adult mandatory details
        PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("Passenger1");

        //select Driver Dropdown
        pageObjectManager.getPassengerInfoPage().getPrimaryDriverDropDown().click();

        //Select Driver name
        pageObjectManager.getPassengerInfoPage().getPrimaryDriverDropDown().sendKeys(passengerInfoData.firstName);

        //wait for 2 second
        WaitUtil.untilTimeCompleted(2000);

        //click with continue button
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

        //Seat Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Option Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
        WaitUtil.untilPageLoadComplete(getDriver());

        //select no on the travel guard modal
        //  pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GAURD);
        WaitUtil.untilPageLoadComplete(getDriver());

        //Payment Page Methods

        //boarding pass page
        final String TRAVEL_MORE    = "TRAVEL MORE. WORRY LESS. GET INSURANCE FOR ONLY";
        final String PER_PERSON     = "FOR ALL TRAVELERS.*";
//        final String PER_PERSON     = "PER PERSON.*";

//Step--4
        //verify Travel guard header is displaying on TG section
        ValidationUtil.validateTestStep("Travel More worry less.. Travel guard Section header is displaying on TG section",
                pageObjectManager.getPaymentPage().getTravelMoreWorryLessText().getText(),TRAVEL_MORE);

        //verify Travel guard header is displaying on TG section
        ValidationUtil.validateTestStep("Per person.. Travel guard Section header is displaying on TG section",
                pageObjectManager.getPaymentPage().getTravelMoreWorryLessText().getText(),PER_PERSON);
//Step--5 and Step-6
//        ///////////validation that the travel Guard text is displaying
//        String[] textBeingValidated =
//                {
//                        "100% Trip Cost Cancellation",
//                        "125% Trip Cost Trip Interruption",
//                        "$750 Missed Connection",
//                        "$500 Trip Delay",
//                        "$1000 Baggage & Personal Effects"
//                };
//
//        for(String element : textBeingValidated)
//        {
//            try
//            {
//                WebElement tg_Text = getDriver().findElement(By.xpath("//div[@class='insurance-container']//p[contains(text(),'" + element + "')]"));
//
//                System.out.println(element);
//                ValidationUtil.validateTestStep("Travel Guard text \"" + element + "\" is supressed for New York Resident: pass" , true); //text should dislpay: pass
//            }
//            catch (Exception e)
//            {
//                ValidationUtil.validateTestStep("Travel Guard text \" " + element + "\" is supressed for New York Resident: pass", false);
//            }
//        }

        //Validating Travel Insurance information
        List<String> textBeingValidated = new ArrayList<String>();
        textBeingValidated.add("100% Trip Cost Cancellation");
        textBeingValidated.add("125% Trip Cost Trip Interruption");
        textBeingValidated.add("$500 Missed Connection");
        textBeingValidated.add("$500 Trip Delay");
        textBeingValidated.add("$500 Baggage & Personal Effects");

        //Verify All section under TG section
        WaitUtil.untilTimeCompleted(1200);
        pageMethodManager.getPaymentPageMethods().travelGuardVerbiagesAndLink(textBeingValidated);

//Step--7 & STEP--8

        //verify YES-label verbiage under TG section
        final String ADD_INSURANCE          = "Yes, add insurance for";
        final String COVERING_ALL_CUSTOMERS = "covering all guests on this reservation.";
        final String READ_UNDERSTAND        = "I have read and understand the ";
        final String AGREE_TERMS            = " and agree to the terms of conditions.";

        ValidationUtil.validateTestStep("Yes, add insurance.. verbiage is displaying in TG section",
                pageObjectManager.getPaymentPage().getYesTravelGuardLabel().getText(),ADD_INSURANCE);

        ValidationUtil.validateTestStep("covering all customers on this reservation... verbiage is displaying in TG section",
                pageObjectManager.getPaymentPage().getYesTravelGuardLabel().getText(),COVERING_ALL_CUSTOMERS);

        ValidationUtil.validateTestStep("I have read and understand the .. verbiage is displaying in TG section",
                pageObjectManager.getPaymentPage().getHavereadAndUnderstandText().getText(),READ_UNDERSTAND);

        ValidationUtil.validateTestStep("Agree to Terms and Condition .. verbiage is displaying in TG section",
                pageObjectManager.getPaymentPage().getHavereadAndUnderstandText().getText(),AGREE_TERMS);

        //verify No-label verbiage under TG section
        final String NO_INSURANCE                   = "No, I don't want to insure my";
        final String RESPONSIBLE_FOR_NONREFUNDABLE  = "trip and understand that I may be responsible for non-refundable charges.";
        final String COVERAGE_OFFERED               = "Coverage offered by Travel Guard Group, Inc. and limitations will apply. Full Disclaimer";

        ValidationUtil.validateTestStep("No, I don't want to insure my.. verbiage is diplaying in TG section",
                pageObjectManager.getPaymentPage().getNoTravelGuardLabel().getText(),NO_INSURANCE);

        ValidationUtil.validateTestStep("trip and understand that I am responsible.. verbiage is diplaying in TG section",
                pageObjectManager.getPaymentPage().getNoTravelGuardLabel().getText(),RESPONSIBLE_FOR_NONREFUNDABLE);

        ValidationUtil.validateTestStep("Coverage offered by Travel guard.. verbiage is diplaying in TG section",
                pageObjectManager.getPaymentPage().getCoverageOfferedText().getText(),COVERAGE_OFFERED);
//STEP--9
        //Click on Insurance Policy Link
        pageObjectManager.getPaymentPage().getInsurancePolicyLink().click();

        //Wait till page load complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //Put wait for 2 second
        WaitUtil.untilTimeCompleted(2000);

        //move focus from one window to another
        TestUtil.switchToWindow(getDriver(),1);

        //Constant value
        final String INSURANCE_POLICY = "webservices.travelguard.com/Product";

        //Verify User landed on TG Insurance Website
        ValidationUtil.validateTestStep("User verify Navigated to Travel guard Insurance website",
                getDriver().getCurrentUrl(),INSURANCE_POLICY);

        //Close second window
        getDriver().close();

        //make focus on first window
        TestUtil.switchToWindow(getDriver(),0);
//*********************************************************************************
//STEP--10
        //Click on Travel guard Group link
        pageObjectManager.getPaymentPage().getTravelGuardGroupLink().click();

        //Wait till page load complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //Put wait for 2 second
        WaitUtil.untilTimeCompleted(2000);

        //move focus from one window to another
        TestUtil.switchToWindow(getDriver(),1);

        //Constant value
        final String TRAVEL_GUARD_GROUP = "www.travelguard.com/spirit/";

        //Verify User landed on TG Group Website
        ValidationUtil.validateTestStep("User verify Navigated to Travel guard Group website",
                getDriver().getCurrentUrl(),TRAVEL_GUARD_GROUP);

        //Close second window
        getDriver().close();

        //make focus on first window
        TestUtil.switchToWindow(getDriver(),0);
//*********************************************************************************

        //Click on Travel guard Group link
        pageObjectManager.getPaymentPage().getTravelGuardDisclaimerLink().click();

        //Wait till page load complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //Put wait for 2 second
        WaitUtil.untilTimeCompleted(2000);

        //move focus from one window to another
        TestUtil.switchToWindow(getDriver(),1);

        //Constant value
        final String TRAVEL_GUARD_DISCLAIMER = "www.travelguard.com/legal/disclaimer";

        //Verify User landed on TG Group Website

        ValidationUtil.validateTestStep("User verify Navigated to Travel guard disclaimer website",
                getDriver().getCurrentUrl(),TRAVEL_GUARD_DISCLAIMER);

        //Close second window
        getDriver().close();

        //make focus on first window
        TestUtil.switchToWindow(getDriver(),0);

//STEP--11

        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
//STEP--12

        //declare constant used in validation
        final String TG_RECOMMEND   = "Travel Guard Recommends Travel Insurance";
        final String DONT_IGNORE    = "Don't Ignore the Unexpected";

        pageObjectManager.getPaymentPage().getTermsAndConditionsLabel().click();
        pageObjectManager.getPaymentPage().getBookTripButton().click();
        pageMethodManager.getPaymentPageMethods().travelGuardRecommendedPopUp();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup("NotRequired");

        //***********************Validation to Payment Page****************************
        //declare constant used in validation
        final String BOOKING_STATUS     = "Confirmed";
        final String CONFIRMATION_URL   = "book/confirmation";

        try {
            //Confirmation Page Methods
            //close ROKT pop-up
            pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

            //verify booking is confirmed
            ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page", getDriver().getCurrentUrl(), CONFIRMATION_URL);

            //verify booking is confirmed
            ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page", pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(), BOOKING_STATUS);

            //Cancelling Car Reservation
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