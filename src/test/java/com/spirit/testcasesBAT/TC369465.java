package com.spirit.testcasesBAT;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//*******************************************************************************************************************************************
//Test Case ID: TC369465
//Description: Dynamic Shopping Cart Bags Page Generic for all Booking Types
//Created By: Anthony Cardona
//Created on 20-March-2019
//Reviewed By: Salim Ansari
//Reviewed On:
//*******************************************************************************************************************************************

public class TC369465 extends BaseClass {

    @Parameters ({"platform"})
    @Test(groups = {"Guest","BookPath","OneWay","DomesticDomestic","Outside21Days","Adult","Connecting","BookIt","CarryOn",
            "CheckedBags","NoSeats","ShortCutBoarding","CheckInOptions","MasterCard","BagsUI","DynamicShoppingCartUI"})
    public void dynamic_Shopping_Cart_Bags_Page_Generic_for_all_Booking_Types (@Optional("NA")String platform)
    {
        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC369465 under BAT Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "OneWay";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "ATL";
        final String DEP_DATE 			= "25";
        final String ARR_DATE 			= "NA";
        final String ADULTS				= "2";
        final String CHILD				= "0";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT	    = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 	    = "Connecting";
        final String FARE_TYPE			= "Standard";
        final String JOURNEY_UPGRADE    = "BookIt";

        //Passenger Info Page

        //Bags Page
        final String BAGS_PAGE_URL      = "book/bags";

        //option page constant value
        final String CHECKIN_OPTION     = "CheckInOption:MobileFree";

        //payment page constant value
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_GAURD       = "NotRequired";

        //Step--1,2,3
        //open browser
        openBrowser(platform);

        /***************************************************
         *********Navigate to Flight Availability Page******
         ***************************************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        /***************************************************
         *********Navigate to Passenger Information Page****
         ***************************************************/
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        //flight price to use in validation in step 8
        String flightPrice = pageObjectManager.getFlightAvailabilityPage().getFlightTotalAmountText().getText().trim().replace("$","").trim();
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(JOURNEY_UPGRADE);

        /***************************************************
         ****************Navigate to Bags Page**************
         ***************************************************/
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /***************************************************
         *************Validation on Bags Page**************
         ***************************************************/
//Step -- 4
        //Declare variable used for validation
        String tempCarryOnBagPrice , tempCheckedBagPrice;
        String BreakDownTotalPrice;
        String bagsTotalst;
        double carryonPrice;
        double checkedBagPrice;
        double bagsTotal;

        //get carryon price
        tempCarryOnBagPrice = pageObjectManager.getBagsPage().getCarryOnBagPriceDisplayText().getText();
        tempCarryOnBagPrice = tempCarryOnBagPrice.substring(tempCarryOnBagPrice.indexOf("$"), tempCarryOnBagPrice.length()).replace("$", "").trim();
        carryonPrice = Double.parseDouble(tempCarryOnBagPrice); //changed to double
        carryonPrice = carryonPrice * (Integer.parseInt(ADULTS)  + Integer.parseInt(CHILD) + Integer.parseInt(INFANT_SEAT)); //Multiply price by num of pax

        //get checked bag price
        tempCheckedBagPrice = pageObjectManager.getBagsPage().getCheckedBagPriceDisplayText().getText();
        tempCheckedBagPrice = tempCheckedBagPrice.substring(tempCheckedBagPrice.indexOf("$"), tempCheckedBagPrice.length()).replace("$", "").trim();
        checkedBagPrice = Double.parseDouble(tempCheckedBagPrice); //changed to double
        checkedBagPrice = checkedBagPrice * (Integer.parseInt(ADULTS)  + Integer.parseInt(CHILD) + Integer.parseInt(INFANT_SEAT)); //Multiply price by num of pax

        bagsTotal = carryonPrice + checkedBagPrice;   //expected total for bags

        //click on 1 Carry0n bag for each passenger (2 passengers)
        for(WebElement carryonPlusbutton : pageObjectManager.getBagsPage().getDepartingCarryOnPlusButton()) {
            if (TestUtil.verifyElementDisplayed(carryonPlusbutton)) {
                carryonPlusbutton.click();  // if carry-on bag plus button display, click
                WaitUtil.untilTimeCompleted(2000);
            }

        }
        //click on 1 checked bag for each passenger (2 passengers)
        for(WebElement checekedBagPlusbutton : pageObjectManager.getBagsPage().getDepartingCheckedBagPlusButton()) {
            if (TestUtil.verifyElementDisplayed(checekedBagPlusbutton)) {
                checekedBagPlusbutton.click(); // if checked bag plus button display, click
                WaitUtil.untilTimeCompleted(2000);
            }
        }

        bagsTotalst = Double.toString(bagsTotal); //bags total converted to a string

        BreakDownTotalPrice = pageObjectManager.getBagsPage().getBagsTotalContainerAmountTotalText().getText().replace("$", "");
        ValidationUtil.validateTestStep("The Expected price is not correct on the Total Breakdown amount",  BreakDownTotalPrice,bagsTotalst);

//Step -- 5
        validateBagsPriceOnShoppingCart(bagsTotalst);

//Step -- 6
        //go to the seats page and validate the bags total on the shopping cart
        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);
        WaitUtil.untilPageLoadComplete(getDriver());
        validateBagsPriceOnShoppingCart(bagsTotalst);

//Step -- 7
        //click on edit on the bags section on shopping cart , validate redirected to the bags page
        pageObjectManager.getHeader().getArrowBagsItineraryImage().click();

