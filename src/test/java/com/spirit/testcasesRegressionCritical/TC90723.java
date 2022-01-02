package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//**********************************************************************************************
//Test Case ID: TC90723
//Description : Dynamic_Shopping_Cart_Passenger_Page_WireFrame_Vacation_Package
//Created By : Anthony Cardona
//Created On : 30-JUL-2019
//Reviewed By: Salim Ansari
//Reviewed On: 1-AUG-2019
//**********************************************************************************************

public class TC90723 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "RoundTrip" , "FlightHotel" , "DomesticDomestic" , "Outside21Days" , "Adult" , "Guest" , "BookIt" ,"PassengerInformationUI", "DynamicShoppingCartUI"})
    public void Dynamic_Shopping_Cart_Passenger_Page_WireFrame_Vacation_Package(@Optional("NA") String platform) {

        /******************************************************************************
         ***********************Navigate to Passenger Info Page************************
         ******************************************************************************/

        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90723 under REGRESSION CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE               = "English";
        final String JOURNEY_TYPE           = "Vacation";
        final String TRIP_TYPE              = "Flight+Hotel";
        final String DEP_AIRPORTS           = "AllLocation";
        final String DEP_AIRPORT_CODE       = "FLL";
        final String ARR_AIRPORTS           = "AllLocation";
        final String ARR_AIRPORT_CODE       = "LAS";
        final String DEP_DATE               = "60";
        final String ARR_DATE               = "65";
        final String ADULTS                 = "1";
        final String CHILDS                 = "0";
        final String INFANT_LAP             = "0";
        final String INFANT_SEAT            = "0";
        final String HOTELROOM              = "1 Room";


        final String UPGRADE_VALUE          = "BookIt";

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
        pageMethodManager.getHomePageMethods().selectHotelRoom(HOTELROOM);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Complete the flight + Hotel page

        pageObjectManager.getHotelPage().getHotelViewButton().get(0).click();
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);
        String flighPlusHotelPrice = pageObjectManager.getHotelPage().getHotelWindowRoomPricesButton().getText().replace("ROOMS FROM " , "").trim();
        pageObjectManager.getHotelPage().getHotelPopUpSelectRoomsFromButton().click();
        pageObjectManager.getHotelPage().getHotelPopUpSelectRoomButton().get(0).click();

        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);

        pageObjectManager.getHotelPage().getContinueButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);

        pageObjectManager.getCarPage().getCarsPageContinueWithoutCarButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);

        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);

        //Constant Values to validate
        final String PASSENGER_URL      = "book/passenger";
        final String BACKGROUND_GRAY    = "239, 239, 239";
        final String BLACK_RGB          = "0, 0, 0, 1";
        final String WHITE_RGB          = "255, 255, 255";
        final String BLUE_RGB           = "0, 115, 230";

//Step 1: Validate the user lands on the passenger information page
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Verify user navigated to Passenger Info page",
                getDriver().getCurrentUrl(), PASSENGER_URL);

//Step 2: User validates the breadcrumbs
        String[] breadCrumbText = { "Flight + Hotel" , "Passenger", "Bags" ,  "Seats" , "Options" , "Payment" , "Confirmation" };

        //get to the passenger information page
        List<WebElement> breadCrumbs = pageObjectManager.getHeader().getBreadCumListText();
        for(int count = 0; count < breadCrumbs.size(); count++) {
            if (breadCrumbs.get(count).isDisplayed()) {
                ValidationUtil.validateTestStep("Verifying BreadCrumb " + breadCrumbText[count] + " is Displayed" ,
                        breadCrumbs.get(count).getText(),(breadCrumbText[count]));
            }
        }

        for(WebElement check:pageObjectManager.getHeader().getBreadCumCheckMarkImage()){
            ValidationUtil.validateTestStep("BreadCrumb check mark back ground color is blue",
                    JSExecuteUtil.getElementAfterProperty(getDriver() , check , "background-color").contains(BLUE_RGB));
            ValidationUtil.validateTestStep("BreadCrumb check mark color is white",
                    JSExecuteUtil.getElementAfterProperty(getDriver() , check.findElement(By.tagName("i")), "color").contains(WHITE_RGB));
        }

        ValidationUtil.validateTestStep("BreadCrumb Active Step Number back ground color is black",
                JSExecuteUtil.getElementAfterProperty(getDriver() , pageObjectManager.getHeader().getYourItineraryPanel() , "background-color").contains(BLACK_RGB));

        ValidationUtil.validateTestStep("BreadCrumb Active Step Number color is white",
                JSExecuteUtil.getElementAfterProperty(getDriver() ,
                pageObjectManager.getHeader().getBreadCumActiveStepNumberText().findElement(By.tagName("p")) , "color").contains(WHITE_RGB));


