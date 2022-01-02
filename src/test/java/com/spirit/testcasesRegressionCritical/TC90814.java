package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.*;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

//**********************************************************************************************
//Test Case ID: TC90814
//Description: Search Widget_ CP_ Wireframe_Select a Point of Origin to Point of Destination Standalone test case
//Created By : Sunny Sok and Gabriela
//Created On : 05-Aug-2019 / 13-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 20-Aug-2019
//**********************************************************************************************

public class TC90814 extends BaseClass{

    @Parameters({"platform"})
    @Test(groups = {"HomeUI"})
    public void SearchWidget_CP_Wireframe_Select_a_Point_of_Origin_to_Point_of_Destination_Standalone_test_case(@Optional("NA") String platform) {

        /******************************************************************************
         ***********************Navigate to Confirmation Page**************************
         ******************************************************************************/

        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90814 under REGRESSION-CRITICAL Suite on " + platform + " Browser" , true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String NEW_TRIP_TYPE      = "OneWay";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LAS";

        final String BLUE               = "rgba(0, 115, 230, 1)";
        final String DEP_ERROR          = "Please enter a valid origin";

        //open browser
        openBrowser( platform);

        /*********************************** Home Page ***********************************************/
//-- Step 21:Access testing environment (web)
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);

        //--step 1
        //On the home page select a RT flight. Verify the labels are in gray.
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);

        //--step 2
        //box is labeled FROM with a DOWN ARROW and a blue background
        ValidationUtil.validateTestStep("Verify box is labeled FROM with a DOWN ARROW and a blue background",
                pageObjectManager.getHomePage().getFromLabel().getText().equals("FROM")
                        && pageObjectManager.getHomePage().getFromLabel().getCssValue("background-color").equals(BLUE)
                        && TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getFromArrowDownImage()));

        //--step 3
        //Verify that there RT arrows between Origin (FROM) / Destination (TO) field boxes when FLIGHT is OW
        pageMethodManager.getHomePageMethods().selectTripType(NEW_TRIP_TYPE);
        WaitUtil.untilTimeCompleted(1000);
        ValidationUtil.validateTestStep("Verify there's RT arrows between Origin (FROM) / Destination (TO) field boxes",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getRoundTripArrowsImage()));

        //--step 4
        //Select a Point of Origin(From)  to Point of Destination (To)
        //verify A horizontal content block list of ALL LOCATIONS is displayed, with North America, Central America, South America, Caribbean. Right aligned X to CLOSE.
        pageObjectManager.getHomePage().getDepartAirportBox().get(0).click();

        WaitUtil.untilTimeCompleted(1000);

        ValidationUtil.validateTestStep("Verify ALL LOCATIONS is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getAllLocationAirportText()));

        ValidationUtil.validateTestStep("Verify North America is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getNorthAmericaAirportText()));

        ValidationUtil.validateTestStep("Verify Central America is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getCentralAmericaAirportText()));

        ValidationUtil.validateTestStep("Verify South America is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getSouthAmericaAirportText()));

        ValidationUtil.validateTestStep("Verify Caribbean is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getCaribbeanAirportText()));

        ValidationUtil.validateTestStep("Verify Close button is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHomePage().getStationCloseButton()));

        //--step 5: For the default ALL LOCATIONS display there are 4 columns, each column is left aligned
        //Unable to validate this step

        //--step 6: ROWS are blue letters with white background
        //Invalid Step


        //--step 7: Location is listed as City, State/Country (Airport Code)
        List<String> cityName = new ArrayList<>();
        cityName.add(" Aguadilla, Puerto Rico (BQN) ");
        cityName.add("Akron/Canton, OH (CAK)");
        cityName.add(" Armenia, Colombia (AXM) ");
        cityName.add(" Aruba, Aruba (AUA) ");
        cityName.add(" Asheville, NC (AVL) ");
        cityName.add(" Atlanta, GA (ATL) ");
        cityName.add(" Atlantic City, NJ (ACY) ");
        cityName.add(" Austin, TX (AUS) ");
        cityName.add(" Baltimore, MD / Washington, DC AREA (BWI) ");
        cityName.add(" Bogota, Colombia (BOG) ");
        cityName.add(" Boston, MA (BOS) ");
        cityName.add(" Burbank, CA (BUR) ");
        cityName.add(" Cali, Colombia (CLO) ");
        cityName.add(" Cancun, Mexico (CUN) ");
        cityName.add(" Cap Haitien, Haiti (CAP) ");
        cityName.add(" Cartagena, Colombia (CTG) ");
        cityName.add(" Charleston, WV (CRW) ");
        cityName.add(" Charlotte, NC (CLT) ");
        cityName.add(" Chicago, IL - O'Hare (ORD) ");
        cityName.add(" Cincinnati, OH (CVG) ");
        cityName.add(" Cleveland, OH (CLE) ");
        cityName.add(" Columbus, OH (CMH) ");
        cityName.add(" Dallas/Ft. Worth, TX (DFW) ");
        cityName.add(" Denver, CO (DEN) ");
        cityName.add(" Detroit, MI (DTW) ");
        cityName.add(" Fort Lauderdale, FL / Miami, FL AREA (FLL) ");
        cityName.add(" Fort Myers, FL (RSW) ");
        cityName.add(" Greensboro/Winston-Salem, NC (GSO) ");
        cityName.add(" Guatemala City, Guatemala (GUA) ");
        cityName.add(" Guayaquil, Ecuador (GYE) ");
        cityName.add(" Hartford, CT (BDL) ");
        cityName.add(" Houston, TX - Intercontinental (IAH) ");
        cityName.add(" Howes Airport (HON) ");
        cityName.add(" Indianapolis, IN (IND) ");
        cityName.add(" Islip/Long Island, NY (ISP) ");
        cityName.add(" Jacksonville, FL (JAX) ");
        cityName.add(" Kansas City, MO (MCI) ");
        cityName.add(" Kingston, Jamaica (KIN) ");
        cityName.add(" Las Vegas, NV (LAS) ");
        cityName.add(" Latrobe, PA (LBE) ");
        cityName.add(" Lima, Peru (LIM) ");
        cityName.add(" Los Angeles, CA (LAX) ");
        cityName.add(" Los Cabos, Mexico (SJD) ");
        cityName.add(" Managua, Nicaragua (MGA) ");
        cityName.add(" Medellin, Colombia (MDE) ");
        cityName.add(" Memphis, TN (MEM) ");
        cityName.add(" Minneapolis/St. Paul, MN (MSP) ");
        cityName.add(" Montego Bay, Jamaica (MBJ) ");
        cityName.add(" Myrtle Beach, SC (MYR) ");
        cityName.add(" Nashville, TN (BNA) ");
        cityName.add(" New Orleans, LA (MSY) ");
        cityName.add(" New York, NY - LaGuardia (LGA) ");
        cityName.add(" Newark, NJ (EWR) ");
        cityName.add(" Niagara Falls, NY / Toronto AREA (IAG) ");
        cityName.add(" Oakland, CA / San Francisco, CA AREA (OAK) ");
        cityName.add(" Orlando, FL (MCO) ");
        cityName.add(" Panama City, Panama (PTY) ");
        cityName.add(" Philadelphia, PA (PHL) ");
        cityName.add(" Phoenix/Sky Harbor, AZ (PHX) ");
        cityName.add(" Pittsburgh, PA (PIT) ");
        cityName.add(" Plattsburgh, NY / Montreal AREA (PBG) ");
        cityName.add(" Ponce, Puerto Rico (PSE) ");
        cityName.add(" Port-au-Prince, Haiti (PAP) ");
        cityName.add(" Portland, OR (PDX) ");
        cityName.add(" Punta Cana, Dominican Republic (PUJ) ");
        cityName.add(" Raleigh, NC (RDU) ");
        cityName.add(" Richmond, VA (RIC) ");
        cityName.add(" Sacramento, CA (SMF) ");
        cityName.add(" San Diego, CA (SAN) ");
        cityName.add(" San Jose, Costa Rica (SJO) ");
        cityName.add(" San Juan, Puerto Rico (SJU) ");
        cityName.add(" San Pedro Sula, Honduras (SAP) ");
        cityName.add(" San Salvador, El Salvador (SAL) ");
        cityName.add(" Santiago, Dominican Republic (STI) ");
        cityName.add(" Santo Domingo, Dominican Republic (SDQ) ");
        cityName.add(" Seattle-Tacoma, WA (SEA) ");
        cityName.add(" St. Croix, U.S. Virgin Islands (STX) ");
        cityName.add(" St. Maarten, St. Maarten (SXM) ");
        cityName.add(" St. Thomas, U.S. Virgin Islands (STT) ");
        cityName.add(" Tampa, FL (TPA) ");
        cityName.add(" West Palm Beach, FL (PBI) ");

        ArrayList<String> actualCityList = new ArrayList<>();
        boolean cityNameStatus = true;
        boolean cityNameColorStatus = true;
        //loop through all departing airport
        for(WebElement depAirport: pageObjectManager.getHomePage().getAirportListText()){
            //check current departing airport contains airport code
            if(depAirport.isDisplayed()){
                actualCityList.add(depAirport.getText().trim().toUpperCase());
                if(depAirport.getCssValue("color").equals("rgba(52, 85, 219, 1)")){
                    cityNameColorStatus = cityNameColorStatus&&true;
                }else {
                    cityNameColorStatus = cityNameColorStatus&&false;
                }
            }
        }

        for(String city: cityName){
            if(actualCityList.contains(city.trim().toUpperCase())){
                cityNameStatus = cityNameStatus&&true;
            }else {
                cityNameStatus = cityNameStatus&&false;
                ValidationUtil.validateTestStep("Validating "+city+" is exist in the City List ",false);
            }
        }

        ValidationUtil.validateTestStep("Validating Departing location listed under FROM FIELD",cityNameStatus);
        ValidationUtil.validateTestStep("Validating cities color without selection made",cityNameColorStatus);

        //--step 9
        //Remove the Point of Origin(From)/Point of Destination (To),Verify the error shows under the field box.
        TestUtil.clearTextBoxUsingSendKeys(getDriver(),  pageObjectManager.getHomePage().getDepartAirportBox().get(0));
        pageObjectManager.getHomePage().getArrivalAirportBox().get(0).click();
        WaitUtil.untilTimeCompleted(3000);

//        ValidationUtil.validateTestStep("Verifying the error shown under the origin box.",
//                pageObjectManager.getCommon().getErrorMessageLabel().getText(),DEP_ERROR);

        //--step 10 and 11
        //Select North America tab and make sure that web has all destination in the list attached to this step
        pageObjectManager.getHomePage().getDepartAirportBox().get(0).click();
        WaitUtil.untilTimeCompleted(1200);

        pageObjectManager.getHomePage().getNorthAmericaAirportText().get(0).click();
        WaitUtil.untilTimeCompleted(2000);

        //Step 7 and 8 Validation
        List<String> northAmericaCities = new ArrayList<>();
        northAmericaCities.add(" Akron/Canton, OH (CAK) ");
        northAmericaCities.add(" Asheville, NC (AVL) ");
        northAmericaCities.add(" Atlanta, GA (ATL) ");
        northAmericaCities.add(" Atlantic City, NJ (ACY) ");
        northAmericaCities.add(" Austin, TX (AUS) ");
        northAmericaCities.add(" Baltimore, MD / Washington, DC AREA (BWI) ");
        northAmericaCities.add(" Boston, MA (BOS) ");
        northAmericaCities.add(" Burbank, CA (BUR) ");
        northAmericaCities.add(" Cancun, Mexico (CUN) ");
        northAmericaCities.add(" Charleston, WV (CRW) ");
        northAmericaCities.add(" Charlotte, NC (CLT) ");
        northAmericaCities.add(" Chicago, IL - O'Hare (ORD) ");
        northAmericaCities.add(" Cincinnati, OH (CVG) ");
        northAmericaCities.add(" Cleveland, OH (CLE) ");
        northAmericaCities.add(" Columbus, OH (CMH) ");
        northAmericaCities.add(" Dallas/Ft. Worth, TX (DFW) ");
        northAmericaCities.add(" Denver, CO (DEN) ");
        northAmericaCities.add(" Detroit, MI (DTW) ");
        northAmericaCities.add(" Fort Lauderdale, FL / Miami, FL AREA (FLL) ");
        northAmericaCities.add(" Fort Myers, FL (RSW) ");
        northAmericaCities.add(" Greensboro/Winston-Salem, NC (GSO) ");
        northAmericaCities.add(" Hartford, CT (BDL) ");
        northAmericaCities.add(" Houston, TX - Intercontinental (IAH) ");
        northAmericaCities.add(" Howes Airport (HON) ");
        northAmericaCities.add(" Indianapolis, IN (IND) ");
        northAmericaCities.add(" Islip/Long Island, NY (ISP) ");
        northAmericaCities.add(" Jacksonville, FL (JAX) ");
        northAmericaCities.add(" Kansas City, MO (MCI) ");
        northAmericaCities.add(" Las Vegas, NV (LAS) ");
        northAmericaCities.add(" Latrobe, PA (LBE) ");
        northAmericaCities.add(" Los Angeles, CA (LAX) ");
        northAmericaCities.add(" Los Cabos, Mexico (SJD) ");
        northAmericaCities.add(" Memphis, TN (MEM) ");
        northAmericaCities.add(" Minneapolis/St. Paul, MN (MSP) ");
        northAmericaCities.add(" Myrtle Beach, SC (MYR) ");
        northAmericaCities.add(" Nashville, TN (BNA) ");
        northAmericaCities.add(" New Orleans, LA (MSY) ");
        northAmericaCities.add(" New York, NY - LaGuardia (LGA) ");
        northAmericaCities.add(" Newark, NJ (EWR) ");
        northAmericaCities.add(" Niagara Falls, NY / Toronto AREA (IAG) ");
        northAmericaCities.add(" Oakland, CA / San Francisco, CA AREA (OAK) ");
        northAmericaCities.add(" Orlando, FL (MCO) ");
        northAmericaCities.add(" Philadelphia, PA (PHL) ");
        northAmericaCities.add(" Phoenix/Sky Harbor, AZ (PHX) ");
        northAmericaCities.add(" Pittsburgh, PA (PIT) ");
        northAmericaCities.add(" Plattsburgh, NY / Montreal AREA (PBG) ");
        northAmericaCities.add(" Portland, OR (PDX) ");
        northAmericaCities.add(" Raleigh, NC (RDU) ");
        northAmericaCities.add(" Richmond, VA (RIC) ");
        northAmericaCities.add(" Sacramento, CA (SMF) ");
        northAmericaCities.add(" San Diego, CA (SAN) ");
        northAmericaCities.add(" Seattle-Tacoma, WA (SEA) ");
        northAmericaCities.add(" Tampa, FL (TPA) ");
        northAmericaCities.add(" West Palm Beach, FL (PBI) ");

        ArrayList<String> actualNorthAmericaCityList = new ArrayList<>();
        boolean northAmericaCitiesStatus = true;
        boolean northAmericaCitiesColorStatus = true;
        //loop through all departing airport
        for(WebElement depAirport: pageObjectManager.getHomePage().getAirportListText()){
            //check current departing airport contains airport code
            if(depAirport.isDisplayed()){
                actualNorthAmericaCityList.add(depAirport.getText().trim().toUpperCase());
                if(depAirport.getCssValue("color").equals("rgba(52, 85, 219, 1)")){
                    northAmericaCitiesColorStatus = northAmericaCitiesColorStatus&&true;
                }else {
                    northAmericaCitiesColorStatus = northAmericaCitiesColorStatus&&false;
                }
            }
        }

        for(String city: northAmericaCities){
            if(actualNorthAmericaCityList.contains(city.trim().toUpperCase())){
                northAmericaCitiesStatus = northAmericaCitiesStatus&&true;
            }else {
                northAmericaCitiesStatus = northAmericaCitiesStatus&&false;
                ValidationUtil.validateTestStep("Validating "+city+" is exist in the City List ",false);
            }
        }
        //--step 12
        ValidationUtil.validateTestStep("Validating North American cities information",northAmericaCitiesStatus);
        ValidationUtil.validateTestStep("Validating cities color without selection made",northAmericaCitiesColorStatus);


        //Select South America tab and make sure that web has all destination in the list attached to this step
        pageObjectManager.getHomePage().getSouthAmericaAirportText().get(0).click();
        WaitUtil.untilTimeCompleted(2000);


        //--step 13
        List<String> southAmericaCities = new ArrayList<>();
        southAmericaCities.add(" Armenia, Colombia (AXM) ");
        southAmericaCities.add(" Bogota, Colombia (BOG) ");
        southAmericaCities.add(" Cali, Colombia (CLO) ");
        southAmericaCities.add(" Cartagena, Colombia (CTG) ");
        southAmericaCities.add(" Guayaquil, Ecuador (GYE) ");
        southAmericaCities.add(" Lima, Peru (LIM) ");
        southAmericaCities.add(" Medellin, Colombia (MDE) ");

        ArrayList<String> actualSouthAmericaCityList = new ArrayList<>();
        boolean southAmericaCitiesStatus = true;
        boolean southAmericaCitiesColorStatus = true;
        //loop through all departing airport
        for(WebElement depAirport: pageObjectManager.getHomePage().getAirportListText()){
            //check current departing airport contains airport code
            if(depAirport.isDisplayed()){
                actualSouthAmericaCityList.add(depAirport.getText().trim().toUpperCase());
                if(depAirport.getCssValue("color").equals("rgba(52, 85, 219, 1)")){
                    southAmericaCitiesColorStatus = southAmericaCitiesColorStatus&&true;
                }else {
                    southAmericaCitiesColorStatus = southAmericaCitiesColorStatus&&false;
                }
            }
        }

        for(String city: southAmericaCities){
            if(actualSouthAmericaCityList.contains(city.trim().toUpperCase())){
                southAmericaCitiesStatus = southAmericaCitiesStatus&&true;
            }else {
                southAmericaCitiesStatus = southAmericaCitiesStatus&&false;
                ValidationUtil.validateTestStep("Validating "+city+" is exist in the City List ",false);
            }
        }

        ValidationUtil.validateTestStep("Validating North American cities information",southAmericaCitiesStatus);
        ValidationUtil.validateTestStep("Validating cities color without selection made",southAmericaCitiesColorStatus);


        //--step 14 :Select Central America tab and make sure that web has all destination in the list
        pageObjectManager.getHomePage().getCentralAmericaAirportText().get(0).click();
        WaitUtil.untilTimeCompleted(2000);

        List<String> centralAmericaCities = new ArrayList<>();
        centralAmericaCities.add(" Guatemala City, Guatemala (GUA) ");
        centralAmericaCities.add(" Managua, Nicaragua (MGA) ");
        centralAmericaCities.add(" Panama City, Panama (PTY) ");
        centralAmericaCities.add(" San Jose, Costa Rica (SJO) ");
        centralAmericaCities.add(" San Pedro Sula, Honduras (SAP) ");
        centralAmericaCities.add(" San Salvador, El Salvador (SAL) ");

        //--step 15: ROWS are blue letters with white background
        //Location is listed as City, State/Country (Airport Code).
        ArrayList<String> actualCentralAmericaCityList = new ArrayList<>();
        boolean centralAmericaCitiesStatus = true;
        boolean centralAmericaCitiesColorStatus = true;
        //loop through all departing airport
        for(WebElement depAirport: pageObjectManager.getHomePage().getAirportListText()){
            //check current departing airport contains airport code
            if(depAirport.isDisplayed()){
                actualCentralAmericaCityList.add(depAirport.getText().trim().toUpperCase());
                if(depAirport.getCssValue("color").equals("rgba(52, 85, 219, 1)")){
                    centralAmericaCitiesColorStatus = centralAmericaCitiesColorStatus&&true;
                }else {
                    centralAmericaCitiesColorStatus = centralAmericaCitiesColorStatus&&false;
                }
            }
        }

        for(String city: centralAmericaCities){
            if(actualCentralAmericaCityList.contains(city.trim().toUpperCase())){
                centralAmericaCitiesStatus = centralAmericaCitiesStatus&&true;
            }else {
                System.out.println(actualCentralAmericaCityList);
                System.out.println("|"+city.trim().toUpperCase()+"|");

                centralAmericaCitiesStatus = centralAmericaCitiesStatus&&false;
                ValidationUtil.validateTestStep("Validating "+city+" is exist in the City List ",false);
            }
        }

        ValidationUtil.validateTestStep("Validating North American cities information",centralAmericaCitiesStatus);
        ValidationUtil.validateTestStep("Validating cities color without selection made",centralAmericaCitiesColorStatus);

//-- Step 16: Select Caribbean tab and make sure that web has all destination in the list attached to this step
        pageObjectManager.getHomePage().getCaribbeanAirportText().get(0).click();
        WaitUtil.untilTimeCompleted(2000);

        List<String> caribbeanCities = new ArrayList<>();
        caribbeanCities.add(" Aguadilla, Puerto Rico (BQN) ");
        caribbeanCities.add(" Aruba, Aruba (AUA) ");
        caribbeanCities.add(" Cap Haitien, Haiti (CAP) ");
        caribbeanCities.add(" Kingston, Jamaica (KIN) ");
        caribbeanCities.add(" Montego Bay, Jamaica (MBJ) ");
        caribbeanCities.add(" Ponce, Puerto Rico (PSE) ");
        caribbeanCities.add(" Port-au-Prince, Haiti (PAP) ");
        caribbeanCities.add(" Punta Cana, Dominican Republic (PUJ) ");
        caribbeanCities.add(" San Juan, Puerto Rico (SJU) ");
        caribbeanCities.add(" Santiago, Dominican Republic (STI) ");
        caribbeanCities.add(" Santo Domingo, Dominican Republic (SDQ) ");
        caribbeanCities.add(" St. Croix, U.S. Virgin Islands (STX) ");
        caribbeanCities.add(" St. Maarten, St. Maarten (SXM) ");
        caribbeanCities.add(" St. Thomas, U.S. Virgin Islands (STT) ");

//-- Step 17: Redo Steps 7 and 8 in the Caribbean tab

        ArrayList<String> actualCaribbeanCityList = new ArrayList<>();
        boolean caribbeanCitiesStatus = true;
        boolean caribbeanCitiesColorStatus = true;
        //loop through all departing airport
        for(WebElement depAirport: pageObjectManager.getHomePage().getAirportListText()){
            //check current departing airport contains airport code
            if(depAirport.isDisplayed()){
                actualCaribbeanCityList.add(depAirport.getText().trim().toUpperCase());
                if(depAirport.getCssValue("color").equals("rgba(52, 85, 219, 1)")){
                    caribbeanCitiesColorStatus = caribbeanCitiesColorStatus&&true;
                }else {
                    caribbeanCitiesColorStatus = caribbeanCitiesColorStatus&&false;
                }
            }
        }

        for(String city: caribbeanCities){
            if(actualCaribbeanCityList.contains(city.trim().toUpperCase())){
                caribbeanCitiesStatus = caribbeanCitiesStatus&&true;
            }else {
                caribbeanCitiesStatus = caribbeanCitiesStatus&&false;
                ValidationUtil.validateTestStep("Validating "+city+" is exist in the City List ",false);
            }
        }

        ValidationUtil.validateTestStep("Validating North American cities information",caribbeanCitiesStatus);
        ValidationUtil.validateTestStep("Validating cities color without selection made",caribbeanCitiesColorStatus);


//-- Step 18: On the From box input an invalid city name
        pageObjectManager.getHomePage().getDepartAirportBox().get(0).sendKeys("greece");

        pageObjectManager.getHomePage().getArrivalAirportBox().get(0).click();

        ValidationUtil.validateTestStep("Verifying the error shown under the origin box.",
                pageObjectManager.getCommon().getErrorMessageLabel().getText(),DEP_ERROR);

//-- Step 19: On the from box input 2 letters for a valid city name
        pageObjectManager.getHomePage().getDepartAirportBox().get(0).clear();
        WaitUtil.untilTimeCompleted(1200);

        pageObjectManager.getHomePage().getDepartAirportBox().get(0).sendKeys("TP");

        ValidationUtil.validateTestStep("Validating right information is displayed after input Cities Initials",
                pageObjectManager.getHomePage().getAirportListText().get(0).getText(),"Tampa");

//-- Step 20: Select location on the search widget
        pageObjectManager.getHomePage().getDepartAirportBox().get(0).clear();
        WaitUtil.untilTimeCompleted(1200);

        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
    }
}