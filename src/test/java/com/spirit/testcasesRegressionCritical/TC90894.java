package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;

//**********************************************************************************************
//Test Case ID: TC90894
//Test Case Name: Task 24684: 35322 CP_BP_Payment Page_Purchase Price
//Created By: Gabriela
//Created On: 19-Jul-2019
//Reviewed By: Salim Ansari
//Reviewed On: 19-Jul-2019
//**********************************************************************************************

public class TC90894 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "NonStop" , "BookIt" , "CarryOn" , "CheckedBags" , "Standard" ,"ShortCutBoarding", "CheckInOptions" ,"TravelInsuranceBlock", "PaymentUI" , "TaxesAndFee"})
    public void CP_BP_Payment_Page_Purchase_Price(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90894 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant variables
        final String LANGUAGE                           = "English";
        final String JOURNEY_TYPE                       = "Flight";
        final String TRIP_TYPE                          = "RoundTrip";
        final String DEP_AIRPORTS                       = "AllLocation";
        final String DEP_AIRPORT_CODE                   = "MCO";
        final String ARR_AIRPORTS                       = "AllLocation";
        final String ARR_AIRPORT_CODE                   = "FLL";
        final String DEP_DATE                           = "3";
        final String ARR_DATE                           = "5";
        final String ADULT                              = "1";
        final String CHILD                              = "0";
        final String INFANT_LAP                         = "0";
        final String INFANT_SEAT                        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT                         = "NonStop";
        final String RET_FLIGHT                         = "NonStop";
        final String FARE_TYPE                          = "Standard";
        final String UPGRADE_VALUE                      = "BookIt";
        final String FA_URL                             = "/book/flights";

        //Bags Page Constant Values
        final String DEP_BAGS                           = "Carry_1|Checked_2";
        final String RET_BAGS                           = "Carry_1|Checked_2";
        final String BAG_URL                            = "/book/bags";

        //Seats Page Constant Values
        final String DEP_SEATS                          = "Standard";
        final String RET_SEATS                          = "Standard";
        final String SEAT_URL                           = "/book/seats";

        //Options Page Constant Value
        final String OPTIONS_VALUE                      = "CheckInOption:MobileFree";
        final String OPTIONS_URL                        = "/book/options";

        //Payment page constant value
        final String PAYMENT_URL                        = "/book/payment";
        final String TOTAL_DUE_TXT                      = "TOTAL DUE";
        final String FARES_INFO                         = "Fares are not guaranteed until purchased.";
        final String BOOKING_TYPE                       = "FLIGHT";
        final String SEAT_TYPE                          = "STANDARD";
        final String OPTION_SELECTED                    = "SHORTCUT BOARDING";
        final String TRAVEL_GUARD                       = "Required";

        //open browser
        openBrowser(platform);
//--Step 1
        //**********************Navigate to Payment Page ******************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //*********************************Flight Availability Methods*************************************************/
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //*********************************Passenger info Page Methods*************************************************/
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //*********************************Bags Page Methods*************************************************/
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectReturnBags(RET_BAGS);
        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

        //*********************************Seats Page Methods*************************************************/
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEATS);
        pageMethodManager.getSeatsPageMethods().selectReturningSeats(RET_SEATS);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //*********************************Options Page Methods*************************************************/
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //***********************Payment Page Validations ****************************/
//-- Step 2:
        //URL Validation
        ValidationUtil.validateTestStep("Validating Payment Page URL",
                getDriver().getCurrentUrl(),PAYMENT_URL);

//-- Step 3:
        //Text Validation
        ValidationUtil.validateTestStep("Validating Total Due banner title",
                pageObjectManager.getPaymentPage().getTotalDueText().getText(),TOTAL_DUE_TXT);

        //Price Validation
        ValidationUtil.validateTestStep("Validating dollar amount is displayed",
                pageObjectManager.getPaymentPage().getTotalDuePriceText().getText().startsWith("$"));

        //Fares info
        ValidationUtil.validateTestStep("Validating fares info is displayed",
                pageObjectManager.getPaymentPage().getTotalDueDisclaimerText().getText(),FARES_INFO);

//-- Step 4
        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();
        WaitUtil.untilTimeCompleted(1000);

        ValidationUtil.validateTestStep("Validating Total Due get expanded",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getTotalDueBreakDownText()));

        List<WebElement> totalDueSectionList = pageObjectManager.getPaymentPage().getTotalDueBreakDownText();
        ValidationUtil.validateTestStep("Validating booking type in the top of the drop down section",
                totalDueSectionList.get(0).getText(),BOOKING_TYPE);