//Step 3:
        //Validate Shopping Cart is present
        ValidationUtil.validateTestStep("Verify user Shopping Cart is Displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getItineraryPanel()));

        //Verify A Grey text block, left aligned icon of cart followed by verbiage in black font: Your Itinerary and right aligned $XXX (dynamic pricing).
        ValidationUtil.validateTestStep("Verify A Grey text block",
                JSExecuteUtil.getElementAfterProperty(getDriver() , pageObjectManager.getHeader().getYourItineraryPanel() , "background-color").contains(BACKGROUND_GRAY));

        ValidationUtil.validateTestStep("Verify Your Itinerary is Displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getYourItineraryText()));

        ValidationUtil.validateTestStep("Verify dynamic pricing Displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getItineraryTotalAmountText()));

        ValidationUtil.validateTestStep("Verify dynamic pricing correct",
                pageObjectManager.getHeader().getItineraryTotalAmountText().getText() , flighPlusHotelPrice);

        ValidationUtil.validateTestStep("Verify Caret is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getArrowYourItineraryImage()));

//Step 4: Expand caret and validate the boxes and text
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();

        //verify Icon of an airplane
        ValidationUtil.validateTestStep("verify Icon of an airplane is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getFlightItineraryImage()));

        //verify flight label
        ValidationUtil.validateTestStep("verify flight label is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getFlightItineraryText()));

        WaitUtil.untilTimeCompleted(1200);

        ValidationUtil.validateTestStep("Verify dynamic pricing correct",
                pageObjectManager.getHeader().getFlightItineraryText().getText() , "Flight + Hotel");

        //verify flight dynamic pricing
        ValidationUtil.validateTestStep("verify flight label is displayed",
                pageObjectManager.getHeader().getFlightPriceItineraryText().getText() , flighPlusHotelPrice);

        //verify arrow
        ValidationUtil.validateTestStep("verify arrow  is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getArrowFlightItineraryImage()));

// Step 5: Open the subtext box and validate POO and POD
        pageObjectManager.getHeader().getArrowFlightItineraryImage().click();
        //verify date format
        ValidationUtil.validateTestStep("Verify date format",
                validateDateFormat(pageObjectManager.getHeader().getFlightDateItineraryText().get(0).getText()));
        //verify city pair
        ValidationUtil.validateTestStep("verify POO – POD",
                DEP_AIRPORT_CODE + " - " + ARR_AIRPORT_CODE, pageObjectManager.getHeader().getAirportFlightItineraryText().get(0).getText());

        //verify Edit link(blue font)
        ValidationUtil.validateTestStep("verify Edit link(blue font) is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHeader().getEditFlightItineraryButton())
                        && JSExecuteUtil.getElementAfterProperty(getDriver() ,pageObjectManager.getHeader().getEditFlightItineraryButton(), "color").contains(BLUE_RGB));

        WaitUtil.untilPageLoadComplete(getDriver(),(long)2000);

//Step 6: Invalid step as Flight Standard fare will already be selected and user will not always get 9DFC savings banner
//        //verify $9FC tile, yellow background, with black font, left aligned verbiage: JOIN $9FC AND SAVE
//        //$XXX! (this amount is dynamic) right aligned down button / caret.
//        ValidationUtil.validateTestStep("Verify yellow background",
//                getDriver().findElement(By.xpath("//div[contains(@class,'flight-club-savings')]")).getCssValue("background-color"),BACKGROUND_YELLOW);
//
//        ValidationUtil.validateTestStep("Verify Font is black",
//                pageObjectManager.getHeader().getJoinFareClubAndSaveItineraryText().getCssValue("color"), FONT_BLACK);
//
////        ValidationUtil.validateTestStep("Verify verbiage: JOIN AND SAVE (link)",
////                pageObjectManager.getHeader().getJoinFareClubAndSaveItineraryText().getText(), JOIN_SAVE_TEXT);


    }
    private boolean validateDateFormat(String strDate) {
        /*
         * Set preferred date format,
         * For example MM-dd-yyyy, MM.dd.yyyy,dd.MM.yyyy etc.*/
        SimpleDateFormat sdfrmt = new SimpleDateFormat("E, MMM d, YYYY");
        sdfrmt.setLenient(false);
        /* Create Date object
         * parse the string into date
         */
        try {
            Date javaDate = sdfrmt.parse(strDate);
            System.out.println(strDate+" is valid date format");
            return true;
        }
        /* Date format is invalid */
        catch (Exception e) {
            System.out.println(strDate+" is Invalid Date format");
            return false;
        }
    }
}