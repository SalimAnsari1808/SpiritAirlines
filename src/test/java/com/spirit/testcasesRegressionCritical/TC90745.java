package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.*;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

//**********************************************************************************************
//Test Case ID: TC90745
//Test Case Name: Task 24688: 35324 CP_BP_Payment Page_Travel Guard
//Description: Validating TG coverages for INT & DOM flights
//Created By: Gabriela
//Created On: 2-Jul-2019
//Reviewed By: Salim Ansari
//Reviewed On: 4-Jul-2019
//**********************************************************************************************
//INC 24421 CP: BP: International Flight (FLL-CUN), Travel Guard "Insurance Policy" link redirecting to domestic insurance by state page
/**10/21/19 test case passed, removed active bug tag**/

public class TC90745 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "RoundTrip" ,"DomesticDomestic", "DomesticInternational" ,"WithIn7Days", "WithIn21Days" , "Adult" , "FSMasterCard" , "NonStop" , "BookIt" , "NoBags" , "NoSeats" , "CheckInOptions" , "PaymentUI"})
    public void CP_BP_Payment_Page_Travel_Guard(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90745 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE               = "English";
        final String LOGIN_EMAIL            = "FSMCEmail";
        final String JOURNEY_TYPE           = "Flight";
        final String TRIP_TYPE              = "RoundTrip";
        final String DEP_AIRPORTS           = "AllLocation";
        final String DEP_AIRPORT_CODE_DOM   = "FLL";
        final String ARR_AIRPORTS           = "AllLocation";
        final String ARR_AIRPORT_CODE       = "ORD";
        final String ARR_AIRPORT_CODE_INT   = "CUN";
        final String DEP_DATE               = "5";
        final String ARR_DATE               = "10";
        final String ADULTS                 = "1";
        final String CHILD                  = "0";
        final String INFANT_LAP             = "0";
        final String INFANT_SEAT            = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT             = "NonStop";
        final String FARE_TYPE              = "Standard";
        final String UPGRADE_VALUE          = "BookIt";

        //Options Page Constant Value
        final String OPTION_VALUE 	        = "CheckInOption:DecideLater";

        //Payment page constant values
        final String PAYMENT_URL            = "/book/payment";
        final String TRAVEL_MORE            = "TRAVEL MORE. WORRY LESS. GET INSURANCE FOR ONLY";
        final String PER_PERSON             = "ALL TRAVELERS.*";
        final String YES_TG                 = "Yes, add insurance for";
        final String YES_TG_1               = "covering all customers on this reservation.";
        final String NO_TG                  = "No, I don't want to insure my ";
        final String NO_TG_1                = "trip and understand that I may be responsible for non-refundable charges.";
        final String POLICY                 = "I have read and understand the Insurance Policy and agree to the terms of conditions.";
        final String POLICY_URL             ="https://webservices.travelguard.com/Product/FileRetrieval.aspx?CountryCode=US&StateCode=AL&ProductCode=944301&PlanCode=NW&FileType=PROD_PLAN_GM";
        final String TG_GROUP_URL           = "https://www.travelguard.com/spirit/";
        final String TG_FULL_DISCLAIMER     = "https://www.travelguard.com/legal/disclaimer";

        //open browser
        openBrowser(platform);

//-- Step 1:
        /******************************************************************************
         *******************************Navigate to Payment Page***********************
         *********************RT DOM flight outside of 24HRs***************************/
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_EMAIL);
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE_DOM, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Customer Info Page Method
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags page
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
        WaitUtil.untilPageLoadComplete(getDriver());

        //Seats Page
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
        WaitUtil.untilPageLoadComplete(getDriver());

        //Option Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /******************************************************************************
         ***************************Validation on Payment Page********************
         ******************************************************************************/
//-- Step 2: Navigate to the Payment Page
        ValidationUtil.validateTestStep("Validating Payment Page right URL",
                getDriver().getCurrentUrl(),PAYMENT_URL);

