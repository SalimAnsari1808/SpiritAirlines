package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC91445
//Description: Search Widget_BP_CP_OW_ Valid Promo Code for Percent off
//Created By:  Kartik Chauhan
//Created On:  13-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 13-Aug-2019
//**********************************************************************************************
//TODO: 26044 - CP: BP: Payment Page PMT: PROMO APPLIED verbiage is missing from the caret drop down
//TODO: [IN:24085] CP: BP: Payment Page PMT: PROMO APPLIED verbiage is missing from the caret drop down
public class TC91445 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"ActiveBug" , "BookPath" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "PromoCode" , "Guest" , "NonStop" , "BookIt" , "NoBags" , "NoSeats" ,"CheckInOptions", "MasterCard","HomeUI","PaymentUI","ConfirmationUI"})
    public void Search_Widget_BP_CP_OW_Valid_Promo_Code_for_Percent_off(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91445 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			        = "English";
        final String JOURNEY_TYPE 	  	        = "Flight";
        final String TRIP_TYPE 			        = "OneWay";
        final String DEP_DATE 		    	    = "4";
        final String ARR_DATE 		    	    = "7";
        final String DEP_AIRPORTS               = "AllLocation";
        final String DEP_AIRPORT_CODE           = "FLL";
        final String ARR_AIRPORTS               = "AllLocation";
        final String ARR_AIRPORT_CODE           = "LAX";
        final String ADULT   		       	    = "1";
        final String CHILDREN 		            = "0";
        final String INFANT_LAP 	    	    = "0";
        final String INFANT_SEAT 		        = "0";
        final String TOOLTIP_TEXT               = "You'll find our Promo Codes in our promotional emails.\n" + "Sign Up Now";
        final String SPECIAL_CHARACTER          = "!@#$%";
        final String SPECIAL_CHARACTER_ERROR    = "Only letters and numbers are allowed";
        final String MORE_THAN_EIGHT_CHARACTER  = "123456789123";
        final String VALID_PROMO_CODE           = "10PCT";

        //Flight Availability Page Constant Values
        final String FA_URL                     = "book/flights";
        final String DEP_FLIGHT                 = "9DFC";
        final String FARE_TYPE                  = "Standard";
        final String UPGRADE_VALUE              = "BookIt";

        //Options Page Constant Values
        final String OPTIONS_VALUE	            = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String CARD_TYPE                  = "MasterCard";
        final String TRAVEL_GUARD               = "NotRequired";

        //Confirmation Page Constant Values
        final String BOOKING_STATUS             = "Confirmed";
        final String CONFIRMATION_URL           = "book/confirmation";

        //open browser
        openBrowser(platform);

        /****************************************** Home Page Methods ************************************/
//STEP--1
        //launch application
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
//STEP--2
        //Select Departure and arrival airport
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);

//STEP--3
        //Select 1 Adult
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILDREN, INFANT_SEAT, INFANT_LAP);

//STEP--4
        //Select Departure and arrival Date
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);

//STEP--5
        //cl;ick on Promo code link
        pageObjectManager.getHomePage().getPromoCodeLink().click();

        //Wait for 2 sec
        WaitUtil.untilTimeCompleted(1200);

        //Validating Promo Code field box should populate in that
        ValidationUtil.validateTestStep("Validating Promo Code field box should populate in that space, replacing the tool tip",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getPromoCodeTextBox()));

        //Promo code text input is alphanumeric only.
        pageObjectManager.getHomePage().getPromoCodeTextBox().sendKeys(SPECIAL_CHARACTER);

        //click on search button
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Promo code text input is alphanumeric only.
        ValidationUtil.validateTestStep("Verify Promo code text input accept only alphanumeric character on Home Page",
                pageObjectManager.getHomePage().getErrorMessageText().getText(),SPECIAL_CHARACTER_ERROR);

        //clear promo code box
        TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getHomePage().getPromoCodeTextBox());

        //Input Invalid PromoCode
        pageObjectManager.getHomePage().getPromoCodeTextBox().sendKeys(MORE_THAN_EIGHT_CHARACTER);

        //TODO: Bug 21419: GAP: CP: BP: Search Widget SW: Promo Codes are only 8 Characters long should the Promo Text box allow infinite Characters?
        //Promo code can have a maximum 8 characters
         ValidationUtil.validateTestStep("Verify Promo code can have a maximum 8 characters on Home Page",
         pageObjectManager.getHomePage().getErrorMessageText().getText().toString().length()==8);

        pageMethodManager.getHomePageMethods().clickSearchButton();

        //clear promo code box
        TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getHomePage().getPromoCodeTextBox());

