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

import java.util.Date;
import java.util.List;

//**********************************************************************************************
//Test CaseID: TC91231
//Title      : My Trips_CP_MT_ $9FC_My Trips 5 Upcomming
//Created By : Kartik chauhan
//Created On : 02-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 02-Aug-2019
//**********************************************************************************************

public class TC91231 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"NineDFC" , "MyTrips" , "HomeUI"})
    public void My_Trips_CP_MT_9FC_My_Trips_5_Upcomming (@Optional("NA") String platform) {
        /******************************************************************************
         ***************************Navigate to Home Page******************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91231 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant variables
        final String LANGUAGE                       = "English";
        final String LOGIN_TYPE                     = "NineDFCEmail";

        //my trip verbiage
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

        /******************************************************************************
         *******************************FA Page****************************************
         ******************************************************************************/
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_TYPE);
        pageMethodManager.getHomePageMethods().storeAirportsDetails();

//STEP--2
        //click on My Trip
        pageObjectManager.getHomePage().getMyTripPathLink().click();
//STEP--3
        //validate Change or cancel
        ValidationUtil.validateTestStep("Change or Cancel.. verbiage is displaying under My Trip section",
                pageObjectManager.getHomePage().getManageTravelVerbageText().isDisplayed());
//STEP--4
        //click on confirmation code link
        pageObjectManager.getHomePage().getMyTripConfirmationCodeLink().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //verify correct pop-up is displaying
        ValidationUtil.validateTestStep("Your Confirmation code pop-up is displaying",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getYouConfirmationPopupHeaderText()));
//STEP--5
        //close popup
        pageObjectManager.getHomePage().getPopUpCrossButton().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);
//STEP--6
        //verify Route.. header verbiage is displaying
        ValidationUtil.validateTestStep("Route.. header verbiage is displaying",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getManageTravelRouteText()));

        //verify Date.. header verbiage is displaying
        ValidationUtil.validateTestStep("Date.. header verbiage is displaying",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getManageTravelDateText()));

        //verify Confirmation code.. header verbiage is displaying
        ValidationUtil.validateTestStep("Confirmation code.. header verbiage is displaying",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getManageTravelConfirmationCodeText()));

        //STEP--7
        //verify At least 5 latest booking should display
        ValidationUtil.validateTestStep("At least 5 latest booking should display",
                pageObjectManager.getHomePage().getManageTravelTableRouteTextBox().size() == 5);
//STEP--8
        //create loop for all journey
        for(int count = 0; count< pageObjectManager.getHomePage().getManageTravelTableRouteTextBox().size(); count++) {

            //verify all FROM city pair name
            ValidationUtil.validateTestStep("Valid City Name is displaying in 'From' destination",
                    scenarioContext.getContext(Context.HOMEPAGE_CITYPAIRS).toString().trim(),
                    ((pageObjectManager.getHomePage().getManageTravelTableRouteTextBox().get(count).getText().split("-"))[1].trim().split("\\("))[0]);
        }
        //create loop for all journey
        for(int count1 = 0; count1< pageObjectManager.getHomePage().getManageTravelTableRouteTextBox().size(); count1++) {

            //verify all TO city pair name
            ValidationUtil.validateTestStep("Valid City Name is displaying in 'To' destination",
                    scenarioContext.getContext(Context.HOMEPAGE_CITYPAIRS).toString().trim(),
                    ((pageObjectManager.getHomePage().getManageTravelTableRouteTextBox().get(count1).getText().split("-"))[1].trim().split("\\("))[0]);
        }

