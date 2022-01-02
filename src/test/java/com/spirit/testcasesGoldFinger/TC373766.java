package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

//**********************************************************************************************
//Test Case ID: TC373766
//Description: CP - Hertz Logo Update - Check-In Upsell - Validate the Facade of the new Hertz logo on Check-In Upsell path
//Created By : Anthony Cardona
//Created On : 18-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC373766 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"CheckIn","RoundTrip","DomesticDomestic","WithIn7Days","Adult","Guest","BookIt", "NoBags", "NoSeats",
            "NonStop", "MasterCard"})
    public void Validate_the_Facade_of_the_new_Hertz_logo_on_Check_In_Upsell_path(@Optional("NA") String platform) {
        /******************************************************************************
         *******************************Navigate to Option Page************************
         ******************************************************************************/

        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373766  under GoldFinger Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "RoundTrip";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LAS";
        final String ARR_CITY           = "Las Vegas, NV";
        final String DEP_DATE 			= "0";
        final String ARR_DATE 			= "4";
        final String ADULTS 			= "1";
        final String CHILD   			= "0";
        final String INFANT_LAP 		= "0";
        final String INFANT_SEAT 		= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 	    = "NonStop";
        final String ARR_FLIGHT 		= "NonStop";
        final String FARE_TYPE 	    	= "Standard";
        final String UPGRADE_VALUE 	    = "BookIt";

        //Option Page Constant value
        final String OPTION_VALUE       = "CheckInOption:MobileFree";
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //Confirmation Page Constant value
        final String BOOKING_STATUS 	= "Confirmed";

//STEP--1 Land on current test environment.
        //open browser
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();

//STEP--2 Complete booking
        //Home Page Methods
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats page methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Option Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment page
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //Confirmation page closing ROKT Popup
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //Verifying booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);

        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //Steps 4- 12
        completeSteps4Through12();

        /******************************************************************************
         **************************Validation On Rental Car Popup**********************
         ******************************************************************************/
//STEP 13 validate the last chance Car-PopUp header
        //if the element is displayed then validate the header (This "if" statement will avoid NoSuchElement exception if the pop-up is not displayed)
        if(TestUtil.verifyElementDisplayed(getDriver(), By.xpath(pageObjectManager.getCarPage().xpath_LastChanceHeaderText)))
        {
            //Constant Color variables
            final String BACKGROUND_YELLOW          = "255, 236, 0";
            final String FONT_BLACK                 = "0, 0, 0";

            //Colors from the Header
            final String BackGroundColor = getDriver().findElement(By.xpath(pageObjectManager.getCarPage().xpath_LastChanceHeaderText + "/..")).getCssValue("background-color");
            final String TextColor =  pageObjectManager.getCarPage().getLastChanceHeaderText().getCssValue("color");

            ValidationUtil.validateTestStep("The car UpSell \"Last Chance\" text is correct",  pageObjectManager.getCarPage().getLastChanceHeaderText().getText() , "Last Chance!");

            ValidationUtil.validateTestStep("The car UpSell \"Last Chance\" header has a yellow background",
                    BackGroundColor.contains(BACKGROUND_YELLOW));

            ValidationUtil.validateTestStep("The car UpSell \"Last Chance\" Header Font is black",
                    TextColor.contains( FONT_BLACK));
        }
        else
        {
            ValidationUtil.validateTestStep("The car UpSell \"Last Chance\" is not displayed" , false);
        }

//STEP 14 validate the Car-PopUp sub-header and view all cars button
        //get the price of the cheapest car
        String cheapestCarPrice = pageObjectManager.getCarPage().getLastChanceCarPriceText().getText();

        //validate Verbiage is correct. The price is the same as the cheapest car tile under neath the verbiage. Right aligned is the "VIEW ALL CARS" button
        ValidationUtil.validateTestStep("The Sub-Header text is correct",
                pageObjectManager.getCarPage().getLastChanceReserveACarText().getText() , " Reserve a car in " + ARR_CITY + " from only " + cheapestCarPrice + " per day. ");
        //Validate the View all button is displayed
        ValidationUtil.validateTestStep("The \"View All\" button is displayed on the pop-up" ,
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getLastChanceViewAllCarsButton()));

//STEP 15 validate  "Or Select a Quick Pick" text is displayed in the pop-up
        ValidationUtil.validateTestStep("The \"Or Select a Quick Pick\" text is displayed" ,
                "Or Select a Quick Pick" , getLastChanceOrSelectAQuickPick().getText());


//STEP 16 validate Validate up to 3 car option tile are displayed
        ValidationUtil.validateTestStep("There is only up to 3 cars on the \"Last Chance\" pop-up" ,
                getDriver().findElements(By.xpath(pageObjectManager.getCarPage().xpath_LastChanceBookCarButton)).size() <= 3 );

