package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.*;
import com.spirit.dataType.PassengerInfoData;
import com.spirit.enums.Context;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.*;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

//**********************************************************************************************
//Test Case ID: TC91539
//Description : CP_MT_Payment Page_9DFC_use fraudulent CC
//Created By : Sunny Sok
//Created On : 08-JUL-2019
//Reviewed By: Salim Ansari
//Reviewed On: 17-JUL-2019
//**********************************************************************************************
public class TC91539 extends BaseClass{

    @Parameters({"platform"})
    @Test(groups = {"OutOfExecution","BookPath" , "OneWay" , "DomesticDomestic" , "Outside21Days" , "Adult" , "NineDFC"
            , "NonStop" , "BookIt" , "PassengerInfoSSR" , "CarryOn" , "CheckedBags" , "Standard" ,"ShortCutBoarding",
            "CheckInOptions","TravelInsuranceBlock", "MasterCard" , "PaymentUI"})
    public void CP_MT_Payment_Page_9DFC_use_fraudulent_CC(@Optional("NA") String platform) {

        //**********************Navigate to Payment Page*******************************
        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91539 under REGRESSION-CRITICAL Suite on " + platform + " Browser" , true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String LOGIN_EMAIL 		= "NineDFCEmail";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "OneWay";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LGA";
        final String DEP_DATE 			= "25";
        final String ARR_DATE 			= "NA";
        final String ADULTS				= "1";
        final String CHILDS				= "0";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		= "Nonstop";
        final String FARE_TYPE			= "Standard";
        final String UPGRADE_FARE		= "BookIt";

        //Passenger Info Page Constant Values
        final String SSR                = "HearingImpaired";
        final String SSR_INFO           = "Hearing Impaired";

        //Bags Page constant values
        final String DEP_BAGS           = "Carry_1|Checked_5";

        //Seat Page Constant Value
        final String DEP_SEATS          = "Standard";

        //Options Page constant values
        final String OPTION_VALUE 	    = "CheckInOption:MobileFree";

        //Payment Page Constant values
        final String TRAVEL_GUARD 	    = "Required";
        final String INVALID_CARD_TYPE 	= "HotCard";
        final String VALID_CARD_TYPE    = "MasterCard";

        //reservation page constant value
        final String RESERVATION_URL    = "my-trips/reservation-summary";

        //open browser
        openBrowser( platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_EMAIL);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);

        //Recording Departing Journey Information
        pageObjectManager.getFlightAvailabilityPage().getSeletedDepatingFlightNatureButton().get(0).click();
        WaitUtil.untilTimeCompleted(2500);

        List<String> layoverInfo    = new ArrayList<>();
        for (int i = 0; i < pageObjectManager.getFlightAvailabilityPage().getStopsPopUpLayoverTimeText().size(); i++) {
            layoverInfo.add(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpLayoverTimeText().get(i).getText().replace(" LAYOVER", ""));
        }

        //Declaring Lists to store flight info
        WaitUtil.untilTimeCompleted(1000);
        List<String> depCityName    = new ArrayList<>();
        List<String> arrCityName    = new ArrayList<>();
        List<String> depTime        = new ArrayList<>();
        List<String> arrTime        = new ArrayList<>();
        List<String> nkInfo         = new ArrayList<>();

        //Storing Flight duration
        String durationInfo = pageObjectManager.getFlightAvailabilityPage().getStopsPopUpFlightDurationTimeText().get(0).getText();

        //Storing Departure Cities Name for 1st journey
        depCityName.add(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpDepartureAirportsText().get(0).getText().trim());

        //Storing Arrival Cities Name for 1st journey
        arrCityName.add(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpArrivalAirportsText().get(0).getText().trim());

        //Storing Departure Cities Time for 1st journey
        depTime.add(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpDepartureTimeText().get(0).getText().trim());

        //Storing Arrival Cities Time for 1st journey
        arrTime.add(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpArrivalTimeText().get(0).getText().trim());

        //Storing NK Number for 1st journey
        nkInfo.add(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpFlightsNumberText().get(0).getText().replace("Flight ","").replace(" ",""));


