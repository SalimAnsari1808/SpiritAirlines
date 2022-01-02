
package com.spirit.testcasesProdBAT;

import com.spirit.baseClass.*;
import com.spirit.dataType.*;
import com.spirit.managers.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC90913
//Description: Customer Info_CP_BP_ Enroll as a FS member
//Created By : Sunny Sok
//Created On : 12-Mar-2019
//Reviewed By: Salim Ansari
//Reviewed On: 16-Mar-2019
//**********************************************************************************************
public class PRODTC90913 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups="Production")

    public void Customer_Info_CP_BP_Enroll_as_a_FS_member(@Optional("NA") String platform) {
        /******************************************************************************
         ***********************Validation on Customer Information Page****************
         ******************************************************************************/
    	//Mention Suite and Browser in reports 
    	if(!platform.equals("NA")) {
    		ValidationUtil.validateTestStep("Starting Test Case ID PRODTC90913 under PRODUCTION Suite on " + platform + " Browser"   , true);
    	}
    	
        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "RoundTrip";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "BWI";
        final String DEP_DATE 			= "25";
        final String ARR_DATE 			= "30";
        final String ADULTS 			= "1";
        final String CHILDS 			= "0";
        final String INFANT_LAP 		= "0";
        final String INFANT_SEAT 		= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		= "Connecting";
        final String ARR_Flight 		= "Connecting";
        final String FARE_TYPE 			= "Standard";
        final String UPGRADEVALUE 		= "BookIt";

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
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADEVALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

        /******************************************************************************
         ***********************Validation on Customer Information Page****************
         ******************************************************************************/
        //declare constant used in Validation
        final String BAGS_URL = "book/bags";
        
        //click on primary passenger checkbox
        pageObjectManager.getPassengerInfoPage().getPrimaryPassengerIstheContactPersonCheckBox().click();
        
        //get password value from json file
        MemberEnrollmentData memberEnrollmentData = FileReaderManager.getInstance().getJsonReader().getMemberEnrollmentDetailsByTab("FSAccountTab");

        //Clicking Yes u want to become a free spirit member
        pageObjectManager.getPassengerInfoPage().getYesIwantToJoinFSCheckBox().click();

        //Send password
        pageObjectManager.getPassengerInfoPage().getFSEnrollmentPasswordTextBox().sendKeys(memberEnrollmentData.createPassword);
        
        //send confirm password
        pageObjectManager.getPassengerInfoPage().getFSEnrollmentConfirmPasswordTextBox().sendKeys(memberEnrollmentData.confirmPassowrd);

        //Clicking on Continue button
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //wait until page bags is appear on web
        WaitUtil.untilPageLoadComplete(getDriver());
        
        //skip test if overlay error message occur 
        //ValidationUtil.skipTestCase("Unable to SignUp FS Login Account on Passenger Info Page", pageObjectManager.getPassengerInfoPage().getOverlayErrorMessageText().size()>0);

        //wait till url appear on web
        WaitUtil.untilPageURLVisible(getDriver(), BAGS_URL);

        //wait until page bags is appear on web
        WaitUtil.untilPageLoadComplete(getDriver());
        
        //validate user reached to the Bags page
        ValidationUtil.validateTestStep("User after Signup to FS member on Passenger Info proceeds to Bags Page", getDriver().getCurrentUrl(),BAGS_URL);
    }
}