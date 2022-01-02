package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.*;
import com.spirit.enums.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

//**********************************************************************************************
//Test Case ID: TC90732
//Description : CP_MT_Payment Page_Passenger_Info
//Created By : Alex Rodriguez
//Created On : 17-JUL-2019
//Reviewed By: Salim Ansari
//Reviewed On: 22-JUL-2019
//**********************************************************************************************

public class TC90732 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"MyTrips" , "OneWay" , "DomesticDomestic" , "Outside21Days" , "Adult" , "FreeSpirit" , "NonStop" , "BookIt" , "PassengerInfoSSR" , "CarryOn" , "CheckedBags" , "Standard" ,"ShortCutBoarding", "CheckInOptions" ,"TravelInsuranceBlock" , "MasterCard" ,"AddEditBags", "PaymentUI"})
    public void CP_MT_Payment_Page_Passenger_Info(@Optional("NA") String platform) {

        /******************************************************************************
         ***********************Navigate to Payment Page*******************************
         ******************************************************************************/

        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90732 under REGRESSION-CRITICAL Suite on " + platform + " Browser" , true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			    = "English";
        final String LOGIN_EMAIL 		    = "FSEmail";
        final String JOURNEY_TYPE 		    = "Flight";
        final String TRIP_TYPE 			    = "OneWay";
        final String DEP_AIRPORTS 		    = "AllLocation";
        final String DEP_AIRPORT_CODE       = "FLL";
        final String ARR_AIRPORTS 		    = "AllLocation";
        final String ARR_AIRPORT_CODE       = "LGA";
        final String DEP_DATE 			    = "25";
        final String ARR_DATE 			    = "NA";
        final String ADULTS                 = "3";
        final String CHILDREN               = "0";
        final String INFANT_LAP             = "0";
        final String INFANT_SEAT            = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT             = "NonStop";
        final String FARE_TYPE              = "Standard";
        final String UPGRADE_FARE           = "BookIt";

        //Passenger Info Page Constant Values
        final String SSR                    = "HearingImpaired";
        final String SSR_INFO               = "Hearing Impaired";

        //Bags Page Constant Values
        final String DEP_BAGS               = "Carry_1|Checked_1||Carry_1|Checked_1||Carry_1|Checked_1";

        //Seats Page Constant Values
        final String DEP_SEATS              = "Standard|Standard|Standard";

        //Options Page Constant Value
        final String OPTIONS_VALUE          = "CheckInOption:MobileFree";

        //Payment Page Constant values
        final String TRAVEL_GUARD 		    = "Required";
        final String VALID_CARD_TYPE        = "MasterCard";

        //Seats pop up
        final String SEATS                  ="DontPurchase";

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
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_FARE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();

        // Pax 1 info
        List<String> passTitle      = new ArrayList<>();
        List<String> passFirstName  = new ArrayList<>();
        List<String> passLastName   = new ArrayList<>();

        for (int count = 0; count < pageObjectManager.getPassengerInfoPage().getAdultTitleListDropDown().size(); count++) {
            passTitle.add(pageObjectManager.getPassengerInfoPage().getAdultTitleListDropDown().get(count).getAttribute("value"));
        }

        for (int count = 0; count < pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().size(); count ++) {
            passFirstName.add(pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(count).getAttribute("value"));
        }

        for (int count = 0; count < pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().size();count ++) {
            passLastName.add(pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(count).getAttribute("value"));
        }

        List<WebElement> ktnNumber = pageObjectManager.getPassengerInfoPage().getAdultKTNListTextBox();
        List<String> ktnMatch = new ArrayList<>();
        String KTN = "123654";
        for (int count = 0; count < ktnNumber.size(); count++) {
            ktnNumber.get(count).sendKeys(KTN + count);
            ktnMatch.add(ktnNumber.get(count).getAttribute("value"));
        }

        pageMethodManager.getPassengerInfoMethods().selectSSRPerPassenger(SSR);
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats page method
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEATS);

        List<WebElement> seatsSelect = pageObjectManager.getSeatsPage().getPassengerSeatText();
        List<String> seatsList = new ArrayList<>();

        for (int count = 0; count < seatsSelect.size(); count ++) {
            seatsList.add(seatsSelect.get(count).getText());
        }

        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //Options page methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment Page Method
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(VALID_CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        //Confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //Retrieve PNR through My Trips
        pageMethodManager.getHomePageMethods().loginToMyTrip();

        pageMethodManager.getReservationSummaryPageMethods().buyBagsSeatsPassengerSection("Bags");
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
//Payment page constant value
        final String PAYMENT_URL        = "/my-trips/payment";
        final String FREE_SPIRIT_TEXT   = "FREE SPIRIT#:";
        final String KTN_TEXT           = "Known Traveler # (TSA Pre  ";
        final String ADD_INFO           = "ADDITIONAL INFO:";

        /****************************************************************************
         * *********************Payment Page Validations ****************************
         ****************************************************************************/
//-- Step 1:
        WaitUtil.untilPageLoadComplete(getDriver());
        //URL Validation
        ValidationUtil.validateTestStep("Validating Payment Page URL",
                getDriver().getCurrentUrl(), PAYMENT_URL);

//-- Step 2:
        List<WebElement> passengerName = pageObjectManager.getPaymentPage().getPassengerNameText();
        for (int count = 0; count < passengerName.size(); count++) {
            ValidationUtil.validateTestStep("Validating Passenger image is displayed for each passenger in the booking",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getPassengerIcon().get(count)));
        }