        //Closing Flight Info Pop Up
        pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();
        WaitUtil.untilTimeCompleted(1000);


        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_FARE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().selectSSRPerPassenger(SSR);
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //wait until Bags page appear on web
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
        //Seats page method
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEATS);
        List<WebElement> seatsSelect = pageObjectManager.getSeatsPage().getPassengerSeatText();
        List<String> seatsList = new ArrayList<>();

        for (int count = 0; count < seatsSelect.size(); count ++) {
            seatsList.add(seatsSelect.get(count).getText());
        }
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //options
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment Page Method
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(VALID_CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        //Confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        pageMethodManager.getHomePageMethods().loginToMyTrip();

        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("User verify Navigated to Payment page", getDriver().getCurrentUrl(),RESERVATION_URL);

        pageObjectManager.getReservationSummaryPage().getPassengerSectionAddBagsButton().get(0).click();

        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        //get bag price
        scenarioContext.setContext(Context.BAGS_TOTAL_PRICE, pageObjectManager.getBagsPage().getBagsTotalContainerAmountTotalText().getText());
        pageObjectManager.getBagsPage().getContinueWithBagsButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());

        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
        //*********************Validation Payment Page*********************************

        //Constant Values to validate
        final String PAYMENT_URL        = "my-trips/payment";
        final String FRAUD_CARD_ERROR   = "Were sorry, an unknown error has occurred. Your card has not been charged. Please check that all payment information below matches what is printed on your card (including cardholder name) or try another card.";

        ValidationUtil.validateTestStep("User verify Navigated to Payment page", getDriver().getCurrentUrl(),PAYMENT_URL);

        //Storing flight info for validation
        List<String> depCityNamePaymentNew = getDisplayedText(pageObjectManager.getPaymentPage().getDepartingFlightCityNameText());
        List<String> arrCityNamePaymentNew = getDisplayedText(pageObjectManager.getPaymentPage().getArriveFlightCityNameText());

        //Validating departing flight info
        ValidationUtil.validateTestStep("Validating Cities and Time ", depCityNamePaymentNew.get(0), depCityName.get(0) + " " + depTime.get(0));

        //Validating Arrival info displayed
        ValidationUtil.validateTestStep("Validating Cities and Time Arriving", arrCityNamePaymentNew.get(0), arrCityName.get(0) + " " + arrTime.get(0));

        //Click on Flight details chevron
        pageObjectManager.getPaymentPage().getYourItineraryArrowIcon().get(0).click();
        WaitUtil.untilTimeCompleted(1000);

        //Storing Flight NUmber information foe validation
        List<String> nk = getDisplayedText(pageObjectManager.getPaymentPage().getFlightSectionFlightNumberText());
        ValidationUtil.validateTestStep("Validating NK time right information", nk.get(0),nkInfo.get(0));

        ValidationUtil.validateTestStep("Validating right Duration ifo is displayed ",
                pageObjectManager.getPaymentPage().getDurationTimeText().get(0).getText(),durationInfo);

        pageObjectManager.getPaymentPage().getYourItineraryPassengerInfoArrowIcon().click();

        final int FIRST_INDEX = 0;
        final int SECOND_INDEX = 1;
        String depAirportCode = scenarioContext.getContext(Context.HOMEPAGE_DEP_AIRPORT).toString().split("\\(")[SECOND_INDEX].split("\\)")[FIRST_INDEX];
        String retAirportCode = scenarioContext.getContext(Context.HOMEPAGE_ARR_AIRPORT).toString().split("\\(")[SECOND_INDEX].split("\\)")[FIRST_INDEX];

        for (WebElement bagsInfo: pageObjectManager.getPaymentPage().getAllBagsInfoText()) {
            ValidationUtil.validateTestStep("Validating right Bags section displayed",
                    bagsInfo.getText().trim(),depAirportCode + "-" + retAirportCode + ":  1 Carry-On , 5 Checked Bag".toUpperCase());
        }

        for (WebElement seatInfo:pageObjectManager.getPaymentPage().getYourItineraryPassengerInfoSeatNumberText()) {
            ValidationUtil.validateTestStep("Validating City Pair format for Seats is correct",
                    depAirportCode + "-" + retAirportCode + ": " + seatInfo.getText().toUpperCase(), seatInfo.getText().toUpperCase());
        }

        ValidationUtil.validateTestStep("Right SSR information displayed",
                pageObjectManager.getPaymentPage().getAdditionalInfoValueText().get(1).getText(), SSR_INFO.toUpperCase());

        //verify passenger first and lastname
        PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("NineFCMember");
        ValidationUtil.validateTestStep("Verify passenger name on itinerary section", pageObjectManager.getPaymentPage().getPassengerNameText().get(0).getText(),passengerInfoData.firstName + " " + passengerInfoData.lastName);

        //Verify Verify Bags total amount is on payment page
        JSExecuteUtil.scrollDownToElementVisible(getDriver(),pageObjectManager.getPaymentPage().getTotalDuePriceText());
        ValidationUtil.validateTestStep("Verify Bags total amount is on payment page", pageObjectManager.getPaymentPage().getTotalDuePriceText().getText(),scenarioContext.getContext(Context.BAGS_TOTAL_PRICE).toString());

        //fill invalid card details
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPaymentPage().getSelectCardDropDown(),"Use Another Card");
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(INVALID_CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        WaitUtil.untilPageLoadComplete(getDriver());

        String cardError = pageObjectManager.getCommon().getIBlockVerbiageText().getText();
        cardError = cardError.replaceAll("[^\\p{ASCII}]", "");
        ValidationUtil.validateTestStep("User Verify error message for Hot Card", cardError,FRAUD_CARD_ERROR);

        pageObjectManager.getCommon().getIBlockCloseButton().click();
        WaitUtil.untilTimeCompleted(1000);

        //clear invalid card information
        WaitUtil.untilTimeCompleted(2000);

        //clear invalid card information
        pageObjectManager.getMemberEnrollmentPage().getCardNumberTextBox().clear();
        pageObjectManager.getMemberEnrollmentPage().getAccountHolderNameTextBox().clear();

        //enter valid card information
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(VALID_CARD_TYPE);
        pageObjectManager.getPaymentPage().getBookTripButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());

        //confirmation page
        final String BOOKING_STATUS     = "Confirmed";
        final String CONFIRMATION_URL   = "my-trips/reservation-summary";

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page", getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page", pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
    }

    private List<String> getDisplayedText(List<WebElement> elementList){

        ArrayList<String> arrayList = new ArrayList<>();//empty

        for (WebElement element:elementList) {
            if(element.isDisplayed()){

                arrayList.add(element.getText().trim());
            }
        }
        return arrayList;
    }
}