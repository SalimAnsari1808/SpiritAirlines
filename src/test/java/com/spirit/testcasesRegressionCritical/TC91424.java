package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

//**********************************************************************************************
//Test Case ID: TC91424
//Test Name: Task 24868: 35186 Search Widget_CP_BP_Vacation_Testing the Passengers Section
// Created By: Gabriela Gonzalez
//Created On : 05-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 20-Aug-2019
//**************************************************************************************************

public class TC91424 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "FlightCar" , "DomesticDomestic" , "Outside21Days" , "Adult" , "Guest" , "BookIt" , "CarryOn" , "CheckedBags" , "NoSeats","HomeUI" })
    public void Search_Widget_CP_BP_Vacation_Testing_the_Passengers_Section(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91424 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE 			    = "English";
        final String JOURNEY_TYPE 		    = "Vacation";
        final String TRIP_TYPE 			    = "Flight+Car";
        final String DEP_AIRPORTS 		    = "AllLocation";
        final String DEP_AIRPORT_CODE 	    = "FLL";
        final String ARR_AIRPORTS 		    = "AllLocation";
        final String ARR_AIRPORT_CODE 	    = "LAS";
        final String DEP_DATE 			    = "35";
        final String ARR_DATE 			    = "36";
        final String DRIVER_AGE 		    = "25+";

        final String FLIGHT_CAR             = "Flight + Car";
        final String FLIGHT_HOTEL           = "Flight + Hotel";
        final String FLIGHT_HOTEL_CAR       = "Flight + Hotel + Car";

        //Flight Availability Page Constant Values
        final String FLIGHT_AVAILABILITY_URL= "/book/flights-cars";
        final String UPGRADE_VALUE          = "BookIt";

        //Bags Page Constant Values
        final String DEP_BAGS               = "Carry_1|Checked_2||Carry_1|Checked_2||Carry_1|Checked_2";
        final String FARE_TYPE			    = "Standard";

        //Payment Page Constant Values
        final String PAYMENT_URL            = "/book/payment";

        //open browser
        openBrowser( platform);

        //**************************Home Page Methods********************************/
//-- Step 12: Access testing environment
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//-- Step 1: On the Search Widget, under the Book Tab, select Vacation
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);

        ValidationUtil.validateTestStep("Validating Flight + Car text",
                pageObjectManager.getHomePage().getFlightCarLabel().getText(),FLIGHT_CAR);

        ValidationUtil.validateTestStep("Validating Flight + Hotel text",
                pageObjectManager.getHomePage().getFlightHotelRadiobutton().getText(),FLIGHT_HOTEL);

        ValidationUtil.validateTestStep("Validating Flight + Hotel + Car text",
                pageObjectManager.getHomePage().getFlightHotelCarRadiobutton().getText(),FLIGHT_HOTEL_CAR);

//-- Step 2: Select Flight+ Car and a DOM city pair
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);

//-- Step 3: Click on the Adults and Children chevron in content box
        pageObjectManager.getHomePage().getPassengerBox().click();
        WaitUtil.untilTimeCompleted(1000);

        List<WebElement> paxBox = pageObjectManager.getHomePage().getPassengerTypeText();
        List<String> paxList = new ArrayList<>();

        for (int count = 0; count < paxBox.size(); count ++) {
            paxList.add(paxBox.get(count).getText());
        }

        for (int count = 0; count < paxList.size(); count ++) {
            ValidationUtil.validateTestStep("Validating Adult and Child options are properly displayed on the passenger chevron",
                    paxList.get(count).equals("Adults")||paxList.get(count).equals("Children\n" + "Ages 0-17"));
        }

//-- Step 4: Click on the chevron symbol for adult till you max it out (8).
        for (int count = 0; count < 6; count ++) {
            pageObjectManager.getHomePage().getAdultPlusLink().click();
        }

        WaitUtil.untilTimeCompleted(3000);
        ValidationUtil.validateTestStep("Validating for vacation path only are allowed to select 8 pax",
                pageObjectManager.getHomePage().getAdultPlusLink().getAttribute("class"),"disabled" );

