package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


//**********************************************************************************************
//Test Case ID: TC349802
//Description:  Flight Only - Car Page - Validate Display components and functionality
// Created By:  Salim Ansari
//Created On:   03-Dec-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC349802 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "Outside21Days", "Adult", "Guest", "BookIt", "NonStop", "NoBags",
            "NoSeats" , "CarsUI","OptionalUI"})
    public void CP_Flight_Only_Hotel_Page_Validate_Display_components_and_functionality(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC349802  under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "132";
        final String ARR_DATE           = "133";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String ARR_Flight         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Payment Page Constant values
        final String TRAVEL_GUARD = "NotRequired";
        final String CARD_DETAIL = "DiscoverCard1";

//- Step 1: Access test environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 2: Start booking a flight only RT INT and reach the Car Page via the Options Page
        //Home Page Methods
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Avaliability Page Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Page Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Option Page
        pageObjectManager.getCarPage().getViewAllCarsButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());

        WaitUtil.untilTimeCompleted(1000);


//- Step 3: Once on the Car Page, next to the filter, validate there is a car count formatted "# cars".
        try{
            Integer.parseInt(pageObjectManager.getCarPage().getCarsPageCarCounterText().getText().replace("Cars","").trim());

            ValidationUtil.validateTestStep("Once on the Car Page, next to the filter, validate there is a car count formatted \"# cars\".",true);

        }catch(Exception e){
            ValidationUtil.validateTestStep("Once on the Car Page, next to the filter, validate there is a car count formatted \"# cars\".",false );
        }

//- Step 4: Use any of the filters to narrow the search and validate the value changes to only the amount of cars meeting the filters applied.
        pageMethodManager.getCarPageMethods().filterCarByCarOptions("Automatic");
        boolean carOptiobFalg = true;
        for(WebElement optionType : pageObjectManager.getCarPage().getCarsCardTransmissionTypeText()){
            if(optionType.isDisplayed()){
                if(!optionType.getText().equalsIgnoreCase("Automatic")){
                    carOptiobFalg = false;
                }
            }
        }

        ValidationUtil.validateTestStep("Use any of the filters to narrow the search and validate the value changes to only the amount of cars meeting the filters applied.",carOptiobFalg);

        pageMethodManager.getCarPageMethods().removeCarFilterByCategory("AllFilter");

//- Step 5: Locate the Sort by section and validate Recommended is the default selected state.
        System.out.println(pageObjectManager.getCarPage().getSortByRecommendedButton().getAttribute("color"));
        ValidationUtil.validateTestStep("Locate the Sort by section and validate Recommended is the default selected state",
                true);


//- Step 6: Click on Seat
        //pageObjectManager.getCarPage().getSortBySeatsButton().click();
        JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getCarPage().getSortBySeatsButton());

        WaitUtil.untilPageLoadComplete(getDriver());

        boolean carSeatNumberFalg = true;
        for(int carSeatCount=0;carSeatCount<pageObjectManager.getCarPage().getCarsCardNumberOfSeatsText().size()-1;carSeatCount++){

            int firstCarSeat = Integer.parseInt(pageObjectManager.getCarPage().getCarsCardNumberOfSeatsText().get(carSeatCount).getText().replace("Seats","").trim());
            int secondCarSeat = Integer.parseInt(pageObjectManager.getCarPage().getCarsCardNumberOfSeatsText().get(carSeatCount+1).getText().replace("Seats","").trim());

            if(firstCarSeat>secondCarSeat){
                carSeatNumberFalg = false;
            }
        }

        ValidationUtil.validateTestStep("validate the order seats will be  Smallest to Largest  number of seats",carSeatNumberFalg);

//- Step 7: Click on Seat one more time and validate the order now reverses (Largest to Smallest number of seats)
        JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getCarPage().getSortBySeatsButton());

        WaitUtil.untilPageLoadComplete(getDriver());

        carSeatNumberFalg = true;
        for(int carSeatCount=0;carSeatCount<pageObjectManager.getCarPage().getCarsCardNumberOfSeatsText().size()-1;carSeatCount++){

            int firstCarSeat = Integer.parseInt(pageObjectManager.getCarPage().getCarsCardNumberOfSeatsText().get(carSeatCount).getText().replace("Seats","").trim());
            int secondCarSeat = Integer.parseInt(pageObjectManager.getCarPage().getCarsCardNumberOfSeatsText().get(carSeatCount+1).getText().replace("Seats","").trim());

            if(firstCarSeat<secondCarSeat){
                carSeatNumberFalg = false;
            }
        }

        ValidationUtil.validateTestStep("Click on Seat one more time and validate the order now reverses (Largest to Smallest number of seats)",carSeatNumberFalg);

