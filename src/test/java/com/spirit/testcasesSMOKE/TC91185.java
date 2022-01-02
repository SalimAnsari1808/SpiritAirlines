package com.spirit.testcasesSMOKE;


import com.spirit.baseClass.*;
import com.spirit.dataType.*;
import com.spirit.managers.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.*;

//**********************************************************************************************
//TODO: Bug 26051: CP: BP: Flight Availability FA: User Receives red i block when trying to create a miles booking when logging in either on the homepage, or the FA page
/**10/21/19 test case passed, removed active bug tag**/
//Test CaseID: TC91185
//Title      : Flight Availability_CP_BP_Successful Login to use MILES_View in USD
//Created By : Alex Rodriguez
//Created On : 11-Apr-2019
//Reviewed By: Salim Ansari
//Reviewed On: 17-Apr-2019
//**********************************************************************************************
public class TC91185 extends BaseClass{

    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "Oneway" , "Miles" , "DomesticDomestic" , "Within21Days" , "Adult" , "FSMasterCard" ,
                    "NonStop" , "FlightAvailabilityUI"})
    public void Flight_Availability_CP_BP_Successful_Login_to_use_MILES_View_in_USD(@Optional("NA") String platform) {
        //mention the browser
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91185 under SMOKE suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";

        //Flight Availability Page Constant Values
        final String LOGIN_ACCOUNT      = "ZeroMiles";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LGA";
        final String DEP_DATE           = "10";
        final String ARR_DATE           = "NA";
        final String ADULTS             = "1";
        final String CHILDREN           = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		= "NonStop";
        final String FARE_TYPE			= "Standard";

        //open browser
        openBrowser(platform);

//Step_ 1,2,3, 4
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        //User switches from dollars to miles
        pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselMilesViewSwitchLabel().click();

        //User signs into the Log-In modal with No miles User
        LoginCredentialsData loginCredentialsData = FileReaderManager.getInstance().getJsonReader().getCredentialsByUserType(LOGIN_ACCOUNT);

        WaitUtil.untilPageLoadComplete(getDriver());

        //enter username
        pageObjectManager.getHomePage().getUserNameBox().sendKeys(loginCredentialsData.userName);

        //enter password
        pageObjectManager.getHomePage().getPasswordBox().sendKeys(loginCredentialsData.password);

        //click on login button
        pageObjectManager.getHomePage().getLoginButton().click();

        //wait till page is stable
        WaitUtil.untilPageLoadComplete(getDriver());

        //Replacing this method for the one we are using from the framework
//        List<WebElement> milesJourneyResults = getDriver().findElements(By.xpath("//label[contains(@for,'availability-journey-radio')]"));
//
//        for(WebElement milesButton : milesJourneyResults){
//            System.out.println(milesButton.getAttribute("for"));
//            if(milesButton.isDisplayed()){
//                if(milesButton.getAttribute("for").contains("standard")){
//                    milesButton.click();
//                }
//            }
//        }
//        WaitUtil.untilPageLoadComplete(getDriver());
//        WebElement continueButton = getDriver().findElement(By.xpath("//button[contains(text(), \"Continue\")]"));
//        continueButton.click();

        pageMethodManager.getFlightAvailabilityMethods().selectMilesFlightNatureType("Dep" , DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);

        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getInsufficientMilesPopUpViewInDollarsButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());

        //validate user on dollar view FA page
        ValidationUtil.validateTestStep("User navigated to Dollar Price view on Flight Availability Page",
                pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselDollarsViewSwitchLabel().getAttribute("class"),"active" );
    }
}


