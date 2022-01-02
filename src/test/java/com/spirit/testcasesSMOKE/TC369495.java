package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
@Listeners(com.spirit.testNG.Listener.class)
// **********************************************************************************************
// TestCase :TC369495
// Description: CP_CI_Hazmat_Wireframe
// Created By : Kartik Chauhan
// Created On : 22-April-2019
// Reviewed By: Salim Ansari
// Reviewed On: 22-April-2019
// **********************************************************************************************
public class TC369495 extends BaseClass {

    @Parameters ({"platform"})
    @Test(groups = {"CheckIn","RoundTrip","DomesticDomestic","WithIn7Days","Adult","Guest","Connecting","BookIt",
            "NoBags","NoSeats", "CheckInOptions","TravelInsuranceBlock","MasterCard","HazmatUI"})
    public void CP_CI_Hazmat_Wireframe (@Optional("NA")String platform){
        //mention the browser
        if(!platform.equals("NA")){
            ValidationUtil.validateTestStep("Starting Test Case ID TC369495 under SMOKE suite on " + platform +" Browser", true);
        }
        /******************************************************************************
         ****************************Navigate to Confirmation Page*****************
         ******************************************************************************/
        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "RoundTrip";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LAS";
        final String DEP_DATE 			= "0";
        final String ARR_DATE 			= "1";
        final String ADULTS				= "1";
        final String CHILD				= "0";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String SORTING_TYPE		= "Departure";
        final String DEP_FLIGHT			= "Connecting";
        final String ARR_Flight			= "Connecting";
        final String FARE_TYPE			= "standard";
        final String UPGRADE_TYPE		= "BookIt";

        //Option Page Constant Values
        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

        //Payment page Constant values
        final String TRAVEL_GUARD		= "Required";
        final String CARD_TYPE			= "MasterCard";

        //Confirmation Page Constant value
        final String BOOKING_STATUS 	= "Confirmed";

//Step1
        //open browser
        openBrowser( platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE,ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT,INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectSortingOption("Dep", SORTING_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_TYPE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //wait till page is load is complete
        WaitUtil.untilTimeCompleted(2000);

        //Seat Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Option method
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment Page Method
        //pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        //Confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page", pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText().contains(BOOKING_STATUS));
//Step--2
        /*********************************************Start OF CheckIn Path**********************/
        //login to checkIn Path
        pageMethodManager.getHomePageMethods().loginToCheckIn();

        //wait till page is load is complete
        WaitUtil.untilPageLoadComplete(getDriver());
//Step--3
        //Check-In and Boarding Pass Button is displaying
        ValidationUtil.validateTestStep("Check-In and Boarding Pass Button is displaying at the bottom center of the page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getCheckInAndGetBoardingPassButton().get(0)));
//Step--4
        //Click on Check In Boarding Pass
        pageMethodManager.getReservationSummaryPageMethods().clickCheckInAndGetBoardingPass();

        //wait till page is load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //CLick on Nope i'M good Button
        pageObjectManager.getReservationSummaryPage().getSaveOnBagsPopupNopeIAmGoodButton().click();

        //wait till page is load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);
//Step--4
        //Seat page during check-in path
        pageObjectManager.getReservationSummaryPage().getChooseYourSeatGetRandomSeatButton().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //Do not Purchase Seat
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseRentACarPopup("DontPurchase");

        //Check In Option method
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup("NotRequired");

        //wait for 3 sec
        WaitUtil.untilTimeCompleted(3000);

//Step--5
        /******************************************************************************
         ***********************Validation to Hazmat Pop-up****************************
         ******************************************************************************/
        //validate Hazmat Pop-up is displaying
        ValidationUtil.validateTestStep("Hazmat Pop-up Header is displaying",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getHazardousMaterialPopupHeaderText()));
//Step--6
        //validate Hazmat Pop-up Sub-Header is displaying
        ValidationUtil.validateTestStep("Hazmat Pop-up Sub-Header is displaying",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getHazardousMaterialSubHeaderText()));

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);
//Step--7
        //validate Hazmat Pop-up  Forbidden Item verbiage is displaying
        ValidationUtil.validateTestStep("Forbidden Item verbiage is displaying on Hazmat Pop-up",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getForbiddenItemText()));
//Step--8
        //validate Hazmat Pop-up, Explosive & Fireworks verbiage is displaying
        ValidationUtil.validateTestStep("Explosive & Fireworks verbiage is displaying on Hazmat Pop-up",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getExplosiveFireworksText()));
//Step--9
        //validate Hazmat Pop-up, Flammable liquid and Solid verbiage is displaying
        ValidationUtil.validateTestStep("Flammable liquid and Solid verbiage is displaying on Hazmat Pop-up",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getFlammableLiquidText()));
