package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC88617
//Description: Task 27855: TC88617- 007 - CP - Flight Flex Packaging Verbiage (Spanish) - Flight + Hotel + Car - Validate verbiage for Flight Flex on the Thrills Pop-Up
//Created By: Gabriela
//Created On: 25-Nov-2019
//Reviewed By: Anthony Cardona
//Reviewed On: 06-Dec-2019
//**********************************************************************************************

public class TC88617 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"ActiveBug", "BookPath", "RoundTrip", "DomesticDomestic", "FlightHotelCar", "Outside21Days", "Adult", "Guest","FlightAvailabilityUI","Spanish"})
    public void CP_Flight_Flex_Packaging_Verbiage_Spanish_Flight_Hotel_Car_Validate_verbiage_for_Flight_Flex_on_the_Thrills_Pop_Up(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC88617 under GoldFinger Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "132";
        final String ARR_DATE           = "133";
        final String ADULT              = "5";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE         = "25+";
        final String ROOMS_VALUE        = "2 Rooms";

        //Flight Availability Page Constant Values
        final String F_H_PAGE           = "/book/hotels";
        final String F_C_PAGE           = "/book/options/cars";

        //Save and Upgrade Pop Up Constant Values
        final String  FLIGHT_FLEX       = "Flight Flex - ¡Modifique su vuelo una vez gratis! (podrían aplicar diferencias en la tarifa)";

//- Step 8: Land on current test environment.
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        //*** Home Page **/
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 1: Click on Vacation tab and keep the radio button defaulted to Flight + Hotel + Car
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);

//- Step 2: Input the following: DOM_DOM | Any Date 3 months in the future | 5 PAX | 2 Room | Driver 25+ and click SEARCH VACATION
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectHotelRoom(ROOMS_VALUE);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("The user redirected to the correct page",
                getDriver().getCurrentUrl(),(F_H_PAGE));

//- Step 3: Select any hotel and proceed options/cars page
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("NA","NA");

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("The user redirected to the correct page",
                getDriver().getCurrentUrl(),(F_C_PAGE));

//- Step 4: Click Español near the SIGN-IN link in the header.
        WaitUtil.untilPageLoadComplete(getDriver());
        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getHomePage().getSelectedLanguage().get(0));
        WaitUtil.untilPageLoadComplete(getDriver());

//- Step 5: Book any car by clicking RESERVAR AUTO
        pageMethodManager.getCarPageMethods().selectCarOnCarPage("NA","NA");

//- Step 6: Click CONTINUE at the bottom for the cars page
        //Invalid Step

//- Step 7: In the Bundle Pop up, verify the Bundle It option contains the following verbiage:
        ValidationUtil.validateTestStep("Validating Flight Flex verbiage on Save & Upgrade pop up",
                getDriver().findElement(By.xpath("//app-branded-modal//ul//li[6]")).getText(),FLIGHT_FLEX);
    }
}