package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test CaseID: TC349832
//Title      : Task: 28187 |TC349832 - 006 - CP - Flight + Hotel + Car - Availability Page - NEG Validate old filter is not displaying
//Created By : Anthony Cardona
//Created On : 01-Dec-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************+
public class TC349832 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"ActiveBug", "BookPath", "RoundTrip", "DomesticDomestic", "FlightHotelCar", "Outside21Days", "Adult", "Guest"})
    public void CP_Flight_Hotel_Availability_Page_NEG_Validating_One_ADT_is_required_per_room(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC349832 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "132";
        final String ARR_DATE           = "133";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE         = "25+";
        final String HOTELROOM          = "1 Room";

//- Step 1: Access test environment [nav01.spirit.com]
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 2: Start booking a Vacation [Flight + Hotel + Car]
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().selectHotelRoom(HOTELROOM);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//Step 3: Validate the heading on the Flight Availability page matches the given screenshot
        pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityPageHeaderText();

        ValidationUtil.validateTestStep("The \"Review Your Flights\" SubHeader is displayed" , getDriver().findElement(By.xpath("(//app-flights-hotels-page//div[1]//span[1]/..)[1]")).getText() , "Review Your Flights");
        ValidationUtil.validateTestStep("The \"Choose Your Hotel\" SubHeader is displayed" , pageObjectManager.getHotelPage().getChooseYourHotelHeaderText().getText() , "Choose Your Hotel");

//Step 4: Validate there isn't a filter under the "Choose Your Hotel" section
        ValidationUtil.validateTestStep("The Hotel TextBox Filter is not displayed: pass" , !TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getFilterByNameTextBox()));
        ValidationUtil.validateTestStep("The Hotel Sort By Filter is not displayed: pass" , !TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getSortByDropDown()));
        ValidationUtil.validateTestStep("The Hotel Display Number Filter is not displayed: pass" , !TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getDisplayNumberDropDown()));
    }
}