//STEP--9
        //validate that the dates are in chronological order from closest departing to longest departing
        for (WebElement dateElement: pageObjectManager.getHomePage().getManageTravelTableDateText()) {

            //convert into date type
            Date date= TestUtil.convertStringToDate(dateElement.getText().trim(),MYTRIP_DATE_PATTERN);
            ValidationUtil.validateTestStep(dateElement.getText().trim()+" Dates are displayed properly",
                    !date.equals(null));
        }

        //validate 5 PNR should display
        ValidationUtil.validateTestStep("There are 5 confirmation codes displayed on my trips" ,
                pageObjectManager.getHomePage().getManageTravelTableConfirmationCodeText().size() == 5);

        for (int count = 0 ; count < pageObjectManager.getHomePage().getManageTravelTableConfirmationCodeText().size(); count++){

            //getting confirmation code co-ordinate
            int confirmationCodeXPoint = pageObjectManager.getHomePage().getManageTravelTableConfirmationCodeText().get(count).getLocation().x;

            //getting continue co-ordinate
            int continueXPoint = pageObjectManager.getHomePage().getManageTravelTableContinueButton().get(count).getLocation().x;

            //getting difference and convert into string
            String difference = Integer.toString(continueXPoint - confirmationCodeXPoint);

            ValidationUtil.validateTestStep(" PNR is displayed properly and continue align right to PNR",!difference.contains("-"));
        }
        for (WebElement continueElement: pageObjectManager.getHomePage().getManageTravelTableContinueButton()) {
            ValidationUtil.validateTestStep("The continue is hyperlink on Manage Travel",
                    continueElement.getAttribute(MYTRIP_CONTINUE_ATTRIBUTE),MYTRIP_CONTINUE_ATTRIBUTE_VALUE);
        }

//Step 11, 12 and 13
        //click on each continue button
        for (int count = 0 ; count < 5 ; count++) {

            //wait till page load complete
            WaitUtil.untilPageLoadComplete(getDriver());

            //create list
            List<WebElement> continueButtons = pageObjectManager.getHomePage().getManageTravelTableContinueButton();

            //click on every continue button
            continueButtons.get(count) .click();

            //wait till page load complete
            WaitUtil.untilPageLoadComplete(getDriver());

            //validate user is redirected correctly
            ValidationUtil.validateTestStep("User is correctly redirected to reservation Summary after clicking continue button " + (count+1) , getDriver().getCurrentUrl(),
                    "reservation-summary");

            //back to page
            getDriver().navigate().back();

            //wait till page load complete
            WaitUtil.untilPageLoadComplete(getDriver());

            //wait for 1 sec
            WaitUtil.untilTimeCompleted(1000);
        }
//Step 14
        //expand table by click on on show all, should display 10 bookings
        pageObjectManager.getHomePage().getManageTravelShowAllButton().click();

        //create loop for all journey
        for(int count = 0; count< pageObjectManager.getHomePage().getManageTravelTableRouteTextBox().size(); count++) {

            //verify all FROM city pair name
            ValidationUtil.validateTestStep("Valid City Name is displaying in 'From' destination",
                    scenarioContext.getContext(Context.HOMEPAGE_CITYPAIRS).toString().trim(),
                    ((pageObjectManager.getHomePage().getManageTravelTableRouteTextBox().get(count).getText().split("-"))[1].trim().split("\\("))[0]);


        }
        for(int count1 = 0; count1< pageObjectManager.getHomePage().getManageTravelTableRouteTextBox().size(); count1++) {

            //verify all TO city pair name
            ValidationUtil.validateTestStep("Valid City Name is displaying in 'To' destination",
                    scenarioContext.getContext(Context.HOMEPAGE_CITYPAIRS).toString().trim(),
                    ((pageObjectManager.getHomePage().getManageTravelTableRouteTextBox().get(count1).getText().split("-"))[1].trim().split("\\("))[0]);
        }


        //validate that the dates are in chronological order from closest departing to longest departing
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
            int continueXPoint = pageObjectManager.getHomePage().getManageTravelTableContinueButton().get(count).getLocation().x;

            //getting difference and convert into String
            String difference = Integer.toString(continueXPoint - confirmationCodeXPoint);
            ValidationUtil.validateTestStep(" PNR is displayed properly and continue align right to PNR",!difference.contains("-"));
        }

        for (WebElement continueElement: pageObjectManager.getHomePage().getManageTravelTableContinueButton()) {
            ValidationUtil.validateTestStep("The continue is hyperlink, on Manage Travel",
                    continueElement.getAttribute(MYTRIP_CONTINUE_ATTRIBUTE),MYTRIP_CONTINUE_ATTRIBUTE_VALUE);
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
        ValidationUtil.validateTestStep("User is correctly redirected to reservation Summary after giving Last name and PNR" ,
                getDriver().getCurrentUrl(),RESEVATION_SUMMARY_URL);
    }
}