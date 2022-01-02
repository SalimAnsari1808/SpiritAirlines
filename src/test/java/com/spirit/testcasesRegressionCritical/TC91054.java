package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

//**********************************************************************************************
//Test Case ID: TC91054
//Description: Task 22958: 31274 Search Widget_CP_BP_Flight+Car Valid Promo for dollars Code
//Created By: Gabriela
//Created On: 12-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 13-Aug-2019
//**********************************************************************************************

public class TC91054 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "DomesticDomestic" , "FlightHotel" , "FlightCar" , "FlightHotelCar" , "HomeUI", "PromoCode", "ActiveBug"})


    public void Search_Widget_CP_BP_Flight_Car_Valid_Promo_for_dollars_Code(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91054 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
//-- Step 1: Speak with lead on find out which Promo code are available for money off (not percentage) and is vaild for Flight+Car Vaction Booking
        //Home Page Constant Values
        final String LANGUAGE 			        = "English";
        final String JOURNEY_TYPE 	  	        = "Vacation";
        final String TRIP_TYPE 			        = "Flight+Car";
        final String DEP_AIRPORTS 	  	        = "AllLocation";
        final String DEP_AIRPORT_CODE 	        = "FLL";
        final String ARR_AIRPORTS 	  	        = "AllLocation";
        final String ARR_AIRPORT_CODE 	        = "LAX";
        final String DEP_DATE 		    	    = "7";
        final String ARR_DATE 	    		    = "10";
        final String ADULT   		       	    = "1";
        final String CHILDREN 		            = "0";
        final String INFANT_LAP 	    	    = "0";
        final String INFANT_SEAT 		        = "0";

        final String FLIGHT_CAR                 = "Flight + Car";
        final String FLIGHT_HOTEL               = "Flight + Hotel";
        final String FLIGHT_HOTEL_CAR           = "Flight + Hotel + Car";
        final String DRIVER_AGE 		        = "25+";

        final String TOOLTIP_TEXT               = "You'll find our Promo Codes in our promotional emails.\n" + "Sign Up Now";
        final String SPECIAL_CHARACTER          = "!@#$%";
        final String SPECIAL_CHARACTER_ERROR    = "Only letters and numbers are allowed";
        final String MORE_THAN_EIGHT_CHARACTER  = "123456789123";
        final String VALID_PROMO_CODE           = "25PCT";

        //Flight Availability Page Constant Values
        final String F_A_URL                    = "/book/flights-cars";

        //open browser
        openBrowser(platform);

        /****************************************** Home Page Methods ************************************/
//-- Step 2: Access Skysales testing environment
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//-- Step 3: On the Search Widget, under the Book Tab, select Vacation
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);

        ValidationUtil.validateTestStep("Validating Flight + Car text",
                pageObjectManager.getHomePage().getFlightCarLabel().getText(),FLIGHT_CAR);

        ValidationUtil.validateTestStep("Validating Flight + Hotel text",
                pageObjectManager.getHomePage().getFlightHotelRadiobutton().getText(),FLIGHT_HOTEL);

        ValidationUtil.validateTestStep("Validating Flight + Hotel + Car text",
                pageObjectManager.getHomePage().getFlightHotelCarRadiobutton().getText(),FLIGHT_HOTEL_CAR);

//-- Step 4: Select Flight+ Car and a DOM city pair
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);

        //Note: Preferably a major city where car rentals are available
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);

//-- Step 5: Add 1 adult
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILDREN, INFANT_SEAT, INFANT_LAP);

//-- Step 6: Select the drivers age
        List<WebElement> ageDropDown =  new Select(pageObjectManager.getHomePage().getDriversAgeDropDown()).getOptions();
        List<String> dropDownValue = new ArrayList<>();
        for (int count = 0; count < ageDropDown.size(); count ++) {
            dropDownValue.add(ageDropDown.get(count).getAttribute("value"));
        }

        for (int count = 1; count < dropDownValue.size(); count ++) {
            ValidationUtil.validateTestStep("Validating age drop down contains the right 2 options",
                    dropDownValue.get(count).equals("21")||dropDownValue.get(count).equals("25"));
        }
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);

//-- Step 7: Select a Departing and Returning date that is 48 hours later  (Car will not be offered if flight is within 48hrs of booking to minimize fraud)
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);

//-- Step 8: Click on the blue question mark tool tip directly to the right of Add a Promo Code link
        pageObjectManager.getHomePage().getOptionalServicesPopoverLink().click();

        System.out.println("lo que muestra " + pageObjectManager.getHomePage().getPopOverContainerText().getText());
        ValidationUtil.validateTestStep("Validating the right info is displayed",
                pageObjectManager.getHomePage().getPopOverContainerText().getText(), TOOLTIP_TEXT);

//-- Step 9: Click on " Add a Promo Code" link
        pageObjectManager.getHomePage().getPromoCodeLink().click();
        WaitUtil.untilTimeCompleted(1200);

        ValidationUtil.validateTestStep("Validating Promo Code field box should populate in that space, replacing the tool tip",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getPromoCodeTextBox()));

        //Promo code text input is alphanumeric only.
        pageObjectManager.getHomePage().getPromoCodeTextBox().sendKeys(SPECIAL_CHARACTER);

        //click on search button
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Promo code text input is alphanumeric only.
        ValidationUtil.validateTestStep("Verify Promo code text input accept only alphanumeric character on Home Page",
                pageObjectManager.getHomePage().getErrorMessageText().getText(),SPECIAL_CHARACTER_ERROR);

        //clear promo code box
        TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getHomePage().getPromoCodeTextBox());

        //Input Invalid PromoCode
        pageObjectManager.getHomePage().getPromoCodeTextBox().sendKeys(MORE_THAN_EIGHT_CHARACTER);

        //TODO: Bug 21419: GAP: CP: BP: Search Widget SW: Promo Codes are only 8 Characters long should the Promo Text box allow infinite Characters?
        //Promo code can have a maximum 8 characters
        ValidationUtil.validateTestStep("Verify Promo code can have a maximum 8 characters on Home Page",
                pageObjectManager.getHomePage().getErrorMessageText().getText().length()==8);

        pageMethodManager.getHomePageMethods().clickSearchButton();

        //clear promo code box
        TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getHomePage().getPromoCodeTextBox());

//-- Step 10: Enter a valid Promo Code and select SEARCH VACATION
        WaitUtil.untilTimeCompleted(3000);
        pageObjectManager.getHomePage().getPromoCodeTextBox().sendKeys(VALID_PROMO_CODE);

        pageMethodManager.getHomePageMethods().clickSearchButton();

        /****************************************** Flight Availability Page Methods ************************************/
        WaitUtil.untilPageLoadComplete(getDriver());

//-- Step 11: User should now land on Car Availability page, where the flights are pre-selected, to select a Car
        ValidationUtil.validateTestStep("Verify Car Availability Page load",
                getDriver().getCurrentUrl(),(F_A_URL));
    }
}