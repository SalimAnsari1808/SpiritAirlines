package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.openqa.selenium.Keys;
import org.testng.annotations.*;
import org.testng.annotations.Optional;

//**********************************************************************************************
//Test Case ID: TC91451
//Description: Search Widget_BP_CP_Multi_ Valid Promo Code for Percent off
// Search Widget_CP_BP_Flight+Hotel Valid Promo for Percent off Code
//Created By : Sunny Sok
//Created On : 05-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 08-Aug-2019
//**********************************************************************************************

public class TC91451 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "MultiCity" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "PromoCode" , "Guest" , "NonStop" , "BookIt" , "NoBags" , "NoSeats" , "CheckInOptions" , "MasterCard","HomeUI","PaymentUI","ConfirmationUI"})
    public void Search_Widget_BP_CP_Multi_Valid_Promo_Code_for_Percent_off(@Optional("NA") String platform) {

        /******************************************************************************
         *******************************Navigate to Home Page**************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91451 under  REGRESSION-CRITICAL Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "MultiCity";
        final String DEP_AIRPORTS       = "AllLocation|AllLocation";
        final String DEP_AIRPORT_CODE   = "LAS|FLL";
        final String ARR_AIRPORTS       = "AllLocation|AllLocation";
        final String ARR_AIRPORT_CODE   = "FLL|LAS";
        final String DEP_DATE           = "5|8";
        final String ADULTS             = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String PROMOCODE 			= "10PCT";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String RET_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";


        //Options Page constant values
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //payment page constant value
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirportsMultiCity(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDatesMultiCity(DEP_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);

        //Constants for validation
        final String PROMO_TEXT                 = "You'll find our Promo Codes in our promotional emails.";
        final String SPECIAL_CHARACTER          = "!@#$%" + Keys.TAB;
        final String SPECIAL_CHARACTER_ERROR    = "Only letters and numbers are allowed";
        final String MORE_THAN_EIGHT_CHARACTER  = "123456789123";
        String FLIGHT_AVAILABILITY_URL          = "/book/flights";
        String PAYMENT_URL                      = "/book/payment";
        String CONFIRMATION_URL                 = "/book/confirmation";

        //Hover over the blue question mark tool tip directly to the right of Add a Promo Code link
        pageObjectManager.getHomePage().getOptionalServicesPopoverLink().click();
        ValidationUtil.validateTestStep(" Verify text will display with text stating You’ll find our Promo Codes in our promotional emails.",
                pageObjectManager.getHomePage().getPopOverContainerText().getText().trim(), PROMO_TEXT);

        ValidationUtil.validateTestStep("Verify Sign up now link is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getPromoSignUpNowLink()));

        //Click on " Add a Promo Code"  link
        pageObjectManager.getHomePage().getPopUpOverCloseImage().click();
        pageObjectManager.getHomePage().getPromoCodeLink().click();

        //Input Invalid PromoCode
        pageObjectManager.getHomePage().getPromoCodeTextBox().sendKeys(SPECIAL_CHARACTER);

        //click on search button
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Promo code text input is alphanumeric only.
        ValidationUtil.validateTestStep("Verify Promo code text input accept only alphanumeric character on Home Page",
                pageObjectManager.getHomePage().getErrorMessageText().getText(), SPECIAL_CHARACTER_ERROR);

        //clear promo code box
        TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getHomePage().getPromoCodeTextBox());

        /**********************
         ****This is a Bug*****
         **********************/
//        //Input Invalid PromoCode
//        pageObjectManager.getHomePage().getPromoCodeTextBox().sendKeys(MORE_THAN_EIGHT_CHARACTER);
//
//        //Promo code can have a maximum 8 characters
//         ValidationUtil.validateTestStep("Verify Promo code can have a maximum 8 characters on Home Page", pageObjectManager.getHomePage().getErrorMessageText().getText().toString().length()==8);
//
//        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Enter a valid Promo Code and select SEARCH VACATION.

        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getHomePage().getPromoCodeTextBox().sendKeys(PROMOCODE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seat Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //******************************************
        //*********Payment Page Validations ********
        //******************************************
        //declare constant used in validation
        final String PROMO_APPLIED     =   "PROMO APPLIED";

        //declare variable used in validation
        double finalFlightPrice;
        double withoutPromoPrice;
        double withPromoPrice;

        //open total due breakdown
        pageObjectManager.getPaymentPage().getTotalDueText().click();

        //wait for 1 sec
        WaitUtil.untilTimeCompleted(1000);

        //open flight breakdown
        pageObjectManager.getPaymentPage().getTotalDueFlightText().click();

        //wait for 1 sec
        WaitUtil.untilTimeCompleted(1000);

        //promo code for departureThis is missing in multicity, TFS is raised)
//        ValidationUtil.validateTestStep("The promo code has been applied to the booking for the Departure Flight on Payment Page",
//                pageObjectManager.getPaymentPage().getDepOnlyFlightChargeText().get(0).getText().toUpperCase(),PROMO_APPLIED);

        //flight price without promo code
        withoutPromoPrice = Double.parseDouble(pageObjectManager.getPaymentPage().getDepOnlyFlightChargeText().get(1).getText().split(" ")[0].replace("$",""));

        //flight price with promo code
        withPromoPrice = Double.parseDouble(pageObjectManager.getPaymentPage().getDepOnlyFlightChargeText().get(1).getText().split(" ")[1].replace("$",""));

        //promo code flight price
        ValidationUtil.validateTestStep("Departure Flight Price is reduced after adding Promo Code on Payment Page",
                withoutPromoPrice - withPromoPrice > 0);

//        //promo code for return(This is missing in multicity, TFS is raised)
//        ValidationUtil.validateTestStep("The promo code has been applied to the booking for the Return Flight on Payment Page",
//                pageObjectManager.getPaymentPage().getRetOnlyFlightChargeText().get(0).getText().toUpperCase(),PROMO_APPLIED);

        //flight price without promo code
        withoutPromoPrice = Double.parseDouble(pageObjectManager.getPaymentPage().getRetOnlyFlightChargeText().get(1).getText().split(" ")[0].replace("$",""));

        //flight price with promo code
        withPromoPrice = Double.parseDouble(pageObjectManager.getPaymentPage().getRetOnlyFlightChargeText().get(1).getText().split(" ")[1].replace("$",""));

        //promo code flight price
        ValidationUtil.validateTestStep("Return Flight Price is reduced after adding Promo Code on Payment Page",
                withoutPromoPrice - withPromoPrice > 0);

        //Payment page
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //Confirmation page closing ROKT Popup
        WaitUtil.untilPageLoadComplete(getDriver());

        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //verify user lands on payment page
        ValidationUtil.validateTestStep("Validating Confirmation Page URL",
                getDriver().getCurrentUrl(), CONFIRMATION_URL);


        //open total due breakdown
        pageObjectManager.getConfirmationPage().getTotalPaidPriceText().click();

        //wait for 1 sec
        WaitUtil.untilTimeCompleted(1000);

        //open flight breakdown
        pageObjectManager.getPaymentPage().getTotalDueFlightText().click();

        //wait for 1 sec
        WaitUtil.untilTimeCompleted(1000);

//        //promo code for departure(This is missing in multicity, TFS is raised)
//        ValidationUtil.validateTestStep("The promo code has been applied to the booking for the Departure Flight on Confirmation Page",
//                pageObjectManager.getPaymentPage().getDepOnlyFlightChargeText().get(0).getText().toUpperCase(),PROMO_APPLIED);

        //flight price without promo code
        withoutPromoPrice = Double.parseDouble(pageObjectManager.getPaymentPage().getDepOnlyFlightChargeText().get(1).getText().split(" ")[0].replace("$",""));

        //flight price with promo code
        withPromoPrice = Double.parseDouble(pageObjectManager.getPaymentPage().getDepOnlyFlightChargeText().get(1).getText().split(" ")[1].replace("$",""));

        //promo code flight price
        ValidationUtil.validateTestStep("Departure Flight Price is reduced after adding Promo Code on Confirmation Page",
                withoutPromoPrice - withPromoPrice > 0);

//        //promo code for return(This is missing in multicity, TFS is raised)
//        ValidationUtil.validateTestStep("The promo code has been applied to the booking for the Return Flight on Confirmation Page",
//                pageObjectManager.getPaymentPage().getRetOnlyFlightChargeText().get(0).getText().toUpperCase(),PROMO_APPLIED);

        //flight price without promo code
        withoutPromoPrice = Double.parseDouble(pageObjectManager.getPaymentPage().getRetOnlyFlightChargeText().get(1).getText().split(" ")[0].replace("$",""));

        //flight price with promo code
        withPromoPrice = Double.parseDouble(pageObjectManager.getPaymentPage().getRetOnlyFlightChargeText().get(1).getText().split(" ")[1].replace("$",""));

        //promo code flight price
        ValidationUtil.validateTestStep("Return Flight Price is reduced after adding Promo Code on Confirmation Page",
                withoutPromoPrice - withPromoPrice > 0);
    }
}