        WaitUtil.untilTimeCompleted(2000);
        pageObjectManager.getHeader().getEditBagsItneraryButton().click();

        WaitUtil.untilTimeCompleted(2000);
        ValidationUtil.validateTestStep("The user is not redirected to the bags page", getDriver().getCurrentUrl(),BAGS_PAGE_URL);
        validateBagsPriceOnShoppingCart(bagsTotalst);

//Step -- 8
        //Expand shopping cart and validate the flight price
        pageObjectManager.getHeader().getArrowFlightItineraryImage().click();
        WaitUtil.untilTimeCompleted(2000);
        //validate the flight price from original flight price
        ValidationUtil.validateTestStep("The Flight price is the same as the FA page",
                pageObjectManager.getHeader().getFlightPriceItineraryText().getText().trim(),flightPrice);

//Step -- 9
        //click on the edit flight button and validate that the edit flight pop-up is displayed
        WaitUtil.untilTimeCompleted(2000);
        pageObjectManager.getHeader().getEditFlightItineraryButton().click();
        //validate pop-up
        WaitUtil.untilTimeCompleted(2000);
        ValidationUtil.validateTestStep("The Edit FLight modal is displayed",
                TestUtil.verifyElementDisplayed(getDriver(),By.xpath(pageObjectManager.getHeader().xpath_AreYouSurePopUpPanel)));

//Step -- 10
        //exit out of pop-up
        pageObjectManager.getHeader().getAreYouSurePopUpCloseImage().click();
        WaitUtil.untilTimeCompleted(2000);

        //continue to the payment page
        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getOptionsPageMethods().selectOptions(CHECKIN_OPTION);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//Step -- 11, 12
        //Input CC information
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GAURD);

//Step -- 13
        //Redirected to the confirmation page
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

    }


    /**
     * Description: Validates the bags price on the shopping cart vs the price from the bags page <BR>
     * @author Anthony Cardona
     * @param bagsTotal : price from the bags page
     */
    private void validateBagsPriceOnShoppingCart (String bagsTotal)
    {
        /************this xpath still pending and need to be implemented in separate file***********************/
        //this xpath still pending and need to be implemented in separate file
        //open the shopping cart breakdown
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(2000);
        ValidationUtil.validateTestStep("The Shopping Cart is not displayed correctly", pageObjectManager.getHeader().getBagsPriceItineraryText().getText().trim(),bagsTotal);
    }
}