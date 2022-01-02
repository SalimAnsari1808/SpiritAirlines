package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.LoginCredentialsData;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test CaseID: TC91124
//Title: 22969: 31285 Flight Availability_CP_BP_Forgot Password to Login to use MILES
//Description: Validating Retrieve Password after switch to miles booking
//Created By : Gabriela
//Created On : 10-May-2019
//Reviewed By: Salim Ansari
//Reviewed On: 11-May-2019
//**********************************************************************************************

public class TC91124 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "Outside7Days" , "Guest" ,"Adult","FlightAvailabilityUI"})
    public void Flight_Availability_CP_BP_Forgot_Password_to_Login_to_use_MILES(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91124 under SMOKE suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE 			    = "English";
        final String JOURNEY_TYPE 		    = "Flight";
        final String TRIP_TYPE 		        = "Oneway";
        final String DEP_AIRPORTS 		    = "AllLocation";
        final String DEP_AIRPORT_CODE 	    = "FLL";
        final String ARR_AIRPORTS 		    = "AllLocation";
        final String ARR_AIRPORT_CODE 	    = "ATL";
        final String DEP_DATE 			    = "8";
        final String ARR_DATE 			    = "NA";
        final String ADULTS 			    = "1";
        final String CHILD  			    = "0";
        final String INFANT_LAP 		    = "0";
        final String INFANT_SEAT		    = "0";

        //Flight Availability Constant Values
        final String FA_URL                 = "book/flights";
        final String URL2                   = "retrieve-password";
        final String ERROR1                 = "Email address is invalid";
        final String ERROR2                 = "Invalid email address or incorrect password. Please correct and re-try or select Sign Up.";


//--Step 1: Access test environment and start booking and reach flight availability page
        //open browser
        openBrowser(platform);

        //Starting booking
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);

        //Continue to flight availability
        pageMethodManager.getHomePageMethods().clickSearchButton();

//--Step 2: Verify user lands on Flight Availability page url:
        ValidationUtil.validateTestStep("Validating the user land on Flight Availability Page",
                getDriver().getCurrentUrl(),FA_URL);

//--Step 3: Locate the slider button WEEK/MONTH and DOLLARS/MILES
        ValidationUtil.validateTestStep("Validating Dollar button present on Flight Availability Page",
                pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselDollarsViewSwitchLabel().isDisplayed());

        ValidationUtil.validateTestStep("Validating Miles button present on Flight Availability Page",
                pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselMilesViewSwitchLabel().isDisplayed());

        ValidationUtil.validateTestStep("Validating Months button present on Flight Availability Page",
                pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselMonthViewSwitchLabel().isDisplayed());

        ValidationUtil.validateTestStep("Validating Week button present on Flight Availability Page",
                pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselWeekViewSwitchLabel().isDisplayed());

//--Step 4: Click on Miles
        pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselMilesViewSwitchLabel().click();

        //User signs into the Log-In modal with No miles User
        LoginCredentialsData loginCredentialsData = FileReaderManager.getInstance().getJsonReader().getCredentialsByUserType("ZeroMiles");

//--Step 5: Attempt to login using an invalid email address
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHomePage().getUserNameBox().sendKeys(loginCredentialsData.userName.replace("com", "" ));

        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getHomePage().getLoginButton().click();

        ValidationUtil.validateTestStep("Validating the right error message is displayed for invalid email on Flight Availability Page",
                pageObjectManager.getCommon().getErrorMessageLabel().getText(),ERROR1);

//-- Step 6: Enter a valid email address and an invalid password
        TestUtil.clearTextBoxUsingSendKeys(getDriver(),pageObjectManager.getHomePage().getUserNameBox());
        WaitUtil.untilTimeCompleted(1200);

        pageObjectManager.getHomePage().getUserNameBox().sendKeys(loginCredentialsData.userName);
        pageObjectManager.getHomePage().getPasswordBox().sendKeys(loginCredentialsData.password + "123123");

        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getHomePage().getLoginButton().click();

        ValidationUtil.validateTestStep("Validating the right error message is displayed for invalid email",
                getDriver().findElement(By.xpath("//p[contains(text(),' Invalid email address')]")).getText(),ERROR2);

//-- Step 7: Click on "Reset Password
        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getHomePage().getResetPasswordButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating the user land in the right URL",
                getDriver().getCurrentUrl(),URL2);


    }
}