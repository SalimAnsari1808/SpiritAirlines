package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC91147
//Description: Vacation Path_CP_Flight-Hotel-Car_NEG_Booking 16 yrs passengers_getting insufficient age popup
//Created By : Anthony Cardona
//Created On : 06-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 08-Aug-2019
//**********************************************************************************************
public class TC91147 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups={"BookPath", "Child","Guest", "DomesticDomestic", "RoundTrip", "FlightHotelCar",  "Outside21Days","FlightAvailabilityUI","HotelsUI", "CarsUI","BookIt","MandatoryFields","PassengerInformationUI"})

    public void Vacation_Path_CP_Flight_Hotel_Car_NEG_Booking_16_yrs_passengers_getting_insufficient_age_popup(@Optional("NA") String platform) {

        /********************Navigate to Passenger Info Page**************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91147  under REGRESSION CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE               = "English";
        final String JOURNEY_TYPE           = "Vacation";
        final String TRIP_TYPE              = "Flight+Hotel+car";
        final String DEP_AIRPORTS           = "AllLocation";
        final String DEP_AIRPORT_CODE       = "FLL";
        final String ARR_AIRPORTS           = "AllLocation";
        final String ARR_AIRPORT_CODE       = "LAS";
        final String DEP_DATE               = "60";
        final String ARR_DATE               = "65";
        final String ADULTS                 = "2";
        final String CHILDS                 = "0";
        final String INFANT_LAP             = "0";
        final String INFANT_SEAT            = "0";
        final String HOTELROOM              = "1 Room";
        final String DRIVER_AGE             = "25+";

        final String UPGRADE_VALUE          = "BookIt";

        final String DOB16YearOld           = TestUtil.getStringDateFormat((-5840), "MM/dd/yyyy");

        //open browser
        openBrowser(platform);
//Step 1,2 Access the test environment select vacation booking 3 adults, 1 room
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectHotelRoom(HOTELROOM);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        WaitUtil.untilPageLoadComplete(getDriver());

//Step 3: Validate that the pre-selected flight is selected (2 flights, departure and return)
        ValidationUtil.validateTestStep("A default flight is pre-selected for the departure and return",
                pageObjectManager.getFlightAvailabilityPage().getSelectedFlightBlocksPanel().size() == 2);

//Step 4: Click EDIT change Departing and returning Flight(This step is not mandatory for this test case)

//Step 9: Validate that there is no continue button if a hotel is not selected
        ValidationUtil.validateTestStep("The user should not be able to continue",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getVacationPathContinueButton()));

//Step 10: Select Hotel
        //Complete the flight + Hotel page
        JSExecuteUtil.scrollDownToElementVisible(getDriver(),pageObjectManager.getHotelPage().getHotelViewButton().get(0));
        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getHotelPage().getHotelViewButton().get(0).click();

        ValidationUtil.validateTestStep("User click on View Hotel button on Hotel Page", true);
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);

//Step 11: Select Room
        pageObjectManager.getHotelPage().getHotelPopUpSelectRoomsFromButton().click();
        pageObjectManager.getHotelPage().getHotelPopUpSelectRoomButton().get(0).click();

        ValidationUtil.validateTestStep("User click on Select Room button on Hotel Page", true);

        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(3000);

//Step 12: Click continue at the bottom of the page
//        JSExecuteUtil.scrollDownToElementVisible(getDriver(),pageObjectManager.getHotelPage().getContinueButton());
//        WaitUtil.untilTimeCompleted(1200);
//        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getHotelPage().getContinueButton());
//        ValidationUtil.validateTestStep("User click on Continue Hotel button on Hotel Page", true);

//        contButton.click();//click continue at the bottom of the page
        WaitUtil.untilPageLoadComplete(getDriver());

        //select upgrade value
        ValidationUtil.validateTestStep("The user taken to the Cars page",
                getDriver().getCurrentUrl(),"book/options/cars");

        //book car
//        pageObjectManager.getCarPage().getBookCarButton().get(0).click();
        pageObjectManager.getCarPage().getCarsPageBookButton().get(0).click();
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);

        ValidationUtil.validateTestStep("User click on Book Car button on Car Page", true);

        //select upgrade value
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);
        WaitUtil.untilTimeCompleted(1200);
        WaitUtil.untilPageLoadComplete(getDriver());

//Step 13: Fill contact information with two passenger that is 16 years old
        pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(0).sendKeys(DOB16YearOld);
        pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(1).sendKeys(DOB16YearOld);

        ValidationUtil.validateTestStep("User enter 16 years of passenger Age on Passenger Information Page", true);

        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Insufficient Miles Popup is displayed
        ValidationUtil.validateTestStep("User verify Header section of Insufficient Age PopUp on Passenger Information Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getInsufficientAgePopUpHeaderText()));

        ValidationUtil.validateTestStep("User verify Flight Only button section of Insufficient Age PopUp on Passenger Information Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getInsufficientAgePopUpFlightOnlyButton()));

        ValidationUtil.validateTestStep("User verify body section of Insufficient Age PopUp on Passenger Information Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getInsufficientAgePopUpVerbageText()));

//Step 14: Close the pop-up, click continue again, and validate the user gets the pop-up again
        pageObjectManager.getPassengerInfoPage().getInsufficientAgePopUpCloseButton().click();

        ValidationUtil.validateTestStep("User click close button of Insufficient Age PopUp on Passenger Information Page", true);

//Step 15: Click on flight only and user is redirected to the flight only search
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
        WaitUtil.untilTimeCompleted(2000);
        pageObjectManager.getPassengerInfoPage().getInsufficientAgePopUpFlightOnlyButton().click();
        WaitUtil.untilTimeCompleted(1200);
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("The user is taken back to the home page User lands on the search widget",
                pageObjectManager.getHomePage().getRoundTripLabel().isDisplayed());
    }
}