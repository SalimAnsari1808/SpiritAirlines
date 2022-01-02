package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.*;

//**********************************************************************************************
//Not getting F+H on mentioned city pair.. so to complete TC, picked FLL-LAS
//Test Case ID: TC91127
//Description : Vacation Path_CP_Flight-Hotel_Booking adults only_No Car No Activities
//Created By : Kartik Chauhan
//Created On : 14-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 14-Aug-2019
//**********************************************************************************************

public class TC91127 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups={"BookPath", "DomesticDomestic", "FlightHotel", "Outside21Days","Adult","Guest","HotelsUI","CarsUI", "CarryOn", "CheckedBags","NoSeats", "OptionalUI", "Visa"})

    public void Vacation_Path_CP_Flight_Hotel_Booking_adults_only_No_Car_No_Activities(@Optional("NA") String platform) {

        /******************************************************************************
         ***********************Navigate to Passenger Info Page************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91127 under REGRESSION CRITICAL Suite on " + platform + " Browser", true);
        }
//STEP--1
        //Home Page Constant Values
        final String LANGUAGE               = "English";
        final String JOURNEY_TYPE           = "Vacation";
        final String TRIP_TYPE              = "Flight+Hotel";
        final String DEP_AIRPORTS           = "AllLocation";
        final String DEP_AIRPORT_CODE       = "FLL";
        final String ARR_AIRPORTS           = "AllLocation";
        final String ARR_AIRPORT_CODE       = "LAS";
        final String DEP_DATE               = "110";
        final String ARR_DATE               = "111";
        final String ADULTS                 = "8";
        final String CHILDS                 = "0";
        final String INFANT_LAP             = "0";
        final String INFANT_SEAT            = "0";
        final String HOTELROOM              = "1 Room";
        final String UPGRADE_VALUE          = "BookIt";

        //Bags Page Constant Values
        final String SELECT_BAGS            = "Carry_1|Checked_2";
        final String FARE_TYPE              = "Standard";

        //Options Page Constant Values
        final String OPTIONS_VALUE          = "CheckInOption:MobileFree";
        final String BACKGROUND_GRAY        = "rgba(226,226,226,1)";

        //Payment Page Constant Value
        final String CARD_TYPE              = "VisaCard";
        final String TRAVEL_GUARD           = "NotRequired";

        //Confirmation Page Constant Values
        final String BOOKING_STATUS         = "Confirmed";

        //open browser
        openBrowser(platform);
//STEP--2
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
//STEP--3
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
//**************STEP-4 and STEP--5(Steps are not for more than 3 months)**********
//STEP--6
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectHotelRoom(HOTELROOM);
        pageMethodManager.getHomePageMethods().clickSearchButton();
//**************STEP-7(Test case is not in same condition)**********
//STEP--8
        //************ All flights are preselected
//STEP--9
        //***************As per New functionality it will not displaying
//STEP--10
        //All Hotels Link is displaying on FA page
        ValidationUtil.validateTestStep("All Hotels Link is displaying on FA page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getAllHotelsButton()));
//STEP-11, 12 , 13 & 14
        //Complete the flight + Hotel page
        pageObjectManager.getHotelPage().getHotelViewButton().get(0).click();
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);
        String flighPlusHotelPrice = pageObjectManager.getHotelPage().getHotelWindowRoomPricesButton().getText().replace("ROOMS FROM " , "").trim();
        pageObjectManager.getHotelPage().getHotelPopUpSelectRoomsFromButton().click();
        pageObjectManager.getHotelPage().getHotelPopUpSelectRoomButton().get(0).click();
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getHotelPage().getVacationPathContinueButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);
//STEP--15
        //Continue without car
        pageObjectManager.getCarPage().getCarsPageContinueWithoutCarButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);

        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);

        //Constant Values to validate
        final String PASSENGER_URL             = "book/passenger";
//STEP 16 //*********Activities are not displaying
//Step 17
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Verify user navigated to Passenger Info page",
                getDriver().getCurrentUrl(), PASSENGER_URL);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
//STEP--18
        //Bags Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getBagsPageMethods().selectDepartingBags(SELECT_BAGS);
        pageMethodManager.getBagsPageMethods().selectReturnBags(SELECT_BAGS);
        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);
//STEP--19 & 20
        //Seats Page Methods
        //***********Currently Seat method is not working during package booking*****************
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
//STEP--21
        //Hotel is displaying as selected on Option Page
        ValidationUtil.validateTestStep("Hotel is displaying as selected on Option Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getSelectedHotelNameText()));
//STEP--22 & STEP-23
        //Verify Flight Flex is displaying as Greyed text block
        ValidationUtil.validateTestStep("Verify Flight Flex is displaying as Greyed text block",
                pageObjectManager.getOptionsPage().getFlightFlexCardGreyedPanel().getCssValue("background-color"),BACKGROUND_GRAY);

        //Select Option
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);

        //continue with purchase
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
//STEP--24 & STEP--25
        //Payment Page Methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        //******************RETURN TO HOME PAGE ERROR*******************************************
//STEP--26 & STEP--27
        //Confirmation Code
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
//STEP--28
        pageObjectManager.getReservationSummaryPage().getPrintReceiptButton().click();
//STEP--29
        //**********************SKYSPEED*********************************
    }
}
