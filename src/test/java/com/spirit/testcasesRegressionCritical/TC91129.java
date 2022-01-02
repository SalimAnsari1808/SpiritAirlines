package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC91129
//Test Name: Task 22984: 31287 Vacation Path_CP_Flight-Hotel_NEG_Booking 16 yrs passengers_getting insufficient age popup
// Created By: Gabriela
//Created On : 09-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 13-Aug-2019
//**************************************************************************************************

public class TC91129 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups={"BookPath", "DomesticDomestic", "RoundTrip", "FlightHotel", "Outside21Days", "Child","Guest", "Outside21Days","FlightAvailabilityUI","CarsUI","HotelsUI","BookIt","PassengerInformationUI"})

    public void Vacation_Path_CP_Flight_Hotel_NEG_Booking_16_yrs_passengers_getting_insufficient_age_popup(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91129 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
//-- Step 1: (Pre-requisite: Create a booking 3 months out. Keep booking a cheap as possible. In other words only book for 1 night stay
        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Vacation";
        final String TRIP_TYPE 			= "Flight+Hotel";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LAS";
        final String DEP_DATE 			= "110";
        final String ARR_DATE 			= "111";
        final String ADULT				= "2";
        final String CHILD				= "0";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT		= "0";
        final String HOTEL_ROOM 		= "1 Room";
        final String HOME_PAGE_URL      = "spirit.com";

        //Flight + Hotel Availability Page
        final String UPGRADE_VALUE      = "BookIt";

        final String DOB16YearOld       = TestUtil.getStringDateFormat((-5840), "MM/dd/yyyy");

        //Passenger Information Constant Values
        final String PASS_INFO_URL      = "/book/passenger";

        //open browser
        openBrowser( platform);

        /******************************** Home Page *****************************************/
//-- Step 2: Access test environment URL: http://nav01.spirit.com/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//-- Step 3: On the Search Widget, click on Vacation tab and perform a Flight + Hotel search, add 2 passengers
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectHotelRoom(HOTEL_ROOM);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        /******************************** Flight Availability Page *****************************************/
//-- Step 4: If the booking date is within 48 hrs, no Hotels will be offered to minimize fraud. Verify the Hotel error message modal displays
//Invalid step

//-- Step 5: If the booking date is within 48 hrs and the modal displayed, click on CHANGE DATES button
//Invalid step

//-- Step 6: Review the displaying flights
        ValidationUtil.validateTestStep("A default flight is pre-selected for the departure and return",
                pageObjectManager.getFlightAvailabilityPage().getSelectedFlightBlocksPanel().size() == 2);

//-- Step 7: If the pre-selected flights are not what you want, click on Change Departing and/or Change Returning
// Invalid Step

//-- Step 8: Select flight(s)(This step is not mandatory for this test case)
//Note: capture selection for future reference
//        //Recording Departing Journey Information
//        pageObjectManager.getFlightAvailabilityPage().getSeletedDepatingFlightNatureButton().get(0).click();
//
//        //Declaring Lists to store flight info
//        WaitUtil.untilTimeCompleted(1000);
//        List<String> depCityName    = new ArrayList<>();
//        List<String> arrCityName    = new ArrayList<>();
//        List<String> depTime        = new ArrayList<>();
//        List<String> arrTime        = new ArrayList<>();
//        List<String> nkInfo         = new ArrayList<>();
//
//        //Storing Departure Cities Name for 1st journey
//        for (int i = 0; i < getDriver().findElements(By.xpath("//div[@data-qa='depart-city-station']//p[2]")).size(); i++) {
//            depCityName.add(getDriver().findElements(By.xpath("//div[@data-qa='depart-city-station']//p[2]")).get(i).getText());
//            System.out.println("depCityName " + depCityName);
//        }
//
//        //Storing Arrival Cities Name for 1st journey
//        for (int i = 0; i < getDriver().findElements(By.xpath("//div[@data-qa='arrive-city-station']//p[2]")).size(); i++) {
//            arrCityName.add(getDriver().findElements(By.xpath("//div[@data-qa='arrive-city-station']//p[2]")).get(i).getText());
//            System.out.println("arrCityName " + arrCityName);
//        }
//
//        //Storing Departure Cities Time for 1st journey
//        for (int i = 0; i < getDriver().findElements(By.xpath("//div[@data-qa='depart-time']")).size(); i++) {
//            depTime.add(getDriver().findElements(By.xpath("//div[@data-qa='depart-time']")).get(i).getText());
//            System.out.println("depTime " + depTime);
//        }
//
//        //Storing Arrival Cities Time for 1st journey
//        for (int i = 0; i < getDriver().findElements(By.xpath("//div[@data-qa='arrive-time']")).size(); i++) {
//            arrTime.add(getDriver().findElements(By.xpath("//div[@data-qa='arrive-time']")).get(i).getText());
//            System.out.println("arrTime " + arrTime);
//        }
//
//        //Storing NK Number for 1st journey
//        for (int i = 0; i < getDriver().findElements(By.xpath("//div[@class='col-12 flight-info-header']//p[contains(text(),'Flight')]")).size(); i++) {
//            nkInfo.add(getDriver().findElements(By.xpath("//div[@class='col-12 flight-info-header']//p[contains(text(),'Flight')]")).get(i).getText().replace("Flight ", "").replace(" ", ""));
//            System.out.println("nkInfo " + nkInfo);
//        }
//
//        //Closing Flight Info Pop Up
//        pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();
//        WaitUtil.untilTimeCompleted(1000);
//
//        //Recording Returning Journey Information
//        pageObjectManager.getFlightAvailabilityPage().getSelectedReturningFlightNatureButton().get(0).click();
//        WaitUtil.untilTimeCompleted(1000);
//
//        //Storing Departure Cities Name for 2nd journey
//        for (int i = 0; i < getDriver().findElements(By.xpath("//div[@data-qa='depart-city-station']//p[2]")).size(); i ++){
//            depCityName.add(getDriver().findElements(By.xpath("//div[@data-qa='depart-city-station']//p[2]")).get(i).getText());
//            System.out.println("depCityName " + depCityName);
//        }
//
//        //Storing Arrival Cities Name for 2nd journey
//        for (int i = 0; i < getDriver().findElements(By.xpath("//div[@data-qa='arrive-city-station']//p[2]")).size(); i ++){
//            arrCityName.add(getDriver().findElements(By.xpath("//div[@data-qa='arrive-city-station']//p[2]")).get(i).getText());
//            System.out.println("arrCityName " + arrCityName);
//        }
//
//        //Storing Departure Cities Time for 2nd journey
//        for (int i = 0; i < getDriver().findElements(By.xpath("//div[@data-qa='depart-time']")).size(); i ++){
//            depTime.add(getDriver().findElements(By.xpath("//div[@data-qa='depart-time']")).get(i).getText());
//            System.out.println("depTime " + depTime);
//        }
//
//        //Storing Arrival Cities Time for 2nd journey
//        for (int i = 0; i < getDriver().findElements(By.xpath("//div[@data-qa='arrive-time']")).size(); i ++){
//            arrTime.add(getDriver().findElements(By.xpath("//div[@data-qa='arrive-time']")).get(i).getText());
//            System.out.println("arrTime " + arrTime);
//        }
//
//        //Storing NK Number for 2nd journey
//        for (int i = 0; i < getDriver().findElements(By.xpath("//div[@class='col-12 flight-info-header']//p[contains(text(),'Flight')]")).size(); i++) {
//            nkInfo.add(getDriver().findElements(By.xpath("//div[@class='col-12 flight-info-header']//p[contains(text(),'Flight')]")).get(i).getText().replace("Flight ", "").replace(" ", ""));
//            System.out.println("nkInfo " + nkInfo);
//        }
//
//        //Closing Flight Info Pop Up
//        pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();

//-- STep 9: Under the Choose your hotel section, a list of hotels should display, verify there is a promotion message link
//Note: This will only display when a hotel has a promotion
        WaitUtil.untilPageLoadComplete(getDriver());
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getHotelPage().getSortByDropDown(),"Sort By: Price: Low to High");
        WaitUtil.untilTimeCompleted(1200);

        for (int i = 0; i < pageObjectManager.getHotelPage().getHotelCard().size(); i ++) {
            if ( TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getLimitedTimePromotionLink())) {
                ValidationUtil.validateTestStep("Validating Limited Time promotion Link appear on Hotel Page", true);
            }
        }

