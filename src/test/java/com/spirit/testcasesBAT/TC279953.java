package com.spirit.testcasesBAT;

import com.spirit.baseClass.BaseClass;
import org.testng.annotations.*;
import com.spirit.utility.*;

//********************************************************************************************************
//Test Case ID: TC279953
//Description: Seats_Page_CP_BP_CI_MT_SinglePAX_BFS_Seat_Selection_With_Dynamic_Shopping_Cart_Validation
//Created By: Anthony Cardona
//Created on 18-March-2019
//Reviewed By: Salim Ansari
//Reviewed On: 12-July-2019
//*********************************************************************************************************

public class TC279953 extends BaseClass{

    @Parameters ({"platform"})
    @Test(groups = {"Guest","BookPath","OneWay","DomesticDomestic","Outside21Days","Adult","NonStop","BookIt","NoBags","BigFrontSeat","SeatsUI"})
    public void Seats_Page_CP_BP_CI_MT_SinglePAX_BFS_Seat_Selection_With_Dynamic_Shopping_Cart_Validation (@Optional("NA")String platform) {

        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC279953 under BAT Suite on " + platform + " Browser"   , true);
        }

       //****************************Navigate to Seat Page*****************************
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
        final String ADULTS				= "1";
        final String CHILD				= "0";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		= "NonStop";
        final String FARE_TYPE			= "Standard";
        final String JOURNEY_UPGRADE	= "BookIt";

        //option page constant value
        final String OPTION_PAGE_URL    = "book/options";

//Step--1,2,3
        //open browser
        openBrowser(platform);

        //Home Page common Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Common Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(JOURNEY_UPGRADE);

        //Customer Info Common Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Common Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //**************Validation on Seats Page**************
//Step--4
        String seatPrice = pageObjectManager.getSeatsPage().getBigFrontEmptySeatsGridView().get(0).getText().trim().replace("&nbsp","");
        pageObjectManager.getSeatsPage().getBigFrontEmptySeatsGridView().get(0).click();

//Step--5
        ValidationUtil.validateTestStep("The BFS price correctly reflects on the Seats breakdown",
                pageObjectManager.getSeatsPage().getSeatsTotalPriceText().getText().trim(),seatPrice);
//Step--6
        //open breakdown
        pageObjectManager.getSeatsPage().getSeatsTotalContainerLink().click();
        WaitUtil.untilTimeCompleted(1500);
        ValidationUtil.validateTestStep("The BFS price correctly reflects on the Seats breakdown", pageObjectManager.getSeatsPage().getSeatTotalBreakDownPriceText().getText().trim(),seatPrice);

//Step--7,8
        //open the shopping cart breakdown
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1500);

        //expand shopping Cart
        pageObjectManager.getHeader().getSeatsItinerarayImage().click();
        WaitUtil.untilTimeCompleted(1500);
        String shoppingCartSeatsPrice = pageObjectManager.getHeader().getSeatsPriceItineraryText().getText();

        //validate the Seats Total on shopping Cart
        ValidationUtil.validateTestStep("The Shopping Cart has the correct price of the BFS" , shoppingCartSeatsPrice, seatPrice);

//Step--9
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        ValidationUtil.validateTestStep("The User is redirected to the Options Page", getDriver().getCurrentUrl(),OPTION_PAGE_URL);
    }
}