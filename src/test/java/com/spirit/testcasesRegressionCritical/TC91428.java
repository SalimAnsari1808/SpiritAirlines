package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

//**********************************************************************************************
//Test Case ID: TC91428
//Test Case Name: TTask 24679: 35348 CP_BP_Payment Page_Itinerary_RT_Next Day_Validate EDIT Seats link
//Created By: Gabriela
//Created On: 5-Jul-2019
//Reviewed By: Salim Ansari
//Reviewed On: 8-Jul-2019
//**********************************************************************************************

public class TC91428 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "NonStop" , "BookIt" , "NoBags" , "Standard" , "BigFrontSeat" , "CheckInOptions" , "MasterCard" , "PaymentUI"})
    public void CP_BP_Payment_Page_Itinerary_RT_Next_Day_Validate_EDIT_Seats_link(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91428 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "ORD";
        final String DEP_DATE           = "0";
        final String ARR_DATE           = "1";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Seats Page Constant Values
        final String DEP_SEAT           = "Standard";
        final String RET_SEAT           = "Standard";
        final String DEP_BFS            = "BigFront";
        final String RET_BFS            = "BigFront";


        //Options Page Constant Value
        final String OPTION_VALUE 	    = "CheckInOption:MobileFree";

        //Payment page constant values
        final String PAYMENT_URL        = "/book/payment";
        final String ARRIVAL_TEXT       = "(Next Day)";
        final String HEADER             = "SEATS";
        final String SEAT_URL           = "/book/seats";
        final String SELECTED_DEP_SEAT  = "BIG FRONT SEAT";
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //Confirmation Page Constant value
        final String BOOKING_STATUS     = "Confirmed";
        final String CONFIRMATION_URL   = "book/confirmation";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//-- Step 1:
        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", DEP_FLIGHT);

        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getSeletedDepatingFlightNatureButton().get(0).click();

        WaitUtil.untilPageLoadComplete(getDriver());
        String DEP_CITY_INFO = pageObjectManager.getFlightAvailabilityPage().getStopsPopUpDepartureAirportsText().get(0).getText();
        String RET_CITY_INFO= pageObjectManager.getFlightAvailabilityPage().getStopsPopUpArrivalAirportsText().get(0).getText();

        pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Customer Info Page Method
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags page
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats Page
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEAT);
        pageMethodManager.getSeatsPageMethods().selectReturningSeats(RET_SEAT);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //Option Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//-- Step 2
        /******************************************************************************
         ***************************Navigate to Payment Page********************
         ******************************************************************************/
        ValidationUtil.validateTestStep("Validating Payment Page  is on the right URL",
                getDriver().getCurrentUrl(),PAYMENT_URL);

//-- Step 3:
        //Within Your Itinerary content block, verify your booking details are reflecting what was selected
        ValidationUtil.validateTestStep("Validating Departure City info",
                pageObjectManager.getPaymentPage().getDepartingFlightCityNameText().get(0).getText(),DEP_CITY_INFO);

        ValidationUtil.validateTestStep("Validating Arrival city info",
                pageObjectManager.getPaymentPage().getArriveFlightCityNameText().get(0).getText(), RET_CITY_INFO);

        ValidationUtil.validateTestStep("Validating Next Day displayed on departure arrival time info",
                pageObjectManager.getPaymentPage().getArriveFlightCityNameText().get(0).getText(),ARRIVAL_TEXT);

//-- Step 4:
        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();

        WaitUtil.untilTimeCompleted(1000);
        ValidationUtil.validateTestStep("Validating Seats section is the third line",
                pageObjectManager.getPaymentPage().getTotalDueBreakDownText().get(2).getText(),HEADER);

//-- Step 5:
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getPaymentPage().getTotalDueSeatsEditText().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating Seats Page is on the right URL", getDriver().getCurrentUrl(),SEAT_URL);

        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_BFS);
        pageMethodManager.getSeatsPageMethods().selectReturningSeats(RET_BFS);

        List<String> actualSeatsList = new ArrayList<>();
        for (int count = 0; count < pageObjectManager.getSeatsPage().getPassengerDetailListLink().size(); count++)
        {
            pageObjectManager.getSeatsPage().getPassengerDetailListLink().get(count).click();
            actualSeatsList.add(pageObjectManager.getSeatsPage().getPassengerSeatText().get(count).getText());
        }
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //Options Page Methods
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment Page Methods
//-- Step 6:
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getPaymentPage().getYourItineraryPassengerInfoArrowIcon().click();

        List<WebElement> seatsVerify = pageObjectManager.getPaymentPage().getYourItineraryPassengerInfoSeatNumberText();

        for (int expectedSeatCount = 0; expectedSeatCount < seatsVerify.size(); expectedSeatCount ++)
        {
            for (int actualSeatCount = 0; actualSeatCount < actualSeatsList.size(); actualSeatCount ++)
            {
                ValidationUtil.validateTestStep("Validating BFS was added properly on the journey",
                        seatsVerify.get(expectedSeatCount).getText(),actualSeatsList.get(actualSeatCount));
            }
        }

//-- Step 7:
        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();
        WaitUtil.untilTimeCompleted(1000);

//-- Step 8:
        pageObjectManager.getPaymentPage().getTotalDueSeatsChevronLink().click();
        WaitUtil.untilTimeCompleted(1000);

        List<WebElement> bFS = pageObjectManager.getPaymentPage().getTotalDueSeatsTypeText();
        for (int count = 0; count < bFS.size(); count ++)
        {
            ValidationUtil.validateTestStep("Validating BFS are displayed on the total due drop down",
                    bFS.get(count).getText(),SELECTED_DEP_SEAT);
        }

//-- Step 9:
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page",
                getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
    }
}