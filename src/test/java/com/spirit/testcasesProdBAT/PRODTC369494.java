package com.spirit.testcasesProdBAT;

import com.spirit.baseClass.*;
import com.spirit.enums.*;
import com.spirit.utility.*;import org.testng.annotations.*;


//**********************************************************************************************
//Test Case ID: TC369494
//Description: Flight Availability_CP_BP_Logged FS_RT_Selecting 9DFC and STD flights_Bare Fare
//Created By : Sunny Sok
//Created On : 18-Apr-2019
//Reviewed By: Salim Ansari
//Reviewed On: 22-Apr-2019
//**********************************************************************************************
public class PRODTC369494 extends BaseClass {
    @Parameters ({"platform"})
    @Test(groups="Production")

    public void Flight_Availability_CP_BP_Logged_FS_RT_Selecting_9DFC_and_STD_flights_Bare_Fare(@Optional("NA")String platform) {
        /******************************************************************************
         ************************Navigation to Payments Page***************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID PROTC369494 under PRODUCTION Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "RoundTrip";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "BWI";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LAS";
        final String DEP_DATE 			= "25";
        final String ARR_DATE 			= "30";
        final String ADULTS				= "1";
        final String CHILDS				= "0";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "9DFC";
        final String ARR_Flight 		= "NonStop";
        final String FARE_TYPE			= "Member";
        final String UPGRADE_FARE		= "BookIt";

        //Bags Page constant values
        final String DEP_BAGS 			= "Carry_1|Checked_1";
        final String RET_BAGS			= "Carry_1|Checked_1";

        //Option page Constant Values
        final String OPTION_VALUE 		= "CheckInOption:MobileFree";

        //Payment page constant values
        final String TRAVEL_GUARD 		= "NotRequired";
        final String PAYMENT_CARD 		= "MasterCard";

        //Confirmation Page Constant value
        final String BOOKING_STATUS     = "Confirmed";

        //open browser
        openBrowser( platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

        //click on footer Member enrollment link
        pageObjectManager.getFooter().getFreeSpiritLink().click();

        //Enrollment Page methods
        pageMethodManager.getMemberEnrollmentPageMethods().createNewFSMember();
        pageObjectManager.getHeader().getSpiritLogoImage().click();

        //wait for page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //Home Page Methods
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightFareType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);

        //Verify $9 Fare Club Fare and Standard Fare tiles are present at the bottom of the page.
        ValidationUtil.validateTestStep("User validate $9 Fare Club tile is present at the bottom of the page.",
                TestUtil.verifyElementDisplayed(pageObjectManager.getFlightAvailabilityPage().getFareContainerPanel().get(0)));

        ValidationUtil.validateTestStep("User validate Standard Fare tile is present at the bottom of the page.",
                TestUtil.verifyElementDisplayed(pageObjectManager.getFlightAvailabilityPage().getFareContainerPanel().get(1)));

        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_FARE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectReturnBags(RET_BAGS);
        pageMethodManager.getBagsPageMethods().continueWithSelectingBags();

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /******************************************************************************
         ************************Validation on Payments Page***************************
         ******************************************************************************/
        //declare constant used in Validation
        final String PAYMENT_URL 		= "/book/payment";
        final String $9FC_PRICE 		= "$59.95";
        final String CONFIRMATION_URL   = "book/confirmation";

        //wait till url appear on web
        WaitUtil.untilPageURLVisible(getDriver(), PAYMENT_URL);

        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        //validate flight price
        ValidationUtil.validateTestStep("Verify Flight price is 9DFC price on Payment Page",
                pageObjectManager.getPaymentPage().getTotalDueFlightPriceText().getText().trim().replace("$", ""),scenarioContext.getContext(Context.AVAILABILITY_9DFC_TOTAL_PRICE).toString());

        //validate bags price
        ValidationUtil.validateTestStep("Verify Bags price on Payment Page",
                pageObjectManager.getPaymentPage().getTotalDueBagsPriceText().getText().trim().replace("$", ""),scenarioContext.getContext(Context.BAGS_TOTAL_PRICE).toString());

        //validate $9fc charge
        ValidationUtil.validateTestStep("Verify user is being charged 9fc",
                pageObjectManager.getPaymentPage().getTotalDueOptionsPriceText().getText(),$9FC_PRICE);

        //Reach to Confirmation page
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(PAYMENT_CARD);
//        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
//
//        WaitUtil.untilPageURLVisible(getDriver(), CONFIRMATION_URL);
//
//        //verify user on Reservation summary OPage
//        ValidationUtil.validateTestStep("Verify user navigated to Confirmation Page,",
//                getDriver().getCurrentUrl(),CONFIRMATION_URL);
//
//        //Verifying booking is confirmed
//        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
//                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
    }
}
