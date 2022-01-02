package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

//**********************************************************************************************
//Test Case ID: TC88615
//Description : Task 27832: TC88615- 005 - CP - Flight Flex Packaging Verbiage (Spanish) - Flight + Hotel - Validate verbiage for Flight Flex on the Thrills Pop-Up
//Created By  : Gabriela
//Created On  : 21-Nov-2019
//Reviewed By :
//Reviewed On :
//**********************************************************************************************

public class TC88615 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"Spanish", "BookPath", "RoundTrip", "DomesticDomestic", "FlightHotel", "Outside21Days", "Adult",
                    "NineDFC", "FlightAvailabilityUI"})
    public void CP_Flight_Flex_Packaging_Verbiage_Spanish_Flight_Hotel_Validate_verbiage_for_Flight_Flex_on_the_Thrills_Pop_up(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC88615 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String LOGIN_ACCOUNT      = "NineDFCEmail";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "132";
        final String ARR_DATE           = "133";
        final String ADULT              = "2";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE 		= "25+";

        //Save and Upgrade Pop Up Constant Values
        final String  FLIGHT_FLEX       = "Flight Flex - ¡Modifique su vuelo una vez gratis! (podrían aplicar diferencias en la tarifa)";

//- Step 9: Land on current test environment.
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 1: Login as a $9 FC member
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);

//- Step 2: Click on Vacation tab and select Flight + Hotel
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);

//- Step 3: Input the following: DOM_DOM (The city pair need to have the Thrill Combo Offer, actual BUNDLE IT) | Any Date 3 months in the future | 2 PAX | 1 Room and click SEARCH VACATION
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 4: Select any hotel and proceed options/cars page
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("NA" , "NA");

//- Step 5: Click Espanol near the SIGN-IN link
        for(WebElement appLanguage: pageObjectManager.getHomePage().getSelectedLanguage()) {
            //check for displayed language link on Home Page
            if(appLanguage.isDisplayed()) {

                //change to Spanish language
                JSExecuteUtil.clickOnElement(getDriver(), appLanguage);

                //wait till page is stable
                WaitUtil.untilPageLoadComplete(getDriver());

                //validate Spanish language is selected
                ValidationUtil.validateTestStep("User selected Spanish Language on Home Page",
                        appLanguage.getText().trim().equalsIgnoreCase("English"));

                break;
            }
        }

//- Step 6 & 7: Book any car by clicking RESERVAR AUTO &Click CONTINUE at the bottom for the cars page
        pageMethodManager.getCarPageMethods().selectCarOnCarPage("NA","NA");

        boolean Flag = false;
        List<WebElement> bundleItBenefit = pageObjectManager.getFlightAvailabilityPage().getBundleTileBenefitsList();
        for(WebElement bundleItBenefits : bundleItBenefit){
            if(bundleItBenefits.isDisplayed()){
                if(bundleItBenefits.getText().equals("Flight Flex - ¡Modifique su vuelo una vez gratis! (podrían aplicar diferencias en la tarifa)")){
                    Flag = true;
                    break;
                }
            }
        }

        ValidationUtil.validateTestStep("Verify the ¡Modifique su vuelo una vez gratis! verbiage in the Bundle it section of the pop up on Flight Availability Page", Flag);

    }
}