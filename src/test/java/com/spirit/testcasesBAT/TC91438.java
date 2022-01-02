package com.spirit.testcasesBAT;

import com.spirit.baseClass.*;
import com.spirit.dataType.*;
import com.spirit.enums.Context;
import com.spirit.managers.*;
import com.spirit.utility.*;
import org.openqa.selenium.By;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC91438
//Description: CP_BP_Payment Page_Join 9DFC Trial
//Created By : Sunny Sok
//Created On : 20-Mar-2019
//Reviewed By: Salim Ansari
//Reviewed On: 22-Mar-2019
//**********************************************************************************************

public class TC91438 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups={"NineDFC","BookPath","RoundTrip","DomesticDomestic","Outside21Days","Adult","Connecting","BookIt",
                  "NoBags","NoSeats","CheckInOptions","TrialMembership","MasterCard","PaymentUI","ConfirmationUI","AccountProfileUI"})
    public void CP_BP_Payment_Page_Join_9DFC_Trial(@Optional("NA") String platform) {
    	/******************************************************************************
    	 ***********************Navigate to Customer Info Page*************************
    	 ******************************************************************************/
    	//Mention Suite and Browser in reports 
    	if(!platform.equals("NA")) {
    		ValidationUtil.validateTestStep("Starting Test Case ID TC91438 under BAT Suite on " + platform + " Browser"   , true);
    	}

        //Home Page Constant Values
        final String LANGUAGE 				= "English";
        final String JOURNEY_TYPE 			= "Flight";
        final String TRIP_TYPE 				= "RoundTrip";
        final String DEP_AIRPORTS 			= "AllLocation";
        final String DEP_AIRPORT_CODE 		= "FLL";
        final String ARR_AIRPORTS 			= "AllLocation";
        final String ARR_AIRPORT_CODE 		= "LGA";
        final String DEP_DATE 				= "25";
        final String ARR_DATE 				= "30";
        final String ADULTS 				= "1";
        final String CHILDS 				= "0";
        final String INFANT_LAP 			= "0";
        final String INFANT_SEAT 			= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 			= "Connecting";
        final String ARR_Flight 			= "Connecting";
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
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADEVALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /******************************************************************************
         ************************Validation Payments Page********************************
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
        ValidationUtil.validateTestStep("User verify terms and conditions for 9DFC Trail is displayed on Payment Page",
                TestUtil.verifyElementDisplayed(getDriver(), By.xpath(pageObjectManager.getPaymentPage().xpath_MemberTrailTermsAndConditionPanel)));

        /******************************************************************************
         **********************************9FC Sign up*********************************
         ******************************************************************************/
        //buy 9dfc trail membership
        pageObjectManager.getPaymentPage().getTrialMembershipLabel().click();

        //Waiting for popup
        WaitUtil.untilPageLoadComplete(getDriver());
        
        //complete 9fc popup
        //get 9FC Credentials from json file
        MemberEnrollmentData memberEnrollmentData = FileReaderManager.getInstance().getJsonReader().getMemberEnrollmentDetailsByTab("NineFCAccountTab");

        //Clicking on Sign up button
        pageObjectManager.getPassengerInfoPage().get9FCUpsellSignUpButton().click();

        //Sending Password
        pageObjectManager.getPassengerInfoPage().get9FCUpselSignUpChoosePasswordTextBox().sendKeys(memberEnrollmentData.createPassword);

        //Confirming Password
        pageObjectManager.getPassengerInfoPage().get9FCUpsellSignUpConfirmPasswordTextBox().sendKeys(memberEnrollmentData.confirmPassowrd);

        //Clicking on sign up with email
        pageObjectManager.getPassengerInfoPage().get9FCUpsellSignUpWithEmailButton().click();

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
        WaitUtil.untilTimeCompleted(2000);

        JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getConfirmationPage().getOptionsBreakDownLink());

        //clicking on options container carrot
        //pageObjectManager.getConfirmationPage().getOptionsBreakDownLink().click();

        //wait for 30 sec
        WaitUtil.untilTimeCompleted(2000);
        
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
        final String MyAccount_URL      = "/account/profile";
        final String Home_URL           = "spirit.com";
        final String PaidMembershipType = "$9 Fare Club";

        //Return to homepage
        pageObjectManager.getHeader().getSpiritLogoImage().click();

        //wait till url appear on web
        WaitUtil.untilPageURLVisible(getDriver(), Home_URL);
        WaitUtil.untilPageLoadComplete(getDriver());
        
        //login to application
        pageMethodManager.getHomePageMethods().loginToApplication(scenarioContext.getContext(Context.CUSTOMER_EMAIL).toString(), memberEnrollmentData.confirmPassowrd);

        //navigate to my account drop down menu
        pageObjectManager.getHeader().getUserDropDown().click();

        //access your FS user account
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getMyAccountUserLink().click();

        //wait till url appear on web
        WaitUtil.untilPageURLVisible(getDriver(), MyAccount_URL);
        
        //verify user is apart of the 9FC trial period
        ValidationUtil.validateTestStep("User verify 9FC Paid Type membership on Account Profile Page",
        		pageObjectManager.getAccountProfilePage().getYourMembershipTypeText().getText(),PaidMembershipType);
    }
}
