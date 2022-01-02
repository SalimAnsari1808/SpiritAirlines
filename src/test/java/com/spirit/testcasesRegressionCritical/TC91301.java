package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC91301
//Description: Task 24672: 35315 CP_BP_Payment_Page_9DFC_Upsell_Banner
//Created By: Gabriela
//Created On: 1-Jul-2019
//Reviewed By: Salim Ansari
//Reviewed On: 5-Jul-2019
//**********************************************************************************************

public class TC91301 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"NineDFC" , "BookPath" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "NonStop" , "BookIt" , "NoBags" , "NoSeats","CheckInOptions","PaymentUI" })
    public void CP_BP_Payment_Page_9DFC_Upsell_Banner(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91301 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String LOGIN_ACCOUNT      = "NineDFCEmail";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "OneWay";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "MCO";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "FLL";
        final String DEP_DATE 			= "3";
        final String ARR_DATE 			= "NA";
        final String ADULT  			= "1";
        final String CHILD  			= "0";
        final String INFANT_LAP 		= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		= "NonStop";
        final String FARE_TYPE			= "Standard";
        final String FARE_TYPE_1		= "Member";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page Constant Values
        final String OPTIONS_VALUE	    = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String MARKETING_VERBIAGE = "Join the $9 Fare Club 60-day Trial Membership for exclusive fares and bag discounts, on your next booking!";
        final String MARKETING_VERBIAGE1= "Exclusive 9FC fares and bag discounts";
        final String MARKETING_VERBIAGE2= "You are required to purchase $9 Fare Club membership to receive a discount " +
                "on the selected itinerary. If you remove the membership you will need to select a different itinerary.";
        final String MODAL              = "$9 Fare Club";
        final String TRIAL_PRICE        = "$19.95";
        final String EXCLUSIVE_PRICE    = "$59.95";
        final String SAVING_TEXT        = "$9 Fare Club Discount Savings";
        final String BUTTON_1           = "Yes, I want to save";
        final String BUTTON_2           = "No, I don't want to save";
        final String LEFT_VERBIAGE      = "Yes, I want to save by joining the $9 Fare Club for $59.95 (*Membership charges " +
                "are non-refundable and program renews automatically each year)";
        final String RIGHT_VERBIAGE     = "You will be redirected to select your itinerary at standard flight and bag prices.";

//-- Step 1 Navigate to the payment page
        //open browser
        openBrowser(platform);
        /****************************************************************************
         * ************************Navigate to Payment Page***************************
         ****************************************************************************/
        //Home Page
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Page
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Information Page
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats Page
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options Page
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /****************************************************************************
         * *********************Payment Page Validations*****************************
         ****************************************************************************/
//-- Step 2: When a Guest is not a $9FC member and did not make any $9FC selections during the booking process,
// the following $9FC content block / pencil banner should be displayed:

        ValidationUtil.validateTestStep("Validating 9FC Trial banner is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getTrialMembershipLabel()) );

        ValidationUtil.validateTestStep("Validating check box is in blank by default",
                !pageObjectManager.getPaymentPage().getTrialMembershipLabel().isSelected());

        ValidationUtil.validateTestStep("Validating Marketing right verbiage displayed",
                pageObjectManager.getPaymentPage().getTrialMembershipLabel().getText(),MARKETING_VERBIAGE);

        ValidationUtil.validateTestStep("Validating the right price per the trial is displayed",
                pageObjectManager.getPaymentPage().getTrialMembershipPriceText().getText(),TRIAL_PRICE);

        pageObjectManager.getPaymentPage().getTrialMembershipTermsAndConditionsIcon().click();

        ValidationUtil.validateTestStep("Validating terms and Conditions is displayed after click on the caret",
                pageObjectManager.getPaymentPage().getMemberTrailTermsAndConditionPanel().isDisplayed());

        pageObjectManager.getHeader().getSpiritLogoImage().click();

//-- Step 1 Navigate to the payment page

        /****************************************************************************
         * ************************Navigate to Payment Page***************************
         ****************************************************************************/
        //Home Page
        WaitUtil.untilPageLoadComplete(getDriver());
        JSExecuteUtil.refreshBrowser(getDriver());
        WaitUtil.untilPageLoadComplete(getDriver());

        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Page
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE_1);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Information Page
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getPassengerInfoPage().get9FCUpsellSignUpButton().click();

        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getPassengerInfoPage().get9FCUpselSignUpChoosePasswordTextBox().sendKeys("Spirit1!");

        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getPassengerInfoPage().get9FCUpsellSignUpConfirmPasswordTextBox().sendKeys("Spirit1!");

        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getPassengerInfoPage().get9FCUpsellSignUpWithEmailButton().click();

        //Bags Page
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats Page
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options Page
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /****************************************************************************
         * *********************Payment Page Validations*****************************
         ****************************************************************************/
        ValidationUtil.validateTestStep("Validating 9FC Trial banner is displayed",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getTrialMembershipLabel()) );

        ValidationUtil.validateTestStep("Validating Marketing right verbiage displayed",
                pageObjectManager.getPaymentPage().getExclusiveMembershipLabel().getText(),MARKETING_VERBIAGE1);

        ValidationUtil.validateTestStep("Validating the right price per the trial is displayed",
                pageObjectManager.getPaymentPage().getExclusiveMembershipPriceText().getText(),EXCLUSIVE_PRICE);

        pageObjectManager.getPaymentPage().getExclusiveMembershipArrowImage().click();

        WaitUtil.untilTimeCompleted(2000);
        ValidationUtil.validateTestStep("Validating terms and Conditions is displayed after click on the caret",
                pageObjectManager.getPaymentPage().getMemberTrailTermsAndConditionPanel().isDisplayed());

