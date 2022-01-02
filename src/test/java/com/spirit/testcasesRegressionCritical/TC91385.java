package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.*;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

//**********************************************************************************************
//Test Case ID: TC91385
//Test Case: Task 24727: 35331 CP_CI_Payment Page_Passenger Info
//Created By: Gabriela
//Created On: 18-Jul-2019
//Reviewed By: Salim Ansari
//Reviewed On: 18-Jul-2019
//**********************************************************************************************

public class TC91385 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"CheckIn" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "FreeSpirit" , "NonStop" , "BookIt" , "PassengerInfoSSR" , "CarryOn" , "CheckedBags" , "Standard" ,"ShortCutBoarding", "CheckInOptions" , "Visa" , "PaymentUI","AddEditBags"})
    public void CP_CI_Payment_Page_Passenger_Info(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91385 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant variables
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String LOGIN_ACCOUNT      = "FSEmail";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "0";
        final String ARR_DATE           = "NA";
        final String ADULT              = "4";
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
        final String DEP_BAGS           = "Carry_1|Checked_1||Carry_1|Checked_1||Carry_1|Checked_1||Carry_1|Checked_1";

        //Seats Page Constant Values
        final String DEP_SEATS          = "Standard|Standard|Standard|Standard";

        //Options Page Constant Value
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //Payment page constant value
        final String PAYMENT_URL        = "/book/payment";
        final String CARD_TYPE          = "VisaCard";
        final String TRAVEL_GUARD       = "NotRequired";
        final String CI_PAYMENT_URL     = "/check-in/payment";
        final String FREE_SPIRIT_TEXT   = "FREE SPIRIT#:";
        final String KTN_TEXT           = "Known Traveler # (TSA Pre  ";
        final String ADD_INFO           = "ADDITIONAL INFO:";

        //open browser
        openBrowser(platform);

        /*********************************************************************************************************
         * ******************************************HOME PAGE****************************************************
         *********************************************************************************************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        /*********************************Flight Availability Methods*************************************************/
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        /*********************************Passenger Info Methods*************************************************/
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();

        // Pax 1 info
        List<String> passTitle = new ArrayList<>();
        List<String> passFirstName = new ArrayList<>();
        List<String> passLastName = new ArrayList<>();

        for (int count = 0;count < pageObjectManager.getPassengerInfoPage().getAdultTitleListDropDown().size(); count++) {
            passTitle.add(pageObjectManager.getPassengerInfoPage().getAdultTitleListDropDown().get(count).getAttribute("value"));
        }

        for (int count = 0; count < pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().size(); count++) {
            passFirstName.add(pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(count).getAttribute("value"));
        }

        for (int count = 0; count < pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().size(); count++) {
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
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /**************************************Bags Page Methods*************************************************/
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        /*********************************Seats Page Methods*************************************************/
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        /*********************************Options Page Methods*************************************************/
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /*********************************Payment Page Methods*************************************************/
        //Validating Payment URL
        ValidationUtil.validateTestStep("User verify Navigated to Payment page", getDriver().getCurrentUrl(),PAYMENT_URL);

        //Completing payment and booking
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        /*********************************Confirmation Page Methods*************************************************/
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //Reaching into Check In path
        pageMethodManager.getHomePageMethods().loginToCheckIn();

        /*********************************Online Check In Page Methods*************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        JSExecuteUtil.refreshBrowser(getDriver());
        WaitUtil.untilPageLoadComplete(getDriver());

        //Add bags
        pageObjectManager.getReservationSummaryPage().getPassengerSectionAddBagsButton().get(0).click();

        /*********************************Bags Page Methods*************************************************/
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);

        String bagPrice = pageObjectManager.getBagsPage().getBagsTotalContainerAmountTotalText().getText();
        WaitUtil.untilTimeCompleted(1000);

        pageObjectManager.getBagsPage().getBagsTotalContainerLink().click();
        WaitUtil.untilTimeCompleted(1000);

        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

        /*********************************Seats Page Methods*************************************************/
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnSeatsPopup("Purchase");

        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEATS);

        List<WebElement> seatsSelect = pageObjectManager.getSeatsPage().getPassengerSeatText();
        List<String> seatsList = new ArrayList<>();

        for (int count = 0; count < seatsSelect.size(); count++) {
            seatsList.add(seatsSelect.get(count).getText());
        }

        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        /*********************************Options Page Methods*************************************************/
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /*********************************Payment Page Methods*************************************************/
        //-- Step 1:
        WaitUtil.untilPageLoadComplete(getDriver());
        //URL Validation
        ValidationUtil.validateTestStep("Validating Payment Page URL", getDriver().getCurrentUrl(), CI_PAYMENT_URL);

