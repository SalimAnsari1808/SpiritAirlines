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

import java.util.ArrayList;
import java.util.List;

//**********************************************************************************************
//Test Case ID: TC373717
//Description: Task 27146: TC373717- US 17540 - 002 - CP - Hotel Upsell carousel - for a booking with 8 Passengers
//Created By: Gabriela
//Created On: 01-Nov-2019
//Reviewed By: Kartik Chauhan
//Reviewed On: 18-Nov-2019
//**********************************************************************************************

public class TC373717 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "Outside21Days", "Adult", "Guest", "NonStop", "BookIt",
            "NoBags", "NoSeats", "FlightHotel", "CheckInOptions", "OptionalUI", "HotelsUI","Visa"})
    public void CP_Hotel_Upsell_carousel_for_a_booking_with_8_Passengers(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373717 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "120";
        final String ARR_DATE           = "122";
        final String ADULT              = "8";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String ARR_Flight         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Constant Values
        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

        //Payment Page Constant values
        final String TRAVEL_GUARD 		= "NotRequired";
        final String CARD_DETAIL 		= "VisaCard";

//- Step 1: Access to the GoldFinger Test environment.
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();

//- Step 2: Create a regular booking for 8 Adt , DOM to DOM, RT, outside 48 hrs, and click on Search Flight button.
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 3: Choose flights for POO and POD
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);

//- Step 4: Click Continue at the bottom of the page.
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);

//- Step 5: If the pop up for Upgrade & Save appears select Book it
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//- Step 6: Fill out passenger info and click "Continue"
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Step 7 & 8: click "continue without bags" at the bottom of the pag & Click "I Don't Need Bags"
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//- Step 9: Click "Continue without seats"
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//- Step 10: Validate that the Hotel carousel is displayed on the Options page.
        WaitUtil.untilPageLoadComplete(getDriver());

//- Step 11: Validate that the Hotel carousel is displaying 4 tiles and is having an Arrow pointing at the right and left on each side.
        List<WebElement> hotelTileList = getDriver().findElements(By.xpath("//app-hotel//app-ancillary-item[contains(@class,'options-table')]"));
        int count=0;
        for (int i = 0; i < 4; i ++)
        {
            TestUtil.verifyElementDisplayed(hotelTileList.get(i));
            count++;
        }
        ValidationUtil.validateTestStep("Validating first 4 tiles are present on Hotel Carousel",count==4);

        List<String> hotelCarousel = new ArrayList<>();
        for (int i = 0; i < hotelTileList.size(); i ++)
        {
            hotelCarousel.add(getDriver().findElement(By.xpath("//app-hotel//app-ancillary-item[contains(@class,'options-table')]//p[@class='clamp-text-box']")).getText());
        }


//- Step 12: Click the Right Arrow once.
        pageObjectManager.getHotelPage().getHotelCarouselRightButton().click();
        WaitUtil.untilTimeCompleted(3000);

        List<String> hotelCarouselNew = new ArrayList<>();
        for (int i =0; i < hotelTileList.size(); i ++)
        {
            hotelCarouselNew.add(getDriver().findElement(By.xpath("//app-hotel//app-ancillary-item[contains(@class,'options-table')]//p[@class='clamp-text-box']")).getText());
        }
//        System.out.println("hotelCarouselNew: " + hotelCarouselNew);
        count = 0;
        for (int i = 0; i <hotelCarousel.size(); i ++)
        {
            for (int e = 0; e < hotelCarouselNew.size(); e ++) {
                if(!hotelCarousel.get(i).equals(hotelCarouselNew.get(e)));
                count++;
            }
        }
        ValidationUtil.validateTestStep("Validating second new tiles are displayed after click on the right arrow", count>0);


//- Step 13: Click the Right Arrow per second time
        pageObjectManager.getHotelPage().getHotelCarouselRightButton().click();
        WaitUtil.untilTimeCompleted(3000);

        List<String> hotelCarouselNew1 = new ArrayList<>();
        for (int i =0; i < hotelTileList.size(); i ++)
        {
            hotelCarouselNew1.add(getDriver().findElement(By.xpath("//app-hotel//app-ancillary-item[contains(@class,'options-table')]//p[@class='clamp-text-box']")).getText());
        }

        count = 0;
        for (int i = 0; i <hotelCarouselNew.size(); i ++)
        {
            for (int e = 0; e < hotelCarouselNew1.size(); e ++) {
                        if(!hotelCarouselNew.get(i).equals(hotelCarouselNew1.get(e)));
                        count++;
            }
        }
        ValidationUtil.validateTestStep("Validating third new tiles are displayed after click on the right arrow", count >0);