//-- Step 5:
        ValidationUtil.validateTestStep("Flight Edit link Validation",
                pageObjectManager.getPaymentPage().getTotalDueFlightEditText().isEnabled());

        ValidationUtil.validateTestStep("Bags Edit link Validation",
                pageObjectManager.getPaymentPage().getTotalDueBagsEditText().isEnabled());

        ValidationUtil.validateTestStep("Seats Edit link Validation",
                pageObjectManager.getPaymentPage().getTotalDueSeatsEditText().isEnabled());

        ValidationUtil.validateTestStep("Options Edit link Validation",
                pageObjectManager.getPaymentPage().getTotalDueOptionsEditText().isEnabled());

//-- Step 6
        //*******************************FLIGHT EDIT LINK*****************************/
        WaitUtil.untilTimeCompleted(3000);
        pageObjectManager.getPaymentPage().getTotalDueFlightEditText().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        pageObjectManager.getPaymentPage().getAreYouSurePopUpContinueToFlightButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());


        //*********************************Flight Availability Page Methods*************************************************/
        ValidationUtil.validateTestStep("Validating Flight Availability Page URL",
                getDriver().getCurrentUrl(),FA_URL);

        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //*********************************Passenger Info Page Methods*************************************************/
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //*********************************Bags Page Methods*************************************************/
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectReturnBags(RET_BAGS);
        //***************************************************************************************************************/
        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

        //*********************************Seats Page Methods*************************************************/
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEATS);
        pageMethodManager.getSeatsPageMethods().selectReturningSeats(RET_SEATS);

        // Storing seat type
        pageObjectManager.getSeatsPage().getSeatsTotalContainerLink().click();
        WaitUtil.untilTimeCompleted(1000);

        List<String> seatType = new ArrayList<>();
        List<String> type = getDisplayedText(pageObjectManager.getSeatsPage().getSeatTotalOptionItemText());

        for (int count = 0; count < type.size(); count++) {
            seatType.add(type.get(count));
        }

        String seatTotalPrice = pageObjectManager.getSeatsPage().getSeatsTotalPriceText().getText();

        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //*********************************Options Page Methods*************************************************/
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //*********************************Payment Page Methods*************************************************/
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();
        WaitUtil.untilTimeCompleted(1000);

        pageObjectManager.getPaymentPage().getTotalDueBagsChevronLink().click();
        WaitUtil.untilTimeCompleted(1000);

        //******************************BAGS EDIT LINK*******************************/
        pageObjectManager.getPaymentPage().getTotalDueBagsEditText().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating Bags Page URL", getDriver().getCurrentUrl(),BAG_URL);

        //*********************************Bags Page Methods*************************************************/
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getBagsPage().getBagsTotalContainerLink().click();
        WaitUtil.untilTimeCompleted(1000);

        List<String> bagsList = new ArrayList<>();
        for(WebElement element:pageObjectManager.getBagsPage().getBagsTotalContainerOptionsText()){
            bagsList.add(element.getText().trim());
        }

        String bagPrice = pageObjectManager.getBagsPage().getBagsTotalContainerAmountTotalText().getText();
        WaitUtil.untilTimeCompleted(1000);
        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

        //*********************************Seats Page Methods*************************************************/
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //*********************************Options Page Methods*************************************************/
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //*********************************Payment Page Methods*************************************************/
        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();
        WaitUtil.untilTimeCompleted(1000);

        //*******************************SEATS EDIT LINK*****************************/
        pageObjectManager.getPaymentPage().getTotalDueSeatsEditText().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating Seats Page URL", getDriver().getCurrentUrl(),SEAT_URL);

        //*********************************Seats Page Methods*************************************************/
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //*********************************Options Page Methods*************************************************/
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //*********************************Payment Page Methods*************************************************/
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);

        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();
        WaitUtil.untilTimeCompleted(1000);

        //**************************OPTIONS EDIT LINK*******************************/
        pageObjectManager.getPaymentPage().getTotalDueOptionsEditText().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating Options Page URL",
                getDriver().getCurrentUrl(),OPTIONS_URL);

        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //*********************************Payment Page Methods*************************************************/
        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();
        WaitUtil.untilTimeCompleted(1000);

//-- Step 7:Not applicable for this test case