//-- Step 3: Locate the Travel Guard content block
        ValidationUtil.validateTestStep("Validating Travel insurance is displayed on Payment Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getTravelGaurdPanel()));

//-- Step 4:
        ValidationUtil.validateTestStep("Travel More worry less.. Travel guard Section header is displaying on TG section",
                pageObjectManager.getPaymentPage().getTravelMoreWorryLessText().getText(),TRAVEL_MORE);

        //verify Travel guard header is displaying on TG section
        ValidationUtil.validateTestStep("Per person.. Travel guard Section header is displaying on TG section",
                pageObjectManager.getPaymentPage().getTravelMoreWorryLessText().getText(),PER_PERSON);

//-- Step 5, 6 and 7:
        List<String> textBeingValidated = new ArrayList<String>();
        textBeingValidated.add("100% Trip Cost Cancellation");
        textBeingValidated.add("125% Trip Cost Trip Interruption");
        textBeingValidated.add("$500 Missed Connection");
        textBeingValidated.add("$500 Trip Delay");
        textBeingValidated.add("$500 Baggage & Personal Effects");

        //Verify All section under TG section
        pageMethodManager.getPaymentPageMethods().travelGuardVerbiagesAndLink(textBeingValidated);

//-- Step 8: Follow on INT validation section

//-- Step 9: invalid step

//-- Step 10:
        ValidationUtil.validateTestStep("Validating Yes TG Radio Button"
                , pageObjectManager.getPaymentPage().getYesTravelGuardLabel().getText(), YES_TG);
        ValidationUtil.validateTestStep("Validating Yes TG Radio Button",
                pageObjectManager.getPaymentPage().getYesTravelGuardLabel().getText(), YES_TG_1);

        ValidationUtil.validateTestStep("Validating No TG Radio Button",
                pageObjectManager.getPaymentPage().getNoTravelGuardLabel().getText(),NO_TG);
        ValidationUtil.validateTestStep("Validating No TG Radio Button",
                pageObjectManager.getPaymentPage().getNoTravelGuardLabel().getText(),NO_TG_1);

        ValidationUtil.validateTestStep("Validating Insurance policy info is displayed",
                pageObjectManager.getPaymentPage().getHavereadAndUnderstandText().getText(), POLICY);

//-- Step 11:
        pageObjectManager.getPaymentPage().getInsurancePolicyLink().click();

        //Put wait for 2 second
        WaitUtil.untilTimeCompleted(2000);

        //move focus from one window to another
        TestUtil.switchToWindow(getDriver(),1);

        ValidationUtil.validateTestStep("Validating Payment Page right URL",
                getDriver().getCurrentUrl(),POLICY_URL);

        //Close second window
        getDriver().close();

        //make focus on first window
        TestUtil.switchToWindow(getDriver(),0);

//-- Step 12:
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getPaymentPage().getTravelGuardGroupLink().click();

        //Put wait for 2 second
        WaitUtil.untilTimeCompleted(2000);

        //move focus from one window to another
        TestUtil.switchToWindow(getDriver(),1);

        ValidationUtil.validateTestStep("Validating Tg Group link redirect to the right URL",
                getDriver().getCurrentUrl(),TG_GROUP_URL);

        //Close second window
        getDriver().close();

        //make focus on first window
        TestUtil.switchToWindow(getDriver(),0);

        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getPaymentPage().getTravelGuardDisclaimerLink().click();

        //Put wait for 2 second
        WaitUtil.untilTimeCompleted(2000);

        //move focus from one window to another
        TestUtil.switchToWindow(getDriver(),1);

        ValidationUtil.validateTestStep("Validating Tg Full Disclaimer link redirect to the right URL",
                getDriver().getCurrentUrl(),TG_FULL_DISCLAIMER);

        //Close second window
        getDriver().close();

        //make focus on first window
        TestUtil.switchToWindow(getDriver(),0);

//-- Step 13:
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getPaymentPage().getNoTravelGuardLabel().click();

        WaitUtil.untilTimeCompleted(1000);
        ValidationUtil.validateTestStep("Validating CC Dropdown displayed when No on TG is selected",
                pageObjectManager.getPaymentPage().getSelectCardDropDown().isDisplayed());

        pageObjectManager.getPaymentPage().getYesTravelGuardLabel().click();

        pageMethodManager.getPaymentPageMethods().calculateTravelGuard();

//-- Step 14: Follow on INT validation section

//-- Step 15, 16, 17 and 18: Invalid Step

//-- Step 1:
        /******************************************************************************
         ***************************Navigate to Payment Page***************************
         ************************RT INT flight outside of 24HRs************************/
        pageObjectManager.getHeader().getSpiritLogoImage().click();

        WaitUtil.untilPageLoadComplete(getDriver());

        JSExecuteUtil.refreshBrowser(getDriver());
        WaitUtil.untilPageLoadComplete(getDriver());

        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, "CUN", ARR_AIRPORTS, "FLL");
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Customer Info Page Method
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags page
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
        WaitUtil.untilPageLoadComplete(getDriver());

        //Seats Page
        pageMethodManager.getSeatsPageMethods().continueWithSeats();
        WaitUtil.untilPageLoadComplete(getDriver());

        //Option Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /******************************************************************************
         ***************************Validation on Payment Page********************
         ******************************************************************************/
