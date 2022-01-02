package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.*;
import com.spirit.enums.*;
import com.spirit.utility.*;
import org.testng.annotations.*;
import org.testng.annotations.Optional;

import java.util.*;

//**********************************************************************************************
//Test Case ID: TC91052
//Title       : Search Widget_BP_CP_ Valid Promo Code for dollars
//Description : Confirm flight is being discounted using valid promo code
//Created By  : Alex Rodriguez
//Created On  : 27-Mar-2019
//Reviewed By : Salim Ansari
//Reviewed On : 04-Apr-2019
//**********************************************************************************************
public class TC91052 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "PromoCode" , "Within21Days" , "Adult" , "Guest" ,
            "Nonstop" , "BookIt" , "NoBags" , "NoSeats" , "CheckInOptions" , "AmericanExpress","PaymentUI","ConfirmationUI"})
    public void Search_Widget_BP_CP_Valid_Promo_Code_for_dollars(@Optional("NA") String platform) {
        /******************************************************************************
         *******************************Navigate to Home Page**************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91052 under SMOKE Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 	  	= "Flight";
        final String TRIP_TYPE 		    = "RoundTrip";
        final String DEP_AIRPORTS 	  	= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "ATL";
        final String ARR_AIRPORTS 	  	= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "BWI";
        final String DEP_DATE 		    = "7";
        final String ARR_DATE 	        = "10";
        final String ADULTS 		    = "1";
        final String CHILDREN 		    = "0";
        final String INFANT_LAP 	    = "0";
        final String INFANT_SEAT 		= "0";
        final String VALID_PROMO_CODE   = "25PCT";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 	    = "Nonstop";
        final String ARR_FLIGHT 	    = "Nonstop";
        final String FARE_TYPE 	    	= "Standard";
        final String UPGRADE_VALUE 	  	= "BookIt";

        //Option Page Constant Values
        final String OPTIONS_VALUE	    = "CheckInOption:MobileFree";

        //Purchase Page Constant Values
        final String TRAVEL_GUARD 	  	= "NotRequired";
        final String CARD_TYPE 			= "AmericanExpressCard";

        //Steps 1-6
        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        selectDatesInFirstFourDayOFWeek(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().enterPromoCode(VALID_PROMO_CODE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Step 7 -9
        //Flight Availability Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Step 10
        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seat Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Step 11-12
        //******************************************
        //*********Payment Page Validations ********
        //******************************************
        //declare constant used in validation
        final String PROMO_TEXT     =   "PROMO APPLIED";

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

        //promo code for departure
        ValidationUtil.validateTestStep("The promo code has been applied to the booking for the Departure Flight on Payment Page",
                pageObjectManager.getPaymentPage().getDepOnlyFlightChargeText().get(0).getText().toUpperCase(),PROMO_TEXT);

        //flight price without promo code
        withoutPromoPrice = Double.parseDouble(pageObjectManager.getPaymentPage().getDepOnlyFlightChargeText().get(1).getText().split(" ")[0].replace("$",""));

        //flight price with promo code
        withPromoPrice = Double.parseDouble(pageObjectManager.getPaymentPage().getDepOnlyFlightChargeText().get(1).getText().split(" ")[1].replace("$",""));

        //promo code flight price
        ValidationUtil.validateTestStep("Departure Flight Price is reduced after adding Promo Code on Payment Page",
                withoutPromoPrice - withPromoPrice > 0);

        //promo code for return
        ValidationUtil.validateTestStep("The promo code has been applied to the booking for the Return Flight on Payment Page",
                pageObjectManager.getPaymentPage().getRetOnlyFlightChargeText().get(0).getText().toUpperCase(),PROMO_TEXT);

        //flight price without promo code
        withoutPromoPrice = Double.parseDouble(pageObjectManager.getPaymentPage().getRetOnlyFlightChargeText().get(1).getText().split(" ")[0].replace("$",""));

        //flight price with promo code
        withPromoPrice = Double.parseDouble(pageObjectManager.getPaymentPage().getRetOnlyFlightChargeText().get(1).getText().split(" ")[1].replace("$",""));

        //promo code flight price
        ValidationUtil.validateTestStep("Return Flight Price is reduced after adding Promo Code on Payment Page",
                withoutPromoPrice - withPromoPrice > 0);

        //get total flight price
        finalFlightPrice = Double.parseDouble(pageObjectManager.getPaymentPage().getTotalDueFlightPriceText().getText().replace("$", "").trim());

        //Reach to Confirmation page
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        //Confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //total price
        ValidationUtil.validateTestStep("Total Booking Flight on Payment Page is same on Confirmation Page",
                Double.parseDouble(pageObjectManager.getConfirmationPage().getTotalPaidPriceText().getText().replace("$", "").trim())==finalFlightPrice);

        //open total due breakdown
        pageObjectManager.getConfirmationPage().getTotalPaidPriceText().click();

        //wait for 1 sec
        WaitUtil.untilTimeCompleted(1000);

        //open flight breakdown
        pageObjectManager.getPaymentPage().getTotalDueFlightText().click();

        //wait for 1 sec
        WaitUtil.untilTimeCompleted(1000);

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

        //promo code for return
        ValidationUtil.validateTestStep("The promo code has been applied to the booking for the Return Flight on Confirmation Page",
                pageObjectManager.getPaymentPage().getRetOnlyFlightChargeText().get(0).getText().toUpperCase(),PROMO_TEXT);

        //flight price without promo code
        withoutPromoPrice = Double.parseDouble(pageObjectManager.getPaymentPage().getRetOnlyFlightChargeText().get(1).getText().split(" ")[0].replace("$",""));

        //flight price with promo code
        withPromoPrice = Double.parseDouble(pageObjectManager.getPaymentPage().getRetOnlyFlightChargeText().get(1).getText().split(" ")[1].replace("$",""));

        //promo code flight price
        ValidationUtil.validateTestStep("Return Flight Price is reduced after adding Promo Code on Confirmation Page",
                withoutPromoPrice - withPromoPrice > 0);


    }

    //**********************************************************************************************
    //Method Name:selectDatesInFirstFourDayOFWeek
    //Description: Method is used to select the date in first four day of week i.e Monday to Thursday
    //Input Arguments:1.strDepDate->Departure Date
    //                2.strArrDate->Return Date
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 10-Feb-2018
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    private void selectDatesInFirstFourDayOFWeek(String strDepDate, String strArrDate) {
        //declare variable used in method
        String strActualDepDate, strActualArrDate = null, strFinalDate = null;

        int dateCounter;


        //*****************************************************************
        //***********************Departure Date****************************
        //*****************************************************************

        for(dateCounter=0;dateCounter<10;dateCounter++){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(TestUtil.convertStringToDate(TestUtil.getStringDateFormat(Integer.toString(Integer.parseInt(strDepDate)+dateCounter), "MM/dd/yyyy"),"MM/dd/yyyy"));

            //check date is coming with four days of week
            if(calendar.get(Calendar.DAY_OF_WEEK)>=2 && calendar.get(Calendar.DAY_OF_WEEK)<=5){
                break;
            }
        }

        //get new date
        strDepDate = Integer.toString(Integer.parseInt(strDepDate)+dateCounter);

        //get the dep date
        strActualDepDate = TestUtil.getStringDateFormat(strDepDate, "MM/dd/yyyy");

        //*****************************************************************
        //*************************Arrival Date****************************
        //*****************************************************************
        //new arrival date
        strArrDate = Integer.toString(Integer.parseInt(strArrDate)+dateCounter);

        for(dateCounter=0;dateCounter<10;dateCounter++){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(TestUtil.convertStringToDate(TestUtil.getStringDateFormat(Integer.toString(Integer.parseInt(strArrDate)+dateCounter), "MM/dd/yyyy"),"MM/dd/yyyy"));

            //check date is coming within first four days of week
            if(calendar.get(Calendar.DAY_OF_WEEK)>=2 && calendar.get(Calendar.DAY_OF_WEEK)<=5){
                break;
            }
        }

        //get final arrival date
        strArrDate = Integer.toString(Integer.parseInt(strArrDate)+dateCounter);

        //get the dep date
        strActualArrDate = TestUtil.getStringDateFormat(strArrDate, "MM/dd/yyyy");

        //get date string
        strFinalDate = strActualDepDate + " - " + strActualArrDate;

        //clear previous dates
        TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getHomePage().getDateBox());

        //enter new dates
        pageObjectManager.getHomePage().getDateBox().sendKeys(strFinalDate);

        //validate depart date
        ValidationUtil.validateTestStep("User Selected Departure Date as "+ strActualDepDate + " on Home Page", true);

        //store dep date
        scenarioContext.setContext(Context.HOMEPAGE_DEP_DATE, strDepDate);

        //set global variable for 24 hour flight
        if(strDepDate.equalsIgnoreCase("0")){
            scenarioContext.setContext(Context.HOMEPAGE_CHECK_IN, "24Hours");
        }else{
            scenarioContext.setContext(Context.HOMEPAGE_CHECK_IN, "Outside24Hours");
        }

        //validate return date
        ValidationUtil.validateTestStep("User Selected Departure Date as "+ strActualArrDate + " on Home Page", true);

        //store ret date
        scenarioContext.setContext(Context.HOMEPAGE_ARR_DATE, strArrDate);

    }
}


