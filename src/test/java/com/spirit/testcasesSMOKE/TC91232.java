package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.*;

// **********************************************************************************************
// TestCase : My Trips_CP_MT_ FS_My Trips 5 Upcomming
// Description: validate the functionality of the my trips table on home page when logged in as a FS member
// Created By : Anthony Cardona
// Created On : 15-April-2019
// Reviewed By: Salim Ansari
// Reviewed On: 24-April-2019
// **********************************************************************************************

public class TC91232 extends BaseClass {
    @Parameters ({"platform"})
    @Test (groups = {"Header" , "FreeSpirit" , "MyTrips", "HomeUI"})
    public void My_Trips_CP_MT_FS_My_Trips_5_Upcoming (@Optional("NA")String platform) {

        /******************************************************************************
         ****************************Navigate to My Trip Page*****************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91232 under SMOKE Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE                       = "English";
        final String LOGIN_EMAIL                    = "FSEmail";

        //my trip verbage
        final String MYTRIP_DESCRIPTION             = "Change or cancel your flight, and add extras like bags or seat assignments. Your Confirmation Code can be found in your confirmation email.";
        final String MYTRIP_ROUTE                   = "Route";
        final String MYTRIP_DATE                    = "Date";
        final String MYTRIP_CONFIRMATION_CODE       = "Confirmation Code";
        final String MYTRIP_DATE_PATTERN            = "MMM dd, yyyy";
        final String MYTRIP_CONTINUE_ATTRIBUTE      = "class";
        final String MYTRIP_CONTINUE_ATTRIBUTE_VALUE= "btn-link";
        final String RESEVATION_SUMMARY_URL         = "reservation-summary";
        final int SECOND_INDEX                      = 1;
        final String LAST_NAME                      = "Test";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();

//Step 1
        //log in to a member have more than 10 future bookings for a FS member

        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_EMAIL);
//Step 2
        //click on my trips on home page

        pageObjectManager.getHomePage().getMyTripPathLink().click();

//Step 3
        //Validate text on my trips tab
        String actualText = pageObjectManager.getHomePage().getManageTravelVerbageText().getText();
        ValidationUtil.validateTestStep("The text matches for the manage travel description", MYTRIP_DESCRIPTION, actualText.replace("   "," ").trim());

//Step 4
        //click on confirmation code hyperlink
        pageObjectManager.getHomePage().getManageTravelConfirmationCodeLink().click();
        WaitUtil.untilTimeCompleted(1200);
        //validate pop-up displayed
        ValidationUtil.validateTestStep("The Confirmation Code Pop-Up is displayed"
                , pageObjectManager.getHomePage().getManageTravelConfirmationCodePopUpHeaderText().isDisplayed());

//Step 5
        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //close pop-up
        //pageObjectManager.getHomePage().getManageTravelConfirmationCodePopUpCrossButton().click();
        JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getHomePage().getManageTravelConfirmationCodePopUpCrossButton());

//Step 6
        //validate reservation table route, date and confirmation code
        ValidationUtil.validateTestStep("Route Text is Displayed on MYTRIP Manage Travel"
                ,pageObjectManager.getHomePage().getManageTravelRouteText().getText().trim(),MYTRIP_ROUTE);
        ValidationUtil.validateTestStep("Date Text is Displayed on MYTRIP Manage Travel"
                ,pageObjectManager.getHomePage().getManageTravelDateText().getText().trim(),MYTRIP_DATE);
        ValidationUtil.validateTestStep("Confirmation Code Text is Displayed on MYTRIP Manage Travel"
                ,pageObjectManager.getHomePage().getManageTravelConfirmationCodeText().getText().trim(),MYTRIP_CONFIRMATION_CODE);

//Step 7
        //Validate that there are 5 bookings displayed on the table
        ValidationUtil.validateTestStep("There are 5 bookings displayed on my trips table", "5"
                , Integer.toString(pageObjectManager.getHomePage().getManageTravelTableConfirmationCodeText().size()));

//Step 8
        //validate the origin and destination cities
        for (WebElement routeElement: pageObjectManager.getHomePage().getManageTravelTableRouteTextBox()) {
            ValidationUtil.validateTestStep(routeElement.getText().trim()+" City code comes after the Origin and Destination City "
                    ,routeElement.getText().trim().matches("(.)*\\s\\(([A-Z]){3}\\)\\s\\-\\s+(.)*"));
        }

//Step 9
        //validate that the dates are in chronological order from closest departing to longets departing
        for (WebElement dateElement: pageObjectManager.getHomePage().getManageTravelTableDateText()) {

            //convert into date type
            Date date= TestUtil.convertStringToDate(dateElement.getText().trim(),MYTRIP_DATE_PATTERN);
            ValidationUtil.validateTestStep(dateElement.getText().trim()+" Dates are displayed properly",!date.equals(null));
        }

//Step 10
        ValidationUtil.validateTestStep("There are 5 confirmation codes displayed on my trips" , "5"
                , Integer.toString(pageObjectManager.getHomePage().getManageTravelTableConfirmationCodeText().size()));

        for (int count = 0 ; count < pageObjectManager.getHomePage().getManageTravelTableConfirmationCodeText().size(); count++){

            //getting confirmation code co-ordinate
            int confirmationCodeXPoint = pageObjectManager.getHomePage().getManageTravelTableConfirmationCodeText().get(count).getLocation().x;

            //getting continue co-ordinate
            int countinueXPoint = pageObjectManager.getHomePage().getManageTravelTableContinueButton().get(count).getLocation().x;

            //getting difference and converst into string
            String difference = Integer.toString(countinueXPoint - confirmationCodeXPoint);

            ValidationUtil.validateTestStep(" PNR is displayed properly and continue align right to PNR",!difference.contains("-"));
        }

        for (WebElement countinueElement: pageObjectManager.getHomePage().getManageTravelTableContinueButton()) {
            ValidationUtil.validateTestStep("The continue is hyperlink on Manage Travel",
                    countinueElement.getAttribute(MYTRIP_CONTINUE_ATTRIBUTE),MYTRIP_CONTINUE_ATTRIBUTE_VALUE);
        }

//Step 11, 12 and 13
        //click on each continue button

        for (int count = 0 ; count < 5 ; count++) {
            List<WebElement> continueButtons = pageObjectManager.getHomePage().getManageTravelTableContinueButton();
            continueButtons.get(count) .click();
            WaitUtil.untilPageLoadComplete(getDriver());
            ValidationUtil.validateTestStep("User is correctly redirected to reservation Summary after clicking continue button " + (count+1) , getDriver().getCurrentUrl(),"reservation-summary");
            getDriver().navigate().back();
            WaitUtil.untilPageLoadComplete(getDriver());
            WaitUtil.untilTimeCompleted(1000);
        }

//Step 14
        //expand table by clickong on show all, should display 10 bookings
        pageObjectManager.getHomePage().getManageTravelShowAllButton().click();

//Step 15

        for (WebElement routeElement: pageObjectManager.getHomePage().getManageTravelTableRouteTextBox()) {

            ValidationUtil.validateTestStep(routeElement.getText().trim()+" City code comes after the Origin and Destination City "
                    ,routeElement.getText().trim().matches("(.)*\\s\\(([A-Z]){3}\\)\\s\\-\\s+(.)*"));
        }
        //validate that the dates are in chronological order from closest departing to longets departing
        for (WebElement dateElement: pageObjectManager.getHomePage().getManageTravelTableDateText()) {
            Date date= TestUtil.convertStringToDate(dateElement.getText().trim(),MYTRIP_DATE_PATTERN);
            ValidationUtil.validateTestStep(dateElement.getText().trim()+" Dates are displayed properly",!date.equals(null));
        }

        ValidationUtil.validateTestStep("There are 10 confirmation codes displayed on my trips" , "10"
                , Integer.toString(pageObjectManager.getHomePage().getManageTravelTableConfirmationCodeText().size()));

        for (int count = 0 ; count < pageObjectManager.getHomePage().getManageTravelTableConfirmationCodeText().size(); count++){

            //getting co-ordinate of confirmation code text
            int confirmationCodeXPoint = pageObjectManager.getHomePage().getManageTravelTableConfirmationCodeText().get(count).getLocation().x;

            //getting co-ordinate of continue button
            int countinueXPoint = pageObjectManager.getHomePage().getManageTravelTableContinueButton().get(count).getLocation().x;

            //getting difference and convert into String
            String difference = Integer.toString(countinueXPoint - confirmationCodeXPoint);
            ValidationUtil.validateTestStep(" PNR is displayed properly and continue align right to PNR",!difference.contains("-"));
        }

        for (WebElement countinueElement: pageObjectManager.getHomePage().getManageTravelTableContinueButton()) {
            ValidationUtil.validateTestStep("The continue is hyperlink on Manage Travel",
                    countinueElement.getAttribute(MYTRIP_CONTINUE_ATTRIBUTE),MYTRIP_CONTINUE_ATTRIBUTE_VALUE);
        }

        //click on each continue button

        for (int count = 0 ; count < 5 ; count++) {
            List<WebElement> continueButtons = pageObjectManager.getHomePage().getManageTravelTableContinueButton();
            continueButtons.get(count) .click();
            WaitUtil.untilPageLoadComplete(getDriver());
            ValidationUtil.validateTestStep("User is correctly redirected to reservation Summary after clicking continue button " + (count+1) , getDriver().getCurrentUrl().contains("reservation-summary"));
            getDriver().navigate().back();
            WaitUtil.untilPageLoadComplete(getDriver());
            WaitUtil.untilTimeCompleted(1000);
        }

//Step 16
        //click on other reservation code
        pageObjectManager.getHomePage().getManageTravelAnotherConfirmationCodeButton().click();

//Step 17
        //input last name and PNR
        pageObjectManager.getHomePage().getMyTripsLastNameTextBox().sendKeys(LAST_NAME);
        pageObjectManager.getHomePage().getMyTripsPNRTextBox().sendKeys(pageObjectManager.getHomePage().getManageTravelTableConfirmationCodeText().get(SECOND_INDEX).getText());
        pageObjectManager.getHomePage().getMyTripContinueButton().click();
//Step 18
        //user taken to Reservation Summary
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("User is correctly redirected to reservation Summary after inputting Last name and PNR" ,
                getDriver().getCurrentUrl(),RESEVATION_SUMMARY_URL);
    }
}
