package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC374018
//Description: Task 27827: TC374018- 002 - CP - Price Display Total Due - Hub Packaging - Flight with Hotel upsell
//Created By: Gabriela
//Created On: 22-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC374018 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "Outside21Days", "Adult", "Guest", "BookIt", "NoBags", "NoSeats", "OptionalUI", "CheckInOptions", "Hotels","PaymentUI","MasterCard", "ConfirmationUI"})
    public void CP_Price_Display_Total_Due_Hub_Packaging_Flight_with_Hotel_upsell(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC374018 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "132";
        final String ARR_DATE           = "133";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 	    = "NonStop";
        final String ARR_Flight 		= "NonStop";
        final String FARE_TYPE 	    	= "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page Constant Values
        final String FLIGHT_TEXT        = "Flight";
        final String FLIGHT_HOTEL_TEXT  = "Flight + Hotel";
        final String CAROUSEL_TITLE     = "Hotels for Less";
        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

        //Payment Page Constant Value
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_GUARD       = "NotRequired";

//- Step 1: Access GoldFinger testing environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 2: Create RT DOM-DOM | 1 ADT | 3 months out
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 3: Select first available flights and select continue at the bottom of the page
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);

//- Step 4: Select Book it
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//- Step 5: Enter info for all pax
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Step 6 & 7: Continue without bags & I don't need bags
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//- Step 8: Continue without selecting seats
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//- Step 9: On Options page locate the dynamic shopping cart on the right and select the drop down arrow
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1000);

//- Step 10: Verify flight details is only displaying
        ValidationUtil.validateTestStep("Validating Flight only is displayed on the Dynamic Shopping Cart",
                pageObjectManager.getHeader().getFlightItineraryText().getText(),FLIGHT_TEXT);

//- Step 11: Verify hotel carousel is displaying on Options page
        ValidationUtil.validateTestStep("Validating right Hotel's carousel title verbiage",
                pageObjectManager.getHotelPage().getHotelCarouselTitleText().getText(),CAROUSEL_TITLE);

        ValidationUtil.validateTestStep("Validating the hotel carousel is displayed",
                TestUtil.verifyElementDisplayed(getDriver().findElement(By.xpath("//app-multi-carousel"))));

//- Step 12: Before selecting a hotel, you must check HBG to make sure the hotel will be refundable with no cancellation fees.
        //For MCO city we are using any Universal hotel available

//- Step 13: Select ROOMS FROM $XXX.XX
        pageObjectManager.getHotelPage().getViewAllHotelsButton().click();

//- Step 14: Select room
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("Universal","NA");

//- Step 15: Verify there is a remove selection in the hotel box
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating 'Remove' button is displayed on the hotel box",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelCardRemoveLink()));

//- Step 16: Verify the Options section does not display hotel separately in drop down
        //Invalid Step. Validating Flight+Hotel on Dynamic Shopping Cart instead
        //Opening Dynamic Shopping Cart
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1000);

        ValidationUtil.validateTestStep("Validating Flight + Hotel is displayed on the Dynamic Shopping Cart",
                pageObjectManager.getHeader().getFlightItineraryText().getText(),FLIGHT_HOTEL_TEXT);

//- Step 17: Verify Flight Flex is not grayed out
        ValidationUtil.validateTestStep("Validating Flight Flex options is available",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getFlightFlexCardAddButton()));

//- Step 18: Select I'll check in at Spirit.com/Mobile for free and continue
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//- Step 19: Complete booking
        //Hotel validation on payment page method need it
//        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
//        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
//        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
//
////- Step 20: Verify total paid is displaying as packaging item and that options doesn't display anything
//        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
//
//        //Hotel validation on Confirmation page method need it
//        WaitUtil.untilPageLoadComplete(getDriver());
//        pageObjectManager.getConfirmationPage().getTotalPaidBreakDownLink().click();
//        WaitUtil.untilTimeCompleted(1000);
//
//        ValidationUtil.validateTestStep("Validating total paid is displayed as a package",
//                pageObjectManager.getConfirmationPage().getFlightVerbiageLabel().getText(),FLIGHT_HOTEL_TEXT);
//
//        pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
    }
}

/************** XPATH Hotel Page ***************/
//*******************************************************************
//********Hotel Card Container Options Page**************************
//*******************************************************************
//Remove button
//public final String xpath_HotelCardRemoveLinkButton = "//app-hotel//app-selected-ancillary-item//button[contains(text(),'Remove') or contains(text(),'Quitar')]";
//    @FindBy(xpath=xpath_HotelCardRemoveLinkButton)
//    private WebElement lnkbtn_HotelCardRemove;

//public WebElement getHotelCardRemoveLink() {return lnkbtn_HotelCardRemove;}