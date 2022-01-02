package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.*;
import com.spirit.pageObjects.*;
import com.spirit.utility.*;
import org.omg.CORBA.portable.ValueInputStream;
import org.openqa.selenium.*;
import org.testng.annotations.Optional;
import org.testng.annotations.*;

import java.util.*;

//**********************************************************************************************
//TODO: [IN:25595]	GoldFinger R1: CP: BP: F+H: Availability Page. Same picture displayed twice for The D Las Vegas hotel.
//Test Case ID: TC349822
//Description :  CP - Flight + Hotel - Availability Page - Validate Tile components and functionality
//Created By  : Sunny Sok
//Created On  : 21-Nov-2019
//Reviewed By : Gabriela
//Reviewed On : 10-Dec-2019
//**********************************************************************************************

public class TC349822 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"ActiveBug", "BookPath", "FlightHotel", "DomesticDomestic", "Outside21Days", "Adult", "Guest"})
    public void CP_Flight_Hotel_Availability_Page_Validate_Tile_components_and_functionality(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC349822 under GoldFinger Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "90";
        final String ARR_DATE           = "100";
        final String ADULTS             = "2";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String HOTEL_ROOM         = "1 Room";

        //Validation  Flight + Hotel - Availability Page
        final String FLIGHTANDHOTELS_URL = "/flights-hotels";

        //Cars Page
        final String CAR_URL        = "/book/options/cars";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectHotelRoom(HOTEL_ROOM);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        ValidationUtil.validateTestStep("User navigated to Flights + Hotels page", getDriver().getCurrentUrl(), FLIGHTANDHOTELS_URL);

        JSExecuteUtil.scrollDownToElementVisible(getDriver(), pageObjectManager.getHotelPage().getChooseYourHotelHeaderText());

        //TODO: step 4 - Validate the first tile on the list is Outlined blue , Labeled DEAL OF THE DAY, There is a count down clock right aligned
        String HotelImage1, HotelImage2;
        int hotels = pageObjectManager.getHotelPage().getHotelCard().size();
        for (int hotelCounter = 0; hotelCounter < hotels; hotelCounter++) {
            //step 5- Validate each tile contains a Picture Carousel, Locate and click the left and right arrows on the Hotel Picture, Validate a new picture shows with every click.
            if (hotelCounter == hotels-1){break;}
            TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelCardImageImage().get(hotelCounter));

            pageObjectManager.getHotelPage().getHotelCardImageImage().get(hotelCounter).click();
            WaitUtil.untilPageLoadComplete(getDriver());

            pageObjectManager.getHotelPage().getHotelImageLeftSlideButton().get(hotelCounter).click();
            WaitUtil.untilPageLoadComplete(getDriver());
            HotelImage1 = pageObjectManager.getHotelPage().getHotelCardSlideImage().get(0).getAttribute("src");

            pageObjectManager.getHotelPage().getHotelImageRightSlideButton().get(hotelCounter).click();
            HotelImage2 = pageObjectManager.getHotelPage().getHotelCardSlideImage().get(0).getAttribute("src");
            System.out.println(hotelCounter + "\n" + HotelImage1 +"\n" + HotelImage2);

            //TODO: [IN:25595]	GoldFinger R1: CP: BP: F+H: Availability Page. Same picture displayed twice for The D Las Vegas hotel.
            ValidationUtil.validateTestStep("User Validate the images are different", !HotelImage1.equals(HotelImage2));

            //step 6- Validate each tile contains the Hotel Name
            ValidationUtil.validateTestStep("User Validate each Hotel Tile contains a Hotel name",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelNamesText().get(hotelCounter)));

            //step 7- Validate each tile contains the Hotel Area right under the Hotel Name.
            ValidationUtil.validateTestStep("User Validate each Hotel Tile contains Hotel Area",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelAddressText().get(hotelCounter)));

            //step 8- Click the Hotel Name and validate it opens the "Hotel Details" tab
            //Validate the hotel address shows when tile is expanded.
            pageObjectManager.getHotelPage().getHotelNamesText().get(hotelCounter).click();
            WaitUtil.untilPageLoadComplete(getDriver());
            ValidationUtil.validateTestStep("User Validates Hotel details are displayed after Hotel name is clicked",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelPageDetailsPanel().get(0)));

            //Step 9- Validate each tile contains icons
            //step 10- Validate each icon highlights when hover to indicate it can be selected.
            //step 11- Select each icon and validate tile expands to display pertinent tab details
            //Steps are unable to automate

            //step 12- Validate each tile contains 2 difference pricing: $Price per person, $Price Total
            ValidationUtil.validateTestStep("User verify price per person is displayed",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getStartingFromPricePerPersonText().get(hotelCounter)));

            ValidationUtil.validateTestStep("User verify Total price is displayed",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getTotalPriceText().get(hotelCounter)));

//        step 13- If the Total booking is $200 of more, validate each tile contains the Uplift option is displaying
            String totalPrice1 = pageObjectManager.getHotelPage().getTotalPriceText().get(hotelCounter).getText().replace("$","");
            String totalPrice2 = totalPrice1.replace(" Total","");
            double totalPrice = Double.parseDouble(totalPrice2.replace(",",""));
          if (totalPrice >= 300) {
              ValidationUtil.validateTestStep("User verify Uplift option is displaying is displayed",
                      TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelPageUpliftLink().get(hotelCounter)));
          }
            //step 14- Validate there is a SELECT ROOM button, click on it
            ValidationUtil.validateTestStep("Validating 'Select Room' button is displayed on each tile",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelViewButton().get(hotelCounter)));
        }

//Step 14: Validate there is a SELECT ROOM button, click on it
        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getHotelPage().getHotelViewButton().get(0));
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Validating room modal pops up",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelPopUpSelectRoomsFromButton()));

// Step 15: Make a hotel selection
        JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getHotelPage().getHotelPopUpSelectRoomsFromButton());
        WaitUtil.untilTimeCompleted(1200);

        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getHotelPage().getHotelPopUpSelectRoomButton().get(0));

// Step 16: Validate there is a Continue hyperlink above the REMOVE button. Click on it
        //Invalid Step.. user is automatically taken to next page

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("User navigated to Flights + Hotels page", getDriver().getCurrentUrl(), CAR_URL);

    }
}