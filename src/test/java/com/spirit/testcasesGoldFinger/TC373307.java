package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

//**********************************************************************************************
//Test Case ID: TC373307
//Description: Task:27929 | TC373307 - 001 - CP - Vacation Flight + Car - NEGATIVE - Validate that a user cannot book an Adult under the age of 21
//Created By: Anthony Cardona
//Created On: 21-Nov-2019
//Reviewed By: Kartik Chauhan
//Reviewed On:10-Nov-2019
//**********************************************************************************************
public class TC373307 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"FlightCar", "DomesticDomestic", "Guest" , "Outside21Days", "Adult","HomeUI"})

    public void CP_Vacation_Flight_Car_NEGATIVE_Validate_that_a_user_cannot_book_an_Adult_under_the_age_of_21(@Optional("NA") String platform) {

        /******************************************************************************
         ***********************Navigate to Payment Page*******************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373289 under GOLDFINGER Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "flight+car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LGA";
        final String DEP_DATE           = "90";
        final String ARR_DATE           = "100";
        final String ADULTS             = "1";
        final String CHILDS             = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DRIVERS_AGE        = "25+";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
//Step2: Click on vacation tab, Flight+car tab
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
//Step 3: Under the drivers age tab, validate you do not have an option for a passenger under 21
        pageObjectManager.getHomePage().getDriversAgeDropDown().click();

        Select primaryDriverAge = new Select(pageObjectManager.getHomePage().getDriversAgeDropDown());

        int optionsOnPrimaryDriverDropDown  =   primaryDriverAge.getOptions().size();
        ValidationUtil.validateTestStep("There is only 2 options on the passenger drop down" ,
                    optionsOnPrimaryDriverDropDown==3);

        List<WebElement> options = primaryDriverAge.getOptions();

        for(WebElement element : options)
        {
            String OptionText = element.getText();

            ValidationUtil.validateTestStep("The option is valid" , OptionText.contains("Driver's Age") ||  OptionText.contains("21-24") || OptionText.contains("25+"));
        }
    }
}