
package com.spirit.testcasesBAT;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC91056
//Description: Search Widget_CP_BP_ Flight+Car+Hotel Valid Promo for Dollars off Code
//Created By : Sunny Sok
//Created On : 11-Mar-2019
//Reviewed By: Salim Ansari
//Reviewed On: 14-Mar-2019
//**********************************************************************************************

public class TC91056 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups={"Guest","BookPath","FlightHotelCar","DomesticDomestic","WithIn7Days","Adult","PromoCode","HomeUI"})
    public void Search_Widget_CP_BP_Flight_Car_Hotel_Valid_Promo_for_Dollars_off_Code(@Optional("NA") String platform) {

		/******************************************************************************
		 *******************************Navigate to Home Page**************************
		 ******************************************************************************/
    	//Mention Suite and Browser in reports 
    	if(!platform.equals("NA")) {
    		ValidationUtil.validateTestStep("Starting Test Case ID TC91056 under BAT Suite on " + platform + " Browser"   , true);
    	}
    	
        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Vacation";
        final String TRIP_TYPE			= "Flight+Hotel+Car";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LAS";
        final String DEP_DATE 			= "3";
        final String ARR_DATE 			= "30";
        final String ADULTS 			= "1";
        final String CHILDS 			= "0";
        final String INFANT_LAP 		= "0";
        final String INFANT_SEAT 		= "0";
        final String DRIVERS_AGE 		= "21-24";
        final String HOTELROOM 			= "1 Room";
        final String PROMOCODE 			= "25PCT";
        
        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVERS_AGE);
        pageMethodManager.getHomePageMethods().selectHotelRoom(HOTELROOM);
        pageMethodManager.getHomePageMethods().enterPromoCode(PROMOCODE);
        
		/******************************************************************************
		 *****************************Validation to Home Pag***************************
		 ******************************************************************************/
        //declare constant used in validation
        final int FIRST_INDEX  		= 1;
        final int SECOND_INDEX 		= 2;
        final String DRIVER_BELOW25 = "21-24";
        final String DRIVER_ABOVE25 = "25+";
        final String HOTELPAGEURL   = "book/hotels";
        final String SELECTED_PAX   = "1 Adult";
       
        //Validate after selecting F+H+C hotel room drop down appear Home Page
        ValidationUtil.validateTestStep("Verify after selecting F+H+C hotel room drop down appear Home Page",
                TestUtil.verifyElementDisplayed(getDriver(), By.xpath(pageObjectManager.getHomePage().xpath_RoomsDropDown)));
        
        //Validate after selecting F+H+C driver age drop down appear Home Page
        ValidationUtil.validateTestStep("Verify after selecting F+H+C driver age drop down appear Home Page",
                TestUtil.verifyElementDisplayed(getDriver(),By.xpath(pageObjectManager.getHomePage().xpath_DriversAgeDropDown)));
        
        //get all the list of drop down option from driver drop down
        Select drpdnDriverAge = new Select(pageObjectManager.getHomePage().getDriversAgeDropDown());
        List<WebElement> drpdnDriverAgeOptions = drpdnDriverAge.getOptions();
        
        //Verify first driver age option appear in driver age drop down is 21-24
        ValidationUtil.validateTestStep("Verify first driver age option appear in driver age drop down is 21-24 on Home Page",
                drpdnDriverAgeOptions.get(FIRST_INDEX).getText(),DRIVER_BELOW25);
        
        //Verify second driver age option appear in driver age drop down is 25+
        ValidationUtil.validateTestStep("Verify second driver age option appear in driver age drop down is 25+ on Home Page",
                drpdnDriverAgeOptions.get(SECOND_INDEX).getText(),DRIVER_ABOVE25);
        
        //Verify User selected 1 Adult for Flight+Hotel+Car booking
		ValidationUtil.validateTestStep("Verify User selected 1 Adult for Flight+Hotel+Car booking on Home Page",
                JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getHomePage().getPassengerBox()),SELECTED_PAX);
  
		//click on continue button
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Validating user proceeds to hotels page
        ValidationUtil.validateTestStep("Verify user after selecting Flight+Hotel+Car booking with promo code proceeds to Hotels page", getDriver().getCurrentUrl().contains(HOTELPAGEURL));
    }
}