//-- Step 2: Navigate to the Payment Page
        ValidationUtil.validateTestStep("Validating Payment Page right URL",
                getDriver().getCurrentUrl(),PAYMENT_URL);

//-- Step 3: Locate the Travel Guard content block
        ValidationUtil.validateTestStep("Validating Travel insurance is displayed on Payment Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getTravelGaurdPanel()));

//-- Step 4:
        ValidationUtil.validateTestStep("Travel More worry less.. Travel guard Section header is displaying on TG section",
                pageObjectManager.getPaymentPage().getTravelMoreWorryLessText().getText(),TRAVEL_MORE);

        //verify Travel guard header is displaying on TG section
        ValidationUtil.validateTestStep("Per person.. Travel guard Section header is displaying on TG section",
                pageObjectManager.getPaymentPage().getTravelMoreWorryLessText().getText(),PER_PERSON);

//-- Step 5, 6 and 8:
        List<String> textBeingValidated_1 = new ArrayList<String>();
        textBeingValidated_1.add("100% Trip Cost Cancellation");
        textBeingValidated_1.add("125% Trip Cost Trip Interruption");
        textBeingValidated_1.add("$500 Missed Connection");
        textBeingValidated_1.add("$500 Trip Delay");
        textBeingValidated_1.add("$500 Baggage & Personal Effects");

        //Verify All section under TG section
        pageMethodManager.getPaymentPageMethods().travelGuardVerbiagesAndLink(textBeingValidated_1);

//-- Step 7: Follow on DOM validation section

//-- Step 9: invalid step

//-- Step 10:
        ValidationUtil.validateTestStep("Validating Yes TG Radio Button"
                , pageObjectManager.getPaymentPage().getYesTravelGuardLabel().getText(), YES_TG);
        ValidationUtil.validateTestStep("Validating Yes TG Radio Button",
                pageObjectManager.getPaymentPage().getYesTravelGuardLabel().getText(), YES_TG_1);

        ValidationUtil.validateTestStep("Validating No TG Radio Button",
                pageObjectManager.getPaymentPage().getNoTravelGuardLabel().getText(),NO_TG);
        ValidationUtil.validateTestStep("Validating No TG Radio Button",
                pageObjectManager.getPaymentPage().getNoTravelGuardLabel().getText(),NO_TG_1);

        ValidationUtil.validateTestStep("Validating Insurance policy info is displayed",
                pageObjectManager.getPaymentPage().getHavereadAndUnderstandText().getText(), POLICY);

//-- Step 11:
        pageObjectManager.getPaymentPage().getInsurancePolicyLink().click();

        //Put wait for 2 second
        WaitUtil.untilTimeCompleted(2000);

        //move focus from one window to another
        TestUtil.switchToWindow(getDriver(),1);

        ValidationUtil.validateTestStep("Validating Travel Guard URL for international Flights" ,
                POLICY_URL , getDriver().getCurrentUrl());

        //Close second window
        getDriver().close();

        //make focus on first window
        TestUtil.switchToWindow(getDriver(),0);

//-- Step 12:
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getPaymentPage().getTravelGuardGroupLink().click();

        //Put wait for 2 second
        WaitUtil.untilTimeCompleted(2000);

        //move focus from one window to another
        TestUtil.switchToWindow(getDriver(),1);

        ValidationUtil.validateTestStep("Validating Tg Group link redirect to the right URL",
                getDriver().getCurrentUrl(),TG_GROUP_URL);

        //Close second window
        getDriver().close();

        //make focus on first window
        TestUtil.switchToWindow(getDriver(),0);

        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getPaymentPage().getTravelGuardDisclaimerLink().click();

        //Put wait for 2 second
        WaitUtil.untilTimeCompleted(2000);

        //move focus from one window to another
        TestUtil.switchToWindow(getDriver(),1);

        ValidationUtil.validateTestStep("Validating Tg Full Disclaimer link redirect to the right URL",
                getDriver().getCurrentUrl(),TG_FULL_DISCLAIMER);

        //Close second window
        getDriver().close();

        //make focus on first window
        TestUtil.switchToWindow(getDriver(),0);

//-- Step 13: Follow on INT validation section

//-- Step 14:
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getPaymentPage().getNoTravelGuardLabel().click();

        WaitUtil.untilTimeCompleted(1000);
        ValidationUtil.validateTestStep("Validating CC Dropdown displayed when No on TG is selected",
                pageObjectManager.getPaymentPage().getSelectCardDropDown().isDisplayed());

        pageObjectManager.getPaymentPage().getYesTravelGuardLabel().click();
        pageMethodManager.getPaymentPageMethods().calculateTravelGuard();

//-- Step 15, 16, 17 and 18: Invalid Step
    }
}