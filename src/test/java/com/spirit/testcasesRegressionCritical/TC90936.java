package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.*;
import com.spirit.dataType.*;
import com.spirit.managers.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;

public class TC90936 extends BaseClass {
  //**********************************************************************************************
//Test Case ID: TC90936
//Description: $9FC Booking_CP_BP_Seats_ Dynamic Shopping Cart  test Wireframe of 9DFC sign up
//Created By : Alex Rodriguez
//Created On : 26-Jun-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
  @Parameters({"platform"})
  @Test(groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "NineDFC" ,"Connecting", "BookIt" , "NoBags" , "NoSeats" ,"CheckInOptions" , "MasterCard" , "DynamicShoppingCartUI","AccountProfileUI"})

  public void $9FC_Booking_CP_BP_Seats_Dynamic_Shopping_Cart_test_Wireframe_of_9DFC_sign_up(@Optional("NA") String platform) {
    if (!platform.equals("NA")) {
      ValidationUtil.validateTestStep("Starting Test Case ID TC90936 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
    }
    //Home Page Constant Values
    final String LANGUAGE         = "English";
    final String JOURNEY_TYPE     = "Flight";
    final String TRIP_TYPE        = "OneWay";
    final String DEP_AIRPORTS     = "AllLocation";
    final String DEP_AIRPORT_CODE = "LGA";
    final String ARR_AIRPORTS     = "AllLocation";
    final String ARR_AIRPORT_CODE = "FLL";
    final String DEP_DATE         = "3";
    final String ARR_DATE         = "NA";
    final String ADULT            = "1";
    final String CHILD            = "0";
    final String INFANT_LAP       = "0";
    final String INFANT_SEAT      = "0";

    //Flight Availability Page Constant Values
    final String DEP_FLIGHT       = "Nonstop";
    final String FARE_TYPE        = "Standard";
    final String UPGRADE_VALUE    = "BookIt";

    //Bags Page Method
    final String DEP_BAGS         = "Carry_1|Checked_2";

    //Seats Page Constant Values
    final String TERMS_AND_CONDITIONS_URL = "/Shared/en-us/Documents/9FC_Terms_and_Conditions.pdf";

    //Options Page Constant Values
    final String OPTIONS_VALUE	    = "CheckInOption:MobileFree";

    //Payment Page Constant Values
    final String CARD_TYPE          = "MasterCard";
    final String TRAVEL_GUARD       = "NotRequired";


    //Confirmation Page Constant Values
    final String BOOKING_STATUS     = "Confirmed";
    final String CONFIRMATION_URL   = "book/confirmation";

    //Account Profile Page Constant Values
    final String MyAccount_URL = "/account/profile";
    final String PaidMembershipType = "$9 Fare Club";

    //open browser
    openBrowser(platform);
    pageMethodManager.getHomePageMethods().launchSpiritApp();


    //****************************************************************************
    //**************************Home Page Methods*********************************
    //****************************************************************************/
//    Step 1
    //click on footer Member enrollment link
    pageObjectManager.getFooter().getFreeSpiritLink().click();

    //Enrollment Page methods
    pageMethodManager.getMemberEnrollmentPageMethods().createNewFSMember();
    pageObjectManager.getHeader().getSpiritLogoImage().click();
    WaitUtil.untilPageLoadComplete(getDriver(), (long) 500);

    pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
    pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
    pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
    pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
    pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
    pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
    pageMethodManager.getHomePageMethods().clickSearchButton();

    //****************************************************************************
    //* *************Flight Availability Page Methods*****************************
    //****************************************************************************/
    pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
    pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
    pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

    //****************************************************************************
    //*****************Passenger Information Page Methods************************
    //****************************************************************************/
    pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
    pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
    pageMethodManager.getPassengerInfoMethods().clickContinueButton();

    //****************************************************************************
    //*************************Bags Page Methods*********************************
    //****************************************************************************/
    WaitUtil.untilPageLoadComplete(getDriver());
    pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

    //****************************************************************************
    //*************************Seats Page Methods*********************************
    //****************************************************************************
    WaitUtil.untilPageLoadComplete(getDriver());
//    Step 2
    TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getArrowJoinFareClubAndSaveItineraryImage());
    pageObjectManager.getHeader().getArrowJoinFareClubAndSaveItineraryImage().click();
    WaitUtil.untilTimeCompleted(500);
    TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getMemberTermsAndConditionItineraryLink());
//    Step 3-5
    //Click on the Terms and Conditions Link
    //wait for 1 sec
    WaitUtil.untilTimeCompleted(1000);
    //open new tab using java script
    JSExecuteUtil.openNewTabWithGivenURL(getDriver(),pageObjectManager.getMemberEnrollmentPage().getTermsAndConditionsLink().getAttribute("href"));

    //switch to new tab
    TestUtil.switchToWindow(getDriver(),1);

    //verify url navigated
    ValidationUtil.validateTestStep("User verify Term and Condition link is navigated correctly",
            getDriver().getCurrentUrl(),TERMS_AND_CONDITIONS_URL);

    //close new open tab
    getDriver().close();

    //switch to default tab
    TestUtil.switchToWindow(getDriver(),0);

    WaitUtil.untilPageLoadComplete(getDriver(), (long) 2000);