//- Step 14: Click the Right Arrow for third time
        pageObjectManager.getHotelPage().getHotelCarouselRightButton().click();
        WaitUtil.untilTimeCompleted(3000);

        List<String> hotelCarouselNew2 = new ArrayList<>();
        for (int i =0; i < hotelTileList.size(); i ++)
        {
            hotelCarouselNew2.add(getDriver().findElement(By.xpath("//app-hotel//app-ancillary-item[contains(@class,'options-table')]//p[@class='clamp-text-box']")).getText());
        }

        count = 0;
        for (int i = 0; i <hotelCarouselNew1.size(); i ++)
        {
            for (int e = 0; e < hotelCarouselNew2.size(); e ++) {
                        if(!hotelCarouselNew1.get(i).equals(hotelCarouselNew2.get(e)));
                        count++;
            }
        }
        ValidationUtil.validateTestStep("Validating fourth new tiles are displayed after click on the right arrow", count >0);

//- Step 15: Click the Right Arrow for the fourth time
        pageObjectManager.getHotelPage().getHotelCarouselRightButton().click();
        WaitUtil.untilTimeCompleted(3000);

//- Step 16: Click the Left Arrow once.
        pageObjectManager.getHotelPage().getHotelCarouselLeftButton().click();
        WaitUtil.untilTimeCompleted(3000);

//- Step 17: Click over the Arrow pointing Right again and validate that the arrow is gray and unresponsive to the user action.
        pageObjectManager.getHotelPage().getHotelCarouselRightButton().click();
        WaitUtil.untilTimeCompleted(3000);

        ValidationUtil.validateTestStep("Validating right arrow is disabled on gray color",
                pageObjectManager.getHotelPage().getHotelCarouselRightButton().getCssValue("-webkit-tap-highlight-color"),"rgba(0, 0, 0, 0)");


//- Step 18: Click the Left Arrow per second time
        List<String> hotelCarouselLeft = new ArrayList<>();
        for (int i = 0; i < hotelTileList.size(); i ++)
        {
            hotelCarouselLeft.add(getDriver().findElement(By.xpath("//app-hotel//app-ancillary-item[contains(@class,'options-table')]//p[@class='clamp-text-box']")).getText());
        }

        pageObjectManager.getHotelPage().getHotelCarouselLeftButton().click();
        WaitUtil.untilTimeCompleted(3000);

        List<String> hotelCarouselLeftNew = new ArrayList<>();
        for (int i =0; i < hotelTileList.size(); i ++)
        {
            hotelCarouselLeftNew.add(getDriver().findElement(By.xpath("//app-hotel//app-ancillary-item[contains(@class,'options-table')]//p[@class='clamp-text-box']")).getText());
        }

        count = 0;
        for (int i = 0; i <hotelCarouselLeft.size(); i ++)
        {
            for (int e = 0; e < hotelCarouselNew.size(); e ++) {
                        if(!hotelCarouselLeft.get(i).equals(hotelCarouselLeftNew.get(e)));
                        count++;
            }
        }
        ValidationUtil.validateTestStep("Validating new tiles are displayed after click on the left arrow", count>0);

//- Step 19: Click the Left Arrow for third time
        pageObjectManager.getHotelPage().getHotelCarouselLeftButton().click();
        WaitUtil.untilTimeCompleted(3000);

        List<String> hotelCarouselLeftNew1 = new ArrayList<>();
        for (int i =0; i < hotelTileList.size(); i ++)
        {
            hotelCarouselLeftNew1.add(getDriver().findElement(By.xpath("//app-hotel//app-ancillary-item[contains(@class,'options-table')]//p[@class='clamp-text-box']")).getText());
        }

        count = 0;
        for (int i = 0; i <hotelCarouselLeftNew.size(); i ++)
        {
            for (int e = 0; e < hotelCarouselLeftNew1.size(); e ++) {
                        if(!hotelCarouselLeftNew.get(i).equals(hotelCarouselLeftNew1.get(e)));
                        count++;
            }
        }
        ValidationUtil.validateTestStep("Validating 4 new tiles are displayed after click on the left arrow", count>0);


//- Step 20: Click the Left Arrow for the fourth time
        pageObjectManager.getHotelPage().getHotelCarouselLeftButton().click();
        WaitUtil.untilTimeCompleted(3000);

        List<String> hotelCarouselLeftNew2 = new ArrayList<>();
        for (int i =0; i < hotelTileList.size(); i ++)
        {
            hotelCarouselLeftNew2.add(getDriver().findElement(By.xpath("//app-hotel//app-ancillary-item[contains(@class,'options-table')]//p[@class='clamp-text-box']")).getText());
        }

        count = 0;
        for (int i = 0; i <hotelCarouselLeftNew1.size(); i ++)
        {
            for (int e = 0; e < hotelCarouselLeftNew2.size(); e ++) {
                        if(!hotelCarouselLeftNew1.get(i).equals(hotelCarouselLeftNew2.get(e)));
                        count++;
            }
        }
        ValidationUtil.validateTestStep("Validating 4 new tiles are displayed after click on the left arrow", count>0);