//-- Step 5: Click on the Child chevron symbol to confirm no more passengers can be added.
        ValidationUtil.validateTestStep("Validating for vacation path not allowing to select more children when already are selected 8 pax",
                pageObjectManager.getHomePage().getChildPlusLink().getAttribute("class"),"disabled" );

//-- Step 6: Click on chevron symbol to remove all adult passengers
        for (int count = 0; count < 7; count ++) {
            pageObjectManager.getHomePage().getAdultMinusLink().click();
        }

        WaitUtil.untilTimeCompleted(3000);
        ValidationUtil.validateTestStep("Validating Minus button is inactive when adults counter is in zero",
                pageObjectManager.getHomePage().getAdultMinusLink().getAttribute("class"),"disabled" );

//-- Step 7: Click on the chevron symbol for children till you max it out.
        for (int count = 0; count < 7; count ++) {
            pageObjectManager.getHomePage().getChildPlusLink().click();
        }

        ValidationUtil.validateTestStep("Validating plus button get inactive once the passenger total amount get 8",
                pageObjectManager.getHomePage().getChildPlusLink().getAttribute("class"),"disabled" );

        for (int count = 0; count < 6; count ++) {
            pageObjectManager.getHomePage().getChildMinusLink().click();
        }

        for (int count = 0; count < 1; count ++) {
            pageObjectManager.getHomePage().getAdultPlusLink().click();
        }
        pageObjectManager.getHomePage().getPromoCodeLink().click();

//-- Step 8: Now add 2 ADT, 1 Child > 6 - select Drivers age 25+. Click SEARCH
        //Selecting 25+
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);

        //Selecting date
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);

        // Clicking on SEARCH VACATION button
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Filling child information
        pageObjectManager.getHomePage().getChildPopUpBirthBoxes().get(0).sendKeys(TestUtil.getStringDateFormat((-2690 - 0), "MM/dd/yyyy"));

        WaitUtil.untilTimeCompleted(3000);
        pageObjectManager.getHomePage().getChildPopUpCloseButton().click();

        //*********************Flight Availability Page Methods**************************/
//-- Step 9: User should now land on Car Availability page to select a Car
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Verify user reached on flight + car Availability",
                getDriver().getCurrentUrl(),(FLIGHT_AVAILABILITY_URL));

        pageObjectManager.getCarPage().getCarsPageBookButton ().get(0).click();
//        pageObjectManager.getCarPage().getBookCarButton().get(0).click();
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //*********************Passenger Information Page Methods**************************/
//-- Step 10: Select Bare Fare and reach Payment page with 1CO / 2CB's / seats for all PAX / no extras
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();

        //Selecting primary driver from the dropdown
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPassengerInfoPage().getPrimaryDriverDropDown(),
                pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0).getAttribute("value") + " " +
                        pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(0).getAttribute("value"));

        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //**************************** Bags Page Methods****************************/
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

        //****************************Seats Page Methods **********************************/
        int count = 0;
        //Selecting standard seats for pax
        for (int seatcount = 0; seatcount < pageObjectManager.getSeatsPage().getStandardSeatsGridView().size(); seatcount ++) {
            if (count <= 2) {
                if (!pageObjectManager.getSeatsPage().getStandardSeatsGridView().get(seatcount).getAttribute("class").contains("unavailable")){
                    pageObjectManager.getSeatsPage().getStandardSeatsGridView().get(seatcount).click();
                    count++;
                }
            }else {
                break;
            }
        }
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

        //*********************Options Page Methods ******************************/
        //Selecting options from the check in drop down
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getOptionsPage().getCheckInOptionCardBodySelectDropDown(),
                "I'll check in at Spirit.com/Mobile for free");

        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //************************Payment Page Constant Values ********************/
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Verify user reached on Payment",
                getDriver().getCurrentUrl(),(PAYMENT_URL));
    }
}