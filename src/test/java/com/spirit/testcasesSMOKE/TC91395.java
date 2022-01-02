
package com.spirit.testcasesSMOKE;
import com.spirit.baseClass.BaseClass;
import com.spirit.utility.EmailUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.*;

// **********************************************************************************************
// TODO: [IN:24419]: CP: BP: Retrieve Password page: User does not receive the password reset email after inputting proper account email
// Test Case ID: TC91395
// TestCase : $9FC Booking _CP_MT_Flight Availiablity_ $9FC Dont know your password
// Description: A 9FC member who booked as a guest can reset their password after picking 9FC fare
// Created By : Anthony Cardona
// Created On : 14-May-2019
// Reviewed By: Salim Ansari
// Reviewed On: 17-May-2019
// **********************************************************************************************
public class TC91395 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"NineDFC","MyTrips","OneWay","DomesticDomestic","Within21Days","Adult","NonStop","BookIt",
            "MandatoryFields" , "NoBags", "NoSeats","CheckInOptions","MasterCard", "ChangeFlight" , "PassengerInfoPasswordReset" , "RetrievePasswordUI" , "Email"})
    public void NineFC_Booking_CP_MT_Flight_Availiablity_$9FC_Dont_know_your_password(@Optional("NA") String platform) {
        /******************************************************************************
         *******************Navigate to My Trip Retrieve Password Page*****************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91395 under SMOKE Suite on " + platform + " Browser", true);
        }

        //open browser
        openBrowser(platform);

        //launch application
        pageMethodManager.getHomePageMethods().launchSpiritApp();

        //return map of member credentials
        Map<String, String> memberCredentials = pageMethodManager.getMemberEnrollmentPageMethods().getNew9FCMemberInformation();

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LGA";
        final String DEP_DATE           = "10";
        final String ARR_DATE           = "NA";
        final String ADULTS             = "1";
        final String CHILDS             = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String SORT_BY            = "Departure";
        final String DEP_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADEVALUE       = "BookIt";

        //Options Page constant values
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //Payment Page Constant Values
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //Manage Travel New Flight
        final String MT_DEP_FLIGHT      = "9DFC";
        final String MT_FARE_TYPE       = "Member";

        //Keys for the Map
        final String TITLE              = "title";
        final String FIRST_NAME         = "firstName";
        final String LAST_NAME          = "lastName";
        final String DOB                = "birthDate";
        final String EMAIL              = "email";
        final String FS_NUMBER          = "fsNumber";
        final String START_PASSWORD     = "password";
        final String TEMP_PASSWORD      = "tempPassword";


        //STEP--1
        //Create a PNR and login via manage travel
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
        pageMethodManager.getFlightAvailabilityMethods().selectSortingOption("Dep", SORT_BY);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADEVALUE);

        //Passenger Info Methods for new 9DFC member
        //fill first passenger detail
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPassengerInfoPage().getAdultTitleListDropDown().get(0),memberCredentials.get(TITLE));
        pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0).sendKeys(memberCredentials.get(FIRST_NAME));
        pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(0).sendKeys(memberCredentials.get(LAST_NAME));
        pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(0).sendKeys(memberCredentials.get(DOB));
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //option Page Methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //payment Page Methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //confirmation Page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        /*****************************Start of manage Travel Path******************************/
        pageMethodManager.getHomePageMethods().loginToMyTrip();
        //Reservation Summary Page
        pageObjectManager.getReservationSummaryPage().getFlightSectionChangeFlightButton().click();
        //wait till pop up appear on page
        WaitUtil.untilPageLoadComplete(getDriver());

//Step 2: modify flight and select a 9FC flight
        //click on Departing edit box
        pageObjectManager.getReservationSummaryPage().getChangeFlightPopupDepEditLabel().click();
        pageObjectManager.getReservationSummaryPage().getChangeFlightPopupContinueButton().click();
        pageMethodManager.getFlightAvailabilityMethods().selectFlightFareType("Dep",MT_DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(MT_FARE_TYPE);
        WaitUtil.untilPageLoadComplete(getDriver());


//Step 3: Click on Log in Drop Down
        //9FC pop up to log in
        pageObjectManager.getPassengerInfoPage().get9FCUpsellLogInButton().click();

//Step 4: Click on Reset Password
        pageObjectManager.getPassengerInfoPage().getFCUpsellLogInResetPasswordTextBox().click();

        /******************************************************************************
         ************************Validation on Retrieve Password Page******************
         ******************************************************************************/
        //declare constant used in validation
        final String RETRIVE_PASSWORD_URL   = "retrieve-password";
        final String EMAIL_ERROR            = "A valid Free Spirit Number or associated Email address is required";
        final String RESET_PASSWORD_HEADER  = "Password Reset";
        final String RESET_PASSWORD_BODY    = "Your temporary password has been emailed to you. Please check your spam folder if you don't receive it within 30 minutes.";

        //Retrieve Password page constant variables
        String newPassword = "Spirit1!";


//Step 5: retrieve password page and input your FS number or email address and click send to get email
        //validate user taken to the Retrieve Password page
        WaitUtil.untilPageLoadComplete(getDriver());
        //veify user navigated to retrieve password page
        ValidationUtil.validateTestStep("The user correctly taken to the retrieve password  page", getDriver().getCurrentUrl(),RETRIVE_PASSWORD_URL);

        //enter correct FS number
        pageObjectManager.getRetrievePasswordPage().getEmailFSNumberTextBox().sendKeys((memberCredentials.get(FS_NUMBER)));

        //Click on send to send password reset email
        pageObjectManager.getRetrievePasswordPage().getResetPasswordButton().click();

        //get current time
        String startTime = TestUtil.getStringDateFormat("0","yyyy-MM-dd HH:mm:ss");


        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

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

//Step 6: Follow Instructions on password Reset Email to reset password
        //store temporary password
        memberCredentials.put(TEMP_PASSWORD , EmailUtil.openOutlookInNewTab(getDriver(),memberCredentials.get("firstName"),startTime,"TemporaryPasswordFromEmail") );

        //Use email and temporary password to
        pageObjectManager.getHomePage().getUserNameBox().sendKeys(memberCredentials.get(EMAIL));
        pageObjectManager.getHomePage().getPasswordBox().sendKeys(memberCredentials.get(TEMP_PASSWORD));
        pageObjectManager.getHomePage().getLoginButton().click();

        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getHomePage().getNewPasswordBox().sendKeys(newPassword);
        pageObjectManager.getHomePage().getConfirmNewPasswordBox().sendKeys(newPassword);
        pageObjectManager.getHomePage().getLoginButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("User Loged in to Spirit Application successfully", pageObjectManager.getHomePage().getLoginUserIconImage().size()>0);
    }

}