//Step--6
        //wait for 3 sec
        WaitUtil.untilTimeCompleted(3000);
        pageObjectManager.getHomePage().getPromoCodeTextBox().sendKeys(VALID_PROMO_CODE);
//STEP--7
        pageMethodManager.getHomePageMethods().clickSearchButton();

        /****************************************** Flight Availability Page Methods ************************************/
        //wait till page load completely
        WaitUtil.untilPageLoadComplete(getDriver());

        //Verify Flight Availability Page load
        ValidationUtil.validateTestStep("Verify Flight Availability Page load",
                getDriver().getCurrentUrl().contains(FA_URL));


//STEP--8,9 & 10
        //****************************************************************************
        //* *************Flight Availability Page Methods*****************************
        //****************************************************************************/
        pageMethodManager.getFlightAvailabilityMethods().selectFlightFareType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//STEP--11 & 12
        //****************************************************************************
        //*****************Passenger Information Page Methods************************
        //****************************************************************************/
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
//STEP--13 & 14
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//STEP--15
        //****************************************************************************
        //*************************Seats Page Methods*********************************
        //****************************************************************************
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
//STEP--16 ,17 & 18
        //****************************************************************************
        //*************************Options Page Methods*********************************
        //****************************************************************************/
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
//STEP--19
        //****************************************************************************
        //*************************Payment Page Methods*********************************
        //****************************************************************************/
        //declare constant used in validation
        final String PROMO_TEXT     =   "PROMO APPLIED";

        //declare variable used in validation
        double finalFlightPrice;
        double withoutPromoPrice;
        double withPromoPrice;

        //click on Total Due price
        pageObjectManager.getPaymentPage().getTotalDuePriceText().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);
//STEP--20
        //Click on Total Flight Due
        pageObjectManager.getPaymentPage().getTotalDueFlightText().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //scroll down to display taxes on screen
        JSExecuteUtil.scrollDown(getDriver(),"100");

//STEP--21
        //promo code for departure
        //TODO: [IN:24085] CP: BP: Payment Page PMT: PROMO APPLIED verbiage is missing from the caret drop down
        ValidationUtil.validateTestStep("The promo code has been applied to the booking for the Departure Flight on Payment Page",
                pageObjectManager.getPaymentPage().getDepOnlyFlightChargeText().get(0).getText().toUpperCase(),PROMO_TEXT);

        //flight price without promo code
        withoutPromoPrice = Double.parseDouble(pageObjectManager.getPaymentPage().getDepOnlyFlightChargeText().get(1).getText().split(" ")[0].replace("$",""));

        //flight price with promo code
        withPromoPrice = Double.parseDouble(pageObjectManager.getPaymentPage().getDepOnlyFlightChargeText().get(1).getText().split(" ")[1].replace("$",""));

        //promo code flight price
        ValidationUtil.validateTestStep("Departure Flight Price is reduced after adding Promo Code on Payment Page",
                withoutPromoPrice - withPromoPrice > 0);

//STEP--22
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //*****************************************************************************
        //*****************Confirmation Page Method************************************
        //*****************************************************************************
//STEP--23 & 24
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify complete booking completed and  guest reached the Confirmation Page",
                getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);

//STEP--25
        //Click on Total BreakDown
        pageObjectManager.getConfirmationPage().getTotalPaidPriceText().click();

        //wait for 3 sec
        WaitUtil.untilTimeCompleted(2000);

        //Click on Total BreakDown
        pageObjectManager.getConfirmationPage().getFlightVerbiageLabel().click();

        //wait for 3 sec
        WaitUtil.untilTimeCompleted(2000);

        //scroll down to display taxes on screen
        JSExecuteUtil.scrollDown(getDriver(),"100");
//STEP--26
        //promo code for departure
        ValidationUtil.validateTestStep("The promo code has been applied to the booking for the Departure Flight on Confirmation Page",
                pageObjectManager.getPaymentPage().getDepOnlyFlightChargeText().get(0).getText().toUpperCase(),PROMO_TEXT);

        //flight price without promo code
        withoutPromoPrice = Double.parseDouble(pageObjectManager.getPaymentPage().getDepOnlyFlightChargeText().get(1).getText().split(" ")[0].replace("$",""));

        //flight price with promo code
        withPromoPrice = Double.parseDouble(pageObjectManager.getPaymentPage().getDepOnlyFlightChargeText().get(1).getText().split(" ")[1].replace("$",""));

        //promo code flight price
        ValidationUtil.validateTestStep("Departure Flight Price is reduced after adding Promo Code on Confirmation Page",
                withoutPromoPrice - withPromoPrice > 0);
    }
}