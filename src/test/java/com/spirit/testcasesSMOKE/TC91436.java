package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC91436
//Title       : CP_BP_Payment Page_Travel Guard_PR Resident
//Description : Validate Travel Guard is not displayed for Puerto Rican User
//Created By  : Anthony Cardona
//Created On  : 08-Apr-2019
//Reviewed By : Salim Ansari
//Reviewed On : 23-Apr-2019
//**********************************************************************************************
public class TC91436 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "Outside21Days" , "Adult" , "Guest" , "NonStop" , "BookIt" ,
            "ContactInformation" , "NoBags" , "NoSeats" , "PaymentUI","CheckInOptions"})
    public void CP_BP_Payment_Page_Travel_Guard_PR_Resident(@Optional("NA") String platform) {
        /******************************************************************************
         *******************************Navigate to Payment Page***********************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91436 under SMOKE Suite on " + platform + " Browser", true);
        }

//Step 1 and 2
        //Navigate to the payment page as a Puerto Rican Resident (Change Contact information to be from Puerto Rico)

        final String LANGUAGE                   = "English";
        final String JOURNEY_TYPE               = "Flight";
        final String TRIP_TYPE                  = "Oneway";
        final String DEP_AIRPORTS               = "AllLocation";
        final String DEP_AIRPORT_CODE           = "FLL";
        final String ARR_AIRPORTS               = "AllLocation";
        final String ARR_AIRPORT_CODE           = "LGA";
        final String DEP_DATE                   = "25";
        final String ARR_DATE                   = "NA";
        final String ADULTS                     = "1";
        final String CHILDREN                   = "0";
        final String INFANT_LAP                 = "0";
        final String INFANT_SEAT                = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		        = "nonstop";
        final String FARE_TYPE                  = "Standard";
        final String UPGRADE_VALUE              = "BookIt";
        final String JOURNEY                    = "Dep";

        //Passenger Information Page
        final String NON_US_RESIDENT_COUNTRY    = "Puerto Rico, Commonwealth of";

        //Options page constant Values
        final String CHECKIN_OPTION             = "CheckInOption:MobileFree";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType(JOURNEY, DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactNonUSResident();
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPassengerInfoPage().getContactPersonCountryDropDown(),NON_US_RESIDENT_COUNTRY);
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Pagec
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
        WaitUtil.untilPageLoadComplete(getDriver());

        //Seats Page methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options page methods
        pageMethodManager.getOptionsPageMethods().selectOptions(CHECKIN_OPTION);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//Step 3
        /******************************************************************************
         *****************************Validation on Payment Page***********************
         ******************************************************************************/
        //validation that the travel Guard is not displayed
        ValidationUtil.validateTestStep( "Travel Guard is not Offered to Non-US resident: pass" , !TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getNoTravelGuardLabel()));


    }
}