//STEP 17/18 Validate the Car Image, Car Name Descriptions, Price per day, Excluded taxes, and book car button

        /*
        TODO: These element are on the page object model as webElement and need to be modified into List<WebElement>
         */

        int sizeOfCars = getDriver().findElements(By.xpath(pageObjectManager.getCarPage().xpath_LastChanceBookCarButton)).size();

        List<WebElement> CarImage   = getDriver().findElements(By.xpath(pageObjectManager.getCarPage().xpath_LastChanceCarLogoImage));
        List<WebElement> CarNameDescription = getDriver().findElements(By.xpath(pageObjectManager.getCarPage().xpath_LastChanceCarNameText));
        List<WebElement> CarPricePerDay = getDriver().findElements(By.xpath("//div[@class='modal-body']//div[@class='option-card-info-container']/div[3]/p[1]"));
        List<WebElement> CarExcludedTaxes = getDriver().findElements(By.xpath("//div[@class='modal-body']//div[@class='option-card-info-container']/div[3]/p[2]"));
        List<WebElement> CarBookButton = getDriver().findElements(By.xpath(pageObjectManager.getCarPage().xpath_LastChanceBookCarButton));

        //loop through each car on the pop-up and validate
        for(int i = 0 ; i < sizeOfCars ; i ++)
        {
            ValidationUtil.validateTestStep("The Car Image is displayed" , TestUtil.verifyElementDisplayed(CarImage.get(i)));
            ValidationUtil.validateTestStep("The Car Name Description is displayed", TestUtil.verifyElementDisplayed(CarNameDescription.get(i)));
            ValidationUtil.validateTestStep("The Car Price per day is displayed" , TestUtil.verifyElementDisplayed(CarPricePerDay.get(i)));
            ValidationUtil.validateTestStep("The Car Excluded taxes is displayed" , TestUtil.verifyElementDisplayed(CarExcludedTaxes.get(i)));
            ValidationUtil.validateTestStep("The book car button is displayed" , TestUtil.verifyElementDisplayed(CarBookButton.get(i)));
        }


//STEP 19 Validate the Continue Without Car button is displayed
        ValidationUtil.validateTestStep("The Continue without cars button is displayed" ,
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getLastChanceContinueWithoutCarButton()));

//STEP 20 Click on View All cars button
        pageObjectManager.getCarPage().getLastChanceViewAllCarsButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());


        final String CHECKIN_CARS_URL   = "check-in/cars";

        //validate the user is taken to the check-in Car page
        ValidationUtil.validateTestStep("The user is taken to the check-in/car page" , getDriver().getCurrentUrl() , CHECKIN_CARS_URL);

//STEP 21 Exit out of check in and retrieve booking again
        pageObjectManager.getHeader().getSpiritLogoImage().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(2000);

//STEP 22 Complete Steps 4 - 12 and validate that the last chance modal is displayed and click on the firs car option that is displayed
        completeSteps4Through12();
        WaitUtil.untilTimeCompleted(2000);

        ValidationUtil.validateTestStep("The Last Chance modal is displayed" ,
                TestUtil.verifyElementDisplayed( pageObjectManager.getCarPage().getLastChanceHeaderText()));

        //Click on the first Book car button on the last chance pop-up
        getDriver().findElements(By.xpath(pageObjectManager.getCarPage().xpath_LastChanceBookCarButton)).get(0).click();
        WaitUtil.untilPageLoadComplete(getDriver());

        //Validate that the options page is loaded
        final String CHECKIN_OPTIONS_PAGE_URL =  "check-in/extras";
        ValidationUtil.validateTestStep("The user is taken to the check-in/extras page" , getDriver().getCurrentUrl() , CHECKIN_OPTIONS_PAGE_URL);


//STEP 24 Exit out of check in and retrieve booking again
        pageObjectManager.getHeader().getSpiritLogoImage().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(2000);

//Step 25 complete steps 4-12
        completeSteps4Through12();
        WaitUtil.untilTimeCompleted(2000);

//STEP 26 click on continue without car button, options page should display
        ValidationUtil.validateTestStep("The Last Chance modal is displayed" ,
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getLastChanceHeaderText()));

        pageObjectManager.getCarPage().getLastChanceContinueWithoutCarButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("The user is taken to the check-in/extras page" , getDriver().getCurrentUrl() , CHECKIN_OPTIONS_PAGE_URL);


    }
    public void completeSteps4Through12()
    {
        //Reservation Summary page constant
        final String BAGS_POPUP         = "Purchase";
        final String CHECKIN_DEP_BAGS   = "Carry_1";


        final String SEATS_POPUP        = "Purchase";
        final String CHECKIN_DEP_SEATS  ="Standard||Standard";

//STEP- 3/4/5/6 Click on Check in tab and retrieve booking
        pageMethodManager.getHomePageMethods().loginToCheckIn();

        WaitUtil.untilPageLoadComplete(getDriver());

//STEP- 7/8 Begin check in process
        //Click on Check-in and get Boarding pass button
        pageMethodManager.getReservationSummaryPageMethods().clickCheckInAndGetBoardingPass();

//STEP 9 Select add Bags on the bags UpSell
        //accept add bags select a carry on and continue
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSaveOnBagsPopup(BAGS_POPUP);
//STEP 10 Select to add a carry on and continue
        pageMethodManager.getBagsPageMethods().selectDepartingBags(CHECKIN_DEP_BAGS);
        pageObjectManager.getBagsPage().getContinueWithStandardBagsContainerContinueButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

//STEP 11 accept add Seats and add any seat available
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseSelectYourSeatPopup(SEATS_POPUP);

//STEP 12 Select to any available seat and continue
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(CHECKIN_DEP_SEATS);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();
    }

    //TODO: Add the below Xpath to the cars page
    private WebElement getLastChanceOrSelectAQuickPick()
    {
        return getDriver().findElement(By.xpath("//div[@class='modal-body']//div[contains(@class,'select-quick-pick-label')]"));
    }
}