//    Step 6-8
    joinNineFCFromShoppingCart();

//    Step 9
    WaitUtil.untilPageLoadComplete(getDriver());
    pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

    //****************************************************************************
    //*************************Options Page Methods*********************************
    //****************************************************************************/
    pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
    pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

    //****************************************************************************
    //*************************Payment Page Methods*********************************
    //****************************************************************************/
    pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
    pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
    pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

    //*****************************************************************************
    //*****************Confirmation Page Method************************************
    //*****************************************************************************
    pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
    pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

    //verify booking is confirmed
    ValidationUtil.validateTestStep("Verify complete booking completed and  guest reached the Confirmation Page",
            getDriver().getCurrentUrl(),CONFIRMATION_URL);

    //verify booking is confirmed
    ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
            pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);

    //Access your new user account
    WaitUtil.untilPageLoadComplete(getDriver());
    pageObjectManager.getHeader().getUserDropDown().click();

    WaitUtil.untilPageLoadComplete(getDriver());
    pageObjectManager.getHeader().getMyAccountUserLink().click();

    //Wait till url is visible on web
    WaitUtil.untilPageURLVisible(getDriver(), MyAccount_URL);

    //ToDO Not sure if bug but have to refresh page to see the Membership Upgrade
    JSExecuteUtil.refreshBrowser(getDriver());
    WaitUtil.untilPageLoadComplete(getDriver());

    //Verify user is apart of the 9FC trial period
    ValidationUtil.validateTestStep("User verify 9FC Paid Type membership on Account Profile Page",
            pageObjectManager.getAccountProfilePage().getYourMembershipTypeText().getText(),PaidMembershipType);

  }

  private void joinNineFCFromShoppingCart(){

    WaitUtil.untilPageLoadComplete(getDriver(), (long)2000);
    if(!pageObjectManager.getHeader().getArrowJoinFareClubAndSaveItineraryImage().getAttribute("style").contains("0deg")){
      pageObjectManager.getHeader().getArrowJoinFareClubAndSaveItineraryImage().click();
    }
    WaitUtil.untilTimeCompleted(500);
    pageObjectManager.getHeader().getJoinNowAndSaveItineraryButton().click();
    WaitUtil.untilPageLoadComplete(getDriver());

    //************************************************************
    //*****************If Signing up as Guest*********************
    //************************************************************

    if(TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getLoginButton())){
      //get password value from json file
      MemberEnrollmentData memberEnrollmentData = FileReaderManager.getInstance().getJsonReader().getMemberEnrollmentDetailsByTab("NineFCAccountTab");

      //Clicking on Sign up button
      pageObjectManager.getPassengerInfoPage().get9FCUpsellSignUpButton().click();

      //Sending Password
      pageObjectManager.getPassengerInfoPage().get9FCUpselSignUpChoosePasswordTextBox().sendKeys(memberEnrollmentData.createPassword);

      //Confirming Password
      pageObjectManager.getPassengerInfoPage().get9FCUpsellSignUpConfirmPasswordTextBox().sendKeys(memberEnrollmentData.confirmPassowrd);

      //Clicking on sign up with email
      pageObjectManager.getPassengerInfoPage().get9FCUpsellSignUpWithEmailButton().click();

      //wait until page bags is appear on web
      WaitUtil.untilPageLoadComplete(getDriver());

    }

    //********************************************************************
    //*******Verify the 9fc membership was added to the cart**************
    //********************************************************************
    if(!pageObjectManager.getHeader().getArrowYourItineraryImage().getAttribute("style").contains("0deg")){
      pageObjectManager.getHeader().getArrowYourItineraryImage().click();
    }
    WaitUtil.untilTimeCompleted(500);
    pageObjectManager.getHeader().getArrowOptionsItineraryImage().click();
    WaitUtil.untilTimeCompleted(500);


    //declare constant used in Validation
    final String $9FC_LABEL       = "$9 Fare Club Membership";
    final String $9FC_PRICE       = "$59.95";
    WebElement NineFCMemberLabel  =  getDriver().findElement(By.xpath("//div/p[contains(text(),'$9 Fare Club Membership')]"));
    WebElement NineFCMemberPrice  =  getDriver().findElement(By.xpath("//div/p[contains(text(),'$59.95')]"));

    //verify 9DFC membership verbiage in Cart
    ValidationUtil.validateTestStep("User verify 9FC label is displayed in Shopping Cart",
            NineFCMemberLabel.getText(), $9FC_LABEL);

    //verify 9DFC membership Price in Cart
    ValidationUtil.validateTestStep("User verify 9FC Price is displayed in Shopping Cart",
            NineFCMemberPrice.getText(), $9FC_PRICE);
  }
}