//Step--10
        //validate Hazmat Pop-up, Lighter, Refills, Torch etc.. is displaying
        ValidationUtil.validateTestStep("Lighter, Refills, Torch etc.. verbiage is displaying on Hazmat Pop-up",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getLighterRefillsText()));
//Step--11
        //validate Hazmat Pop-up, Alcohol above 140 proof is displaying
        ValidationUtil.validateTestStep("Alcohol above 140 proof... verbiage is displaying on Hazmat Pop-up",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getAlcoholAboveProofText()));
//Step--12
        //validate Hazmat Pop-up, Weapon and Self Defence.. is displaying
        ValidationUtil.validateTestStep("Weapon and Self Defence.. verbiage is displaying on Hazmat Pop-up",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getWeaponsSelfDefenceText()));
//Step--13
        //validate Hazmat Pop-up, Products under safety verbiage is displaying
        ValidationUtil.validateTestStep("Products under safety... verbiage is displaying on Hazmat Pop-up",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getProductUnderSafetyText()));
//Step--14
        //validate Hazmat Pop-up, compressed Gas is displaying
        ValidationUtil.validateTestStep("Compressed Gas verbiage is displaying on Hazmat Pop-up",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getCompressedGasText()));
//Step--15
        //validate Hazmat Pop-up, RadioActive Material verbiage is displaying
        ValidationUtil.validateTestStep("RadioActive Material verbiage is displaying on Hazmat Pop-up",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getRadioactiveMaterialsText()));
//Step--16
        //validate Hazmat Pop-up, Corrossive and Oxidiser verbiage is displaying
        ValidationUtil.validateTestStep("Corrossive and Oxidiser verbiage is displaying on Hazmat Pop-up",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getCorrosivesAndOxidizersText()));
//Step--17
        //validate Hazmat Pop-up, Mercury Barometer verbiage is displaying
        ValidationUtil.validateTestStep("Mercury Barometer verbiage is displaying on Hazmat Pop-up",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getMercuryBarometerText()));
//Step--18
        //validate Hazmat Pop-up, Poison verbiage is displaying
        ValidationUtil.validateTestStep("Poison verbiage is displaying on Hazmat Pop-up",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getPoisonText()));
//Step--19
        //validate Hazmat Pop-up, HoverBoards verbiage is displaying
        ValidationUtil.validateTestStep("HoverBoards verbiage is displaying on Hazmat Pop-up",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getHoverboardsText()));
//Step--20
        //validate Hazmat Pop-up, carry-on Items only verbiage is displaying
        ValidationUtil.validateTestStep("Carry-on Items only verbiage is displaying on Hazmat Pop-up",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getCarryOnItemsOnlyText()));
//Step--21
        //validate Hazmat Pop-up, Battery Powered Smoking Devices verbiage is displaying
        ValidationUtil.validateTestStep("Battery Powered Smoking Devices verbiage is displaying on Hazmat Pop-up",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getBatteryPoweredSmokingDeviceText()));
//Step--22
        //validate Hazmat Pop-up, Spare Lithium Batteries verbiage is displaying
        ValidationUtil.validateTestStep("Spare Lithium Batteries verbiage is displaying on Hazmat Pop-up",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getSpareLithiumBatteriesText()));
//Step--23
        //validate Hazmat Pop-up, Fuel Cells Devices verbiage is displaying
        ValidationUtil.validateTestStep("Fuel Cells Devices verbiage is displaying on Hazmat Pop-up",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getFuelCellsText()));
//Step--24
        //validate Hazmat Pop-up, Safety Matches and Lighters verbiage is displaying
        ValidationUtil.validateTestStep("Safety Matches and Lighters verbiage is displaying on Hazmat Pop-up",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getSafetyMatchesAndLightersText()));
//Step--25
        //validate Hazmat Pop-up, You Must Indicate... verbiage is displaying
        ValidationUtil.validateTestStep("You Must indicate.. verbiage is displaying on Hazmat Pop-up",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getYouMustIndicateText()));
//Step--26
        //validate Hazmat Pop-up, Cancel Button is displaying
        ValidationUtil.validateTestStep("Cancel Button is displaying on Hazmat Pop-up",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getHazardousMaterialPopupRejectBoardingPassButton()));
//Step--27
        //validate Hazmat Pop-up, Accept and Print Boarding Pass Button is displaying
        ValidationUtil.validateTestStep("Accept and Print Boarding Pass Button is displaying on Hazmat Pop-up",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getHazardousMaterialPopupAcceptBoardingPassButton()));

        //Click to cancel Hazardous pop-up
        pageObjectManager.getReservationSummaryPage().getHazardousMaterialPopupRejectBoardingPassButton().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //Verify Hazardous pop-up is not displaying after clicking on cancel Button
        ValidationUtil.validateTestStep("Hazardous material Pop-up is not displaying, after clicking on Cancel",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getHazardousMaterialPopupHeaderText()));
    }

}

