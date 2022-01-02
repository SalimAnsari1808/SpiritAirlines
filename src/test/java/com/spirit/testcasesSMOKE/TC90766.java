package com.spirit.testcasesSMOKE;

import com.spirit.dataType.*;
import com.spirit.managers.*;
import com.spirit.utility.*;
import org.testng.annotations.*;
import com.spirit.baseClass.BaseClass;

//**********************************************************************************************
//Test Case ID: TC90766
//Description: Customer Info_CP_BP_Have two PAX with the same name but with SR and JR suffix
//Created By : Alex Rodriguez
//Created On : 8-May-2019
//Reviewed By: Salim Ansari
//Reviewed On: 9-May-2019
//**********************************************************************************************
public class TC90766 extends BaseClass {

    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "Within21Days" , "Adult" , "InfantLap" , "Guest" ,"NonStop" ,
            "BundleIt" , "MandatoryFields","PassengerInformationUI"})
    public void Customer_Info_CP_BP_Have_two_PAX_with_the_same_name_but_with_SR_and_JR_suffix(@Optional("NA") String platform) {
        /******************************************************************************
         ***********************Navigate to Customer Info Page*************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90766 under Smoke Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE         = "English";
        final String JOURNEY_TYPE     = "Flight";
        final String TRIP_TYPE        = "OneWay";
        final String DEP_AIRPORTS     = "AllLocation";
        final String DEP_AIRPORT_CODE = "FLL";
        final String ARR_AIRPORTS     = "AllLocation";
        final String ARR_AIRPORT_CODE = "ATL";
        final String DEP_DATE         = "13";
        final String RET_DATE         = "NA";
        final String ADULTS           = "1";
        final String CHILDREN         = "0";
        final String INFANT_LAP       = "1";
        final String INFANT_SEAT      = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT       = "NonStop";
        final String FARE_TYPE        = "Standard";
        final String JOURNEY_UPGRADE  = "BundleIt";

        //Bags page constant Value
        final String BAGS_PAGE_URL    = "book/bags";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, RET_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        //Flight Availability Page Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(JOURNEY_UPGRADE);

        //Filling infant information with the same adult information
        PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("Passenger1");
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPassengerInfoPage().getInfantTitleListDropDown().get(0),passengerInfoData.title);
        pageObjectManager.getPassengerInfoPage().getInfantFirstNameListTextBox().get(0).sendKeys(passengerInfoData.firstName);
        pageObjectManager.getPassengerInfoPage().getInfantLastNameListTextBox().get(0).sendKeys(passengerInfoData.lastName);

        //Filling mandatory passenger fields
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        ValidationUtil.validateTestStep("Edit duplicate name popup is Displayed to Passenger Page",pageObjectManager.getPassengerInfoPage().getEditDuplicateNamesButton().isDisplayed());
        //Duplicates Passenger Names Found pop up
        pageObjectManager.getPassengerInfoPage().getEditDuplicateNamesButton().click();

        //Fill infant Suffix to eliminate duplicates passengers
        TestUtil.selectDropDownUsingValue(pageObjectManager.getPassengerInfoPage().getInfantSuffixListDropDown().get(0),"JR");
        TestUtil.selectDropDownUsingValue(pageObjectManager.getPassengerInfoPage().getAdultSuffixListDropDown().get(0),"SR");

        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("User Reach from Passenger Page to Bags Page",getDriver().getCurrentUrl(),BAGS_PAGE_URL);
    }
}