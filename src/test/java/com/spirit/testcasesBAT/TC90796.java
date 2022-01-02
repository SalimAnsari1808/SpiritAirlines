package com.spirit.testcasesBAT;
import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import com.spirit.utility.ValidationUtil;
//@Listeners(com.spirit.testNG.Listener.class)

//**********************************************************************************************
//Test Case ID: TC90796
//Description: Search Widget_CP_BP_OW_ Testing the Passengers section on the home page
//Created By : Sunny Sok
//Created On : 8-Mar-2019
//Reviewed By: Salim Ansari
//Reviewed On: 14-Mar-2019
//**********************************************************************************************

public class TC90796 extends BaseClass{

    @Parameters ({"platform"})
    @Test(groups = {"BookPath","OneWay","DomesticDomestic","Outside21Days","Guest","HomeUI"})
    public void Search_Widget_CP_BP_OW_Testing_the_Passengers_section_on_the_home_page (@Optional("NA")String platform) {
		/******************************************************************************
		 *******************************Navigate to Home Page**************************
		 ******************************************************************************/
    	//Mention Suite and Browser in reports 
    	if(!platform.equals("NA")) {
    		ValidationUtil.validateTestStep("Starting Test Case ID TC90796 under BAT Suite on " + platform + " Browser"   , true);
    	}

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "OneWay";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LGA";
        final String DEP_DATE 			= "25";
        final String ARR_DATE 			= "NA";

        //open browser
        openBrowser( platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);

		/******************************************************************************
		 *******************************Validation on Home Page**************************
		 ******************************************************************************/
        //wait till page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //clicking on Passenger input box
        pageObjectManager.getHomePage().getPassengerBox().click();

        WaitUtil.untilPageLoadComplete(getDriver());

        //Increasing number of adult passengers to Nine
        for (int adultCounter = 1; adultCounter < 9; adultCounter++) {
        	//click on adult plus button
            pageObjectManager.getHomePage().getAdultPlusLink().click();
        }
        
        //verify plus symbol for adult till you max it out.
        ValidationUtil.validateTestStep("verify adult count is reached to maximum value of 9 on Home Page", JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getHomePage().getAdultBox()).contains("9"));
        
    	//click on adult plus button
        pageObjectManager.getHomePage().getAdultPlusLink().click();
       
        //adult count cannot be more than 9
        ValidationUtil.validateTestStep("verify adult count cannot be more than 9 on Home Page", JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getHomePage().getAdultBox()).contains("9"));
        
        //adult plus sign is disabled
        ValidationUtil.validateTestStep("Verify adult plus sign is disabled after selecting maximum adult on Home Page", pageObjectManager.getHomePage().getAdultPlusLink().getAttribute("class").contains("disabled"));
        
        //child plus sign is disabled
        ValidationUtil.validateTestStep("Verify child plus sign is disabled after selecting maximum adult on Home Page", pageObjectManager.getHomePage().getChildPlusLink().getAttribute("class").contains("disabled"));

         //Decreasing adult passengers to zero
        for (int adultCounter = 9; adultCounter > 0; adultCounter--) {
        	//click on adult minus sign
        	pageObjectManager.getHomePage().getAdultMinusLink().click();
        }
        
        // Increasing child passengers to nine
         for (int childCounter = 0; childCounter < 9; childCounter++) {
        	 //click on child plus sign
        	 pageObjectManager.getHomePage().getChildPlusLink().click();
         }
         
         //verify plus symbol for child till you max it out.
         ValidationUtil.validateTestStep("verify child count is reached to maximum value of 9 on Home Page", JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getHomePage().getChildBox()).contains("9"));
         
     	//click on adult plus button
         pageObjectManager.getHomePage().getAdultPlusLink().click();
        
         //adult count cannot be more than 9
         ValidationUtil.validateTestStep("verify child count cannot be more than 9 on Home Page", JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getHomePage().getChildBox()).contains("9"));

         //adult plus sign is disabled
         ValidationUtil.validateTestStep("Verify adult plus sign is disabled after selecting maximum child on Home Page", pageObjectManager.getHomePage().getAdultPlusLink().getAttribute("class").contains("disabled"));
         
         //child plus sign is disabled
         ValidationUtil.validateTestStep("Verify child plus sign is disabled after selecting maximum child on Home Page", pageObjectManager.getHomePage().getChildPlusLink().getAttribute("class").contains("disabled"));

        //Decreasing child passengers to zero
        for (int childCounter = 9; childCounter > 0; childCounter--) {
        	//click on child minus sign
        	pageObjectManager.getHomePage().getChildMinusLink().click();
        }
        
        //adult count is become 0
        ValidationUtil.validateTestStep("verify adult count is reduced to 0 on Home Page", JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getHomePage().getAdultBox()).contains("0"));
        
        //child count is become 0
        ValidationUtil.validateTestStep("verify child count is reduced to 0 on Home Page", JSExecuteUtil.getElementTextValue(getDriver(), pageObjectManager.getHomePage().getChildBox()).contains("0"));

        //click on search flight button
        pageMethodManager.getHomePageMethods().clickSearchButton();
 
        //wait till page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());
        
        //Validates Passengers Required Popup Displays
        ValidationUtil.validateTestStep("Passengers Required Pop is displayed on Home Page",
                TestUtil.verifyElementDisplayed(getDriver(), By.xpath(pageObjectManager.getHomePage().xpath_PassengersRequiredHeaderText)));

        //Click on Close Window button
        pageObjectManager.getHomePage().getPassengersRequiredCloseText().click();
    }
}
