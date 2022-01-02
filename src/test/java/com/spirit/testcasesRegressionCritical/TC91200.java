package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC91200
//Description: My Account_CP_$9FC My Trips Display PNR List
//Created By: Anthony Cardona
//Created On: 13-Jun-2019
//Reviewed By: Salim Ansari
//Reviewed On: 1-July-2019
//**********************************************************************************************
public class TC91200 extends BaseClass{

    @Parameters({"platform"})
    @Test(groups = {"NineDFC" , "AccountProfileUI"})
    public void My_Account_CP_$9FC_My_Trips_Display_PNR_List(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91200 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String LOGIN_ACCOUNT      = "NineDFCEmail";
        final String DATE               = "Date";
        final String ORIGIN             = "Origin";
        final String DESTINATION        = "Destination";
        final String CONFIRMATION_CODE  = "Confirmation Code";
        final String MILES              = "Miles";
        final String CITY_PATTERN       = "^(.)*\\s\\(+([A-z]){3}+\\)$";
        final String CODE_PATTERN       = "^([A-Z0-9]){6}$";
        final String DATE_PATTERN       = "^(.)*\\s([0-9]){1,2}\\,\\s([0-9]){4}$";
        final String RESERVATION_URL    = "my-trips/reservation-summary";

        //open browser
        openBrowser(platform);

//Step 1, 2 : Go to Test Environment
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//Step 3: Log in to 9DFC account
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);

//Step 4: Click on user drop Down
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getUserDropDown().click();

//Step 5: Click on My Reservations
        WaitUtil.untilTimeCompleted(1500);
        pageObjectManager.getHeader().getMyReservationUserLink().click();
        ValidationUtil.validateTestStep("The user clicks on My Reservations link", true);
        WaitUtil.untilPageLoadComplete(getDriver());

//Step 6: Scroll to Current Reservations
        JSExecuteUtil.scrollDownToElementVisible(getDriver(),pageObjectManager.getAccountProfilePage().getCurrentReservationsHeaderText());

//Step 7: Validate Current reservation table content
        //VALIDATE TABLE HEADERS
        ValidationUtil.validateTestStep("Current reservation Table Label Date field is displayed correctly" , DATE , pageObjectManager.getAccountProfilePage().getCurrentReservationsDateLabel().getText());
        ValidationUtil.validateTestStep("Current reservation Table Label Origin field is displayed correctly" , ORIGIN , pageObjectManager.getAccountProfilePage().getCurrentReservationsOriginLabel().getText());
        ValidationUtil.validateTestStep("Current reservation Table Label Destination field is displayed correctly" , DESTINATION , pageObjectManager.getAccountProfilePage().getCurrentReservationsDestinationLabel().getText());
        ValidationUtil.validateTestStep("Current reservation Table Label ConfirmationCode field is displayed correctly", CONFIRMATION_CODE , pageObjectManager.getAccountProfilePage().getCurrentReservationsConfirmationCodeLabel().getText());
        ValidationUtil.validateTestStep("Current reservation Table Label Miles field is displayed correctly" , MILES , pageObjectManager.getAccountProfilePage().getCurrentReservationsMilesLabel().getText());

        ValidationUtil.validateTestStep("Current reservation Date field is displayed correctly" , pageObjectManager.getAccountProfilePage().getCurrentReservationsDateText().get(0).getText().trim().matches(DATE_PATTERN));
        ValidationUtil.validateTestStep("Current reservation Origin field is displayed correctly" , pageObjectManager.getAccountProfilePage().getCurrentReservationsOriginText().get(0).getText().trim().matches(CITY_PATTERN));
        ValidationUtil.validateTestStep("Current reservation Destination field is displayed correctly" ,pageObjectManager.getAccountProfilePage().getCurrentReservationsDestinationText().get(0).getText().trim().matches(CITY_PATTERN));
        ValidationUtil.validateTestStep("Current reservation ConfirmationCode field is displayed correctly", pageObjectManager.getAccountProfilePage().getCurrentReservationsConfirmationCodeText().get(0).getText().trim().matches(CODE_PATTERN));
        ValidationUtil.validateTestStep("Current reservation Miles field is displayed correctly" , pageObjectManager.getAccountProfilePage().getCurrentReservationsMilesText().get(0).getText().trim().length()>1);

//Step 8: Validate last column on the table has hyperlink
        ValidationUtil.validateTestStep("Current reservation Click Here field is displayed correctly" , pageObjectManager.getAccountProfilePage().getCurrentReservationsClickHereButton().get(0).isDisplayed());

        //Store PNR
        String TempPNR = pageObjectManager.getAccountProfilePage().getCurrentReservationsConfirmationCodeText().get(0).getText();

//Step 9: Click on the Hyperlink associated with that PNR
        pageObjectManager.getAccountProfilePage().getCurrentReservationsClickHereButton().get(0).click();
        WaitUtil.untilPageLoadComplete(getDriver());

//Validate that the Reservation is correct
        ValidationUtil.validateTestStep("The user redirected to the reservation summary page",getDriver().getCurrentUrl(),RESERVATION_URL);
        ValidationUtil.validateTestStep("The user redirected to the correct reservation" , TempPNR , pageObjectManager.getReservationSummaryPage().getConfirmationCodeNumberText().get(0).getText().replace("Confirmation Code: ",""));
    }
}