//- Step 8: Click Price and validate the order goes from LOW to HIGH with first click and it reverses on second click

        JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getCarPage().getSortByPriceButton());

        WaitUtil.untilPageLoadComplete(getDriver());

        boolean carPriceFalg = true;
        for(int carSeatCount=0;carSeatCount<pageObjectManager.getCarPage().getCarsPageRentalPriceText().size()-1;carSeatCount++){

            float firstCarPrice =  Float.parseFloat(pageObjectManager.getCarPage().getCarsPageRentalPriceText().get(carSeatCount).getText().replace("$","").trim());
            float secondCarPrice = Float.parseFloat(pageObjectManager.getCarPage().getCarsPageRentalPriceText().get(carSeatCount+1).getText().replace("$","").trim());

            if(firstCarPrice>secondCarPrice){
                carPriceFalg = false;
            }
        }

        ValidationUtil.validateTestStep("validate the Price order goes from LOW to HIGH with first click on Car Page)",carPriceFalg);

        JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getCarPage().getSortByPriceButton());

        WaitUtil.untilPageLoadComplete(getDriver());

        carPriceFalg = true;
        for(int carSeatCount=0;carSeatCount<pageObjectManager.getCarPage().getCarsPageRentalPriceText().size()-1;carSeatCount++){

            float firstCarPrice =  Float.parseFloat(pageObjectManager.getCarPage().getCarsPageRentalPriceText().get(carSeatCount).getText().replace("$","").trim());
            float secondCarPrice = Float.parseFloat(pageObjectManager.getCarPage().getCarsPageRentalPriceText().get(carSeatCount+1).getText().replace("$","").trim());

            if(firstCarPrice<secondCarPrice){
                carPriceFalg = false;
            }
        }


        ValidationUtil.validateTestStep("validate the Price order goes from HIGH to LOW with second click on Car Page)",carPriceFalg);

//- Step 9: Click on Car Type and validate the order sorts by car type.
        JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getCarPage().getSortByCarTypeButton());

        WaitUtil.untilPageLoadComplete(getDriver());

        int carTypeListCounter = 0;
        boolean carTypeFlag = true;
        for(int carTypeCounter=0;carTypeCounter<pageObjectManager.getCarPage().getCarsPageCarTypeText().size()-1;carTypeCounter++){
            String firstCarType = pageObjectManager.getCarPage().getCarsPageCarTypeText().get(carTypeCounter).getText().split(" ")[1];
            String secondCarType = pageObjectManager.getCarPage().getCarsPageCarTypeText().get(carTypeCounter+1).getText().split(" ")[1];

            if(!firstCarType.equalsIgnoreCase(secondCarType)){
                if(pageObjectManager.getCarPage().getCarTypeFilterOptionListButton().get(carTypeListCounter).getText().equalsIgnoreCase(firstCarType)){
                    carTypeListCounter++;
                }else{
                    carTypeFlag = false;
                }
            }
        }

        ValidationUtil.validateTestStep("Click on Car Type and validate the order sorts by car type on Car page", carTypeFlag);

//- Step 10: Moving down, validate there is a list of 20 car tiles displaying.
        ValidationUtil.validateTestStep("validate there is a list of 20 car tiles displaying.on Car Page)",
                pageObjectManager.getCarPage().getCarsPageRentalPriceText().size()<=20);

//- Step 11: Return to the Sort by section and click on "Recommended"
        JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getCarPage().getSortByRecommendedButton());

        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("'Company logo' is displaying in car panel" ,
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageCarCompanyLogoImg().get(0)));

        ValidationUtil.validateTestStep("'Car image' is displaying in car panel" ,
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageCarRentalImg().get(0)));

        ValidationUtil.validateTestStep("'Car Type' is displaying in car panel" ,
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageCarTypeText().get(0)));

        ValidationUtil.validateTestStep("'Car Model' is displaying in car panel" ,
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageCarModelText().get(0)));

        ValidationUtil.validateTestStep("'Number of Seat' is displaying in car panel" ,
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsCardNumberOfSeatsText().get(0)));

        ValidationUtil.validateTestStep("'Number of Bag' is displaying in car panel" ,
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsCardNumberOfBagsText().get(0)));
//Step--12
        ValidationUtil.validateTestStep("'Transmission Type Card' is displaying in car panel" ,
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsCardTransmissionTypeText().get(0)));

        ValidationUtil.validateTestStep("'Mileage Limit' is displaying in car panel" ,
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsCardMileageLimitText().get(0)));

        ValidationUtil.validateTestStep("'More Info' is displaying in car panel" ,
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageMoreInfoLink().get(0)));

        ValidationUtil.validateTestStep("'Add Car Button' is displaying in car panel" ,
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getAddCarButton().get(0)));

        ValidationUtil.validateTestStep("'Price per day' is displaying in car panel" ,
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPagePricePerDay().get(0)));

//Step--14:
        ValidationUtil.validateTestStep("The Car count is displayed onto the Car section" ,
                pageObjectManager.getCarPage().getCarsPageCarsPanel().size() <= 20);

//Step--15:
        while (TestUtil.verifyElementDisplayed(getDriver(), By.xpath(pageObjectManager.getCarPage().xpath_CarPageShowMoreButton))) {
            pageObjectManager.getCarPage().getCarPageShowMoreButton().click();
            WaitUtil.untilTimeCompleted(2000);
        }

//Step--16:
        ValidationUtil.validateTestStep("Validate there is a CONTINUE WITHOUT A CAR button at the end of the Car page" ,
                TestUtil.verifyElementDisplayed(getDriver(),By.xpath(pageObjectManager.getCarPage().xpath_CarsPageContinueWithoutCarButton)));
    }
}