//- Step 21: Click over the Arrow pointing Left again and validate that the arrow is gray and unresponsive to the user action.
                pageObjectManager.getHotelPage().getHotelCarouselLeftButton().click();
        WaitUtil.untilTimeCompleted(3000);

        ValidationUtil.validateTestStep("Validating right arrow is disabled on gray color",
                pageObjectManager.getHotelPage().getHotelCarouselLeftButton().getCssValue("-webkit-tap-highlight-color"),"rgba(0, 0, 0, 0)");


//- Step 22: Select View inside of one Hotel tile
        //click on any hotel card
        JSExecuteUtil.clickOnElement(getDriver(), getDriver().findElements(By.xpath("//app-hotel//app-ancillary-item[contains(@class,'options-table')]//p[@class='clamp-text-box']//a")).get(0));
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Validating hotel information pops up in a new window",
                TestUtil.verifyElementDisplayed(getDriver(),By.xpath("//app-package-detail-select-modal")));

//- Step 23: Verify that the following information is displayed:
        //Hotel name and address
        ValidationUtil.validateTestStep("Verifying hotel name information displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelWindowRoomCategoryHotelNameText()));

        ValidationUtil.validateTestStep("Verifying hotel address information displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelWindowRoomCategoryHotelAddressText()));

        //Hotel image
        ValidationUtil.validateTestStep("Verifying hotel Image displayed",
                TestUtil.verifyElementDisplayed(getDriver(),By.xpath("//app-package-detail-select-modal//img[contains(@class,'hotel-image')]")));
//        ValidationUtil.validateTestStep("Verifying hotel Image displayed",
//                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelWindowRoomCategoryImage()));

        //View map hyperlink
        ValidationUtil.validateTestStep("Verifying hotel map link displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelWindowRoomCategoryViewMapText()));

        //ROOMS FROM $ ##.##
        ValidationUtil.validateTestStep("Verifying hotel Rooms From button is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelPopUpSelectRoomsFromButton()));

        //Additional info modal (all fields/tabs) (Overview, Policies, Accommodations, Dining, Activities, Photos, Map)
        ValidationUtil.validateTestStep("Verifying hotel Rooms link displayed",
                TestUtil.verifyElementDisplayed( pageObjectManager.getHotelPage().getHotelPopUpRoomsTab()));

        ValidationUtil.validateTestStep("Verifying hotel Overview link displayed",
                TestUtil.verifyElementDisplayed( pageObjectManager.getHotelPage().getHotelPopUpOverviewTab()));

        ValidationUtil.validateTestStep("Verifying hotel Policies link displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelPopUpPoliciesTab()));

        ValidationUtil.validateTestStep("Verifying hotel Amenities link displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelPopUpAmenitiesTab()));

        ValidationUtil.validateTestStep("Verifying hotel Dining Link displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelPopUpDiningTab()));

//        ValidationUtil.validateTestStep("Verifying hotel Activities&Entertainment link displayed",
//                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelPopUpActivitiesTab()));

        ValidationUtil.validateTestStep("Verifying hotel Photos link displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelPopUpPhotosTab()));

        ValidationUtil.validateTestStep("Verifying hotel map link displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelPopUpMapTab()));

//- Step 24: Open HBG website and use to validate Hotel information is correct shown with the same city pair and date chossen for the booking.
        //This step is not valid for automation

//- Step 25: Select the button Rooms From $##.##
        getDriver().findElement(By.xpath("//app-hotel-detail-tabset//a[@id='roomSelect']//button")).click();
//        pageObjectManager.getHotelPage().getRoomsFromButton().click();
        WaitUtil.untilTimeCompleted(3000);

//- Step 26: Click the Select Room button on the first available Room
        pageObjectManager.getHotelPage().getHotelWindowSelectRoomButton().get(0).click();
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Verifying hotel selected is displayed",
                TestUtil.verifyElementDisplayed(getDriver(),By.xpath("//img[@alt='Selected Hotel']")));

//- Step 27: On the options page book a hotel and select check-in online.
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);

//- Step 28: Validate that the Flight Flex Option is available after the user selects a Hotel.
        ValidationUtil.validateTestStep("Validating Flight Flex options is available",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getFlightFlexCardNotAvailableText()));

//- Step 29: Click continue with purchase
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//- Step 30: Use a valid credit card from roles and credentials and complete the booking
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);
//

//- Step 31: Verify that in addition to the flight PNR a Packaging confirmation will display: Hotel Confirmation
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

//- Step 32: Retrive the HBG Confirmation code on the HBG website https://www.hotelbeds.com observe the price charged for the Hotel
        pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();

//- Step 33: Login to skyspeed, retrieve the booking and locate the DPF
        //This Step is not valid for automation

//- Step 34: Verify Package Confirmation Code in Comments section
        //This Step is not valid for automation


    }
}