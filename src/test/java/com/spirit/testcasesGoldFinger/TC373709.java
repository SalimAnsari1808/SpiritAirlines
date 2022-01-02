package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.PassengerInfoData;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID : TC373709
//Description  : CP - Hub Packaging - View All Cars - Validate that the View All Cars link is present for 5 Passenger Booking
//Created By   : Kartik Chauhan
//Created On   : 25 Nov, 2019
//Reviewed By  : Gabriela
//Reviewed On  : 6-Dec-2019
//**********************************************************************************************

public class TC373709 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "WithIn21Days" , "Adult" , "Guest" , "NonStop" , "BookIt" , "CarryOn"
                    , "CheckedBags" ,"Standard", "OptionsUI","CarsUI"})
    public void CP_Hub_Packaging_View_All_Cars_Validate_that_the_View_All_Cars_link_is_present_for_5_Passenger_Booking(@Optional("NA") String platform) {
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373709 under GoldFinger Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant variables
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "10";
        final String ARR_DATE           = "15";
        final String ADULT              = "5";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String RET_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Bags Page constant values
        final String DEP_BAGS 			= "Carry_1|Checked_3||Carry_1|Checked_3||Carry_1|Checked_3||Carry_1|Checked_3||Carry_1|Checked_3";
        final String RET_BAGS			= "Carry_1|Checked_3||Carry_1|Checked_3||Carry_1|Checked_3||Carry_1|Checked_3||Carry_1|Checked_3";

        //Seats Page Constant Values
        final String SEATS_VALUE          = "Standard|Standard|Standard|Standard|Standard";

        //open browser
        openBrowser(platform);
//STEP--1/2/3:
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
//STEP--4:
        //Flight Availability Page Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
//STEP--5:
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);
//STEP--6:
        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
//STEP--7:
        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectReturnBags(RET_BAGS);
        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);
//STEP--8:
        //Seat Methods
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(SEATS_VALUE);
        pageMethodManager.getSeatsPageMethods().selectReturningSeats(SEATS_VALUE);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //Options page methods
        WaitUtil.untilPageLoadComplete(getDriver());

//Step--9: Verify Cars section is shown on Options page
            ValidationUtil.validateTestStep("Options page Car header is displayed on the options page",
                    pageObjectManager.getCarPage().getCarCarouselTitleText().getText(), "Savings on Cars");

//STEP--10/11:
            ValidationUtil.validateTestStep("Validating 'Viea All car link' section is displaying",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getViewAllCarsButton()));
            pageObjectManager.getCarPage().getViewAllCarsButton().click();

            WaitUtil.untilPageLoadComplete(getDriver());

//STEP--12
            ValidationUtil.validateTestStep("Validating 'Price' section is displaying on left side",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getPriceFilterLabel()));

            ValidationUtil.validateTestStep("Validating 'Seat' section is displaying on left side",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getSeatsFilterLabel()));

            ValidationUtil.validateTestStep("Validating 'Bag' section is displaying on left side",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getBagsFilterLabel()));

            ValidationUtil.validateTestStep("Validating 'Rental Agency' section is displaying on left side",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getRentalAgencyFilterLabel()));

            ValidationUtil.validateTestStep("Validating 'Car Type' section is displaying on left side",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarTypeFilterLabel()));

            ValidationUtil.validateTestStep("Validating 'Car Options' section is displaying on left side",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarOptionsFilterLabel()));

//STEP--13:
            ValidationUtil.validateTestStep("Validating 'Recommended button' section is displaying on Top",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getSortByRecommendedButton()));

            ValidationUtil.validateTestStep("Validating 'Price' section is displaying on Top",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getSortByPriceButton()));

            ValidationUtil.validateTestStep("Validating 'Seat' section is displaying on Top",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getSortBySeatsButton()));

            ValidationUtil.validateTestStep("Validating 'Car Type' section is displaying on Top",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getSortByCarTypeButton()));
//STEP--14/15/16:
            for (int i = 0; i < pageObjectManager.getCarPage().getCarCardNameText().size(); i ++) {
                ValidationUtil.validateTestStep("Validating 'Car Image' section is displaying on Top",
                        TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageCarRentalImg().get(i)));

                ValidationUtil.validateTestStep("Validating 'Company' section is displaying on Top",
                        TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageCarCompanyLogoImg().get(i)));

                ValidationUtil.validateTestStep("Validating 'Type and Name of Car' section is displaying on Top",
                        TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageCarModelText().get(i)));

                ValidationUtil.validateTestStep("Validating 'More Info' section is displaying on Top",
                        TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageMoreInfoLink().get(i)));

                ValidationUtil.validateTestStep("Validating 'Pricing in USD' section is displaying on Top",
                        TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageRentalPriceText().get(i)));
//STEP--17:
            pageObjectManager.getCarPage().getCarsPageMoreInfoLink().get(i).click();
                WaitUtil.untilPageLoadComplete(getDriver());
                ValidationUtil.validateTestStep("Validating Car descriptions is displayed",
                        TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarPageMoreInfoVehicleDescriptionLink()));

                ValidationUtil.validateTestStep("Validating Car Policies is displayed",
                        TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarPageMoreInfoPoliciesLink()));

                ValidationUtil.validateTestStep("Validating Car Location is displayed",
                        TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarPageMoreInfoLocationsLink()));

                ValidationUtil.validateTestStep("Validating 'More Info' link is replaced per 'Less' link",
                        TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageLessInfoLink()));
//STEP--18:
            pageObjectManager.getCarPage().getCarsPageLessInfoLink().click();
            WaitUtil.untilPageLoadComplete(getDriver());
            ValidationUtil.validateTestStep("Validating information collapsed",
                    !TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarPageMoreInfoVehicleDescriptionLink()));
            }
//STEP--19:
            WaitUtil.untilTimeCompleted(2000);
            pageObjectManager.getCarPage().getAddCarButton().get(0).click();

            WaitUtil.untilTimeCompleted(2000);
//STEP--20:
            //get adult mandatory details
            PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("Passenger1");

            //select Driver Dropdown
            pageObjectManager.getPassengerInfoPage().getPrimaryDriverDropDown().click();

            //Select Driver name
            pageObjectManager.getPassengerInfoPage().getPrimaryDriverDropDown().sendKeys(passengerInfoData.firstName);

            //wait for 2 second
            WaitUtil.untilTimeCompleted(2000);
//STEP--21:
            pageObjectManager.getCarPage().getWhoSDrivingVerifyAndBookThisCarButton().click();
//            pageObjectManager.getCarPage().getAddCarButton().get(0).click();

            //wait for 2 second
            WaitUtil.untilPageLoadComplete(getDriver());
            WaitUtil.untilTimeCompleted(2000);

//STEP--22:
            ValidationUtil.validateTestStep("Options page Car header is displayed on the options page",
                    !TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarCarouselTitleText()));

            ValidationUtil.validateTestStep("Validating 'Remove' Link is displaying on left side",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageCarRemoveButton()));
//STEP--23:
            ValidationUtil.validateTestStep("Validating 'View All' link is suppressed",
                    !TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getViewAllCarsButton()));

    }
}

//    public final String xpath_AddCarButton = "//button[contains(text(),'Add Car') or contains(text(),'Añadir auto')]";
//    @FindBy(xpath = xpath_AddCarButton)
//    private List<WebElement> btn_AddCar;
//    public List<WebElement> getAddCarButton(){return btn_AddCar;}
