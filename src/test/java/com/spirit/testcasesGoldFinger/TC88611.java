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
//Test Case ID: TC88611
//Description: Flight Flex Packaging Verbiage (Spanish) - Hub Packaging - Validate verbiage for Flight Flex on the Thrills Pop-Up
//Created By: Salim Ansari
//Created On: 26-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC88611 extends BaseClass {

    @Parameters("platform")
    @Test(groups = {"BookPath" , "Flight" , "RoundTrip", "DomesticDomestic" , "Outside21Days" , "Adult" , "NonStop" , "BundleIt" ,"FlightAvailabilityUI "})
    public void Flight_Flex_Packaging_Verbiage_Spanish_Hub_Packaging_Validate_verbiage_for_Flight_Flex_on_the_Thrills_Pop_Up(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC88611 under GoldFinger Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "RoundTrip";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LAS";
        final String DEP_DATE 			= "9";
        final String ARR_DATE 			= "11";
        final String ADULT				= "1";
        final String CHILD				= "0";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT             = "NonStop";
        final String RET_FLIGHT             = "NonStop";
        final String FARE_TYPE              = "Standard";

//Step1--Land on current test environment.
        openBrowser( platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);


//STEP--2 Input the following: RT | DOM_DOM (The city pair need to have the Thrill Combo Offer, actual BUNDLE IT)| Any Date 3 months in the future | 1 PAX and click SEARCH FLIGHTS
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//STEP--3 Select any flights and click CONTINUAR in the ESTANDAR tile
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep",DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret",RET_FLIGHT);


//STEP--4 Click Espanol near the SIGN-IN link
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


        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);

//STEP--5 In the Bundle Pop up, verify the Bundle It option contains the following verbiage: "Flight Flex - ¡Modifique su vuelo una vez gratis! (podrían aplicar diferencias en la tarifa)"
        boolean Flag = false;
        List <WebElement> bundleItBenefit = pageObjectManager.getFlightAvailabilityPage().getBundleTileBenefitsList();
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
