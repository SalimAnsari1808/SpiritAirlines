package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.*;
import com.spirit.enums.Context;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC91456
//Description: Search Widget_CP_BP_Flight+Hotel Valid Promo for Percent off Code
// Search Widget_BP_CP_Multi_ Valid Promo Code for Percent off
//Created By : Sunny Sok
//Created On : 05-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 07-Aug-2019
//**********************************************************************************************

public class TC91456 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "FlightHotel" , "DomesticDomestic" , "Outside21Days" , "Adult" , "PromoCode" , "Guest" , "BookIt" , "CarryOn" , "Premium","CheckInOptions","HomeUI"})
    public void Search_Widget_CP_BP_Flight_Hotel_Valid_Promo_for_Percent_off_Code(@Optional("NA") String platform) {
        /******************************************************************************
         *******************************Navigate to Home Page**************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91456 under  REGRESSION-CRITICAL Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Vacation";
        final String TRIP_TYPE			= "flight+hotel";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LAS";
        final String DEP_DATE 			= "31";
        final String ARR_DATE 			= "35";
        final String ADULTS 			= "1";
        final String CHILDS 			= "0";
        final String INFANT_LAP 		= "0";
        final String INFANT_SEAT 		= "0";
        final String HOTELROOM 			= "1 Room";
        final String PROMOCODE 			= "10PCT";

        final String UPGRADE_VALUE      = "BookIt";

        //Bags Page constant values
        final String DEP_BAGS           = "Carry_1|Checked_0";

        //Seat Page Constant Value
        final String SEATS              = "Premium";

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
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectHotelRoom(HOTELROOM);

        //Constants for validation
        final String PROMO_TEXT                 = "You'll find our Promo Codes in our promotional emails.";
        final String SPECIAL_CHARACTER          = "!@#$%";
        final String SPECIAL_CHARACTER_ERROR    = "Only letters and numbers are allowed";
        final String MORE_THAN_EIGHT_CHARACTER  = "123456789123";
        final String FLIGHT_AVAILABILITY_URL    = "/book/flights";
        final String PAYMENT_URL                = "/book/payment";

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
        //Input Invalid PromoCode
        //pageObjectManager.getHomePage().getPromoCodeTextBox().sendKeys(MORE_THAN_EIGHT_CHARACTER);

        //Promo code can have a maximum 8 characters
        // ValidationUtil.validateTestStep("Verify Promo code can have a maximum 8 characters on Home Page", pageObjectManager.getHomePage().getErrorMessageText().getText().toString().length()==8);

        //pageMethodManager.getHomePageMethods().clickSearchButton();

        //Enter a valid Promo Code and select SEARCH VACATION.

        WaitUtil.untilTimeCompleted(1000);

        pageObjectManager.getHomePage().getPromoCodeTextBox().sendKeys(PROMOCODE);

        pageMethodManager.getHomePageMethods().clickSearchButton();

        //verify user proceeds to flights page
        ValidationUtil.validateTestStep("User navigated to flights-hotels page",
                getDriver().getCurrentUrl(),(FLIGHT_AVAILABILITY_URL));

        pageObjectManager.getHotelPage().getHotelViewButton().get(0).click();

        WaitUtil.untilPageLoadComplete(getDriver());

        WaitUtil.untilTimeCompleted(1200);

        pageObjectManager.getHotelPage().getHotelPopUpSelectRoomsFromButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());

        WaitUtil.untilTimeCompleted(1200);

        pageObjectManager.getHotelPage().getHotelPopUpSelectRoomButton().get(0).click();

        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);

        pageObjectManager.getHotelPage().getContinueButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());

        pageObjectManager.getCarPage().getCarsPageContinueWithoutCarButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());

        WaitUtil.untilTimeCompleted(1200);

        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //wait until Bags page appear on web
        WaitUtil.untilPageLoadComplete(getDriver());

        //Bags Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectBagsFare("Standard");

        //Seat Page Methods
        scenarioContext.setContext(Context.AVAILABILITY_DEP_LEG , "1");
        scenarioContext.setContext(Context.AVAILABILITY_RET_LEG , "1");

        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(SEATS);
        pageMethodManager.getSeatsPageMethods().selectReturningSeats(SEATS);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //Options Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);

        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //verify user lands on payment page
        ValidationUtil.validateTestStep("Validating payment Page URL", getDriver().getCurrentUrl(), PAYMENT_URL);

        /****DO NOT PROCEED WITH PAYMENT****/
        //Payment page
//        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
//        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
//        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);
//
//        //Confirmation page closing ROKT Popup
//        WaitUtil.untilPageLoadComplete(getDriver());
//        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

    }
}