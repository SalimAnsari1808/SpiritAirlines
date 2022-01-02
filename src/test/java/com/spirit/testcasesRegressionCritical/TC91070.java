package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.PassengerInfoData;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC91070
//Test Name: Customer Info_CP_BP _9DFC logging out using why cant you edit your name link
//Created By : Kartik Chauhan
//Created On : 23-July-2019
//Reviewed By: Salim Ansari
//Reviewed On: 29-July-2019
//**********************************************************************************************
public class TC91070 extends BaseClass{
    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "WithIn21Days" , "Adult" , "Child" , "NineDFC" , "NonStop" , "BookIt" , "PassengerInformationUI"})
    public void Customer_Info_CP_BP_9DFC_logging_out_using_why_cant_you_edit_your_name_link(@Optional("NA") String platform) {
        /******************************************************************************
         ************************Navigate to Passenger Information Page****************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91070 under REGRESSION_CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String LOGIN_ACCOUNT      = "NineDFCEmail";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LGA";
        final String DEP_DATE           = "10";
        final String ARR_DATE           = "NA";
        final String ADULTS             = "1";
        final String CHILDS             = "1";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String SORT_BY            = "Departure";
        final String DEP_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADEVALUE       = "BookIt";

        //open browser
        openBrowser(platform);

        //launch application
        pageMethodManager.getHomePageMethods().launchSpiritApp();

//STEP 1:
        //Home Page Methods
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(3000);

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectSortingOption("Dep", SORT_BY);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        WaitUtil.untilTimeCompleted(4000);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADEVALUE);

        //Passenger Info Methods for new 9DFC member
//Step 3:
        // Wait till page load completely
        WaitUtil.untilPageLoadComplete(getDriver());

        PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("NineFCMember");

        //verify Birth Date is auto Populate
        ValidationUtil.validateTestStep("Validate Birth date is auto populate",
              pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(0).getAttribute("value"),passengerInfoData.dob);

        //Click on Why Cant you Edit your name Link
        pageObjectManager.getPassengerInfoPage().getWhyCantEditYourNameLink().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(5000);
//STEP--5
        //verify First Name is displaying in pop-up header
        ValidationUtil.validateTestStep("First Name is displaying in pop-up header",
                pageObjectManager.getPassengerInfoPage().getEditYourNamePopUpHeaderText().getText(),passengerInfoData.firstName.toUpperCase());

        //verify Last Name is displaying in pop-up header
        ValidationUtil.validateTestStep("Last Name is displaying in pop-up header",
                pageObjectManager.getPassengerInfoPage().getEditYourNamePopUpHeaderText().getText(),passengerInfoData.lastName);
//STEP--6
        //close pop-up via clicking on cross icon on pop-up
        pageObjectManager.getPassengerInfoPage().getEditYourNamePopUpCrossIcon().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);
//STEP--7
        //Click on Why Cant you Edit your name Link
        pageObjectManager.getPassengerInfoPage().getWhyCantEditYourNameLink().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);
//STEP--8
        //Click on Why Cant you Edit your name Link
        pageObjectManager.getPassengerInfoPage().getEditYourNamePopUpCloseButton().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);
//STEP--9
        //Click on Why Cant you Edit your name Link
        pageObjectManager.getPassengerInfoPage().getWhyCantEditYourNameLink().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);
//STEP-10
        //Log -out via pop-up
        pageObjectManager.getPassengerInfoPage().getEditYourNamePopUpLogOutButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Verify user logged out from application", TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getSignInLink()));
    }
}