package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import com.spirit.utility.JSExecuteUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC369563
//Test Name: E2E_FS_OW DOM 1 ADT 2 INFT 1 lap 1 seat_SW ChangePAX to 1 INFT (LAP) 1 child +2 Bundle It [Tier 3] Fare Direct Flight_STD_OW 1CO 1CB_1 Free Seat_No Extras_CI Web_Credit Card
//Description: Validating "New Search" button on Flight Availability page and "Duplicates Passenger Names Found" pop up is displayed on Passenger information page
//Created By : Gabriela
//Created On : 25-APR-2019
//Reviewed By: Salim Ansari
//Reviewed On: 26-APR-2019
//**********************************************************************************************
public class TC369563 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups =  {"BookPath" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" ,"Child", "InfantLap" , "FreeSpirit" ,"FlightAvailabilityUI",
                    "NonStop" , "BundleIt" , "NewFlightSearch" , "CarryOn" , "CheckedBags" ,"BagsUI","SeatsUI", "Standard" , "TravelInsurancePopUp" , "Visa"} )
    public void E2E_FS_OW_DOM_1_ADT_2_INFT_1_lap_1_seat_SW_ChangePAX_to_1_INFT_LAP_1_child_2_Bundle_It_Tier_3_Fare_Direct_Flight_STD_OW_1CO_1CB_1_Free_Seat_No_Extras_CI_Web_Credit_Card(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC369563 under SMOKE Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE               ="English";
        final String LOGIN_ACCOUNT          ="FSEmail";
        final String JOURNEY_TYPE           ="Flight";
        final String TRIP_TYPE              ="OneWay";
        final String DEP_AIRPORTS           ="AllLocation";
        final String DEP_AIRPORT_CODE       ="LGA";
        final String ARR_AIRPORTS           ="AllLocation";
        final String ARR_AIRPORT_CODE       ="MYR";
        final String DEP_DATE               ="5";
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

        //Bags Page Constant Values
        final String BAGS_PRESELECTED 	    = "1";
        final String BAGS_INCLUDED		    = "Included";
        final String DEP_BAGS               = "Carry_1|Checked_5||Carry_1|Checked_5";

        //Seats Page Constant Values
        final String DEP_SEATS			    = "Standard|Standard";
        final String ZERO_SEAT_PRICE 	    = "$0.00";

        //Payment Page Constant Values
        final String CARD_TYPE              = "VisaCard";
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

       /* //Selecting 1 infant on lap and 1 infant with chair
        int ChildInfantDOB = pageObjectManager.getHomePage().getChildTravelerPanel().size();
        int ChildCounter = 2;

        //get pax details from global variables
        int intChildCount = Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_INFANTLAP_COUNT).toString());

        // Verifying Infant amount
        for (int DateBoxCounter = 0; DateBoxCounter < ChildInfantDOB; DateBoxCounter++) {

            //get the DOB box available
            WebElement travellingChildSection = pageObjectManager.getHomePage().getChildTravelerPanel().get(DateBoxCounter);

            //Selecting Infant on lap
            if (intChildCount < ChildCounter) {
                //convert date in required format
                travellingChildSection.findElement(By.tagName("input")).sendKeys(TestUtil.getStringDateFormat((-630 - ChildCounter), "MM/dd/yyyy"));

                // Selecting Infant with seat
                travellingChildSection.click();
                if (intChildCount < ChildCounter) {
                    //convert date in required format
                    travellingChildSection.findElement(By.tagName("input")).sendKeys(TestUtil.getStringDateFormat((-728 - ChildCounter), "MM/dd/yyyy"));

                    travellingChildSection.click();

                    pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

                    //break loop
                    break;
                }
            }

        }*/

        //******************Flight Availability Page*************************
        WaitUtil.untilPageLoadComplete(getDriver());
        //New Search button is selected and passengers changes
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS1, CHILD1, INFANT_SEAT1, INFANT_LAP1);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        /*// Verifying Infant amount
        int ChildCounter1 = 1;
        for (int DateBoxCounter = 0; DateBoxCounter < ChildInfantDOB; DateBoxCounter++) {

            //get the DOB box available
            WebElement travellingChildSection = pageObjectManager.getHomePage().getChildTravelerPanel().get(DateBoxCounter);

            //Selecting Infant on lap
            if (intChildCount < ChildCounter1) {
                //convert date in required format
                travellingChildSection.findElement(By.tagName("input")).sendKeys(TestUtil.getStringDateFormat((-630 - ChildCounter), "MM/dd/yyyy"));

                travellingChildSection.click();

                //break loop
                break;

            }
        }*/
        //Selecting child > 2 years
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        //Selecting Standard Flight fares
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);

        //************************Upgrade and Save Pop Up********************
        //Verifying Bundle saving is higher than Boost it
        WaitUtil.untilPageLoadComplete(getDriver());

        //Converting Bundle Saving from string to double
        String BundleSaveUpto = pageObjectManager.getFlightAvailabilityPage().getBundleItSaveUpToPriceText().getText();
        String BundleSaveUptoSub = BundleSaveUpto.replace("Save Up To $", "");
        double BundleSaveDouble = Double.parseDouble(BundleSaveUptoSub);

        //Converting Bundle Saving from string to double
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
                pageObjectManager.getBagsPage().getDepartureCityText().getText(),DEP_AIRPORT_CODE);

        ValidationUtil.validateTestStep("Validating arriving city is correct",
                pageObjectManager.getBagsPage().getDepartureCityText().getText(),ARR_AIRPORT_CODE);

        //Verifying there only 2 passengers present on Bags Page
        ValidationUtil.validateTestStep("Validating only 2 passengers can select bags",
                pageObjectManager.getBagsPage().getDepartingPassengerFlightContainerListText().size() <= 2);

        //ValidationUtil.validateTestStep("Verify 1-Carry-On and 1-Checked Bags are Pre-Selected for all passengers on Bags Page
        for(int i = 0; i < pageObjectManager.getBagsPage().getDepartingCarryOnBagCounterTextBox().size(); i ++) {
            // Verifying Carry-on is preselected
            ValidationUtil.validateTestStep("Verifying Carry On is preselected",
                    JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingCarryOnBagCounterTextBox().get(i)),BAGS_PRESELECTED);

            //Verifying Carry On price is included
            ValidationUtil.validateTestStep("Verifying Carry On price is included",
                    pageObjectManager.getBagsPage().getDepartingCarryOnPriceText().get(i).getText(),BAGS_INCLUDED);

            // Verifying Checked Bag is preselected
            ValidationUtil.validateTestStep("Verifying 1 checked bag is preselected",
                    JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getBagsPage().getDepartingCheckedBagCounterTextBox().get(i)),BAGS_PRESELECTED);

            //Verifying Checked Bag price is included
            ValidationUtil.validateTestStep("Verifying Checked Bag price is included",
                    pageObjectManager.getBagsPage().getDepartingCheckedBagPriceText().get(i).getText(),BAGS_INCLUDED);

        }

        //Selecting Max Checked Bags
       pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);

        //Verifying price is not 0.00 after increase bags

        for(int i = 0; i < pageObjectManager.getBagsPage().getDepartingCarryOnBagCounterTextBox().size(); i ++) {
            //Verifying Checked Bag price is included
            ValidationUtil.validateTestStep("Verifying Checked Bag price changed",
                    !pageObjectManager.getBagsPage().getDepartingCheckedBagPriceText().get(i).getText().equals(BAGS_INCLUDED));
        }

        //Verifying there is $9FC saving
        ValidationUtil.validateTestStep("Validating Saving 9FC is displayed",
                pageObjectManager.getBagsPage().getContinueWith9FCBagsContainerSavingsText().isDisplayed());

        //Verifying the 9FC price is cheapest than the standard price
        //Converting 9FC bag price from string to double
        String MemberPrice = pageObjectManager.getBagsPage().getContinueWith9FCBagsContainerPriceText().getText();
        String MemberPriceSub = MemberPrice.replace("$", "");
        double Member = Double.parseDouble(MemberPriceSub);

        //Converting Standard bag price from sting to double
        String StandardPrice = pageObjectManager.getBagsPage().getContinueWithStandardBagsContainerPriceText().getText();
        String StandardPriceSub = StandardPrice.replace("$","");
        double Standard = Double.parseDouble(StandardPriceSub);

        //Validation 9FC < Standard price
        ValidationUtil.validateTestStep("Verifying 9FC bag price is cheapest than the standard price",
                Member < Standard);

        //Selecting Continue Without Bags button
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getBagsPage().getContinueWithOutChangesButton().get(0).click();

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
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //*******************************************************************
        //***********************Confirmation Page***************************
        //*******************************************************************
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);


    }
}