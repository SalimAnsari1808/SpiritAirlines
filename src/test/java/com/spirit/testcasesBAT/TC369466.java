
package com.spirit.testcasesBAT;

import com.spirit.baseClass.*;
import com.spirit.dataType.MemberEnrollmentData;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC369466
//Description: Customer Info_CP_BP_Select a 9DFC flight and get the sign up model and get 9DFC flight
//Created By : Sunny Sok
//Created On : 12-Mar-2019
//Reviewed By: Salim Ansari
//Reviewed On: 14-Mar-2019
//**********************************************************************************************
public class TC369466 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"Guest","BookPath","RoundTrip","DomesticDomestic","Outside21Days","Adult","BookIt","PassengerinfoSignUp","NineDFC"})
    public void Customer_Info_CP_BP_Select_a_9DFC_flight_and_get_the_sign_up_model_and_get_9DFC_flight(@Optional("NA") String platform) {
    	
		//**********************Navigate to Customer Information Page******************
    	//Mention Suite and Browser in reports 
    	if(!platform.equals("NA")) {
    		ValidationUtil.validateTestStep("Starting Test Case ID TC369466 under BAT Suite on " + platform + " Browser"   , true);
    	}
    	
        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "RoundTrip";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "DFW";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "ATL";
        final String DEP_DATE 			= "1";
        final String ARR_DATE 			= "5";
        final String ADULTS 			= "1";
        final String CHILDS 			= "0";
        final String INFANT_LAP 		= "0";
        final String INFANT_SEAT 		= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		= "9DFC";
        final String ARR_Flight 		= "9DFC";
        final String FARE_TYPE 			= "Member";
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
        pageMethodManager.getFlightAvailabilityMethods().selectFlightFareType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightFareType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADEVALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        
        //click on primary passenger checkbox
        pageObjectManager.getPassengerInfoPage().getPrimaryPassengerIstheContactPersonCheckBox().click();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
        
    	/******************************************************************************
		 ***********************Validation on Customer Information Page****************
		 ******************************************************************************/
        //declare constant used in Validation
        final String BAGS_URL = "book/bags";
        
        //get password value from json file
        MemberEnrollmentData memberEnrollmentData = FileReaderManager.getInstance().getJsonReader().getMemberEnrollmentDetailsByTab("NineFCAccountTab");
		
		//Clicking on Sign up button
		pageObjectManager.getPassengerInfoPage().get9FCUpsellSignUpButton().click();
		
		//Sending Password
		pageObjectManager.getPassengerInfoPage().get9FCUpselSignUpChoosePasswordTextBox().sendKeys(memberEnrollmentData.createPassword);
		
		//Confirming Password
		pageObjectManager.getPassengerInfoPage().get9FCUpsellSignUpConfirmPasswordTextBox().sendKeys(memberEnrollmentData.confirmPassowrd);
		
		//Clicking on sign up with email
		pageObjectManager.getPassengerInfoPage().get9FCUpsellSignUpWithEmailButton().click();
		
		//wait until page bags is appear on web
		WaitUtil.untilPageLoadComplete(getDriver());
		
        //skip test if overlay error message occur 
        //ValidationUtil.skipTestCase("Unable to SignUp 9DFC Login Account on Passenger Info Page", pageObjectManager.getPassengerInfoPage().getOverlayErrorMessageText().size()>0);

		//wait till url appear on web
		WaitUtil.untilPageURLVisible(getDriver(), BAGS_URL);
		
		//wait until page bags is appear on web
		WaitUtil.untilPageLoadComplete(getDriver());

		//validate user reached to the Bags page
        ValidationUtil.validateTestStep("User after Signup with 9DFC member on Passenger Info proceeds to Bags Page", getDriver().getCurrentUrl(),BAGS_URL);
    }
}