//-- Step 10: Without selecting a Hotel, scroll down and verify there is no CONTINUE button at the end of the page. Instead, there should be a "All Hotels" link under the number of pages
        ValidationUtil.validateTestStep("Validating there is not option to continue without hotel selection",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getVacationPathContinueButton()));

//-- Step 11: Select a Hotel by clicking the VIEW button
//Note: capture selection for future reference
        pageObjectManager.getHotelPage().getHotelViewButton().get(0).click();
        WaitUtil.untilPageLoadComplete(getDriver());

//-- Step 12: On the Modal, select a room category by clicking the SELECT ROOM button
//Note: capture selection for future reference
        WaitUtil.untilTimeCompleted(3000);
        pageObjectManager.getHotelPage().getHotelWindowRoomPricesButton().click();
        WaitUtil.untilTimeCompleted(3000);

        pageObjectManager.getHotelPage().getHotelPopUpSelectRoomButton().get(0).click();
        WaitUtil.untilPageLoadComplete(getDriver());

//-- Step 13: Click CONTINUE at the end of the page
        pageObjectManager.getHotelPage().getVacationPathContinueButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        //check if car page appear
        if(TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageContinueWithoutCarButton())){
            pageObjectManager.getCarPage().getCarsPageContinueWithoutCarButton().click();
        }

        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);
        WaitUtil.untilPageLoadComplete(getDriver());

        /******************************** Passenger Information Page *****************************************/
//-- Step 14: Enter passengers info and continue
//both passenger 16 yrs old
        pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(0).sendKeys(DOB16YearOld);
        pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(1).sendKeys(DOB16YearOld);

        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        WaitUtil.untilTimeCompleted(2000);

        //Insufficient Miles Popup is displayed
        ValidationUtil.validateTestStep("User verify Header section of Insufficient Age PopUp on Passenger Information Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getInsufficientAgePopUpHeaderText()));

        ValidationUtil.validateTestStep("User verify Flight Only button section of Insufficient Age PopUp on Passenger Information Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getInsufficientAgePopUpFlightOnlyButton()));

        ValidationUtil.validateTestStep("User verify body section of Insufficient Age PopUp on Passenger Information Page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getInsufficientAgePopUpVerbageText()));

//-- Step 15: Close the popup by clicking the X and Click continue on the availability page one more time
        pageObjectManager.getPassengerInfoPage().getInsufficientAgePopUpCloseButton().click();

        ValidationUtil.validateTestStep("User click close button of Insufficient Age PopUp on Passenger Information Page", true);

        WaitUtil.untilTimeCompleted(2000);

//-- Step 16: Click on the FLIGHT ONLY button
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
        WaitUtil.untilTimeCompleted(3000);
        pageObjectManager.getPassengerInfoPage().getInsufficientAgePopUpFlightOnlyButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Validating user is taken back to home page",
                getDriver().getCurrentUrl(),HOME_PAGE_URL);
    }
}