package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//*********************************************************************************************
//Test Case ID: TC374020
//Description: Task 27850: TC374020- 004 - CP - Price Display Total Due - Vacation Path - Flight + Hotel with Car upsell
//Created By: Gabriela
//Created On: 25-Nov-2019
//Reviewed By: Anthony Cardona
//Reviewed On: 06-Dec-2019
//**********************************************************************************************
public class TC374020 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "Outside21Days","FlightHotel", "Adult", "Guest", "Cars", "BookIt", "NoBags", "NoSeats", "OptionalUI","CheckInOptions", "Discover", "ConfirmationUI"})
    public void CP_Price_Display_Total_Due_Vacation_Path_Flight_Hotel_with_Car_upsell(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC374020 under GoldFinger Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "132";
        final String ARR_DATE           = "133";
        final String ADULT              = "2";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String F_H_URL            = "/book/flights-hotels";
        final String F_C_URL           = "/book/options/cars";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Constant Values
        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

        //Payment Page Constant values
        final String TRAVEL_GUARD 		= "NotRequired";
        final String CARD_DETAIL 		= "DiscoverCard4";
        final String F_H_CL_TEXT        = "FLIGHT + HOTEL + CAR";

//- Step 1: Access GoldFinger testing environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        //*** Home Page **/
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 2: Create a vacation booking DOM-DOM | F+H | 2 ADT | 1 room | 3 months out
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Verify user land on F + H Page", getDriver().getCurrentUrl(),F_H_URL);

//- Step 3: Before selecting a hotel, you must check HBG to make sure the hotel will be refundable with no cancellation fees.
//- Step 4: Select ROOMS FROM $XXX.XX
//- Step 5: Select room
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();
       pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("Universal","NA");

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Verify user land on F + C Page", getDriver().getCurrentUrl(),F_C_URL);

//- Step 6: Select ADD CAR on any car
        pageMethodManager.getCarPageMethods().selectCarOnCarPage("NA","NA");

//- Step 7: Select Book It
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//- Step 8: Enter all pax information and select continue
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().selectPrimaryDriver();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Step 9 & 10: Continue without bags & I don't need bags
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//- Step 11: Continue without selecting seats
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//- Step 12: Verify on the options drop down that car is not listed
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getOptionsPage().getOptionTotalContainerAmountTotalText().click();
        WaitUtil.untilTimeCompleted(1000);
        ValidationUtil.validateTestStep("Verifying Car cost is not displayed within Option Total breakdown",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getOptionTotalCarBreakdownPriceText()));

//- Step 13: Verify Flight Flex is not grayed out
        ValidationUtil.validateTestStep("Verifying Flight Flex option is available",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getFlightFlexCardAddButton()));

//- Step 14: Select I'll check in at Spirit.com/Mobile for free and continue
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//- Step 15: Complete booking
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
        try {
            pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
            //- Step 16: Verify total paid is displaying as packaging item and that options doesn't display anything
            pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
            pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

            //Hotel validation on Confirmation page method need it
            WaitUtil.untilPageLoadComplete(getDriver());
            pageObjectManager.getConfirmationPage().getTotalPaidBreakDownLink().click();
            WaitUtil.untilTimeCompleted(1000);

            ValidationUtil.validateTestStep("Validating total paid is displayed as a package",
                    pageObjectManager.getConfirmationPage().getFlightVerbiageLabel().getText(), F_H_CL_TEXT);

            pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
            pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
        }
        catch (Exception fail)
        {
            pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
            pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
        }
        catch(AssertionError fail)
        {
            pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
            pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
        }
    }
}