package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC280655
//Test Name: E2E_FS_OW_DOM_1_ADT_2_INFT_1_lap_1_seat_SW_ChangePAX_to_1_INFT_LAP_1_child_2_Bundle_It_Tier_3_Fare_Flight_STD_OW_1CO_1CB_1_Free_Seat_No_Extras_CI_Web_Credit_Card
//Description: FS member with 1 infant on lap and 1 child. Bags, seats, and extras are included for the Bundle option validation
//Created By : Gabriela
//Created On : 29-APR-2019
//Reviewed By: Salim Ansari
//Reviewed On: 3-May-2019
//**********************************************************************************************

public class TC280655 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups= {"BookPath" , "OneWay" , "DomesticDomestic" , "Within21Days" , "Adult" , "Child" , "InfantLap" ,
                    "FreeSpirit" , "NewFlightSearch" , "NonStop" , "BundleIt" , "CarryOn" , "CheckedBags" , "Standard" , "SeatsUI"
                    , "FlightFlex" , "ShortCutBoarding" , "CheckInOptions" , "TravelInsurancePopUp" , "Visa" , "BagsUI" ,
                    "PassengerInformationUI"})
    public void E2E_FS_OW_DOM_1_ADT_2_INFT_1_lap_1_seat_SW_ChangePAX_to_1_INFT_LAP_1_child_2_Bundle_It_Tier_3_Fare_Flight_STD_OW_1CO_1CB_1_Free_Seat_No_Extras_CI_Web_Credit_Card(@Optional("NA") String platform) {
        /******************************************************************************
         ***********************Navigation to Confirmation Page************************
         ******************************************************************************/
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC280655 under SMOKE Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE               ="English";
        final String LOGIN_ACCOUNT          ="FSEmail";
        final String JOURNEY_TYPE           ="Flight";
        final String TRIP_TYPE              ="OneWay";
        final String DEP_AIRPORTS           ="AllLocation";
        final String DEP_AIRPORT_CODE       ="CLE";
        final String ARR_AIRPORTS           ="AllLocation";
        final String ARR_AIRPORT_CODE       ="LAX";
        final String DEP_DATE               ="9";
        final String ARR_DATE               ="NA";
        final String ADULTS                 ="1";
        final String CHILD                  ="0";
        final String INFANT_LAP             ="1";
        final String INFANT_SEAT            ="1";

        //New Search Flight Availability Page Constant Values
        final String ADULTS1                = "1";
        final String CHILD1                 = "1";
        final String INFANT_LAP1            = "1";
        final String INFANT_SEAT1           = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT             ="NonStop";
        final String FARE_TYPE              ="Standard";
        final String UPGRADE_VALUE          ="BundleIt";

        //Bags Page Constant
        final String BAGS_PRESELECTED 	    = "1";
        final String BAGS_INCLUDED		    = "Included";

        //Seats Page Constant Values
        final String DEP_SEATS			    = "Standard|Standard";
        final String ZERO_SEAT_PRICE 	    = "$0.00";

        //payment page constant value
        final String CARD_TYPE              = "VisaCard";
        final String TRAVEL_GUARD           = "NotRequired";

        //Confirmation Page Constant Value
        final String BOOKING_STATUS         = "Confirmed";
        final String SELECTED_OPTION        = "FlightFlex|ShortCutBoarding";

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
        //New Search button is selected and passengers changes
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS1, CHILD1, INFANT_SEAT1, INFANT_LAP1);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        //Selecting Standard Flight fares
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);

        //************************Upgrade and Save Pop Up********************
        //Verifying Bundle saving is higher than Boost it
        WaitUtil.untilPageLoadComplete(getDriver());

        //Converting Bundle Saving from sting to double
        String BundleSaveUpto = pageObjectManager.getFlightAvailabilityPage().getBundleItSaveUpToPriceText().getText();
        String BundleSaveUptoSub = BundleSaveUpto.replace("Save Up To $", "");
        double BundleSaveDouble = Double.parseDouble(BundleSaveUptoSub);

        //Converting Bundle Saving from sting to double
        String BoostSaveUpto = pageObjectManager.getFlightAvailabilityPage().getBoostItSaveUpToPriceText().getText();
        String BoostSaveUptoSub = BoostSaveUpto.replace("Save Up To $", "");
        double BoostSaveDouble = Double.parseDouble(BoostSaveUptoSub);
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Verifying Bundle saving is higher than Boost it",
                BundleSaveDouble > BoostSaveDouble);

        //Selecting Bundle It Options
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //************************Passenger Information page*****************
        //Verifying infant DOB fields are present
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Verifying infant in lap DOB is displayed",
                !pageObjectManager.getPassengerInfoPage().getInfantDOBListTextBox().get(0).getAttribute("value").isEmpty());

        ValidationUtil.validateTestStep("Verifying infant with seat DOB is displayed",
                !pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(1).getAttribute("value").isEmpty());

        //Filling fields
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();

        //Selecting Traveling with a car seat and continue
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //*******************************Bags page***************************
        //Validating City Pair are right
        ValidationUtil.validateTestStep("Validating departure city is correct",
                pageObjectManager.getBagsPage().getDepartureCityText().getText().contains(DEP_AIRPORT_CODE));

        ValidationUtil.validateTestStep("Validating arriving city is correct",
                pageObjectManager.getBagsPage().getDepartureCityText().getText().contains(ARR_AIRPORT_CODE));

        //ValidationUtil.validateTestStep("Verify 1-Carry-On and 1-Checked Bags are Pre-Selected for all passengers on Bags Page
        for(int i = 0; i < pageObjectManager.getBagsPage().getDepartingCarryOnBagCounterTextBox().size(); i ++) {
            // Verifying Carry-on is preselected
            ValidationUtil.validateTestStep("Verifying Carry On is preselected",
                    JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingCarryOnBagCounterTextBox().get(i)), (BAGS_PRESELECTED));

            //Verifying Carry On price is included
            ValidationUtil.validateTestStep("Verifying Carry On price is included",
                    pageObjectManager.getBagsPage().getDepartingCarryOnPriceText().get(i).getText(),(BAGS_INCLUDED));

            // Verifying Checked Bag is preselected
            ValidationUtil.validateTestStep("Verifying 1 checked bag is preselected",
                    JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingCheckedBagCounterTextBox().get(i)) , (BAGS_PRESELECTED));

            //Verifying Checked Bag price is included
            ValidationUtil.validateTestStep("Verifying Checked Bag price is included",
                    pageObjectManager.getBagsPage().getDepartingCheckedBagPriceText().get(i).getText(), (BAGS_INCLUDED));

        }

        pageObjectManager.getBagsPage().getContinueWithStandardBagsContainerContinueButton().click();

        //****************************Seats Page*****************************
        //Selecting standard seats
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEATS);

        //Validating seats price is 0.00
        ValidationUtil.validateTestStep("Verifying Seats Price is $0.00",
                pageObjectManager.getSeatsPage().getSeatsTotalPriceText().getText().contains(ZERO_SEAT_PRICE));

        //Validating only 2 passengers present on seats page
        ValidationUtil.validateTestStep("Verifying only 2 passengers can select seats",
                pageObjectManager.getSeatsPage().getPassengerListText().size() <= 2);

        //continue to Options page
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //*************************Options Page******************************
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //****************************Payment Page***************************
        pageMethodManager.getConfirmationPageMethods().verifyOptionSectionSelectedOptions(SELECTED_OPTION);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        /******************************************************************************
         ***********************Validation on Confirmation Page************************
         ******************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());

        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);

        pageMethodManager.getConfirmationPageMethods().verifyOptionSectionSelectedOptions(SELECTED_OPTION);
    }
}