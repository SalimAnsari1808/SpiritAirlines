package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

//**********************************************************************************************
//Test Case ID: TC91057
//Description:  Search Widget_CP_BP_Seasonal Service
//Created By : Anthony Cardona
//Created On : 12-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 13-Aug-2019
//**********************************************************************************************
public class TC91057 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "Adult" , "HomeUI", "Guest", "WithIn7Days"})
    public void Search_Widget_CP_BP_Seasonal_Service(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91057 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        final String LANGUAGE               = "English";
        final String JOURNEY_TYPE           = "Flight";
        final String TRIP_TYPE              = "Oneway";
        final String DEP_AIRPORTS           = "AllLocation";
        final String DEP_AIRPORT_CODE       = "PBI";
        final String ARR_AIRPORTS           = "AllLocation";
        final String ARR_AIRPORT_CODE       = "ACY";
        final String ADULTS                 = "1";
        final String CHILDREN               = "0";
        final String INFANT_LAP             = "0";
        final String INFANT_SEAT            = "0";
        final String ARR_DATE               = "NA";

        String          DEP_DATE = "";
        String          START_OF_SERVICE_DATE = "";

        //Seasonal Pop-Up will only display for PBI between 11/14/2019 and 04/14/2020, skip test case if date is between season

            SimpleDateFormat SearchWidgetformat = new SimpleDateFormat("MM/dd/yyyy");//Date format on search widget
            SimpleDateFormat textFormat         = new SimpleDateFormat("MMMMM dd, yyyy");// date format on the pop-up

        Date today = null;
        Date seasonalStartDate = null;
        Date seasonalEndDate = null;
        boolean skipTest = false;

        try {
            today = new Date();//current date
            seasonalStartDate = SearchWidgetformat.parse("11/14/2019"); //start of the seasonal
            seasonalEndDate = SearchWidgetformat.parse("04/14/2020"); //End of the seasonal flight
        }
        catch (Exception e){/*do nothing*/}

            //if operator greater it will return a +1, if it is same it will return 0, if it is less it returns -1
            boolean isTodayLessThanSeasonalStartDate  = (today.compareTo(seasonalStartDate) <= 0);
            boolean isTodayGreaterThanSeasonalEndDate = (seasonalEndDate.compareTo(today) >= 0);

            System.out.println("isTodayLessThanSeasonalStartDate: " + isTodayLessThanSeasonalStartDate);
            System.out.println("isTodayGreaterThanSeasonalEndDate: " + isTodayGreaterThanSeasonalEndDate);

            //If today is a valid day for pop-up, select todays date, else skip test case
            if (isTodayLessThanSeasonalStartDate && isTodayGreaterThanSeasonalEndDate)
            {
                DEP_DATE = "0";
            }
            else
            {
                skipTest = true;
            }
            START_OF_SERVICE_DATE = textFormat.format(seasonalStartDate);//String of service start date as it appears on the pop-up

        openBrowser(platform);
        //***********************************Home Page Section*****************************************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();

        ValidationUtil.skipTestCase("Date could not be found" , skipTest);

        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        String message               = pageObjectManager.getHomePage().getSeasonalServiceText().getText();
        String faresStartMessage     = "Seasonal service to/from West Palm Beach, FL resumes on " + START_OF_SERVICE_DATE + ".";

        ValidationUtil.validateTestStep("The Seasonal Service message is correct" ,  faresStartMessage , message);

        pageObjectManager.getHomePage().getSeasonalServiceCloseButton().click();

        WaitUtil.untilTimeCompleted(2000);

        pageMethodManager.getHomePageMethods().clickSearchButton();

        pageObjectManager.getHomePage().getSeasonalServiceCloseWindowButton().click();
    }
}