package com.spirit.testcasesProdBAT;

import com.spirit.baseClass.*;
import com.spirit.enums.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.Optional;
import org.testng.annotations.*;

import java.util.*;

//**********************************************************************************************
//Test Case ID: TC90739
//Test Case Name: Task 24682: 35321 CP_BP_Payment Page_Passenger Info
//Created By: Gabriela
//Created On: 12-Jul-2019
//Reviewed By: Salim Ansari
//Reviewed On: 16-Jul-2019
//**********************************************************************************************

public class PRODTC90739 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups="Production")

    public void CP_BP_Payment_Page_Passenger_Info(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID PRODTC90739 under PRODUCTION Suite on " + platform + " Browser", true);
        }
        //Home Page Constant variables
        final String LANGUAGE           = "English";
        final String LOGIN_ACCOUNT      = "ProdFSEmail";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "MCO";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "FLL";
        final String DEP_DATE           = "3";
        final String ARR_DATE           = "NA";
        final String ADULT              = "3";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Passenger Info Page Constant Values
        final String SSR                = "HearingImpaired";
        final String SSR_INFO           = "Hearing Impaired";

        //Bags Page Constant Values
        final String DEP_BAGS           = "Carry_1|Checked_1||Carry_1|Checked_1||Carry_1|Checked_1";

        //Seats Page Constant Values
        final String DEP_SEATS          = "Standard|Standard|Standard";

        //Options Page Constant Value
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //Payment page constant value
        final String PAYMENT_URL        = "/book/payment";
        final String FREE_SPIRIT_TEXT   = "FREE SPIRIT#:";
        final String KTN_TEXT           = "Known Traveler # (TSA Pre  ";
        final String ADD_INFO           = "ADDITIONAL INFO:";

        //open browser
        openBrowser(platform);
//--Step 1
        /****************************************************************************
         * *********************Navigate to Payment Page ****************************
         ****************************************************************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

        // Pax 1 info
        String passenger1FirstName = pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0).getAttribute("value");
        String passenger1LastName = pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(0).getAttribute("value");
        String passenger1Title = pageObjectManager.getPassengerInfoPage().getAdultTitleListDropDown().get(0).getAttribute("value");

        // Pax 1 info
        String passenger2FirstName = pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(1).getAttribute("value");
        String passenger2LastName = pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(1).getAttribute("value");
        String passenger2Title = pageObjectManager.getPassengerInfoPage().getAdultTitleListDropDown().get(1).getAttribute("value");

        // Pax 1 info
        String passenger3FirstName = pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(2).getAttribute("value");
        String passenger3LastName = pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(2).getAttribute("value");
        String passenger3Title = pageObjectManager.getPassengerInfoPage().getAdultTitleListDropDown().get(2).getAttribute("value");

        List<WebElement> ktnNumber = pageObjectManager.getPassengerInfoPage().getAdultKTNListTextBox();
        List<String> ktnMatch = new ArrayList<>();
        String KTN = "123654";
        for (int i = 0; i < ktnNumber.size(); i++) {
            ktnNumber.get(i).sendKeys(KTN + i);
            ktnMatch.add(ktnNumber.get(i).getAttribute("value"));
        }

        pageMethodManager.getPassengerInfoMethods().selectSSRPerPassenger(SSR);
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(2000);
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);
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

        /****************************************************************************
         * *********************Payment Page Validations ****************************
         ****************************************************************************/
//-- Step 1:
        WaitUtil.untilPageLoadComplete(getDriver());
        //URL Validation
        ValidationUtil.validateTestStep("Validating Payment Page URL", getDriver().getCurrentUrl(), PAYMENT_URL);

//-- Step 2:
        List<WebElement> passengerName = pageObjectManager.getPaymentPage().getPassengerNameText();
        for (int count = 0; count < passengerName.size(); count++) {
            ValidationUtil.validateTestStep("Validating Passenger image is displayed for each passenger in the booking",
                    pageObjectManager.getPaymentPage().getPassengerIcon().get(count).isDisplayed());
        }

//-- Step 3:

//-- Step 4:
        ValidationUtil.validateTestStep("Pax 1 right information is displayed",
                pageObjectManager.getPaymentPage().getPassengerNameText().get(0).getText(), passenger1Title + ". " + passenger1FirstName + " " + passenger1LastName);
        ValidationUtil.validateTestStep("Pax 2 right information is displayed",
                pageObjectManager.getPaymentPage().getPassengerNameText().get(1).getText(), passenger2Title + ". " + passenger2FirstName + " " + passenger2LastName);
        ValidationUtil.validateTestStep("Pax 3 right information is displayed",
                pageObjectManager.getPaymentPage().getPassengerNameText().get(2).getText(), passenger3Title + ". " + passenger3FirstName + " " + passenger3LastName);

//-- Step 5
        pageObjectManager.getPaymentPage().getYourItineraryPassengerInfoArrowIcon().click();

//-- Step 6
        WaitUtil.untilTimeCompleted(3000);
        for (WebElement fsNumberText : pageObjectManager.getPaymentPage().getFreeSpiritText()) {
            if (fsNumberText.isDisplayed()) {
                ValidationUtil.validateTestStep("Validating FREE SPIRIT#: text is displayed", fsNumberText.getText(), FREE_SPIRIT_TEXT);
            }
        }

        String FSNUM = pageObjectManager.getPaymentPage().getFreeSpiritText().get(3).getText().split(":")[1].trim();
        ValidationUtil.validateTestStep("Validating FS# length is 9 or 10", FSNUM.length() == 9 || FSNUM.length() == 10);

//-- Step 7:
        for (WebElement ktnText : pageObjectManager.getPaymentPage().getKnownTravelerText()) {
            if (ktnText.isDisplayed()) {
                ValidationUtil.validateTestStep("Validating TSA number text is displayed", ktnText.getText(), KTN_TEXT);
            }
        }

        List<WebElement> ktnNum = pageObjectManager.getPaymentPage().getKnownTravelerText();
        List<String> tsaNumber = new ArrayList<>();
        for (int count = 3; count < ktnNum.size(); count++) {
            String TSA = ktnNum.get(count).getText().substring(ktnNum.get(count).getText().indexOf(":") + 1).trim();
            tsaNumber.add(TSA);
        }

        for (int count = 0; count < tsaNumber.size(); count++) {
            ValidationUtil.validateTestStep("Validating KTN matching", tsaNumber.get(count), ktnMatch.get(count));
        }

//-- Step 7:
        for (WebElement addInfo : pageObjectManager.getPaymentPage().getAdditionalInfoText()) {
            if (addInfo.isDisplayed()) {
                ValidationUtil.validateTestStep("Validating Additional Info text is displayed", addInfo.getText(), ADD_INFO);
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
        WaitUtil.untilTimeCompleted(2000);

        ValidationUtil.validateTestStep("Validating Passenger chevron is closed",
                pageObjectManager.getPaymentPage().getYourItineraryPassengerInfoArrowIcon().getAttribute("style"),"transform: rotate(-180deg);");
    }
}