//-- Step 8:
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageObjectManager.getPaymentPage().getTotalDueChevronLink().click();
        List <String> taxList = new ArrayList<String>();

        //add tax into list
        taxList.add("Regulatory Compliance Charge (Carrier Fee)");
        taxList.add("Fuel Charge (Carrier Fee)");
        taxList.add("Security Fee");
        taxList.add("Segment Fee");
        taxList.add("Passenger Usage Charge (Non-refundable Carrier Fee)");
        taxList.add("Passenger Facility Fee");
        taxList.add("Federal Excise Tax");

        //Create new method to verify "To City pair" taxes
        pageMethodManager.getPaymentPageMethods().toCityPairTaxes(taxList);

//-- Step 9:
        WaitUtil.untilTimeCompleted(1000);

        final int FIRST_INDEX 					= 0;
        final int SECOND_INDEX 					= 1;
        String depAirportCode					= scenarioContext.getContext(Context.HOMEPAGE_DEP_AIRPORT).toString().split("\\(")[SECOND_INDEX].split("\\)")[FIRST_INDEX];
        String retAirportCode					= scenarioContext.getContext(Context.HOMEPAGE_ARR_AIRPORT).toString().split("\\(")[SECOND_INDEX].split("\\)")[FIRST_INDEX];

        ValidationUtil.validateTestStep("Validating right city displayed on bags section",
                pageObjectManager.getPaymentPage().getTotalDueBagsCityText().getText(),depAirportCode + " - " + retAirportCode);


        ValidationUtil.validateTestStep("Validating the right Carry On bag amount is displayed",
                pageObjectManager.getPaymentPage().getTotalDueBagsCarryOnText().get(0).getText(),bagsList.get(0));

        //TODO: Bug 25143: CP: BP: Bags Page BG: Bag type is shown in the singular form on bags pricing breakdown section on the Payment
        // page instead on the plural form as is shown on the Bags page and Dynamic Shopping cart

        ValidationUtil.validateTestStep("Validating the right checked bag amount is displayed",
                bagsList.get(1),pageObjectManager.getPaymentPage().getTotalDueBagsCheckedText().get(0).getText());
//-- Step 10:
        //TODO: Bug 25106: CP: BP: Payment Page PMT: Seats selected originally are being removed once customer select EDIT link (for bags or seats) from the Payment page
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getPaymentPage().getTotalDueSeatsChevronLink().click();

//        //Seats Price
//        ValidationUtil.validateTestStep("Validating seats price",
//                pageObjectManager.getPaymentPage().getTotalDueSeatsPriceText().getText(),seatTotalPrice);

        //Seats Type
        for (int count = 0; count < pageObjectManager.getPaymentPage().getTotalDueSeatsTypeText().size(); count++) {
            ValidationUtil.validateTestStep("Validating Seats Type",
                    seatType.get(count), pageObjectManager.getPaymentPage().getTotalDueSeatsTypeText().get(count).getText());
        }

//-- step 12:
        pageMethodManager.getPaymentPageMethods().calculateTravelGuard();

//-- Step 13: Not applicable for this test case

//-- Step 14
        double totalDuePrice        = Double.parseDouble(pageObjectManager.getPaymentPage().getTotalDuePriceText().getText().replace("$",""));
        double flightTotalDuePrice  = Double.parseDouble(pageObjectManager.getPaymentPage().getTotalDueFlightPriceText().getText().replace("$",""));
        double bagsTotalDuePrice    = Double.parseDouble(pageObjectManager.getPaymentPage().getTotalDueBagsPriceText().getText().replace("$",""));
        double seatsTotalDuePrice   = Double.parseDouble(pageObjectManager.getPaymentPage().getTotalDueSeatsPriceText().getText().replace("$",""));
        double optionsTotalDuePrice = Double.parseDouble(pageObjectManager.getPaymentPage().getTotalDueOptionsPriceText().getText().replace("$",""));
        double tgTotalDuePrice      = Double.parseDouble(pageObjectManager.getPaymentPage().getTotalDueTravelInsurancePriceText().getText().replace("$",""));
        double total                = Double.parseDouble(TestUtil.convertTo2DecimalValue(flightTotalDuePrice + bagsTotalDuePrice + seatsTotalDuePrice + optionsTotalDuePrice + tgTotalDuePrice));

        ValidationUtil.validateTestStep("Validating the sum of all the groupings is equal Total Due",
                totalDuePrice== total);
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