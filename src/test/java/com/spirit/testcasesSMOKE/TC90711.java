package com.spirit.testcasesSMOKE;


import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.MemberEnrollmentData;
import com.spirit.dataType.PassengerInfoData;
import com.spirit.enums.Context;
import com.spirit.managers.FileReaderManager;
import com.spirit.pageObjects.PassengerInfoPage;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

//@Listeners(com.spirit.testNG.Listener.class)

//**********************************************************************************************
//Test Case ID: TC90711
//Title: CP_MT_Non-U.S Residents_outside 24HRs
//Description: Select max bags for departing flight and ensure the return trip matches the number
//             of bags selected after "Keep the same bags for all flights" checkbox is selected
//Created By : Anthony Cardona
//Created On : 01-Apr-2019
//Reviewed By: Salim Ansari
//Reviewed On: 08-Apr-2019
//**********************************************************************************************
public class TC90711 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"MyTrips","OneWay","DomesticDomestic","Outside21Days","Adult","NonStop","BookIt",
            "ContactInformation","CheckedBags", "Standard","CheckInOptions","MasterCard","PaymentUI","AddEditSeats"})
    public void CP_MT_Non_US_Residents_outside_24HRs(@Optional("NA") String platform) {
        //mention the browser
        if(!platform.equals("NA")){
            ValidationUtil.validateTestStep("Starting Test Case ID TC90711 under SMOKE suite on " + platform +" Browser", true);
        }

        //Steps 1
        //******************************************************************************
        //****************************Navigate to Bags Page*****************************
        //******************************************************************************/
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "Oneway";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LGA";
        final String DEP_DATE           = "25";
        final String ARR_DATE           = "NA";
        final String ADULTS             = "1";
        final String CHILDREN           = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		= "nonstop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Passenger Information Page
        final String NON_US_RESIDENT_COUNTRY = "Colombia, Republic of";

        //Bags Page constant values
        final String DEP_BAGS 			= "Carry_0|Checked_1";
        final String BAGS_FARE          = "Standard";

        //Options page constant Values
        final String CHECKIN_OPTION     = "CheckInOption:MobileFree";

        //payment page constant value
        final String CARD_TYPE          = "MasterCard";

        //Reservation Page common methods
        final String MYTRIP_SEAT        = "Seats";

        //My Trip Common Methods
        final String MYTRIP_DEP_SEAT    = "Standard";

        //My Trip

        //open browser
        openBrowser(platform);

//Step 1
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
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactNonUSResident();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Pagec
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectBagsFare(BAGS_FARE);
        WaitUtil.untilPageLoadComplete(getDriver());

        //Seats Page methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options page methods
        pageMethodManager.getOptionsPageMethods().selectOptions(CHECKIN_OPTION);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//Step 2
        //validate that travel guard is not available
        ValidationUtil.validateTestStep("Travel Guard Section is not displayed on Payment Page",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getYesTravelGuardLabel()));

        //Payment page Methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        /**Non Us Residents do not receive Rokt**/
        //Confirmation Page methods
//        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

//Step 3, 4
        //Home Page common Method
        pageMethodManager.getHomePageMethods().loginToMyTrip();

//Step 5
        //click on add seat and select any available seat
        pageMethodManager.getReservationSummaryPageMethods().buyBagsSeatsPassengerSection(MYTRIP_SEAT);
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(MYTRIP_DEP_SEAT);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //handle the Bags pop-up, Dont purchase Bags
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnBagsPopup("DontPurchase");

        //Do not select any extras
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//Step 6
        //validate that travel guard is not available
        ValidationUtil.validateTestStep("Travel Guard Section is not displayed on My Trip Path payment page",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getYesTravelGuardLabel()));

        //validate the seats on the breakdown
        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //verify seat price on payment page
        ValidationUtil.validateTestStep("The BFS price correctly reflects on the Seats breakdown on My Trip Payment Page",
                pageObjectManager.getPaymentPage().getTotalDueSeatsPriceText().getText().contains(scenarioContext.getContext(Context.SEATS_TOTAL_PRICE).toString()));

    }
}