//-- Step 2:
        List<WebElement> passengerName = pageObjectManager.getPaymentPage().getPassengerNameText();
        for (int count = 0; count< passengerName.size(); count++) {
            ValidationUtil.validateTestStep("Validating Passenger image is displayed for each passenger in the booking",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getPassengerIcon().get(count)));
        }

//-- Step 3 and 4
        for (int count = 0; count < pageObjectManager.getPaymentPage().getPassengerNameText().size(); count++) {
            ValidationUtil.validateTestStep("Validating Pass Info displayed properly",
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

        List<String> fsnums = getDisplayedText(pageObjectManager.getPaymentPage().getFreeSpiritText());
        System.out.println(fsnums);
        System.out.println((fsnums.get(0).substring(fsnums.indexOf(":")+2)));
//        ValidationUtil.validateTestStep("Validating FS# length is 9 or 10",
//                fsnums.get(0).substring(fsnums.indexOf(":")+2).trim().length() == 9
//                        || fsnums.get(0).substring(fsnums.indexOf(":")+2).trim().length() == 10);


        List<String> ktnNum = getDisplayedText(pageObjectManager.getPaymentPage().getKnownTravelerText());
        List<String> tsaNumber = new ArrayList<>();

        for (int count = 0; count < ktnNum.size(); count++) {
            tsaNumber.add(ktnNum.get(count).substring(ktnNum.get(count).indexOf(":") + 1));
        }

        for (int count = 0;count < tsaNumber.size();count++) {
            ValidationUtil.validateTestStep("Validating KTN matching",
                    tsaNumber.get(count), ktnMatch.get(count));
        }

//-- Step 7:
        //Additional info text displayed
        for (WebElement addInfo : pageObjectManager.getPaymentPage().getAdditionalInfoText()) {
            if (addInfo.isDisplayed()) {
                ValidationUtil.validateTestStep("Validating Additional Info text is displayed",
                        addInfo.getText(), ADD_INFO);
            }
        }

        //SSR displayed

        ValidationUtil.validateTestStep("Right SSR information displayed",
                pageObjectManager.getPaymentPage().getAdditionalInfoValueText().get(1).getText(), SSR_INFO.toUpperCase());


//-- Step 8 and 9
        final int FIRST_INDEX = 0;
        final int SECOND_INDEX = 1;
        String depAirportCode = scenarioContext.getContext(Context.HOMEPAGE_DEP_AIRPORT).toString().split("\\(")[SECOND_INDEX].split("\\)")[FIRST_INDEX];
        String retAirportCode = scenarioContext.getContext(Context.HOMEPAGE_ARR_AIRPORT).toString().split("\\(")[SECOND_INDEX].split("\\)")[FIRST_INDEX];

        ValidationUtil.validateTestStep("Validating right Bags section displayed",
                pageObjectManager.getPaymentPage().getAllBagsInfoText().get(0).getText(), depAirportCode + "-" + retAirportCode + ":  1 Carry-On , 1 Checked Bag".toUpperCase());

//-- Step 10 and 11
        List<WebElement> seatSection = pageObjectManager.getPaymentPage().getPassengerSectionSeatFlightSeatNumberText();
        for (int count = 0; count < seatSection.size(); count++) {
            ValidationUtil.validateTestStep("Validating City Pair format for Seats is correct",
                    depAirportCode + "-" + retAirportCode + ": " + seatsList.get(count).toUpperCase(), seatSection.get(count).getText().toUpperCase());
        }

//-- Step 12:
        ValidationUtil.validateTestStep("Validating Passenger chevron is opened",
                pageObjectManager.getPaymentPage().getYourItineraryPassengerInfoArrowIcon().getAttribute("style"), "transform: rotate(0deg)");

        pageObjectManager.getPaymentPage().getYourItineraryPassengerInfoArrowIcon().click();
        WaitUtil.untilTimeCompleted(1000);

        ValidationUtil.validateTestStep("Validating Passenger chevron is closed",
                pageObjectManager.getPaymentPage().getYourItineraryPassengerInfoArrowIcon().getAttribute("style"), "transform: rotate(-180deg);");

    }

    private List<String> getDisplayedText(List<WebElement> elementList) {

        ArrayList<String> arrayList = new ArrayList<>();//empty

        for (WebElement element : elementList) {
            if (element.isDisplayed()) {

                arrayList.add(element.getText().trim());
            }
        }
        return arrayList;
    }
}