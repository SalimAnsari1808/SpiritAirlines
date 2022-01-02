package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;

//**********************************************************************************************
//Test Case ID: TC91078
//Title       : Reservation Summary_MT_Modify Flight with Flight Flex
//Description : Validate that the user is not charged change fee when they have selected Flight Flex on Booking Path
//Created By  : Anthony Cardona
//Created On  : 10-Apr-2019
//Reviewed By : Salim Ansari
//Reviewed On : 22-Apr-2019
//**********************************************************************************************

public class TC91078 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"MyTrips" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "NonStop" , "BookIt" ,
            "NoBags" , "NoSeats" , "FlightFlex" , "CheckInOptions" , "MasterCard" , "ChangeFlight","FlightAvailabilityUI","PaymentUI"})
    public void Reservation_Summary_MT_Modify_Flight_with_Flight_Flex(@Optional("NA") String platform) {
        /******************************************************************************
         ******************Navigate to Manage Travel Payment Page**********************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91078 under SMOKE Suite on " + platform + " Browser", true);
        }

//Step 1
        final String LANGUAGE                   = "English";
        final String JOURNEY_TYPE               = "Flight";
        final String TRIP_TYPE                  = "Oneway";
        final String DEP_AIRPORTS               = "AllLocation";
        final String DEP_AIRPORT_CODE           = "FLL";
        final String ARR_AIRPORTS               = "AllLocation";
        final String ARR_AIRPORT_CODE           = "LGA";
        final String ARR_AIRPORT_CODE1          = "LAX";
        final String DEP_DATE                   = "3";
        final String ARR_DATE                   = "NA";
        final String ADULTS                     = "1";
        final String CHILDREN                   = "0";
        final String INFANT_LAP                 = "0";
        final String INFANT_SEAT                = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		        = "nonstop";
        final String FARE_TYPE                  = "Standard";
        final String UPGRADE_VALUE              = "BookIt";
        final String JOURNEY                    = "Dep";

        //Options page constant Values
        final String OPTIONS_VALUE              = "FlightFlex|CheckInOption:MobileFree";

        //Payment page constant Values
        final String CARD_TYPE                  = "MasterCard";
        final String TRAVEL_GUARD               = "Notrequired";

        //My Trip Flight Avaliability constant value
        final String MYTRIP_CONTIFLIGHT_POPUP   = "Continue";
        final String MYTRIP_CANCELFLIGHT_POPUP  = "Cancel";
        final String MYTRIPS_FLIGHT_URL         = "my-trips/flights";

        //My Trip Reservation Summary Constant Values
        final String MYTRIP_DEP_FLIGHT          = "connecting";
        final String MYTRIP_FLIGHT_FARE_TYPE    = "Standard";
        final String MYTRIP_FLIGHT_PRICE        = "Costly";
        final String MYTRIP_BAG_POPUP           = "DontPurchase";
        final String MYTRIP_SEAT_POPUP          = "DontPurchase";

        //MyTrip Confirmation page constant values
        final String MYTRIP_CONFIRMATION        = "my-trips/confirmation";

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
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType(JOURNEY, DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seat Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Option Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //payment page Methods
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();


        //confirmation page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

//Step 2 and 3
        //login to my trip on Home page
        pageMethodManager.getHomePageMethods().loginToMyTrip();
//Step 4
        //Click on Change FLight Button
        pageObjectManager.getReservationSummaryPage().getFlightSectionChangeFlightButton().click();
        WaitUtil.untilTimeCompleted(1500); //wait for pop-up

//Step 5
        //click on Edit button
        pageObjectManager.getReservationSummaryPage().getChangeFlightPopupDepEditLabel().click();
        WaitUtil.untilTimeCompleted(1000);

//Step 6
        //validate that the Origin and destination
        ValidationUtil.validateTestStep("Verify Departure city drop down is visible on Change Flight Popup on Reservation Summary page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getChangeFlightPopupDepFromCityDropDown()));

        ValidationUtil.validateTestStep("Verify Return city drop down is visible on Change Flight Popup on Reservation Summary page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getChangeFlightPopupDepToCityDropDown()));

//Step 7
        //validate that the Calendar is displayed on the pop-up
        pageObjectManager.getReservationSummaryPage().getChangeFlightPopupDepDateTextBox().click();

        ValidationUtil.validateTestStep("The Calendar is displayed on the Change Flight pop-up on Reservation Summary Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getCalenderPanel()));

//Step 8
        pageMethodManager.getReservationSummaryPageMethods().continueCancelOnChangeFlightPopup(MYTRIP_CANCELFLIGHT_POPUP);

        pageMethodManager.getReservationSummaryPageMethods().changeFlightOnChangeFlightPopup("Dep",DEP_AIRPORT_CODE,ARR_AIRPORT_CODE1,"NA");

        pageMethodManager.getReservationSummaryPageMethods().continueCancelOnChangeFlightPopup(MYTRIP_CONTIFLIGHT_POPUP);

//Step 9
        ///validate that the user is taken to the My Trips flight page
        ValidationUtil.validateTestStep("The user is correctly taken to the Manage Travel flights page" , getDriver().getCurrentUrl(),MYTRIPS_FLIGHT_URL);

//Step 10
        /******************************************************************************
         ******************Validation on Manage Travel Payment Page********************
         ******************************************************************************/
        //Validate that the user sees the following text
        final String FLIGHT_FLEX_COVERAGE1 = "You’re covered with Flight Flex! Modification charges of up to" ; //some price amount($###.## will be displayed which is dynamic) so we must split the string in 2
        final String FLIGHT_FLEX_COVERAGE2 = "dollars per person are waived for this change (fare difference applies).";

        ValidationUtil.validateTestStep(FLIGHT_FLEX_COVERAGE1+" text on the Manage Travel FA page",
                pageObjectManager.getFlightAvailabilityPage().getCoveredWithFlightFlexVerbiageText().getText().trim(),FLIGHT_FLEX_COVERAGE1);

        ValidationUtil.validateTestStep(FLIGHT_FLEX_COVERAGE2+" text on the Manage Travel FA page",
                pageObjectManager.getFlightAvailabilityPage().getCoveredWithFlightFlexVerbiageText().getText().trim(),FLIGHT_FLEX_COVERAGE2);

//Step 11
        //change flight to a connecting flight so it is more expensive than the original flight
        //pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType(JOURNEY, MYTRIP_DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightCheapCostlyType("Dep",MYTRIP_FLIGHT_FARE_TYPE, MYTRIP_FLIGHT_PRICE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(MYTRIP_FLIGHT_FARE_TYPE);

        //bags upsell, no
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnBagsPopup(MYTRIP_BAG_POPUP);

        //seats upsell, no
        //pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnSeatsPopup(MYTRIP_SEAT_POPUP);
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        WaitUtil.untilPageLoadComplete(getDriver());

//Step 12

        //scroll down Total Due
        JSExecuteUtil.scrollDownToElementVisible(getDriver(),pageObjectManager.getPaymentPage().getTotalDueChevronLink());

        //open breakdown
        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();
        WaitUtil.untilTimeCompleted(1000);

        //Booking that has flight flex should not be charged change fee when they change the booking
        ValidationUtil.validateTestStep("The change fee is not displayed on the Payment page" ,
                !TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getTotalDueChangeFeesPriceText()));

//Step 13
        //complete booking
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        //wait till page load is comoplete
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for confirmation page
        WaitUtil.untilPageURLVisible(getDriver(),MYTRIP_CONFIRMATION);

        //validate confirmation url
        ValidationUtil.validateTestStep("Verify user reached to My Trip Confirmation Page",
                getDriver().getCurrentUrl(),MYTRIP_CONFIRMATION);

    }
}