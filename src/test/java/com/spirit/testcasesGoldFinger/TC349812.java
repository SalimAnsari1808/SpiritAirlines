package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.Optional;
import org.testng.annotations.*;


//**********************************************************************************************
//TODO:[IN:15876] - CP: BP: Options page:  Cars or Hotels sections are not displayed for an International destination (CUN)
//Test Case ID: TC349812
//Description :  CP - Flight Only - Complete RT INT booking + Hotel Upsell using pricing slider filter to find hotels within price range
//Created By  : Sunny Sok
//Created On  : 21-Nov-2019
//Reviewed By : Gabriela
//Reviewed On : 5-Dec-2019
//**********************************************************************************************

public class TC349812 extends BaseClass{
    @Parameters({"platform"})
    @Test (groups = {"ActiveBug", "BookPath" , "RoundTrip" , "DomesticInternational" , "Outside21Days" , "Adult" , "NonStop" , "BookIt" ,
            "NoBags" , "NoSeats" ,"Hotels","HotelsUI","OptionalUI", "CheckInOptions" , "MasterCard" , "PaymentUI"})

    public void CP_Flight_Only_Complete_RT_INT_booking_Hotel_Upsell_using_pricing_slider_filter_to_find_hotels_within_price_range(@Optional("NA") String platform) {
        //**********************Navigate to Options Page********************************/
        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC349812 under GoldFinger Suite on " + platform + " Browser" , true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "RoundTrip";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "CUN";
        final String DEP_DATE 			= "98";
        final String ARR_DATE 			= "100";
        final String ADULTS				= "2";
        final String CHILD 				= "0";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		= "Nonstop";
        final String RET_FLIGHT 		= "Nonstop";
        final String FARE_TYPE			= "Standard";
        final String UPGRADE_FARE		= "BookIt";

        //Options Page constant values
        final String OPTION_VALUE 	    = "CheckInOption:MobileFree";

        //Payment Page constant values
        final String TRAVEL_GUARD       = "NotRequired";
        final String CARD_DETAILS       = "MasterCard";

        //open browser
        openBrowser( platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_FARE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //wait until Bags page appear on web
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats page method
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();



        //*********************Validation options Page**************************************/
        //Constant Values to validate
        final String OPTIONS_URL        = "/options";
        final String HOTELS_URL         = "/hotels";
        final String PAYMENTS_URL       = "/payment";

        ValidationUtil.validateTestStep("User verify Navigated to Options page", getDriver().getCurrentUrl(),OPTIONS_URL);

        //step 4 -locate the Hotel for Less section and click on "VIEW ALL HOTELS" link
        JSExecuteUtil.scrollDownToElementVisible(getDriver(),pageObjectManager.getHotelPage().getHotelCarouselTitleText());
        pageObjectManager.getHotelPage().getViewAllHotelsButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("User verify Navigated to Hotels page", getDriver().getCurrentUrl(),HOTELS_URL);

        /**TODO: //step5 - Once on the Hotel Page, look for a hotel in the price range (between middle and max price)
         * slider is broken
         */

        //Step 6- Select Hotel and continue with the booking.
         pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("Fiesta Americana Condesa" , "NA");

        //Step 7 - Verify the Hotel selection and details are displaying on the Options page
        ValidationUtil.validateTestStep("User verify Navigated to Options page", getDriver().getCurrentUrl(),OPTIONS_URL);
        pageMethodManager.getHotelPageMethods().verifySelectedHotelOptionPage();

        //Step 8- verify the hotel selected on hotels page is selected on options page
        pageMethodManager.getHotelPageMethods().verifySelectedHotelOptionPage();

        //Validate REMOVE button is displayed
        ValidationUtil.validateTestStep("User verify Remove button is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelRemoveButton()));

        //Step 9- Click the remove button
        pageObjectManager.getHotelPage().getHotelRemoveButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());

        //validate display reverts to the carousel view
        ValidationUtil.validateTestStep("User verify carousel view is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelPanel()));

        //Step 10 - Make a new Hotel selection, this time from the carousel view.
        pageMethodManager.getHotelPageMethods().selectHotelOnOptionPage("U by Grand Fiesta Americana","NA");

        //step 11 -Select a Check-in option and continue to the next page
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("User verify Navigated to Payments page",
                getDriver().getCurrentUrl(),PAYMENTS_URL);

        // Step 12
        //Verify the new Hotel selection and details are displaying on the Payment page
        pageMethodManager.getPaymentPageMethods().verifyHotelSectionDetails();

        //step 13- Proceed with payment and record PNR
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAILS);
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);

        //TODO: Booking without complete due exceed $1000-$1500
//        try {
//            pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
//
//            //Confirmation Page Methods
//            pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
//            pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
//
//            //step 14- Validate the Hotel selection and details are displaying on the Confirmation Page
//            //step 15- Validate the Hotel selection and details are displaying on the Confirmation Page
//            pageMethodManager.getConfirmationPageMethods().verifyHotelSectionDetails();
//
//            //Step 16: Go to Skyspeed and validate the booking details are accurate
//            //Unable to automate this step
//
//            //step 17 - Gather the booking information [PNR] and send it for cancellation.Ask your lead who can cancel package PNRs
//            pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
//        }catch (AssertionError fail){
//            pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
//            ValidationUtil.validateTestStep("Test case failed after Payment: " + fail.getMessage() , false );
//        }
//        catch (Exception fail)
//        {
//            pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
//
//            ValidationUtil.validateTestStep("Test case failed after Payment: " + fail.getMessage() , false );
//        }
    }

}