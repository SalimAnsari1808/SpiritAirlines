package com.spirit.misc;

import com.spirit.baseClass.*;
import com.spirit.dataProviders.*;
import com.spirit.dataProviders.DataGenerator;
import com.spirit.utility.*;
import org.testng.*;
import org.testng.annotations.Optional;
import org.testng.annotations.*;
import org.testng.annotations.Test;

import java.util.*;

public class Test1 extends BaseClass {
    /******************************************************************************
     ***********************Set-Up / Configurations********************************
     ******************************************************************************/

    String platform = "NA";
    @Parameters({"platform"})
    @Test(dataProviderClass = DataGenerator.class, dataProvider = "dp")

    public void Test1(Hashtable<String,String> data){

        if(!data.get("Runmode").equals("Y")){
            throw new SkipException("Skipping the test case as the Run mode for data set is NO");
}
        ValidationUtil.validateTestStep("Starting Test Case ID "+ data.get("TestID") +" "+ data.get("Test_Case_Name") + " under BAT Suite on " + platform + " Browser"   , true);

        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(data.get("LANGUAGE"));

            if(!data.get("LOGIN").equals("NA")){
            pageMethodManager.getHomePageMethods().loginToApplication(data.get("LOGIN"));}
            else {
                System.out.println("Log-in NA");
            }
            pageMethodManager.getHomePageMethods().selectJourneyType(data.get("JOURNEY_TYPE"));
            pageMethodManager.getHomePageMethods().selectTripType(data.get("TRIP_TYPE"));
            pageMethodManager.getHomePageMethods().selectAirports("AllLocation", data.get("DEP_AIRPORT_CODE"), "AllLocation", data.get("ARR_AIRPORT_CODE"));

            if(data.get("ARR_DATE").equals("NA")) {
                pageMethodManager.getHomePageMethods().selectDates(String.valueOf(((int) Double.parseDouble(data.get("DEP_DATE")))), "NA");
            }
            else{
                pageMethodManager.getHomePageMethods().selectDates(String.valueOf(((int) Double.parseDouble(data.get("DEP_DATE")))), String.valueOf(((int) Double.parseDouble(data.get("ARR_DATE")))));
            }
            pageMethodManager.getHomePageMethods().selectTravellingPassenger(String.valueOf(((int)Double.parseDouble(data.get("ADULTS")))), String.valueOf(((int)Double.parseDouble(data.get("CHILD")))),String.valueOf(((int)Double.parseDouble(data.get("INFANT_SEAT")))), String.valueOf(((int)Double.parseDouble(data.get("INFANT_LAP")))));
            pageMethodManager.getHomePageMethods().clickSearchButton();
//
            WaitUtil.untilTimeCompleted(2000);

            if(TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getYoungTravelerPopup())){
            pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();
            }
            else {
                System.out.println("No Young traveler pop");
            }

            pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", data.get("DEP_FLIGHT"));
            if(!data.get("RET_FLIGHT").equals("NA")){
                pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", data.get("RET_FLIGHT"));
            }else{
                System.out.println("Return flight is NA");
            }
            pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(data.get("FARE_TYPE"));
            pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(data.get("UPGRADE_VALUE"));

            //Passenger Info Methods
            pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
            pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
            pageMethodManager.getPassengerInfoMethods().clickContinueButton();

            //Bags Page Methods
            if(data.get("DEP_BAGS").equals("NA") && data.get("RET_BAGS").equals("NA")) {
                pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
            }else if(!data.get("DEP_BAGS").equals("NA")) {
                pageMethodManager.getBagsPageMethods().selectDepartingBags(data.get("DEP_BAGS"));
                if (!data.get("RET_BAGS").equals("NA")) {
                    pageMethodManager.getBagsPageMethods().selectReturnBags(data.get("RET_BAGS"));
                    pageMethodManager.getBagsPageMethods().selectBagsFare(data.get("BAGS_FARE"));
                }else{
                    pageMethodManager.getBagsPageMethods().selectBagsFare(data.get("BAGS_FARE"));
                }
            }

            //Seats Page Methods
            if(data.get("DEP_SEATS").equals("NA") && data.get("RET_SEATS").equals("NA")) {
                pageMethodManager.getSeatsPageMethods().continueWithSeats();
            }else if(!data.get("DEP_SEATS").equals("NA")) {
                pageMethodManager.getSeatsPageMethods().selectDepartureSeats(data.get("DEP_SEATS"));
                if (!data.get("RET_SEATS").equals("NA")) {
                    pageMethodManager.getSeatsPageMethods().selectDepartureSeats(data.get("RET_SEATS"));;
                    pageMethodManager.getSeatsPageMethods().continueWithSeats();
                }else{
                    pageMethodManager.getSeatsPageMethods().continueWithSeats();
                }
            }

            pageMethodManager.getOptionsPageMethods().selectOptions(data.get("OPTION_VALUE"));
            pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

            pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(data.get("CARD_TYPE"));
            pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
            pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(data.get("TRAVEL_GUARD"));

            pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

    }

}

