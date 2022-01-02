package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC280746
//Test Name: E2E_FS_RT_INT_MAX_PAX_MIX_SW_Change_Month_Date_Bundle_It_Tie_3_Thru_Flight_Pax3_Voluntary_Emergency_Services__Pax1_MAx_bike_Pax2_max_SB_Pax3_Max_bagsrest_1CO_1CB_Any_free_seat_No_Extras_Discover
//Description: FS member in a multi-passenger booking with 3 children on an international flight. Bundle is selected and validations were made on bags page when surf, bikes and bags were added
//Created By : Gabriela
//Created On : 29-APR-2019
//Reviewed By: Salim Ansari
//Reviewed On: 3-May-2019
//**********************************************************************************************

public class TC280746 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "Outside21Days" , "Adult" , "Child" ,
            "FreeSpirit" , "NonStop" , "BundleIt" , "NewFlightSearch" , "PassengerInfoSSR" , "CarryOn" ,
            "CheckedBags" , "SurfBoard" , "Bikes" , "Standard","FlightFlex" , "ShortCutBoarding" , "CheckInOptions" ,
            "Discover","FlightAvailabilityUI","BagsUI"})
    public void E2E_FS_RT_INT_MAX_PAX_MIX_SW_Change_Month_Date_Bundle_It_Tie_3_Thru_Flight_Pax3_Voluntary_Emergency_Services__Pax1_MAx_bike_Pax2_max_SB_Pax3_Max_bagsrest_1CO_1CB_Any_free_seat_No_Extras_Discover(@Optional("NA") String platform) {
        /******************************************************************************
         ************************Navigate to Confirmation Page*************************
         ******************************************************************************/
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280746 under SMOKE Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE               ="English";
        final String LOGIN_ACCOUNT          ="FSEmail";
        final String JOURNEY_TYPE           ="Flight";
        final String TRIP_TYPE              ="RoundTrip";
        final String DEP_AIRPORTS           ="AllLocation";
        final String DEP_AIRPORT_CODE       ="MYR";
        final String ARR_AIRPORTS           ="AllLocation";
        final String ARR_AIRPORT_CODE       ="LGA";
        final String DEP_DATE               ="9";
        final String ARR_DATE               ="20";
        final String ADULTS                 ="6";
        final String CHILD                  ="3";
        final String INFANT_LAP             ="0";
        final String INFANT_SEAT            ="0";

        //Through Flight is not available so changed to NonStop Flight
        //Flight Availability Page Constant Values
        final String DEP_FLIGHT             ="NonStop";
        final String RET_FLIGHT             ="NonStop";
        final String FARE_TYPE              ="Standard";
        final String UPGRADE_VALUE          ="BundleIt";

        //New Search on Flight Availability page Constant Values
        final String DEP_DATE1              ="55";
        final String ARR_DATE1              ="76";

        //Bags Page Constant Values
        final String BAGS_PRESELECTED 	    = "1";
        final String BAGS_INCLUDED		    = "Included";

        //Seats page Constant Values
        final String DEP_SEATS			    = "Standard|Standard|Standard|Standard|Standard|Standard|Standard|Standard|Standard";
        final String RET_SEATS			    = "Standard|Standard|Standard|Standard|Standard|Standard|Standard|Standard|Standard";

        //Options Page Constant Value
        final String OPTION_VALUE 		    = "CheckInOption:MobileFree";

        //payment page constant value
        final String CARD_TYPE              = "DiscoverCard1";
        final String TRAVEL_GUARD           = "NotRequired";

        //Confirmation Page Constant Value
        final String BOOKING_STATUS         = "Confirmed";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        //******************Flight Availability Page*************************
        WaitUtil.untilPageLoadComplete(getDriver());

        //New Search button is selected
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();

        //Dates are changed
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE1, ARR_DATE1);

        //Search new selection
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Young Traveler Pop Up
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        WaitUtil.untilPageLoadComplete(getDriver(),(long)120);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);

        //************************Upgrade and Save Pop Up********************
        //Verifying Bundle saving is higher than Boost it
        WaitUtil.untilPageLoadComplete(getDriver(),(long)120);

        //Converting Bundle Saving from sting to double
        String BundleSaveUpto = pageObjectManager.getFlightAvailabilityPage().getBundleItSaveUpToPriceText().getText();
        String BundleSaveUptoSub = BundleSaveUpto.replace("Save Up To $", "");
        String BundleSaveUptoSub1 = BundleSaveUptoSub.replace(",","");
        double BundleSaveDouble = Double.parseDouble(BundleSaveUptoSub1);

        //Converting Bundle Saving from sting to double
        String BoostSaveUpto = pageObjectManager.getFlightAvailabilityPage().getBoostItSaveUpToPriceText().getText();
        String BoostSaveUptoSub = BoostSaveUpto.replace("Save Up To $", "");
        String BoostSaveUptoSub1 = BoostSaveUptoSub.replace(",", "");
        double BoostSaveDouble = Double.parseDouble(BoostSaveUptoSub1);
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Verifying Bundle saving is higher than Boost it",
                BundleSaveDouble > BoostSaveDouble);

        //Selecting Bundle It Options
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        /************************Passenger Information page*****************/
        //waitForPageToLoad();
        WaitUtil.untilPageLoadComplete(getDriver(),(long)120);

        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageObjectManager.getPassengerInfoPage().getAdditionalServicesListLinkButton().get(0).click();
        pageObjectManager.getPassengerInfoPage().getVoluntaryProvisionofEmergencyServicesProgramListCheckBox().get(0).click();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /*******************************Bags page***************************/
        //Validating City Pair are right
        ValidationUtil.validateTestStep("Validating departure city is correct",
                pageObjectManager.getBagsPage().getDepartureCityText().getText().contains(DEP_AIRPORT_CODE));

        ValidationUtil.validateTestStep("Validating arriving city is correct",
                pageObjectManager.getBagsPage().getDepartureCityText().getText().contains(ARR_AIRPORT_CODE));

        //ValidationUtil.validateTestStep("Verify 1-Carry-On and 1-Checked Bags are Pre-Selected for all passengers on Bags Page
        for(int i = 0; i < pageObjectManager.getBagsPage().getDepartingCarryOnBagCounterTextBox().size(); i ++) {
            // Verifying Carry-on is preselected
            ValidationUtil.validateTestStep("Verifying Carry On is preselected",
                    JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingCarryOnBagCounterTextBox().get(i)) , (BAGS_PRESELECTED));

            //Verifying Carry On price is included
            ValidationUtil.validateTestStep("Verifying Carry On price is included",
                    pageObjectManager.getBagsPage().getDepartingCarryOnPriceText().get(i).getText() , (BAGS_INCLUDED));

            // Verifying Checked Bag is preselected
            ValidationUtil.validateTestStep("Verifying 1 checked bag is preselected",
                    JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingCheckedBagCounterTextBox().get(i)) , (BAGS_PRESELECTED));

            //Verifying Checked Bag price is included
            ValidationUtil.validateTestStep("Verifying Checked Bag price is included",
                    pageObjectManager.getBagsPage().getDepartingCheckedBagPriceText().get(i).getText() , (BAGS_INCLUDED));
        }

        //Selecting 4 bikes for pax 1
        pageObjectManager.getBagsPage().getDepartingSportingEquipmentLinkButton().get(0).click();
        WaitUtil.untilTimeCompleted(1200);
        for (int count = 0; count <= 4; count ++){
            pageObjectManager.getBagsPage().getDepartingBicyclePlusButton().get(0).click();
        }

        //Selecting 4 Surf for pax 2
        pageObjectManager.getBagsPage().getDepartingSportingEquipmentLinkButton().get(1).click(); //open sporting equipment
        WaitUtil.untilTimeCompleted(1200);
        for (int count = 0; count <= 4; count ++){
            pageObjectManager.getBagsPage().getDepartingSurfBoardPlusButton().get(1).click();
        }

        //Selecting Max Bags for pax 3
        WaitUtil.untilTimeCompleted(1200);
        for (int count = 0; count <= 4; count ++){
            pageObjectManager.getBagsPage().getDepartingCheckedBagPlusButton().get(2).click();
        }

        //Continue with bags and equipments selection
        pageObjectManager.getBagsPage().getContinueWithStandardBagsContainerContinueButton().click();

        /****************************Seats Page*****************************/
        //Selecting Seats for Departure Flight
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEATS);

        //Selecting Seats for Returning Flight
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getSeatsPageMethods().selectReturningSeats(RET_SEATS);

        //Continue to Options page
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        /***************************Option Page*****************************/
        //Selecting Check In on mobile for free
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);

        //Continue to Payment page
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /************************Payment Page Methods*********************************/
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);


        /**********************Confirmation Page***************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
    }
}
