package com.spirit.testcasesRegressionCritical;
import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

// **********************************************************************************************
// TestCase ID: TC90821
// TestCase : CP_Bag-O-Tron New Trip_ OW INT_Promo Code
// Created By : Kartik Chauhan
// Created On : 21-Jun-2019
// Reviewed By: Salim Ansari
// Reviewed On: 24-Jun-2019
// **********************************************************************************************

public class TC90821 extends BaseClass {

    @Parameters ({"platform"})
    @Test(groups = {"BagOTron" , "OneWay" , "InternationalDomestic" , "WithIn7Days" , "Adult" , "Guest" , "PromoCode"})
    public void CP_Bag_O_Tron_New_Trip_OW_INT_Promo_Code (@Optional("NA")String platform){
        /******************************************************************************
         ****************************Navigate to Bags Page via FA user*****************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90821 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";

        //Bag-O-Tron Constant Values
        final String TRIP_TYPE          = "NewTrip";
        final String DEP_AIRPORT_CODE 	= "CUN";
        final String ARR_AIRPORT_CODE 	= "LAS";
        final String DEP_DATE 			= "6";
        final String ARR_DATE 			= "NA";
        final String ADULTS				= "1";
        final String CHILDS				= "0";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT		= "0";
        final String DISPLAY_PRICE      = "DisplayBagPrice";
        final String DISPLAY_PRICE1     = "UpdateBagPrice";
        final String BAGS_PATH          = "BookingPath";
        final String BAGS_PRICE         = "Standard";

        //Optional Services page constant
        final String PROMO_CODE         = "25PCT";
//STEP-1
        //open browser
        openBrowser( platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        WaitUtil.untilTimeCompleted(2000);
//STEP-2
        JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getHomePage().getOptionalServiceLink());
        WaitUtil.untilPageLoadComplete(getDriver());
//STEP-3
        //Bag-O-Tron Page Methods
        pageMethodManager.getOptionalServicesPageMethods().selectTrip(TRIP_TYPE);
        pageMethodManager.getOptionalServicesPageMethods().selectAirports(DEP_AIRPORT_CODE,ARR_AIRPORT_CODE);
        pageMethodManager.getOptionalServicesPageMethods().selectDates(DEP_DATE,ARR_DATE);
        pageMethodManager.getOptionalServicesPageMethods().selectTravellingPassenger(ADULTS,CHILDS,INFANT_LAP,INFANT_SEAT);
//STEP-4
        pageMethodManager.getOptionalServicesPageMethods().selectBagoTronButton(DISPLAY_PRICE);
        pageMethodManager.getOptionalServicesPageMethods().setBagOTronBagPrices("Dep",BAGS_PATH,BAGS_PRICE);
//STEP-5
        pageObjectManager.getOptionalServicesPage().getBagOTronStartOverButton().click();
        pageMethodManager.getOptionalServicesPageMethods().selectTrip(TRIP_TYPE);
        pageMethodManager.getOptionalServicesPageMethods().selectAirports(DEP_AIRPORT_CODE,ARR_AIRPORT_CODE);
        pageMethodManager.getOptionalServicesPageMethods().selectDates(DEP_DATE,ARR_DATE);
//STEP--6
        //Apply PROMO CODE
        pageObjectManager.getOptionalServicesPage().getBNewTripPromoCodeTextBox().sendKeys(PROMO_CODE);
        pageMethodManager.getOptionalServicesPageMethods().selectBagoTronButton(DISPLAY_PRICE1);
//STEP--7
        pageMethodManager.getOptionalServicesPageMethods().selectBookTrip();
        pageMethodManager.getHomePageMethods().selectOneWayInternationalPopup();

        //Flight Availability Methods
        //Verify User land on Flight Availability Page
        ValidationUtil.validateTestStep("User land on Flight Availiability Page",
                pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityPageHeaderText().isDisplayed());
    }
}