package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.EmailUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//**********************************************************************************************
//Test Case ID: TC90976
//Description: $9FC Booking _CP_CI_Flight Availiablity_ $9FC Dont know your password
//Created By : Anthony Cardona
//Created On : 18-JUN-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC90976 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"CheckIn" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "NineDFC" , "NonStop" , "BookIt" ,"MandatoryFields", "NoBags" , "NoSeats" , "CheckInOptions" , "MasterCard" , "ChangeFlight" , "PassengerInfoPasswordReset" ,"RetrievePasswordUI", "Email"})
    public void $9FC_Booking_CP_CI_Flight_Availiablity_$9FC_Dont_know_your_password(@Optional("NA") String platform) {
        /******************************************************************************
         ************************Navigate to Retrieve Password Page********************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90976 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //open browser
        openBrowser(platform);

//pre-req= create a member, store information and log out
        //launch application
        pageMethodManager.getHomePageMethods().launchSpiritApp();

        //return map of member credentials
        Map<String, String> memberCredentials = pageMethodManager.getMemberEnrollmentPageMethods().getNew9FCMemberInformation();

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "Oneway";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "ATL";
        final String DEP_DATE 			= "0";
        final String ARR_DATE 			= "NA";
        final String ADULT  			= "1";
        final String CHILD  			= "0";
        final String INFANT_LAP 		= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability constant variables
        final String DEP_FLIGHT         = "Nonstop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page Constant Values
        final String OPTIONS_VALUE	    = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //Confirmation Page Constant Values
        final String BOOKING_STATUS     = "Confirmed";
        final String CONFIRMATION_URL   = "book/confirmation";

        //Reservation Summary page constant values
        final String NEW_DEP_AIRPORTS   = "LGA";
        final String NEW_ARR_AIRPORTS   = "LAX";
        final String CHANGE_FLIGHT_POPUP= "Continue";

        //CheckIn path flight availability page constant values
        final String NEW_DEP_FLIGHT     = "9DFC";
        final String NEW_FARE_TYPE      = "Member";

//Step 1 create a booking within 24 hours and retrieve reservation on check in path to change flight
        /****************************************************************************
         * ************************Home Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        /****************************************************************************
         * *************Flight Availability Page Methods*****************************
         ****************************************************************************/

        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        /****************************************************************************
         * *****************Passenger Information Page Methods************************
         ****************************************************************************/
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPassengerInfoPage().getAdultTitleListDropDown().get(0),memberCredentials.get("title"));
        pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0).sendKeys(memberCredentials.get("firstName"));
        pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(0).sendKeys(memberCredentials.get("lastName"));
        pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(0).sendKeys(memberCredentials.get("birthDate"));

        //Filling mandatory passenger fields
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /****************************************************************************
         * ************************Bags Page Methods*********************************
         ****************************************************************************/

        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
        WaitUtil.untilPageLoadComplete(getDriver());

        /****************************************************************************
         * ***********************Seats Page Methods*********************************
         ****************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        /****************************************************************************
         * *********************Options Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /****************************************************************************
         * *********************Payment Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        /*************************************************************************************************************
         * *********************************Confirmation Page Method**************************************************
         *************************************************************************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page",
                getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);

        //login to checkin path
        pageMethodManager.getHomePageMethods().loginToCheckIn();

        //Change the Origin and destination to get to the Check in FA page
        pageMethodManager.getReservationSummaryPageMethods().changeFlightOnChangeFlightPopup("Dep", NEW_DEP_AIRPORTS, NEW_ARR_AIRPORTS,"NA");
        pageMethodManager.getReservationSummaryPageMethods().continueCancelOnChangeFlightPopup(CHANGE_FLIGHT_POPUP);

//Step 2 select a 9DFC fare
        pageMethodManager.getFlightAvailabilityMethods().selectFlightFareType("Dep",NEW_DEP_FLIGHT);

//Step 3 click continue with Member fare
        //select member fare on Flight Availability page
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(NEW_FARE_TYPE);

//Step 4: Click on log in on the 9DFC upsell
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getPassengerInfoPage().get9FCUpsellLogInButton().click();

//Step 5: Click on Reset Password on the 9DFC upsell
        pageObjectManager.getPassengerInfoPage().getFCUpsellLogInResetPasswordTextBox().click();

        /******************************************************************************
         ************************Validation on Retrieve Password Page******************
         ******************************************************************************/
        //declare constant used in validation
        final String NEW_PASSWORD           = "Spirit1!";
        final String RETRIVE_PASSWORD_URL   = "retrieve-password";
        final String RESET_PASSWORD_HEADER  = "Password Reset";
        final String RESET_PASSWORD_BODY    = "Your temporary password has been emailed to you. Please check your spam folder if you don't receive it within 30 minutes.";

//Step 6: retrieve password page and input your FS number or email address and click send to get email
        //validate user taken to the Retrieve Password page
        WaitUtil.untilPageLoadComplete(getDriver());

        //veify user navigated to retrieve password page
        ValidationUtil.validateTestStep("The user correctly taken to the retrieve password  page", getDriver().getCurrentUrl(),RETRIVE_PASSWORD_URL);

        //enter correct FS number
        pageObjectManager.getRetrievePasswordPage().getEmailFSNumberTextBox().sendKeys((memberCredentials.get("fsNumber")));

        //Click on send to send password reset email
        pageObjectManager.getRetrievePasswordPage().getResetPasswordButton().click();

        //get current time
        String startTime = TestUtil.getStringDateFormat("0","yyyy-MM-dd HH:mm:ss");

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(3500);

        //verify resert password header
        ValidationUtil.validateTestStep("The Password Reset Header is correct on Retrieve Password Page" ,
                pageObjectManager.getRetrievePasswordPage().getResetPasswordHeaderText().getText() , RESET_PASSWORD_HEADER );

        //verify reset password body
        ValidationUtil.validateTestStep("The Password Reset Body Text is correct on Retrieve Password Page" ,
                pageObjectManager.getRetrievePasswordPage().getResetPasswordBodyText().getText() , RESET_PASSWORD_BODY );

        //verify go to login page
        ValidationUtil.validateTestStep("The Password Reset \"Go To Login Page\" is displayed on Retrieve Password Page" ,
                pageObjectManager.getRetrievePasswordPage().getResetPasswordGoToLoginPageButton().isDisplayed());

        //click to get the log in pop-up
        pageObjectManager.getRetrievePasswordPage().getResetPasswordGoToLoginPageButton().click();

//Step 7: Follow Instructions on password Reset Email to reset password
        //store temporary password
        memberCredentials.put("tempPassword" , EmailUtil.openOutlookInNewTab(getDriver(),memberCredentials.get("firstName"),startTime,"TemporaryPasswordFromEmail") );

        //Use email and temporary password to
        pageObjectManager.getHomePage().getUserNameBox().sendKeys(memberCredentials.get("email"));
        pageObjectManager.getHomePage().getPasswordBox().sendKeys(memberCredentials.get("tempPassword"));
        pageObjectManager.getHomePage().getLoginButton().click();

        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getHomePage().getNewPasswordBox().sendKeys(NEW_PASSWORD);
        pageObjectManager.getHomePage().getConfirmNewPasswordBox().sendKeys(NEW_PASSWORD);
        pageObjectManager.getHomePage().getLoginButton().click();

        //wait unntil page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //verify user login to application
        ValidationUtil.validateTestStep("User Loged in to Spirit Application successfully",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getSpiritLogoImage()));
    }
}