package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

// **********************************************************************************************
// TestCase ID: TC90875
// TestCase : CP_CI_Validate Hazmat during check in path_Wireframe
// Created By : Kartik Chauhan
// Created On : 01-July-2019
// Reviewed By:
// Reviewed On:
// **********************************************************************************************
public class TC90875 extends BaseClass {

    @Parameters ({"platform"})
    @Test(groups = {"CheckIn" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest", "Connecting" , "BookIt" , "NoBags" , "NoSeats" , "CheckInOptions" , "MasterCard" , "ReservationUI","HazmatUI"})

    public void CP_CI_Validate_Hazmat_during_check_in_path_Wireframe (@Optional("NA")String platform){
        /******************************************************************************
         ****************************Navigate to Bags Page via FA user*****************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90875 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE               = "English";
        final String JOURNEY_TYPE           = "Flight";
        final String TRIP_TYPE 		    	= "RoundTrip";
        final String DEP_AIRPORTS 		    = "AllLocation";
        final String DEP_AIRPORT_CODE 	    = "FLL";
        final String ARR_AIRPORTS 	  	    = "AllLocation";
        final String ARR_AIRPORT_CODE  	    = "LAS";
        final String DEP_DATE 	    		= "0";
        final String ARR_DATE 	    		= "3";
        final String ADULTS 		       	= "1";
        final String CHILDREN		      	= "0";
        final String INFANT_LAP 	     	= "0";
        final String INFANT_SEAT	     	= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 	    	= "Connecting";
        final String ARR_Flight 		    = "Connecting";
        final String FARE_TYPE 	    		= "Standard";
        final String UPGRADE_VALUE 	  	    = "BookIt";

        //Options Page Constant Value
        final String OPTIONS_VALUE          = "CheckInOption:MobileFree";

        //Payment page Constant values
        final String TRAVEL_GUARD		    = "NotRequired";
        final String CARD_TYPE			    = "MasterCard";

        //Bag Page constant values
        final String CHECKIN_BAG_PURCHASE   = "DontPurchase";

        //Seat Page constant values
        final String CHECKIN_SEAT_PURCHASE  = "DontPurchase";

        //Car Page constant values
        final String CHECKIN_CAR_PURCHASE   = "DontPurchase";
//Step 2
        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats page methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options page methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment page methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //confirmation page
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        /******************************************************************************
         ***********************Validation to Confirmation Page************************
         ******************************************************************************/
        //declare constant used in validation
        final String BOOKING_STATUS = "CONFIRMED";
        final String CONFIRMATION_URL = "book/confirmation";

        //verify user is navigated to confirmation page
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page",
                getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText().trim().toUpperCase(),BOOKING_STATUS);

        //Store all the basic info
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //login to My Trip
        pageMethodManager.getHomePageMethods().loginToCheckIn();

        ValidationUtil.validateTestStep("Verify verbiage of Checkin and Get Boarding Pass button",
                pageObjectManager.getReservationSummaryPage().getCheckInAndGetBoardingPassButton().get(0).isDisplayed());

//STEP--4
        pageMethodManager.getReservationSummaryPageMethods().clickCheckInAndGetBoardingPass();

        //do not select Bag
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSaveOnBagsPopup(CHECKIN_BAG_PURCHASE);

        //do not select Seat
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSelectYourSeatPopup(CHECKIN_SEAT_PURCHASE);

        //do not select car
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseRentACarPopup(CHECKIN_CAR_PURCHASE);
////wait till 2 sec
//        WaitUtil.untilTimeCompleted(2000);
//
//        //Create web element for Nope I am Good verbiage
//        pageObjectManager.getReservationSummaryPage().getSaveOnBagsPopupNopeIAmGoodButton().click();
//
//        //wait till 2 sec
//        WaitUtil.untilTimeCompleted(2000);
//
//        //get random Seats
//        pageObjectManager.getReservationSummaryPage().getChooseYourSeatGetRandomSeatButton().click();
//
//        //wait till 2 sec
//        WaitUtil.untilTimeCompleted(2000);
//
//        //wait till page is load is complete
//        WaitUtil.untilPageLoadComplete(getDriver());
//
//        pageObjectManager.getReservationSummaryPage().getRentACarNoThanksButton().click();
//
//        //wait till 2 sec
//        WaitUtil.untilTimeCompleted(2000);

        //Check In Option method
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);
//STEP--5
        /******************************************************************************
         ***********************Validation to Hazmat Pop-up****************************
         ******************************************************************************/
        //validate Hazmat Pop-up is displaying
        ValidationUtil.validateTestStep("Hazmat Pop-up Header is displaying",
                pageObjectManager.getReservationSummaryPage().getHazardousMaterialPopupHeaderText().isDisplayed());
//Step--6
        //validate Hazmat Pop-up Sub-Header is displaying
        ValidationUtil.validateTestStep("Hazmat Pop-up Sub-Header is displaying",
                pageObjectManager.getReservationSummaryPage().getHazardousMaterialSubHeaderText().isDisplayed());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);
//Step--7
        //validate Hazmat Pop-up  Forbidden Item verbiage is displaying
        ValidationUtil.validateTestStep("Forbidden Item verbiage is displaying on Hazmat Pop-up",
                pageObjectManager.getReservationSummaryPage().getForbiddenItemText().isDisplayed());
//Step--8
        //validate Hazmat Pop-up, Explosive & Fireworks verbiage is displaying
        ValidationUtil.validateTestStep("Explosive & Fireworks verbiage is displaying on Hazmat Pop-up",
                pageObjectManager.getReservationSummaryPage().getExplosiveFireworksText().isDisplayed());
//Step--9
        //validate Hazmat Pop-up, Flammable liquid and Solid verbiage is displaying
        ValidationUtil.validateTestStep("Flammable liquid and Solid verbiage is displaying on Hazmat Pop-up",
                pageObjectManager.getReservationSummaryPage().getFlammableLiquidText().isDisplayed());
//Step--10
        //validate Hazmat Pop-up, Lighter, Refills, Torch etc.. is displaying
        ValidationUtil.validateTestStep("Lighter, Refills, Torch etc.. verbiage is displaying on Hazmat Pop-up",
                pageObjectManager.getReservationSummaryPage().getLighterRefillsText().isDisplayed());
//Step--11
        //validate Hazmat Pop-up, Alcohol above 140 proof is displaying
        ValidationUtil.validateTestStep("Alcohol above 140 proof... verbiage is displaying on Hazmat Pop-up",
                pageObjectManager.getReservationSummaryPage().getAlcoholAboveProofText().isDisplayed());
//Step--12
        //validate Hazmat Pop-up, Weapon and Self Defence.. is displaying
        ValidationUtil.validateTestStep("Weapon and Self Defence.. verbiage is displaying on Hazmat Pop-up",
                pageObjectManager.getReservationSummaryPage().getWeaponsSelfDefenceText().isDisplayed());
//Step--13
        //validate Hazmat Pop-up, Products under safety verbiage is displaying
        ValidationUtil.validateTestStep("Products under safety... verbiage is displaying on Hazmat Pop-up",
                pageObjectManager.getReservationSummaryPage().getProductUnderSafetyText().isDisplayed());
//Step--14
        //validate Hazmat Pop-up, compressed Gas is displaying
        ValidationUtil.validateTestStep("Compressed Gas verbiage is displaying on Hazmat Pop-up",
                pageObjectManager.getReservationSummaryPage().getCompressedGasText().isDisplayed());
//Step--15
        //validate Hazmat Pop-up, RadioActive Material verbiage is displaying
        ValidationUtil.validateTestStep("RadioActive Material verbiage is displaying on Hazmat Pop-up",
                pageObjectManager.getReservationSummaryPage().getRadioactiveMaterialsText().isDisplayed());
//Step--16
        //validate Hazmat Pop-up, Corrosive and Oxidiser verbiage is displaying
        ValidationUtil.validateTestStep("Corrosive and Oxidiser verbiage is displaying on Hazmat Pop-up",
                pageObjectManager.getReservationSummaryPage().getCorrosivesAndOxidizersText().isDisplayed());
//Step--17
        //validate Hazmat Pop-up, Mercury Barometer verbiage is displaying
        ValidationUtil.validateTestStep("Mercury Barometer verbiage is displaying on Hazmat Pop-up",
                pageObjectManager.getReservationSummaryPage().getMercuryBarometerText().isDisplayed());
//Step--18
        //validate Hazmat Pop-up, Poison verbiage is displaying
        ValidationUtil.validateTestStep("Poison verbiage is displaying on Hazmat Pop-up",
                pageObjectManager.getReservationSummaryPage().getPoisonText().isDisplayed());
//Step--19
        //validate Hazmat Pop-up, HoverBoards verbiage is displaying
        ValidationUtil.validateTestStep("HoverBoards verbiage is displaying on Hazmat Pop-up",
                pageObjectManager.getReservationSummaryPage().getHoverboardsText().isDisplayed());
//Step--20
        //validate Hazmat Pop-up, carry-on Items only verbiage is displaying
        ValidationUtil.validateTestStep("Carry-on Items only verbiage is displaying on Hazmat Pop-up",
                pageObjectManager.getReservationSummaryPage().getCarryOnItemsOnlyText().isDisplayed());
//Step--21
        //validate Hazmat Pop-up, Battery Powered Smoking Devices verbiage is displaying
        ValidationUtil.validateTestStep("Battery Powered Smoking Devices verbiage is displaying on Hazmat Pop-up",
                pageObjectManager.getReservationSummaryPage().getBatteryPoweredSmokingDeviceText().isDisplayed());
//Step--22
        //validate Hazmat Pop-up, Spare Lithium Batteries verbiage is displaying
        ValidationUtil.validateTestStep("Spare Lithium Batteries verbiage is displaying on Hazmat Pop-up",
                pageObjectManager.getReservationSummaryPage().getSpareLithiumBatteriesText().isDisplayed());
//Step--23
        //validate Hazmat Pop-up, Fuel Cells Devices verbiage is displaying
        ValidationUtil.validateTestStep("Fuel Cells Devices verbiage is displaying on Hazmat Pop-up",
                pageObjectManager.getReservationSummaryPage().getFuelCellsText().isDisplayed());
//Step--24
        //validate Hazmat Pop-up, Safety Matches and Lighters verbiage is displaying
        ValidationUtil.validateTestStep("Safety Matches and Lighters verbiage is displaying on Hazmat Pop-up",
                pageObjectManager.getReservationSummaryPage().getSafetyMatchesAndLightersText().isDisplayed());
//Step--25
        //validate Hazmat Pop-up, You Must Indicate... verbiage is displaying
        ValidationUtil.validateTestStep("You Must indicate.. verbiage is displaying on Hazmat Pop-up",
                pageObjectManager.getReservationSummaryPage().getYouMustIndicateText().isDisplayed());
//Step--26
        //validate Hazmat Pop-up, Cancel Button is displaying
        ValidationUtil.validateTestStep("Cancel Button is displaying on Hazmat Pop-up",
                pageObjectManager.getReservationSummaryPage().getHazardousMaterialPopupRejectBoardingPassButton().isDisplayed());
//Step--27
        //validate Hazmat Pop-up, Accept and Print Boarding Pass Button is displaying
        ValidationUtil.validateTestStep("Accept and Print Boarding Pass Button is displaying on Hazmat Pop-up",
                pageObjectManager.getReservationSummaryPage().getHazardousMaterialPopupAcceptBoardingPassButton().isDisplayed());

        //Click to cancel Hazardous pop-up
        pageObjectManager.getReservationSummaryPage().getHazardousMaterialPopupRejectBoardingPassButton().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //Verify Hazardous pop-up is not displaying after clicking on cancel Button
        ValidationUtil.validateTestStep("Hazardous material Pop-up is not displaying, after clicking on Cancel",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getHazardousMaterialPopupHeaderText()));
    }
}