
package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC91441
//Description:  CP_BP_Payment Page_FS_Join 9DFC Trial
//Created By : Sunny Sok
//Created On : 20-Mar-2019
//Reviewed By: Salim Ansari
//Reviewed On: 11-April-2019
//**********************************************************************************************
public class TC91441 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath , OneWay" , "DomesticDomestic" , "Outside21Days" , "Adult" , "NineDFC" ,
            "NonStop" , "BookIt" ,"NoBags" , "NoSeats" , "TrialMembership" , "MasterCard" , "AccountProfileUI" ,
            "PaymentUI","CheckInOptions", "ConfirmationUI"})
    public void CP_BP_Payment_Page_FS_Join_9DFC_Trial(@Optional("NA") String platform) {
        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91441 under SMOKE Suite on " + platform + " Browser"   , true);
        }
        /******************************************************************************
         ************************Navigation on Payments Page***************************
         ******************************************************************************/
        //Home Page Constant Values
        final String LANGUAGE 				= "English";
        final String JOURNEY_TYPE 			= "Flight";
        final String TRIP_TYPE 				= "OneWay";
        final String DEP_AIRPORTS 			= "AllLocation";
        final String DEP_AIRPORT_CODE 		= "FLL";
        final String ARR_AIRPORTS 			= "AllLocation";
        final String ARR_AIRPORT_CODE 		= "LGA";
        final String DEP_DATE 				= "25";
        final String ARR_DATE 				= "NA";
        final String ADULTS 				= "1";
        final String CHILDS 				= "0";
        final String INFANT_LAP 			= "0";
        final String INFANT_SEAT 			= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 			= "NonStop";
        final String FARE_TYPE 				= "Standard";
        final String UPGRADEVALUE 			= "BookIt";

        //Option page Constant Values
        final String OPTION_VALUE 			= "CheckInOption:MobileFree";

        //Payment page constant values
        final String TRAVEL_GUARD 			= "NotRequired";
        final String PAYMENT_CARD 			= "MasterCard";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

        //click on footer Member enrollment link
        pageObjectManager.getFooter().getFreeSpiritLink().click();

        //Enrollment Page methods
        pageMethodManager.getMemberEnrollmentPageMethods().createNewFSMember();
        pageObjectManager.getHeader().getSpiritLogoImage().click();

        //wait for page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //home page methods
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADEVALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /******************************************************************************
         ************************Validation on Payments Page***************************
         ******************************************************************************/
        //declare constant used in Validation
        final String PAYMENT_URL 		= "/book/payment";
        final String TRAIL_9DFC_LABEL	= "Join the $9 Fare Club 60-day Trial Membership for exclusive fares and bag discounts, on your next booking!";
        final String TRAIL_9DFC_PRICE 	= "$19.95";

        WaitUtil.untilPageLoadComplete(getDriver());

        //wait till url appear on web
        WaitUtil.untilPageURLVisible(getDriver(), PAYMENT_URL);

        //verify 9fc 60 day trial is displayed
        ValidationUtil.validateTestStep("User Verify Trial membership is displayed on Payment Page",
                pageObjectManager.getPaymentPage().getTrialMembershipLabel().getText(),TRAIL_9DFC_LABEL);

        //verify 9fc price
        ValidationUtil.validateTestStep("User Verify Trial membership equals $19.95 on Payment Page",
                pageObjectManager.getPaymentPage().getTrialMembershipPriceText().getText(),TRAIL_9DFC_PRICE);

        //click on 9fc carrot
        pageObjectManager.getPaymentPage().getTrialMembershipTermsAndConditionsIcon().click();

        //verify terms and conditions are displayed
        ValidationUtil.validateTestStep("User verify terms and conditions for 9DFC Trail is displayed on Payment Page", pageObjectManager.getPaymentPage().getMemberTrailTermsAndConditionPanel().isDisplayed());

        /******************************************************************************
         **********************************9FC Sign up*********************************
         ******************************************************************************/
        //buy 9dfc trail membership
        pageObjectManager.getPaymentPage().getTrialMembershipLabel().click();

        //Waiting for popup
        WaitUtil.untilPageLoadComplete(getDriver());

        //complete payment
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(PAYMENT_CARD);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        /******************************************************************************
         *************************Validation Confirmation Page**************************
         ******************************************************************************/
        //declare constant used in Validation
        final String BREAKDOWN_9DFC_LABEL	 = "$9 FARE CLUB MEMBERSHIP";
        final String BREAKDOWN_9DFC_PRICE	 = "$19.95";

        //close rokt pop
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //verify the user was enrolled into the 9FC trial period and charged $19.95.
        //clicking on total due container carrot
        pageObjectManager.getConfirmationPage().getTotalPaidBreakDownLink().click();

        //wait for 30 sec
        WaitUtil.untilTimeCompleted(500);

        //clicking on options container carrot
        pageObjectManager.getConfirmationPage().getOptionsBreakDownLink().click();

        //wait for 30 sec
        WaitUtil.untilTimeCompleted(500);

        //verify 9DFC trail membership verbiage
        ValidationUtil.validateTestStep("User verify 9FC label is displayed in breakdown",
                pageObjectManager.getConfirmationPage().get9FCClubMembershipVerbiageLabel().getText(),BREAKDOWN_9DFC_LABEL);

        //verify 9DFC trail membership
        ValidationUtil.validateTestStep("User verify 9FC price in breakdown",
                pageObjectManager.getConfirmationPage().get9FCClubMembershipPriceText().getText(),BREAKDOWN_9DFC_PRICE);

        /******************************************************************************
         *************************Validation My Account Page**************************
         ******************************************************************************/
        //declare constants used in Validation
        final String MyAccount_URL          = "/account/profile";
        final String Home_URL               = "spirit.com";
        final String PAID_MEMBERSHIP_TYPE   = "$9 Fare Club";
        final String PAID_MEMBERSHIP_DAYS   = "60";

        //Return to homepage
        pageObjectManager.getHeader().getSpiritLogoImage().click();

        //wait till url appear on web
        WaitUtil.untilPageURLVisible(getDriver(), Home_URL);
        WaitUtil.untilPageLoadComplete(getDriver());

        //navigate to my account drop down menu
        pageObjectManager.getHeader().getUserDropDown().click();

        //access your FS user account
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getMyAccountUserLink().click();

        //wait till url appear on web
        WaitUtil.untilPageURLVisible(getDriver(), MyAccount_URL);

/****Must refresh page for account to udpate with 9fc
 * BUG23544 - 9FC membership type is not updated on My Account page after free spirit member selects 9FC trail membership on the payment page during booking path and goes to my account. 9FC membership type only updates after clicking refresh
 */
        getDriver().navigate().refresh();

        WaitUtil.untilPageLoadComplete(getDriver());

        //verify user is apart of the 9FC trial period
        ValidationUtil.validateTestStep("User verify 9FC Paid Type membership on Account Profile Page",
                pageObjectManager.getAccountProfilePage().getYourMembershipTypeText().getText(),PAID_MEMBERSHIP_TYPE);

        //verify user is apart of the 9FC trial period
        ValidationUtil.validateTestStep("User verify 9FC membership days left on Account Profile Page",
                pageObjectManager.getAccountProfilePage().getDaysLeftInMembershipText().getText(),PAID_MEMBERSHIP_DAYS);
    }

}