//-- Step 3:

//-- Step 4:
        for (int count = 0; count< pageObjectManager.getPaymentPage().getPassengerNameText().size(); count ++) {
            ValidationUtil.validateTestStep("Validating Passenger Info displayed properly",
                    pageObjectManager.getPaymentPage().getPassengerNameText().get(count).getText(), passTitle.get(count) + ". " + passFirstName.get(count) + " " + passLastName.get(count));
        }

//-- Step 5
        pageObjectManager.getPaymentPage().getYourItineraryPassengerInfoArrowIcon().click();

//-- Step 6
        WaitUtil.untilTimeCompleted(3000);
        for (WebElement fsNumberText : pageObjectManager.getPaymentPage().getFreeSpiritText()) {
            if (fsNumberText.isDisplayed()) {
                ValidationUtil.validateTestStep("Validating FREE SPIRIT#: text is displayed",
                        fsNumberText.getText(), FREE_SPIRIT_TEXT);
            }
        }

        List<WebElement> fsnums = pageObjectManager.getPaymentPage().getFreeSpiritText();
        String FSNUM = fsnums.get(3).getText().substring(fsnums.get(3).getText().indexOf(":") + 2).trim();
        ValidationUtil.validateTestStep("Validating FS# length is 9 or 10",
                FSNUM.length() == 9 || FSNUM.length() == 10);

//-- Step 7:
        for (WebElement ktnText : pageObjectManager.getPaymentPage().getKnownTravelerText()) {
            if (ktnText.isDisplayed()) {
                ValidationUtil.validateTestStep("Validating TSA number text is displayed",
                        ktnText.getText(), KTN_TEXT);
            }
        }

        List<WebElement> ktnNum =  pageObjectManager.getPaymentPage().getKnownTravelerText();
        List<String> tsaNumber = new ArrayList<>();
        for (int count = 3; count < ktnNum.size(); count++) {
            String TSA = ktnNum.get(count).getText().substring(ktnNum.get(count).getText().indexOf(":") + 1).trim();
            tsaNumber.add(TSA);

        }

        for (int count = 0; count < tsaNumber.size(); count++) {
            ValidationUtil.validateTestStep("Validating KTN matching",
                    tsaNumber.get(count), ktnMatch.get(count));
        }

//-- Step 7:
        for (WebElement addInfo : pageObjectManager.getPaymentPage().getAdditionalInfoText()) {
            if (addInfo.isDisplayed()) {
                ValidationUtil.validateTestStep("Validating Additional Info text is displayed",
                        addInfo.getText(), ADD_INFO);
            }
        }

        ValidationUtil.validateTestStep("Right SSR information displayed",
                pageObjectManager.getPaymentPage().getAdditionalInfoValueText().get(1).getText(), SSR_INFO.toUpperCase());

//-- Step 8:
        final int FIRST_INDEX = 0;
        final int SECOND_INDEX = 1;
        String depAirportCode = scenarioContext.getContext(Context.HOMEPAGE_DEP_AIRPORT).toString().split("\\(")[SECOND_INDEX].split("\\)")[FIRST_INDEX];
        String retAirportCode = scenarioContext.getContext(Context.HOMEPAGE_ARR_AIRPORT).toString().split("\\(")[SECOND_INDEX].split("\\)")[FIRST_INDEX];

        for (WebElement bagsInfo: pageObjectManager.getPaymentPage().getAllBagsInfoText()) {
            ValidationUtil.validateTestStep("Validating right Bags section displayed",
                    bagsInfo.getText().trim(),depAirportCode + "-" + retAirportCode + ":  1 Carry-On , 1 Checked Bag".toUpperCase());
        }

//-- Step 9:
        for (WebElement seatInfo:pageObjectManager.getPaymentPage().getYourItineraryPassengerInfoSeatNumberText()) {
            ValidationUtil.validateTestStep("Validating City Pair format for Seats is correct",
                    depAirportCode + "-" + retAirportCode + ": " + seatInfo.getText().toUpperCase(), seatInfo.getText().toUpperCase());
        }

//-- Step 10:
        ValidationUtil.validateTestStep("Validating Passenger chevron is opened",
                pageObjectManager.getPaymentPage().getYourItineraryPassengerInfoArrowIcon().getAttribute("style"),"transform: rotate(0deg)");

        pageObjectManager.getPaymentPage().getYourItineraryPassengerInfoArrowIcon().click();
        WaitUtil.untilTimeCompleted(1000);

        ValidationUtil.validateTestStep("Validating Passenger chevron is closed",
                pageObjectManager.getPaymentPage().getYourItineraryPassengerInfoArrowIcon().getAttribute("style"),"transform: rotate(-180deg);");
    }
}