//-- Step 4: Enrollment during the booking validation on Payment Page
        ValidationUtil.validateTestStep("Validating the 9FC saving banner is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getExclusiveMembership$9FareClubDiscountText()));

        ValidationUtil.validateTestStep("Validating right saving information is displayed",
                pageObjectManager.getPaymentPage().getFareClubSavingVerbiageText().getText(),SAVING_TEXT.toUpperCase());

//-- Step 5: 9FC pop up displayed validation on Payment Page
        pageObjectManager.getPaymentPage().getExclusiveMembershipLabel().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating 9FC pop up is displayed due uncheck the check box",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().get$9FCFareClubPopUpPanel()));

        ValidationUtil.validateTestStep("Validating Pop Up title",
                pageObjectManager.getPaymentPage().get$9FCFareClubPopUpHeaderText().getText(),MODAL);

        ValidationUtil.validateTestStep("Validating X is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().get$9FCFareClubPopUpCloseImage()));

        ValidationUtil.validateTestStep("Validating right verbiage under title",
                pageObjectManager.getPaymentPage().get$9FCFareClubPopUpSubHeaderText().getText(),MARKETING_VERBIAGE2);

        ValidationUtil.validateTestStep("Validating left aligned button verbiage",
                pageObjectManager.getPaymentPage().get$9FCFareClubPopUpYesIWantButton().getText(),BUTTON_1);

        ValidationUtil.validateTestStep("Validating left aligned under button verbiage",
                pageObjectManager.getPaymentPage().get$9FCFareClubPopUpYesIWantVerbageText().getText(),LEFT_VERBIAGE);

        ValidationUtil.validateTestStep("Validating right aligned button verbiage",
                pageObjectManager.getPaymentPage().get$9FCFareClubPopUpNoIDontWantButton().getText(),BUTTON_2);

        ValidationUtil.validateTestStep("Validating right aligned under button verbiage",
                pageObjectManager.getPaymentPage().get$9FCFareClubPopUpNoIDontWantVerbageText().getText(),RIGHT_VERBIAGE);

        pageObjectManager.getPaymentPage().get$9FCFareClubPopUpCloseImage().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getHomePageMethods().logoutFromApplication();
        pageObjectManager.getHeader().getSpiritLogoImage().click();

        //-- Step 1 Navigate to the payment page

        /****************************************************************************
         * ************************Navigate to Payment Page***************************
         ****************************************************************************/
        //Home Page
        WaitUtil.untilPageLoadComplete(getDriver());
        JSExecuteUtil.refreshBrowser(getDriver());
        WaitUtil.untilPageLoadComplete(getDriver());

        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Page
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE_1);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Information Page
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats Page
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options Page
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /****************************************************************************
         * *********************Payment Page Validations*****************************
         ****************************************************************************/
//-- Step 6:
        ValidationUtil.validateTestStep("Validate 9FC saving banner is displayed",
                pageObjectManager.getPaymentPage().getFareClubSavingVerbiageText().getText(),SAVING_TEXT.toUpperCase());
    }
}