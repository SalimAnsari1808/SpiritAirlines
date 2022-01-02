package com.spirit.testcasesSMOKE;


import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

// **********************************************************************************************
// TestCase : CP_Bag-O-Tron New Trip_ RT DOM_Purchase with Miles
// Description: Validate that prices displayed in bag-o-tron are consistent when completing a booking
// Created By : Salim Ansari
// Created On : 2-May-2019
// Reviewed By: Kartik Chauhan
// Reviewed On: 2-May-2019
// **********************************************************************************************
public class TC90828 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"OutOfExecution","BagOTron","RoundTrip","DomesticColombia","WithIn7Days","Adult","Child","FSMiles"})

    public void CP_Bag_O_Tron_New_Trip_RT_DOM_Purchase_with_Miles(@Optional("NA")String platform){
        //*********Navigate to Flight Availability Page*****************
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90828 under SMOKE Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";

        //Bag-O-Tron Constant Values
        final String LOGIN_ACCOUNT      = "FSEmail";
        final String TRIP_TYPE          = "NewTrip";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORT_CODE 	= "BOG";
        final String DEP_DATE 	        = "6";
        final String ARR_DATE 		    = "8";
        final String ADULTS	    	    = "2";
        final String CHILDREN		    = "1";
        final String INFANT_LAP		    = "0";
        final String INFANT_SEAT	    = "0";
        final String DISPLAY_PRICE      = "DisplayBagPrice";

        //open browser
        openBrowser( platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);
        WaitUtil.untilTimeCompleted(2000);
        pageObjectManager.getHomePage().getOptionalServiceLink().click();

        //Bag-O-Tron Page Methods
        pageMethodManager.getOptionalServicesPageMethods().selectTrip(TRIP_TYPE);
        pageMethodManager.getOptionalServicesPageMethods().selectAirports(DEP_AIRPORT_CODE,ARR_AIRPORT_CODE);
        pageMethodManager.getOptionalServicesPageMethods().selectDates(DEP_DATE,ARR_DATE);
        pageMethodManager.getOptionalServicesPageMethods().selectTravellingPassenger(ADULTS,CHILDREN,INFANT_LAP,INFANT_SEAT);
        pageMethodManager.getOptionalServicesPageMethods().selectFreeSpiritMiles();
        pageMethodManager.getOptionalServicesPageMethods().selectBagoTronButton(DISPLAY_PRICE);
        WaitUtil.untilPageLoadComplete(getDriver(),(long)120);

        //Out of execution due no miles fares available in this season
        pageMethodManager.getOptionalServicesPageMethods().selectBookTrip();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        //********Validation on Flight Availability Page***************
        //declare constant used in validation
        final String FLIGHT_PAGE_URL    = "book/flights";

        WaitUtil.untilTimeCompleted(3000);
        ValidationUtil.validateTestStep("Passenger from Bag-O-Tron with free spirit miles reach to Flight Availablity Page ",
                getDriver().getCurrentUrl(),FLIGHT_PAGE_URL);
    }
}

